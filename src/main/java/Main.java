public class Main {
    public static void main(String [] args) {
        try {
            new App().run(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
