package org.amazon.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.amazon.steps.LoginSteps;

public class LoginStepDefinition {

    LoginSteps loginSteps=new LoginSteps();

    @Given("^Launch the url from--(.*)$")
    public void launch_the_url_from_prop_file(String loginType) {
        loginSteps.launchURLandClickSignIn(loginType);
    }

    @When("^Enter valid username and password from prop file$")
    public void enter_valid_username_and_password_from_prop_file()  {
        loginSteps.enterUserNamePassword();
    }

    @When("^Enter valid username and password from datatable$")
    public void enter_valid_username_and_password_from_datatable(DataTable dataTable) {
        loginSteps.enterUserNamePasswordFromDataTable(dataTable);
    }

    @When("Enter valid Username as {string} and Password as {string}.")
    public void enter_valid_username_as_and_password_as(String username, String password) {
        loginSteps.enterUserNamePasswordFromExamples(username,password);
    }

    @Then("^Login and Validate successful login$")
    public void login_and_validate_successful_login() {
        loginSteps.loginAndValidate();
    }

    @And("^Logout$")
    public void logout() {
        loginSteps.logoutAndVerify();
    }
}
