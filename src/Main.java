import java.util.HashMap;

public class Main {

    static Inventory inventory;
    static ProductTree productTree;

    public static void main(String[] args) {

        Material shovel = new Material("1605", "Snow Shovel", 1, 1, 1, null);
        Material topHandle = new Material("13122", "Top handle", 1, 40, 1, null);
        Material scoopShaft = new Material("048", "Scoop-Shaft", 3, 30, 1, null);
        Material shaft = new Material("118", "Shaft", 2, 1, 1, null);
        Material nail = new Material("062", "Nail", 2, 1, 4, null);
        Material rivet = new Material("14127", "Rivet", 1, 100, 4, null);
        Material scoopAssembly = new Material("314", "Scoop-Assembly", 1, 50, 1, null);
        Material topHandle2 = new Material("457", "Top handle", 2, 1, 1, null);
        Material nail2 = new Material(nail.values ,"062", "Nail", 2, 1, 2, null);
        Material bracelet = new Material("11495", "Bracelet", 1, 50, 1, null);
        Material topHandle3 = new Material("129", "Top handle", 4, 40, 1, null);
        Material topHandle4 = new Material("1118", "Top handle", 3, 1, 1, null);
        Material scoop = new Material("2142", "Scoop", 2, 100, 1, null);
        Material blade = new Material("019", "Blade", 2, 50, 1, null);
        Material rivet2 = new Material(rivet.values ,"14127", "Rivet", 1, 100, 6, null);

        productTree = new ProductTree(shovel);

        productTree.add(topHandle,shovel);
        productTree.add(scoopShaft,shovel);
        productTree.add(shaft,shovel);
        productTree.add(nail,shovel);
        productTree.add(rivet,shovel);
        productTree.add(scoopAssembly,shovel);

        productTree.add(topHandle2,topHandle);
        productTree.add(nail2,topHandle);
        productTree.add(bracelet,topHandle);

        productTree.add(topHandle3,bracelet);
        productTree.add(topHandle4,bracelet);

        productTree.add(scoop,scoopAssembly);
        productTree.add(blade,scoopAssembly);
        productTree.add(rivet2,scoopAssembly);

        inventory = new Inventory();
        setInventory();

        int[] grossReqForShovel = {0,0,0,60,100,0,50,0,30,0};

        for (int i = 0; i<productTree.getRoot().values[0].length; i++){
            productTree.getRoot().values[0][i] = grossReqForShovel[i];
        }



        produce(productTree.getRoot());

        for (int i = 0; i < 10; i++){
            System.out.println("topHandle3: "+ topHandle3.values[0][i] +" in week" + (i+1));
        }

        System.out.println();
        System.out.println();


        for (int i = 0; i < 10; i++){
            System.out.println("topHandle4: "+ topHandle4.values[0][i] +" in week" + (i+1));
        }

        for (int i = 0; i < 10; i++){
            System.out.println("topHandle: "+ topHandle.values[0][i] +" in week" + (i+1));
        }

        System.out.println();

        for (int i = 0; i < 10; i++){
            System.out.println("scope shaft: " + scoopShaft.values[0][i] +" in week" + (i+1));
        }

        System.out.println();

        for (int i = 0; i < 10; i++){
            System.out.println("shaft: " + shaft.values[0][i] +" in week" + (i+1));
        }

        System.out.println();

        for (int i = 0; i < 10; i++){
            System.out.println("nail: " + nail.values[0][i] +" in week" + (i+1));
        }

        System.out.println();

        for (int i = 0; i < 10; i++){
            System.out.println("scopeass: " + scoopAssembly.values[0][i] +" in week" + (i+1));
        }

        System.out.println();

        for (int i = 0; i < 10; i++){
            System.out.println("rivet: " + rivet.values[0][i] +" in week" + (i+1));
        }

        //shovel hangi hafta isteniyor
        //tespit edildi 4. hafta 60
        //depoda 60 tane shovel var mı?(Envanter sınıfı yaz orda amount on hand değerini kontrol et)
        //varsa bitir, varsa ama 60 tane değilse kaç tane gerekli olduğunu tekrar hesapla
        //bir önceki adımdaki işlemi scheduled recipt için de yap (scheduled recipt'e de envanterden eriş) --> gerekliShovel(30)
        //shovel'ı üretmek için neye ihtiyacımız var?
        //shovel'ın childlarına ihtiyacımız var
        //childa git gerekliShovel*required depo kontrol + scheduled recipt kontrol
        // gerekli Tophandle

    }

    private static void produce(Material tempMaterial) {
        for (int i = 0; i<10; i++){
            int week = i+1;
            int wantedCount = tempMaterial.values[0][i];

            tempMaterial.values[2][i] = inventory.checkOnHand(tempMaterial.id);

            if (wantedCount > 0){

                wantedCount -= inventory.checkOnHand(tempMaterial.id);
                inventory.resetFromHand(tempMaterial.id);

                if (inventory.checkReceipt(tempMaterial.id) > 0){

                    if (inventory.checkArrivalWeek(tempMaterial.id) <= week){
                        wantedCount -= inventory.checkReceipt(tempMaterial.id);
                        inventory.resetFromReceipt(tempMaterial.id);
                        inventory.resetArrivalWeek(tempMaterial.id);
                    }

                }

                if (wantedCount <= 0){
                    inventory.addOnHand(tempMaterial.id,Math.abs(wantedCount));
                }else {
                    if (!productTree.isLeaf(tempMaterial)){
                        for (Material child: tempMaterial.childList){
                            if (wantedCount%tempMaterial.lotSizing != 0){
                                wantedCount += tempMaterial.lotSizing - (wantedCount%tempMaterial.lotSizing);
                            }
                            child.values[0][i-tempMaterial.leadTime] = wantedCount*child.required;
                            produce(child);
                        }
                    }
                }
            }
        }
    }

    public static void setInventory(){
        HashMap<String,Integer> aoh = new HashMap<>();
        HashMap<String,Integer> sr = new HashMap<>();
        HashMap<String,Integer> aow = new HashMap<>();

        aoh.put("1605",30);
        aoh.put("13122",0);
        aoh.put("048",30);
        aoh.put("118",0);
        aoh.put("062",50);
        aoh.put("14127",60);
        aoh.put("314",0);
        aoh.put("457",0);
        aoh.put("11495",120);
        aoh.put("129",0);
        aoh.put("1118",30);
        aoh.put("2142",80);
        aoh.put("019",50);

        sr.put("1605",0);
        sr.put("13122",70);
        sr.put("048",0);
        sr.put("118",50);
        sr.put("062",100);
        sr.put("14127",0);
        sr.put("314",50);
        sr.put("457",20);
        sr.put("11495",0);
        sr.put("129",100);
        sr.put("1118",0);
        sr.put("2142",0);
        sr.put("019",40);

        aow.put("1605",0);
        aow.put("13122",3);
        aow.put("048",0);
        aow.put("118",2);
        aow.put("062",6);
        aow.put("14127",0);
        aow.put("314",5);
        aow.put("457",2);
        aow.put("11495",0);
        aow.put("129",8);
        aow.put("1118",0);
        aow.put("2142",0);
        aow.put("019",5);

        inventory.setAmountOnHand(aoh);
        inventory.setScheduledReceipt(sr);
        inventory.setArrivalWeek(aow);
    }
}