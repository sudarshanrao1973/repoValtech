package com.test;

import com.test.pageobjects.HomePage;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.test.helpers.UrlBuilder.startAtHomePage;

public class HomePageSteps implements En {
    private HomePage homePage;

       public HomePageSteps() {
        Given("^User is on valtech home page$", () -> {
            startAtHomePage();
        });
        And("^Click on accept cookies if it displays$", () -> {
            homePage = new HomePage();
            homePage.clickOnAcceptCookies();
        });
           And("^User clicks on top menu$", () -> {
              homePage.clickOnTopMenu();
           });
           When("^User click on About page$", () -> {
              homePage.clickOnAboutLink();
           });
           Then("^About page should be displyed with heading \"([^\"]*)\"$", (String heading) -> {
               Assert.assertEquals(homePage.getSectionHeading(),heading,"Heading is not correct");
           });
           When("^User click on Work page$", () -> {
              homePage.clickOnWorkLink();
           });
           Then("^Work page should be displyed with heading \"([^\"]*)\"$", (String heading) -> {
               Assert.assertEquals(homePage.getSectionHeading(),heading,"Heading is not correct");
           });
           When("^User click on Serivcs page$", () -> {
               homePage.clickOnServiceLink();
           });
           Then("^Services page should be displyed with heading \"([^\"]*)\"$", (String heading) -> {
               Assert.assertEquals(homePage.getSectionHeading(),heading,"Heading is not correct");
           });
           Given("^User is on valtech contact us page$", () -> {

           });
           And("^User Click on Contactus Page$", () -> {

           });
           Then("^User should be on Contactus page$", () -> {

           });
           And("^Verify the offices count$", () -> {
               
           });
       }
}
