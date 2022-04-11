import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String command = "";
        String[] split;

        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();

        System.out.println("Welcome to the LVM system! Enter your commands:\n");
        while (!command.equals("exit")){
            System.out.print("cmd# ");
            command = scanner.nextLine();
            split = command.split(" ");
            HD hd = new HD("", "");
            PV pv = new PV("", hd);
            VG vg = new VG("");
            LV lv = new LV("", "", vg);

            if (split[0].equals("install-drive")){
                hd = new HD(split[1], split[2]);
                commands.installDrive(split, hd);
            }
            if (split[0].equals("list-drives")){
                commands.listDrives(hd);
            }
            if (split[0].equals("pvcreate")){
                commands.pvCreate(split, pv, hd);
            }
            if (split[0].equals("pvlist")){
                commands.pvList();
            }
            if (split[0].equals("vgcreate")){
                commands.vgCreate(split, vg, pv);
            }
            if (split[0].equals("vgextend")){
                commands.vgExtend(split, vg, pv);
            }
            if (split[0].equals("vglist")){
                commands.vgList();
            }
            if (split[0].equals("lvcreate")){
                commands.lvCreate(split, vg);
            }
            if (split[0].equals("lvlist")){
                commands.lvList();
            }

            System.out.println();
        }
    }
}