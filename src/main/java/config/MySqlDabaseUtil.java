package config;

import dto.ResultCustomer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDabaseUtil {

    public static ResultCustomer getValuesFromMysql(DataSource datasource, String customerId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        ResultCustomer resultCustomer = new ResultCustomer();

        try {

            connection = datasource.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM customerdetails where id=? ");

            pstmt.setString(1, customerId);
            List<String> addressList = new ArrayList<>();
            List<Integer> mobileList = new ArrayList<>();
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String address1 = resultSet.getString("Address1");
                String address2 = resultSet.getString("Address2");
                addressList.add(address1);
                addressList.add(address2);
                Integer numbers1 = resultSet.getInt("mobileNo1");
                Integer numbers2 = resultSet.getInt("mobileNo2");

                mobileList.add(numbers1);
                mobileList.add(numbers2);
            }

            resultCustomer.setId(customerId);
            resultCustomer.setAddress(addressList);
            resultCustomer.setMobileNo(mobileList);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return resultCustomer;
    }
}

