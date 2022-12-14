package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    ActionsDriver actionsDriver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }

    @FindBy(xpath="//div[@id='nav-signin-tooltip']/a/span")
    private WebElement bannerHeaderSignIn;
    public void clickBannerSignIn(){
        actionsDriver.moveToElementClick(bannerHeaderSignIn);
    }


    @FindBy(xpath="//div[@class='layoutToolbarPadding']/a[2]")
    private WebElement linkHeaderSignIn;
    public void cickHeaderSignIn(){
        actionsDriver.driverClick(linkHeaderSignIn);
    }

    @FindBy(id="ap_email")
    private WebElement txtusername;
    public void setUserName(String username){
        actionsDriver.typeText(txtusername,username);
    }


    @FindBy(id="continue")
    private WebElement btnContinue;
    public void cickContinue(){
        actionsDriver.moveToElementClick(btnContinue);
    }

    @FindBy(xpath="//input[@id='ap_password']")
    private WebElement txtpassword;
    public void setPassword(String password){
        actionsDriver.typeText(txtpassword,password);
    }

    @FindBy(xpath = "//input[@id='signInSubmit']")
    private WebElement btnLogin;
    public void clickLogin(){
       actionsDriver.JSClick(btnLogin);
    }


    @FindBy(xpath = "(//div[@class='_28p97w'])[1]")
    private WebElement usernameDropdown;

    @FindBy(xpath = "//div[@class='layoutToolbarPadding']/a[2]/div/span")
    private WebElement xpathWelcomeAmzShp;
    @FindBy(xpath = "//span[@class='profiles-dropdown-name']")
    private WebElement xpathWelcomeAmzVideo;

    public boolean validateWelcome(String appName){
        boolean flag=false;
        switch (appName.toLowerCase()) {
            case ("amazon shopping"):
                flag= actionsDriver.validateText(xpathWelcomeAmzShp, "Hello, Rishabh");
            break;
            case ("amazon videos"):
                flag= actionsDriver.validateText(xpathWelcomeAmzVideo, "Rishabh Bisht");
            break;
            default:
                System.out.println("Invalid AppName");
        }
        return flag;
    }

    @FindBy(xpath = "//div[@id='nav-logo']")
    private WebElement amazonNavLink;
    public void clickAmazonNavLink(){
        actionsDriver.moveToElementClick(amazonNavLink);
    }

    @FindBy(xpath = "//a[@id='nav-item-signout']")
    private WebElement signOutLink;
    public void clickSignOutLink() {
        actionsDriver.JSClick(signOutLink);
    }

}
