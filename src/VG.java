import java.util.ArrayList;

public class VG extends LVM{
    private String freeSpace;
    private String totalSpace;

    public VG(String name, ArrayList<PV> pv, ArrayList<LV> lv){
        super(name);
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

    public String getFreeSpace(){
        return freeSpace;
    }

    public String getTotalSpace(){
        return totalSpace;
    }
}
