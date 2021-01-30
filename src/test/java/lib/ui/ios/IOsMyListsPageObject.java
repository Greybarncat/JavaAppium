package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        SYNC_POPUP_CLOSE_BUTTON = "id:Close";
    }

    public IOsMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
