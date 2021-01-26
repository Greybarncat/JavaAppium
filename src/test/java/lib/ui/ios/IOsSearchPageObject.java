package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsSearchPageObject extends SearchPageObject
{
    static{
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@label='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_LABEL = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCollectionView//*[@name='{DESCRIPTION}']/../*[@name='{TITLE}']";
        SEARCH_INPUT_FIELD = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_TITLE = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_CLEAR_BUTTON = "id:Clear text";
    }

    public IOsSearchPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
