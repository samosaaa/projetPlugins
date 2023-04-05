import java.util.regex.Pattern;

public class Regex {
    public static Pattern CONTACT_REGEX = Pattern.compile("\\b(|né|naissance|âge|age|née|nee|Mail|Email|Mèl|Mel|Contact)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    public static Pattern NUMBER_REGEX = Pattern.compile("\\b(numero|num|tel|fix|contacter|contacte)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    public static Pattern BIRTH_REGEX = Pattern.compile("\\b(né|naissance|âge|age|née|nee)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    public static Pattern EMAIL_REGEX = Pattern.compile("\\b(Mail|Email|Mèl|Mel)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
    public static Pattern ADDRESS_REGEX = Pattern.compile("\\b(adresse|habite|maison)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE |  Pattern.CANON_EQ);
}
