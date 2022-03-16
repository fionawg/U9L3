public class LV extends LVM{
    private VG vg;

    public LV(String name, int size, VG vg){
        super(name, size);
        this.vg = vg;
    }
}
