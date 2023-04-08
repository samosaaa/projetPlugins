package Controller;


import Model.Contact;
import View.Print;

public class ContactController {

    private ContactController(){}
    
    
    public static void ContactManager(String input, Contact contact){
            if(ContactFunction.matchNumber(input) && contact.getNumber() != null){
                Print.write(contact.getNumber());
            }
            else if(ContactFunction.matchBirth(input) && contact.getBirthday() != null){
                Print.write(contact.getBirthday());
            }
            else if(ContactFunction.matchEmail(input) && contact.getEmail() != null){
                Print.write(contact.getEmail());
            }
            else if(ContactFunction.matchAddress(input) && contact.getAddress() != null){
                Print.write(contact.getAddress());
            }
            else{
                Print.write("Je n'ai pas trouvé l'information que vous cherchez parmis vos contacts.");
            }
            
        }
    }
