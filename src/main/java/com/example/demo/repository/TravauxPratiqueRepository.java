package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TravauxPratique;

@Repository
public interface TravauxPratiqueRepository  extends JpaRepository<TravauxPratique, Long>{

}
