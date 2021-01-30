package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://li[@id='page-actions-watch']/a";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.watched";
        SHORT_INFO_TITLE_TPL = "xpath://table/caption[@class='summary'][text()='{TITLE}']";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}