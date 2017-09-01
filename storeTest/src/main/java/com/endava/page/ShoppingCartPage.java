package com.endava.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by cciocoveanu
 */
public class ShoppingCartPage extends GenericPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='input-group btn-block']/input")
    private List<WebElement> productQuantityList;

    @FindBy(xpath = "//div[@class='table-responsive']//tbody/tr/td[2]/a")
    private List<WebElement> productNameList;

    public ShoppingCartPage(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public String getProductQuantity(String productName){
        int index = 0, i = 0;
        for(WebElement element : productNameList){
            //assertThat(element.getText(), is(productName));
            if(element.getText() == productName) index = i;
            i++;
        }
        return productQuantityList.get(index).getAttribute("value");
    }
}
