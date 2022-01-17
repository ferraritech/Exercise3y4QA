package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;



public class ObjectsYelp {

    WebDriver driver;
    public ObjectsYelp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath="//*[@id='find_desc']")
    WebElement findButton;

    @FindBy(xpath="//*[@data-suggest-query='Restaurants']")
    WebElement RestaurantsItem;

    @FindBy(xpath = "//input[@id='search_description']")
    WebElement searchBox;

    @FindBy(xpath="//form[@id='header_find_form']//button[@value='submit']")
    WebElement searchButton;

    @FindBy(xpath="//*[contains(text(),'Phone number')]//following-sibling::p")
    WebElement restaurantPhone;

    @FindBy(xpath="//*[contains(text(),'Get Directions')]//parent::p//following-sibling::p")
    WebElement restaurantAddress;


    static String xpathNombreRestaurante="//*[contains(@class,'ResultsContainer')]//descendant::h4";
    static Integer count;
    static List<WebElement> myListOfRestaurants;
    static List<WebElement> myReviewsOfRestaurant;


    public void searchRestauran(){

        WebDriverWait wait = new WebDriverWait(driver,5);

        findButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(RestaurantsItem));
        RestaurantsItem.click();
    }

    public void searchTypeRestaurant(String restaurant){

        WebDriverWait wait = new WebDriverWait(driver,30);

        searchBox.clear();
        searchBox.sendKeys(restaurant);
        searchButton.click();
        myListOfRestaurants=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathNombreRestaurante)));
        numSearchPerPage(myListOfRestaurants);

    }


    public void selectFilter(String neighborhood) {

        WebDriverWait wait = new WebDriverWait(driver,20);


        String filterxpath="//span[contains(text(),'"+neighborhood+"')]//parent::div//parent::div//preceding::div[2]";
        WebElement filterNeighboorH = wait.until(ExpectedConditions.elementToBeClickable((By.xpath(filterxpath))));
        filterNeighboorH.click();

    }

    public void selectFirstResult(){

        WebDriverWait wait = new WebDriverWait(driver,30);

        myListOfRestaurants = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathNombreRestaurante)));
        for(WebElement inputElement : myListOfRestaurants){
            if(inputElement.getText().contains(".")){

                inputElement.click();
                break;
            }
        }
    }

    public void getDetails() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,30);

        String phone="El telefono del restaurante es: " +restaurantPhone.getText();
        String address="La direccion del restaurante es: " +restaurantAddress.getText();

        Thread.sleep(4000);

        String xpathReviews = "//p[@class='comment__09f24__gu0rG css-1sufhje']";
        myReviewsOfRestaurant = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathReviews)));
        String firstReview="El primer review mas antiguo es: " +myReviewsOfRestaurant.get(0).getText();
        String secondReview="El segundo review mas antiguo es: " +myReviewsOfRestaurant.get(1).getText();
        String thirdReview="El tercer review mas antiguo es: " +myReviewsOfRestaurant.get(2).getText();
        String details = phone + "\n" + address + "\n" + firstReview + "\n" + secondReview + "\n" +
                thirdReview;
        System.out.println(details);
    }

    public void numSearchPerPage(List<WebElement> lista){
        count=0;
        for(WebElement inputElement : lista){
            if(inputElement.getText().contains(".")){
                count=count+1;
            }
        }

    }

}
