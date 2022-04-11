import java.lang.reflect.Array;
import java.util.ArrayList;

public class PV extends LVM{
    private HD hardDrive;
    private boolean inVG;
    private VG vg;
    private static ArrayList<PV> pvList = new ArrayList<PV>(); //all pvs
    private static ArrayList<String> pvNames = new ArrayList<String>(); //list of pv names

    public PV(String name, HD hardDrive){
        super(name, hardDrive.getSize());
        this.hardDrive = hardDrive;
        inVG = false;
        vg = null;
    }

    public static void addPV(PV pv){
        pvList.add(pv);
        pvNames.add(pv.getName());
    }

    public void inVG(VG vg){
        this.vg = vg;
        inVG = true;
    }

    public void associatedHD(HD hd){
        hardDrive = hd;
    }

    public boolean getInVG(){
        return inVG;
    }

    public HD getHardDrive(){
        return hardDrive;
    }

    public VG getVG(){
        return vg;
    }

    public static ArrayList<PV> getPvList(){
        return pvList;
    }

    public static ArrayList<String> getPvNames(){
        return pvNames;
    }
}