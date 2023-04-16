package Controller;

import Model.Contact;

public class ContactProcessor {
    
    private ContactFunction contactFunction;

    public ContactProcessor(ContactFunction contactFunction){
        this.contactFunction = contactFunction;
    }

    public boolean matchFunction(String input){ return contactFunction.match(input); }
    public String findGetter(Contact contact){ return contactFunction.get(contact); }
}
