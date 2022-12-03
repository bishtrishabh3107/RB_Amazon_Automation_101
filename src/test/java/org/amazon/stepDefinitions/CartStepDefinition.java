package org.amazon.stepDefinitions;

import io.cucumber.java.en.Then;
import org.amazon.steps.CartSteps;

public class CartStepDefinition {

    CartSteps cartSteps=new CartSteps();

    @Then("Add maximum bought product to cart")
    public void add_maximum_bought_product_to_cart() {
        cartSteps.addMaximumBoughtProductToCart();
    }
    @Then("Validate the cart")
    public void validate_the_cart() {
        cartSteps.validateCart();
    }

}
