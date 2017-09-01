package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

/**
 * Created by cciocoveanu
 */
public class MainPage extends GenericPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='row']//div[@class='image']/a")
    private List<WebElement> productList;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void selectRandomProduct(){
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        int[] index = {0, 1, 3};

        productList.get(index[randomNum]).click();
    }
}
