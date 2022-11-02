package com.ibik.academicservices.academicservices.program_study;

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
@RequestMapping("/api/program_study")
public class ProgramStudyController {

    @Autowired
    private ProgramStudyServices programStudyServices;

    @PostMapping()
    public ProgramStudy postPrograms(@RequestBody ProgramStudy programStudy) {
        return programStudyServices.save(programStudy);
    }// untuk memasukan data

    @GetMapping
    public Iterable<ProgramStudy> fetchPrograms() {
        return programStudyServices.findAll();
    }// mengambil seluruh data

    @GetMapping("/{id}")
    public ProgramStudy fetchProgramsById(@PathVariable("id") int id) {
        return programStudyServices.findOne(id);
    }// mengambil data by id

    @PutMapping
    public ProgramStudy updatePrograms(@RequestBody ProgramStudy programStudy) {
        return programStudyServices.save(programStudy);
    }// update programs

    @DeleteMapping("/{id}")
    public void deleteProgramsById(@PathVariable("id") int id) {
        programStudyServices.removoOne(id);
    }
}
