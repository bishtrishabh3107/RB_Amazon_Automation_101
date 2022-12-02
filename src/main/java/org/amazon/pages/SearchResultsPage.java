package org.amazon.pages;

import org.amazon.actionsdriver.ActionsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {
    WebDriver driver;
    ActionsDriver actionsDriver;

    public SearchResultsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actionsDriver=new ActionsDriver(driver);
    }

    public void clickCustomerReviewFilter(String customerReview){
        String customerReviewEle="//section[@aria-label='"+customerReview+" Stars & Up']";
        actionsDriver.JSClick(driver.findElement(By.xpath(customerReviewEle)));
        actionsDriver.hardwait(2000);
    }
    @FindBy(xpath="//input[@id='low-price']")
    private WebElement txtFieldMinPrice;
    public void enterTxtFieldMinPrice(String MinPrice){
        actionsDriver.typeText(txtFieldMinPrice,MinPrice);
    }
    @FindBy(xpath="//input[@id='high-price']")
    private WebElement txtFieldMaxPrice;
    public void enterTxtFieldMaxPrice(String MinPrice){
        actionsDriver.typeText(txtFieldMaxPrice,MinPrice);
    }
    @FindBy(xpath="//span[@class='a-button a-spacing-top-mini a-button-base s-small-margin-left']/span/input")
    private WebElement priceGoBtn;
    public void pressPriceGoBtn(){
        actionsDriver.moveToElementClick(priceGoBtn);
        actionsDriver.hardwait(2000);
    }

    public void clickDiscountFilter(String Discount){
        String discountEle="//span[text()[contains(.,'"+Discount+" Off or more')]]";
        actionsDriver.JSClick(driver.findElement(By.xpath(discountEle)));
        actionsDriver.hardwait(2000);
    }

    @FindBy(xpath = "//div[@data-component-type='s-search-result']/div/div/div/div/div/div/h2/a/span")
    private WebElement gridProductName;

    public void clickBrandFilter(List<String> brandsList){
        for(int i = 0; i<brandsList.size(); i++){
            List<WebElement> filterListCheckboxes=driver.findElements(By.xpath("//div[@class='a-checkbox a-checkbox-fancy s-navigation-checkbox aok-float-left']/following::span[1]"));
            filterExpandClick();
            for(int j=0;j<filterListCheckboxes.size();j++) {
                int attempt=0;
                while(attempt<2) {
                    try {
                        System.out.println("Checkbox Name: " + filterListCheckboxes.get(j).getText().toLowerCase() + " Brand Name: " + brandsList.get(i));
                        if (filterListCheckboxes.get(j).getText().toLowerCase().equals(brandsList.get(i))) {
                            actionsDriver.JSClick(filterListCheckboxes.get(j));
                            actionsDriver.hardwait(2000);
                        }
                        break;
                    }catch (StaleElementReferenceException e){
//                        System.out.println("<------ State Element ----->");
                    }
                    attempt++;
                }
            }
        }
    }

    public void filterExpandClick() {
        try {
            List<WebElement> filterExpandIcons = driver.findElements(By.className("a-expander-prompt"));
            for(int k=0;k<filterExpandIcons.size();k++){
                actionsDriver.JSClick(filterExpandIcons.get(k));
            }
            System.out.println("All Expand Filer icons Clicked");
        }catch (Exception e) {
            System.out.println("******* NO expand found *********");
        }
    }

    //String gridsElement="(//div[@class='sg-col-20-of-24 s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16']/div/span[1]/div/div)";
    String gridsElement="//div[@class='s-main-slot s-result-list s-search-results sg-row']/div";

    public void validateProductRating(int Rating){
        int gridCount= driver.findElements(By.xpath(gridsElement)).size();
        while(gridCount==0){
            actionsDriver.hardwait(1000);
        }
        System.out.println("Grid Count : " + gridCount);
        System.out.println("Rating to validate -------> "+ Rating);
        for(int i=1;i<=gridCount;i++){
            String gridSponPrdRating=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div[3]/div/span[1]/span/a/i[1]";
            String gridNonSponPrdRating1=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[3]/div/span[1]/span/a/i[1]";
            String gridNonSponPrdRating2=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/div/span[1]/span/a/i[1]";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2/a/span";
            String gridNonSponPrdName1=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2/a/span";
            String gridNonSponPrdName2=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div/h2/a/span";
            if(driver.findElements(By.xpath(gridSponPrdRating)).size()!=0){
                System.out.println(i);
                String SponPrdRating= actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdRating)));
                String SponPrdName= actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Rating of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdRating);
            } else if (driver.findElements(By.xpath(gridNonSponPrdRating1)).size()!=0) {
                System.out.println(i);
                String NonSponPrdRating1=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdRating1)));
                String NonSponPrdName1=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdName1)));
                System.out.println("Rating of NonSponsored1 Prod --> " + NonSponPrdName1 + " ---------> " + NonSponPrdRating1);
            } else if (driver.findElements(By.xpath(gridNonSponPrdRating2)).size()!=0) {
                System.out.println(i);
                String NonSponPrdRating2=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdRating2)));
                String NonSponPrdName2=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdName2)));
                System.out.println("Rating of NonSponsored2 Prod --> " + NonSponPrdName2 + " ---------> " + NonSponPrdRating2);
            }else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductPrice(int MinPrice, int MaxPrice){
        int gridCount= driver.findElements(By.xpath(gridsElement)).size();
        System.out.println("Grid Count : " + gridCount);
        System.out.println("Prices to validate : " + MinPrice + " <--to--> " +MaxPrice);
        for(int i=1;i<gridCount;i++){
            String gridSponPrdPrice=gridsElement+ "["+ i +"]/div/div/div/div/div/div/div/div[4]/div[2]/a/span[1]";
            String gridNonSponPrdPricewithDeal=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[4]/div[2]/a/span[1]";
            String gridNonSponPrdPrice=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[4]/div/a/span";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2";
            String gridNonSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2";
            if(driver.findElements(By.xpath(gridSponPrdPrice)).size()!=0){
                System.out.println(i);
                String priceSponPrdPrice=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdPrice)));
                String SponPrdPriceName=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Price of Sponsored Prod --> " + SponPrdPriceName + " ---------> " + priceSponPrdPrice);
            } else if (driver.findElements(By.xpath(gridNonSponPrdPricewithDeal)).size()!=0) {
                System.out.println(i);
                String priceNonSponPrdPriceWithDeal=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdPricewithDeal)));
                String NonSponPrdPriceName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdName)));
                System.out.println("Price of NonSponsored Prod with deal --> " + NonSponPrdPriceName + " ---------> " + priceNonSponPrdPriceWithDeal);
            } else if (driver.findElements(By.xpath(gridNonSponPrdPrice)).size()!=0) {
                System.out.println(i);
                String priceNonSponPrdPrice=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdPrice)));
                String NonSponPrdPriceName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdName)));
                System.out.println("Price of NonSponsored Prod --> " + NonSponPrdPriceName + " ---------> " + priceNonSponPrdPrice);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductBrand(String brand){
        int gridCount= driver.findElements(By.xpath(gridsElement)).size();
        System.out.println("Grid Count : " + gridCount);
        System.out.println("Brand to validate -------> "+ brand);
        for(int i=1;i<gridCount;i++){
            String gridSponPrdBrand1=gridsElement+ "["+ i +"]/div/div/div/div/div/div/div[2]/div[1]/div[2]/h5/span";
            String gridSponPrdBrand2=gridsElement+ "["+ i +"]/div/div/div/div/div/div/div[2]/div[2]/div[2]/h5/span";
            String gridNonSponPrdBrand=gridsElement+ "["+ i +"]/div/div/div/div/div/div[2]/div/h5/span";
            String gridNonSponPrdBrandwithDeal=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[1]/div[1]/h5/span";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2/a/span";
            String gridNonSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2/a/span";
            String gridNonSponPrdNameWithDeal=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[1]/h2/a/span";
            if(driver.findElements(By.xpath(gridSponPrdBrand1)).size()!=0){
                System.out.println(i);
                String SponPrdBrand1=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdBrand1)));
                String SponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Brand of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdBrand1);
            } else if (driver.findElements(By.xpath(gridSponPrdBrand2)).size()!=0) {
                System.out.println(i);
                String SponPrdBrand2=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdBrand2)));
                String SponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Brand of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdBrand2);
            }else if (driver.findElements(By.xpath(gridNonSponPrdBrand)).size()!=0) {
                System.out.println(i);
                String NonSponPrdBrand1=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdBrand)));
                String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdName)));
                System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBrand1);
            } else if (driver.findElements(By.xpath(gridNonSponPrdBrandwithDeal)).size()!=0) {
                System.out.println(i);
                String NonSponPrdBrand2=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdBrandwithDeal)));
                String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdNameWithDeal)));
                System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBrand2);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductDiscount(int discount){
        int gridCount= driver.findElements(By.xpath(gridsElement)).size();
        System.out.println("Grid Count : " + gridCount);
        System.out.println("Discount to validate -------> "+ discount);
        for(int i=1;i<gridCount;i++){
            String gridSponPrdDiscount1=gridsElement+ "["+ i +"]/div/div/div/div/div/div/div[2]/div[3]/div[2]/span[2]";
            String gridSponPrdDiscount2=gridsElement+ "["+ i +"]/div/div/div/div/div/div/div[2]/div[4]/div[2]/span[2]";
            String gridNonSponDiscountBrand=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[3]/div/span[2]";
            String gridNonSponPrdDiscountwithDeal1=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[3]/div[2]/span[2]";
            String gridNonSponPrdDiscountwithDeal2=gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[4]/div[2]/span[2]";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2/a/span";
            String gridNonSponPrdNameWithDeal1=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[1]/h2/a/span";
            String gridNonSponPrdNameWithDeal1Exc= gridsElement + "["+"]/div/div/div/div/div[2]/div[4]/div[2]/span[2]";
            String gridNonSponPrdNameWithDeal2=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2/a/span";

            if(driver.findElements(By.xpath(gridSponPrdDiscount1)).size()!=0){
                System.out.println(i);
                String SponPrdDiscount1=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdDiscount1)));
                String SponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Brand of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdDiscount1);
            } else if (driver.findElements(By.xpath(gridSponPrdDiscount2)).size()!=0) {
                System.out.println(i);
                String SponPrdDiscount2=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdDiscount2)));
                String SponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridSponPrdName)));
                System.out.println("Brand of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdDiscount2);
            }
