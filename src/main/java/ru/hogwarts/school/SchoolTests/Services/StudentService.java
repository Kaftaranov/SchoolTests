package ru.hogwarts.school.SchoolTests.Services;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.SchoolTests.Models.Faculty;
import ru.hogwarts.school.SchoolTests.Models.Student;
import ru.hogwarts.school.SchoolTests.Repositories.FacultyRepository;
import ru.hogwarts.school.SchoolTests.Repositories.StudentsRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentsRepository studentsRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentsRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentsRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student add(Student student) {
        return studentsRepository.save(student);
    }
    public Student findById(long id) {
        return studentsRepository.findById(id);
    }
    public List<Student> filterByAge(int age) {
        List<Student> filteredByAge = new ArrayList<>(studentsRepository.findByAge(age)) ;
        return filteredByAge.stream()
                .filter(student ->student.getAge()==age)
                .collect(Collectors.toList());
    }
    public Student update(Student student) {
        return studentsRepository.save(student);
    }
    public void remove(long id) {
        studentsRepository.deleteById(id);
    }
    public List<Student>getAll(){
        return studentsRepository.findAll();
    }

    public List<Student> findByAgeBetween(int min_age, int max_age) {
        return studentsRepository.findByAgeBetween(min_age,max_age);
    }
    public List<Student> findAllByNameContaining(String letter){
        return studentsRepository.findAllByNameContainingIgnoreCase(letter);
    }

    public List<Student> getStudentsOfFaculty(long faculty_id) {
        return studentsRepository.findStudentByFaculty_IdOrderByName(faculty_id);
    }

    public Faculty getfacultyOfStudent(String studentname) {
        return facultyRepository.findById(studentsRepository.findStudentByName(studentname).getFacultyId()) ;
    }
}


