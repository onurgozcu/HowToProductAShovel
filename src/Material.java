import java.util.ArrayList;

public class Material {
    int[][] values;
    String id;
    String name;
    int leadTime, lotSizing, required;
    ArrayList<Material> childList;

    public Material(String id, String name, int leadTime, int lotSizing, int required, ArrayList<Material> childList) {
        this.values = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
        this.id = id;
        this.name = name;
        this.leadTime = leadTime;
        this.lotSizing = lotSizing;
        this.required = required;
        this.childList = childList;
    }

    public Material(int[][] values, String id, String name, int leadTime, int lotSizing, int required, ArrayList<Material> childList) {
        this.values = values;
        this.id = id;
        this.name = name;
        this.leadTime = leadTime;
        this.lotSizing = lotSizing;
        this.required = required;
        this.childList = childList;
    }

    public boolean equals(Material m){
        return this.id.equals(m.id);
    }
}
