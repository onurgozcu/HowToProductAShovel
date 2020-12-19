import java.util.HashMap;

public class Inventory {

    HashMap<String, Integer> amountOnHand;
    HashMap<String, Integer> scheduledReceipt;
    HashMap<String, Integer> arrivalWeek;

    public Inventory(){
        amountOnHand = new HashMap<>();
        scheduledReceipt = new HashMap<>();
        arrivalWeek = new HashMap<>();
    }


    public HashMap<String,Integer> getAmountOnHand() {
        return amountOnHand;
    }

    public HashMap<String, Integer> getScheduledReceipt() {
        return scheduledReceipt;
    }

    public HashMap<String, Integer> getArrivalWeek() {
        return arrivalWeek;
    }



    public void setAmountOnHand(HashMap<String,Integer> amountOnHand) {
        this.amountOnHand = amountOnHand;
    }

    public void setScheduledReceipt(HashMap<String, Integer> scheduledReceipt) {
        this.scheduledReceipt = scheduledReceipt;
    }

    public void setArrivalWeek(HashMap<String, Integer> arrivalWeek) {
        this.arrivalWeek = arrivalWeek;
    }



    public int checkOnHand(String id){
        return amountOnHand.get(id);
    }

    public int checkReceipt(String id){
        return scheduledReceipt.get(id);
    }

    public int checkArrivalWeek(String id){
        return arrivalWeek.get(id);
    }



    public void addOnHand(String id, int number){
        if (amountOnHand.get(id) == null){
            amountOnHand.put(id,number);
            return;
        }
        amountOnHand.put(id,amountOnHand.get(id)+number);
    }

    public void addScheduledReceipt(String id, int number){
        if (scheduledReceipt.get(id) == null){
            scheduledReceipt.put(id,number);
            return;
        }
        scheduledReceipt.put(id,scheduledReceipt.get(id)+number);
    }

    public void addArrivalOnWeek(String id, int number){
        if (arrivalWeek.get(id) == null){
            arrivalWeek.put(id,number);
            return;
        }
        arrivalWeek.put(id,arrivalWeek.get(id)+number);
    }



    public void resetFromHand(String id){
        amountOnHand.put(id,0);
    }

    public void resetScheduledReceipt(String id){
        scheduledReceipt.put(id,0);
    }

    public void resetArrivalWeek(String id){
        arrivalWeek.put(id,0);
    }

}
