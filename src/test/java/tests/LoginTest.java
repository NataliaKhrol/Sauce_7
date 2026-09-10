package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @DataProvider(name = "oioio")
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {"Standard_user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "oioio", priority = 1, enabled = false)
    public void incorrectDataLoginTest(User user, String errorMsg) {
        System.out.println("incorrectDataLoginTest is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, errorMsg, "Error text does not match");
    }

    @Test(description = "Проверка авторизации", priority = 2, invocationCount = 1)
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
