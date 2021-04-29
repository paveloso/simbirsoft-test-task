package ru.simbirsoft.simbirsofttest.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArgValidatorTest {

    private ArgValidator argValidator;
    private String resultNotNull;

    @BeforeEach
    void init() {
        argValidator = new ArgValidator();
        resultNotNull = "error";
    }

    @Test
    void validateArgsSizeOne() {
        String[] args = {"test1"};
        String result = argValidator.validate(args);

        Assertions.assertEquals(!resultNotNull.isEmpty(), !result.isEmpty());
    }

    @Test
    void validateArgsSizeThree() {
        String[] args = {"test1", "test2", "test3"};
        String result = argValidator.validate(args);

        Assertions.assertEquals(!resultNotNull.isEmpty(), !result.isEmpty());
    }

    @Test
    void validateArgsUrlNoProtocol() {
        String[] args = {"www.test.com", "1"};
        String result = argValidator.validate(args);

        Assertions.assertEquals(!resultNotNull.isEmpty(), !result.isEmpty());
    }

    @Test
    void validateArgsSecondArgMustBeDigit() {
        String[] args = {"https://www.test.com", "test1"};
        String result = argValidator.validate(args);

        Assertions.assertEquals(!resultNotNull.isEmpty(), !result.isEmpty());
    }

    @Test
    void validateArgsAllValid() {
        String[] args = {"https://www.test.com", "1"};
        String result = argValidator.validate(args);

        Assertions.assertEquals("".isEmpty(), result.isEmpty());
    }
}