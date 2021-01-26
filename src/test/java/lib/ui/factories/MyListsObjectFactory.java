package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListsPageObject;
import lib.ui.android.AndroidMyListsObject;
import lib.ui.ios.IOsMyListsObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsObjectFactory
{
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsObject(driver);
        } else {
            return new IOsMyListsObject(driver);
        }
    }
}
