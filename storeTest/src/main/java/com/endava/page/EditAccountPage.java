package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by cciocoveanu
 */
public class EditAccountPage extends GenericPage{
    private WebDriver webDriver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    public EditAccountPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String getFirstName(){
        return firstNameField.getAttribute("value");
    }

    public String getLastName(){
        return lastNameField.getAttribute("value");
    }

    public String getEmail(){
        return emailField.getAttribute("value");
    }

    public String getTelephone(){
        return telephoneField.getAttribute("value");
    }
}
