public class Runner {
    public static void main(String[] args) {
        LVM x = new LVM("SDA", 50);
        System.out.println(x.getUuid());

        LVM x2 = new LVM("DDD", 3);
        System.out.println(x2.getUuid());

        HD x3 = new HD("SDF", 5);

        PV x4 = new PV("DFH", x3);
        System.out.println(x4.getName());
        System.out.println(x4.getSize());
    }
}
