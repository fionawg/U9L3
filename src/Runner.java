import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        String command = "";
        String[] split = new String[4];
        ArrayList<HD> hdList = new ArrayList<HD>();
        ArrayList<String> hdNames = new ArrayList<String>();
        ArrayList<PV> pvList = new ArrayList<PV>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LVM system! Enter your commands:\n");
        while (!command.equals("exit")){
            System.out.print("cmd# ");
            command = scanner.nextLine();
            split = command.split(" ");

            //install-drive
            if (split[0].equals("install-drive")){
                while (hdNames.contains(split[1])){
                    System.out.println("Error. Drive \"" + split[1]+ "\" is already installed.\n");
                    System.out.print("Enter another name: ");
                    split[1] = scanner.nextLine();
                }
                System.out.println("Drive " + split[1] + " installed");
                HD hd = new HD(split[1], split[2]);
                hdNames.add(hd.getName());
                hdList.add(hd);
            }

            //list-drives
            if (split[0].equals("list-drives")){
                for (HD hd : hdList){
                    System.out.println(hd.getName() + " [" + hd.getSize() + "]");
                }
            }

            System.out.println();
        }
    }
}
