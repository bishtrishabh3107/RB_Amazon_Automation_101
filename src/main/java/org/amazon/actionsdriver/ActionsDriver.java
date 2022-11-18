package org.amazon.actionsdriver;

import org.amazon.utils.PropsReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ActionsDriver {


    int timeout= PropsReader.timeoutInSeconds;

    // Wait operations
    private void waitUntilCondition(WebDriver driver, ExpectedCondition condition, String timeoutMessage, int timeout){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    public void forLoading(WebDriver driver){
        ExpectedCondition<Object> condition= ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        String timeoutMessage="Page didn't load after " + Integer.toString(timeout) + " seconds. ";
        waitUntilCondition(driver,condition,timeoutMessage,timeout);
    }

    public void forElementToBeDisplayed(WebDriver driver, WebElement webElement){
        ExpectedCondition<WebElement> condition= ExpectedConditions.visibilityOf(webElement);
        String timeoutMessage=webElement+ " wasn't displayed after" + Integer.toString(timeout) + " seconds. ";
        waitUntilCondition(driver,condition,timeoutMessage,timeout);
    }

    public void forPresenceToBeDisplayed(WebDriver driver, By elementLocator){
        ExpectedCondition<List<WebElement>> condition= ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator);
        String timeoutMessage=elementLocator+ " elements were not displayed after" + Integer.toString(timeout) + " seconds. ";
        waitUntilCondition(driver,condition,timeoutMessage,timeout);
    }

    public void forElementToBeClickable(WebDriver driver, WebElement webElement){
        ExpectedCondition<WebElement> condition= ExpectedConditions.elementToBeClickable(webElement);
        String timeoutMessage=webElement+ " wasn't displayed after" + Integer.toString(timeout) + " seconds. ";
        waitUntilCondition(driver,condition,timeoutMessage,timeout);
    }

    //highlight and scroll operations
    public void highlightElement(WebDriver driver, WebElement ele){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:3px solid green; background:LightCoral')", ele);
    }

    public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele);
    }

    public void scrollHighlight(WebDriver driver, WebElement ele){
        scrollByVisibilityOfElement(driver,ele);
        highlightElement(driver,ele);
    }

    public void scrollHighlightWaitToBeDisplayed(WebDriver driver, WebElement ele){
        forElementToBeDisplayed(driver,ele);
        highlightElement(driver,ele);
        scrollByVisibilityOfElement(driver,ele);
    }

    public void scrollHighlightWaitToBeClickable(WebDriver driver, WebElement ele){
        forElementToBeClickable(driver,ele);
        highlightElement(driver,ele);
        scrollByVisibilityOfElement(driver,ele);
    }

    //click operations
    public void driverClick(WebDriver driver, WebElement ele) {
        boolean flag = findElement(driver,ele);
        try {
            flag = ele.isDisplayed();
            ele.click();
            flag = true;
        } catch (Exception e) {
            System.out.println("Location Not found");
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Successfully entered value");
            } else {
                System.out.println("Unable to enter value");
            }

        }
    }

    public void JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            scrollHighlight(driver,ele);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
            // driver.executeAsyncScript("arguments[0].click();", element);
            flag = true;
        }
        catch (Exception e) {
            throw e;
        } finally {
            if (flag) {
                System.out.println("Click Action is performed on :"+ele);
            } else if (!flag) {
                System.out.println("Click Action is not performed on :"+ele);
            }
        }
    }

    public void moveToElementClick(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        scrollHighlightWaitToBeDisplayed(driver,ele);
        act.moveToElement(ele).click().build().perform();
        System.out.println("Clicked on "+ ele);
    }


    public void typeText(WebDriver driver, WebElement ele, String text) {
        boolean flag = findElement(driver,ele);
        try {
            flag = ele.isDisplayed();
            ele.clear();
            System.out.println("Entered text: "+text);
            ele.sendKeys(text);

            // logger.info("Entered text :"+text);
            flag = true;
        } catch (Exception e) {
            System.out.println("Location Not found");
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Successfully entered value");
            } else {
                System.out.println("Unable to enter value");
            }

        }
    }

    public boolean validateText(WebDriver driver, WebElement ele, String text){
        boolean status=findElement(driver,ele);
        if(status && ele.getText().equals(text)){
            status=true;
        }else{
            status=false;
        }
        return status;
    }

    public boolean findElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            scrollHighlightWaitToBeDisplayed(driver,ele);
            flag = true;
        } catch (Exception e) {
            // System.out.println("Location not found: "+locatorName);
            flag = false;
        } finally {
            if (flag) {
                System.out.println("Successfully Found element" + ele);
            } else {
                System.out.println("Unable to locate element" + ele);
            }
        }
        return flag;
    }

    public boolean isDisplayed(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isDisplayed();
            highlightElement(driver,ele);
            if (flag) {
                System.out.println("Element "+ele+" is Displayed");
            } else {
                System.out.println("Element "+ele+" is not Displayed");
            }
        } else {
            System.out.println("Not displayed ");
        }
        return flag;
    }

    public boolean isSelected(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isSelected();
            highlightElement(driver,ele);
            if (flag) {
                System.out.println("Element "+ele+" is Selected");
            } else {
                System.out.println("Element "+ele+" is not Selected");
            }
        } else {
            System.out.println("Not selected");
        }
        return flag;
    }

    public boolean isEnabled(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isEnabled();
            highlightElement(driver,ele);
            if (flag) {
                System.out.println("Element "+ele+" is Enabled");
            } else {
                System.out.println("Element "+ele+" is not Enabled");
            }
        } else {
            System.out.println("Not Enabled ");
        }
        return flag;
    }

