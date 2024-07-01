package com.SpringAndReact.SyRFullStack.repository;

import com.SpringAndReact.SyRFullStack.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
