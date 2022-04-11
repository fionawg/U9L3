import java.util.*;

public class Commands {
    Scanner scanner = new Scanner(System.in);

    public void installDrive(String[] split, HD hd){
        if (hd.getHdNames().contains(split[1])){
            System.out.print("Error. Drive \"" + split[1]+ "\" is already installed.\n");
            return;
        }
        System.out.print("Drive " + split[1] + " installed\n");
        HD x = new HD(split[1], split[2]);
        HD.addHD(x);
    }

    public void listDrives(HD hd){
        for (HD x : hd.getHdList()){
            System.out.println(x.getName() + " [" + x.getSize() + "]");
        }
    }

    public void pvCreate(String[] split, PV pv, HD hd){
        int index = -1;
        if (PV.getPvNames().contains(split[1])){
            System.out.print("Error. Drive \"" + split[1]+ "\" is already installed.\n");
            return;
        }
        for (int i = 0; i < hd.getHdList().size(); i++){
            if (split[2].equals(hd.getHdList().get(i).getName())){
                index = i;
                break;
            }
        }
        if (index == -1){
            System.out.print("Error. Drive \"" + split[2]+ "\" does not exist.\n");
            return;
        }
        if (hd.getHdList().get(index).isWithPV()){
            System.out.print("Error. Drive \"" + split[2]+ "\" is associated with a different PV.\n");
            return;
        }
        System.out.print(split[1] + " created\n");
        PV x = new PV(split[1], hd.getHdList().get(index));
        hd.getHdList().get(index).associatedPV(pv);
        PV.addPV(x);
    }

    public void pvList(){
        for (PV x : PV.getPvList()){
            if (x.getInVG()){
                System.out.println(x.getName() + ": [" + x.getVG().getName() + "] [" + x.getSize() + "] [" + x.getUuid() + "]");
            }
            else {
                System.out.println(x.getName() + ": [" + x.getSize() + "] [" + x.getUuid() + "]");
            }
        }
    }

    public void vgCreate(String[] split, VG vg, PV pv){
        int index = -1;
        if (VG.getVgNames().contains(split[1])){
            System.out.print("Error. VG \"" + split[1]+ "\" is already created.\n");
            return;
        }
        for (int i = 0; i < PV.getPvList().size(); i++){
            if (split[2].equals(PV.getPvList().get(i).getName())){
                index = i;
            }
        }
        if (index == -1){
            System.out.print("Error. \"" + split[2]+ "\" does not exist.\n");
            return;
        }
        if (PV.getPvList().get(index).getInVG()){
            System.out.print("Error. PV \"" + split[2]+ "\" is associated with a different VG.\n");
            return;
        }
        System.out.print(split[1] + " created\n");
        VG x = new VG(split[1]);
        PV.getPvList().get(index).inVG(x);
        x.addPV(PV.getPvList().get(index));
        VG.addUsedPV(PV.getPvList().get(index));
        VG.addVG(x);
        PV.getPvList().get(index).inVG(x);
    }

    public void vgExtend(String[] split, VG vg, PV pv){
        int index = -1;
        if (!VG.getVgNames().contains(split[1])){
            System.out.print("Error. \"" + split[1]+ "\" does not exist.\n");
            return;
        }
        if (!PV.getPvNames().contains(split[2])){
            System.out.print("Error. \"" + split[2]+ "\" does not exist.\n");
            return;
        }
        for (int i = 0; i < PV.getPvList().size(); i++){
            if (PV.getPvList().get(i).getName().equals(split[2])){
                index = i;
            }
        }
        if (PV.getPvList().get(index).getInVG()){
            System.out.print("Error. \"" + split[2]+ "\" is already part of a VG.\n");
            return;
        }
        System.out.print(split[2] + " was successfully added to " + split[1] + "\n");
        int index2 = 0;
        for (int i = 0; i < VG.getVgList().size(); i++){
            if (VG.getVgList().get(i).getName().equals(split[1])){
                index2 = i;
            }
        }
        PV.getPvList().get(index).inVG(VG.getVgList().get(index2));
        VG.getVgList().get(index2).addPV(PV.getPvList().get(index));
    }

    public void vgList(){
        for (VG x : VG.getVgList()){
            x.calculateSpace();
            System.out.print(x.getName() + ": total: [" + x.getTotalSpace() + "G] available: [" + x.getFreeSpace() + "G] [");
            for (int i = 0; i < x.getPV().size(); i++){
                if (i < x.getPV().size() - 1){
                    System.out.print(x.getPV().get(i).getName() + ", ");
                } else {
                    System.out.print(x.getPV().get(i).getName() + "] [");
                }
            }
            System.out.println(x.getUuid() + "]");
        }
    }

    public void lvCreate(String[] split, VG vg){
        int index = -1;
        if (LV.getLvNames().contains(split[1])){
            System.out.print("Error. LV \"" + split[1]+ "\" is already created.\n");
            return;
        }
        for (int i = 0; i < LV.getLvList().size(); i++){
            if (split[1].equals(LV.getLvList().get(i).getName())){
                index = i;
            }
        }
        if (index != -1){
            System.out.print("Error. \"" + split[1]+ "\" is already created.\n");
            return;
        }
        int index1 = -1;
        for (int i = 0; i < VG.getVgList().size(); i++){
            if (split[3].equals(VG.getVgList().get(i).getName())){
                index1 = i;
            }
        }
        if (index1 == -1){
            System.out.print("Error. \"" + split[3]+ "\" does not exist.\n");
            return;
        }
        int s = Integer.parseInt(split[2].replaceAll("[^0-9]", ""));
        if (VG.getVgList().get(index1).getFreeSpace() < s){
            System.out.print("Error. There is not enough space in " + VG.getVgList().get(index1).getName() + ".\n");
            return;
        }
        System.out.print(split[1] + " created\n");
        LV x = new LV(split[1], split[2], VG.getVgList().get(index1));
        LV.addLV(x);
        VG.getVgList().get(index1).addLV(x);
    }

    public void lvList(){
        for (LV lv : LV.getLvList()){
            System.out.println(lv.getName() + ": [" + lv.getSize() + "] [" + lv.getVg().getName() + "] [" + lv.getUuid() + "]");
        }
    }
}
