import java.lang.reflect.Array;
import java.util.ArrayList;

public class PV extends LVM{
    private HD hardDrive;
    private boolean inVG;

    public PV(String name, HD hardDrive){
        super(name, hardDrive.getSize());
        this.hardDrive = hardDrive;
        inVG = false;
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
