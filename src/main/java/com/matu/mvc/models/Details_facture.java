package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Details_facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String numfacture;
	private String police;
	private String matricule;
	private String Client;
	private double pht;
	private double taxe;
	private double pttc;

	
	public Details_facture() {
		super();
	}

	

	public Details_facture(String police, String matricule, String client,double pht,double taxe,double pttc) {
		super();
		this.police = police;
		this.matricule = matricule;
		this.pht = pht;
		Client = client;
		this.taxe = taxe;
		this.pttc = pttc;
	}



	public double getPttc() {
		return pttc;
	}



	public void setPttc(double pttc) {
		this.pttc = pttc;
	}



	public double getTaxe() {
		return taxe;
	}



	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}



	public double getPht() {
		return pht;
	}



	public void setPht(double pht) {
		this.pht = pht;
	}



	public String getClient() {
		return Client;
	}



	public void setClient(String client) {
		Client = client;
	}



	public String getPolice() {
		return police;
	}


	public void setPolice(String police) {
		this.police = police;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	
	
	
}
