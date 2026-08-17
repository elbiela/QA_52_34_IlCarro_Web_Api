package utils;

import dto.User;
import net.datafaker.Faker;

import static utils.PropertiesReader.*;

public class UserFactory {
    static User user;
    static Faker faker = new Faker();

    public static User positiveLoginUser() {
        user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        return user;
    }

    public static User positiveRegisterUser() {
        user = User.builder()
                .username(faker.internet().emailAddress())
                .password(getProperty("base.properties", "password"))
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
        return user;
    }
}
