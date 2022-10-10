package ru.hogwarts.school.SchoolTests;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.SchoolTests.Controllers.FacultyController;
import ru.hogwarts.school.SchoolTests.Controllers.StudentController;
import ru.hogwarts.school.SchoolTests.Models.Faculty;
import ru.hogwarts.school.SchoolTests.Models.Student;
import ru.hogwarts.school.SchoolTests.Repositories.FacultyRepository;
import ru.hogwarts.school.SchoolTests.Repositories.StudentsRepository;
import ru.hogwarts.school.SchoolTests.Services.AvatarService;
import ru.hogwarts.school.SchoolTests.Services.FacultyService;
import ru.hogwarts.school.SchoolTests.Services.StudentService;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SchoolTestsApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentsRepository studentsRepository;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private StudentService studentService;
    @MockBean
    private AvatarService avatarservice;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private StudentController studentController;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void addStudent() throws Exception {
        final String name = "Test Student";
        final int age = 18;
        final Long id = 1L;
        final Faculty faculty = new Faculty(null,null,null);
        JSONObject studentObject = new JSONObject();
        studentObject.put("name",name);
        studentObject.put("age",age);
        studentObject.put("id",id);
        studentObject.put("faculty",faculty);
        Student student = new Student(null,null,null,null);
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);
        when(studentsRepository.save(any(Student.class))).thenReturn(student);
        when(studentsRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/student/add")
                .content(studentObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.faculty").value(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/student/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

}
