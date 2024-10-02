package hcyclewala.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import hcyclewala.TestComponents.BaseTest;
import hcyclewala.pageobjects.CartPage;
import hcyclewala.pageobjects.CheckoutPage;
import hcyclewala.pageobjects.ConfirmationPage;
import hcyclewala.pageobjects.OrderPage;
import hcyclewala.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
    {
        ProductCatalogue pc = l.LoginApplication(input.get("email"), input.get("password"));

        CartPage cp = pc.addProductToCart(input.get("productName"));
        pc.goToCart();

        Boolean match = cp.VerifyProducts(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage cop = cp.goToCheckout();

        cop.selectContry("ind", "India");
        ConfirmationPage cnfPg = cop.SubmitOrder();

        String ConfirmationMsg = cnfPg.getCnfrmatnMsg();
        Assert.assertTrue(ConfirmationMsg.equalsIgnoreCase("Thankyou for the order."));
       
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrdersHistory(){
        String ProductName = "ZARA COAT 3";
        l.LoginApplication("Husain@gmail.com", "Hostel@1234");
        OrderPage orderPage = l.gotoOrders();
        Boolean match = orderPage.verifyOrderDisplay(ProductName);
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() throws IOException{

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\hcyclewala\\data\\PurchaseOrder.json");

        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
            // HashMap<String, String> map = new HashMap<String, String>();
        // map.put("email", "Husain@gmail.com");
        // map.put("password", "Hostel@1234");
        // map.put("productName", "ZARA COAT 3");

        // HashMap<String, String> map1 = new HashMap<String, String>();
        // map1.put("email", "Husain@gmail.com");
        // map1.put("password", "Hostel@1234");
        // map1.put("productName", "ADIDAS ORIGINAL");
}
