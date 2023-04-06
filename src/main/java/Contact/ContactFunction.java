import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;


public class ContactFunction {

    private ContactFunction(){}

    public static boolean isContact(String input) throws GeneralSecurityException, IOException{
        Matcher matchContact = Regex.getContactPattern().matcher(input);
        return matchContact.find();
    }


    public static boolean matchNumber(String input) {
        Matcher matchNumContact = Regex.getNumPattern().matcher(input);
        return matchNumContact.find();
    }

    public static boolean matchEmail(String input) {
        Matcher matchEMailContact = Regex.getEmailPattern().matcher(input);
        return matchEMailContact.find();
    }


    public static boolean matchAddress(String input) {
        Matcher matchAddressContact = Regex.getAddressPattern().matcher(input);
        return matchAddressContact.find();
    }


    public static boolean matchBirth(String input) {
        Matcher matchBirthContact = Regex.getBirthdayPattern().matcher(input);
        return matchBirthContact.find();
    }
}