package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObjectsTripadvisor {

    WebDriver driver;
    public ObjectsTripadvisor(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@class='fZFmh P']")
    WebElement searchBox;

    @FindBy(xpath = "//*[contains(text(),'Los Angeles')]")
    WebElement losAngelesItem;

    @FindBy(xpath = "//span[contains(text(),'Explore')]")
    WebElement titleExplore;

    @FindBy(xpath = "//span[contains(text(),'Restaurants')]")
    WebElement restaurantButton;

    @FindBy(xpath = "//form[@role='search']//input[@type='search']")
    WebElement searchRestaurant;

    @FindBy(xpath = "//button[@title='Search']")
    WebElement searchButton;


    @FindBy(xpath = "//span[contains(@class,'ffdhf b')]")
    WebElement totalResultados;

    static String xpathNombreRestaurante="//*[contains(@class,'OhCyu')]//descendant::a";
    static String xpathRestaurantContainers = "//*[@id='EATERY_SEARCH_RESULTS']//descendant::a";
    static List<WebElement> myListOfRestaurants;
    static List<WebElement> typeOfMeal;

    public void searchRestauran(){

        WebDriverWait wait = new WebDriverWait(driver,5);

        searchBox.click();
        wait.until(ExpectedConditions.elementToBeClickable(losAngelesItem));
        losAngelesItem.click();

    }

    public void searchTypeRestaurant(String restaurant){

        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        wait.until(ExpectedConditions.visibilityOf(titleExplore));
        restaurantButton.click(); //Hace click a la opcion restaurant
        wait.until(ExpectedConditions.elementToBeClickable(searchRestaurant)); //se espera que cargue la pagina
        searchRestaurant.click();  // hace click al buscador;
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchRestaurant.sendKeys(restaurant);
        searchButton.click();
        myListOfRestaurants=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathNombreRestaurante)));


    }

    public void selectFilter(String meal) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);

        String filterxpath= "//*[@id=\"component_45\"]/div/div[3]/div[2]/div[1]/div/label/div/span/span[contains(text(),'" +meal+ "')]";

        this.typeOfMeal = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(filterxpath)));
        typeOfMeal.get(0).click();

        Thread.sleep(5000);
    }

    public void getDetails(){

        WebDriverWait wait = new WebDriverWait(driver,30);

        myListOfRestaurants = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathRestaurantContainers)));

        System.out.println("ESTOS SON LOS PRIMEROS 30 RESULTADOS");
        for(int i=0 ; i<=myListOfRestaurants.size();i++) {
            try {

                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                System.out.println(myListOfRestaurants.get(i).getText());
            } catch (Exception e) {
            }
        }

        String Resultados = "EL NUMERO TOTAL DE RESULTADOS ES: "+totalResultados.getText();
        System.out.println(Resultados);



    }

}
