package dj.learning.section2;

import java.io.*;

public class IOUtils {
    public static void copy (String src, String dest) throws IOException {
        InputStream is = new FileInputStream(src);
        OutputStream os = new FileOutputStream(dest);
        int value;
        while ((value=is.read()) != -1) {
            os.write(value);
        }
        is.close();
        os.close();
    }
}
