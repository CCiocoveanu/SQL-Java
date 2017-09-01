package com.endava.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cciocoveanu
 */
public class ProductPage extends GenericPage{
    private WebDriver webDriver;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    @FindBy(xpath = "//h1[text()='Canon EOS 5D']/../div[@id='product']/div/select")
    private WebElement selectCanon;

    @FindBy(id = "input-quantity")
    private WebElement quantityField;

    @FindBy(xpath = "//div[@class='row']/div/h1")
    private WebElement productName;

    private By cannonColorSelector = By.xpath("//h1[text()='Canon EOS 5D']/../div[@id='product']/div/select");

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public void selectCanon(String color){
        Select dropdown = new Select(selectCanon);

        List<WebElement> optionList = dropdown.getOptions();
        List<WebElement> availableList = new ArrayList<>();
        for(WebElement element : optionList) {
            if(!element.getText().trim().isEmpty()){
                availableList.add(element);
            }
        }
        for(WebElement desktop : availableList) {
            if(desktop.getText().trim().equals(color)) desktop.click();
        }
    }

    public void addToCart(Integer quant){
        boolean present;
        try {
            webDriver.findElement(cannonColorSelector);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        quantityField.clear();
        quantityField.sendKeys(quant.toString());

        if(present) selectCanon("Red");
        addToCartButton.click();
    }

    public String getProductName(){
        return productName.getText();
    }
}
