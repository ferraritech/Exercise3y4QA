package Yelp;

import PageObjects.ObjectsYelp;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {



    private ChromeDriver driver = Hooks.getDriver();

    ObjectsYelp objectsYelp = new ObjectsYelp(driver);


    @Given("^user navigates to https://www.yelp.com/$")
    public void userNavigateToYelp(){
        String tittleHomePage = "Restaurants, Dentists, Bars, Beauty Salons, Doctors - Yelp";
        Assert.assertEquals(tittleHomePage,driver.getTitle());

    }

    @And("^selects find Restaurants$")
    public void userSearch(){

        objectsYelp.searchRestauran();
    }

    @Given("^User search restaurant (.*)$")
    public void userSearchRestaurant(String restaurant){
        objectsYelp.searchTypeRestaurant(restaurant);

    }

    @When("^filters by (.*)$")
    public void userSelectFilter(String filter){
        objectsYelp.selectFilter(filter);

    }

    @And("^select the first search result$")
    public void userSelectFirstResult(){
        objectsYelp.selectFirstResult();

    }

    @Then("^User views restaurant information$")
    public void userViewDetails() throws InterruptedException {
        objectsYelp.getDetails();

    }
}
