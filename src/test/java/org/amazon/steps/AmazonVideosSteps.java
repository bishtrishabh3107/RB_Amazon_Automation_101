package org.amazon.steps;

import org.amazon.actionsdriver.ActionsDriver;
import org.amazon.pages.AmazonVideosLoginPage;
import org.amazon.pages.LoginPage;
import org.amazon.utils.BasePage;
import org.amazon.utils.PropsReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AmazonVideosSteps extends BasePage {
    AmazonVideosLoginPage alp=new AmazonVideosLoginPage(driver);
    int number_of_broken_links=0;
    public void getContentFromCategories(String category, String filter) throws InterruptedException, IOException {
        Thread.sleep(3000);
        alp.selectCategory(category);
        //alp.selectFilter(filter);
        List<WebElement> eleList=driver.findElements(By.xpath("//div[@class='av-grid-wrapper']/div/div"));
        System.out.println("Total number of Content :"+eleList.size());
        for(int i=0;i<eleList.size();i++){
            WebElement ele=driver.findElement(By.xpath("//div[@class='av-grid-wrapper']/div/div["+i+"]/div/div/div/span/a"));
            System.out.println(ele.getText());
            String url=ele.getAttribute("href");

            URL link = new URL(url);
            HttpURLConnection httpconn=(HttpURLConnection)link.openConnection();
            Thread.sleep(2000);
            httpconn.connect();

            int resCode=httpconn.getResponseCode();

            if(resCode>=400) {
                System.out.println(ele.getText() + " is broken link.");
                number_of_broken_links++;
            }
            else {
                System.out.println(ele.getText() + " is valid link");
            }
        }
        System.out.println(number_of_broken_links + " links are broken.");

    }

    public void validateContentFromCategories(String category, String name){
            alp.selectCategory(category);
            List<WebElement> eleList=driver.findElements(By.xpath("//div[@class='av-grid-wrapper']/div/div"));
            boolean flag=false;
            for(int i=0;i<eleList.size();i++){
                WebElement ele=driver.findElement(By.xpath("//div[@class='av-grid-wrapper']/div/div["+i+"]/div/div/div/span/a"));
                if((ele.getText()).equals(name)){
                    flag=true;
                    break;
                }
            }
            Assert.assertTrue(flag);
    }

}

