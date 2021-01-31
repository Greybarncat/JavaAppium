package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.Assert;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_LABEL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_INPUT_FIELD,
            SEARCH_RESULT_TITLE,
            SEARCH_CLEAR_BUTTON;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement (String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitleAndDescription (String title, String description)
    {
        String search_result_with_title = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title);
        String search_result_with_title_and_description =search_result_with_title.replace("{DESCRIPTION}",description);
        return search_result_with_title_and_description;
    }
    /* TEMPLATE METHODS */

    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clicking search init element");
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Waiting for button to cancel search to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Clicking button to cancel search results")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Clicking button to clear search input")
    public void clickClearSearchButton()
    {
        this.waitForElementAndClick(SEARCH_CLEAR_BUTTON, "Cannot find and click clear search button", 5);
    }

    @Step("Typing '{search_line}' into the search line")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Select an article by substring in article title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Getting amount of found articles")
    public int getAmountOfFindArticles ()
    {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request", 15);
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Waiting for empty results label")
    public void waitForEmptyResultLabel()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_LABEL, "Cannot find empty result element", 15);
    }

    @Step("Making sure there are no results for the search")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");
    }

    @Step("Check result's title and description")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description, 10);
    }

    @Step("Making sure search input has '{text}' text")
    public void assertSearchInputHasText(String text)
    {
        if (Platform.getInstance().isAndroid()){
            this.assertElementHasText(SEARCH_INPUT_FIELD, text, "We supposed search line has text " + text);
        } else {
            this.assertElementHasName(SEARCH_INPUT, text, "We supposed search line has text " + text, 5);
        }
    }

    @Step("Check all results contains '{text}'")
    public void assertAllSearchResultContainsText(String text)
    {
        this.waitForElementPresent(SEARCH_RESULT_TITLE, "Cannot find search result", 10);

        ArrayList<WebElement> results = (ArrayList<WebElement>) getElementsByString(SEARCH_RESULT_TITLE);

        for (int i=0;i<results.size();i++){
            String title = results.get(i).getAttribute("text");
            Assert.assertTrue("Result don't contain " + text ,title.toLowerCase().contains(text));
        }
    }

    @Step("Clear search line")
    public void clearSearchInput()
    {
        this.waitForElementAndClear(SEARCH_INPUT, "Cannot find and clear search input", 5);
    }
}
