package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.classe_etudiant;

@Repository
public interface ClasseEtudiantRepository extends JpaRepository <classe_etudiant , Long>{

}
