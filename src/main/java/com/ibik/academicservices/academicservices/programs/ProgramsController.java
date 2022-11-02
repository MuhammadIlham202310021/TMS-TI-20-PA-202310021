package com.ibik.academicservices.academicservices.programs;

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
@RequestMapping("/api/programs")
public class ProgramsController {
    
    // @GetMapping
    // public String HelloWorld(){
    //     return "<h1>Hello<h1>";
    // }

    
    @Autowired
    private ProgramsServices programsServices;

    @PostMapping()
    public Programs postPrograms(@RequestBody Programs programs){
        return programsServices.save(programs);
    }//untuk memasukan data

    @GetMapping
    public Iterable<Programs> fetchPrograms(){
        return programsServices.findAll();
    }//mengambil seluruh data

    @GetMapping("/{id}")
    public Programs fetchProgramsById(@PathVariable("id") int id){
        return programsServices.findOne(id);
    }//mengambil data by id

    @PutMapping
    public Programs updatePrograms(@RequestBody Programs programs){
        return programsServices.save(programs);
    }//update programs


    @DeleteMapping("/{id}")
    public void deleteProgramsById(@PathVariable("id") int id){
        programsServices.removoOne(id);
    }
}
