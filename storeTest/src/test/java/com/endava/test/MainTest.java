package com.endava.test;

import com.endava.page.MyAccountPage;
import com.endava.page.ProductPage;
import com.endava.page.ShoppingCartPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.sql.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by cciocoveanu
 */
public class MainTest extends CoreTest {

    private String logInEmail = readFromProperties("name", "src/test/resources/login.properties");
    private String password = readFromProperties("password", "src/test/resources/login.properties");

    @Test
    public void registerUserTest(){
        // 1. Navigate My Account -> Register
        registerPage = mainPage.goToRegisterPage();

        // 2. Fill the form with your information (email address, name etc) , 3. Summit the form
        Integer rand = getRandomNumber();
        String firstName = "John";
        String lastName = "Snow"+rand;
        String email = "john.snow"+rand+"@gmail.com";
        String telephone = "0724550"+rand;
        registerPage.createAccount(firstName, lastName, email, telephone, "winterIsHere", 1);
        //registerPage.createAccount("Constantin", "Ciocoveanu", "ciocoveanu.constantin@gmail.com", "0724372882", "hidden", 1);

        // --- My Account ---
        myAccountPage = PageFactory.initElements(webDriver, MyAccountPage.class);
        editAccountPage = myAccountPage.goToEditAccountPage();

        // 4. Assert user from Account -> Edit Information
        Assert.assertEquals(firstName, editAccountPage.getFirstName());
        Assert.assertEquals(lastName, editAccountPage.getLastName());
        Assert.assertEquals(email, editAccountPage.getEmail());
        Assert.assertEquals(telephone, editAccountPage.getTelephone());

        // 4. Log out
        editAccountPage.logOut();
    }

    @Test
    public void logInTest() {
        // 1. Navigate to My Account -> Login
        loginPage = mainPage.goToLogInPage();

        // 2. Fill your email and password
        loginPage.logIn(logInEmail, password);

        // 3. Verify login was successful
        mainPage.clickMyAccount();
        assertThat(mainPage.getLogOutButton().isDisplayed(), is(true));
        mainPage.clickMyAccount();

        // 4. Log out
        mainPage.logOut();
    }

    @Test
    public void addAdressBookTest() throws SQLException {
        // 1. Connect to database
        MariaDB database = new MariaDB(logInEmail);

        // 2. Add a new entry for your account in the oc_address ( dummy address)
        String firstName = readFromProperties("firstName", "src/test/resources/adress.properties");
        String lastName = readFromProperties("lastName", "src/test/resources/adress.properties");
        String company = readFromProperties("company", "src/test/resources/adress.properties");
        String adress1 = readFromProperties("adress1", "src/test/resources/adress.properties");
        String adress2 = readFromProperties("adress2", "src/test/resources/adress.properties");
        String city = readFromProperties("city", "src/test/resources/adress.properties");
        String postcode = readFromProperties("postcode", "src/test/resources/adress.properties");
        Integer countryId = Integer.valueOf(readFromProperties("countryId", "src/test/resources/adress.properties"));
        Integer zoneId = Integer.valueOf(readFromProperties("zoneId", "src/test/resources/adress.properties"));
        Integer customField = Integer.valueOf(readFromProperties("customField", "src/test/resources/adress.properties"));

        database.addNewAdress(firstName,lastName,company,adress1,adress2,city,postcode,countryId,zoneId,customField);
        database.closeConnection();
        // 3. Login in application
        loginPage = mainPage.goToLogInPage();
        loginPage.logIn(logInEmail, password);

        // 4. Navigate Account -> Address book
        myAccountPage = PageFactory.initElements(webDriver, MyAccountPage.class);
        adressBookPage = myAccountPage.goToAdressBookPage();

        // 5. Assert that the address is present in the UI
        assertThat(adressBookPage.lastEnteredAdressBookText()[0], is(firstName+" "+lastName));
        assertThat(adressBookPage.lastEnteredAdressBookText()[1], is(company));
        assertThat(adressBookPage.lastEnteredAdressBookText()[2], is(adress1));
        assertThat(adressBookPage.lastEnteredAdressBookText()[3], is(adress2));
        assertThat(adressBookPage.lastEnteredAdressBookText()[4], is(city+" "+postcode));
        assertThat(adressBookPage.lastEnteredAdressBookText()[5], is("Ismayilli"));
        assertThat(adressBookPage.lastEnteredAdressBookText()[6], is("Aruba"));

        adressBookPage.logOut();
    }

    @Test
    public void productSelectionTest() throws SQLException {
        // 1 Login in application
        loginPage = mainPage.goToLogInPage();
        loginPage.logIn(logInEmail, password);

        // 2. Click on a random product from Home Page
        mainPage.gotoMainPage();
        mainPage.selectRandomProduct();

        // 3. Add any required information
        Integer quantity = 2;
        productPage = PageFactory.initElements(webDriver, ProductPage.class);
        String productName = productPage.getProductName();
        productPage.addToCart(quantity);

        // 4. From database (oc_cart) for the current session increase quantity by 1
        MariaDB database = new MariaDB(logInEmail);
        database.incrementProduct(productName);

        // 5. Verify in the UI that the values has been increased
        productPage.gotoMainPage();
        ShoppingCartPage shoppingCartPage = mainPage.goToShoppingCart();
        assertThat(shoppingCartPage.getProductQuantity(productName), is((++quantity).toString()));

        // 6. Delete cart and log out
        database.deleteCartContent();
        database.closeConnection();
        shoppingCartPage.logOut();
    }
}
