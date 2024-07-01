package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.Mapper.DepartmentMapper;
import com.SpringAndReact.SyRFullStack.dto.DepartmentDto;
import com.SpringAndReact.SyRFullStack.entity.Department;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.DepartmentRepository;
import com.SpringAndReact.SyRFullStack.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department is not exist with given id: " + departmentId)
                );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentDtoList = departmentRepository.findAll();
        return departmentDtoList.stream()
                .map((departmentDto) -> DepartmentMapper.mapToDepartmentDto(departmentDto))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
        Department departmentToUpdate = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department not exist with given id: " + departmentId));
        departmentToUpdate.setDepartmentName(updatedDepartment.getDepartmentName());
        departmentToUpdate.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
        Department departmentSaved = departmentRepository.save(departmentToUpdate);
        return DepartmentMapper.mapToDepartmentDto(departmentSaved);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("department not exist with that id given: " + departmentId)
                );
        departmentRepository.deleteById(departmentId);
        System.out.println("department deleted");

    }
}
