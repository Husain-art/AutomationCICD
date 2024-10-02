Feature: Purchase the order from Ecommerce website

Background: I have landed on Ecommerce page

@Regression
Scenario Outline: positive test ofsubmitting the order

    Given Logged in with userName <UserName> and Password<Password>
    When I add product <ProductName> to cart 
    And Checkout ProductName    <ProductName> and submit the order
    Then "Thankyou FOR THE ORDER" message should be displayed on confirmation page.

    Examples:
    |UserName               |Password           |ProductName    |
    |Husain@gmail.com       |Hostel@1234        |ZARA COAT 3    |
    |Husain@gmail.com       |Hostel@1234        |ADIDAS ORIGINAL|