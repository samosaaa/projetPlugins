package Controller;

import java.io.IOException;
import java.util.regex.Matcher;

import Model.DictionnaryModel;
import Model.HttpURLConn;

public class DictionnaryController {

    private DictionnaryController(){}

    public static boolean isDictionnary(String input){
        Matcher matchDictionnary = DictionnaryRegex.getDictionnaryPattern().matcher(input);
        System.out.println(matchDictionnary.find());
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

    public static void getDefinition(String word) throws IOException {
        DictionnaryModel dictionnaryModel = new HttpURLConn();
        dictionnaryModel.findDefinition(word);
    }

    public static void DictionnaryManager(String input) throws IOException{
        String word = findWordToDefine(input);
        getDefinition(word);

    }
}
