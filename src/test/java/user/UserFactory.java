package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }

    public static User withLockedPermission() {
        return new User(PropertyReader.getProperty("saucedemmo.locked.user"),
                PropertyReader.getProperty("saucedemmo.password"));
    }
}
