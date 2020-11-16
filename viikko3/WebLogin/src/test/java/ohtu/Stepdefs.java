package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }    

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
        public void validUNandPW(String username, String password) {
            createUser(username, password, password);
    }

    @Then("a new user is created")
    public void newUserCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("an invalid username {string} and password {string} and matching password confirmation are entered")
        public void invalidUNandPW(String username, String password) {
            createUser(username, password, password);
    }

    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void usernameTooShort() {
        pageHasContent("username should have at least 3 characters");
    }

    @When("a valid username {string} and an invalid password {string} and matching password confirmation are entered")
        public void invalidPW(String username, String password) {
            createUser(username, password, password);
    }

    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void passwordTooShort() {
        pageHasContent("password should have at least 8 characters");
    }

    @When("a valid username {string} and password {string} and nonmatching password confirmation {string} are entered")
    public void aValidUsernameAndPasswordAndPasswordConfirmationAreEntered(String username, String password, String confirm) {
        createUser(username, password, confirm);
    }

   @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void passwordNoMatch() {
        pageHasContent("password and password confirmation do not match");
    }

    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    

        @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameAndCorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 

    private void createUser(String username, String password, String confirm) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirm);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }     
}
