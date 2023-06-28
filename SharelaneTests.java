import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SharelaneTests {

    private static final String URL = "https://www.sharelane.com/cgi-bin/main.py";
    private WebDriver driver;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver(); //в начале можно писать вебдрайвер (это лучше и правильнее), но и хромдрайвер; во второй части можно менять названия браузеров
        driver.manage().window().maximize(); // база
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // база, чтобы мы могли видеть, что выполняет программа, иначе будет слишком быстро
    }
    @AfterClass
    public void tearDown () {
        driver.quit(); //закрывает окно браузера; driver.close() - закрывает последнюю вкладку
    }

    @BeforeMethod
    public void navigate () {
        driver.get(URL); // закроет потом браузер после ожидания
    }

    @Test
    public void positiveRegistrationTest() {
       WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
       signUpButton.click();

       WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
       zipCodeInput.clear();
       zipCodeInput.sendKeys("12345");

       WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
       continueButton.click();

       zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]")); // проверка 1
       Assert.assertFalse(zipCodeInput.isDisplayed());

       WebElement firstNameInput = driver.findElement(By.cssSelector("input[name=first_name]")); // проверка 2
       Assert.assertTrue(firstNameInput.isDisplayed());


       WebElement firstName = driver.findElement(By.cssSelector("input[name=first_name]"));
       firstName.clear();
       firstName.sendKeys("Vika");

       WebElement lastName = driver.findElement(By.cssSelector("input[name=last_name]"));
       lastName.clear();
       lastName.sendKeys("Zlobich");

       WebElement eMail = driver.findElement(By.cssSelector("input[name=email]"));
       eMail.clear();
       eMail.sendKeys("vzlobich@mail.ru");

       WebElement password1 = driver.findElement(By.cssSelector("input[name=password1]"));
       password1.clear();
       password1.sendKeys("dbrf123");

       WebElement confirmPassword = driver.findElement(By.cssSelector("input[name=password2]"));
       confirmPassword.clear();
       confirmPassword.sendKeys("dbrf123");

       WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
       registerButton.click();

       WebElement confirmationMessage = driver.findElement(By.cssSelector(".confirmation_message")); // проверка
       Assert.assertTrue(confirmationMessage.isDisplayed());
       String expectedConfirmationMessageText = "Account is created!";
       Assert.assertEquals(confirmationMessage.getText(), expectedConfirmationMessageText);
    }

    @Test
    public void negativeSignUpTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();

        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]")); // проверка 1
        Assert.assertFalse(zipCodeInput.isDisplayed());

        WebElement firstName = driver.findElement(By.cssSelector("input[name=first_name]"));
        firstName.clear();
        firstName.sendKeys("Вика");

        WebElement lastName = driver.findElement(By.cssSelector("input[name=last_name]"));
        lastName.clear();
        lastName.sendKeys("Злобич");

        WebElement eMail = driver.findElement(By.cssSelector("input[name=email]"));
        eMail.clear();
        eMail.sendKeys("vzlobich@mail.ru");

        WebElement password1 = driver.findElement(By.cssSelector("input[name=password1]"));
        password1.clear();
        password1.sendKeys("dbrf123");

        WebElement confirmPassword = driver.findElement(By.cssSelector("input[name=password2]"));
        confirmPassword.clear();
        confirmPassword.sendKeys("dbrf123");

        WebElement registerButton = driver.findElement(By.cssSelector("input[value=Register]"));
        registerButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".error_message")); // проверка
        Assert.assertTrue(errorMessage.isDisplayed());
        String expectedErrorMessageText = "Oops, error on page. Some of your fields have invalid data or email was previously used";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessageText);
    }
    @Test
    public void negativeRegistrationTest() {
        WebElement signUpButton = driver.findElement(By.xpath("//a[@href='./register.py']"));
        signUpButton.click();

        WebElement zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("1234");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value=Continue]"));
        continueButton.click();

        zipCodeInput = driver.findElement(By.cssSelector("input[name=zip_code]")); // проверка 1
        Assert.assertTrue(zipCodeInput.isDisplayed());

       WebElement errorMessage = driver.findElement(By.cssSelector(".error_message")); // проверка 2
       Assert.assertTrue(errorMessage.isDisplayed());
       String expectedErrorMessageText = "Oops, error on page. ZIP code should have 5 digits";
       Assert.assertEquals(errorMessage.getText(), expectedErrorMessageText);
    }

}
