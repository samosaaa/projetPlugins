package Controller;

import java.util.ArrayList;

import Model.Contact;


public abstract class ContactFunction {


    protected ContactFunction(){}

    static ArrayList<ContactFunction> functions = new ArrayList() {{
        add(new ContactNumber());
        add(new ContactEmail());
        add(new ContactAddress());
        add(new ContactBirthday());
       }};


    protected abstract boolean match(String input);
    protected abstract String get(Contact contact);

}
