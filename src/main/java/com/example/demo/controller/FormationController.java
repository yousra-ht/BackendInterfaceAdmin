package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
import com.example.demo.model.Formation;
import com.example.demo.repository.FormationRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class FormationController {
	
@Autowired
private FormationRepository formationRepository ; 
	
@RequestMapping("/table_formation")
public List<Formation> getAllFormation() {
        return formationRepository.findAll();
}
@PostMapping("/table_formation")
public Formation createreservation(@Validated @RequestBody Formation formation) {
    return formationRepository.save(formation);

}
@DeleteMapping("/table_formation/{id}")
public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long FormationId)
   throws ResourceNotFoundException {
	Formation formation = formationRepository.findById(FormationId)
 .orElseThrow(() -> new ResourceNotFoundException("Formation not found for this id :: " + FormationId));

	formationRepository.delete(formation);
  Map<String, Boolean> response = new HashMap<>();
  response.put("deleted", Boolean.TRUE);
  return response;
}
}