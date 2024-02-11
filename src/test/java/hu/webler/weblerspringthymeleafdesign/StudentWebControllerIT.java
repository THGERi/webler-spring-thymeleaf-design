package hu.webler.weblerspringthymeleafdesign;

import hu.webler.weblerspringthymeleafdesign.controller.StudentWebController;
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

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentWebController.class)
@DisplayName("Integration test of StudentWebController - Thymeleaf")
public class StudentWebControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @Test
    void testHome_shouldReturnIndexView() throws Exception {
        mockMvc.perform(get("/thy"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("Integration test of StudentWebController - Thymeleaf - to return all students")
    void testThymeleaf_shouldReturnAllStudents() throws Exception {
        // Arrange
        List<Student> mockStudents = createMockStudents();
        when(studentService.getStudents()).thenReturn(mockStudents);

        // Act and Assert
        mockMvc.perform(get("/thy/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students")) // Check the view name
                .andExpect(model().attribute("students", mockStudents)); // Check the model attribute
    }

    @Test
    @DisplayName("Integration test of StudentWebController - Thymeleaf - to return filtered students")
    void testThymeleaf_shouldReturnFilteredStudents() throws Exception {
        // Arrange
        List<Student> mockFilteredStudents = createMockStudents();
        when(studentService.searchStudentsByEmail(anyString())).thenReturn(mockFilteredStudents);

        // Act and Assert
        mockMvc.perform(get("/thy/students/search").param("search", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("students")) // Check the view name
                .andExpect(model().attribute("students", mockFilteredStudents)); // Check the model attribute
    }

    @Test
    @DisplayName("Integration test of StudentWebController - Thymeleaf - to return null when search param is empty")
    void testThymeleaf_shouldReturnNull_whenSearchParamIsEmpty() throws Exception {
        // Arrange
        when(studentService.searchStudentsByEmail(anyString())).thenReturn(null);

        // Act and Assert
        mockMvc.perform(get("/thy/students/search").param("search", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("students")) // Check the view name
                .andExpect(model().attribute("students", nullValue())); // Expect null using nullValue()
    }

    private List<Student> createMockStudents() {
        return Arrays.asList(
                new Student("John", null, "Doe", "john@example.com"),
                new Student("Jane", "Marta", "Doe", "jane@example.com"),
                new Student("Mike", "James", "Smith", "mike@example.com")
        );
    }
}
