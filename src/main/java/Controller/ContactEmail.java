package Controller;

import java.util.regex.Matcher;

import Model.Contact;

public class ContactEmail extends ContactFunction {
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
    
}
