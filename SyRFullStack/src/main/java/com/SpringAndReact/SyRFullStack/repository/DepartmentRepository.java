package com.SpringAndReact.SyRFullStack.repository;

import com.SpringAndReact.SyRFullStack.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
