import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import com.google.api.services.people.v1.model.Person;

public class Main{

    public static void findPlugin() throws GeneralSecurityException, IOException{
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Comment puis-je vous aider ? ");
            String input = sc.nextLine();

            if (ContactFunction.isContact(input)){ // verifie s'il s'agit d'une question de COntact
            Person CONTACT = PeopleQuickstart.findContact(input);
                if(ContactFunction.matchNumber(input)){
                    System.out.println(ContactFunction.numberTString(CONTACT));
                }
                else if(ContactFunction.matchBirth(input)){
                    ContactFunction.birthdayToString(CONTACT);
                }
                else if(ContactFunction.matchEmail(input)){
                    ContactFunction.emailToString(CONTACT);
                }
                else  {
                    if(ContactFunction.matchAddress(input)){
                        ContactFunction.addressToString(CONTACT);
                    }
                } 
            }
            else{
                System.out.println("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
            }
        }
    }
    

    public static void main(String[] args) throws GeneralSecurityException, IOException{
        findPlugin();
    }



}