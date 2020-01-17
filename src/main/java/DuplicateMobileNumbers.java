import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.*;
public class DuplicateMobileNumbers {

        public static void main(String[] args) {
        ArrayList<String> testList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();
        testList.add("112");
        testList.add("8983136602");
        testList.add("  7976789098 ");
        testList.add("  9864837338");
        testList.add("6323339082  ");
        testList.add("00909 ");
        testList.add("893873432473");
//System.out.println(Pattern.matches("[6789][0-9]{9}","8652926602"));

        //System.out.println(numbers);

        for (String s : testList) {
            if(s.trim().matches("[^0-5][0-9]{9}"))
                resultList.add(s.trim());
        }
       // System.out.println(resultList);
        for (String r:resultList) {
            System.out.println(r);

        }
       /* for (int i = 0; i < testList.size(); i++) {
            //System.out.print(testList.get(i) + " ");
            if (testList.get(i).startsWith("6") && testList.get(i).startsWith("9") && testList.get(i).length()==10){
            resultList.add(testList.get(i));

            }*/
        }

        }



