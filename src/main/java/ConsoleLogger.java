
public class ConsoleLogger {

    public static void writeInfo(String msg) {
        System.out.println("Info: "+ msg);
    }

    public static void writeError(String msg, Exception e) {
        System.out.println("Error: " + msg + ";" + e);
    }
    
}