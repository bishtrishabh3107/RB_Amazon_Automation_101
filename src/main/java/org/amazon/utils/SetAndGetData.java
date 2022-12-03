package org.amazon.utils;

public class SetAndGetData {

    private String productName;
    private String productRating;
    private String productPrice;
    private String productDiscount;
    private String productBoughtNum;


    public void setProductName(String productName){
        this.productName=productName;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductRating(String productRating){
        this.productRating=productRating;
    }

    public String getProductRating(){
        return productRating;
    }

    public void setProductPrice(String productPrice){
        this.productPrice=productPrice;
    }

    public String getProductPrice(){
        return productPrice;
    }

    public void setProductDiscount(String productDiscount){
        this.productDiscount=productDiscount;
    }

    public String getProductDiscount(){
        return productDiscount;
    }

    public void setProductBoughtNum(String productBoughtNum){
        this.productBoughtNum=productBoughtNum;
    }

    public String getProductBoughtNum(){
        return productBoughtNum;
    }

}
