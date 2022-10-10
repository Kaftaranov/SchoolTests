package ru.hogwarts.school.SchoolTests.Models;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Faculty(Long id, String name, String color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Faculty() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty faculty)) return false;
        return getId() == faculty.getId() && getName().equals(faculty.getName()) && getColor().equals(faculty.getColor()) && Objects.equals(students, faculty.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getColor(), students);
    }


}

