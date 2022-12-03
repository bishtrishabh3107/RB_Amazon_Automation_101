package org.amazon.steps;

import org.amazon.pages.AddToCartPage;
import org.amazon.utils.BasePage;
import org.amazon.utils.SetAndGetData;

import java.util.List;

public class CartSteps extends BasePage {

    AddToCartPage addToCartPage=new AddToCartPage(driver);
    String ProductName;
    String ProductPrice;
    SetAndGetData setAndGetData=new SetAndGetData();

    public void addMaximumBoughtProductToCart(){
        addToCartPage.getMaxBoughtProduct();
        ProductName= setAndGetData.getProductName();
        ProductPrice= setAndGetData.getProductPrice();
        System.out.println(ProductName +" --------> "+ ProductPrice);
    }

    public void validateCart(){
        addToCartPage.validateCart(ProductName,ProductPrice);
    }

}
