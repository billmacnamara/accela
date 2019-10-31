package billmacnamara.accela.techtest.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccelaLogger {

    private static final String LOG_STRING_TEMPLATE = "%1$s [%2$s]: %3$s\n";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void printWarn(String msg) {
        Date now =  new Date();
        System.out.println(String.format(LOG_STRING_TEMPLATE, "WARNING", dateFormat.format(now), msg));
    }

    public static void printError(String msg) {
        Date now =  new Date();
        System.out.println(String.format(LOG_STRING_TEMPLATE, "ERROR", dateFormat.format(now), msg));
    }

    public static void printInfo(String msg) {
        Date now =  new Date();
        System.out.println(String.format(LOG_STRING_TEMPLATE, "INFO", dateFormat.format(now), msg));
    }
}
