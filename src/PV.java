import java.util.ArrayList;

public class PV extends LVM{
    private HD hardDrive;

    public PV(String name, HD hardDrive){
        super(name, hardDrive.getSize());
        this.hardDrive = hardDrive;
    }

    public HD getHardDrive(){
        return hardDrive;
    }
}
