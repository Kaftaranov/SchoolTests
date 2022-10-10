package ru.hogwarts.school.SchoolTests.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.SchoolTests.Models.Avatar;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
    Avatar findByStudentId(Long student_id);
}