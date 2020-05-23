package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodSchedulePage extends BasePage {

    public FoodSchedulePage(WebDriver driver) {
        super(driver);
    }

    private final String menuFoodSchedule = "//a/span[text()='Food Schedule']";

    public WebObject getMenuFoodSchedule()
    {
        return findWebElement(menuFoodSchedule);
    }

    public void getStatusofSort(String column, String sortType) {

        String indexColumn = "";

        switch (column) {
            case "Item":
                indexColumn = "1";
                break;

            case "Category":
                indexColumn = "2";
                break;

            case "Start Date":
                indexColumn = "3";

            case "Frequency":
                indexColumn = "4";
        }

        String elementOfColumn = "//div[@class='react-bs-container-body']/table/tbody//td[" + indexColumn + "]";
        System.out.println("element after using switch case" + elementOfColumn);

        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = getWebDriver().findElements(By.xpath(elementOfColumn));
        System.out.println("elementlist" + elementList);
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }

        System.out.println("List get form table" + obtainedList);
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : obtainedList) {
            sortedList.add(s);
        }

        if (sortType.equals("ASC")) {
            Collections.sort(sortedList);
            System.out.println("List after sorting ASC" + sortedList );
        }
        else if (sortType.equals("DESC")) {
            Collections.sort(sortedList);
            Collections.reverse(sortedList);
            System.out.println("List after sorting DESC" + sortedList );
        }

        Assert.assertTrue(sortedList.equals(obtainedList));

    }

    public void verifySortByColumn(String nameofColumn, String typeofSort) {

        //Identify the element of Header from variable
        String elementofHeader = "//th[@title='" + nameofColumn + "']/span";
        WebElement getHeader = getWebDriver().findElement(By.xpath(elementofHeader));
        // For exactly status of Sort button. We need to click on first time for getting Status
        getHeader.click();

        if (typeofSort.equals("ASC")) {
            //Get current status of Sort button and make click/unclick based on SortType
            String getCurrentSortStatuofName = getHeader.getAttribute("class");
            if (!getCurrentSortStatuofName.equals("order dropup")) {
                getHeader.click();
                getStatusofSort(nameofColumn, typeofSort);
            } 
            else getStatusofSort(nameofColumn, typeofSort);
        }
        else if (typeofSort.equals("DESC")) {
            //Get current status of Sort button and make click/unclick based on SortType
            String getCurrentSortStatuofName = getHeader.getAttribute("class");
            if (!getCurrentSortStatuofName.equals("order")) {
                getHeader.click();
                getStatusofSort(nameofColumn, typeofSort);
            } 
            else getStatusofSort(nameofColumn, typeofSort);
        } 
    }
}
