package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonVideosLoginPage {

    WebDriver driver;

    public AmazonVideosLoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    ActionsDriver actionsDriver=new ActionsDriver();

    @FindBy(className = "pv-nav-account-icon")
    private WebElement accountDropdown;
    public void clickAccountDropdown(){
        actionsDriver.moveToElementClick(driver, accountDropdown);
    }

    @FindBy(xpath = "//*[@class='pv-nav-account-icon']/following::ul[1]/li[1]/a")
    private WebElement signInButton;
    public void clickSignInButton(){
        actionsDriver.JSClick(driver,signInButton);
    }

    @FindBy(xpath = "//label[@id='nav-profiles-dropdown-label']")
    private WebElement usernameDropdown;
    @FindBy(xpath = "//a[@id='pv-nav-sign-out']")
    private WebElement signOutButton;
    public void clickSignOutButton(){
        actionsDriver.JSClick(driver, usernameDropdown);
        actionsDriver.JSClick(driver, signOutButton);
    }

}
