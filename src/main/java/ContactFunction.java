import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;


public class ContactFunction {

    private ContactFunction(){}

    public static boolean isContact(String input) throws GeneralSecurityException, IOException{
        Matcher matchContact = Regex.getContactPattern().matcher(input);
        if (matchContact.find()) {
            return true;
        }else {
            return false;
        }
    }


    public static boolean matchNumber(String input) {
        Matcher matchNumContact = Regex.getNumPattern().matcher(input);
        if(matchNumContact.find()){
            return true;
        }
        return false;
    }

    public static boolean matchEmail(String input) {
        Matcher matchEMailContact = Regex.getEmailPattern().matcher(input);
        if(matchEMailContact.find()){
            return true;
        }
        return false;
    }


    public static boolean matchAddress(String input) {
        Matcher matchAddressContact = Regex.getAddressPattern().matcher(input);
        if(matchAddressContact.find()){
            return true;
        }
        return false;
    }


    public static boolean matchBirth(String input) {
        Matcher matchBirthContact = Regex.getBirthdayPattern().matcher(input);
        if(matchBirthContact.find()){
            return true;
        }
        return false;
    }
}
