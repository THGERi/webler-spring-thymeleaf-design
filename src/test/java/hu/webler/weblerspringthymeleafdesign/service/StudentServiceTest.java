package hu.webler.weblerspringthymeleafdesign.service;

import hu.webler.weblerspringthymeleafdesign.bootstrap.DataInitializer;
import hu.webler.weblerspringthymeleafdesign.model.Student;
import hu.webler.weblerspringthymeleafdesign.util.Sorting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.RuleBasedCollator;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Unit test of StudentService")
class StudentServiceTest {

    // https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea
    // https://junit.org/junit5/docs/current/user-guide/
    // https://java-design-patterns.com/patterns/arrange-act-assert/
    // https://www.baeldung.com/mockito-annotations
    // https://www.baeldung.com/mockito-junit-5-extension
    // Mock data, Dummy data, Test data, Fake data, Stub data, Test fixture (fixture data) and Spies <- check all these expression and differentiate them
    // Cucumber - https://cucumber.io/docs/guides/overview/
    // https://www.baeldung.com/cucumber-rest-api-testing
    // https://www.baeldung.com/cucumber-spring-integration
    // https://www.baeldung.com/cucumber-jvm-java
    // https://medium.com/codex/bdd-testing-with-cucumber-junit-5-fb5a1c4354f9
    // https://visilver.github.io/cucumber-spring-multiple-profiles.html

    private DataInitializer dataInitializerMock;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        dataInitializerMock = mock(DataInitializer.class);
        // Customize the behavior of dataInitializerMock as needed for each test case

        studentService = new StudentService(dataInitializerMock);
    }

    @Test()
    @DisplayName("Test getStudents() method")
    void testGetStudents_shouldReturnNonEmptyListOfStudents() { // naming convention: testMethodName_StateUnderTest_ExpectedBehavior instead of this: void testGetStudents()
        // Arrange
        List<Student> mockStudents = createTestList();

        when(dataInitializerMock.getSTUDENTS_ENGLISH()).thenReturn(mockStudents);

        // Act
        List<Student> students = studentService.getStudents();

        // Assert
        assertNotNull(students);
        assertEquals(2, students.size());
        assertEquals("John", students.get(0).getFirstName());
        assertEquals("Jane", students.get(1).getFirstName());
    }

    @Test
    @DisplayName("Test searchStudentsByEmail() method")
    void testSearchStudentsByEmail_ShouldReturnFilteredStudents_WhenSearchingByEmail() { // naming convention: testMethodName_StateUnderTest_ExpectedBehavior
        // Arrange
        List<Student> mockStudents = createTestList();

        when(dataInitializerMock.getSTUDENTS_ENGLISH()).thenReturn(mockStudents);

        String emailToSearch = "john@example.com";

        // Act
        List<Student> filteredStudents = studentService.searchStudentsByEmail(emailToSearch);

        // Assert
        assertNotNull(filteredStudents);
        assertEquals(1, filteredStudents.size());
        assertEquals("John", filteredStudents.get(0).getFirstName());
    }

    @Test
    @DisplayName("Test getStudentsHungarian() method")
    void testGetStudentsHungarian_shouldReturnSortedStudents() {
        // Arrange
        List<Student> students = createHungarianTestList();

        when(dataInitializerMock.getSTUDENTS_HUNGARIAN()).thenReturn(students);

        // Create a collator with Hungarian rules
        RuleBasedCollator hungarianCollator = Sorting.createHungarianCollator();

        // Act
        List<Student> sortedStudents = studentService.getStudentsHungarian();

        // Assert
        assertNotNull(sortedStudents);
        assertEquals(4, sortedStudents.size()); // Ensure all students are present in the result

        // Verify that the list is sorted by last name using the Hungarian collator
        assertTrue(isSortedByLastName(sortedStudents, hungarianCollator));
    }

    private boolean isSortedByLastName(List<Student> students, RuleBasedCollator collator) {
        for (int i = 0; i < students.size() - 1; i++) {
            Student current = students.get(i);
            Student next = students.get(i + 1);

            int comparisonResult = collator.compare(current.getLastName(), next.getLastName());
            if (comparisonResult > 0) {
                return false; // Not sorted in ascending order
            }
        }
        return true; // Sorted in ascending order
    }

    private List<Student> createHungarianTestList() {
        return List.of(
                new Student("John", null, "Doe", "testemail@test.hu"),
                new Student("Árpád", null, "Bálint", "testemail@test.org"),
                new Student("Zsuzsanna", null, "Nagy", "testemail@test.com"),
                new Student("Gábor", null, "Kovács", "testemail@test.co.uk")
        );
    }

    private List<Student> createTestList() {
        return Arrays.asList(
                new Student("John", null, "Doe", "john@example.com"),
                new Student("Jane", "Marta", "Doe", "jane@example.com")
        );
    }
}
