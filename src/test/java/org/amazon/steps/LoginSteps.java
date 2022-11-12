package org.amazon.steps;

import org.amazon.pages.LoginPage;
import org.amazon.utils.BasePage;
import org.amazon.utils.PropsReader;
import org.junit.Assert;


public class LoginSteps extends BasePage {

    LoginPage lp=new LoginPage(driver);

    public void launchURLandClickSignIn(){
        System.out.println("Login url is :" + PropsReader.mainUrl);
        driver.get(PropsReader.mainUrl);
        lp.clickBannerSignIn();
    }

    public void enterUserNamePassword(){
        lp.setUserName(PropsReader.username);
        lp.cickContinue();
        lp.setPassword(PropsReader.password);
    }

    public void loginAndValidate(){
        lp.clickLogin();
        Assert.assertTrue(lp.validateWelcome());
        driver.quit();
    }

}
