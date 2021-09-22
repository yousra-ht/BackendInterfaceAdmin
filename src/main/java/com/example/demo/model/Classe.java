package com.example.demo.model;




import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Classe")
@ComponentScan()
public class Classe implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id ; 
	private String nom ;
	
	
	private Formation Formation ; 
	private List<users> etudiants ; 
	private Filiere Filiere ; 

public Classe() {
		
	}
public Classe(String nom, com.example.demo.model.Formation formation,
			com.example.demo.model.Filiere filiere) {
		super();
		this.nom = nom;
	}
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@OneToOne(fetch = FetchType.LAZY)	
@JoinColumn(name="Filiere_id")	
public Filiere getFiliere() {
		return Filiere;
	}
	public void setFiliere(Filiere filiere) {
		Filiere = filiere;
	}
@OneToMany(fetch=FetchType.EAGER)
@JoinTable(
        name="Classe_Etudiant",
        joinColumns=
            @JoinColumn(name="Classe_id" , insertable = false, updatable = false ,  nullable = false),
        inverseJoinColumns=
            @JoinColumn(name="Etudiant_id",insertable = false, updatable = false ,  nullable = false)
    )	
public List<users> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<users> etudiants) {
		this.etudiants = etudiants;
	}
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@OneToOne(fetch = FetchType.LAZY)	
@JoinColumn(name="Formation_id")
public Formation getFormation() {
		return Formation;
	}
	public void setFormation(Formation formation) {
		Formation = formation;
	}

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
@Column(nullable=false)
	public String getNom() {
		return nom;
	}
	@Column(nullable=false)
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public String toString() {
		return "Classe [id=" + id + ", nom=" + nom + "]";
	}
	
}

