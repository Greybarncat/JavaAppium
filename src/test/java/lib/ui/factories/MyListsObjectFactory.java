package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListsObject;
import lib.ui.ios.IOsMyListsPageObject;
import lib.ui.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsObjectFactory
{
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsObject(driver);
        } else if (Platform.getInstance().isIOs()){
            return new IOsMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
