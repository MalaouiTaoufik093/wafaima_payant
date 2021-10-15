package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WAFAIMA_Quittances {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private Integer idquittance ;
private Integer quittance;
private Integer idsite;
private Integer idclient;
private Integer statut;
private Double mt_total;
private Double mt_wafaima;
private Double mt_fraisgestion;
private Double taxe;
private Double tva;
private String encaisse;
private Double mt_encaisse;
private String dateemis;
private Integer idformule;
private Integer idcontrat;
//**constructeurs**

public Integer getIdcontrat() {
	return idcontrat;
}
public void setIdcontrat(Integer idcontrat) {
	this.idcontrat = idcontrat;
}
public Integer getIdformule() {
	return idformule;
}
public void setIdformule(Integer idformule) {
	this.idformule = idformule;
}
public WAFAIMA_Quittances(Integer quittance, Integer idsite, Integer idclient, Integer statut, Double mt_total,
		Double mt_wafaima, Double mt_fraisgestion, Double taxe, Double tva, String encaisse, Double mt_encaisse,
		String dateemis) {
	super();
	this.quittance = quittance;
	this.idsite = idsite;
	this.idclient = idclient;
	this.statut = statut;
	this.mt_total = mt_total;
	this.mt_wafaima = mt_wafaima;
	this.mt_fraisgestion = mt_fraisgestion;
	this.taxe = taxe;
	this.tva = tva;
	this.encaisse = encaisse;
	this.mt_encaisse = mt_encaisse;
	this.dateemis = dateemis;
}
	public WAFAIMA_Quittances() {
	super();
}
	//**GETTERS AND SETTERS **
public Integer getIdquittance() {
		return idquittance;
}
	public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
}
public Integer getQuittance() {
	return quittance;
}
public void setQuittance(Integer quittance) {
	this.quittance = quittance;
}
public Integer getIdsite() {
	return idsite;
}
public void setIdsite(Integer idsite) {
	this.idsite = idsite;
}
public Integer getIdclient() {
	return idclient;
}
public void setIdclient(Integer idclient) {
	this.idclient = idclient;
}
public Integer getStatut() {
	return statut;
}
public void setStatut(Integer statut) {
	this.statut = statut;
}
public Double getMt_total() {
	return mt_total;
}
public void setMt_total(Double mt_total) {
	this.mt_total = mt_total;
}
public Double getMt_wafaima() {
	return mt_wafaima;
}
public void setMt_wafaima(Double mt_wafaima) {
	this.mt_wafaima = mt_wafaima;
}
public Double getMt_fraisgestion() {
	return mt_fraisgestion;
}
public void setMt_fraisgestion(Double mt_fraisgestion) {
	this.mt_fraisgestion = mt_fraisgestion;
}
public Double getTaxe() {
	return taxe;
}
public void setTaxe(Double taxe) {
	this.taxe = taxe;
}
public Double getTva() {
	return tva;
}
public void setTva(Double tva) {
	this.tva = tva;
}
public String getEncaisse() {
	return encaisse;
}
public void setEncaisse(String encaisse) {
	this.encaisse = encaisse;
}
public Double getMt_encaisse() {
	return mt_encaisse;
}
public void setMt_encaisse(Double mt_encaisse) {
	this.mt_encaisse = mt_encaisse;
}
public String getDateemis() {
	return dateemis;
}
public void setDateemis(String dateemis) {
	this.dateemis = dateemis;
}

}
