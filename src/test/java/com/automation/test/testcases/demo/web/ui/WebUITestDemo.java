package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.GoogleSearchPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class WebUITestDemo extends BaseTest {
    private static final Logger LOGGER = Logger.getLogger(WebUITestDemo.class);

    private String baseURL;

    public WebUITestDemo() {
        baseURL = "http://www.google.com";
    }


    @Test
    public void tc001_VerifyGoogleSearchPageWithoutDataDriven() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        googleSearchPage.navigateToURL(baseURL);

        Assert.assertTrue(googleSearchPage.isPageTitleContains("Google"));

        googleSearchPage.inputSearchTextAndSubmit("No data driven automation");
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc002_VerifyGoogleSearchPageWithData(HashMap<String, String> data) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        googleSearchPage.navigateToURL(baseURL);

        Assert.assertTrue(googleSearchPage.isPageTitleContains("Google"));

        if(data.get("ID TCs").equalsIgnoreCase("tc002")){
            googleSearchPage.inputSearchTextAndSubmit(data.get("search_text"));

        }
//        googleSearchPage.inputSearchTextAndSubmit(data.get("search_text"));

    }

    @Test(dataProvider = "FilterData", groups = "tc003", dataProviderClass = DataProviderClass.class)
    public void tc004_VerifyGoogleSearchPageWithData1(HashMap<String, String> data) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        googleSearchPage.navigateToURL(baseURL);

        Assert.assertTrue(googleSearchPage.isPageTitleContains("Google"));

        googleSearchPage.inputSearchTextAndSubmit(data.get("search_text"));

    }
}
