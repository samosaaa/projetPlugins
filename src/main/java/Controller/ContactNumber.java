package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Contact;

public class ContactNumber extends ContactFunction {

    private static Pattern NUMBER_REGEX = Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    
    public ContactNumber(){
        super();
    }

    @Override
    public boolean match(String input) {
        Matcher matchNumContact = ContactRegex.getNumPattern().matcher(input);
        return matchNumContact.find();
    }

    @Override
    public String get(Contact contact){
        return contact.getNumber();
    }

    @Override
    protected Pattern getPattern() {
        return NUMBER_REGEX;
    }

    
    
}
