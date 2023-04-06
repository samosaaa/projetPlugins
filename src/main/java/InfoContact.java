import com.google.api.services.people.v1.model.Person;

public class InfoContact{

    private InfoContact(){}

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

}
    