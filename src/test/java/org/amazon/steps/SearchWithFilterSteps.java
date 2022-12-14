package org.amazon.steps;

import io.cucumber.datatable.DataTable;
import org.amazon.actionsdriver.ActionsDriver;
import org.amazon.pages.SearchResultsPage;
import org.amazon.utils.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchWithFilterSteps extends BasePage {

    SearchResultsPage srp=new SearchResultsPage(driver);
    ActionsDriver actionsDriver=new ActionsDriver(driver);
    public void enterFilters(DataTable dataTable){
        List<Map<String,String>> list=dataTable.asMaps(String.class, String.class);
        String customerReview=list.get(0).get("CustomerReviews");
        String MinPrice=list.get(0).get("MinPrice");
        String MaxPrice=list.get(0).get("MaxPrice");
        String Discount=list.get(0).get("Discount");
        String brands=list.get(0).get("Brands");
        List<String> brandsList=getCommaSeperatedValues(brands);

        srp.clickCustomerReviewFilter(customerReview);
        srp.enterTxtFieldMinPrice(MinPrice);
        srp.enterTxtFieldMaxPrice(MaxPrice);
        srp.pressPriceGoBtn();
        srp.clickDiscountFilter(Discount);
        srp.clickBrandFilter(brandsList);
    }

    public void validateFilterResults(String displayFormat,DataTable dataTable){
        List<Map<String,String>> list=dataTable.asMaps(String.class, String.class);
        String customerReview=list.get(0).get("CustomerReviews");
        String MinPrice=list.get(0).get("MinPrice");
        String MaxPrice=list.get(0).get("MaxPrice");
        String discount=list.get(0).get("Discount");
        String brands=list.get(0).get("Brands");

        if(displayFormat.equals("grid")){
            srp.validateProductRating(Integer.parseInt(customerReview));
            srp.validateProductPrice(Integer.parseInt(MinPrice),Integer.parseInt(MaxPrice));
            srp.validateProductBrand(brands);
            srp.validateProductDiscount(Integer.parseInt(discount));
        } else if (displayFormat.equals("list")) {
            srp.validateProductRatingList(Integer.parseInt(customerReview));
            srp.validateProductPriceList(Integer.parseInt(MinPrice),Integer.parseInt(MaxPrice));
            srp.validateProductBrandList(brands);
            srp.validateProductDiscountList(Integer.parseInt(discount));
        }
    }

    public List<String> getCommaSeperatedValues(String brands){
        brands=brands.toLowerCase();
        System.out.println(brands);
        List<String> strList=new ArrayList<String>(5);
            String str="";
            for(int i=0;i<brands.length();i++){
                if(brands.charAt(i)==','){
                    strList.add(str);
                    str="";
                }else {
                    str += brands.charAt(i);
                }
            }
            if(str!=""){
               strList.add(str);
            }
            System.out.println(strList.toString());
        return strList;
    }

}
