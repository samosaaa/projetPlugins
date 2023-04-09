package Controller;

import java.util.regex.Matcher;

import Model.Contact;

public class ContactAddress extends ContactFunction {
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
    
}
