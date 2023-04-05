import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{

    public static List<String> matchContact(String input){
        Pattern regexContact = Pattern.compile("\\b(né|habite|naissance|âge|connais-tu)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
        Matcher matchContact = regexContact.matcher("(?iu)" + input);
        List<String> paramsContact = new ArrayList<String>();
        if (matchContact.find()) {
            for (int j = 0; j < matchContact.groupCount(); j++) {
                paramsContact.add(matchContact.group(j + 1));
            }
            return paramsContact;
        }else {
            return null;
        }
    }

    public static void answerContactRequest(String input){
        
    }

    public static void findPlugin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Comment puis-je vous aider ? ");
        String input = sc.nextLine();
        //switch à la place ?
        if(matchContact(input)!= null){
            System.out.println(matchContact(input));
        }
        else{
            System.out.println("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
        }
    }
    

    public static void main(String[] args){
        findPlugin();
    }



}