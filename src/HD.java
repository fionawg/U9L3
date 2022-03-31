import java.util.ArrayList;

public class HD {
    private String name;
    private String size;
    private boolean withPV;
    private static ArrayList<HD> hdList = new ArrayList<HD>();  //all hds
    private static ArrayList<String> hdNames = new ArrayList<String>(); //list of hd names
    private PV physicalVolume;

    public HD(String name, String size){
        this.name = name;
        this.size = size;
        withPV = false;
    }

    public void associatedPV(PV pv){
        withPV = true;
        physicalVolume = pv;
    }

    public PV getPhysicalVolume(){
        return physicalVolume;
    }

    public ArrayList<HD> getHdList(){
        return hdList;
    }

    public ArrayList<String> getHdNames(){
        return hdNames;
    }

    public static void addHD(HD hd){
        hdList.add(hd);
        hdNames.add(hd.getName());
    }

    public String getName(){
        return name;
    }

    public String getSize(){
        return size;
    }

    public boolean isWithPV(){
        return withPV;
    }
}
