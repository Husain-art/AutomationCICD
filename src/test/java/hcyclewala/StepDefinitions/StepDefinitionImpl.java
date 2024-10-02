package hcyclewala.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import hcyclewala.TestComponents.BaseTest;
import hcyclewala.pageobjects.CartPage;
import hcyclewala.pageobjects.CheckoutPage;
import hcyclewala.pageobjects.ConfirmationPage;
import hcyclewala.pageobjects.LandingPage;
import hcyclewala.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
    public LandingPage landingPage;

    @Given("I have landed on Ecommerce page")
    public void I_landed_on_EcommercePage() throws IOException
    {
        landingPage = launchApplication();
    }

    public ProductCatalogue productCatalogue;

    @Given("^Logged in with userName (.+) and Password(.+)$")
    public void logged_in_username_and_password(String userName, String password)
    {
        productCatalogue= landingPage.LoginApplication(userName, password);
    }

    CartPage cartPage;
    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException
    {
        cartPage = productCatalogue.addProductToCart(productName);
        productCatalogue.goToCart();
    }

    ConfirmationPage confirmationPage;
    @When("^Checkout ProductName (.+) and submit the order$")
    public void chkout_Product_and_Submit_order(String ProductName)
    {
        Boolean match = cartPage.VerifyProducts(ProductName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();

        checkoutPage.selectContry("ind", "India");
        confirmationPage = checkoutPage.SubmitOrder();
    }

    @Then("{string} message should be displayed on confirmation page.")
    public void message_Displayed_CofirmationPage(String string)
    {
        String cnfrmMessage = confirmationPage.getCnfrmatnMsg();
        Assert.assertTrue(cnfrmMessage.equalsIgnoreCase(string));
    }



}
