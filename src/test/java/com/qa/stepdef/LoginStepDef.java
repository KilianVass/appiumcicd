package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {
    @When("I enter username as {string}")
    public void iEnterUsernameAs(String username) {
        new LoginPage().enterUsername(username);
     }
    @When("I enter the password as {string}")
    public void iEnterThePasswordAs(String password) {
        new LoginPage().enterPassword(password);
    }
    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
    }
    @Then("Login should fail with an error {string}")
    public void loginShouldFailWithAnError(String err) {
        Assert.assertEquals(new LoginPage().getText(), err);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assert.assertEquals(new ProductsPage().getTitle(), title);
    }
}
