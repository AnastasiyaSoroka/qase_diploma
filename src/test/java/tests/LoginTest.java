package tests;

import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Check Error message if Username is empty")
    public void checkErrorWithIncorrectPassword() {
       loginPage
               .openPage()
               .isPageOpened()
               .attemptLogin(USERNAME, PASSWORD);

       projectsListPage.isPageOpened();
    }
}
