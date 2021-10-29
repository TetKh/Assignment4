package AutomationProgect;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Assignment4 {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tetya\\OneDrive\\Documents\\SELENIUM\\DRIVERS\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
//        1. Navigate to carmax.com
        driver.get("https://www.carmax.com/");

        driver.manage().deleteAllCookies();

//        2. On the bottom of the page in the appraisal form, choose VIN and fill out the form with
//        the below info and click get started:
//        VIN: 4T1BE46K67U162207
//        Zipcode:22182

        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1760)");
        driver.findElement(By.id("button-VIN")).click();
        driver.findElement(By.id("ico-form-vin")).sendKeys("4T1BE46K67U162207");
        driver.findElement(By.id("ico-form-zipcode")).sendKeys("22182");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='submit-button kmx-button--primary kmx-button']")).click();

        //3. On the next page select LE 4D Sedan 2.4L
        // Enter: 4WD/AWD
        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@class='closeButton dialog-close-button kmx-button--tertiary kmx-button']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='submit-button kmx-button--primary kmx-button']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//button[@class='closeButton dialog-close-button kmx-button--tertiary kmx-button']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='submit-button kmx-button--primary kmx-button']")).click();
        Thread.sleep(2000);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[.='LE 4D Sedan 2.4L'] ")));

//        WebElement radioButton = driver.findElement(By.xpath("//input[@class='mdc-radio__native-control'][@value='mnHlNWA']"));
//        radioButton.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("//input[@class='mdc-radio__native-control'][@value='mnHlNWA']"))).click();

        Thread.sleep(5000);
        new Select(driver.findElement(By.name("drive"))).selectByValue("4WD/AWD");
        Thread.sleep(5000);

        new Select(driver.findElement(By.name("transmission"))).selectByValue("Automatic");

//
        //4. For Features, select all options.
        List<WebElement> checkboxes = driver.findElements(By.xpath("//label[starts-with(@for, 'checkbox-ico-cb')]"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                js.executeScript("arguments[0].click();", checkbox);
            }

        }
   //     5. Enter the following mileage and the choose the following options:
       // 60.000
        driver.findElement(By.id("ico-step-Mileage_and_Condition-btn")).click();
        Thread.sleep(2000);
        driver.findElement((By.name("currentMileage"))).sendKeys("60000");
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-100-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-910-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-920-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-200-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-1000-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-300-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-p-830-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-p-320-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-410-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-420-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-500-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-600-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-700-1")));
        js.executeScript("arguments[0].click();", driver.findElement(By.id("radio-ico-r-800-1")));


        List<String[] > list= My_Methods.readFromCSV("MOCK_DATA.csv");

        String[] randomData = null;

        for (int i = 1; i < list.size(); i++){
            int randomIndex = (int)(Math.random()* list.size()-1);
            randomData = list.get(randomIndex);
        }
  //String email= generateRandomEmail();
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[@class='mdc-floating-label']")));
        driver.findElement(By.xpath("//input[@name='preferredEmail']")).sendKeys(randomData[2]);
        Thread.sleep(2000);
       // 7.Click continue
        js.executeScript("arguments[0].click();", driver.findElement(By.id("ico-continue-button")));
        Thread.sleep(5000);
//      8. On the next page, verify that the appraisal amount is 6600.
        String actualOffer = driver.findElement(By.xpath("//div[@class='kmx-ico-offer-offerinfo Offer-module__offerInfo--26dFt']")).getText();
        Assert.assertTrue(actualOffer.contains("8,200"));

        Actions action= new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN,Keys.PAGE_DOWN).perform();



       // 8a. Verify that Vehicle Information table contains the following expected data for the below 2 columns:
        driver.findElement(By.id("ico-step-Vehicle_Profile-btn")).click();

        List<WebElement> vehicleInfo = driver.findElements(By.xpath("//div[@class='kmx-ico-vehicle-info VehicleProfileSummary-module__vehicleInfo--tAwir']"));
        for (WebElement getInfo : vehicleInfo) {
            getInfo.getText();
        }
        Thread.sleep(3000);

        action.sendKeys(Keys.PAGE_UP,Keys.PAGE_UP,Keys.PAGE_UP).perform();



       // 9. Click continue
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[.='Continue']"))).click();
        Thread.sleep(5000);
        driver.manage().deleteAllCookies();
        // 10. On the next page which opens in new window, write a code that chooses one of the locations randomly:
        String windowHandleBefore = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
//            if(driver.getTitle().equals("Appraisal Appointment | CarMax")){
//                break;
          //  }
        }

        Thread.sleep(2000);

        List<WebElement> locations = driver.findElements(By.xpath("//div[@class='mdc-select kmx-select']//option"));
        WebElement sortClick = driver.findElement(By.xpath("//select[@class='mdc-select__native-control']"));
        Select selectOption = new Select(sortClick);
        selectOption.selectByIndex((int) (Math.random() * locations.size()));
Thread.sleep(2000);
        //11. Choose the first available date:
        driver.findElement(By.xpath("//input[@id='react-datepicker']")).click();
        WebElement firstAvailableDate = driver.findElement(By.xpath("//div[starts-with(@aria-label, 'Choose')]"));
        firstAvailableDate.click();
        Thread.sleep(2000);

//      12. Choose the first available time:
        driver.findElement(By.xpath("//input[@id='react-timepicker']")).click();

        driver.findElement(By.id("react-timepicker")).click();

        action.sendKeys(Keys.PAGE_UP).build().perform();
        Thread.sleep(2000);
        WebElement firstAvailableTime = driver.findElement(By.xpath("//li[@class='react-datepicker__time-list-item ']"));
        firstAvailableTime.click();

//     13. Click next.
        js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//button[@type='submit']")));

//      14. On the next page, fill out the form with random info. You can use Faker library
        // or external data file from Mockaroo. DO NOT click on next afterwards since clicking
        // it will create an actual appraisal appointment and will occupy the actual time slot.

       // WebElement radioButton = driver.findElement(By.name("Neither"));
      //  radioButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated
                (By.id("appt-fname"))).click();


        driver.findElement(By.name("appt-fname")).sendKeys(randomData[0]);
        driver.findElement(By.name("appt-lname")).sendKeys(randomData[1]);
        driver.findElement(By.name("appt-phone")).sendKeys(randomData[2]);
        driver.findElement(By.name("appt-email")).sendKeys(randomData[3]);
        driver.findElement(By.id("appt-personal__next")).click();
//      15. Click on Privacy policy link which opens the new tab and verify that the title is "Privacy Policy | CarMax"
        driver.findElement(By.xpath("//a[.='Privacy Policy']")).click();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals("Privacy Policy | CarMax")) {
                break;
            }
        }

//      16. Go back to previous window with the offer amount and click on Save this offer

        driver.close();
        driver.switchTo().window(windowHandleBefore);
        driver.findElement(By.xpath("//button[.='Save this offer']")).click();

//      17. On the pop-up window add random email and click send my offer

        driver.findElement(By.xpath("//label[.='Preferred email']")).sendKeys(randomData[2]);
        driver.findElement(By.xpath("//button[@id='ico-send-offer-email']")).click();

//      18. End the session by closing down all the windows
        driver.close();
        driver.quit();
    }






}

