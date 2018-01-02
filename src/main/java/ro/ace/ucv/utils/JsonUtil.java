package ro.ace.ucv.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonUtil {

    private static final String REGEX_AND = "&";
    private static final String REGEX_EQUAL = "=";
    private static final String REGEX_PLUS = "\\+";
    private static final String REGEX_SPACE = " ";
    private static final String REGEX_SPECIAL_COMMA = "%2C";
    private static final String REGEX_COMMA = ",";

    private static HashMap<String, String> contentCharacters = new HashMap<String, String>();

    private static final String[] unreadableRegexValues =
     {
             "%2C", //comma
             "\\+", //space
             "%28", // left open bracket (
             "%29", // right close bracket )
             "%21", // !
             "%3F", // ?
             "%3B", // ;
             "%3A", // :
             "%22", // ""
             "%2F", // /
             "%5C", // \
             "%5B", // [
             "%5D", // ]
             "%7B", // {
             "%7D", // }
             "%25" // percent %
     };

    private static final String[] readableRegexValues =
     {
          ",",
          " ",
          "\\(",
          "\\)",
          "\\!",
          "\\?",
          "\\;",
          "\\:",
          "\\\"", // ""
          "\\/",
          "\\\\", // \
          "\\[",
          "\\]",
          "\\{",
          "\\}",
          "\\%"
     };


    public static HashMap<String, String> populateHashMap(String[] keyValues, String[] valueOfValues){

        int sizeOfValues = keyValues.length;
        int i = 0;
        while(i<sizeOfValues){
            contentCharacters.put(keyValues[i], valueOfValues[i]);
            i++;
        }
        return contentCharacters;
    }

    //TODO: convert from string to url - java method
    public static HashMap<String, String> getJsonValues(String value){

        contentCharacters = populateHashMap(unreadableRegexValues, readableRegexValues);
        HashMap<String, String> jsonMap = new HashMap<String, String>();
        String[] temporatyValues = value.split(REGEX_AND);

        for(int i=0; i<temporatyValues.length; i++){
            String[] valuesToAdd = temporatyValues[i].split(REGEX_EQUAL);
            String myValue = valuesToAdd[1].replaceAll(REGEX_SPECIAL_COMMA, REGEX_COMMA).replaceAll(REGEX_PLUS, REGEX_SPACE);
            jsonMap.put(valuesToAdd[0], myValue);
        }
        return jsonMap;
    }

    public static String getValueByFilter(HashMap<String, String> jsonMap, String filter){
        Set<Map.Entry<String, String>> entrySet1 = jsonMap.entrySet();
        Iterator<Map.Entry<String, String>> entrySetIterator = entrySet1.iterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry entry = entrySetIterator.next();
            if(filter.equalsIgnoreCase(entry.getKey().toString())){
                return entry.getValue().toString();
            }
        }
        return null;
    }
}
