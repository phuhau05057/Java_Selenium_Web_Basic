package com.automation.test.testcases.demo.web.ui.InventoryTestCases;


import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import com.automation.test.libraries.web.ui.pageobjects.InventoryPage;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Inventory_HN014_VerifyUserCanUpdateCategory extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(Inventory_HN014_VerifyUserCanUpdateCategory.class);
    final String columnCategory = ConfigurationValue.columnCategory;

    @Test(dataProvider = "FilterData", dataProviderClass = DataProviderClass.class)
    public void HN014_VerifyUserCanUpdateCategory(HashMap<String, String> data) throws Exception {

        InventoryPage inventoryPage = new InventoryPage(driver);

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Inventory Menu
        inventoryPage.getMenuInventory().click();

        //Add new Item on Category table
        String category = data.get(columnCategory);
        inventoryPage.addNewCategory(category);

        //Update Category
        String newCategory ="ForUpdated";
        inventoryPage.updateCategoryItem(category,newCategory);

        //Verify that Category is updated correclty
        inventoryPage.checkCategoryIsExisting(newCategory,true);

        //Delete Category
        inventoryPage.deleteCategoryItem(newCategory);

    }
}
