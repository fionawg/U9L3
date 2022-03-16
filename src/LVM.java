import java.util.UUID;

public class LVM {
    private UUID uuid;
    private int size;
    private String name;

    public LVM(String name, int size){
        uuid = UUID.randomUUID();
        this.size = size;
        this.name = name;
    }

    public LVM(String name){
        uuid = UUID.randomUUID();
        this.name = name;
    }

    public UUID getUuid(){
        return uuid;
    }

    public int getSize(){
        return size;
    }

    public String getName(){
        return name;
    }
}
