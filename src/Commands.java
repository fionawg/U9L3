import java.util.*;

public class Commands {
    Scanner scanner = new Scanner(System.in);

    private String command;
    //private String[] split;
    private int index;

    public Commands(){
        command = "";
        //split = null;
        index = -1;
    }

    public void installDrive(String[] split, HD hd){
        if (hd.getHdNames().contains(split[1])){
            System.out.print("Error. Drive \"" + split[1]+ "\" is already installed.\n");
            return;
        }
        System.out.print("Drive " + split[1] + " installed\n");
        HD x = new HD(split[1], split[2]);
        hd.addHD(x);
    }

    public void listDrives(HD hd){
        for (HD x : hd.getHdList()){
            System.out.println(x.getName() + " [" + x.getSize() + "]");
        }
    }

    public void pvCreate(String[] split, PV pv, HD hd){
        index = -1;
        if (pv.getPvNames().contains(split[1])){
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
        x.addPV(x);
    }

    public void pvList(String[] split, PV pv){
        if (split[0].equals("pvlist")) {
            for (PV x : pv.getPvList()){
                if (x.getInVG()){
                    System.out.println(x.getName() + ": [" + x.getVG().getName() + "] [" + x.getSize() + "] [" + x.getUuid() + "]");
                }
                else {
                    System.out.println(x.getName() + ": [" + x.getSize() + "] [" + x.getUuid() + "]");
                }
            }
        }
    }

    //working on this
    public void vgCreate(String[] split, VG vg, PV pv){
        index = -1;
        if (vg.getVgList().contains(split[1])){
            System.out.print("Error. VG \"" + split[1]+ "\" is already created.\n");
            return;
        }
        for (int i = 0; i < pv.getPvList().size(); i++){
            if (split[2].equals(pv.getPvList().get(i).getName())){
                index = i;
            }
        }
        if (index == -1){
            System.out.print("Error. \"" + split[2]+ "\" does not exist.\n");
            return;
        }
        if (pv.getInVG()){
            System.out.print("Error. PV \"" + split[2]+ "\" is associated with a different PV.\n");
            return;
        }
        System.out.print(split[1] + " created\n");
        VG x = new VG(split[1]);
        vg.addPV(pv);
        vg.addVG(x);
        pv.inVG(x);
    }

    public String[] vgExtend(String[] split, VG vg, PV pv){
        while (split[0].equals("vgextend")){
            index = -1;
            if (!vg.getVgNames().contains(split[1])){
                System.out.print("Error. \"" + split[1]+ "\" does not exist.\n");
                break;
            }
            if (!pv.getPvNames().contains(split[2])){
                System.out.print("Error. \"" + split[2]+ "\" does not exist.\n");
                break;
            }
            for (int i = 0; i < pv.getPvList().size(); i++){
                if (pv.getPvList().get(i).getName().equals(split[2])){
                    index = i;
                }
            }
            if (pv.getInVG()){
                System.out.print("Error. \"" + split[1]+ "\" is already part of a VG .\n");
                break;
            }
            System.out.println(split[2] + " was successfully added to " + split[1] + "\n");
            int index2 = 0;
            for (int i = 0; i < vg.getVgList().size(); i++){
                if (vg.getVgList().get(i).getName().equals(split[1])){
                    index2 = i;
                }
            }
            pv.inVG(vg);
            vg.addPV(pv.getPvList().get(index));
            System.out.print("cmd# ");
            command = scanner.nextLine();
            split = command.split(" ");
        }
        return split;
    }

}
