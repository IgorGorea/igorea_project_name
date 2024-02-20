package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cfg.BrowserDriver;

//TODO read about abstract class, about PageObject pattern, about encapsulation
//TODO to understand what is the difference when logger is in every class or only in one class
//TODO annotation
//TODO read about reverse waiter
public class HomePage extends BrowserDriver {

    public static String hamburger_menu_xpath = "//*[@id=\"menuToggle\"]/input";
    public static String signIn_link_xpath = "//*[@id=\"menu\"]/a[2]/li";

    public static void click_hamburger_menu() throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(By.xpath(hamburger_menu_xpath)).click();
    }
    public static void click_signIn_link() throws InterruptedException{
        Thread.sleep(2000);
        driver.findElement(By.xpath(signIn_link_xpath)).click();
    }

    public static void setDriver(WebDriver webDriver) {
    }
}
