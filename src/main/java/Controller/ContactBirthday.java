package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Contact;

public class ContactBirthday extends ContactFunction {
    
    private static Pattern BIRTH_REGEX = Pattern.compile("\\b(né|naissance|âge|age|née|nee)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);

    public ContactBirthday(){
        super();
    }

    @Override
    public boolean match(String input) {
        Matcher matchBirthContact = ContactRegex.getBirthdayPattern().matcher(input);
        return matchBirthContact.find();
    }

    @Override
    public String get(Contact contact){
        return contact.getBirthday();
    }

    @Override
    protected Pattern getPattern() {
        return BIRTH_REGEX;
    }
    
}
