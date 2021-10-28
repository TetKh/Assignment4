package AutomationProgect;

import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Assignment3 {
    public static void main(String[] args) throws InterruptedException, IOException, CsvValidationException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tetya\\OneDrive\\Documents\\SELENIUM\\DRIVERS\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

//        1.	Navigate  to carfax.com
        driver.get("https://www.carfax.com/");

        driver.manage().deleteAllCookies();

//        2.	Click on Find a Used Car
        driver.findElement(By.xpath("//a[@href='/cars-for-sale']")).click();

//        3.	Verify the page title contains the word “Used Cars”
        assertTrue(driver.getPageSource().contains("Used Cars"));

//        4.	Choose “Tesla” for  Make.
        Select make = new Select(driver.findElement(By.name("make")));
        make.selectByValue("Tesla");

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


//        5.	Verify that the Select Model dropdown box contains 4 current
//               Tesla models - (Model 3, Model S, Model X, Model Y).
        List<String> actualModels= new ArrayList<>();
        List<String>expectedModels= Arrays.asList("Model 3","Model S","Model X","Model Y");

        List<WebElement> elements = new Select(driver.findElement(By.xpath("//select[@name='model']"))).getOptions();
        for (int i = 1; i < elements.size() - 1; i++){
            actualModels.add(elements.get(i).getText().trim());
        }
        Assert.assertEquals(actualModels,expectedModels);

        Thread.sleep(1000);


//        6.	Choose “Model S” for Model.
        Select model= new Select(driver.findElement(By.name("model")));
        model.selectByValue("Model S");
//        7.	Enter the zip code 22182 and click Next
        driver.findElement(By.name("zip")).sendKeys("22182");
        driver.findElement(By.id("make-model-form-submit")).click();
        Thread.sleep(3000);
//        8.	Verify that the page contains the text “Step 2 – Show me cars with”
        Assert.assertTrue(driver.getPageSource().contains("Step 2 - Show me cars with"));
//        9.	Check all 4 checkboxes.
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle noAccidents']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle oneOwner']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle personalUse']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@aria-label = 'Toggle serviceRecords']")).click();

        Thread.sleep(1000);
//        10.	Save the count of results from “Show me X Results” button. In this case it is 10.
        String resultText =  driver.findElement(By.xpath("//span[@class = 'totalRecordsText']")).getText();
//        11.	Click on “Show me x Results” button.
        driver.findElement(By.xpath("//button[@class='button large primary-green show-me-search-submit']")).click();

//        12.	Verify the results count by getting the actual number of results displayed
//        in the page by getting the count of WebElements that represent each result
        List<WebElement> actualCount =driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//article"));
        int elementsCount = actualCount.size();

        Assert.assertEquals(elementsCount, Integer.parseInt(resultText));

//        13.	Verify that each result header contains “Tesla Model S”.

        List<WebElement> nameOfElements = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//article"));

        for (WebElement element : nameOfElements) {
            Assert.assertTrue(element.getText().contains("Tesla Model S"));
        }
        Thread.sleep(5000);
//        14.	Get the price of each result and save them into a List in the order of their appearance.
//        (You can exclude “Call for price” options)
        List<String> a = new ArrayList<>();
        List<WebElement> price  = driver.findElements(By.xpath("//span[@class = 'srp-list-item-price']"));
        for(int i = 0; i < price.size(); i++){
            if(!price.get(i).getText().contains("Call for Price")){
                a.add(price.get(i).getText());
            }
        }

        Thread.sleep(500);

//        15.	Choose “Price - High to Low” option from the Sort By menu
        Select highToLow = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
        highToLow.selectByValue("PRICE_DESC");

        Thread.sleep(2000);
//        16.	Verify that the results are displayed from high to low price.

        List<WebElement> pricesHighToLow =driver.findElements(By.xpath("//div//span[@class='srp-list-item-price']"));
        Collections.sort(price, Collections.reverseOrder());
        Assert.assertEquals(pricesHighToLow, price);

//        17.	Choose “Mileage - Low to High” option from Sort By menu
        Select actualMileage = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
        actualMileage.selectByValue("MILEAGE_ASC");
        Thread.sleep(1000);
//        18.	Verify that the results are displayed from low to high mileage.

        List<String> mileagesLowToHigh = My_Methods.getText(driver.findElements(By.xpath("//span[@class='srp-list-item-basic-info-value']")));
        Assert.assertEquals(mileagesLowToHigh, actualMileage);


//        19.	Choose “Year - New to Old” option from Sort By menu
        Select yearNewToOld = new Select(driver.findElement(By.xpath("//select[@aria-label= 'SelectSort']")));
        yearNewToOld.selectByValue("YEAR_DESC");

        Thread.sleep(1000);

//        20.	Verify that the results are displayed from new to old year.
        List<WebElement> listOfYear = driver.findElements(By.xpath("//div[@class='srp-list-container  srp-list-container--srp']//article//h4"));
        List<String>listYear=new ArrayList<>();
        for(WebElement year:listOfYear){
            listYear.add(year.getText());
        }

        for (String y:listYear) {
            int z=listYear.indexOf(y);
            listYear.set(z,y.substring(0,5));}
//
//        Push the code to a new GitHub repo and share the link in a text file and submit.
        driver.quit();

    }
    }
