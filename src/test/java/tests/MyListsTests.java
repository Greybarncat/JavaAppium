package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for My list")
public class MyListsTests extends CoreTestCase
{
    private static final String
            name_of_folder = "Learning programming",
            login = "GreyBarnCat",
            password = "Hellgate65";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Adding My list"), @Feature(value = "Article")})
    @DisplayName("Adding 1 article to my list")
    @Description("We add article to my list and open it through My list page")
    @Step("Starting test testSafeFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSafeFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        try {Thread.sleep(3000);} catch (Exception e) {}
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            try {Thread.sleep(3000);} catch (Exception e) {}
            AuthorizationPageObject.clickAuthButton();
            try {Thread.sleep(3000);} catch (Exception e) {}
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.submitForm();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOs()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            try {Thread.sleep(3000);} catch (Exception e) {}
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOs()){
            MyListsPageObject.clickSyncPopupCloseButton();
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Adding My list"), @Feature(value = "Removing My list"), @Feature(value = "Article")})
    @DisplayName("Adding 2 article to my list")
    @Description("We add 2 articles to my list, then remove one and open second through My list page")
    @Step("Starting test testSaving2Articles")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSaving2Articles()
    {
        String first_article_title = "Java (programming language)";
        String second_article_title = "Python (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
            first_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyList(name_of_folder);
            ArticlePageObject.closeArticle();
        } else if (Platform.getInstance().isIOs()){
            ArticlePageObject.waitForTitleElementIOs(first_article_title);
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.waitForTitleElement();
            first_article_title = ArticlePageObject.getArticleTitle();
            try {Thread.sleep(3000);} catch (Exception e) {}
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject AuthorizationPageObject = new AuthorizationPageObject(driver);
            try {Thread.sleep(3000);} catch (Exception e) {}
            AuthorizationPageObject.clickAuthButton();
            try {Thread.sleep(3000);} catch (Exception e) {}
            AuthorizationPageObject.enterLoginData(login, password);
            AuthorizationPageObject.submitForm();
            ArticlePageObject.waitForTitleElement();
            Assert.assertEquals(
                    "We are not on the same page after login",
                    first_article_title,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()){
            SearchPageObject.initSearchInput();
        } else {
            SearchPageObject.clearSearchInput();
        }
        SearchPageObject.typeSearchLine("Python");
        if (Platform.getInstance().isAndroid()){
            SearchPageObject.clickByArticleWithSubstring("General-purpose programming language");
        } else {
            SearchPageObject.clickByArticleWithSubstring("General-purpose, high-level programming language");
        }
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.waitForTitleElement();
            second_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMyListInExistFolder(name_of_folder);
            ArticlePageObject.closeArticle();
        } else if(Platform.getInstance().isIOs()){
            ArticlePageObject.waitForTitleElementIOs(second_article_title);
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeArticle();
        } else {
            ArticlePageObject.waitForTitleElement();
            second_article_title = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isIOs()){
            SearchPageObject.clickCancelSearch();
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            try {Thread.sleep(5000);} catch (Exception e){}
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOs()){
            MyListsPageObject.clickSyncPopupCloseButton();
        }
        MyListsPageObject.swipeByArticleToDelete(second_article_title);
        MyListsPageObject.assertThereIsArticleWithTitle(first_article_title);
        MyListsPageObject.openArticleByTitle(first_article_title);
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMW()){
            ArticlePageObject.waitForTitleElement();
        } else {
            ArticlePageObject.waitForTitleElementIOs(first_article_title);
        }
        if (Platform.getInstance().isAndroid()){
            Assert.assertEquals(
                    "Unexpected title in first article after deleting second",
                    first_article_title,
                    ArticlePageObject.getArticleTitle()
            );
        } else if (Platform.getInstance().isIOs()){
            ArticlePageObject.clickContentsButton();
            ArticlePageObject.waitForContentsTitleElement(first_article_title);
        } else {
            ArticlePageObject.waitForShortInfoWithTitle("Java Programming Language");
        }
    }
}
