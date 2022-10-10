package ru.hogwarts.school.SchoolTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.SchoolTests.Controllers.StudentController;
import ru.hogwarts.school.SchoolTests.Models.Faculty;
import ru.hogwarts.school.SchoolTests.Models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    private final List<Faculty> facultyList = new ArrayList<>(List.of(
            new Faculty(1L,"Griffindore","Orange"),
            new Faculty(2L,"Slytherin","Green"),
            new Faculty(3L,"Puffenhuff","Red"),
            new Faculty(4L,"Ravenclaw","Brown")
    ));
    private final List<Student> studentList = new ArrayList<>(List.of(
            new Student(1L,"Harry Potter",10,facultyList.get(0)),
            new Student(2L,"Germiona Granger",11, facultyList.get(0)),
            new Student(3L, "Ronald Weasley", 11, facultyList.get(2)),
            new Student(4L, "Dracko Malfoy",12, facultyList.get(1)),
            new Student(5L,"Nevill Longbottom", 13,facultyList.get(3)),
            new Student(6L, "Goyle", 13, facultyList.get(1)),
            new Student(21L,"Crabb",12, facultyList.get(1))));

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    public void testGetStudentById(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/student/1",String.class))
                .isEqualTo("{\"id\":" + studentList.get(0).getId() + ",\"name\":\"" +
                        studentList.get(0).getName() + "\",\"age\":" + studentList.get(0).getAge() +
                        ",\"facultyId\":" + studentList.get(0).getFacultyId() + "}");
    }
    @Test
    public void testGetStudentByAge(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port +
                        "/student/ofage?age=10", String.class))
                .isEqualTo("[{\"id\":" + studentList.get(0).getId() + ",\"name\":\"" +
                        studentList.get(0).getName() + "\",\"age\":" + studentList.get(0).getAge() +
                        ",\"facultyId\":" + studentList.get(0).getFacultyId() + "}]");
    }

    @Test
    public void testGetStudentsByAgeRange(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port +
                        "/student/agebetween?min_age=11&max_age=12", String.class))
                .isEqualTo("[{\"id\":" + studentList.get(1).getId() + ",\"name\":\"" +
                        studentList.get(1).getName() + "\",\"age\":" + studentList.get(1).getAge() +
                        ",\"facultyId\":" + studentList.get(1).getFacultyId() + "}," +
                        "{\"id\":" + studentList.get(2).getId() + ",\"name\":\"" +
                        studentList.get(2).getName() + "\",\"age\":" + studentList.get(2).getAge() +
                        ",\"facultyId\":" + studentList.get(2).getFacultyId() + "}," +
                        "{\"id\":" + studentList.get(3).getId() + ",\"name\":\"" +
                        studentList.get(3).getName() + "\",\"age\":" + studentList.get(3).getAge() +
                        ",\"facultyId\":" + studentList.get(3).getFacultyId() + "}," +
                        "{\"id\":" + studentList.get(6).getId() + ",\"name\":\"" +
                        studentList.get(6).getName() + "\",\"age\":" + studentList.get(6).getAge() +
                        ",\"facultyId\":" + studentList.get(6).getFacultyId() + "}]");
    }

    @Test
    public void testGetStudentByLetterInName(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port +
                        "/student/namecontains?letter=P", String.class))
                .isEqualTo("[{\"id\":" + studentList.get(0).getId() + ",\"name\":\"" +
                        studentList.get(0).getName() + "\",\"age\":" + studentList.get(0).getAge() +
                        ",\"facultyId\":" + studentList.get(0).getFacultyId() + "}]");
    }

    @Test
    public void testGetStudentsOfFaculty(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port +
                        "/student/facultylist?faculty=4", String.class))
                .isEqualTo("[{\"id\":" + studentList.get(4).getId() + ",\"name\":\"" +
                        studentList.get(4).getName() + "\",\"age\":" + studentList.get(4).getAge() +
                        ",\"facultyId\":" + studentList.get(4).getFacultyId() + "}]");
    }
    @Test
    public void testGetFacultyOfStudent(){
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port +
                        "/student/facultyofstudent?studentname=Harry Potter", String.class))
                .isEqualTo("{\"id\":" + facultyList.get(0).getId() + ",\"name\":\"" +
                        facultyList.get(0).getName() + "\",\"color\":\"" + facultyList.get(0).getColor() + "\"}");
    }
    @Test
    public void testAddStudent(){
        Student testStudent = new Student(22L,"Test Student",18,facultyList.get(3));
        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port +
                        "/student/add", testStudent, String.class)).isNotNull();
    }
    @Test
    public void testUpdateStudent(){

    }
    @Test
    public void testDeleteStudent(){
        Assertions
                .assertThat(restTemplate.delete("http:localhost:" + port + "student/delete/36"));
    }

}
