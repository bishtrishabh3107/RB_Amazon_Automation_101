package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
    WebDriver driver;
    ActionsDriver actionsDriver;

    public SearchResultsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }
}
