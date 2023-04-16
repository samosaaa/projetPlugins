package Model;


import java.io.IOException;
import java.security.GeneralSecurityException;

public abstract class ContactModel {

    public abstract Contact findPerson(String input) throws GeneralSecurityException, IOException;

}
