package api.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHelperTest {

    private String clearPassword = "System32!";

    @Test
    void validatePassword() {
        String encryptedPassword = PasswordHelper.encryptPassword(clearPassword);

        boolean test = PasswordHelper.validatePassword(clearPassword, "100:8e850a82f3fa6d5dc3d48266d0d90d43:7e698cc8fc125849e7cd4dfd9ea4fb9acc6ba2290e4338c4ae0b54f36dc88316");


        assertEquals("100:e855f46024cf7ba368037ae4d9c49422:d4653f60f47077195d0a6afff0b6b746f4ec491f92b0ad081742daafe9e0559a", encryptedPassword);
    }
}