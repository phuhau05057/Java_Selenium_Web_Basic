package com.automation.test.testcases.demo.web.ui.InventoryTestCases;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import com.automation.test.libraries.web.ui.pageobjects.InventoryPage;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class Inventory_HN005_VerifyTableSizeIsDisplayedCorrectly extends BaseTest{

    @Test
    public void HN005_VerifyTableSizeIsDisplayedCorrectly() throws Exception {

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Inventory Menu
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getMenuInventory().click();

        //Verify all records on table are displayed equal or less than with Size of page
        String assertbehavior = inventoryPage.checkWorkingSizeofPage(30);
        Assert.assertEquals(assertbehavior,"Table displays only equal or less than 30");

    }
}
