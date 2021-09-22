package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import com.example.demo.model.Formation;
import com.example.demo.repository.ClasseRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class ClasseController {
	
	
	@PersistenceContext
	 private EntityManager entityManager ; 
	 
	@Autowired
	private ClasseRepository classeRepository ; 
	@RequestMapping("/table_classe")
	public List <Classe> getAllClasse() {
	        return classeRepository.findAll();
	}
	
	@PostMapping("/table_classe")
	public Classe createfiliere(@Validated @RequestBody Classe classe) {
	    return classeRepository.save(classe);

	}
	
	 @RequestMapping("/classe/{id}")
	 public  long getClasse(@PathVariable(value = "id") Long classeId)
	 {
		
		long id =  (long) entityManager.createQuery("select classe_id from classe_etudiant where etudiant_id=" +classeId   ).getSingleResult();
		 return  id ; 
	 }
	 
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/filiere/{id}")
	 public  List<Filiere> getFiliere(@PathVariable(value = "id") Long filiereId)
	 {
		 
		 List<Filiere> List = new ArrayList<>() ;
		List =  entityManager.createNativeQuery("select id , nom  from Filiere where id="+filiereId , Filiere.class)
				.getResultList();
		 
		 return List ; 
	 }
	 @SuppressWarnings("unchecked")
	 @RequestMapping("/classeGroup/{id}")
	 public List<Classe>  getselectclasse(@PathVariable(value = "id") Long Classe)
	 {
		
		 List<Classe> List = new ArrayList<>() ;
			List =  entityManager.createNativeQuery("select *  from Classe where id= " +Classe, Classe.class)
					.getResultList();
			 
			 return List ; 
	 }
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping("/formation/{id}")
	 public  List<Formation> getFormation(@PathVariable(value = "id") Long formationId)
	 {
		 
		 List<Formation> List = new ArrayList<>() ;
		List =  entityManager.createNativeQuery("select id , nom  from Formation where id="+formationId , Filiere.class)
				.getResultList();
		 
		 return List ; 
	 }
	 
	  @DeleteMapping("/table_classe/{id}")
	    public Map<String, Boolean> deleteClasse(@PathVariable(value = "id") Long ClasseId)
	         throws ResourceNotFoundException {
		  Classe classe = classeRepository.findById(ClasseId)
	       .orElseThrow(() -> new ResourceNotFoundException("Classe not found for this id :: " + ClasseId));

		  classeRepository.delete(classe);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	  
	  @PutMapping("/table_classe/{id}")
	    public ResponseEntity<Classe> updateClasse(@PathVariable(value = "id") Long classeId,
	         @Valid @RequestBody Classe employeeDetails) throws ResourceNotFoundException {
		  Classe classe = classeRepository.findById(classeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Classe not found for this id :: " + classeId));

		  classe.setId(employeeDetails.getId());
		  classe.setNom(employeeDetails.getNom());
	
	        final Classe updateClasse = classeRepository.save(classe);
	        return ResponseEntity.ok(updateClasse);
	    }

}
