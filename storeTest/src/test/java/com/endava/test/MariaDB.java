package com.endava.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cciocoveanu
 */
public class MariaDB {
    private String loginEmail;
    private static String connectionURL = CoreTest.readFromProperties("connectionURL", "src/test/resources/connectToDB.properties");
    private static String username = CoreTest.readFromProperties("username", "src/test/resources/connectToDB.properties");
    private static String password = CoreTest.readFromProperties("password", "src/test/resources/connectToDB.properties");
    private Connection connection = DriverManager.getConnection(connectionURL, username, password);


    public MariaDB(String loginEmail) throws SQLException {
        this.loginEmail = loginEmail;
    }

    public void addNewAdress(String firstName, String lastName, String company, String adress1, String adress2, String city, String postcode, Integer countryId, Integer zoneId, Integer customField) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO oc_address (customer_id, firstname, lastname, company, address_1, address_2, city, postcode, country_id, zone_id, custom_field) VALUES ((SELECT customer_id from oc_customer WHERE email=\""+loginEmail+"\"), \""+firstName+"\", \""+lastName+"\", \""+company+"\", \""+adress1+"\", \""+adress2+"\", \""+city+"\", \""+postcode+"\", "+countryId+", "+zoneId+","+customField+");");
        statement.close();
    }

    public void incrementProduct(String productName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE oc_cart SET quantity = quantity + 1 WHERE customer_id = (SELECT customer_id from oc_customer WHERE email=\""+loginEmail+"\") AND product_id = (SELECT product_id FROM oc_product_description WHERE name = \""+productName+"\");");
        statement.close();
    }

    public void deleteCartContent() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM oc_cart WHERE customer_id = (SELECT customer_id from oc_customer WHERE email=\""+loginEmail+"\");");
        statement.close();
    }

    public void closeConnection() throws SQLException {connection.close();}
}
