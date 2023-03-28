import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.PeopleServiceScopes;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeopleQuickstart {
  private static final String APPLICATION_NAME = "Google People API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES = Arrays.asList(PeopleServiceScopes.CONTACTS_READONLY);
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = PeopleQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
  }

  public static List<Person> getConnections() throws GeneralSecurityException, IOException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    PeopleService service = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();
    ListConnectionsResponse response = service.people().connections()
        .list("people/me")
        .setPageSize(10)
        .setPersonFields("names,emailAddresses,birthdays,phoneNumbers,addresses")
        .execute();
    List<Person> connections = response.getConnections();
    return connections;
  }

  public static Person compareNames(String input) throws GeneralSecurityException, IOException {

    List<Person> connections = getConnections();

    String[] ContactRequest = input.split(" ");
    for (int i = 0; i < ContactRequest.length; i++) {
      for (Person contact : connections) {
        if (ContactRequest[i].equals(contact.getNames().get(0).getDisplayName())) {
          return contact;
        }
      }
    }
    return null;
  }


  
  public static boolean isContact(String input) throws GeneralSecurityException, IOException {
    Person contact = compareNames(input);
    if (contact == null) {
      System.out.println("Je n'ai pas trouvé le contact dont vous parlez."); // write(error)
      return false;
    } else {
      return true;
    }
  }

  public static boolean matchNumber(String input) throws GeneralSecurityException, IOException {
      Pattern regexNumContact = Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte)\\b",
          Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
      Matcher matchNumContact = regexNumContact.matcher("(?iu)" + input);
      if (matchNumContact.find()) { //si la question correspond a une recherche de numero
        return true;       
      } 
    return false;
  }

  public static void answerContactNumberRequest(String input) throws GeneralSecurityException, IOException{
    Person contact = compareNames(input);
    if (contact.getPhoneNumbers() != null) { //si le contact en question possède un numéro 
      System.out.println("Le numéro de téléphone de " + contact.getNames().get(0).getDisplayName() + " est "
          + contact.getPhoneNumbers().get(0).getValue());
    } else { // sinon
      System.out.println("Le numéro de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseigné.");
    }
  }

  public static boolean matchEmail(String input) throws GeneralSecurityException, IOException {
      Pattern regexMailContact = Pattern.compile("\\b(Mail|Email|Mèl|Mel)\\b",
          Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
      Matcher matchMailContact = regexMailContact.matcher("(?iu)" + input);
      if (matchMailContact.find())  { //si la question correspond a une recherche d'un mail
        return true;       
      } 
    return false;
  }

  public static void answerContactEmailRequest(String input) throws GeneralSecurityException, IOException{
    Person contact = compareNames(input);
    if (contact.getEmailAddresses() != null) { //si le contact en question possède un mail 
      ConsoleLogger.writeInfo("L\'adresse Email de " + contact.getNames().get(0).getDisplayName() + " est "
      + contact.getEmailAddresses().get(0).getValue());
      //System.out.println("L\'adresse Email de " + contact.getNames().get(0).getDisplayName() + " est " + contact.getEmailAddresses().get(0).getValue());
    } else { // sinon
      ConsoleLogger.writeInfo("L\'adresse Email de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
      //System.out.println("L\'adresse Email de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
    }
  }

  public static boolean matchAdress(String input) throws GeneralSecurityException, IOException {
      Pattern regexAdressContact = Pattern.compile("\\b(adresse|habite|maison)\\b",
          Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
      Matcher matchAdressContact = regexAdressContact.matcher("(?iu)" + input);
      if (matchAdressContact.find())  { //si la question correspond a une recherche d'un email
        return true;       
      } 
    return false;
  }

  public static void answerContactAdressRequest(String input) throws GeneralSecurityException, IOException{
    Person contact = compareNames(input);
    if (contact.getAddresses() != null) { //si le contact en question possède un email 
      ConsoleLogger.writeInfo("L\'adresse de " + contact.getNames().get(0).getDisplayName() + " est "
      + contact.getAddresses().get(0).getFormattedValue());
      //System.out.println("L\'adresse de " + contact.getNames().get(0).getDisplayName() + " est " + contact.getAddresses().get(0).getFormattedValue());
    } else { // sinon
      ConsoleLogger.writeInfo("L\'adresse de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
      //System.out.println("L\'adresse de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
    }
  }

  public static boolean matchBirthdate(String input) throws GeneralSecurityException, IOException {
      Pattern regexAgeContact = Pattern.compile("\\b(né|naissance|âge|age|née|nee)\\b",
          Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
      Matcher matchAgeContact = regexAgeContact.matcher("(?iu)" + input);
      if (matchAgeContact.find())  { //si la question correspond a une recherche d'une date de naissance
        return true;       
      } 
    return false;
  }


  public static void answerContactBirthdateRequest(String input) throws GeneralSecurityException, IOException{
    Person contact = compareNames(input);
    if (contact.getBirthdays() != null) { //si le contact en question possède une date de naissance 
      ConsoleLogger.writeInfo(contact.getNames().get(0).getDisplayName() + " est né.e le " + contact.getBirthdays().get(0).getText());
      //System.out.println(contact.getNames().get(0).getDisplayName() + " est né.e le " + contact.getBirthdays().get(0).getText());
    } else { // sinon
      ConsoleLogger.writeInfo("La date de naissance de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
      //System.out.println("La date de naissance de " + contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
    }
  }



  /*
   * public static void answerContactRequest(String input) throws
   * GeneralSecurityException, IOException{
   * Person contact = compareNames(input);
   * if(contact == null){
   * System.out.println("Je n'ai pas trouvé le contact dont vous parlez."); //
   * write(error)
   * return;
   * }
   * Pattern regexAgeContact =
   * Pattern.compile("\\b(né|naissance|âge|age|née|nee)\\b",
   * Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
   * Matcher matchAgeContact = regexAgeContact.matcher("(?iu)" + input);
   * 
   * Pattern regexMailContact = Pattern.compile("\\b(Mail|Email|Mèl|Mel)\\b",
   * Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
   * Matcher matchMailContact = regexMailContact.matcher("(?iu)" + input);
   * 
   * Pattern regexNumContact =
   * Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte)\\b",
   * Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
   * Matcher matchNumContact = regexNumContact.matcher("(?iu)" + input);
   * 
   * Pattern regexAdressContact = Pattern.compile("\\b(adresse|habite|maison)\\b",
   * Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
   * Matcher matchAdressContact = regexAdressContact.matcher("(?iu)" + input);
   * 
   * 
   * if (matchAgeContact.find()) {
   * if(contact.getBirthdays() != null ){
   * System.out.println(contact.getNames().get(0).getDisplayName() +
   * " est né.e le " + contact.getBirthdays().get(0).getText());
   * }
   * else{
   * System.out.println("La date de naissance de " +
   * contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
   * }
   * }
   * else if(matchMailContact.find()){
   * if(contact.getEmailAddresses() != null){
   * System.out.println("L\'adresse Email de " +
   * contact.getNames().get(0).getDisplayName() + " est " +
   * contact.getEmailAddresses().get(0).getValue());
   * }
   * else{
   * System.out.println("L\'adresse Email de " +
   * contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
   * }
   * }
   * else if(matchNumContact.find()){
   * if(contact.getPhoneNumbers() != null){
   * System.out.println("Le numéro de téléphone de " +
   * contact.getNames().get(0).getDisplayName() + " est " +
   * contact.getPhoneNumbers().get(0).getValue());
   * }
   * else{
   * System.out.println("Le numéro de " +
   * contact.getNames().get(0).getDisplayName() + " n'est pas renseigné.");
   * }
   * }
   * else if(matchAdressContact.find()){
   * if(contact.getAddresses() != null){
   * System.out.println("L\'adresse de " +
   * contact.getNames().get(0).getDisplayName() + " est " +
   * contact.getAddresses().get(0).getFormattedValue());
   * }
   * else{
   * System.out.println("L\'adresse de " +
   * contact.getNames().get(0).getDisplayName() + " n'est pas renseignée.");
   * }
   * }
   * }
   */

  public static void main(String... args) throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    PeopleService service = new PeopleService.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
        .setApplicationName(APPLICATION_NAME)
        .build();

    // Request 10 connections.
    ListConnectionsResponse response = service.people().connections()
        .list("people/me")
        .setPageSize(10)
        .setPersonFields("names,emailAddresses")
        .execute();

    // Print display name of connections if available.
    List<Person> connections = response.getConnections();
    if (connections != null && connections.size() > 0) {
      for (Person person : connections) {
        List<Name> names = person.getNames();
        if (names != null && names.size() > 0) {
          System.out.println("Name: " + person.getNames().get(0)
              .getDisplayName());
        } else {
          System.out.println("No names available for connection.");
        }
      }
    } else {
      System.out.println("No connections found.");
    }
  }


}
