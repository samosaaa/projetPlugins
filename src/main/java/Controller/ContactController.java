package Controller;

import java.util.regex.Matcher;

import Model.Contact;
import View.Print;

public class ContactController {

    private ContactController(){}

    public static boolean isContact(String input){
        Matcher matchContact = ContactRegex.getContactPattern().matcher(input);
        return matchContact.find();
    }
    
    public static void ContactManager(String input, Contact contact){

        for(ContactFunction f : ContactFunction.functions){
            ContactProcessor contactProcessor = new ContactProcessor(f);
            if(contactProcessor.matchFunction(input) && contactProcessor.findGetter(contact) != null){
                Print.write(contactProcessor.findGetter(contact));
                return;
            }
        }
        Print.write("Je n'ai pas trouvé l'information que vous cherchez parmis vos contacts.");
    }
            
}
