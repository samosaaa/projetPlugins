package Controller;

import java.util.regex.Matcher;

import Model.Contact;

public class ContactBirthday extends ContactFunction {
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
    
}
