package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class RegisterPage extends GenericPage {
    private WebDriver webDriver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(id = "input-password")
    private WebElement passwordField;

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmField;

    @FindBy(xpath = "//div[@class='form-group']//input")
    private List<WebElement> subscribeList;

    @FindBy(xpath = "//div[@class='pull-right']/input[1]")
    private WebElement agreePolocyCheckbox;

    @FindBy(xpath = "//div[@class='pull-right']/input[2]")
    private WebElement contiuneButton;

    @FindBy(xpath = "//div[@class='buttons']//a")
    private WebElement contiuneButtonAfterRegister;

    public RegisterPage(WebDriver webDriver){
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void createAccount(String firstName, String lastName, String email, String telephone, String password, Integer subscribe){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        emailField.clear();
        emailField.sendKeys(email);
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordConfirmField.clear();
        passwordConfirmField.sendKeys(password);
        subscribeList.get(subscribe).click();
        agreePolocyCheckbox.click();
        contiuneButton.click();

        WebDriverWait wait = new WebDriverWait(webDriver, 4);
        wait.until(ExpectedConditions.elementToBeClickable(contiuneButtonAfterRegister));
        contiuneButtonAfterRegister.click();
    }
}
