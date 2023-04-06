package Controller;


import Model.Contact2;
import View.Print;

public class ContactController {

    private ContactController(){}
    
    
    public static void ContactManager(String input, Contact2 CONTACT){

        if(CONTACT == null){
            Print.write("Je n'ai pas trouvé la personne dont vous parlez parmis vos contacts.");
        }
        else{
            if(ContactFunction.matchNumber(input)){
                Print.write(CONTACT.getNumber());
            }
            else if(ContactFunction.matchBirth(input)){
                Print.write(CONTACT.getBirthday());
            }
            else if(ContactFunction.matchEmail(input)){
                Print.write(CONTACT.getEmail());
            }
            else if(ContactFunction.matchAddress(input)){
                Print.write(CONTACT.getAddress());
            }
            
        }
    }

}
