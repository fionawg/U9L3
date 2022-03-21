import java.util.Scanner;
import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String command = "";
        String name = "";
        String secondName = "";
        String size = "";
        int index = 0;
        ArrayList<HD> hdList = new ArrayList<HD>();
        ArrayList<PV> pvList = new ArrayList<PV>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the LVM system! Enter your commands:\n");
        while (!command.equals("exit")){
            System.out.print("cmd# ");
            command = scanner.nextLine();
            if (command.indexOf(" ") != -1 && command.substring(0, command.indexOf(" ")).equals("install-drive")){
                command = command.substring(command.indexOf(" ") + 1);
                if (command.indexOf(" ") != -1){
                    name = command.substring(0, command.indexOf(" "));
                    size = command;
                    for (int i = 0; i < hdList.size(); i++){
                        name = command.substring(0, command.indexOf(" "));
                        while (name.equals(hdList.get(i).getName())){
                            System.out.println("Error: that drive is already installed.\n");
                            System.out.print("cmd# ");
                            command = scanner.nextLine();
                            name = command.substring(command.indexOf(" ") + 1, command.indexOf(" "));
                            size = command;
                        }
                    }
                    HD h = new HD(name, size);
                    hdList.add(h);
                    System.out.println("Drive " + name + " installed");
                }
            }
            if (command.equals("list-drives")){
                for (HD x : hdList){
                    System.out.println(x.getName() + " [" + x.getSize() + "]");
                }
            }
            if (command.indexOf(" ") != -1 && command.substring(0, command.indexOf(" ")).equals("pvcreate")){
                command = command.substring(command.indexOf(" ") + 1);
                if (command.indexOf(" ") != -1){
                    name = command.substring(0, command.indexOf(" "));
                    command = command.substring(command.indexOf(" ") + 1);
                    secondName = command;
                    for (int i = 0; i < hdList.size(); i++){
                        if (hdList.get(i).getName().equals(secondName)){
                            index = i;
                        }
                    }
                    PV p = new PV(name, hdList.get(index));
                    pvList.add(p);
                    System.out.println(name + " created");
                }
            }
            if (command.equals("pvlist")){
                for (PV p : pvList){
                    System.out.print("[" + p.getName() + "] " + p.getSize() + " ");
                    if (true){
                    }
                    System.out.println(p.getUuid());
                }
            }
            System.out.print("\n");
        }
    }
}
