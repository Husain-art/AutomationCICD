package hcyclewala.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src\\test\\java\\hcyclewala\\cucumber\\PurchaseOrder.feature", glue="hcyclewala.StepDefinitions", monochrome=true, 
tags = "@Regression", plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests
{


}
