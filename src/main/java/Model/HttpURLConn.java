package Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import View.Print;

import java.io.IOException;

public class HttpURLConn extends DictionnaryModel {

@Override
public void findDefinition(String word) throws IOException{
    URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine = in.readLine();
    String[] parts = inputLine.split(",");
    String definition = parts[7];
    Print.write(definition);
    in.close();
}
}