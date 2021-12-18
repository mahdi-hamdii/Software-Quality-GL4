package com.qa.selenium;

import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumSimulator implements ISimulator {

    final static String DATE_FORMAT = "dd/MM/yyyy";
    final static String PRODUCT_NAME = "PC portable MacBook M1 13.3";
    private User user;
    private WebDriver driver;
    private JavascriptExecutor js;

    SeleniumSimulator(User user, WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = js;
        this.user = user;
    }

    public void signUp() throws InterruptedException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);

        Thread.sleep(1000);
        WebElement dropDown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        dropDown.click();


        Thread.sleep(1000);
        WebElement signInButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        signInButton.click();


        Thread.sleep(1000);
        WebElement singUpButton = driver.findElement(By.className("no-account"));
        singUpButton.click();

        Thread.sleep(1000);
        List<WebElement> genderField = driver.findElements(By.className("custom-radio"));
        genderField.get(0).click();

        Thread.sleep(1000);
        List<WebElement> formInputs = driver.findElements(By.cssSelector("input.form-control"));
        formInputs.get(1).sendKeys(user.firstName);
        formInputs.get(2).sendKeys(user.lastName);
        formInputs.get(3).sendKeys(user.email);
        formInputs.get(4).sendKeys(user.password);
        formInputs.get(5).sendKeys(dateFormatter.format(user.birthday));

        js.executeScript("window.scrollBy(0,250)", ""); // Scroll down

        Thread.sleep(1000);
        WebElement signupButton = driver.findElement(By.className("form-control-submit"));
        signupButton.click();
    }

    public void signOut() throws InterruptedException {
        Thread.sleep(1000);
        WebElement dropDown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        dropDown.click();

        Thread.sleep(1000);
        WebElement signoutButton = driver.findElement(By.className("logout"));
        signoutButton.click();
    }

    public void signIn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement dropDown = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        dropDown.click();

        Thread.sleep(1000);
        WebElement signInButton = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        signInButton.click();

        Thread.sleep(1000);
        WebElement emailInput = driver.findElement(By.cssSelector(".form-group > div > input"));
        emailInput.sendKeys(user.email);

        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(By.cssSelector(".form-group > div > div > input"));
        passwordInput.sendKeys(user.password);

        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();
    }

    public void orderProduct() throws InterruptedException {

        Thread.sleep(1000);
        WebElement searchInput = driver.findElement(By.className("search_query"));
        searchInput.sendKeys(PRODUCT_NAME);

        Thread.sleep(1000);
        WebElement searchButton = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        searchButton.click();

        Thread.sleep(1000);
        List<WebElement> productsTitleList = driver.findElements(By.className("product-title"));
        productsTitleList.get(0).click();

        Thread.sleep(1000);
        WebElement addProductButton = driver.findElement(By.className("add-to-cart"));
        addProductButton.click();

        Thread.sleep(1000);
        WebElement orderButton = driver.findElement(By.cssSelector("a.btn-block"));
        orderButton.click();
    }
}
