import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import com.google.api.services.people.v1.model.Person;

public class Main{

    public static void findPlugin() throws GeneralSecurityException, IOException{
        
        // Charger les informations d'authentification depuis le fichier credentials.json
        String credentialFilePath = "chemin/vers/credentials.json";
        CalendarQuickstart calendarQuickstart = new CalendarQuickstart();

        
        try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Bonjour, comment puis-je vous aider ? ");
                String input = sc.nextLine();

                if (ContactFunction.isContact(input)){ // verifie s'il s'agit d'une question de COntact
                    Person CONTACT = PeopleQuickstart.findContact(input);
                    if(CONTACT == null){
                        System.out.println("Je n'ai pas trouvé la personne dont vous parlez parmis vos contacts.");
                    }
                    else{
                        if(ContactFunction.matchNumber(input)){
                            System.out.println(InfoContact.numberToString(CONTACT));
                        }
                        else if(ContactFunction.matchBirth(input)){
                            InfoContact.birthdayToString(CONTACT);
                        }
                        else if(ContactFunction.matchEmail(input)){
                            InfoContact.emailToString(CONTACT);
                        }
                        else if(ContactFunction.matchAddress(input)){
                            InfoContact.addressToString(CONTACT);
                        }

                        
                    // Ajouter un événement au calendrier
                    String eventSummary = "Nouvel événement";
                    String eventDescription = "Description de l'événement";
                    String eventLocation = "Emplacement de l'événement";
                    String eventTimeZone = "Fuseau horaire de l'événement";
                    // Date de début de l'événement
                    java.util.Calendar startCalendar = java.util.Calendar.getInstance();
                    startCalendar.set(2023, 4, 15, 9, 0); // 15 mai 2023 à 09:00
                    // Date de fin de l'événement
                    java.util.Calendar endCalendar = java.util.Calendar.getInstance();
                    endCalendar.set(2023, 4, 15, 10, 0); // 15 mai 2023 à 10:00

                    // Appeler la méthode pour ajouter l'événement au calendrier
                    calendarQuickstart.createEvent(eventSummary, eventDescription, eventLocation, eventTimeZone,
                            startCalendar.getTime(), endCalendar.getTime());

                    System.out.println("Événement ajouté au calendrier avec succès.");
                }
            }
                else{
                    System.out.println("Désolée mais je n'ai pas compris, merci de reformuler votre demande.");
                }
            }
        
    }
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        findPlugin();
    }
}
