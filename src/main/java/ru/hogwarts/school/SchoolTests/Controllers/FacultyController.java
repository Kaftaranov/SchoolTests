package ru.hogwarts.school.SchoolTests.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.SchoolTests.Models.Faculty;
import ru.hogwarts.school.SchoolTests.Services.FacultyService;
import java.util.List;

@RestController
@RequestMapping(path = "/faculty")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty (@PathVariable long id){
        Faculty faculty = facultyService.findById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/find")
    public ResponseEntity<Faculty> filterByColorOrName(@RequestParam String descriptor){
        Faculty faculty = facultyService.filterByColorOrName(descriptor);
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Faculty>> getAllFaculties(){
        return ResponseEntity.ok(facultyService.getAll());
    }

    @PostMapping("/add")
    public Faculty add(@RequestBody Faculty faculty){
        return  facultyService.add(faculty);
    }

    @PutMapping("/update")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty){
        Faculty updatedfaculty = facultyService.update(faculty);
        if (updatedfaculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedfaculty);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity remove(@PathVariable long id){
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }

}

