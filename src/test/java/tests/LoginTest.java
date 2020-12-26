package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("Login")
public class LoginTest extends BaseTest {

    @DataProvider(name = "incorrectLoginData")
    public Object[][] incorrectLoginData() {
        return new Object[][]{{USERNAME, "sdfsdf"}, {"asdasd@mail", PASSWORD}};
    }

    @Test(description = "Check Error message with incorrect credentials", dataProvider = "incorrectLoginData")
    public void checkErrorWithIncorrectCredentials(String username, String password) {
        String errorMessage = loginSteps.checkInvalidCredentialsMessage(username,password);
        Assert.assertEquals(errorMessage,"These credentials do not match our records.", "Error message is not correct");
    }
}
