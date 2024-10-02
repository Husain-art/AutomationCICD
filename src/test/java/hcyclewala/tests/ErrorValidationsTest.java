package hcyclewala.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import hcyclewala.TestComponents.BaseTest;
import hcyclewala.TestComponents.Retry;
import hcyclewala.pageobjects.CartPage;
import hcyclewala.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{
    // 
    @Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException
    {
        l.LoginApplication("Husain@gmail.com", "Hostl@1234");
        String errorMessage = l.getErrorMessage();
        System.out.println(errorMessage);
        Assert.assertTrue(errorMessage.equalsIgnoreCase("Incorrect email password."));
    }

    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException
    {
        String ProductName = "ZARA COAT 3";
        ProductCatalogue pc = l.LoginApplication("Husain@gmail.com", "Hostel@1234");
        CartPage cp = pc.addProductToCart(ProductName);
        pc.goToCart();
        Boolean match = cp.VerifyProducts("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
