package org.amazon.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.amazon.steps.AmazonVideosSteps;

import java.io.IOException;

public class AmazonVideosStepDefinitions {

    AmazonVideosSteps avs=new AmazonVideosSteps();

    @Given("^Select Categories--(.*)--Get--(.*)$")
    public void select_categories_get(String category,String filter) throws IOException, InterruptedException {
        avs.getContentFromCategories(category, filter);
    }

    @And("Categories--(.*)--Validate--(.*)")
    public void categories_validate(String category,String name){
        avs.validateContentFromCategories(category,name);
    }
}
