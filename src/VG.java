import java.util.ArrayList;

public class VG extends LVM{
    private String freeSpace;
    private String totalSpace;
    private ArrayList<PV> pv;
    private ArrayList<LV> lv;
    private static ArrayList<VG> vgList = new ArrayList<VG>();//all vgs
    private static ArrayList<PV> usedPV = new ArrayList<PV>(); //pvs associated with a vg
    private static ArrayList<String> vgNames = new ArrayList<String>(); //list of vg names

    public VG(String name){
        super(name);
        freeSpace = "";
        totalSpace = "";
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
        String str = pv.get(0).getSize();
        String unit = str.replaceAll("1234567890", "");
        for (PV p : pv){
            totalS += Integer.parseInt(p.getSize());
        }
        int freeS = totalS;
        for (LV l : lv){
            freeS -= Integer.parseInt(l.getSize());
        }
        totalSpace = "" + totalS + unit;
        freeSpace = "" + freeS + unit;
    }

    public ArrayList<VG> getVgList(){
        return vgList;
    }

    public ArrayList<PV> getUsedPv(){
        return usedPV;
    }

    public ArrayList<String> getVgNames(){
        return vgNames;
    }

    public String getFreeSpace(){
        return freeSpace;
    }

    public String getTotalSpace(){
        return totalSpace;
    }
}