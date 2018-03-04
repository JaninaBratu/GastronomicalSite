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

     public static HashMap<String, String> getJsonValues(String value){

        HashMap<String, String> jsonMap = new HashMap<String, String>();
        String[] temporatyValues = value.split(REGEX_AND);

        for(int i=0; i<temporatyValues.length; i++){
            String[] valuesToAdd = temporatyValues[i].split(REGEX_EQUAL);
            jsonMap.put(valuesToAdd[0], valuesToAdd[1]);
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
