package org.amazon.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.CacheRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserConfig {
    public static WebDriver driver;
    public BrowserConfig(){
        BrowserConfig.driver=getDriver();
    }
    String huburl="http://localhost:4444";
    public WebDriver getDriver(){
        if(driver==null){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(PropsReader.driver.equals("local-chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }else if(PropsReader.driver.equals("grid")){
            capabilities.setBrowserName("chrome");
            try{
                driver = new RemoteWebDriver(new URL(huburl), capabilities);
                System.out.println("RemoteWebDriver");
                driver.manage().window().maximize();
                System.out.println("RemoteWebDriver-maximize");
            }catch (MalformedURLException e){
                System.out.println("Invalid Grid URL");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return driver;
        }
        return driver;
    }
    public static void closeDriver(){
        driver.quit();
    }

}
