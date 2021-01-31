package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
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
    public void testSearchResultByTitleAndDescription()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("river");
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.waitForElementByTitleAndDiscription("River","Larger natural watercourse");
        } else {
            SearchPageObject.waitForElementByTitleAndDiscription("River","Natural flowing watercourse");
        }
        SearchPageObject.waitForElementByTitleAndDiscription("Riverdale (2017 TV series)","American teen drama television series");
        SearchPageObject.waitForElementByTitleAndDiscription("River Thames","River in southern England");
    }

    @Test
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
    public void testCorrectSearchResults()
    {
        if (Platform.getInstance().isIOs()) return;
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.assertAllSearchResultContainsText("java");
    }
}
