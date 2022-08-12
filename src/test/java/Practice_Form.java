import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Practice_Form {
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
    public  void studentRegForm() throws InterruptedException, IOException, ParseException {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).sendKeys("Sabila");
        driver.findElement(By.id("lastName")).sendKeys("Nur");
        driver.findElement(By.id("userEmail")).sendKeys("sabila.nur@gmail.com");
        driver.findElement(By.cssSelector("[for=gender-radio-2]")).click();
        driver.findElement(By.id("userNumber")).sendKeys("01912768965");
        Actions action = new Actions(driver);
        WebElement txtDate = driver.findElement(By.id("dateOfBirthInput"));
        txtDate.sendKeys(Keys.CONTROL + "a");
        Thread.sleep(1000);
        driver.findElement(By.id("dateOfBirthInput")).sendKeys("01/08/2022");
        txtDate.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[for=hobbies-checkbox-3]")).click();
        driver.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\Ratna\\Pictures\\Saved Pictures");
        driver.findElement(By.id("currentAddress")).sendKeys("Gulshan-2");
        driver.findElement(By.id("submit")).submit();
        String getName= driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
        String getEmail= driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText();

        String filePath="./src/main/resources/students.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(filePath));

        JSONObject stdObj = new JSONObject();
        stdObj.put("name:", getName);
        stdObj.put("email:", getEmail);
        JSONArray studentInfoArray = (JSONArray) obj;
        studentInfoArray.add(stdObj);

        FileWriter file = new FileWriter(filePath);
        file.write(studentInfoArray.toString());
        JSONArray studentInfoString = studentInfoArray;
        file.flush();
        file.close();
    }

}
