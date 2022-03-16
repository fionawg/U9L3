import java.util.ArrayList;

public class VG extends LVM{
    private ArrayList<PV> pv; //add every time PV is created
    private ArrayList<LV> lv; //add every time LV is created
    private int freeSpace;
    private int totalSpace;

    public VG(String name){
        super(name);
        totalSpace = 0;
        for (PV p : pv){
            totalSpace += p.getSize();
        }
        freeSpace = totalSpace;
        for (LV l : lv){
            freeSpace -= l.getSize();
        }
    }

    public void addPV(){

    }

    public int getFreeSpace(){
        return freeSpace;
    }
}
