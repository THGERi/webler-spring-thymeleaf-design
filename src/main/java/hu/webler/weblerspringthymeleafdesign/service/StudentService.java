package hu.webler.weblerspringthymeleafdesign.service;

import hu.webler.weblerspringthymeleafdesign.bootstrap.DataInitializer;
import hu.webler.weblerspringthymeleafdesign.model.Student;
import hu.webler.weblerspringthymeleafdesign.util.Sorting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.RuleBasedCollator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final DataInitializer dataInitializer;

    public List<Student> getStudents() {
        return dataInitializer.getSTUDENTS_ENGLISH()
                .stream()
                .sorted()
                .toList();
    }

    public List<Student> searchStudentsByEmail(String email) {
        return dataInitializer.getSTUDENTS_ENGLISH()
                .stream()
                .filter(student -> student.getEmail().contains(email))
                .sorted()
                .toList();
    }

    public List<Student> getStudentsHungarian() {
        return sortStudentsByLastName(dataInitializer.getSTUDENTS_HUNGARIAN(), Sorting.createHungarianCollator());
    }

    private List<Student> sortStudentsByLastName(List<Student> students, RuleBasedCollator collator) {
        return students.stream()
                .sorted((s1, s2) -> collator.compare(s1.getLastName(), s2.getLastName()))
                .toList();
    }
}
