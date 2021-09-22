package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Classe;
import com.example.demo.model.Filiere;
import com.example.demo.repository.FiliereRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class FiliereController {

	@Autowired
	private FiliereRepository filiereRepository ; 
	@RequestMapping("/table_filiere")
	public List <Filiere> getAllFiliere() {
	        return filiereRepository.findAll();
	}
	
	
	@PostMapping("/table_filiere")
	public Filiere createfiliere(@Validated @RequestBody Filiere filiere) {
	    return filiereRepository.save(filiere);

	}
	  @DeleteMapping("/table_filiere/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long FiliereId)
	         throws ResourceNotFoundException {
		  	Filiere filiere = filiereRepository.findById(FiliereId)
	       .orElseThrow(() -> new ResourceNotFoundException("filiere not found for this id :: " + FiliereId));

		  	filiereRepository.delete(filiere);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	  @PutMapping("/table_filiere/{id}")
	    public ResponseEntity<Filiere> updateClasse(@PathVariable(value = "id") Long FiliereId,
	         @Valid @RequestBody Classe employeeDetails) throws ResourceNotFoundException {
		  Filiere filiere= filiereRepository.findById( FiliereId)
	        .orElseThrow(() -> new ResourceNotFoundException("Filiere not found for this id :: " + FiliereId));

		  filiere.setId(employeeDetails.getId());
		  filiere.setNom(employeeDetails.getNom());
	
	        final Filiere updateFiliere = filiereRepository.save(filiere);
	        return ResponseEntity.ok(updateFiliere);
	    }
}
