package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDao extends JpaRepository<Persona, Long> {
}
