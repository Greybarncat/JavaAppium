package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        MY_LIST_FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{NAME}']";
        TITLE_IOS_BY_NAME_TPL = "id:{NAME}";
        CONTENTS_TITLE_BY_NAME_TPL = "xpath://XCUIElementTypeStaticText[@label='{NAME}']";
        CONTENTS_BUTTON = "id:Table of contents";
    }

    public IOsArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
