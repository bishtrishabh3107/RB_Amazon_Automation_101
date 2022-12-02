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
        String discountEle="//span[text()[contains(.,'"+Discount+"')]]";
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
        List<WebElement> filterExpandIcons=driver.findElements(By.className("a-expander-prompt"));
        for(int k=0;k<filterExpandIcons.size();k++){
            actionsDriver.JSClick(filterExpandIcons.get(k));
        }
        System.out.println("All Expand Filer icons Clicked");
    }

    String gridsElement="(//div[@class='sg-col-20-of-24 s-matching-dir sg-col-16-of-20 sg-col sg-col-8-of-12 sg-col-12-of-16']/div/span[1]/div/div)";

//    String gridSponPrdName=gridsElement + "["+ "i" +"]/div/div/div/div/div/div/div/div/h2";
//
//    String gridSponPrdBrand=gridsElement + "["+ "i" +"]/div/div/div/div/div/div/div/div[2]/div[2]/h5/span";
//    String gridSponPrdDiscount=gridsElement+ "["+ "i" +"]/div/div/div/div/div/div/div/div[4]/div[2]/span[2]";
//
//    String gridNonSponPrdName=gridsElement + "["+ "i" +"]/div/div/div/div/div[2]/div[2]/h2";
//
//    String gridNonSponPrdBrand=gridsElement + "["+ "i" +"]/div/div/div/div/div[2]/div[2]/div[1]/h5/span";
//    String gridNonSponPrdDiscount=gridsElement+ "["+ "i" +"]/div/div/div/div/div[2]/div[4]/div/span[2]";

    public void validateProductRating(int Rating){
        System.out.println(Rating);
        List<WebElement> AllElements=driver.findElements(By.xpath(gridsElement));
        for(int i=0;i<AllElements.size();i++){
            String gridSponPrdRating=gridsElement + "["+ "i" +"]/div/div/div/div/div/div/div/div[3]/div/span[1]/span/a/i/span";
            String gridNonSponPrdRating=gridsElement + "["+ "i" +"]/div/div/div/div/div[2]/div[3]/div/span[1]/span/a/i/span";
            String gridNonSponPrdRating2=gridsElement + "["+ "i" +"]/div/div/div/div/div[2]/div[2]/div/span[1]/span/a/i/span";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2/a/span";
            String gridNonSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2/a/span";
            if(driver.findElements(By.xpath(gridSponPrdRating)).size()!=0){
                String SponPrdRating=driver.findElement(By.xpath(gridSponPrdRating)).getText();
                String SponPrdName= driver.findElement(By.xpath(gridSponPrdName)).getText();
                System.out.println("Rating of Sponsored Prod --> " + SponPrdName + " ---------> " + SponPrdRating);
            } else if (driver.findElements(By.xpath(gridNonSponPrdRating)).size()!=0) {
                String NonSponPrdRating=driver.findElement(By.xpath(gridNonSponPrdRating)).getText();
                String NonSponPrdName=driver.findElement(By.xpath(gridNonSponPrdName)).getText();
                System.out.println("Rating of NonSponsored1 Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdRating);
            } else if (driver.findElements(By.xpath(gridNonSponPrdRating2)).size()!=0) {
                String NonSponPrdRating=driver.findElement(By.xpath(gridNonSponPrdRating2)).getText();
                String NonSponPrdName=driver.findElement(By.xpath(gridNonSponPrdName)).getText();
                System.out.println("Rating of NonSponsored2 Prod --> " + NonSponPrdName + " ---------> " + NonSponPrdRating);
            }
        }
    }

    public void validateProductPrice(int MinPrice, int MaxPrice){
        System.out.println(MinPrice + " <--to--> " +MaxPrice);
        List<WebElement> AllElements=driver.findElements(By.xpath(gridsElement));
        System.out.println();
        for(int i=0;i<AllElements.size();i++){
            String gridSponPrdPrice="("+gridsElement+ "["+ i +"]/div/div/div/div/div/div/div/div[4]/div[2]/a/span/span[1])[1]";
            String gridNonSponPrdPricewithDeal="("+gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[4]/div[2]/a/span[1]/span)[1]";
            String gridNonSponPrdPrice="("+gridsElement+ "["+ i +"]/div/div/div/div/div[2]/div[4]/div/a/span/span)[1]";
            String gridSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div/div/div/div/h2";
            String gridNonSponPrdName=gridsElement + "["+ i +"]/div/div/div/div/div[2]/div[2]/h2";
            if(driver.findElements(By.xpath(gridSponPrdPrice)).size()!=0){
                String priceSponPrdPrice=driver.findElement(By.xpath(gridSponPrdPrice)).getText();
                String SponPrdPriceName= driver.findElement(By.xpath(gridSponPrdName)).getText();
                System.out.println("Price of Sponsored Prod --> " + SponPrdPriceName + " ---------> " + priceSponPrdPrice);
            } else if (driver.findElements(By.xpath(gridNonSponPrdPricewithDeal)).size()!=0) {
                String priceNonSponPrdPriceWithDeal=driver.findElement(By.xpath(gridNonSponPrdPricewithDeal)).getText();
                String NonSponPrdPriceName=driver.findElement(By.xpath(gridNonSponPrdName)).getText();
                System.out.println("Price of NonSponsored Prod with deal --> " + NonSponPrdPriceName + " ---------> " + priceNonSponPrdPriceWithDeal);
            } else if (driver.findElements(By.xpath(gridNonSponPrdPrice)).size()!=0) {
                String priceNonSponPrdPrice=driver.findElement(By.xpath(gridNonSponPrdPrice)).getText();
                String NonSponPrdPriceName=driver.findElement(By.xpath(gridNonSponPrdName)).getText();
                System.out.println("Price of NonSponsored Prod --> " + NonSponPrdPriceName + " ---------> " + priceNonSponPrdPrice);
            }
        }
    }


    public int getValueWithoutComma(String text){
        String textFrmt=text.replaceAll(",","");
        return Integer.parseInt(textFrmt);
    }
}
