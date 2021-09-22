package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Enseignant;
import com.example.demo.repository.EnseignantRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v6")
public class EnseignantController {
	
	@PersistenceContext
	 private EntityManager entityManager ; 
	
	@Autowired
	private EnseignantRepository enseignantRepository ; 
	@RequestMapping("/table_Enseignant")
	public List <Enseignant> getAllEnseignant() {
	        return enseignantRepository.findAll();
	}
	
	
	@PostMapping("/table_Enseignant")
	public Enseignant createfiliere(@Validated @RequestBody Enseignant enseignant) {
	    return enseignantRepository.save(enseignant);

	}
	

 	 @SuppressWarnings("unchecked")
	 @RequestMapping("/enseignantData/{id}")
		 public   List<Enseignant>  getTP(@PathVariable(value = "id") Long ensId)
		 {
		 List<Enseignant> List = new ArrayList<>() ;
		 List= entityManager.createNativeQuery("select *"
				 		+ " from Enseignant where id=" +ensId , Enseignant.class ).getResultList();
				
		 return List ; 
			 }
 	  @DeleteMapping("/table_Enseignant/{id}")
	    public Map<String, Boolean> deleteClasse(@PathVariable(value = "id") Long EnseignantId)
	         throws ResourceNotFoundException {
 		 Enseignant enseignant = enseignantRepository.findById(EnseignantId)
	       .orElseThrow(() -> new ResourceNotFoundException("enseignant not found for this id :: " + EnseignantId));

 		enseignantRepository.delete(enseignant);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
 	  
 	  
}
