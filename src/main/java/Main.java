import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class Main{

    public static void findPlugin() throws GeneralSecurityException, IOException{
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Comment puis-je vous aider ? ");
            String input = sc.nextLine();
            //switch à la place ?
            if(PeopleQuickstart.matchContact(input) == true){
                PeopleQuickstart.answerContactRequest(input);
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