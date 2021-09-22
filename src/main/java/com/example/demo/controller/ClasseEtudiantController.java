package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.classe_etudiant;
import com.example.demo.repository.ClasseEtudiantRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v8")
public class ClasseEtudiantController {
	@PersistenceContext
	 private EntityManager entityManager ; 
	
	@Autowired
	private ClasseEtudiantRepository classeetudiantrepository ;  
	
	@SuppressWarnings("unchecked")
	 @RequestMapping("/classe_etudiant/{id}")
		 public   List<classe_etudiant >  getTP(@PathVariable(value = "id") Long etudiantId)
		 {
		 List<classe_etudiant> List = new ArrayList<>() ;
		 List= entityManager.createNativeQuery("select *"
				 		+ " from classe_etudiant  where classe_id=" +etudiantId, classe_etudiant.class ).getResultList();
				
		 return List ; 
			 }
		 
		 
		 @PostMapping("/classe_etudiant")
			public classe_etudiant createfiliere(@Validated @RequestBody classe_etudiant classe_etudiant) {
			    return classeetudiantrepository.save(classe_etudiant);

			}	 
}
