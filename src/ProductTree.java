import java.util.ArrayList;

public class ProductTree {
    private Material root;

    public ProductTree(Material root){
        this.root = root;
    }

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
}
