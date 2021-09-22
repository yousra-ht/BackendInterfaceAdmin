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
import com.example.demo.model.users;
import com.example.demo.repository.EtudiantRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v5")
public class EtudiantController {
	
	@PersistenceContext
	 private EntityManager entityManager ;  
	
	@Autowired
	private EtudiantRepository etudiantrepository ; 
	
	
	@RequestMapping("/table_Etudiant")
	public List <users> getAllFiliere() {
        return etudiantrepository.findAll();
        
		}
	@PostMapping("/table_Etudiant")
	public users createfiliere(@Validated @RequestBody users etudiant) {
	    return etudiantrepository.save(etudiant);

	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Etudiant/{id}")
	public List <users> getEtudiant(@PathVariable(value = "id") Long etudiantId) {
		 List<users> List = new ArrayList<>() ;
		 List= entityManager.createNativeQuery("select *"
				 		+ " from users where id=" +etudiantId , users.class ).getResultList();
				
		 return List ; 
        
		}
	  @DeleteMapping("/table_Etudiant/{id}")
	    public Map<String, Boolean> deleteClasse(@PathVariable(value = "id") Long userId)
	         throws ResourceNotFoundException {
		  users user = etudiantrepository.findById(userId)
	       .orElseThrow(() -> new ResourceNotFoundException("etudiant not found for this id :: " + userId));

		  etudiantrepository.delete(user);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}

