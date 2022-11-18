package org.amazon.steps;

import io.cucumber.datatable.DataTable;
import org.amazon.pages.LoginPage;
import org.amazon.utils.BasePage;
import org.amazon.utils.BrowserConfig;
import org.amazon.utils.PropsReader;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class LoginSteps extends BasePage {

    LoginPage lp=new LoginPage(driver);

    public void launchURLandClickSignIn(String loginType){
        if(loginType.equals("prop file")){
            System.out.println("Login url is :" + PropsReader.mainUrl);
            driver.get(PropsReader.mainUrl);
            lp.clickBannerSignIn();
        }else{
            System.out.println("Login url is :" + loginType);
            driver.get(loginType);
            lp.clickBannerSignIn();
        }
    }

    public void enterUserNamePassword(){
        lp.setUserName(PropsReader.username);
        lp.cickContinue();
        lp.setPassword(PropsReader.password);
    }

    public void enterUserNamePasswordFromDataTable(DataTable dataTable){
        List<Map<String,String>> list=dataTable.asMaps(String.class,String.class);
        String username=list.get(0).get("UserName");
        String password=list.get(0).get("Password");
        System.out.println(username);
        System.out.println(password);
        lp.setUserName(username);
        lp.cickContinue();
        lp.setPassword(password);
    }

    public void enterUserNamePasswordFromExamples(String username, String password){
        lp.setUserName(username);
        lp.cickContinue();
        lp.setPassword(password);
    }

    public void loginAndValidate(){
        lp.clickLogin();
        Assert.assertTrue(lp.validateWelcome());
    }

    public void logoutAndVerify(){
        lp.clickAmazonNavLink();
        lp.cickHeaderSignIn();
        lp.clickSignOutLink();
        Assert.assertTrue(driver.getTitle().equals("Amazon Sign-In"));
    }

}
