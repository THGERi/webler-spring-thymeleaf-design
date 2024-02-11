package hu.webler.weblerspringthymeleafdesign.bootstrap;

import hu.webler.weblerspringthymeleafdesign.model.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class DataInitializer implements ApplicationRunner {

    private List<Student> STUDENTS_ENGLISH;
    private List<Student> STUDENTS_HUNGARIAN;

    public DataInitializer(List<Student> STUDENTS_HUNGARIAN, List<Student> STUDENTS_ENGLISH) {
        this.STUDENTS_HUNGARIAN = STUDENTS_HUNGARIAN;
        this.STUDENTS_ENGLISH = STUDENTS_ENGLISH;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        STUDENTS_HUNGARIAN = createStudentsHungarian();
        STUDENTS_ENGLISH = createStudentsEnglish();
    }

    private List<Student> createStudentsEnglish() {
        Student laszlo = Student.builder().
                firstName("Laszlo").
                lastName("Foldhazi").
                midName(null).
                email("foldi44@hotmail.com").build();
        Student janos = Student.builder().
                firstName("Janos").
                lastName("Kovacs").
                midName("Pal").
                email("kovacs.janos.pal@gmail.com").build();
        Student miklos = Student.builder().
                firstName("Miklos").
                lastName("Abraham").
                midName(null).
                email("mikcsek2@gmail.com").build();
        Student gyepi = Student.builder().
                firstName("Peter").
                lastName("Antal").
                midName("Attila").
                email("antipet@gmail.com").build();
        Student roland = Student.builder().
                firstName("Roland").
                lastName("Szoke").
                midName(null).
                email("szokeroland0@gmail.com").build();
        Student peter = Student.builder().
                firstName("Peter").
                lastName("Futo").
                midName(null).
                email("futopeter97@gmail.com").build();
        Student gabor = Student.builder().
                firstName("Gabor").
                lastName("Perczel").
                midName(null).
                email("kapusgabor64@gmail.com").build();
        Student norbi = Student.builder().
                firstName("Norbert").
                lastName("Balint").
                midName(null).
                email("norbaa79@gmail.com").build();
        Student geri = Student.builder().
                firstName("Gergely").
                lastName("Toth").
                midName(null).
                email("thgeri17@gmail.com").build();

        STUDENTS_ENGLISH.add(laszlo);
        STUDENTS_ENGLISH.add(janos);
        STUDENTS_ENGLISH.add(miklos);
        STUDENTS_ENGLISH.add(gyepi);
        STUDENTS_ENGLISH.add(roland);
        STUDENTS_ENGLISH.add(peter);
        STUDENTS_ENGLISH.add(gabor);
        STUDENTS_ENGLISH.add(norbi);
        STUDENTS_ENGLISH.add(geri);
        return STUDENTS_ENGLISH;
    }

    public List<Student> createStudentsHungarian() {
        Student laszlo = Student.builder().
                firstName("László").
                lastName("Földházi").
                midName(null).
                email("foldi44@hotmail.com").build();
        Student janos = Student.builder().
                firstName("János").
                lastName("Kovács").
                midName("Pál").
                email("kovacs.janos.pal@gmail.com").build();
        Student miklos = Student.builder().
                firstName("Miklós").
                lastName("Ábrahám").
                midName(null).
                email("mikcsek2@gmail.com").build();
        Student gyepi = Student.builder().
                firstName("Péter").
                lastName("Antal").
                midName("Attila").
                email("antipet@gmail.com").build();
        Student roland = Student.builder().
                firstName("Roland").
                lastName("Szőke").
                midName(null).
                email("szokeroland0@gmail.com").build();
        Student peter = Student.builder().
                firstName("Péter").
                lastName("Futó").
                midName(null).
                email("futopeter97@gmail.com").build();
        Student gabor = Student.builder().
                firstName("Gábor").
                lastName("Perczel").
                midName(null).
                email("kapusgabor64@gmail.com").build();
        Student norbi = Student.builder().
                firstName("Norbert").
                lastName("Bálint").
                midName(null).
                email("norbaa79@gmail.com").build();
        Student geri = Student.builder().
                firstName("Gergely").
                lastName("Tóth").
                midName(null).
                email("thgeri17@gmail.com").build();

        STUDENTS_HUNGARIAN.add(laszlo);
        STUDENTS_HUNGARIAN.add(janos);
        STUDENTS_HUNGARIAN.add(miklos);
        STUDENTS_HUNGARIAN.add(gyepi);
        STUDENTS_HUNGARIAN.add(roland);
        STUDENTS_HUNGARIAN.add(peter);
        STUDENTS_HUNGARIAN.add(gabor);
        STUDENTS_HUNGARIAN.add(norbi);
        STUDENTS_HUNGARIAN.add(geri);
        return STUDENTS_HUNGARIAN;
    }
}
