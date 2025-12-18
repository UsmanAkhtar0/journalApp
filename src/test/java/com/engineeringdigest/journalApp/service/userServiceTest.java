package com.engineeringdigest.journalApp.service;

import com.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest
public class userServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void sampleTest() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFindByUserName() {
        assertNotNull(userRepository.findByUsername("one"));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 2, 4",
            "2, 3, 5",
            "2, 4, 6",
    })
    public void testAdd(int a, int b, int expected) {
        assertEquals(expected, a + b, "failed for: " + expected);
    }
}