//    /**
//     * Type text at location
//     *
//     * @param locatorName
//     * @param text
//     * @return - true/false
//     */



    public boolean selectBySendkeys(String value,WebElement ele) {
        boolean flag = false;
        try {
            ele.clear();
            ele.sendKeys(value);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Selected value from the DropDown");
            } else {
                System.out.println("Not Selected value from the DropDown");
                // throw new ElementNotFoundException("", "", "")
            }
        }
    }

//    /**
//     * select value from DropDown by using selectByIndex
//     *
//     * @param locator     : Action to be performed on element (Get it from Object
//     *                    repository)
//     *
//     * @param index       : Index of value wish to select from dropdown list.
//     *
//     * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
//     *                    Listbox etc..)
//     *
//     */

    public boolean selectByIndex(WebElement element, int index) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByIndex(index);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by Index");
            } else {
                System.out.println("Option not selected by Index");
            }
        }
    }

//    /**
//     * select value from DD by using value
//     *
//     * @param locator     : Action to be performed on element (Get it from Object
//     *                    repository)
//     *
//     * @param value       : Value wish to select from dropdown list.
//     *
//     * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
//     *                    Listbox etc..)
//     */

    public boolean selectByValue(WebElement element,String value) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByValue(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by Value");
            } else {
                System.out.println("Option not selected by Value");
            }
        }
    }

//    /**
//     * select value from DropDown by using selectByVisibleText
//     *
//     * @param locator     : Action to be performed on element (Get it from Object
//     *                    repository)
//     *
//     * @param visibletext : VisibleText wish to select from dropdown list.
//     *
//     * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
//     *                    Listbox etc..)
//     */

    public boolean selectByVisibleText(String visibletext, WebElement ele) {
        boolean flag = false;
        try {
            Select s = new Select(ele);
            s.selectByVisibleText(visibletext);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Option selected by VisibleText");
            } else {
                System.out.println("Option not selected by VisibleText");
            }
        }
    }

