import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.regex.Matcher;
import com.google.api.services.people.v1.model.Person;


public class ContactFunction {

    public static boolean isContact(String input) throws GeneralSecurityException, IOException{
        Matcher matchContact = Regex.CONTACT_REGEX.matcher("(?iu)" + input);
        if (matchContact.find()) {
            return true;
        }else {
            return false;
        }
    }


    public static boolean matchNumber(String input) {
        Matcher matchNumContact = Regex.NUMBER_REGEX.matcher("(?iu)" + input);
        if(matchNumContact.find()){
            return true;
        }
        return false;
    }

    public static String numberTString(Person contact){
        return contact.getPhoneNumbers().get(0).getValue();
    }

    public static boolean matchEmail(String input) {
        Matcher matchEMailContact = Regex.EMAIL_REGEX.matcher("(?iu)" + input);
        if(matchEMailContact.find()){
            return true;
        }
        return false;
    }

    public static String emailToString(Person contact){
        return contact.getEmailAddresses().get(0).getValue();
    }

    public static boolean matchAddress(String input) {
        Matcher matchAddressContact = Regex.ADDRESS_REGEX.matcher("(?iu)" + input);
        if(matchAddressContact.find()){
            return true;
        }
        return false;
    }

    public static String addressToString(Person contact){
        return contact.getAddresses().get(0).getFormattedValue();
    }

    public static String birthdayToString(Person contact){
        return contact.getBirthdays().get(0).getText();
    }

    public static boolean matchBirth(String input) {
        Matcher matchBirthContact = Regex.BIRTH_REGEX.matcher("(?iu)" + input);
        if(matchBirthContact.find()){
            return true;
        }
        return false;
    }
}
