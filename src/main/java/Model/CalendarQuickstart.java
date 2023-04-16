package Model;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.people.v1.model.Date;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* class to demonstarte use of Calendar events list API */
public class CalendarQuickstart {
  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
  /**
   * Global instance of the JSON factory.
   */
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  /**
   * Directory to store authorization tokens for this application.
   */
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES =
      Collections.singletonList(CalendarScopes.CALENDAR);
  private static final String CREDENTIALS_FILE_PATH = "resources/credentials.json";


 /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final HttpTransport httpTransport) throws IOException {
      // Load client secrets.
      GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
      CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH));

      // Build flow and trigger user authorization request.
      GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
              clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                      .setAccessType("offline").build();
      Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
      return credential;
  }

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredential(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    
  }

/////////////////////////////////////////////////////
  
/**
     * Retrieves the events from the specified calendar.
     *
     * @param calendarId The ID of the calendar to retrieve events from.
     * @param maxResults The maximum number of events to retrieve.
     * @return A list of events.
     * @throws IOException              If there was an error communicating with the Calendar API.
     * @throws GeneralSecurityException If there was a security-related error.
     */

  public static List<Event> getEvents(String calendarId, int maxResults) throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
            .setApplicationName(APPLICATION_NAME).build();

    // Retrieve the events from the calendar.
    Events events = service.events().list(calendarId).setMaxResults(maxResults).execute();
    return events.getItems();}


    public static void addEvent(String title, Date startDate, Date endDate) throws GeneralSecurityException, IOException {
      final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
      Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME).build();

      Event event = new Event().setSummary(title);
      Date startDateValue = startDate;
      Date endDateValue = endDate;
      DateTime start = new DateTime(startDateValue, TimeZone.getDefault());
      event.setStart(new EventDateTime().setDateTime(start));
      DateTime end = new DateTime(endDateValue, TimeZone.getDefault());
      event.setEnd(new EventDateTime().setDateTime(end));
      service.events().insert("primary", event).execute();
      System.out.printf("Event created: %s\n", event.getHtmlLink());
  }

    public void createEvent(String eventSummary, String eventDescription, String eventLocation, String eventTimeZone,
    java.util.Date date, java.util.Date date2) throws IOException {
    // Créer une nouvelle instance de l'événement
    Event event = new Event();
    event.setSummary(eventSummary);
    event.setDescription(eventDescription);
    event.setLocation(eventLocation);
    event.setTimeZone(eventTimeZone);

    // Définir les dates de début et de fin de l'événement
    DateTime startDateTime = new DateTime(date);
    EventDateTime start = new EventDateTime()
    .setDateTime(startDateTime)
    .setTimeZone(eventTimeZone);
    event.setStart(start);

    DateTime endDateTime = new DateTime(date2);
    EventDateTime end = new EventDateTime()
    .setDateTime(endDateTime)
    .setTimeZone(eventTimeZone);
    event.setEnd(end);

    // Envoyer la requête d'insertion pour ajouter l'événement au calendrier
    event = calendar.events().insert("primary", event).execute();
    System.out.printf("Événement ajouté : %s\n", event.getHtmlLink());
    }


/////////////////////////////////////////////////////

  public static void main(String... args) throws IOException, GeneralSecurityException {
    // Build a new authorized API client service.
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Calendar service =
        new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();

    // List the next 10 events from the primary calendar.
    DateTime now = new DateTime(System.currentTimeMillis());
    Events events = service.events().list("primary")
        .setMaxResults(10)
        .setTimeMin(now)
        .setOrderBy("startTime")
        .setSingleEvents(true)
        .execute();
    List<Event> items = events.getItems();
    if (items.isEmpty()) {
      System.out.println("No upcoming events found.");
    } else {
      System.out.println("Upcoming events");
      for (Event event : items) {
        DateTime start = event.getStart().getDateTime();
        if (start == null) {
          start = event.getStart().getDate();
        }
        System.out.printf("%s (%s)\n", event.getSummary(), start);
      }
    }
  }

}