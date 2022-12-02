package org.amazon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public static WebDriver driver;
    BrowserConfig browserConfig=new BrowserConfig();

    public BasePage(){
        PageFactory.initElements(driver,this);
        this.driver=browserConfig.getDriver();
    }

}
