package dj.learning.section2;

import java.io.IOException;

public class Main1 {
    public static void main(String[] args)  {
        new Thread(()-> {
            try { IOUtils.copy("a.txt", "c.txt");} catch (IOException e) {e.printStackTrace();}
        }).start();
        new Thread(()-> {
            try { IOUtils.copy("b.txt", "d.txt");} catch (IOException e) {e.printStackTrace();}
        }).start();
        System.out.println("Done.");
    }
}
