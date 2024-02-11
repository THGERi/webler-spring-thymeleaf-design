package hu.webler.weblerspringthymeleafdesign;

import hu.webler.weblerspringthymeleafdesign.controller.StudentController;
import hu.webler.weblerspringthymeleafdesign.model.Student;
import hu.webler.weblerspringthymeleafdesign.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@DisplayName("Integration test of StudentController - REST API")
public class StudentControllerIT {

    // https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework.html
    // https://www.linkedin.com/pulse/testing-controller-layer-spring-boot-3-junit-5-soheil-qalamkari-yt8pf/?utm_source=rss&utm_campaign=articles_sitemaps

    // check BDD testing - Behavior Driven Development Testing - given, when, then

    // https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc/
    // https://www.geeksforgeeks.org/spring-boot-mockmvc-testing-with-example-project/
    // https://www.baeldung.com/spring-boot-testing

    // video: https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc/

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    @DisplayName("Integration test of StudentController to return all students")
    void shouldReturnAllStudents() throws Exception {
        // Arrange
        List<Student> mockStudents = createMockStudents();
        when(studentService.getStudents()).thenReturn(mockStudents);

        // Act and Assert
        var result = mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockStudents.size()))); // Adjusted the expected size based on your mock data

        result.andExpect(jsonPath("$[0].firstName").value("John"));
        result.andExpect(jsonPath("$[1].firstName").value("Jane"));
    }

    @Test
    @DisplayName("Integration test of StudentController to return filtered students with param")
    void shouldReturnFilteredStudents() throws Exception {
        // Arrange
        List<Student> mockFilteredStudents = createMockStudents();
        when(studentService.searchStudentsByEmail(anyString())).thenReturn(mockFilteredStudents);

        // Act and Assert
        mockMvc.perform(get("/api/students/search").param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockFilteredStudents.size()))); // Adjust the expected size based on your mock data
    }

    @Test
    @DisplayName("Integration test of StudentController to return filtered students without param")
    void shouldReturnEmptyList() throws Exception {
        // Arrange
        when(studentService.searchStudentsByEmail(anyString())).thenReturn(Arrays.asList());

        // Act and Assert
        mockMvc.perform(get("/api/students/search").param("email", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // Helper methods to create mock data
    private List<Student> createMockStudents() {
        return Arrays.asList(
                new Student("John", null, "Doe", "john@example.com"),
                new Student("Jane", "Marta", "Doe", "jane@example.com"),
                new Student("Mike", "James", "Smith", "mike@example.com")
        );
    }
}
