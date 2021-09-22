package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.context.annotation.ComponentScan;

@Entity
@Table(name = "classe_etudiant")
@ComponentScan()
@IdClass(classe_etudiant.class)
public class classe_etudiant  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  long  classe_id ; 
	private long etudiant_id ;
	public classe_etudiant() {
		
	}

	
	public classe_etudiant(long classe_id, long etudiant_id) {
		super();
		this.classe_id = classe_id;
		this.etudiant_id = etudiant_id;
	}


	@Id
	@Column(name = "classe_id")
	public  long getClasse_id() {
		return classe_id;
	}
	public void setClasse_id( long classe_id) {
		this.classe_id = classe_id;
	}
	
	@Id
	@Column(name = "etudiant_id")
	public long getEtudiant_id() {
		return etudiant_id;
	}
	public void setEtudiant_id(long etudiant_id) {
		this.etudiant_id = etudiant_id;
	}
	
	
	
}
