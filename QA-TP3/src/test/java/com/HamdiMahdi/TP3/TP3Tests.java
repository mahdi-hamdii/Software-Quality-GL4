package com.HamdiMahdi.TP3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TP3Tests {
	WebDriver webDriver;
	JavascriptExecutor javascriptExecutor;

	@BeforeAll
	public static void setupDriver() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void manageDriver() {
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		webDriver.manage().timeouts().setScriptTimeout(2, TimeUnit.MINUTES);

		javascriptExecutor = (JavascriptExecutor) webDriver;
	}

	@ParameterizedTest
	@ValueSource(strings = { "Backbone.js",
			"AngularJS",
			"Dojo",
			"React" })
	public void todosTestCase(String framework) throws InterruptedException {
		webDriver.get("https://todomvc.com");
		frameworkChoice(framework);
		addTodo("Do Homework");
		addTodo("Write Code");
		addTodo("Sleep");
		addTodo("Repeat");
		// Remove Sleep
		removeTodoByPosition(3);
		Thread.sleep(1000);
		assertNumberOfLeftItems(3);
	}

	@Test
	public void todoTestCase() throws InterruptedException {
		webDriver.get("https://todomvc.com");
		frameworkChoice("Backbone.js");
		addTodo("Do Homework");
		addTodo("Write Code");
		addTodo("Sleep");
		addTodo("Repeat");
		// Remove Sleep
		removeTodoByPosition(3);
		Thread.sleep(1000);
		assertNumberOfLeftItems(3);
	}

	private void frameworkChoice(String framework) {
		WebElement element = webDriver.findElement(By.linkText(framework));
		element.click();
	}

	private void addTodo(String todo) {
		WebElement element = webDriver.findElement(By.className("new-todo"));
		element.sendKeys(todo);
		element.sendKeys(Keys.RETURN);
	}

	private void removeTodoByPosition(int position) {
		String selector = String.format("li:nth-child(%d) .toggle", position);
		WebElement element = webDriver.findElement(By.cssSelector(selector));
		element.click();
	}

	private void assertNumberOfLeftItems(int expectedLeft) {
		WebElement element = webDriver.findElement(By.xpath("//footer/*/span | //footer/span"));
		String expectedTest = String.format("%d %s left", expectedLeft, expectedLeft == 1 ? "item" : "items");
		validateInnerText(element, expectedTest);
	}

	private void validateInnerText(WebElement element, String expectedTest) {
		ExpectedConditions.textToBePresentInElement(element, expectedTest);
	}

}
