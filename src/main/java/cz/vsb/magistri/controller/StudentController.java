package cz.vsb.magistri.controller;

import cz.vsb.magistri.dto.StudentDto;
import cz.vsb.magistri.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping({"/students","/students/"})
    public List<StudentDto> getStudents(){

        return studentService.getStudents();
    }
    @GetMapping({"/students/{id}","/students/{id}/"})
    public StudentDto getStudent(@PathVariable("id") Integer id){
        return studentService.getStudent(id);
    }

    @PostMapping({"/students","/students/"})
    public StudentDto addStudents(@RequestBody StudentDto studentDto){
        return studentService.addStudents(studentDto);
    }
    @PutMapping({"/students/{id}","/students/{id}/"})
    public StudentDto editStudent(@PathVariable("id") Integer id, @RequestBody StudentDto studentDto){
        return studentService.editStudent(id, studentDto);

}
@DeleteMapping({"/students/{id}","/students/{id}/"})
    public StudentDto deleteStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
}

}