//    public boolean mouseHoverByJavaScript(WebElement ele) {
//        boolean flag = false;
//        try {
//            WebElement mo = ele;
//            String javaScript = "var evObj = document.createEvent('MouseEvents');"
//                    + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
//                    + "arguments[0].dispatchEvent(evObj);";
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript(javaScript, mo);
//            flag = true;
//            return true;
//        }
//
//        catch (Exception e) {
//
//            return false;
//        } finally {
//            if (flag) {
//                System.out.println("MouseOver Action is performed");
//            } else {
//                System.out.println("MouseOver Action is not performed");
//            }
//        }
//    }



    public boolean switchToFrameByIndex(WebDriver driver,int index) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
            driver.switchTo().frame(index);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with index \"" + index + "\" is selected");
            } else {
                System.out.println("Frame with index \"" + index + "\" is not selected");
            }
        }
    }

    /**
     * This method switch the to frame using frame ID.
     *
     * @param idValue : Frame ID wish to switch
     *
     */

    public boolean switchToFrameById(WebDriver driver,String idValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(idValue);
            flag = true;
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with Id \"" + idValue + "\" is selected");
            } else {
                System.out.println("Frame with Id \"" + idValue + "\" is not selected");
            }
        }
    }

    /**
     * This method switch the to frame using frame Name.
     *
     * @param nameValue : Frame Name wish to switch
     *
     */

    public boolean switchToFrameByName(WebDriver driver,String nameValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(nameValue);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Frame with Name \"" + nameValue + "\" is selected");
            } else if (!flag) {
                System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
            }
        }
    }

    public boolean switchToDefaultFrame(WebDriver driver) {
        boolean flag = false;
        try {
            driver.switchTo().defaultContent();
            flag = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                // SuccessReport("SelectFrame ","Frame with Name is selected");
            } else if (!flag) {
                // failureReport("SelectFrame ","The Frame is not selected");
            }
        }
    }

    public void mouseOverElement(WebDriver driver,WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                System.out.println(" MouserOver Action is performed on ");
            } else {
                System.out.println("MouseOver Action is not performed on");
            }
        }
    }

    public boolean moveToElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            Actions actions = new Actions(driver);
            // actions.moveToElement(driver.findElement(locator)).build().perform();
            actions.moveToElement(ele).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean mouseover(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            /*
             * if (flag) {
             * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
             * +"\""); } else {
             * failureReport("MouseOver","MouseOver action is not performed on \""
             * +locatorName+"\""); }
             */
        }
    }

    public boolean draggable(WebDriver driver,WebElement source, int x, int y) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDropBy(source, x, y).build().perform();
            Thread.sleep(5000);
            flag = true;
            return true;

        } catch (Exception e) {

            return false;

        } finally {
            if (flag) {
                System.out.println("Draggable Action is performed on \""+source+"\"");
            } else if(!flag) {
                System.out.println("Draggable action is not performed on \""+source+"\"");
            }
        }
    }

    public boolean draganddrop(WebDriver driver,WebElement source, WebElement target) {
        boolean flag = false;
        try {
            new Actions(driver).dragAndDrop(source, target).perform();
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("DragAndDrop Action is performed");
            } else if(!flag) {
                System.out.println("DragAndDrop Action is not performed");
            }
        }
    }

    public boolean slider(WebDriver driver,WebElement ele, int x, int y) {
        boolean flag = false;
        try {
            // new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
            // .perform();
            new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
            Thread.sleep(5000);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("Slider Action is performed");
            } else {
                System.out.println("Slider Action is not performed");
            }
        }
    }

    public boolean rightclick(WebDriver driver,WebElement ele) {
        boolean flag = false;
        try {
            Actions clicker = new Actions(driver);
            clicker.contextClick(ele).perform();
            flag = true;
            return true;
            // driver.findElement(by1).sendKeys(Keys.DOWN);
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                System.out.println("RightClick Action is performed");
            } else {
                System.out.println("RightClick Action is not performed");
            }
        }
    }

    public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count) {
        boolean flag = false;
        try {
            Set<String> windowList = driver.getWindowHandles();

            String[] array = windowList.toArray(new String[0]);

            driver.switchTo().window(array[count-1]);

            if (driver.getTitle().contains(windowTitle)){
                flag = true;
            }else{
                flag = false;
            }
            return flag;
        } catch (Exception e) {
            //flag = true;
            return false;
        } finally {
            if (flag) {
                System.out.println("Navigated to the window with title");
            } else {
                System.out.println("The Window with title is not Selected");
            }
        }
    }

    public boolean switchToNewWindow(WebDriver driver) {
        boolean flag = false;
        try {

            Set<String> s=driver.getWindowHandles();
            Object popup[]=s.toArray();
            driver.switchTo().window(popup[1].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                System.out.println("Window is Navigated with title");
            } else {
                System.out.println("The Window with title: is not Selected");
            }
        }
    }

    public boolean switchToOldWindow(WebDriver driver) {
        boolean flag = false;
        try {

            Set<String> s=driver.getWindowHandles();
            Object popup[]=s.toArray();
            driver.switchTo().window(popup[0].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            flag = false;
            return flag;
        } finally {
            if (flag) {
                System.out.println("Focus navigated to the window with title");
            } else {
                System.out.println("The Window with title: is not Selected");
            }
        }
    }

    public int getColumncount(WebElement row) {
        List<WebElement> columns = row.findElements(By.tagName("td"));
        int a = columns.size();
        System.out.println(columns.size());
        for (WebElement column : columns) {
            System.out.print(column.getText());
            System.out.print("|");
        }
        return a;
    }

    public int getRowCount(WebElement table) {
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int a = rows.size() - 1;
        return a;
    }


    /**
     * Verify alert present or not
     *
     * @return: Boolean (True: If alert preset, False: If no alert)
     *
     */

    public boolean Alert(WebDriver driver) {
        boolean presentFlag = false;
        Alert alert = null;

        try {
            // Check the presence of alert
            alert = driver.switchTo().alert();
            // if present consume the alert
            alert.accept();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            // Alert present; set the flag

            // Alert not present
            ex.printStackTrace();
        } finally {
            if (!presentFlag) {
                System.out.println("The Alert is handled successfully");
            } else{
                System.out.println("There was no alert to handle");
            }
        }

        return presentFlag;
    }

    public boolean launchUrl(WebDriver driver,String url) {
        boolean flag = false;
        try {
            driver.navigate().to(url);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Successfully launched \""+url+"\"");
            } else {
                System.out.println("Failed to launch \""+url+"\"");
            }
        }
    }

    public boolean isAlertPresent(WebDriver driver)
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }

    public String getTitle(WebDriver driver) {
        boolean flag = false;

        String text = driver.getTitle();
        if (flag) {
            System.out.println("Title of the page is: \""+text+"\"");
        }
        return text;
    }

    public String getCurrentURL(WebDriver driver)  {
        boolean flag = false;
        String text = driver.getCurrentUrl();
        if (flag) {
            System.out.println("Current URL is: \""+text+"\"");
        }
        return text;
    }

    public boolean click(WebElement locator, String locatorName) {
        boolean flag = false;
        try {
            locator.click();
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                System.out.println("Able to click on \""+locatorName+"\"");
            } else {
                System.out.println("Click Unable to click on \""+locatorName+"\"");
            }
        }

    }

//    public void fluentWait(WebDriver driver,WebElement element, int timeOut) {
//        Wait<WebDriver> wait = null;
//        try {
//            wait = new FluentWait<WebDriver>((WebDriver) driver)
//                    .withTimeout(Duration.ofSeconds(20))
//                    .pollingEvery(Duration.ofSeconds(2))
//                    .ignoring(Exception.class);
//            wait.until(ExpectedConditions.visibilityOf(element));
//            element.click();
//        }catch(Exception e) {
//        }
//    }

    public void implicitWait(WebDriver driver, int timeOut) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String screenShot(WebDriver driver, String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
        String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
                + dateName + ".png";
        return newImageString;
    }

    public String getCurrentTime() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
        return currentDate;
    }
}
