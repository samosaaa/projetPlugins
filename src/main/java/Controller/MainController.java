package Controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;
import Model.Contact;
import View.Print;

public class MainController {

    private static String input;

    private MainController(){}

    public static String getInput(){
        try (Scanner sc = new Scanner(System.in)) {
            Print.write("Bonjour, comment puis-je vous aider ? ");
            return sc.nextLine();
        }
    }


    public static String findPlugin() throws GeneralSecurityException, IOException{

        input = getInput();

        if (ContactFunction.isContact(input)){
             //interface + factory gestionnaire de contact qui a pqs en parametre
            if(ContactFunction.getContact(input) != null){
                final Contact contact = ContactFunction.getContact(input);
                ContactController.ContactManager(input, contact);
            }
        }    
        else{
            Print.write("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
        }

        return input;
}
}
