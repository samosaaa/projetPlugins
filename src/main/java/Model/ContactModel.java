package Model;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.people.v1.model.Person;

public interface ContactModel {

    public Person findPerson(String input) throws GeneralSecurityException, IOException;

}
