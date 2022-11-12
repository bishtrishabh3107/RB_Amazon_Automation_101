package org.amazon.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.amazon.steps.LoginSteps;

public class LoginStepDefinition {

    LoginSteps loginSteps=new LoginSteps();

    @Given("Launch the url from prop file")
    public void launch_the_url_from_prop_file() {
        loginSteps.launchURLandClickSignIn();
    }

    @When("Enter valid username and password from prop file")
    public void enter_valid_username_and_password_from_prop_file()  {
        loginSteps.enterUserNamePassword();
    }

    @Then("Login and Validate successful login")
    public void login_and_validate_successful_login() {
        loginSteps.loginAndValidate();
    }
}
