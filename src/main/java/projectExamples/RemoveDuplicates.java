package projectExamples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveDuplicates {
    public static void main(String[] args) {
String s="MATHAROO ROLLING SHUTTER MATHAROO ROLLING SHUTTER,1173 HARIPURA G ILL ROAD LUDHIANA     PUNJAB 141003                   |MATHAROO ROLLING SHUTTER MATHAROO ROLLING SHUTTER,1173 HARIPURA G ILL ROAD LUDHIANA  PUNJAB 141003"  ;
System.out.println(removeDuplicates(s));
    }

    private static String removeDuplicates(String input) {



        String regex = "(?i)\\b(\\w+)(\\b\\W+\\1\\b)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(input);
        while (m.find()) {
            input = input.replaceAll(regex, "**");
        }
        return input;
    }
}
