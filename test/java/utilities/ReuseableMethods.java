package utilities;

public class ReuseableMethods {
    public static void sleep(int sec){

        try {
            Thread.sleep(sec*1000);

        } catch (InterruptedException e) {

            System.out.println("No waiting!");
        }

    }

}
