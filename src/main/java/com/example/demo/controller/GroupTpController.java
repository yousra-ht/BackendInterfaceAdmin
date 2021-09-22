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
import com.example.demo.model.GroupTp;
import com.example.demo.repository.GroupTpRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v7")
public class GroupTpController {
	
		@Autowired
		private GroupTpRepository GrouptpRepository; 
		@RequestMapping("/Groupe")
		public List<GroupTp> getAllClasse() {
		        return GrouptpRepository.findAll();
		}
		
		
		@PostMapping("/Groupe")
		public GroupTp creategroup(@Validated @RequestBody GroupTp group) {
		    return GrouptpRepository.save(group);

		}
		
		@DeleteMapping("/Groupe/{id}")
		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long GroupTpId)
		   throws ResourceNotFoundException {
			GroupTp GroupTp = GrouptpRepository.findById( GroupTpId)
		 .orElseThrow(() -> new ResourceNotFoundException("GroupTp  not found for this id :: " +  GroupTpId));

			GrouptpRepository.delete(GroupTp);
		  Map<String, Boolean> response = new HashMap<>();
		  response.put("deleted", Boolean.TRUE);
		  return response;
		}
	
}
