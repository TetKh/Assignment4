package AutomationProgect;

import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
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

// 3. Login using username Tester and password test
        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);

//4. Click on Order link
        driver.findElement(By.linkText("Order")).click();
//5. Enter a random product quantity between 1 and 100
        int nums = 1 + (int) (Math.random() * 100); //random.nextInt(100);
        String quantity = "" + nums;
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(quantity);

//  6. Click on Calculate and verify that the Total value is correct.
        driver.findElement(By.className("btn_dark")).click();

        int total;
        if (nums <= 10) {
            total = 100 * nums;
        } else {
            total = (int) (100 * nums * 0.08);
        }

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).equals(total);

//    Price per unit is 100.  The discount of 8 % is applied to quantities of 10+.
//    So for example, if the quantity is 8, the Total should be 800.
//    If the quantity is 20, the Total should be 1840.
//    If the quantity is 77, the Total should be 7084. And so on.
//
//7. Generate and enter random first name and last name,address,city,state,zipCode.
        Random random=new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String num = "1234567890";

        StringBuilder sb = new StringBuilder();
        for (int i = 3; i < 5; i++)
            sb.append(abc.charAt(random.nextInt(abc.length())));
        StringBuilder sb1 = new StringBuilder();
        for (int i = 4; i < 10; i++)
            sb1.append(abc.charAt(random.nextInt(abc.length())));
        String name = sb +" " + sb1;
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(name);


        StringBuilder sb2 = new StringBuilder();
        for (int i = 1; i < 4; i++) {
            sb2.append(num.charAt(random.nextInt(num.length())));
        }
        StringBuilder sb3 = new StringBuilder();
        for (int i = 4; i < 10; i++)
            sb3.append(abc.charAt(random.nextInt(abc.length())));
        String address = sb2 +" " + sb3;
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(address);

        StringBuilder sb4 = new StringBuilder();
        for (int i = 4; i < 10; i++)
            sb4.append(abc.charAt(random.nextInt(abc.length())));
        String city = sb4.toString();
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);


        StringBuilder sb5 = new StringBuilder();
        for (int i = 1; i <= 2; i++)
            sb5.append(abc.charAt(random.nextInt(abc.length())));
        String state = sb5.toString();
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);


      //List<String[]> list = readFromCSV("C:\\Users\\tetya\\IdeaProjects\\Selenium\\src\\MOCK_DATA (1).csv");

//        for (int i = 0; i < 4; i++) {
//
//        }
//        int randomIndex1 = random.nextInt(list.size());


//        for (String[] col: list) {
//            System.out.println(Arrays.toString(col));
//
//
//            if (col[0].equals("name")) {
//                driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(col[0]);
//            }if (col[1].equals("address")) {
//                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(col[1]);
//            }if (col[2].equals("city")) {
//                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(col[2]);
//            }if (col[3].equals("state")) {
//                driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(col[3]);
//            }
//        }


        StringBuilder sb6 = new StringBuilder();
        for (int i = 1; i <= 5; i++)
            sb6.append(num.charAt(random.nextInt(num.length())));
        String zipCode = sb6.toString();
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode, Keys.TAB);
Thread.sleep(3000);
//
//    EXTRA: As an extra challenge, for steps 7-10 download 1000 row of corresponding realistic data from mockaroo.com
//    in a csv format and load it to your program and use the random set of data from there each time.


// 11. Select the card type randomly. On each run your script should select a random type.

            List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));

            int randomNo = (int) (Math.random() * radioButtons.size());

            radioButtons.get(randomNo).click();
            Thread.sleep(2000);

            if (randomNo == 0) {
                long Visa = (long) (Math.random() * 1000000000000000L);
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("" + 4 + Visa);
            } else if (randomNo == 1) {
                long MC = (long) (Math.random() * 1000000000000000L);
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("" + 5 + MC);
            } else if (randomNo == 2) {
                long AmEx = (long) (Math.random() * 100000000000000L);
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("" + 3 + AmEx);
            }


//   13. Enter a valid expiration date (newer than the current date)
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("09/29");
        Thread.sleep(2000);
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
            Thread.sleep(3000);


// 17. The placed order details appears on the first row of the orders table.

// Verify that the entire information contained on the row (Name, Product, Quantity, etc)
// matches the previously entered information in previous steps.

// 18. Log out of the application.
            driver.close();
            driver.quit();
//    Push your code to GitHub, and share the repo link in the given repo.txt file

        }


        public static List<String[]> readFromCSV(String pathToFile) throws FileNotFoundException {


        List<String[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                list.add(row);}
        } catch (Exception e) {
            e.printStackTrace();
        } return list;
    }


    }