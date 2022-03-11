public class Main {
    public static void main(String [] args) {
//        System.out.println("hello " + args[0] + " " + args[1]);
        long f1 = System.currentTimeMillis();
        Flag flag = new FlagParser().parse(args);
        long f2 = System.currentTimeMillis() - f1;
        System.out.println("time: " + f2 + "\n" + flag.getFileType().name() + " " + flag.getNumber());
    }
}
