package com.ibik.academicservices.academicservices.program_study;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.ibik.academicservices.academicservices.programs.Programs;

@Entity
@Table(name = "Program_Study")
public class ProgramStudy implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(length = 20)
    private String description;

    @Column(length = 5)
    @NotEmpty(message = "Code is required")
    private String code;

    // @Column(length = 11)
    // private int program_id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    @OneToMany
    @JoinColumn(name="faculty_id")
     private Set<ProgramStudy> Departments;

    // @Column(length = 11)
    // private int faculty_id;

    @Column(length = 11)
    private int department_id;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean is_active;

    public ProgramStudy() {
    }

    public ProgramStudy(int id, String name, String description, String code, Programs programs, boolean is_active ,Set<ProgramStudy> Departments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.programs = programs;
        this.Departments = Departments;
        this.is_active = is_active;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public Set<ProgramStudy> getDepartments() {
        return Departments;
    }

    public void setDepartments(Set<ProgramStudy> departments) {
        Departments = departments;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

   
    
   
    // public int getFaculty_id() {
    //     return faculty_id;
    // }

    // public void setFaculty_id(int faculty_id) {
    //     this.faculty_id = faculty_id;
    // }

    
    
}




