package org.amazon.steps;

import org.amazon.actionsdriver.ActionsDriver;
import org.amazon.pages.HomePage;
import org.amazon.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class SearchItemsSteps extends BasePage {
    ActionsDriver actionsDriver=new ActionsDriver(driver);
    String totalDivsOnResultPage="//div[@class='s-main-slot s-result-list s-search-results sg-row']/div";
    HomePage homePage=new HomePage(driver);
    public void searchFromSearchBar(String searchItem){
        homePage.searchFromSearchBar(searchItem);
        System.out.println("<--------- Searching For ------ " + searchItem + " ----------->");
    }

    public void getElectronicsItemFromResultPage(String getItemType){
        switch(getItemType.toLowerCase()){
            case "firstpage":
                allResults();
                break;

            case "sponsored":
                sponsoredResult();
                break;

            case "nonsponsored":
                nonSponsoredResult();
                break;

            case "bestseller":
                bestsellerResult();
                break;

            case "amazonchoice":
                amazonChoiceResult();
                break;

            default:
                System.out.println("Invalid input on getItemFromResultPage()");

        }
    }



    public int getCountResultPageSections(String ele){
        List<WebElement> allSections=driver.findElements(By.xpath(ele));
        return allSections.size();
    }

    public void allResults(){
        nonSponsoredResult();
        sponsoredResult();
    }
    public void bestsellerResult(){
        int bestSellerCount=0;
        String bestSellers="//span[text()='Best seller']";
        int count=driver.findElements(By.xpath(bestSellers)).size();
        List<String> bresult=new ArrayList<>(count);
        for(int i=1;i<=count;i++){
            String bestSeller="(//span[text()='Best seller'])["+i+"]/parent::span/parent::span/parent::span/parent::div/parent::span/parent::a/parent::div/parent::div/parent::div/parent::div/following-sibling::div/div/div/div[1]/h2/a/span";
            if (driver.findElements(By.xpath(bestSeller)).size()==1) {
                WebElement normalTxt = driver.findElement(By.xpath("(//span[text()='Best seller'])["+i+"]/parent::span/parent::span/parent::span/parent::div/parent::span/parent::a/parent::div/parent::div/parent::div/parent::div/following-sibling::div/div/div/div[1]/h2/a/span"));
                actionsDriver.scrollHighlight(normalTxt);
                bresult.add(normalTxt.getText());
                bestSellerCount++;
            }
        }
        for (String result: bresult) {
            System.out.println(result);
        }
        System.out.println("BestSeller Product count : ----------------> " +  bestSellerCount);
    }

    public void amazonChoiceResult(){
        int amazonChoiceCount=0;
        String amazonChoices="//span[@data-a-badge-color='ac-orange']";
        int count=driver.findElements(By.xpath(amazonChoices)).size();
        List<String> acresult=new ArrayList<>(count);
        for(int i=1;i<=count;i++){
            String amazonChoice="(//span[@data-a-badge-color='ac-orange'])["+i+"]/parent::span/parent::span/parent::span/parent::div/parent::span/parent::a/parent::span/parent::div/parent::div/parent::div/parent::div/following-sibling::div/div/div/div/h2/a/span";
            if (driver.findElements(By.xpath(amazonChoice)).size()==1) {
                WebElement normalTxt = driver.findElement(By.xpath(amazonChoice));
                actionsDriver.scrollHighlight(normalTxt);
                acresult.add(normalTxt.getText());
                amazonChoiceCount++;
            }
        }
        for (String result: acresult) {
            System.out.println(result);
        }
        System.out.println("AmazonChoice Product count : ----------------> " +  amazonChoiceCount);
    }
    public void nonSponsoredResult(){
        int count=getCountResultPageSections(totalDivsOnResultPage);
        int normalCount=0;
        List<String> nresult=new ArrayList<>(count);
        for(int i=1;i<=count;i++) {
            String normalProduct = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[" + i + "]/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if (driver.findElements(By.xpath(normalProduct)).size()==1) {
                WebElement normalTxt = driver.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[" + i + "]/div/div/div/div/div/div[2]/div/div/div/h2/a/span"));
                actionsDriver.scrollHighlight(normalTxt);
                nresult.add(normalTxt.getText());
                normalCount++;
            }
        }
        for (String result: nresult) {
            System.out.println(result);
        }
        System.out.println("NonSponsored Product count : ----------------> " +  normalCount);
    }

    public void sponsoredResult(){
        int count=getCountResultPageSections(totalDivsOnResultPage);
        int sponsoredCount=0;
        List<String> sresult=new ArrayList<>(count);
        for(int i=1;i<=count;i++) {
            String sponsoredProduct = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[" + i + "]/div/div/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if (driver.findElements(By.xpath(sponsoredProduct)).size() == 1) {
                WebElement sponsoredTxt = driver.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[" + i + "]/div/div/div/div/div/div/div/div[2]/div/div/div/h2/a/span"));
                actionsDriver.scrollHighlight(sponsoredTxt);
                sresult.add(sponsoredTxt.getText());
                sponsoredCount++;
            }
        }
        for (String result: sresult) {
            System.out.println(result);
        }
        System.out.println("Sponsored Product count : -------------> " +  sponsoredCount);
    }
}
