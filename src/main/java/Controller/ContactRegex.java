package Controller;

import java.util.regex.Pattern;

public class ContactRegex {

    private ContactRegex(){}

    private static Pattern CONTACT_REGEX = Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte|Contact|né|naissance|âge|age|née|nee|Mail|Email|Mèl|Mel|adresse|habite|maison)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
    private static Pattern NUMBER_REGEX = Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    private static Pattern BIRTH_REGEX = Pattern.compile("\\b(né|naissance|âge|age|née|nee)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    private static Pattern EMAIL_REGEX = Pattern.compile("\\b(Mail|Email|Mèl|Mel)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    private static Pattern ADDRESS_REGEX = Pattern.compile("\\b(adresse|habite|maison)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);

    public static Pattern getContactPattern(){
        return CONTACT_REGEX;
    }

    public static Pattern getNumPattern(){
        return NUMBER_REGEX;
    }

    public static Pattern getBirthdayPattern(){
        return BIRTH_REGEX;
    }

    public static Pattern getEmailPattern(){
        return EMAIL_REGEX;
    }

    public static Pattern getAddressPattern(){
        return ADDRESS_REGEX;
    }
}
