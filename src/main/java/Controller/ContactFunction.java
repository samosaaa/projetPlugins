package Controller;

import java.util.ArrayList;
import java.util.regex.Matcher;

import Model.Contact;


public abstract class ContactFunction {


    protected ContactFunction(){}

    static ArrayList<ContactFunction> functions = new ArrayList() {{
        add(new ContactNumber());
        add(new ContactEmail());
        add(new ContactAddress());
        add(new ContactBirthday());
       }};

    public static boolean isContact(String input){
        Matcher matchContact = ContactRegex.getContactPattern().matcher(input);
        return matchContact.find();
    }


    protected abstract boolean match(String input);
    protected abstract String get(Contact contact);


}
