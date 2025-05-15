package manakin.ru.stalcraftmonitor;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthTest {

//    private final WebDriver driver = new ChromeDriver();
    private WebDriverWait wait;
    private final String URL = "http://localhost:8080";
    private final String HOME = URL;
    private final String LOGIN = URL + "/login";
    private final String USERNAME = "test@mail.ru";
    private final String PASSWORD = "testtest";

    @Test
    public void loginTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Was_a\\Desktop\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("http://localhost:8080");

        driver.get(LOGIN);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("loginBtn"));

        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);

        loginButton.click();

        wait.until(ExpectedConditions.urlContains("http://localhost:8080"));
        WebElement logoutBtn = driver.findElement(By.name("logoutBtn"));
        Assertions.assertTrue(logoutBtn.isDisplayed());

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("logoutBtn")));
        logoutButton.click();

        wait.until(ExpectedConditions.urlToBe(LOGIN));
        WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginText")));
        Assertions.assertTrue(loginForm.isDisplayed());
    }
}
