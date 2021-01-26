package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOsNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public IOsNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
}
