package org.amazon.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserConfig {

    static WebDriver driver;

//    public BrowserConfig(){
//        BrowserConfig.driver=getDriver();
//    }

    String huburl="sjahsj";

    public WebDriver getDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(PropsReader.driver.equals("local-chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }else if(PropsReader.driver.equals("grid")){
            capabilities.setCapability("browserName","chrome");
            capabilities.setCapability("platform","LINUX");
            capabilities.setCapability("platformName","LINUX");
            try{
                driver = new RemoteWebDriver(new URL(huburl), capabilities);
                driver.manage().window().maximize();
            }catch (MalformedURLException e){
                System.out.println("Invalid Grid URL");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return driver;
    }
    public static void closeDriver(){
        driver.quit();
    }

}
