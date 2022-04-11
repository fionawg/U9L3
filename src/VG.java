import java.util.ArrayList;

public class VG extends LVM{
    private int freeSpace;
    private int totalSpace;
    private ArrayList<PV> pv;
    private ArrayList<LV> lv;
    private static ArrayList<VG> vgList = new ArrayList<VG>(); //all vgs
    private static ArrayList<PV> usedPV = new ArrayList<PV>(); //pvs associated with a vg
    private static ArrayList<String> vgNames = new ArrayList<String>(); //list of vg names

    public VG(String name){
        super(name);
        freeSpace = 0;
        totalSpace = 0;
        pv = new ArrayList<PV>();
        lv = new ArrayList<LV>();
    }

    public static void addUsedPV(PV x){
        usedPV.add(x);
    }

    public void addPV(PV x){
        pv.add(x);
    }

    public void addLV(LV x){
        lv.add(x);
    }

    public static void addVG(VG vg){
        vgNames.add(vg.getName());
        vgList.add(vg);
    }

    public void calculateSpace(){
        int totalS = 0;
        String str = "";
        String numbers = "";
        for (PV p : pv){
            str = p.getSize();
            numbers = str.replaceAll("[^0-9]", "");
            totalS += Integer.parseInt(numbers);
        }
        int freeS = totalS;
        for (LV l : lv){
            str = l.getSize();
            numbers = str.replaceAll("[^0-9]", "");
            freeS -= Integer.parseInt(numbers);
        }
        freeSpace = freeS;
        totalSpace = totalS;
    }

    public static ArrayList<VG> getVgList(){
        return vgList;
    }

    public static ArrayList<PV> getUsedPv(){
        return usedPV;
    }

    public ArrayList<PV> getPV(){
        return pv;
    }

    public static ArrayList<String> getVgNames(){
        return vgNames;
    }

    public int getFreeSpace(){
        return freeSpace;
    }

    public int getTotalSpace(){
        return totalSpace;
    }
}