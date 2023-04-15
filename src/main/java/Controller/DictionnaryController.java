package Controller;

import java.util.regex.Matcher;

import Model.DictionnaryModel;

public class DictionnaryController {

    private DictionnaryController(){}

    public static boolean isDictionnary(String input){
        Matcher matchDictionnary = DictionnaryRegex.getDictionnaryPattern().matcher(input);
        return matchDictionnary.find();
    }

    public static String findWordToDefine(String input){
        Matcher matchQuotes = DictionnaryRegex.getQuotesPattern().matcher(input);
        if(matchQuotes.find()) {
            String quotedWord = matchQuotes.group(1);
            return quotedWord;
        }
        return null;
    }

    public static void DictionnaryManager(String input){
        String word = findWordToDefine(input);
        DictionnaryModel.findDefinition(word);

    }
}
