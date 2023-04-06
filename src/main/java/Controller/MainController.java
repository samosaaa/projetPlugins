package Controller;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import Model.Contact2;
import Model.PeopleQuickstart;
import View.Print;

public class MainController {

    private MainController(){}

    public static void findPlugin() throws GeneralSecurityException, IOException{
        try (Scanner sc = new Scanner(System.in)) {
            Print.write("Bonjour, comment puis-je vous aider ? ");
            String input = sc.nextLine();

            if (ContactFunction.isContact(input)){
                Contact2 CONTACT = new Contact2(PeopleQuickstart.findContact(input)); //interface + factory gestionnaire de contact qui a pqs en parametre
                ContactController.ContactManager(input, CONTACT);
            }    
            else{
                Print.write("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
            }
        }
    
}
}
