package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.amazon.utils.SetAndGetData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AddToCartPage {
    WebDriver driver;
    ActionsDriver actionsDriver;
    SetAndGetData setAndGetData=new SetAndGetData();

    public AddToCartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }

    String listElement="//div[@class='s-main-slot s-result-list s-search-results sg-row']/div";
    public void getMaxBoughtProduct(){
        int MaxBoughtNumber=0;
        String ProductName=null;
        String ProductPrice1=null;
        String ProductPriceDeal1=null;
        int listCount=driver.findElements(By.xpath(listElement)).size();
        List<String> list=new ArrayList<String>(5);
        for(int i=1;i<=listCount;i++){
            String ProductBougthCount=listElement+"["+i+"]/div/div/div/div/div/div[2]/div/div/div[2]/div/span[2]/a/span";
            if(driver.findElements(By.xpath(ProductBougthCount)).size()!=0){
                int boughtNumber=Integer.parseInt(actionsDriver.getTextString(driver.findElement(By.xpath(ProductBougthCount))).replaceAll(",",""));
                if(boughtNumber>MaxBoughtNumber){
                    MaxBoughtNumber=boughtNumber;
                    ProductName=listElement+"["+i+"]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span";
                    ProductPrice1=listElement+"["+i+"]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[1]/div[1]/a/span[1]/span[1]";
                    ProductPriceDeal1=listElement+"["+i+"]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[1]/div[2]/a/span[1]/span[1]";
                }
            }
        }
        setAndGetData.setProductName(actionsDriver.getTextString(driver.findElement(By.xpath(ProductName))));
        if(driver.findElements(By.xpath(ProductPrice1)).size()!=0){
            setAndGetData.setProductPrice(actionsDriver.getTextString(driver.findElement(By.xpath(ProductPrice1))));
        }else if(driver.findElements(By.xpath(ProductPriceDeal1)).size()!=0){
            setAndGetData.setProductPrice(actionsDriver.getTextString(driver.findElement(By.xpath(ProductPriceDeal1))));
        }
        System.out.println(MaxBoughtNumber + " time users bought--->" + actionsDriver.getTextString(driver.findElement(By.xpath(ProductName))));
        actionsDriver.JSClick(driver.findElement(By.xpath(ProductName)));
        actionsDriver.hardwait(4000);
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                actionsDriver.JSClick(driver.findElement(By.xpath("//input[@id='add-to-cart-button']")));
                actionsDriver.hardwait(5000);
                actionsDriver.JSClick(driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']/span")));
                actionsDriver.hardwait(5000);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    public void validateCart(String ProductName, String ProductPrice){
        actionsDriver.JSClick(driver.findElement(By.xpath("//span[@class='nav-cart-count nav-cart-0 nav-progressive-attribute nav-progressive-content']")));
        String prdNameCart=actionsDriver.getTextString(driver.findElement(By.xpath("//div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/div[4]/div/div/div/div/div[2]/ul/li/span/a/span/span/span")));
        String prdPriceCart=actionsDriver.getTextString(driver.findElement(By.xpath("//div[@class='a-row sc-list-item sc-list-item-border sc-java-remote-feature']/div[4]/div/div[2]/p/span")));
        System.out.println(prdNameCart + " ------------> " + prdPriceCart);
    }
}
