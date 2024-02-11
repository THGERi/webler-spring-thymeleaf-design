package hu.webler.weblerspringthymeleafdesign.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Student implements Comparable<Student> { // Comparable<T>

    private String firstName;

    @Nullable
    private String midName;

    private String lastName;
    private String email;

    private final String  COURSE_NAME = "Java Spring Boot";

    @Override
    public int compareTo(Student other) {
        // Implement comparison logic based on your requirements
        // For example, comparing by last name
        return this.lastName.compareTo(other.lastName);
    }
}
