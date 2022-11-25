package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    ActionsDriver actionsDriver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }

    @FindBy(xpath = "(//input[@class='nav-input nav-progressive-attribute'])[1]")
    private WebElement searchBar;
    @FindBy(xpath = "(//input[@class='nav-input nav-progressive-attribute'])[2]")
    private WebElement firstSearchSugession;
    public void searchFromSearchBar(String searchItem){
        actionsDriver.typeText(searchBar,searchItem);
        actionsDriver.JSClick(firstSearchSugession);
    }


}
