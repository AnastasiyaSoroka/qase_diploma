package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @DataProvider(name = "incorrectLoginData")
    public Object[][] incorrectLoginData() {
        return new Object[][]{{USERNAME, "sdfsdf"}, {"asdasd@mail", PASSWORD}};
    }

    @Test(description = "Check Error message with incorrect credentials", dataProvider = "incorrectLoginData")
    public void checkErrorWithIncorrectCredentials(String username, String password) {
        String errorMessage = loginPage
                .openPage()
                .isPageOpened()
                .attemptLogin(username, password).getErrorText();
        //   projectsListPage.isPageOpened();
    }
}
