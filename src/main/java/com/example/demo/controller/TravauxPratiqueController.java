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
import com.example.demo.model.TravauxPratique;
import com.example.demo.repository.TravauxPratiqueRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v4")
public class TravauxPratiqueController {
	@PersistenceContext
	 private EntityManager entityManager ; 
	@Autowired
	private TravauxPratiqueRepository travauxpratiqueRepository ;
	@RequestMapping("/table_TravauxPratique")
	public List<TravauxPratique> getAlltravauxpratique() {
	        return travauxpratiqueRepository.findAll();
	}
	
	
	@PostMapping("/table_TravauxPratique")
	public TravauxPratique createfiliere(@Validated @RequestBody TravauxPratique filiere) {
	    return travauxpratiqueRepository.save(filiere);

	}
	
	
	 	@SuppressWarnings("unchecked")
		 @RequestMapping("/dataTP/{id}")
			 public   List<TravauxPratique>  getTP(@PathVariable(value = "id") Long TPId)
			 {
			 List<TravauxPratique> List = new ArrayList<>() ;
			 List= entityManager.createNativeQuery("select *"
					 		+ " from travaux_pratique where id=" +TPId , TravauxPratique.class ).getResultList();
					
			 return List ; 
				 }
	 	@DeleteMapping("/table_TravauxPratique/{id}")
		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long travauxpratiqueId)
		   throws ResourceNotFoundException {
	 		TravauxPratique  travauxpratique = travauxpratiqueRepository.findById( travauxpratiqueId)
		 .orElseThrow(() -> new ResourceNotFoundException("travauxpratique not found for this id :: " +  travauxpratiqueId));

			travauxpratiqueRepository.delete(travauxpratique);
		  Map<String, Boolean> response = new HashMap<>();
		  response.put("deleted", Boolean.TRUE);
		  return response;
		}
}
