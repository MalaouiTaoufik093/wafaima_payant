package com.matu.mvc.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WAFAIMA_Formules")
public class WAFAIMA_Formules {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idformule;
private String idusage;
private String  libelle_usage;
private String formule;
private Integer  idsegment;
private String Libelle_Segment;
private double MontantHT89_92;
private double TVA89_92;
private double FraisGest89_92;
private double MontantTTC89_92;
private double MontantHT93_184;
private double TVA93_184;
private double FraisGest93_184;
private double MontantTTC93_184;
private double MontantHT184;
private double TVA184;
private double FraisGest184;
private double MontantTTC184;
private String path;



public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public WAFAIMA_Formules(String idusage, String libelle_usage, String formule, Integer idsegment, String libelle_Segment,
		double montantHT89_92, double tVA89_92, double fraisGest89_92, double montantTTC89_92, double montantHT93_184,
		double tVA93_184, double fraisGest93_184, double montantTTC93_184, double montantHT184, double tVA184,
		double fraisGest184, double montantTTC184) {
	super();
	this.idusage = idusage;
	this.libelle_usage = libelle_usage;
	this.formule = formule;
	this.idsegment = idsegment;
	Libelle_Segment = libelle_Segment;
	MontantHT89_92 = montantHT89_92;
	TVA89_92 = tVA89_92;
	FraisGest89_92 = fraisGest89_92;
	MontantTTC89_92 = montantTTC89_92;
	MontantHT93_184 = montantHT93_184;
	TVA93_184 = tVA93_184;
	FraisGest93_184 = fraisGest93_184;
	MontantTTC93_184 = montantTTC93_184;
	MontantHT184 = montantHT184;
	TVA184 = tVA184;
	FraisGest184 = fraisGest184;
	MontantTTC184 = montantTTC184;
}
public WAFAIMA_Formules() {
	//super();
}
public Integer getIdformule() {
	return idformule;
}
public void setIdformule(Integer idformule) {
	this.idformule = idformule;
}
public String getIdusage() {
	return idusage;
}
public void setIdusage(String idusage) {
	this.idusage = idusage;
}
public String getLibelle_usage() {
	return libelle_usage;
}
public void setLibelle_usage(String libelle_usage) {
	this.libelle_usage = libelle_usage;
}
public String getFormule() {
	return formule;
}
public void setFormule(String formule) {
	this.formule = formule;
}
public Integer getIdsegment() {
	return idsegment;
}
public void setIdsegment(Integer idsegment) {
	this.idsegment = idsegment;
}
public String getLibelle_Segment() {
	return Libelle_Segment;
}
public void setLibelle_Segment(String libelle_Segment) {
	Libelle_Segment = libelle_Segment;
}
public double getMontantHT89_92() {
	return MontantHT89_92;
}
public void setMontantHT89_92(double montantHT89_92) {
	MontantHT89_92 = montantHT89_92;
}
public double getTVA89_92() {
	return TVA89_92;
}
public void setTVA89_92(double tVA89_92) {
	TVA89_92 = tVA89_92;
}
public double getFraisGest89_92() {
	return FraisGest89_92;
}
public void setFraisGest89_92(double fraisGest89_92) {
	FraisGest89_92 = fraisGest89_92;
}
public double getMontantTTC89_92() {
	return MontantTTC89_92;
}
public void setMontantTTC89_92(double montantTTC89_92) {
	MontantTTC89_92 = montantTTC89_92;
}
public double getMontantHT93_184() {
	return MontantHT93_184;
}
public void setMontantHT93_184(double montantHT93_184) {
	MontantHT93_184 = montantHT93_184;
}
public double getTVA93_184() {
	return TVA93_184;
}
public void setTVA93_184(double tVA93_184) {
	TVA93_184 = tVA93_184;
}
public double getFraisGest93_184() {
	return FraisGest93_184;
}
public void setFraisGest93_184(double fraisGest93_184) {
	FraisGest93_184 = fraisGest93_184;
}
public double getMontantTTC93_184() {
	return MontantTTC93_184;
}
public void setMontantTTC93_184(double montantTTC93_184) {
	MontantTTC93_184 = montantTTC93_184;
}
public double getMontantHT184() {
	return MontantHT184;
}
public void setMontantHT184(double montantHT184) {
	MontantHT184 = montantHT184;
}
public double getTVA184() {
	return TVA184;
}
public void setTVA184(double tVA184) {
	TVA184 = tVA184;
}
public double getFraisGest184() {
	return FraisGest184;
}
public void setFraisGest184(double fraisGest184) {
	FraisGest184 = fraisGest184;
}
public double getMontantTTC184() {
	return MontantTTC184;
}
public void setMontantTTC184(double montantTTC184) {
	MontantTTC184 = montantTTC184;
}




}
