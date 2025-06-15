package org_UI;

import org.openqa.selenium.By;

public class CreateAccountLocators {

    public static final By createAccountLink = By.xpath("//div[@class='panel header']//a[text()='Create an Account']");
    public static final By firstNameInput = By.id("firstname");
    public static final By lastNameInput = By.id("lastname");
    public static final By emailInput = By.id("email_address");
    public static final By passwordInput = By.id("password");
    public static final By confirmPasswordInput = By.id("password-confirmation");
    public static final By createButton = By.xpath("//button[@title='Create an Account']");
    public static final By Thank_you_registering = By.xpath("//div[contains(text(), 'Thank you for registering')]");
    public static final By dropdownlogout = By.xpath("(//span[@data-toggle=\"dropdown\"])[1]");
    public static final By logoutUser = By.xpath("(//a[normalize-space(text())='Sign Out'])[1]");
    public static final By existingAccount = By.xpath("//div[contains(text(), 'There is already an account with this email address')]");
    public static final By SignInAccount = By.xpath("(//a[text()[normalize-space()='Sign In']])[1]");
    public static final By SignemailInput = By.id("email");
    public static final By SignpasswordInput = By.id("pass");
    public static final By signInButton = By.xpath("//button[contains(@class, 'action login') and @type='submit']");
    public static final By My_Account = By.xpath("//span[@data-ui-id='page-title-wrapper' and normalize-space()='My Account']");



}

