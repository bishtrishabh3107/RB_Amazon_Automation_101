package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AmazonVideosLoginPage {

    WebDriver driver;
    ActionsDriver actionsDriver;
    public AmazonVideosLoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }

    @FindBy(className = "pv-nav-account-icon")
    private WebElement accountDropdown;
    public void clickAccountDropdown(){
        actionsDriver.moveToElementClick(accountDropdown);
    }

    @FindBy(xpath = "//*[@class='pv-nav-account-icon']/following::ul[1]/li[1]/a")
    private WebElement signInButton;
    public void clickSignInButton(){
        actionsDriver.JSClick(signInButton);
    }

    @FindBy(xpath = "//label[@id='nav-profiles-dropdown-label']")
    private WebElement usernameDropdown;
    @FindBy(xpath = "//a[@id='pv-nav-sign-out']")
    private WebElement signOutButton;
    public void clickSignOutButton(){
        actionsDriver.JSClick(usernameDropdown);
        actionsDriver.JSClick(signOutButton);
    }

    @FindBy(className = "pv-nav-main-menu-subdropdown-label")
    private WebElement categorydropdown;
    public void selectCategory(String category){
//        actionsDriver.JSClick(driver, categorydropdown);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.querySelector('label.pv-nav-main-menu-subdropdown-label>a>span',':before').click();");
          actionsDriver.moveToElementClick(categorydropdown);
//        Actions action = new Actions(driver);
//        action.moveToElement(driver.findElement(By.xpath("//header/div/a"))).perform();

        List<WebElement> eles=driver.findElements(By.xpath("//div[@class='cat_block--grid']/ul/li/a/span"));
        for(int i=0;i<eles.size();i++){
            if((eles.get(i).getText()).equals(category)){
                actionsDriver.moveToElementClick(eles.get(i));
            }
        }
    }

    @FindBy(className = "av-s-filters-dropdown")
    private WebElement filterBtn;
    @FindBy(css = "ul.av-s-refine-drop-section-drop>li>label>a>div>span:after")
    private WebElement filterBtnMovies;
    @FindBy(className = "av-s-filters-dropdown")
    private WebElement filterBtnTVSeries;
    public void selectFilter(String filter){


//        if(filter.equals("All TvSeries")){
//            actionsDriver.driverClick(driver,filterBtn);
//            actionsDriver.JSClick(driver,);
//        } else if (filter.equals("All Movies")) {
//            actionsDriver.driverClick(driver,filterBtn);
//        }else{
//            return;
//        }
    }





}
