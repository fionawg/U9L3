import java.util.ArrayList;

public class LV extends LVM{
    private VG vg;
    private static ArrayList<String> lvNames = new ArrayList<String>(); //list of lv names
    private static ArrayList<LV> lvList = new ArrayList<LV>();//all lvs

    public LV(String name, String size, VG vg){
        super(name, size);
        this.vg = vg;
    }

    public static void addLV(LV x){
        lvList.add(x);
        lvNames.add(x.getName());
    }

    public void inVG(VG vg){
        this.vg = vg;
    }

    public static ArrayList<String> getLvNames(){
        return lvNames;
    }

    public static ArrayList<LV> getLvList(){
        return lvList;
    }

    public VG getVg(){
        return vg;
    }
}
