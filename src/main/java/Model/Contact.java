package Model;

import com.google.api.services.people.v1.model.Person;

public class Contact{

    private String name;
    private String number;
    private String email;
    private String birthday;
    private String address;

    public Contact(Person contact){
        this.birthday = contact.getBirthdays().get(0).getText();
        this.email = contact.getEmailAddresses().get(0).getValue();
        this.name = contact.getNames().get(0).getDisplayName();
        this.number = contact.getPhoneNumbers().get(0).getValue();
        this.address = contact.getAddresses().get(0).getFormattedValue();
    }

    public String getNumber(){
        return number;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getName(){
        return name;
    }

    public String getBirthday(){
        return birthday;
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
