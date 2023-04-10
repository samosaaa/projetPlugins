package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Contact;

public class ContactAddress extends ContactFunction {
    
    private static Pattern ADDRESS_REGEX = Pattern.compile("\\b(adresse|habite|maison)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);

    public ContactAddress(){
        super();
    }

    @Override
    public boolean match(String input) {
        Matcher matchAddressContact = ContactRegex.getAddressPattern().matcher(input);
        return matchAddressContact.find();
    }

    @Override
    public String get(Contact contact){
        return contact.getAddress();
    }

    @Override
    protected Pattern getPattern() {
        return ADDRESS_REGEX;
    }
    
}
