package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            SYNC_POPUP_CLOSE_BUTTON,
            REMOVE_FROM_SAVED_BUTTON_BY_TITLE_TPL;

    /* TEMPLATE METHODS */
    private static String getFolderXpathByName (String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle (String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle (String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */

    public MyListsPageObject (RemoteWebDriver driver){
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find created folder ", 5);
    }

    public void waitForArticleToAppearByTitle (String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article with title "+article_title, 15);
    }

    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved Article still present with title "+article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        if(Platform.getInstance().isAndroid()||Platform.getInstance().isIOs()){
            this.swipeElementToLeft(article_xpath, "Cannot swipe article to delete");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove article from saved", 10);
        }
        if (Platform.getInstance().isIOs()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        waitForArticleToDisappearByTitle(article_title);
    }

    public void assertThereIsArticleWithTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.assertElementPresent(article_xpath, "We supposed to find article with title " + article_title + " in my lists folder");
    }

    public void openArticleByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath, "Cannot find and open saved article with title " + article_title, 5);
    }

    public void clickSyncPopupCloseButton()
    {
        this.waitForElementAndClick(SYNC_POPUP_CLOSE_BUTTON, "Cannot find and click x button on sync popup", 5);
    }
}
