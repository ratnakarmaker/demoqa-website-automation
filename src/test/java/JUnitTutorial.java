import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class JUnitTutorial {
    WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
@Test
    public  void getTitle() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("ToolsQA", title);
        Assert.assertTrue(title.contains("Tools"));
    }
    @Test
    public void writeTest() {
        driver.get("https://demoqa.com/text-box");
        WebElement txtFirstName = driver.findElement(By.id("userName"));
        txtFirstName.sendKeys("Doraemon");

        WebElement txtEmail = driver.findElement(By.cssSelector("[type=email]"));
        txtEmail.sendKeys("doraemon@gmail.com\"");

        WebElement txtCurrentAddress = driver.findElement(By.id("currentAddress"));
        txtCurrentAddress.sendKeys("Panthapath,Dhaka");

        WebElement txtPermanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        txtPermanentAddress.sendKeys("Gazipur, Dhaka");

        List<WebElement> button = driver.findElements(By.tagName("button"));
        button.get(1).click();
        System.out.println(button);
//       driver.findElements(By.tagName("button")).get(1).click();
    }
    @Test
    public void handleAlert() {
        driver.get("https://demoqa.com/alerts");
//        driver.findElement(By.id("alertButton")).click();
//        Thread.sleep(2000);
//        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        String text = driver.findElement(By.className("text-success")).getText();
        Assert.assertTrue(text.contains("Cancel"));
    }
    @Test
    public void selectDate(){
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).click();
    }
    @Test
    public void selectDropDown(){
        driver.get("https://demoqa.com/select-menu");
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByValue("2");
        Select multiSelect = new Select(driver.findElement(By.id("cars")));
        if(multiSelect.isMultiple()){
            multiSelect.selectByValue("volvo");
            multiSelect.selectByValue("audi");
        }
    }
    @Test
    public void mouseHover(){
        driver.get("https://green.edu.bd/");
        Actions actions = new Actions(driver);
        List <WebElement> list = driver.findElements(By.xpath("//a[contains(text(),\"About Us\")]"));
        actions.moveToElement(list.get(2)).perform();
    }

    @After
    public void closeDriver() {
//        driver.close();
    }
}
