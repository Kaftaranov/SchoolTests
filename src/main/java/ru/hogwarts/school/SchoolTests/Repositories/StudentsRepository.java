package ru.hogwarts.school.SchoolTests.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.SchoolTests.Models.Student;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int min, int max);
    List<Student> findAllByNameContainingIgnoreCase(String letter);
    List<Student> findStudentByFaculty_IdOrderByName(long faculty_id);
    List<Student> findAll();
    Student findStudentByName(String studentName);
    Student findById(long id);
    void deleteById(long id);
}
