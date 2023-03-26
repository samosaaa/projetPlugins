import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class Main{

    public static void findPlugin() throws GeneralSecurityException, IOException{
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Comment puis-je vous aider ? ");
            String input = sc.nextLine();

            if (PeopleQuickstart.isContact(input)){ // verifie s'il s'agit d'une question de COntact
                if(PeopleQuickstart.matchNumber(input)){
                    PeopleQuickstart.answerContactNumberRequest(input);
                }
                else if(PeopleQuickstart.matchBirthdate(input)){
                    PeopleQuickstart.answerContactBirthdateRequest(input);
                }
                else if(PeopleQuickstart.matchEmail(input)){
                    PeopleQuickstart.answerContactEmailRequest(input);
                }
                else  {
                    if(PeopleQuickstart.matchAdress(input)){
                        PeopleQuickstart.answerContactAdressRequest(input);
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