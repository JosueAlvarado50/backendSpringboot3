package com.SpringAndReact.SyRFullStack.controller;

import com.SpringAndReact.SyRFullStack.dto.DepartmentDto;
import com.SpringAndReact.SyRFullStack.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    DepartmentService departmentService;

    //Build Department Rest API
    //Create Department

    /**
     *
     * @param departmentDto
     * @return
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentCreated = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(departmentCreated, HttpStatus.CREATED);
    }
    //GET DEPARTMENT BY IDD

    /**
     *
     * @param departmentId
     * @return
     */
    @GetMapping("{id}")
    public  ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(departmentDto);
    }

    //GET TALL DEPARTMENTS
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentDtoList);
    }
    //UPDATE DEPARTMENT
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId, @RequestBody DepartmentDto updatedDepartment){
        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
        return ResponseEntity.ok(departmentDto);
    }
    //DELETE DEPARTMENT
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return  ResponseEntity.ok("Departamento eliminado");
    }

}
