package com.example.eventme;

import junit.framework.TestCase;

import static org.junit.Assert.*;

import androidx.core.util.PatternsCompat;

import org.junit.Test;


public class RegisterActivityTest {
    //Mock function for registration details check in RegisterActivity.java
    public boolean isValidRegistration(String name, String email, String DOB, String password, String confirmedPassword) {
        if(name.isEmpty() || name == null) {
            return false;
        }
        else if(email.isEmpty() || email == null) {
            return false;
        }
        else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }
        else if(DOB.isEmpty() || DOB == null) {
            return false;
        }
        else if(password.isEmpty() || password == null) {
            return false;
        }
        else if(password.length() < 5) {
            return false;
        }
        else if(confirmedPassword.isEmpty() || confirmedPassword == null) {
            return false;
        }
        else if(confirmedPassword.compareTo(password) != 0) {
            return false;
        }

        return true;
    }

    @Test
    public void validRegistration() {
        assertTrue(isValidRegistration("Kenneth", "kgvelasq@gmail.com", "03/30/2005", "password", "password"));
    }

    @Test
    public void passwordIsWeak() {
        assertFalse(isValidRegistration("Kenny", "kgvelasq@gmail.com", "07/23/2003", "pass", "pass"));
    }

    @Test
    public void confirmedPasswordDoesNotMatch() {
        assertFalse(isValidRegistration("Aristo", "aristo@gmail.com", "02/24/2007", "password", "notpassword"));
    }

    @Test
    public void nameIsEmpty() {
        assertFalse(isValidRegistration("", "kgvelasq@gmail.com", "07/23/2003", "password", "password"));
    }

    @Test
    public void passwordIsEmpty() {
        assertFalse(isValidRegistration("Eric", "ericyao@gmail.com", "07/23/2003", "", "password"));
    }

    @Test
    public void dobIsEmpty() {
        assertFalse(isValidRegistration("Aristo", "aristo@gmail.com", "", "password", "password"));
    }

    @Test
    public void emailIsEmpty() {
        assertFalse(isValidRegistration("Kenneth", "", "07/23/2003", "password", "password"));
    }

    @Test
    public void emailDoesNotHaveAt() {
        assertFalse(isValidRegistration("Kenny", "kgvelasqgmail.com", "07/23/2003", "password", "password"));
    }

    @Test
    public void emailIsNotComplete() {
        assertFalse(isValidRegistration("Kenny", "kgvelasq@", "07/23/2003", "password", "password"));
    }

    @Test
    public void everythingIsEmpty() {
        assertFalse(isValidRegistration("", "", "", "", ""));
    }
}