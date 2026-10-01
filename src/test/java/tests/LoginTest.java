package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;


@Epic("Личный кабинет пользователя")
@Feature("Профиль пользователя")
@Owner("Natalia, kaka@mms.ru")
public class LoginTest extends BaseTest {
    @DataProvider(name = "oioio")
    public Object[][] loginData() {
        return new Object[][]{
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {new User("Standard_user", "secret_sauce"), "Epic sadface: Username and password do not match any user in this service"},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {new User("standard_user", ""), "Epic ввввsadface: Password is required"}
        };
    }

    @Story("Некорректный логин")
    @Test(dataProvider = "oioio", priority = 1, dependsOnMethods = "correctUserTest", enabled = true, description = "Human-readable test name")
    public void incorrectDataLoginTest(User user, String errorMsg) {
        System.out.println("incorrectDataLoginTest is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);

        boolean isVisible = loginPage.isErrorVisible();
        String errorText = loginPage.getErrorText();

        assertTrue(isVisible, "Error message does not appear");
        assertEquals(errorText, errorMsg, "Error text does not match");
    }

    @Story("Корректный логин")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Sauce_7")
    @Issue("SaucedemoIfat")
    @Test(description = "Проверка авторизации", priority = 2, invocationCount = 1)
    public void correctUserTest() {
        System.out.println("correctUserTest is running in thread: " + Thread.currentThread().getId());

        loginPage
                .open()
                .login(withAdminPermission());

        boolean pageTitleVisible = productsPage.isPageTitleVisible();
        assertTrue(pageTitleVisible);
        assertEquals(productsPage.getPageTitle(), PRODUCTS.getDisplayName());
    }
}
