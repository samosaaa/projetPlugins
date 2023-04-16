package View;

public class Print {

    private Print(){}
    
    public static void write(String stringToPrint){
        System.out.println(stringToPrint);
    }

    public static void writeError(Error error){
        System.out.println(error);
    }
}
