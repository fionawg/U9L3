import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        String command = "";
        String[] split;
        ArrayList<HD> hdList = new ArrayList<HD>();
        ArrayList<PV> pvList = new ArrayList<PV>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LVM system! Enter your commands:\n");
        while (!command.equals("exit")){
            System.out.print("cmd# ");
            command = scanner.nextLine();
            split = command.split(" ");

            //install-drive
            if (split[0].equals("install-drive")){
                for (int i = 0; i < hdList.size(); i++){
                    if (hdList.get(i).equals(split[1])){
                        System.out.println("Error. There is already a drive with the name \"" + split[1] + "\".\n");
                        System.out.print("cmd# ");
                        command = scanner.nextLine();
                        split = command.split(" ");
                    }
                }
                System.out.println("Drive " + split[1] + " installed");
                HD hd = new HD(split[1], split[2]);
                hdList.add(hd);
            }

            System.out.println();
        }
    }
}
