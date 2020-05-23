package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage extends BasePage {
    public GoogleSearchPage(WebDriver driver) {
        super(driver);
    }

    private String txtQuery = "name=q";

    public WebObject getTxtQuery() {
        return findWebElement(txtQuery);
    }

    public void inputSearchTextAndSubmit(String searchText) {
        getTxtQuery().sendKeys(searchText, true);
    }
}
