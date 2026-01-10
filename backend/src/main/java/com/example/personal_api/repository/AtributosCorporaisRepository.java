package com.example.personal_api.repository;

import com.example.personal_api.entity.AtributosCorporais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtributosCorporaisRepository
        extends JpaRepository<AtributosCorporais, Long> {

}