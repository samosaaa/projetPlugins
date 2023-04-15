package Controller;

import java.util.regex.Pattern;

public class DictionnaryRegex {

    private DictionnaryRegex(){}
    
    private static Pattern DICTIONNARY_REGEX = Pattern.compile("\\b(signifie|veut dire|définition|def|definition)\\b", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.CANON_EQ);
    private static Pattern BETWEEN_QUOTES = Pattern.compile("\"([^\"]*)\"");

    public static Pattern getDictionnaryPattern(){
        return DICTIONNARY_REGEX;
    }

    public static Pattern getQuotesPattern(){
        return BETWEEN_QUOTES;
    }
}
