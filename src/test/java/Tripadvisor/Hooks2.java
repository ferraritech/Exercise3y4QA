package Tripadvisor;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks2 {

    private static ChromeDriver driver;

    @Before

    public void setuUp(){

        System.setProperty("webdriver.chrome.driver","./src/test/resources/resourcedriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.tripadvisor.com/");
        driver.manage().window().maximize();

    }

    @After

    public void tearDown(){
        driver.quit();
    }

    public static ChromeDriver getDriver(){
        return driver;
    }

}
