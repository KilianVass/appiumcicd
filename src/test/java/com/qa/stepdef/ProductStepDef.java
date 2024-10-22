package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.Product;
import org.junit.Assert;

public class ProductStepDef {

    @Given("I'm logged in")
    public void iMLoggedIn() {
        new LoginPage().login("standard_user", "secret_sauce");
    }
    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {
        String checkTitle = String.valueOf(new ProductsPage().getSLBTitle());
        Assert.assertEquals(checkTitle, "Sauce Labs Backpack");

    }

    @When("I click on product title {string}")
    public void iClickOnProductTitle(String title) {
        new ProductsPage().pressSLBTitle();
    }
    @Then("I should be on product details page with title {string}, price {string} and description {string}")
    public void iShouldBeOnProductDetailsPageWithTitlePriceAndDescription(String title, String price, String description) {
        ProductsDetailsPage productsDetailsPage = new ProductsDetailsPage();
        boolean titleCheck = productsDetailsPage.getSLBTitle().equalsIgnoreCase(title);
        boolean descCheck = productsDetailsPage.getSLBText().equalsIgnoreCase(description);
        boolean priceCheck = productsDetailsPage.getSLBPrice().equalsIgnoreCase(price);

        Assert.assertTrue("titleCheck = " + titleCheck + ", descCheck = " + ", priceCheck = "+
                priceCheck, titleCheck & descCheck & priceCheck);
    }
}
