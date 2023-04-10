package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Contact;

public class ContactEmail extends ContactFunction {
    
    private static Pattern EMAIL_REGEX = Pattern.compile("\\b(Mail|Email|Mèl|Mel)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);

    public ContactEmail(){
        super();
    }

    @Override
    public boolean match(String input) {
        Matcher matchEMailContact = ContactRegex.getEmailPattern().matcher(input);
        return matchEMailContact.find();
    }

    @Override
    public String get(Contact contact){
        return contact.getEmail();
    }

    @Override
    protected Pattern getPattern() {
        return EMAIL_REGEX;
    }
    
}
