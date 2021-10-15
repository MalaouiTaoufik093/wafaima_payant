package com.matu.mvc.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WAFAIMA_Garanties {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idgarantie;
private String libelle;
private String accident;
private String panne;
private  Integer idformule;




public Integer getIdformule() {
	return idformule;
}
public void setIdformule(Integer idformule) {
	this.idformule = idformule;
}
public WAFAIMA_Garanties() {
	super();
}
public WAFAIMA_Garanties(String libelle, String accident, String panne) {
	super();
	this.libelle = libelle;
	this.accident = accident;
	this.panne = panne;
}
public Integer getIdgarantie() {
	return idgarantie;
}
public void setIdgarantie(Integer idgarantie) {
	this.idgarantie = idgarantie;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public String getAccident() {
	return accident;
}
public void setAccident(String accident) {
	this.accident = accident;
}
public String getPanne() {
	return panne;
}
public void setPanne(String panne) {
	this.panne = panne;
}



}
