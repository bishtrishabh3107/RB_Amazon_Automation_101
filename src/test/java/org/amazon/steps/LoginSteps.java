package org.amazon.steps;

import io.cucumber.datatable.DataTable;
import org.amazon.pages.AmazonVideosLoginPage;
import org.amazon.pages.LoginPage;
import org.amazon.utils.BasePage;
import org.amazon.utils.PropsReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;


public class LoginSteps extends BasePage {
    LoginPage lp = new LoginPage(driver);
    AmazonVideosLoginPage alp=new AmazonVideosLoginPage(driver);

    public void launchURLandClickSignIn(String appName, String loginType) {
        if (loginType.equals("prop file")) {
            switch (appName.toLowerCase()){
                case ("amazon shopping"):
                    System.out.println("Login url is :" + PropsReader.amazonshoppingUrl);
                    driver.get(PropsReader.amazonshoppingUrl);
                    lp.clickBannerSignIn();
                    break;
                case ("amazon videos"):
                    System.out.println("Login url is :" + PropsReader.amazonvideosUrl);
                    driver.get(PropsReader.amazonvideosUrl);
                    alp.clickAccountDropdown();
                    alp.clickSignInButton();
                    break;
                case ("amazon shopping without signin"):
                    System.out.println("Login url is :" + PropsReader.amazonshoppingUrl);
                    driver.get(PropsReader.amazonshoppingUrl);
                    break;
                default:
                    System.out.println("Invalid Url");
            }
        } else {
            System.out.println("Login url is :" + loginType);
            driver.get(loginType);
            lp.clickBannerSignIn();
        }
    }

    public void enterUserNamePassword(String appName) {
        if(appName.equals("Amazon Shopping")){
            lp.setUserName(PropsReader.username);
            lp.cickContinue();
            lp.setPassword(PropsReader.password);
        } else if (appName.equals("Amazon Videos")) {
            lp.setUserName(PropsReader.username);
            lp.setPassword(PropsReader.password);
        }

    }

    public void enterUserNamePasswordFromDataTable(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        String username = list.get(0).get("UserName");
        String password = list.get(0).get("Password");
        System.out.println(username);
        System.out.println(password);
        lp.setUserName(username);
        lp.cickContinue();
        lp.setPassword(password);
    }

    public void enterUserNamePasswordFromExamples(String username, String password) {
        lp.setUserName(username);
        lp.cickContinue();
        lp.setPassword(password);
    }

    public void loginAndValidate(String appName) {
        lp.clickLogin();
        switch (appName.toLowerCase()){
            case ("amazon shopping"):
                Assert.assertTrue(lp.validateWelcome(appName));
                break;
            case ("amazon videos"):
                Assert.assertTrue(lp.validateWelcome(appName));
                break;
            default:
                System.out.println("Invalid Application");
        }

    }

    public void logoutAndVerify(String appName) {
        switch (appName.toLowerCase()){
            case ("amazon shopping"):
                lp.clickAmazonNavLink();
                lp.cickHeaderSignIn();
                lp.clickSignOutLink();
                Assert.assertTrue(driver.getTitle().equals("Amazon Sign-In"));
                break;
            case ("amazon videos"):
                alp.clickSignOutButton();
                WebElement ele=driver.findElement(By.xpath("//div[@class='pv-nav-sign-up-button']/a"));
                Assert.assertEquals("Try for free", ele.getText());
                break;
            default:
                System.out.println("Invalid Application");
        }
    }

}
