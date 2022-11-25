package org.amazon.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.amazon.steps.SearchItemsSteps;

public class SearchItemsStepDefinitions {

    SearchItemsSteps searchItemsSteps=new SearchItemsSteps();

    @When("^Search item on header--(.*)$")
    public void search_item_on_header(String searchItem) {
        searchItemsSteps.searchFromSearchBar(searchItem);
    }

    @Then("^Get Electronics Item from result--(.*)$")
    public void get_electronics_item_from_result_1st_page(String getItemType) {
        searchItemsSteps.getElectronicsItemFromResultPage(getItemType);
    }
}
