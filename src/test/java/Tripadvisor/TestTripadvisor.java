package Tripadvisor;

import PageObjects.ObjectsTripadvisor;
import Tripadvisor.Hooks2;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTripadvisor {


    private ChromeDriver driver = Hooks2.getDriver();

    ObjectsTripadvisor  objectsTripadvisor = new ObjectsTripadvisor(driver);


    @Given("^user navigates to https://www.tripadvisor.com/$")
    public void userNavigateToYelp(){
        String tittleHomePage = "Tripadvisor: Read Reviews, Compare Prices & Book";
        Assert.assertEquals(tittleHomePage,driver.getTitle());


    }

    @And("^selects find Restaurants$")
    public void userSearch(){
        objectsTripadvisor.searchRestauran();

    }

    @Given("^User search restaurant (.*)$")
    public void userSearchRestaurant(String restaurant){
        objectsTripadvisor.searchTypeRestaurant(restaurant);

    }

    @When("^filters by (.*)$")
    public void userSelectFilter(String filter) throws InterruptedException {
        objectsTripadvisor.selectFilter(filter);

    }



    @Then("^User views restaurant information$")
    public void userViewDetails(){
        objectsTripadvisor.getDetails();

    }

}