//            else if (driver.findElements(By.xpath(gridNonSponDiscountBrand)).size()!=0) {
//                System.out.println(i);
//                String NonSponPrdBDiscount=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponDiscountBrand)));
//                try{
//                    String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdNameWithDeal1)));
//                    System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBDiscount);
//                }catch(Exception e){
//                    String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdNameWithDeal1Exc)));
//                    System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBDiscount);
//                }
//            }
            else if (driver.findElements(By.xpath(gridNonSponPrdDiscountwithDeal1)).size()!=0) {
                System.out.println(i);
                String NonSponPrdBDiscount1=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdDiscountwithDeal1)));
                String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdNameWithDeal1)));
                System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBDiscount1);
            }else if (driver.findElements(By.xpath(gridNonSponPrdDiscountwithDeal2)).size()!=0) {
                System.out.println(i);
                String NonSponPrdBDiscount2=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdDiscountwithDeal2)));
                String NonSponPrdName=actionsDriver.getTextString(driver.findElement(By.xpath(gridNonSponPrdNameWithDeal2)));
                System.out.println("Brand of Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdBDiscount2);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    String listElement="//div[@class='s-main-slot s-result-list s-search-results sg-row']/div";

    public void validateProductRatingList(int Rating){
        int listCount= driver.findElements(By.xpath(listElement)).size();
        while(listCount==0){
            actionsDriver.hardwait(1000);
        }
        System.out.println("List Count : " + listCount);
        System.out.println("Rating to validate -------> "+ Rating);
        for(int i=1;i<=listCount;i++){
            String listNonSponPrdRating1=listElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div[2]/div[1]/span[1]";
            String listNonSponPrdName1=gridsElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if(driver.findElements(By.xpath(listNonSponPrdRating1)).size()!=0){
                System.out.println(i);
                String NonSponPrdRating= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdRating1)));
                String NonSponPrdName= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdName1)));
                System.out.println("Rating of Non Sponsored Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdRating);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductBrandList(String brand){
        int listCount= driver.findElements(By.xpath(listElement)).size();
        System.out.println("List Count : " + listCount);
        System.out.println("Brand to validate -------> "+ brand);
        for(int i=1;i<=listCount;i++){
            String listNonSponPrdName1=gridsElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if(driver.findElements(By.xpath(listNonSponPrdName1)).size()!=0){
                System.out.println(i);
                String NonSponPrdName= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdName1)));
                Boolean brandPresent=NonSponPrdName.toLowerCase().contains(brand.toLowerCase());
                System.out.println("Brand of Non Sponsored Prod --> " + NonSponPrdName + " ---------> Correct Brand"+ brandPresent);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductPriceList(int MinPrice, int MaxPrice){
        int listCount= driver.findElements(By.xpath(listElement)).size();
        System.out.println("Grid Count : " + listCount);
        System.out.println("Prices to validate : " + MinPrice + " <--to--> " +MaxPrice);
        for(int i=1;i<=listCount;i++){
            String listNonSponPrdPrice1=listElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[1]/div[1]/a/span";
            String listNonSponPrdName1=gridsElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if(driver.findElements(By.xpath(listNonSponPrdPrice1)).size()!=0){
                System.out.println(i);
                String NonSponPrdPrice= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdPrice1)));
                String NonSponPrdName= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdName1)));
                System.out.println("Price of Non Sponsored Prod --> " + NonSponPrdName + " --------> " + NonSponPrdPrice);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }

    public void validateProductDiscountList(int discount){
        int listCount= driver.findElements(By.xpath(listElement)).size();
        System.out.println("List Count : " + listCount);
        System.out.println("Discount to validate -------> "+ discount);
        for(int i=1;i<=listCount;i++){
            String listNonSponPrdDiscount1=listElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div[3]/div/div/div[1]/div[1]/span[2]";
            String listNonSponPrdName1=gridsElement + "["+ i +"]/div/div/div/div/div/div[2]/div/div/div/h2/a/span";
            if(driver.findElements(By.xpath(listNonSponPrdDiscount1)).size()!=0){
                System.out.println(i);
                String NonSponPrdDiscount= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdDiscount1)));
                String NonSponPrdName= actionsDriver.getTextString(driver.findElement(By.xpath(listNonSponPrdName1)));
                System.out.println("Discount of Non Sponsored Prod --> " + NonSponPrdName + " --------> " + NonSponPrdDiscount);
            } else {
                System.out.println("No Product validated for iteration : "+ i);
            }
        }
    }


    public int getValueWithoutComma(String text){
        String textFrmt=text.replaceAll(",","");
        return Integer.parseInt(textFrmt);
    }
}
