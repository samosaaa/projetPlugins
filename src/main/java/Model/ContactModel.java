package Model;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.people.v1.model.Person;

public interface ContactModel {

    public abstract Person findPerson(String input) throws GeneralSecurityException, IOException;
    public abstract Contact toContact(Person person);

}
