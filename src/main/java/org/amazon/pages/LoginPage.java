package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    ActionsDriver actionsDriver=new ActionsDriver();

    @FindBy(xpath="//div[@id='nav-signin-tooltip']/a/span")
    private WebElement bannerHeaderSignIn;
    public void clickBannerSignIn(){
        actionsDriver.moveToElementClick(driver,bannerHeaderSignIn);
    }


    @FindBy(xpath="//div[@class='layoutToolbarPadding']/a[2]")
    private WebElement linkHeaderSignIn;
    public void cickHeaderSignIn(){
        actionsDriver.driverClick(driver,linkHeaderSignIn);
    }

    @FindBy(id="ap_email")
    private WebElement txtusername;
    public void setUserName(String username){
        actionsDriver.typeText(driver,txtusername,username);
    }


    @FindBy(id="continue")
    private WebElement btnContinue;
    public void cickContinue(){
        actionsDriver.moveToElementClick(driver,btnContinue);
    }

    @FindBy(xpath="//input[@id='ap_password']")
    private WebElement txtpassword;
    public void setPassword(String password){
        actionsDriver.typeText(driver,txtpassword,password);
    }

    @FindBy(xpath = "//input[@id='signInSubmit']")
    private WebElement btnLogin;
    public void clickLogin(){
       actionsDriver.JSClick(driver,btnLogin);
    }


    @FindBy(xpath = "(//div[@class='_28p97w'])[1]")
    private WebElement usernameDropdown;

    @FindBy(xpath = "//div[@class='layoutToolbarPadding']/a[2]/div/span")
    private WebElement xpathWelcome;


    public boolean validateWelcome(){
        return actionsDriver.validateText(driver, xpathWelcome ,"Hello, Rishabh");
    }

}
