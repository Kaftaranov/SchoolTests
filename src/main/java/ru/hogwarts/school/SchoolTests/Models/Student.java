package ru.hogwarts.school.SchoolTests.Models;
import javax.persistence.*;
import java.util.Objects;
@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private Faculty faculty;

    public Student(Long id, String name, Integer age, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getFacultyId() {
        return faculty.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setId (long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getId() == student.getId() && getAge() == student.getAge() && getName().equals(student.getName())
                && Objects.equals(getFacultyId(), student.getFacultyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getFacultyId());
    }
}

