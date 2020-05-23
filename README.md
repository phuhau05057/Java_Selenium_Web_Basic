# START WEB SERVER
install pantry-for-good instruction:
Install Node.js and MongoDB (Mongo on MacOS: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/)
Clone project https://github.com/freeCodeCamp/pantry-for-good
Install dependencies by running command line: npm install
Start server: npm run dev
Server is ready on address: http://localhost:8080 ; login with user/pass: admin@example.com / password

Test cases are available at https://docs.google.com/spreadsheets/d/1XRUrPVT1wkkWFBjPmP1I1qizqPLR5Wxrl7HNerDmEks/edit?usp=sharing

# Java Automation Framework Demo

### Configure Java Project
It is recommended to edit the project by using Intellij IDE.

###### Run the Demo from the IDE
* Right Click on the TestSuites/com.automation.test.testcases.demo.xml file in the Project panel
* Click on Run '.../JavaDemo/TestSuites/com.automation.test.testcases.demo.xml'

###### Run the Demo from Console
* It is required to download and configure the maven build in advanced
* Jump in the project folder
* Type maven command _mvn clean test_
* Or run another test suite by _mvn clean test -DsuiteXmlFile=Test Suite Path_

###### Get the chrome driver
* Download the executable file for your Chrome browser at https://sites.google.com/a/chromium.org/chromedriver/downloads
* Unzip and copy the chromedriver.exe file to folder **WebDriver**

### Write test script
###### Add a new Test Class under folder **src/test/java/Your_Test_Package**
```java
public class WebAPIDemo extends OrgBaseTest {}
public class WebUITestDemo extends BaseTest {}
```
Item Name | Explanation
------------ | -------------
WebAPIDemo | Demo Test Class for API
WebUITestDemo | Demo Test Class for UI or Selenium
OrgBaseTest | Base Test Class for all Test
BaseTest | Base Test Class for all UI Test

###### Test which uses Data-Driven
* Add Data Provider Class
```java
public class WebAPIDemo extends OrgBaseTest {
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
        public void tc003_VerifyGetPostObjectByIdSuccessfully(HashMap<String, String> data) {
            PostMethods postMethods = new PostMethods();
            Post postObj = postMethods.getPostObjectByID(data.get("post_object_id"));
    
            LOGGER.info(postObj);
        }
}
```
Item Name | Explanation
------------ | -------------
dataProvider | The method of Data Provider Class which generates the data for this Test
dataProviderClass | Data Provider Class for Data-Driven test
HashMap<String, String> data | Data returned from the Data Provider in Map format
data.get("post_object_id") | How to get data from the returned Map data

* Modify DataMappings.csv

test_case_id | data_type | data_file_path
----------------- | --------------- | ---------------
Name of the Test Method | CSV/JSON | Path of the Data-Driven file of the Test under **TestData** folder and Environment folder

- [x] Example:

test_case_id | data_type | data_file_path
----------------- | --------------- | ---------------
tc002_VerifyGoogleSearchPageWithData | CSV | GoogleSearchText.csv

* Create the Data-Driven file (GoogleSearchText.csv)

param1 | param 2 | ... | param n
--------------- | --------------- | ---------------- | ----------------
value 1 row 1 | value 2 row 1 |  ... | value n row 1
value 1 row 2 | value 2 row 2 |  ... | value n row 2
... |  ... |  ... | ...
value 1 row n | value 2 row n |  ... | value n row n

- [x] Example:

search_text | expected_text
---------- | ----------
DEV - KMS Technology |Offshore Product Development and Testing
DEV - Java Automation Framework |Framework

# Your Assignments

- [ ] Present the **Architecture Design** of this Framework by Slides
- [ ] Update Data Provider Class to **support JSON** format
- [ ] Update Report to generate **Reports in a custom folder**
- [ ] Update Selenium module to **support other Browsers** than Chrome browser
- [ ] Implement the **BaseTest class of the Web API** to avoid duplicated code when sending and receiving API Requests
- [ ] Integrate the **Java Framework with Jenkins** to run Test Suites and send Result Emails to the viewers
