package com.ibik.academicservices.academicservices.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentServices studentServices;

    @PostMapping()
    public Student postPrograms(@RequestBody Student student){
        return studentServices.save(student);
    }//untuk memasukan data

    @GetMapping
    public Iterable<Student> fetchPrograms(){
        return studentServices.findAll();
    }//mengambil seluruh data

    @GetMapping("/{id}")
    public Student fetchProgramsById(@PathVariable("id") int id){
        return studentServices.findOne(id);
    }//mengambil data by id

    @PutMapping
    public Student updatePrograms(@RequestBody Student student){
        return studentServices.save(student);
    }//update programs


    @DeleteMapping("/{id}")
    public void deleteProgramsById(@PathVariable("id") int id){
        studentServices.removoOne(id);
    }
}
