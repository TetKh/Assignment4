package AutomationProgect;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class Assignment2 {


    public static void main(String[] args) throws InterruptedException, IOException, CsvValidationException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tetya\\OneDrive\\Documents\\SELENIUM\\DRIVERS\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//2. Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

//        String absolutePath = "C:\\Users\\tetya\\IdeaProjects\\Selenium\\src\\customers.csv";
//        String relativePAth = "customers.csv";
//        File folder = new File(absolutePath);
//        System.out.println(folder.exists());
//        String[] csvCell;
//        CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\tetya\\IdeaProjects\\Selenium\\src\\customers.csv"));
//        while ((csvCell = csvReader.readNext()) != null) {


// 3. Login using username Tester and password test
            driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);

////4. Click on Order link
            driver.findElement(By.linkText("Order")).click();
//
////5. Enter a random product quantity between 1 and 100
            int nums = 1 + (int) (Math.random() * 100); //random.nextInt(100);
            String quantity = "" + nums;
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(quantity);
            driver.findElement(By.xpath("//input[@value =\"Calculate\"]")).click();

////  6. Click on Calculate and verify that the Total value is correct.
            driver.findElement(By.className("btn_dark")).click();
            if (nums < 10) {
                int totalValue = nums * 100;
                String str1 = new String(String.valueOf(totalValue));
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).equals(str1);

            } else {
                int totalValue = (int) (nums * 100 * 0.08);
                String str1 = new String(String.valueOf(totalValue));
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).equals(str1);
            }
//
////7. Generate and enter random first name and last name,address,city,state,zipCode.
//


//            String name = csvCell[0];
//            String address = csvCell[1];
//            String city = csvCell[2];
//            String state = csvCell[3];
//            String zipCode = csvCell[4];


        String[] name1 = new String[]{"Jori Rapin","Stefania Bym","Neilla Simmance","Carly Mee","Serge Spindler"};
        String[] address1 = new String[]{"469 Cambridge Court", "34 Talisman Crossing", "412 Riverside Court", "9078 Banding Center", "375 Mariners Cove Junction"};
        String[] city1 = new String[]{"Brooklyn","Boston","Miami","Exton","Broomall"};
        String[] state1 = new String[]{"NY","MA","FL","PA","TX"};
        String[] zipCode1 = new String[]{"10090","95405","77206","33633","22603"};

        String name = name1[new Random().nextInt(name1.length)];
        String address = address1[new Random().nextInt(address1.length)];
        String city =city1[new Random().nextInt(city1.length)];
        String state = state1[new Random().nextInt(state1.length)];
        String zipCode = zipCode1[new Random().nextInt(zipCode1.length)];

        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(address);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode);

//
//// 11. Select the card type randomly. On each run your script should select a random type.
//
            List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));
            int cardType = (int) (Math.random() * radioButtons.size());
            radioButtons.get(cardType).click();

            long cardNumber = 0;
            String cardName = "";

            if (cardType == 0) {
                cardName = "Visa";
            } else if (cardType == 1) {
                cardName = "MasterCard";
            } else if (cardType == 2) {
                cardName = "American Express";
            }


            //Generating card number corresponding to the selected card type
            if (cardType == 0) {
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).click();
                cardNumber = (long) (Math.random() * 1000000000000000L);

            } else if (cardType == 1) {
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).click();
                cardNumber = (long) (Math.random() * 1000000000000000L);
            } else if (cardType == 2) {
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).click();
                cardNumber = (long) (Math.random() * 100000000000000L);
            }
            long cardNumberInput = cardNumber;

            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(Long.toString(cardNumberInput));

//   13. Enter a valid expiration date (newer than the current date)
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("09/29");
//14. Click on Process
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

//15. Verify that “New order has been successfully added” message appeared on the page.
            //driver.findElement(By.linkText("New order has been successfully added."));

            String expectedText = "New order has been successfully added";
            String pageSource = driver.getPageSource();
            assertTrue(pageSource.contains(expectedText));

//16. Click on View All Orders link.

            //  driver.findElement(By.linkText("View All Orders")).click();
            driver.findElement(By.partialLinkText("all orders")).click();


// 17. The placed order details appears on the first row of the orders table.
        // Verify that the entire information contained on the row (Name, Product, Quantity, etc)
        // matches the previously entered information in previous steps.
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String todaysDate = formatter.format(date);
            String product = "MyMoney";
            String actualFirstRow = driver.findElements(By.tagName("tr")).get(2).getText();
            String expectedFirstRow = name + " " + product + " " +nums+ " " + todaysDate + " " + address + " " + city + " " + state + " " + zipCode + " " + cardName + " " + cardNumberInput + " " + "09/29";
            Assert.assertEquals(actualFirstRow, expectedFirstRow);

// 18. Log out of the application.
            driver.close();
            driver.quit();
//    Push your code to GitHub, and share the repo link in the given repo.txt file

        }


    }