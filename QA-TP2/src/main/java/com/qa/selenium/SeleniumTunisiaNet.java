package com.qa.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class SeleniumTunisiaNet {
    private SeleniumTunisiaNet() {
    }


    final static String URL_LINK = "https://www.tunisianet.com.tn/";
    final static String EMAIL = "userAccount@randomeAccount.com";
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get(URL_LINK);
        String emailAndPassword = RandomStringUtils.random(5, true, true);
        String firstAndLastNames = RandomStringUtils.random(10, true, false);
        User user = new User(firstAndLastNames + "firstname", firstAndLastNames + "lastname",
                emailAndPassword.substring(2) + EMAIL, emailAndPassword,
                new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(1) * 365 * 22));
        

        ISimulator seleniumSimulator = new SeleniumSimulator(user, driver, js);

        seleniumSimulator.signUp();
        seleniumSimulator.signOut();
        seleniumSimulator.signIn();


        Thread.sleep(5500);
        driver.quit();
    }
}
