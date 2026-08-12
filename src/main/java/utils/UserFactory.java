package utils;

import dto.User;

public class UserFactory {
    static User user;

    public static User positiveLoginUser() {
        user = User.builder()
                .username("test567@test.com")
                .password("Test567!")
                .build();
        return user;
    }
}
