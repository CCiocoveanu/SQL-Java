package com.endava.test;

import com.endava.page.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Created by cciocoveanu
 */
public class CoreTest {
    static WebDriver webDriver;

    static MainPage mainPage;
    static RegisterPage registerPage;
    static MyAccountPage myAccountPage;
    static EditAccountPage editAccountPage;
    static LoginPage loginPage;
    static AdressBookPage adressBookPage;
    static ProductPage productPage;
    static ShoppingCartPage shoppingCartPage;

    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver","C:/Users/cciocoveanu/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.get("http://192.168.164.15");

        mainPage = PageFactory.initElements(webDriver, MainPage.class);
    }

    @AfterClass
    public static void after() {

        webDriver.close();
    }

    public Integer getRandomNumber(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    public static String readFromProperties(String key, String fileName){
        try{
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            String value = properties.getProperty(key);
            return value;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
