package com.qa.stepdef;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.runners.MyRunnerTest;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef{
	
//	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
//	private static ExtentTest test;
//	
//	@Before
//    public void before(Scenario scenario) {
//		String testCaseName = scenario.getName();
//		 test = MyRunnerTest.extent.
//				createTest("TC_Name :: "+testCaseName);
//		System.out.println("TC_Name :: "+testCaseName);
//		extentTest.set(test);
//    }

    @When("I enter username as {string}")
    public void iEnterUsernameAs(String username) throws InterruptedException {
        new LoginPage().enterUserName(username);
        Hooks.test.log(Status.PASS, "I enter username as "+username);
    }

    @When("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        new LoginPage().enterPassword(password);
        Hooks.test.log(Status.PASS, "I enter password as "+password);
    }

    @When("I login")
    public void iLogin() {
        new LoginPage().pressLoginBtn();
        Hooks.test.log(Status.PASS, "Clicked on login button");
    }

    @Then("login should fail with an error {string}")
    public void loginShouldFailWithAnError(String err) {
        Assert.assertEquals(new LoginPage().getErrTxt(), err);
        Hooks.test.log(Status.PASS, "Verified the login error message : "+err);
    }

    @Then("I should see Products page with title {string}")
    public void iShouldSeeProductsPageWithTitle(String title) {
        Assert.assertEquals(new ProductsPage().getTitle(), title);
    }
}
