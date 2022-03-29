import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String command = "";
        String[] split = new String[4];
        int index = -1;

        ArrayList<HD> hdList = new ArrayList<HD>(); //all hds
        ArrayList<HD> usedHD = new ArrayList<HD>(); //hds associated with a pv
        ArrayList<String> hdNames = new ArrayList<String>(); //list of hd names

        ArrayList<PV> pvList = new ArrayList<PV>(); //all pvs
        ArrayList<String> pvNames = new ArrayList<String>(); //list of pv names

        ArrayList<VG> vgList = new ArrayList<VG>(); //all vgs
        ArrayList<PV> usedPV = new ArrayList<PV>(); //pvs associated with a vg
        ArrayList<String> vgNames = new ArrayList<String>(); //list of vg names

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LVM system! Enter your commands:\n");
        while (!command.equals("exit")){
            System.out.print("cmd# ");
            command = scanner.nextLine();
            split = command.split(" ");

            //install-drive
            while (split[0].equals("install-drive")){
                if (hdNames.contains(split[1])){
                    System.out.print("Error. Drive \"" + split[1]+ "\" is already installed.\n");
                    break;
                }
                System.out.println("Drive " + split[1] + " installed\n");
                HD hd = new HD(split[1], split[2]);
                hdNames.add(hd.getName());
                hdList.add(hd);
                System.out.print("cmd# ");
                command = scanner.nextLine();
                split = command.split(" ");
            }

            //list-drives
            if (split[0].equals("list-drives")){
                for (HD hd : hdList){
                    System.out.println(hd.getName() + " [" + hd.getSize() + "]");
                }
            }

            //pvcreate
            while (split[0].equals("pvcreate")){
                index = -1;
                if (pvNames.contains(split[1])){
                    System.out.print("Error. Drive \"" + split[1]+ "\" is already installed.\n");
                    break;
                }
                for (int i = 0; i < hdList.size(); i++){
                    if (split[2].equals(hdList.get(i).getName())){
                        index = i;
                    }
                }
                if (index == -1){
                    System.out.print("Error. Drive \"" + split[2]+ "\" does not exist.\n");
                    break;
                }
                if (usedHD.contains(hdList.get(index))){
                    System.out.print("Error. Drive \"" + split[2]+ "\" is associated with a different PV.\n");
                    break;
                }
                System.out.println(split[1] + " created\n");
                PV pv = new PV(split[1], hdList.get(index));
                usedHD.add(hdList.get(index));
                pvNames.add(pv.getName());
                pvList.add(pv);
                System.out.print("cmd# ");
                command = scanner.nextLine();
                split = command.split(" ");
            }

            //pvlist
            if (split[0].equals("pvlist")) {
                for (PV pv : pvList){
                    if (pv.getInVG()){

                    }
                    System.out.println(pv.getName() + ": [" + pv.getSize() + "] [" + pv.getUuid() + "]");
                }
            }

            //vgcreate
            while (split[0].equals("vgcreate")){
                index = -1;
                if (vgNames.contains(split[1])){
                    System.out.print("Error. Drive \"" + split[1]+ "\" is already created.\n");
                    break;
                }
                for (int i = 0; i < pvList.size(); i++){
                    if (split[2].equals(pvList.get(i).getName())){
                        index = i;
                    }
                }
                if (index == -1){
                    System.out.print("Error. Drive \"" + split[2]+ "\" does not exist.\n");
                    break;
                }
                if (usedPV.contains(pvList.get(index))){
                    System.out.print("Error. Drive \"" + split[2]+ "\" is associated with a different PV.\n");
                    break;
                }
                System.out.println(split[1] + " created\n");
                VG vg = new VG(split[1]);
                usedPV.add(pvList.get(index));
                vgNames.add(vg.getName());
                vgList.add(vg);
                System.out.print("cmd# ");
                command = scanner.nextLine();
                split = command.split(" ");
            }

            System.out.println();
        }
    }
}
