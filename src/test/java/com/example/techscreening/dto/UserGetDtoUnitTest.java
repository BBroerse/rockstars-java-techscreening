package com.example.techscreening.dto;

import com.example.techscreening.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetDtoUnitTest {

    @Test
    public void whenConvertUserEntityToUserGetDto_thenCorrect() {
        User user = new User();
        user.setEmail("basbroerse@hotmail.com");
        user.setFirstname("Bas");
        user.setLastname("Broerse");
        user.setPassword("SuperSecure2021!");

        UserGetDto userDto = new UserGetDto(user);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getFirstname(), user.getFirstname());
        assertEquals(userDto.getLastname(), user.getLastname());
    }

    @Test
    public void whenConvertUserDtoToUserEntity_thenCorrect() {
        UserGetDto userDto = new UserGetDto();
        userDto.setEmail("basbroerse@hotmail.com");
        userDto.setFirstname("Bas");
        userDto.setLastname("Broerse");

        User user = userDto.toEntity();
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstname(), userDto.getFirstname());
        assertEquals(user.getLastname(), userDto.getLastname());
    }

}
