package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class AdressBookPage extends GenericPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='table-responsive']//tr/td[@class='text-left']")
    private List<WebElement> adressBookList;

    public AdressBookPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String[] lastEnteredAdressBookText(){
         return adressBookList.get(adressBookList.size()-1).getText().split("\\r?\\n");
    }
}
