package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class MyAccountPage  extends GenericPage{
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@id='content']/ul[@class='list-unstyled'][1]/li/a")
    private List<WebElement> myAccountList;

    @FindBy(xpath = "//div[@id='content']/ul[@class='list-unstyled'][2]/li/a")
    private List<WebElement> myOrdersList;

    @FindBy(xpath = "//div[@id='content']/ul[@class='list-unstyled'][3]/li/a")
    private List<WebElement> myAffiliateAccountList;

    @FindBy(xpath = "//div[@id='content']/ul[@class='list-unstyled'][4]/li/a")
    private List<WebElement> newsletterList;

    public MyAccountPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public EditAccountPage goToEditAccountPage(){
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(myAccountList.get(0)));
        myAccountList.get(0).click();

        EditAccountPage editAccountPage = PageFactory.initElements(webDriver, EditAccountPage.class);
        return  editAccountPage;
    }
    public AdressBookPage goToAdressBookPage(){
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(myAccountList.get(2)));
        myAccountList.get(2).click();

        AdressBookPage adressBookPage = PageFactory.initElements(webDriver, AdressBookPage.class);
        return  adressBookPage;
    }
}
