package org.amazon.utils;

public class SetAndGetData {

    private String productName;
    private String productRating;
    private String productPrice;
    private String productDiscount;
    private String productBoughtNum;


    public void setProductName(String name){
        this.productName=name;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductRating(String rating){
        this.productRating=rating;
    }

    public String getProductRating(){
        return productRating;
    }

    public void setProductPrice(String price){
        this.productPrice=price;
    }

    public String getProductPrice(){
        return productPrice;
    }

    public void setProductDiscount(String discount){
        this.productDiscount=discount;
    }

    public String getProductDiscount(){
        return productDiscount;
    }

    public void setProductBoughtNum(String boughtNum){
        this.productBoughtNum=boughtNum;
    }

    public String getProductBoughtNum(){
        return productBoughtNum;
    }

}
