package com.example.eventme;

import androidx.core.util.PatternsCompat;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

    // Mock function for functionality starting at line 50 in Login.java
    public boolean isValidEmailAndPassword(String email, String password) {
        // This is what TextUtils.isEmpty() does
        if(email.isEmpty() || email == null) {
            return false;
        }
        else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        // This is what TextUtils.isEmpty() does
        else if(password.isEmpty() || password == null) {
            return false;
        }

        return true;
    }

    @Test
    public void bothEmailAndPasswordAreValid() {
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.com", "password123"));
    }

    @Test
    public void bothEmailAndPasswordAreEmpty() {
        assertFalse(isValidEmailAndPassword("", ""));
    }

    @Test
    public void emailIsEmpty() {
        assertFalse(isValidEmailAndPassword("", "password"));
    }

    @Test
    public void passwordIsEmpty() {
        assertFalse(isValidEmailAndPassword("ericyao@gmail.com", ""));
    }

    @Test
    public void emailDoesNotHaveAnAtSign() {
        assertFalse(isValidEmailAndPassword("kgvelasqgmail.com", "passwordUSC"));
    }

    @Test
    public void emailDoesNotHaveDomainExtension() {
        assertFalse(isValidEmailAndPassword("kgvelasq@gmail", "validpassword"));
    }

    @Test
    public void emailDoesNotHaveAUsername() {
        assertFalse(isValidEmailAndPassword("@gmail.com", "isvalidpassword"));
    }

    @Test
    public void emailDoesNotHaveADomain() {
        assertFalse(isValidEmailAndPassword("aristousc@", "anotherpassword"));
    }

    @Test
    public void emailCanHaveOtherDomainExtension() {
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.net", "password123"));
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.org", "password123"));
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.com", "password123"));
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.gov", "password123"));
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.mil", "password123"));
        assertTrue(isValidEmailAndPassword("kgvelasq@gmail.edu", "password123"));
    }
}