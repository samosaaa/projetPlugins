import java.io.IOException;
import java.security.GeneralSecurityException;

import Controller.ContactFunction;
import Controller.MainController;

public class Main{

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        MainController.findPlugin();
        System.out.println(ContactFunction.isContact("salut"));
    }
}
