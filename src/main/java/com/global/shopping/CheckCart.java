package com.global.shopping;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckCart {

    //Set chromedriver environment
    public static void main(String[] args) {
        //WebDriverManager.chromedriver().setup();
        System.getProperties().setProperty("webdriver.chrome.driver","C:\\WebDriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        waitSecond(webDriver,10);
        //TestCase 1
        try {
            testCase1(webDriver);
            System.out.println("Test Case 1 pass");
        }
        finally {
            webDriver.quit();
        }


    }

    public static void testCase1(WebDriver webDriver) {
        webDriver.get("http://automationpractice.com/index.php");
        waitSecond(webDriver,10);
        //Locate product - Add first product
        selectProduct(webDriver,"Faded Short Sleeve T-shirts");
        //Select size
        Select selectSize = new Select(webDriver.findElement(By.name("group_1")));
        selectSize.selectByValue(String.valueOf(2));
        //Select color
        webDriver.findElement(By.id("color_14")).click();
        //Add to cart
        webDriver.findElement(By.id("add_to_cart")).click();
        waitSecond(webDriver, 10);
        webDriver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).click();
        waitSecond(webDriver, 5);

        //Add second product
        //search product
        WebElement searchInputbox1 = webDriver.findElement(By.id("search_query_top"));
        searchInputbox1.clear();
        searchInputbox1.sendKeys("Evening Dress");
        searchInputbox1.submit();
        waitSecond(webDriver, 10);
        //Enter second product page, No evening dress, only evening dress category
        selectProduct(webDriver,"Printed Dress");
        webDriver.findElement(By.id("add_to_cart")).click();
        waitSecond(webDriver, 10);
        webDriver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).click();
        waitSecond(webDriver, 5);

        //Add third product
        WebElement searchInputbox2 = webDriver.findElement(By.id("search_query_top"));
        searchInputbox2.clear();
        searchInputbox2.sendKeys("Printed Summer Dress");
        searchInputbox2.submit();
        //Locate product3, same product name, according to color or URL
        webDriver.get("http://automationpractice.com/index.php?id_product=5&controller=product#/color-orange/size-m");
 /*       WebElement product3 = webDriver.findElement(By.id("color_21"));
        product3.click(); */
        waitSecond(webDriver,10);
        webDriver.findElement(By.id("add_to_cart")).findElement(By.tagName("button")).click();
        waitSecond(webDriver, 10);

        //Checkout
        webDriver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
        waitSecond(webDriver, 10);

        //Delete Evening dress
        WebElement productDelete = webDriver.findElement(By.id("product_4_16_0_0"));
        productDelete.findElement(By.className("cart_quantity_delete")).click();
        waitSecond(webDriver, 10);

        //Add quantity
        WebElement productAdd = webDriver.findElement(By.id("cart_quantity_up_1_4_0_0"));
        productAdd.click();
        waitSecond(webDriver, 20);

        //Check Total for each line
        //wait number refresh
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.attributeToBe(By.name("quantity_1_4_0_0_hidden"),"value","2"));
        WebElement product1Total = webDriver.findElement(By.id("total_product_price_1_4_0"));
        WebElement product2Total = webDriver.findElement(By.id("total_product_price_5_25_0"));
        assertEquals("$33.02",product1Total.getText());
        assertEquals("$28.98",product2Total.getText());

        //Check Total price, Failed cause by discount
        WebElement totalPrice = webDriver.findElement(By.id("total_price"));
        assertEquals("$65.13",totalPrice.getText());

    }

    public static void waitSecond(WebDriver webDriver, int seconds){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public static void selectProduct(WebDriver webDriver, String productName){
        List<WebElement> products = webDriver.findElements(By.className("product-name"));
        for (WebElement product : products) {
            if (product.getAttribute("title").equals(productName)) {
                product.click();
                waitSecond(webDriver,10);
                break;
            }
        }
    }

}
