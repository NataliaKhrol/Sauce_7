package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameInput = By.cssSelector(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordInput = By.cssSelector(DATA_TEST_PATTERN.formatted("password"));
    // private final By passwordInput2 = By.cssSelector(String.format(DATA_TEST_PATTERN, "password"));
    private final By loginBtn = By.id("login-button");
    private final By error = By.xpath("//h3[@data-test='error']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем соответствующий браузер")
    public LoginPage open() {
        driver.get(BASE_URL);

        return this;
    }

    public void open(String url) {
        driver.get(BASE_URL);
    }

    public void open(String url, int age) {
        driver.get(BASE_URL);
    }

    public void open(int age, String url) {
        driver.get(BASE_URL);
    }

    @Step("Авторизация под кредами пользователя")
    public void login(User user) {

        driver.findElement(loginBtn).click();

    }

    @Step("Заполняем поле логина {user}")
    public LoginPage fillLoginInput(String user) {
        driver.findElement(usernameInput).sendKeys(user);
        return this;
    }

    @Step("Заполняем поле пароля {password}")
    public LoginPage fillPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Проверяем, что сообщение об ошибке отображается")
    public boolean isErrorVisible() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверяем, текст сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(error).getText();
    }
}
