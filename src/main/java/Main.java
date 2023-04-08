import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import com.google.api.services.people.v1.model.Person;

import Contact.ContactFunction;
import Contact.ContactInfo;
import Contact.PeopleQuickstart;

public class Main{

    public static void findPlugin() throws GeneralSecurityException, IOException{
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Bonjour, comment puis-je vous aider ? ");
                String input = sc.nextLine();

                if (ContactFunction.isContact(input)){ // verifie s'il s'agit d'une question de COntact
                    Person CONTACT = PeopleQuickstart.findContact(input);
                    if(CONTACT == null){
                        System.out.println("Je n'ai pas trouvé la personne dont vous parlez parmis vos contacts.");
                    }
                    else{
                        if(ContactFunction.matchNumber(input)){
                            System.out.println(ContactInfo.numberToString(CONTACT));
                        }
                        else if(ContactFunction.matchBirth(input)){
                            System.out.println(ContactInfo.birthdayToString(CONTACT));
                        }
                        else if(ContactFunction.matchEmail(input)){
                            System.out.println(ContactInfo.emailToString(CONTACT));
                        }
                        else if(ContactFunction.matchAddress(input)){
                            System.out.println(ContactInfo.addressToString(CONTACT));
                        }
                        
                    }
                }    
                else{
                    System.out.println("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
                }
            }
        
    }
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        findPlugin();
    }
}
