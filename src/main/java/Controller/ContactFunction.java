package Controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import Model.Contact;
import Model.ContactModel;
import Model.PeopleQuickstart;


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

    public static Contact getContact(String input) throws GeneralSecurityException, IOException{
        ContactModel contactModel = new PeopleQuickstart();
        return contactModel.findPerson(input);
    }

    protected abstract boolean match(String input);
    protected abstract String get(Contact contact);

}
