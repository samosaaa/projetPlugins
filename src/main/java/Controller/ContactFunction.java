package Controller;

import java.util.regex.Matcher;


public class ContactFunction {

    private ContactFunction(){}

    public static boolean isContact(String input){
        Matcher matchContact = ContactRegex.getContactPattern().matcher(input);
        return matchContact.find();
    }


    public static boolean matchNumber(String input) {
        Matcher matchNumContact = ContactRegex.getNumPattern().matcher(input);
        return matchNumContact.find();
    }

    public static boolean matchEmail(String input) {
        Matcher matchEMailContact = ContactRegex.getEmailPattern().matcher(input);
        return matchEMailContact.find();
    }


    public static boolean matchAddress(String input) {
        Matcher matchAddressContact = ContactRegex.getAddressPattern().matcher(input);
        return matchAddressContact.find();
    }


    public static boolean matchBirth(String input) {
        Matcher matchBirthContact = ContactRegex.getBirthdayPattern().matcher(input);
        return matchBirthContact.find();
    }
}
