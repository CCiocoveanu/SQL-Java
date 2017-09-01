package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by cciocoveanu
 */
public class GenericPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@id='top-links']//li[contains(@class,'dropdown')]/a")
    private WebElement myAccountDropdown;

    @FindBy(xpath = "//div[@id='top-links']//li[contains(@class,'dropdown')]//li[1]/a")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@id='top-links']//li[contains(@class,'dropdown')]//li[2]/a")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='top-links']//li[contains(@class,'dropdown')]//li[5]/a")
    private WebElement logOutButton;

    @FindBy(xpath = "//div[@class='buttons']//a")
    private WebElement contiuneAfterLogOutButton;

    @FindBy(xpath = "//div[@id='logo']//a")
    private WebElement homePageButton;

    @FindBy(xpath = "//a[@title='Shopping Cart']/span")
    private WebElement shoppingCartButton;

    public GenericPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public RegisterPage goToRegisterPage(){
        myAccountDropdown.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();

        RegisterPage registerPage = PageFactory.initElements(webDriver, RegisterPage.class);
        return registerPage;
    }

    public LoginPage goToLogInPage(){
        myAccountDropdown.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        return loginPage;
    }

    public void logOut(){
        myAccountDropdown.click();
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        logOutButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(contiuneAfterLogOutButton));
        contiuneAfterLogOutButton.click();
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public void clickMyAccount(){
        myAccountDropdown.click();
    }

    public void gotoMainPage(){
        homePageButton.click();
    }

    public ShoppingCartPage goToShoppingCart(){
        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartButton));
        shoppingCartButton.click();

        ShoppingCartPage shoppingCartPage = PageFactory.initElements(webDriver, ShoppingCartPage.class);
        return  shoppingCartPage;
    }
}
