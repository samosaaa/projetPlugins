package Controller;

import java.util.regex.Matcher;

import Model.Contact;

public class ContactNumber extends ContactFunction {
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
    
}
