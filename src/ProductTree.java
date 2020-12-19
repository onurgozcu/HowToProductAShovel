import java.util.ArrayList;

public class ProductTree {
    private Material root;

    public void add(Material materialToAdd, Material parent){
        if (isLeaf(parent)){
            parent.childList = new ArrayList<>();
        }
        parent.childList.add(materialToAdd);
    }

    public boolean isLeaf(Material m){
        return m.childList == null;
    }

    public Material getRoot(){
        return root;
    }

    public void setRoot(Material root){
        this.root = root;
    }

    public Material findById(Material mat, String id){
        if (mat == null){
            return null;
        }
        if (mat.id.matches(id)){
            return mat;
        }
        if (!isLeaf(mat)){
            for (Material child : mat.childList) {
                if (findById(child,id) != null){
                    return child;
                }

            }
        }
        return null;
    }

    public void print(Material material){
        for (int i = 0; i<7; ++i){
            System.out.println("------> Row: "+(i+1));
            for (int j = 0; j<10; ++j){
                System.out.println(material.name + " id: " + material.id + " "+ material.values[i][j] +" in week" + (j+1));
            }
            System.out.println();
        }
        if (!isLeaf(material)){
            for (Material c : material.childList) {
                print(c);
            }
        }
    }

}
