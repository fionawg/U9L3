import java.lang.reflect.Array;
import java.util.ArrayList;

public class PV extends LVM{
    private HD hardDrive;
    private boolean inVG;
    private VG vg;

    public PV(String name, HD hardDrive){
        super(name, hardDrive.getSize());
        this.hardDrive = hardDrive;
        inVG = false;
        vg = null;
    }

    public void inVG(VG vg){
        this.vg = vg;
    }

    public boolean getInVG(){
        return inVG;
    }

    public HD getHardDrive(){
        return hardDrive;
    }

    public void inVolumeGroup(){
        inVG = true;
    }
}
