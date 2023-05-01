package StepDefination;

import Objects.Locators;
import com.gemini.generic.reporting.GemTestReporter;
import com.gemini.generic.reporting.STATUS;
import com.gemini.generic.ui.utils.DriverAction;
import com.gemini.generic.ui.utils.DriverManager;
import com.gemini.generic.utils.ProjectConfigData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static Objects.Locators.*;

public class StepDefination {
    public static String company = "Gemini" + Math.random();
    public static String str = "company" + Math.random();
    public static String projectNames = "SUITE_" + Math.random();
    Logger logger = LoggerFactory.getLogger(StepDefination.class);

    public void global2() throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.logIn);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "arpit.mishra");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "arpit1234");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^click on admin$")
    public void admin_Screen() {
        try {
            global2();
            DriverAction.click(admin);
            DriverAction.waitSec(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^verify the url of admin screen$")
    public void admin_url() throws Exception {
        try {
            String s = DriverAction.getCurrentURL();
            String url = ProjectConfigData.getProperty("launchUrl");
            String url_actual;
            if (url.contains("beta")) {
                url_actual = "https://jewel-beta.gemecosystem.com/#/admin";
            } else {
                url_actual = "https://jewel.gemecosystem.com/#/admin";
            }
            STATUS status;
            if (s.contains(url_actual)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Admin Screen URL Validation", "Expected Text: " + url_actual + "<br>Actual Text: " + s, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify action buttons$")
    public void admin_action() throws Exception {
        try {
            if (DriverAction.getElement(edit_project_details2).isDisplayed() && DriverAction.getElement(delete_project2).isDisplayed() && DriverAction.getElement(edit_access2).isDisplayed()) {
                GemTestReporter.addTestStep("Verify action buttons are enabled", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify action buttons are enabled", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify action button$")
    public void admin_action2() throws Exception {
        try {
            if (DriverAction.getElement(edit_project_details).isDisplayed() && DriverAction.getElement(delete_project).isDisplayed() && DriverAction.getElement(edit_access).isDisplayed()) {
                GemTestReporter.addTestStep("Verify action buttons are disabled", "Successful", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Verify action buttons are disabled", "Unsuccessful", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate sorting of admin screen$")
    public void validate_sorting_adminScreen() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno);
            DriverAction.waitSec(2);
            String s2 = DriverAction.getElementText(Locators.span);
            DriverAction.pageScroll(2000, 2000);
            DriverAction.waitSec(4);
            STATUS status;
            String s3 = DriverAction.getElementText(Locators.total_projects);
            String s4 = s3.substring(6, 7);
            if (s2.equals(s4)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Sorting Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s4, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }

    }

    @Then("^validate the alert when user don't have access$")
    public void validate_alert_when_user_have() throws Exception {
        try {
            DriverAction.click(request_access);
            DriverAction.waitSec(2);
            DriverAction.click(dropdown_of_request);
            DriverAction.waitSec(2);
            DriverAction.click(inputBox_of_request);
            DriverAction.waitSec(2);
            DriverAction.click(select_access_request);
            DriverAction.waitSec(2);
            DriverAction.click(admin_select);
            DriverAction.waitSec(2);
            DriverAction.click(request_access_btn);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "Request has been send";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Request has been send Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the alert user already have access$")
    public void admin_screen_alert2() throws Exception {
        try {
            DriverAction.click(request_access);
            DriverAction.waitSec(2);
            DriverAction.click(dropdown_of_request);
            DriverAction.waitSec(2);
            DriverAction.click(inputBox_of_request2);
            DriverAction.waitSec(2);
            DriverAction.click(select_access_request);
            DriverAction.waitSec(2);
            DriverAction.click(admin_select);
            DriverAction.waitSec(2);
            DriverAction.click(request_access_btn);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "arpit.mishra : You already have Admin access for TEST-PROJECT";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Request has been send Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project has been created on grid$")
    public void project_created_onGrid() throws Exception {
        try {
            DriverAction.click(create_project);
            DriverAction.waitSec(2);
            DriverAction.click(project_name_create_project);
            DriverAction.waitSec(1);
            DriverAction.typeText(project_name_create_project, projectNames);
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("New project name", "New Project name is: " + projectNames, STATUS.INFO);
            DriverAction.click(enviro_create_project);
            DriverAction.waitSec(1);
            DriverAction.typeText(enviro_create_project, projectNames);
            DriverAction.waitSec(1);
            DriverManager.getWebDriver().findElement(enviro_create_project).sendKeys(Keys.ENTER);
            DriverAction.waitSec(1);
            DriverAction.click(textArea);
            DriverAction.waitSec(1);
            DriverAction.typeText(textArea, projectNames);
            DriverAction.waitSec(1);
            GemTestReporter.addTestStep("Description", "Description is: " + projectNames, STATUS.INFO);
            DriverAction.click(create_button_Admin);
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Project is created Successfully !!";
            STATUS status;
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Project is created Successfully !! Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project is created Successfully !! Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
            DriverAction.doubleClick(Locators.sno);
            DriverAction.waitSec(4);
            String project_name = DriverAction.getElementText(project_name_Admin);
            if (projectNames.equals(project_name)) {
                GemTestReporter.addTestStep("Project has been added or not?", "Successful<br>Expected Text: " + projectNames + "<br>Actual Text: " + project_name, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project has been added or not?", "Unsuccessful<br>Expected Text: " + projectNames + "<br>Actual Text: " + project_name, STATUS.FAIL);
            }
            String description = DriverAction.getElementText(project_desc_Admin);
            if (projectNames.equals(description)) {
                GemTestReporter.addTestStep("Description has been added or not?", "Successful<br>Expected Text: " + projectNames + "<br>Actual Text: " + description, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Description has been added or not?", "Unsuccessful<br>Expected Text: " + projectNames + "<br>Actual Text: " + description, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project has been not created on grid$")
    public void project_not_created_admin() throws Exception {
        try {
            DriverAction.click(create_project);
            DriverAction.waitSec(2);
            DriverAction.click(project_name_create_project);
            DriverAction.waitSec(1);
            DriverAction.typeText(project_name_create_project, "TEST-PROJECT");
            DriverAction.waitSec(2);
            DriverAction.click(enviro_create_project);
            DriverAction.waitSec(1);
            DriverAction.typeText(enviro_create_project, "TEST-PROJECT");
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.sendKeys(Keys.ENTER).build().perform();
            DriverAction.waitSec(2);
            DriverAction.click(textArea);
            DriverAction.waitSec(1);
            DriverAction.typeText(textArea, "TEST-PROJECT");
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Description", "Description is: TEST-PROJECT", STATUS.INFO);
            DriverAction.click(create_button_Admin);
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Project with this name is already exists";
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Project with this name is already exists Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Project with this name is already exists Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project has never been not created on grid$")
    public void project_not_created_adminnn() throws Exception {
        try {
            DriverAction.click(create_project);
            DriverAction.waitSec(2);
            DriverAction.click(create_button_Admin);
            DriverAction.waitSec(2);
            String s3 = DriverAction.getElementText(Alert_admin1);
            String s2 = "Please fill out all Mandatory Fields!";
            if (s3.equals(s2)) {
                GemTestReporter.addTestStep("Please fill out all Mandatory Fields! Alert Validation", "Successful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Please fill out all Mandatory Fields! Alert Validation", "Unsuccessful<br>Expected Text: " + s2 + "<br>Actual Text: " + s3, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the project name for admin screen is getting modified$")
    public void admin_screen_project_name_mod() throws Exception {
        try {
//            DriverAction.doubleClick(Locators.sno);
//            DriverAction.waitSec(2);
            String project_name_before = DriverAction.getElementText(project_name_Admin);
            GemTestReporter.addTestStep("Project name before modification", "Name is: " + project_name_before, STATUS.INFO, DriverAction.takeSnapShot());
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverAction.getElement(edit_project_details2));
            actions.click();
            actions.perform();
            DriverAction.waitSec(2);
            DriverAction.click(project_name_create_project);
            DriverAction.waitSec(1);
            DriverAction.clearText(project_name_create_project);
            DriverAction.waitSec(2);
            String s = "TESTER_" + Math.random();
            DriverAction.typeText(project_name_create_project, s);
            DriverAction.waitSec(2);
            DriverAction.click(save_admin_button);
            DriverAction.waitSec(4);
//            String s3 = DriverAction.getElementText(Alert_admin1);
//            String project_alert = "Project is updated Successfully !!";
//            if (s3.equals(project_alert)) {
//                GemTestReporter.addTestStep("Project is updated Successfully !! Alert Validation", "Successful<br>Expected Text: " + project_alert + "<br>Actual Text: " + s3, STATUS.PASS);
//            } else {
//                GemTestReporter.addTestStep("Project is updated Successfully !! Alert Validation", "Unsuccessful<br>Expected Text: " + project_alert + "<br>Actual Text: " + s3, STATUS.FAIL);
//            }
            String new_project_name = DriverAction.getElementText(project_name_Admin);
            GemTestReporter.addTestStep("Project name after modification", "Name is: " + new_project_name, STATUS.INFO);
            if (new_project_name.equals(s)) {
                GemTestReporter.addTestStep("Modified project name Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_project_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Modified project name Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_project_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate the description is getting modified$")
    public void desc_mod_admin() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno);
            DriverAction.waitSec(2);
            String description_nm_bf4 = DriverAction.getElementText(desc_name_admin);
            GemTestReporter.addTestStep("Description before modification", "Description is: " + description_nm_bf4, STATUS.INFO, DriverAction.takeSnapShot());
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverAction.getElement(edit_project_details2));
            actions.click().build().perform();
            DriverAction.waitSec(2);
            DriverAction.click(textArea);
            DriverAction.waitSec(2);
            DriverAction.clearText(textArea);
            DriverAction.waitSec(2);
            String s = "TESTER_" + Math.random();
            DriverAction.typeText(textArea, s);
            DriverAction.waitSec(2);
            DriverAction.click(save_admin_button);
            DriverAction.waitSec(4);
//            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(edit_details_dialog));
            String new_desc_name = DriverAction.getElementText(desc_name_admin);
            GemTestReporter.addTestStep("Description after modification", "Description is: " + new_desc_name, STATUS.INFO, DriverAction.takeSnapShot());
            if (new_desc_name.equals(s)) {
                GemTestReporter.addTestStep("Modified Description Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_desc_name, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Modified Description Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + new_desc_name, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Then("^validate when user clicks on no$")
    public void admin_del_no() throws Exception {
        try {
            DriverAction.doubleClick(Locators.sno);
            DriverAction.waitSec(2);
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(delete_no_btn);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^validate when user clicks on yes$")
    public void admin_del_yes() throws Exception {
        try {
            String project_name = DriverAction.getElementText(project_name_Admin);
            GemTestReporter.addTestStep("Project which is being deleted", "Name is: " + project_name, STATUS.INFO, DriverAction.takeSnapShot());
            Actions actions = new Actions(DriverManager.getWebDriver());
            actions.moveToElement(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            actions.click(DriverManager.getWebDriver().findElement(delete_project2)).build().perform();
            DriverAction.waitSec(2);
            GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(delete_yes_btn);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String s = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            String s2 = "Project has been deleted temporarily and moved to Recycle Bin.";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Project has been deleted temporarily and moved to Recycle Bin Alert Validation", "Expected Text: " + s2 + "<br>Actual Text: " + s, status);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @When("^User (.*) is added to project$")
    public void addUser_UserAccess(String username) throws Exception {
        project_created_onGrid();
        DriverAction.click(edit_access2);
        DriverAction.click(addUser_project);
        DriverAction.click(adduser_select);
        DriverAction.typeText(addUser_search, username);
        DriverAction.click(add_user);
        DriverAction.click(adduser_select);
        DriverAction.click(select_role_empty);
        DriverAction.click(select_role);
        DriverAction.click(adduser_btn);
    }
    @Then("^Validate user (.*) is added$")
    public void validate_useradded(String username) {
        boolean flag = false;
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
        if ("User Added!".equals(DriverAction.getElementText(Alert_admin1))) {
            DriverAction.click(changerole_tab);
            List<String> users = DriverAction.getElementsText(users_list);
            System.out.println(users);
            for (int i = 0; i < users.size(); i++) {
                System.out.println(username);
                System.out.println(users.get(i));
                if (users.get(i).contains("geco-maulik")) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                GemTestReporter.addTestStep("Validate user is added", "User added successfully", STATUS.PASS);
            } else
                GemTestReporter.addTestStep("Validate user is added", "Failed to add user but alert for user added appeared", STATUS.FAIL);
        } else {
            GemTestReporter.addTestStep("Validate user is added", "Failed to add user", STATUS.FAIL);
        }
    }

    @Then("^Validate delete user (.*) function$")
    public void deleteUser(String username) {
        DriverAction.doubleClick(Locators.sno);
        DriverAction.waitSec(2);
        DriverAction.click(edit_access2);
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(delete_user2));
        Actions actions = new Actions(DriverManager.getWebDriver());
        actions.moveToElement(DriverAction.getElement(delete_user2));
        actions.click();
        actions.perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
        if ("User removed !".equals(DriverAction.getElementText(Alert_admin1))) {
            boolean flag = false;
            List<String> users = DriverAction.getElementsText(users_list);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).contains(username)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                GemTestReporter.addTestStep("Validate user is deleted", "User deleted successfully", STATUS.PASS);
            } else
                GemTestReporter.addTestStep("Validate user is deleted", "Failed to delete user but alert for user deletion appeared", STATUS.FAIL);
        } else {
            GemTestReporter.addTestStep("Validate user is deleted", "Failed to delete user", STATUS.FAIL);
        }
    }

    @Given("^click on signup$")
    public void signup() {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.signup);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^enter credentials of new user$")
    public void newUser_signup() throws Exception {
        try {
            String firstName = "QA" + Math.random();
            DriverAction.typeText(Locators.first_name, firstName);
            DriverAction.waitSec(2);
            DriverAction.typeText(Locators.last_name, firstName);
            DriverAction.waitSec(2);
            String userName = "QA" + Math.random();
            DriverAction.typeText(Locators.user_name, userName);
            DriverAction.waitSec(2);
            String email = "qa@" + Math.random() + ".com";
            DriverAction.typeText(Locators.email, email);
            String pass = "Avani0001";
            DriverAction.typeText(Locators.password, pass);
            DriverAction.waitSec(2);
            DriverAction.typeText(Locators.confirm_pass, pass);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.register_button);
            DriverAction.waitSec(5);
            DriverAction.click(select_company_name);
            DriverAction.waitSec(2);
//            String company = "Gemini" + Math.random();
            DriverAction.typeText(select_company_name, company);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.register_button);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^login as super-admin again$")
    public void loginAsSuperAgain() throws Exception {
        try {
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(4);
            DriverAction.click(super_admin);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify the company is created$")
    public void superAdminCompanyVerify() throws Exception {
        try {
            DriverAction.doubleClick(sno);

            DriverAction.waitSec(2);
            String newCompany = DriverAction.getElementText(company_new_name);
            System.out.println("New name is: " + newCompany);
            System.out.println("Company Is: " + company);
            if (company.equalsIgnoreCase(newCompany)) {
                GemTestReporter.addTestStep("Company created validation", "Successful<br>Expected Company Name: " + newCompany + "<br>Actual Company Name: " + company, STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Company created validation", "Unsuccessful<br>Expected Company Name: " + newCompany + "<br>Actual Company Name: " + company, STATUS.FAIL);
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("^validate super-admin is not present$")
    public void superAdminIsPresent() throws Exception {
        try {
            global2();
            List<WebElement> buttons = DriverManager.getWebDriver().findElements(By.xpath("//div[@class=\"icon-text col-md-9 col-sm-12 text-md-start text-center\"]"));
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().contains("Super Admin")) {
                    GemTestReporter.addTestStep("Super Admin isDisplayed?", "Super Admin is Displayed", STATUS.FAIL, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Super Admin isDisplayed?", "Super Admin is not Displayed", STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
//        GemTestReporter.addTestStep("exception","Exception",STATUS.INFO);
    }
    @Then("^verify it has been marked as verified$")
    public void verified_superAdmin() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(tick_option)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Green tick", "Successfully : Clicked on Green tick", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String alert = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            System.out.println("ALERT: " + alert);
            String expectedAlert = company + " is now verified!";
            System.out.println("COMPANY: " + expectedAlert);
            if (alert.equalsIgnoreCase(expectedAlert)) {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Successful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Unsuccessful<br>Expected Text: " + expectedAlert + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            String companyVerify = DriverAction.getElementText(verify_confirmation);
            DriverAction.waitSec(1);
            if (companyVerify.equals("VERIFIED")) {
                GemTestReporter.addTestStep("Company VERIFIED validation", "Successful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + companyVerify, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company VERIFIED validation", "Unsuccessful<br>Expected Company Name: " + companyVerify + "<br>Actual Text: " + "UNVERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify company has been marked as unverified$")
    public void unverified_superAdmin() throws Exception {
        try {
            DriverAction.doubleClick(sno);
            DriverAction.waitSec(2);
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(unlink_option)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(unlink_option)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Ban", "Successfully : Clicked on Ban", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(Alert_admin2));
            String alert = DriverAction.getElement(Alert_admin2).getAttribute("innerHTML");
            System.out.println("ALERT: " + alert);
            if (alert.contains("is now unverified!")) {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company is now verified Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
//            String companyVerify = DriverAction.getElementText(unverify_confirmation);
//            System.out.println("status: " + companyVerify);
//            DriverAction.waitSec(3);
//            if (companyVerify.equals("UNVERIFIED")) {
//                GemTestReporter.addTestStep("Company UNVERIFIED validation", "Successful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + companyVerify, STATUS.PASS, DriverAction.takeSnapShot());
//            } else {
//                GemTestReporter.addTestStep("Company UNVERIFIED validation", "Unsuccessful<br>Expected Text: " + companyVerify + "<br>Actual Text: " + "VERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
//            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^validate total count of company$")
    public void superAdminCompany() throws Exception {
        try {
            DriverAction.doubleClick(sno);
            String companyCount = DriverAction.getElementText(companyCountSuper);
            DriverAction.pageScroll(1000, 1000);
            DriverAction.waitSec(2);
            String s = DriverAction.getElementText(company_Count);
            String sub = s.substring(6, 9);
            if (companyCount.equalsIgnoreCase(sub)) {
                GemTestReporter.addTestStep("Company count validation", "Successful<br>Expected Text: " + companyCount + "<br>Actual Text: " + sub, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company count validation", "Successful<br>Expected Text: " + companyCount + "<br>Actual Text: " + sub, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Given("^login as super-admin$")
    public void loginAsSuper() throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.logIn);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(4);
            DriverAction.click(super_admin);
            DriverAction.waitSec(6);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Check if page switcher is enabled$")
    public void changingArrows() throws Exception {
        try {
            DriverAction.pageScroll(1000, 1000);
            if (DriverAction.getElement(lastPageSuperAdmin).isEnabled() && DriverAction.getElement(nextPageSuperAdmin).isEnabled()) {
                GemTestReporter.addTestStep("Next Page button and Last Page button", "Next Page button and Last Page button are Enabled", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Next Page button and Last Page button", "Next Page button and Last Page button are not Enabled", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^click on > and < and >> and <<$")
    public void nextPageSuper() throws Exception {
        try {
            DriverAction.click(nextPageSuperAdmin);
            DriverAction.waitSec(2);
            DriverAction.click(lastPageSuperAdmin2);
            DriverAction.waitSec(2);
            DriverAction.click(nextPageSuperAdmin);
            DriverAction.waitSec(2);
            DriverAction.click(nextPageSuperAdmin2);
            DriverAction.waitSec(2);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^From bottom right of the table change number of companies visible on screen$")
    public void changeCompanyNumberSuper() throws Exception {
        try {
            WebElement element = DriverManager.getWebDriver().findElement(scroll_upto);
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            DriverAction.waitSec(2);
            DriverAction.click(select_after);
            DriverAction.waitSec(1);
            DriverAction.click(select_10);
            DriverAction.waitSec(1);
            String value = DriverAction.getElementText(valueOf_10);
            DriverAction.waitSec(1);
            String value2 = DriverAction.getElementText(actual_valueOf_10);
            if (value.equalsIgnoreCase(value2)) {
                GemTestReporter.addTestStep("Number of companies visible on page check", "Successful<br>Expected Text: " + value2 + "<br>Actual Text: " + value, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Number of companies visible on page check", "Unsuccessful<br>Expected Text: " + value2 + "<br>Actual Text: " + value, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }
    @Then("^check sorting of super-admin page$")
    public void super_adminSorting() throws Exception {
        DriverAction.doubleClick(sno);
        DriverAction.waitSec(1);
        String num = DriverAction.getElementText(total_superAdmin2);
        DriverAction.pageScroll(1000, 1000);
        DriverAction.waitSec(3);
        String s = DriverAction.getElementText(total_superAdmin);
        String sub = s.substring(6, 9);
        if (num.equalsIgnoreCase(sub)) {
            GemTestReporter.addTestStep("Sorting check", "Successful<br>Expected Text: " + sub + "<br>Actual Text: " + num, STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Sorting check", "Unsuccessful<br>Expected Text: " + sub + "<br>Actual Text: " + num, STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }
    @Then("^Check select all option from dropdown$")
    public void selectAllDropDwn() throws Exception {
        try {
            for (int i = 1; i <= 3; i++) {
                String xPathWithVariable = "(//button[@aria-label=\"Filter\"])" + "[" + i + "]";
                DriverAction.click(By.xpath(xPathWithVariable));
                DriverAction.waitSec(2);
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^register a company$")
    public void superCompanyRegitser() throws Exception {
        try {
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            str = "company" + Math.random();
            DriverAction.typeText(company_name_super, str);
            DriverAction.waitSec(2);
            DriverAction.click(domain_name);
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, str + ".com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin);
            DriverAction.waitSec(3);
            DriverAction.doubleClick(sno);
            DriverAction.waitSec(2);
            String s1 = DriverAction.getElementText(new_compName);
            if (str.equalsIgnoreCase(s1)) {
                GemTestReporter.addTestStep("Resgitered company validation", "Successful<br>Expected Text: " + str + "<br>Actual Text: " + s1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Resgitered company validation", "Unsuccessful<br>Expected Text: " + str + "<br>Actual Text: " + s1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter a company name that already exists (.+)$")
    public void companyAlready_Exist(String alert1) throws Exception {
        try {
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            DriverAction.typeText(company_name_super, "apple");
            DriverAction.waitSec(3);
            String alert = DriverAction.getElementText(companyName_Alert);
            System.out.println("ALERT: " + alert);
            DriverAction.waitSec(3);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Company Name Already Exists Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company Name Already Exists Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter a domain name that already exists (.+)$")
    public void domainAlready_Exist(String alert1) throws Exception {
        try {
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            String s = "company" + Math.random();
            DriverAction.typeText(company_name_super, s);
            DriverAction.waitSec(2);
            DriverAction.click(domain_name);
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, "geminisolutions.com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String alert = DriverAction.getElementText(Alert_admin1);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Domain already exists with another company Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Domain already exists with another company Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^Enter empty fields (.+)$")
    public void mandatoryField(String alert1) throws Exception {
        try {
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String alert = DriverAction.getElementText(Alert_admin1);
            if (alert.equalsIgnoreCase(alert1)) {
                GemTestReporter.addTestStep("Fill out the mandatory fields! Alert Validation", "Successful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Fill out the mandatory fields! Alert Validation", "Unsuccessful<br>Expected Text: " + alert + "<br>Actual Text: " + alert1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^delete company admin$")
    public void deleteCompanyAdmin() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            String name = DriverAction.getElementText(nameBlock);
            List<String> xpath = DriverAction.getElementsText(unlink);
            String xPathWithVariable = "(//*[local-name()='svg' and @data-icon=\"ban\"]/*[local-name()='path'])" + "[" + xpath.size() + "]";
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Click on Unlink", "Successfully : Clicked on Unlink", STATUS.PASS, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Admin removed", "Admin removed is: " + name, STATUS.INFO);
            List<String> list = DriverAction.getElementsText(listBlock);
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(name)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                GemTestReporter.addTestStep("Admin removed validation", "Admin is NOT present in the list", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Admin removed validation", "Admin is present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(close_btn_super);
            DriverAction.waitSec(4);
            Actions action4 = new Actions(DriverManager.getWebDriver());
            action4.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action4.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(addAdmins);
            DriverAction.waitSec(2);
            DriverAction.click(selectUsersToAdd);
            DriverAction.waitSec(2);
            DriverAction.click(textAddName);
            DriverAction.waitSec(2);
            DriverAction.typeText(textAddName, "Ana Nya");
            DriverAction.waitSec(2);
            DriverAction.click(userToBeAdded);
            DriverAction.waitSec(2);
            DriverAction.click(addAdmins);
            DriverAction.waitSec(2);
            DriverAction.click(addAdmin_btn);
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^unverify company (.+)$")
    public void unverifyCompanySuperAdmin(String alert) throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            if (s.equalsIgnoreCase(alert)) {
                GemTestReporter.addTestStep("apple is now unverified! Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("apple is now unverified! Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            List<String> list = DriverAction.getElementsText(statusSuperAdmin);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("UNVERIFIED")) {
                    GemTestReporter.addTestStep("Users blocked", "Status has been marked as UNVERIFIED", STATUS.PASS, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Users blocked", "Status has been marked as VERIFIED", STATUS.FAIL, DriverAction.takeSnapShot());
                }
            }
            DriverAction.click(close_btn_super);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^verify company again (.+)$")
    public void verifyCompanySuper(String alert) throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(link_Main)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(link_Main)).build().perform();
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            if (s.equalsIgnoreCase(alert)) {
                GemTestReporter.addTestStep("Company verified successfully! Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Company verified successfully! Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^unblock a user (.+)$")
    public void unblockUser(String alert) throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            WebElement element = DriverManager.getWebDriver().findElement(blocked_User);
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            DriverAction.waitSec(2);
            String name = DriverAction.getElementText(blockedUser_Name);
            GemTestReporter.addTestStep("Blocked user", "Blocked user name is: " + name, STATUS.INFO);
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(blocked_User)).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(blocked_User)).build().perform();
//            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Unblock user", "Successfully : Clicked on Unblock user", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            if (s.equalsIgnoreCase(alert)) {
                GemTestReporter.addTestStep("Users Unblocked Successfully Alert Validation", "Successful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Users Unblocked Successfully Alert Validation", "Unsuccessful<br>Expected Text: " + s + "<br>Actual Text: " + alert, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            ((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            DriverAction.waitSec(2);
            List<String> list = DriverAction.getElementsText(blockedUserList);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(name)) {
                    GemTestReporter.addTestStep("Added user", "Added user is there in blocked list", STATUS.FAIL, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Added user", "Added user is not there in blocked list", STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
            DriverAction.click(close_btn_super);
            DriverAction.waitSec(2);
            Actions action4 = new Actions(DriverManager.getWebDriver());
            action4.moveToElement(DriverManager.getWebDriver().findElement(profile)).build().perform();
            action4.click(DriverManager.getWebDriver().findElement(profile)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "apple_com");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Avani0001");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(4);
            DriverAction.click(admin);
            DriverAction.waitSec(5);
            DriverAction.click(myCompanyAdmin);
            DriverAction.waitSec(3);
            Actions action5 = new Actions(DriverManager.getWebDriver());
            action5.moveToElement(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
            action5.click(DriverManager.getWebDriver().findElement(unlink_Main)).build().perform();
            DriverAction.waitSec(4);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^check visibility of projects on admin screen$")
    public void checkVisibilityAdmin() throws Exception {
        try {
            DriverAction.waitSec(4);
            List<String> list = DriverAction.getElementsText(projectNameList);
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isEmpty()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                GemTestReporter.addTestStep("project of company is visible", "Projects of all the companies are visible with company names", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("project of company is visible", "Projects of all the companies are not visible with company names", STATUS.FAIL, DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^edit field in admin$")
    public void editFieldAdmin() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(pencil_opt)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(pencil_opt)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Edit project details", "Successfully : Clicked on Edit project details", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.clearText(desciption);
            DriverAction.waitSec(2);
            DriverAction.typeText(desciption, Math.random() + "desc");
            DriverAction.waitSec(2);
            DriverAction.click(save_admin_button);
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "Project is updated Successfully !!";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Project is updated Successfully! Alert Validation", "Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }


    @Then("^delete the same project$")
    public void deleteProjectAdmin() throws Exception {
        GemTestReporter.addTestStep("Project Name to be deleted", "Project to be deleted is: " + projectNames, STATUS.INFO);
        Actions action2 = new Actions(DriverManager.getWebDriver());
        action2.moveToElement(DriverManager.getWebDriver().findElement(trashCan)).build().perform();
        action2.click(DriverManager.getWebDriver().findElement(trashCan)).build().perform();
        DriverAction.waitSec(4);
        GemTestReporter.addTestStep("Click on Delete Project", "Successfully : Clicked on Delete Project", STATUS.PASS, DriverAction.takeSnapShot());
        Actions action3 = new Actions(DriverManager.getWebDriver());
        action3.moveToElement(DriverManager.getWebDriver().findElement(yes)).build().perform();
        action3.click(DriverManager.getWebDriver().findElement(yes)).build().perform();
        DriverAction.waitSec(4);
        List<String> list = DriverAction.getElementsText(projectNameList);
        System.out.println("LIST SIZE: " + list.size());
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(projectNames)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            GemTestReporter.addTestStep("Project has been deleted?", "Project has been deleted from the grid", STATUS.PASS, DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Project has been deleted?", "Project has not been deleted from the grid", STATUS.FAIL, DriverAction.takeSnapShot());

        }
    }

    @Given("^login as admin$")
    public void loginAsAdmin() throws Exception {
        try {
            DriverAction.waitSec(1);
            DriverAction.click(Locators.logIn);
            DriverAction.waitSec(2);
            DriverAction.click(Locators.username);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.username, "super-admin");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.passwordm);
            DriverAction.waitSec(1);
            DriverAction.typeText(Locators.passwordm, "Gemecosystem#1234@");
            DriverAction.waitSec(1);
            DriverAction.click(Locators.LoginButton);
            DriverAction.waitSec(4);
            DriverAction.click(admin);
            DriverAction.waitSec(3);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

    @Then("^unverify the company and create new company with same domain$")
    public void createdUnverifyCom() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(unverifyButton)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(unverifyButton)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on unverify company", "Successfully : Clicked on unverify company", STATUS.PASS, DriverAction.takeSnapShot());
            DriverAction.click(regitser_company);
            DriverAction.waitSec(2);
            DriverAction.typeText(company_name_super, str + "testdemo");
            DriverAction.waitSec(2);
            DriverAction.click(domain_name);
            DriverAction.waitSec(2);
            DriverAction.typeText(domain_name, str + ".com" + Keys.ENTER);
            DriverAction.waitSec(2);
            DriverAction.click(register_superAdmin);
            DriverAction.waitSec(3);
//            DriverAction.doubleClick(sno, "S_NO");
            DriverAction.waitSec(2);
            String s1 = DriverAction.getElementText(new_compName);
            String str1 = str + "testdemo";
            if (str1.equalsIgnoreCase(s1)) {
                GemTestReporter.addTestStep("Resgitered company validation", "Successful<br>Expected Text: " + str1 + "<br>Actual Text: " + s1, STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Resgitered company validation", "Unsuccessful<br>Expected Text: " + str1 + "<br>Actual Text: " + s1, STATUS.FAIL, DriverAction.takeSnapShot());
            }
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(tickmarkVerify)).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(tickmarkVerify)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on verify", "Successfully : Clicked on verify company", STATUS.PASS, DriverAction.takeSnapShot());
            WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(Alert_admin1));
            String s = DriverAction.getElementText(Alert_admin1);
            System.out.println("Alert: " + s);
            String s2 = "Domain is already present in another company !!";
            STATUS status;
            if (s.equals(s2)) {
                status = STATUS.PASS;
            } else {
                status = STATUS.FAIL;
            }
            GemTestReporter.addTestStep("Domain is already present in another company !! Alert Validation", "Expected Text: " + s + "<br>Actual Text: " + s2, status, DriverAction.takeSnapShot());
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^verify admins are present$")
    public void verifyAdminsOf() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            List<String> admins = DriverAction.getElementsText(adminsList);
            for (int i = 0; i < admins.size(); i++) {
                if (admins.get(i).isEmpty()) {
                    GemTestReporter.addTestStep("Company admins visibility", "Company admins are not visible", STATUS.FAIL, DriverAction.takeSnapShot());
                } else {
                    GemTestReporter.addTestStep("Company admins visibility", "Company admins are visible", STATUS.PASS, DriverAction.takeSnapShot());
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred", e);
            GemTestReporter.addTestStep("Error!!", "Something Wrong happened", STATUS.FAIL);
        }
    }

    @Then("^add company admin$")
    public void addcompAdmin() throws Exception {
        try {
            Actions action2 = new Actions(DriverManager.getWebDriver());
            action2.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action2.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            String name = DriverAction.getElementText(nameBlock);
            List<String> xpath = DriverAction.getElementsText(unlink);
            String xPathWithVariable = "(//*[local-name()='svg' and @data-icon=\"ban\"]/*[local-name()='path'])" + "[" + xpath.size() + "]";
            Actions action3 = new Actions(DriverManager.getWebDriver());
            action3.moveToElement(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
            action3.click(DriverManager.getWebDriver().findElement(By.xpath(xPathWithVariable))).build().perform();
            DriverAction.waitSec(4);
            GemTestReporter.addTestStep("Click on Action Edit", "Successfully : Clicked on Action Edit", STATUS.PASS, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Click on Unlink", "Successfully : Clicked on Unlink", STATUS.PASS, DriverAction.takeSnapShot());
            GemTestReporter.addTestStep("Admin removed", "Admin removed is: " + name, STATUS.INFO);
            List<String> list = DriverAction.getElementsText(listBlock);
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(name)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                GemTestReporter.addTestStep("Admin removed validation", "Admin is NOT present in the list", STATUS.PASS, DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Admin removed validation", "Admin is present in the list", STATUS.FAIL, DriverAction.takeSnapShot());
            }
            DriverAction.click(close_btn_super);
            DriverAction.waitSec(4);
            Actions action4 = new Actions(DriverManager.getWebDriver());
            action4.moveToElement(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            action4.click(DriverManager.getWebDriver().findElement(edit_pencil)).build().perform();
            DriverAction.waitSec(4);
            DriverAction.click(addAdmins);
            DriverAction.waitSec(2);
            DriverAction.click(selectUsersToAdd);
            DriverAction.waitSec(2);
            DriverAction.click(textAddName);
            DriverAction.waitSec(2);
            DriverAction.typeText(textAddName, "Ana Nya");
            DriverAction.waitSec(2);
            DriverAction.click(userToBeAdded);
            DriverAction.waitSec(2);
            DriverAction.click(addAdmins);
            DriverAction.waitSec(2);
            DriverAction.click(addAdmin_btn);
            DriverAction.waitSec(5);
        } catch (Exception e) {
            logger.info("An exception occurred!", e);
            GemTestReporter.addTestStep("ERROR", "SOME ERROR OCCURRED", STATUS.FAIL);
        }
    }

}
