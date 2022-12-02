package org.amazon.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.amazon.steps.SearchItemsSteps;
import org.amazon.steps.SearchWithFilterSteps;

public class SearchItemsStepDefinitions {
    SearchItemsSteps searchItemsSteps=new SearchItemsSteps();
    SearchWithFilterSteps searchWithFilterSteps=new SearchWithFilterSteps();

    @When("^Search item on header--(.*)$")
    public void search_item_on_header(String searchItem) {
        searchItemsSteps.searchFromSearchBar(searchItem);
    }

    @Then("^Get Electronics Item from result--(.*)$")
    public void get_electronics_item_from_result_1st_page(String getItemType) {
        searchItemsSteps.getElectronicsItemFromResultPage(getItemType);
    }

    @And("^Enter filters$")
    public void enter_filters(DataTable dataTable) {
        searchWithFilterSteps.enterFilters(dataTable);
    }

    @Then("^Validate filters results$")
    public void validate_filters_results(DataTable dataTable) {
        searchWithFilterSteps.validateFilterResults(dataTable);
    }
}
