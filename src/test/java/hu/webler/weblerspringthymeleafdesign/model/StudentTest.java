package hu.webler.weblerspringthymeleafdesign.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit test of Student Class")
class StudentTest {

    @Test
    @DisplayName("Test compareTo() method in Student Class")
    void testCompareTo_SameLastName() {
        // Arrange
        Student student1 = new Student("John", null,  "Doe", "john@john.co.uk");
        Student student2 = new Student("Jane", "Marry", "Doe", "jane@gmail.com");
        int expected = 0;

        // Act
        int actual = student1.compareTo(student2);
        boolean isTrue = expected == actual;

        // Assert
        assertEquals(expected, actual, "Expecting 0 because the last name are the same!"); // Expecting 0 because last names are the same
        assertNotEquals(1, student1.compareTo(student2)); // Expecting 0 because last names are the same
        assertTrue(isTrue);
    }
}