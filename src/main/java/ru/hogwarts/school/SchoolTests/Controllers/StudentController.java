package ru.hogwarts.school.SchoolTests.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.SchoolTests.Models.Faculty;
import ru.hogwarts.school.SchoolTests.Models.Student;
import ru.hogwarts.school.SchoolTests.Services.AvatarService;
import ru.hogwarts.school.SchoolTests.Services.StudentService;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;
    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent (@PathVariable long id){
        Student student = studentService.findById(id);
        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping("/ofage")
    public ResponseEntity<List<Student>> filterByAge(@RequestParam int age){
        List<Student> ageCollection = studentService.filterByAge(age);
        if (ageCollection.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ageCollection);
    }
    @GetMapping("/agebetween")
    public ResponseEntity<List<Student>> filterByAgeBetween(@RequestParam int min_age, @RequestParam int max_age){
        List<Student> ageCollection = studentService.findByAgeBetween(min_age, max_age);
        if (ageCollection.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ageCollection);
    }
    @GetMapping("/namecontains")
    public ResponseEntity<List<Student>> findNamesContaining(@RequestParam String letter){
        List<Student> namesCollection = studentService.findAllByNameContaining(letter);
        if (namesCollection.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(namesCollection);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAll());
    }
    @GetMapping("/facultylist")
    public ResponseEntity<List<Student>> getStudentsOfFaculty(long faculty){
        return ResponseEntity.ok(studentService.getStudentsOfFaculty(faculty));
    }
    @GetMapping("/facultyofstudent")
    public Faculty getStudentsFaculty(String studentname){
        return studentService.getfacultyOfStudent(studentname);
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student){
        return  studentService.add(student);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student student){
        Student updatedstudent = studentService.update(student);
        if (updatedstudent == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedstudent);
    }
    @DeleteMapping ("{id}")
    public ResponseEntity<String> remove(@PathVariable long id) {
        String deletedStudent = studentService.findById(id).toString();
        if (deletedStudent != null) {
            studentService.remove(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
