package org_UI;
import io.cucumber.java.en.*;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import java.util.List;
import java.util.UUID;

public class StepDefinitionsNewUser {
    private String randomEmail;  // class-level variable
    private String password;
    WebDriver driver;
    commonMethods common;

    public static String
            reusedEmail;
    public StepDefinitionsNewUser() {
        this.driver = Hooks.driver;
        common = new commonMethods(driver);

    }

    @Given("the user is on the homepage with internet access")
    public void the_user_is_on_the_homepage_with_internet_access() {
        driver.get(ConfigReader.getProperty("baseUrl"));
    }


    @When("the user clicks {string}")
    public void the_user_clicks(String buttonName) {

            common.click(CreateAccountLocators.createAccountLink);

    }

    @When("the user enters personal information:")
    public void the_user_enters_personal_information(DataTable table) {
        List<Map<String, String>> data = table.asMaps();
        String firstName = data.get(0).get("First Name");
        String lastName = data.get(0).get("Last Name");

        common.typeText(CreateAccountLocators.firstNameInput, firstName);
        common.typeText(CreateAccountLocators.lastNameInput, lastName);
    }



    @When("the user enters unique sign-in information")
    public void the_user_enters_unique_sign_in_information() {
        randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
        password = "Test@1234";

        System.out.println("Generated email: " + randomEmail);

        common.typeText(CreateAccountLocators.emailInput, randomEmail);
        common.typeText(CreateAccountLocators.passwordInput, password);
        common.typeText(CreateAccountLocators.confirmPasswordInput,password);
    }


    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String string) {
        common.click(CreateAccountLocators.createButton);

    }

    @Then("the account should be created successfully")
    public void the_account_should_be_created_successfully() {
        common.click(CreateAccountLocators.Thank_you_registering);
    }



    @Then("the user clicks the {string} button")
    public void the_user_clicks_the_button(String string) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable(CreateAccountLocators.dropdownlogout));
        signOutButton.click();
        common.click(CreateAccountLocators.logoutUser);
    }

    @Given("I am on the Sign In page")
    public void i_am_on_the_sign_in_page() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable(CreateAccountLocators.SignInAccount));
        signOutButton.click();


    }

    @When("When I enter login credentials")
    public void when_i_enter_login_credentials() {

        String Email = "user_e7187b85@test.com";
        String password = "Test@1234";

        common.typeText(CreateAccountLocators.SignemailInput,Email);
        common.click(CreateAccountLocators.SignpasswordInput);
        common.typeText(CreateAccountLocators.SignpasswordInput,password);
    }


    @When("I click on the {string} button")
    public void i_click_on_the_button(String string) {
        common.click(CreateAccountLocators.signInButton);

    }


    @When("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        String currentUrl = common.getCurrentURL();
        System.out.println("Current URL: " + currentUrl);
    }
}
