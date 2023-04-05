import com.google.api.services.people.v1.model.Person;

public class Conctact {

    public static String nameToString(Person contact){
        return contact.getNames().get(0).getDisplayName();
    }

    public static String addressToString(Person contact){
        return contact.getAddresses().get(0).getFormattedValue();
    }

    public static String numberToString(Person contact){
        return contact.getPhoneNumbers().get(0).getValue();
    }

    public static String emailToString(Person contact){
        return contact.getEmailAddresses().get(0).getValue();
    }
}
