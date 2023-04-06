package Contact;
import com.google.api.services.people.v1.model.Person;

public class ContactInfo{

    private ContactInfo(){}

    //A METTRE dans le package view du COntact
    public static String numberToString(Person contact){
        return contact.getPhoneNumbers().get(0).getValue();
    }

    public static String emailToString(Person contact){
        return contact.getEmailAddresses().get(0).getValue();
    }

    public static String addressToString(Person contact){
        return contact.getAddresses().get(0).getFormattedValue();
    }

    public static String birthdayToString(Person contact){
        return contact.getBirthdays().get(0).getText();
    }
//objet contact
//contact manager qui fait appel a peoplequickstart ou autre pour ... pour etablir la connection
//      recupere donnes, etc
//controller phrase
//contact controller
//analyse de la phrase, definir le prochain controller

//persistence stock de données
//controlleur comment traiter la demande
//sevice 
//
}
    