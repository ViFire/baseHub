package api.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHelperTest {

    private String clearPassword = "System32!";

    @Test
    void validatePassword() {
        String encryptedPassword = PasswordHelper.encryptPassword(clearPassword);

        for (int i = 0; i<20;i++) {
            System.out.println(PasswordHelper.encryptPassword(clearPassword));
        }


        assertEquals("100:e855f46024cf7ba368037ae4d9c49422:d4653f60f47077195d0a6afff0b6b746f4ec491f92b0ad081742daafe9e0559a", encryptedPassword);
    }
}