package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;


@Entity
@Table(name = "Formation")
@ComponentScan()
public class Formation {
private long id ; 
private String nom ;


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
public void setNom(String nom) {
	this.nom = nom;
}

@Override
public String toString() {
	return "Formation [id=" + id + ", nom=" + nom + "]";
} 
}
