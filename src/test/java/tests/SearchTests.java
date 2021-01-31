package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Search page")
public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Searching result")
    @Description("We search 'Java' and check result")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Canceling search")
    @Description("We initialize and cancel search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Test of not empty search")
    @Description("We search 'Linkin Park Discography' and check amount of results")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFindArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Test of empty search")
    @Description("We search 'eyreuerr' and check no results")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "eyreuerr";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Cancel search results")
    @Description("We search 'D'n'D' and cancel search")
    @Step("Starting test testCancelSearchResult")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "D'n'D";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFindArticles();

        Assert.assertTrue("We found less than 2 search results by " +search_line,amount_of_search_results>=2);

        if(Platform.getInstance().isAndroid()){
            SearchPageObject.clickCancelSearch();
        } else {
            SearchPageObject.clickClearSearchButton();
        }
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Results by title and description")
    @Description("We search 'river' and check 3 result title and description")
    @Step("Starting test testSearchResultByTitleAndDescription")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchResultByTitleAndDescription()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("river");
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.waitForElementByTitleAndDescription("River","Larger natural watercourse");
        } else {
            SearchPageObject.waitForElementByTitleAndDescription("River","Natural flowing watercourse");
        }
        SearchPageObject.waitForElementByTitleAndDescription("Riverdale (2017 TV series)","American teen drama television series");
        SearchPageObject.waitForElementByTitleAndDescription("River Thames","River in southern England");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Text on search line")
    @Description("We initialize search and check text on search line")
    @Step("Starting test testCompareSearchLineText")
    @Severity(value = SeverityLevel.MINOR)
    public void testCompareSearchLineText()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.assertSearchInputHasText("Search…");
        } else {
            SearchPageObject.assertSearchInputHasText("Search Wikipedia");
        }
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Search results")})
    @DisplayName("Correct search results")
    @Description("We search 'Java' and check all result contains it")
    @Step("Starting test testCorrectSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testCorrectSearchResults()
    {
        if (Platform.getInstance().isIOs()) return;
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertAllSearchResultContainsText("java");
    }
}
