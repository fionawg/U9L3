public class LV extends LVM{
    private VG vg;

    public LV(String name, String size, VG vg){
        super(name, size);
        this.vg = vg;
    }
}
