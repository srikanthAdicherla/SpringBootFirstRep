package TestClassPanNumbers;

import java.util.HashSet;
import java.util.Set;

public class TestPanClass {
    public static void main(String[] args) {
        Set<String> panNumbers = new HashSet<>();
        Set<String> customerNumbers = new HashSet<>();

        panNumbers.add("sri");
        panNumbers.add("segteri");
        panNumbers.add("34536fgsri");
        panNumbers.add("srartyei");
        panNumbers.add("srierhye");
        String tempPanQuery = "Select c.customerId from c where c.panNumber in(";
        StringBuilder queryBulider = new StringBuilder();
        int i = 0;
        for (String s : panNumbers) {
            {
                queryBulider.append("'");
                queryBulider.append(s);
                queryBulider.append("'");
                if (i < panNumbers.size() - 1) {
                    queryBulider.append(",");
                }
                i++;
            }
        }
        queryBulider.append(")");
        String custNbr = tempPanQuery + queryBulider.toString();

        StringBuilder customerNumber = new StringBuilder();
        String lastQuery = "select * from c where c.customerId in(";
        StringBuilder customerIdBuilder = new StringBuilder();
        int j = 0;
        for (String id : customerNumbers) {
            {
                customerIdBuilder.append("'");
                customerIdBuilder.append(id);
                customerIdBuilder.append("'");
                if (j < panNumbers.size() - 1) {
                    customerIdBuilder.append(",");
                }
                j++;
            }
        }
        customerIdBuilder.append(")");
    }
}
