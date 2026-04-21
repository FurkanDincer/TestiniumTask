package utilities;

public class Wait {
    public static void waitfor(double second) {
        try {
            Thread.sleep((long) (second*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
