import java.util.UUID;

public class LVM {
    private UUID uuid;
    private String size;
    private String name;

    public LVM(String name, String size){
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

    public String getSize(){
        return size;
    }

    public String getName(){
        return name;
    }
}
