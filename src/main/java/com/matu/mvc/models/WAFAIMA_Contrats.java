package com.matu.mvc.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
public class WAFAIMA_Contrats {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_contrat;
private String  datedebut;
private String  datefin;
private Integer idclient;
private Integer idavenant;
private Integer idformule;
private Integer idhistorique;
private String  matricule;
private String  police;
private String  usage;
private String  nom_client;
//@Transient
private String  formule;
private String  usage_formule;
private String  marque;
private String  datemise;
private String  attestation;
private String  puissance;
private String  tonnage;
private String  tel_client;
private String  adresse;
private String  ville_client;
private String  nb_places;
private String  categorie_vehicule;
//private Integer montant;
private String essence;
private String idscat;
@Column(nullable = true)
private int periode_assure;
//
@Column(nullable = true)
private double montantttc184;
@Column(nullable = true)
private double montantTTC93_184;
@Column(nullable = true)
private double montantTTC89_92;
private String date_creation;

private Integer idquittance;



public Integer getIdquittance() {
	return idquittance;
}

public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
}

public double getMontantTTC93_184() {
	return montantTTC93_184;
}

public void setMontantTTC93_184(double montantTTC93_184) {
	this.montantTTC93_184 = montantTTC93_184;
}

public double getMontantTTC89_92() {
	return montantTTC89_92;
}

public void setMontantTTC89_92(double montantTTC89_92) {
	this.montantTTC89_92 = montantTTC89_92;
}

public int getPeriode_assure() {
	return periode_assure;
}

public void setPeriode_assure(int periode_assure) {
	this.periode_assure = periode_assure;
}




//private String  monStant;


//
//public String getMontant() {
//	return montant;
//}
//
//
//
//
//public void setMontant(String montant) {
//	this.montant = montant;
//}



public String getDate_creation() {
	return date_creation;
}

public void setDate_creation(String date_creation) {
	this.date_creation = date_creation;
}

//
public double getMontantttc184() {
	return montantttc184;
}




public void setMontantttc184(Double montantttc184) {
	this.montantttc184 = montantttc184;
}




public String getIdscat() {
	return idscat;
}




public void setIdscat(String idscat) {
	this.idscat = idscat;
}




public String getEssence() {
	return essence;
}




public void setEssence(String essence) {
	this.essence = essence;
}




public Integer getIdhistorique() {
	return idhistorique;
}




public void setIdhistorique(Integer idhistorique) {
	this.idhistorique = idhistorique;
}




//public Integer getMontant() {
//	return montant;
//}




//public void setMontant(Integer montant) {
//	this.montant = montant;
//}




public String getCategorie_vehicule() {
	return categorie_vehicule;
}




public void setCategorie_vehicule(String categorie_vehicule) {
	this.categorie_vehicule = categorie_vehicule;
}




public String getVille_client() {
	return ville_client;
}




public void setVille_client(String ville_client) {
	this.ville_client = ville_client;
}




public String getNb_places() {
	return nb_places;
}




public void setNb_places(String nb_places) {
	this.nb_places = nb_places;
}




public String getVille_cient() {
	return ville_client;
}




public void setVille_cient(String ville_cient) {
	this.ville_client = ville_cient;
}




public String getAdresse() {
	return adresse;
}




public void setAdresse(String adresse) {
	this.adresse = adresse;
}




public String getTel_client() {
	return tel_client;
}




public void setTel_client(String tel_client) {
	this.tel_client = tel_client;
}




public String getPuissance() {
	return puissance;
}




public void setPuissance(String puissance) {
	this.puissance = puissance;
}




public String getTonnage() {
	return tonnage;
}




public void setTonnage(String tonnage) {
	this.tonnage = tonnage;
}




public String getMarque() {
	return marque;
}




public String getAttestation() {
	return attestation;
}




public void setAttestation(String attestation) {
	this.attestation = attestation;
}




public String getDatemise() {
	return datemise;
}




public void setDatemise(String datemise) {
	this.datemise = datemise;
}




public void setMarque(String marque) {
	this.marque = marque;
}




public String getUsage_formule() {
	return usage_formule;
}




public void setUsage_formule(String usage_formule) {
	this.usage_formule = usage_formule;
}




public Integer getIdformule() {
	return idformule;
}




public void setIdformule(Integer id_formule) {
	this.idformule = id_formule;
}




public String getFormule() {
	return formule;
}




public void setFormule(String formule) {
	this.formule = formule;
}




public Integer getId_historique() {
	return idhistorique;
}




public void setId_historique(Integer id_historique) {
	this.idhistorique = id_historique;
}




public WAFAIMA_Contrats(String date_debut, String date_fin, Integer idclient, String matricule, String police,String usage,String nom_client,Integer id_avenant,Integer id_historique,Integer idformule) {
	super();
	this.datedebut = date_debut;
	this.datefin = date_fin;
	this.idclient = idclient;
	this.matricule = matricule;
	this.police = police;
	this.usage = usage;
	this.nom_client = nom_client;
	this.idavenant=id_avenant;
	this.idhistorique=id_historique;
	this.idformule=idformule;
}




public Integer getId_avenant() {
	return idavenant;
}




public void setId_avenant(Integer id_avenant) {
	this.idavenant = id_avenant;
}




public String getNom_client() {
	return nom_client;
}




public void setNom_client(String nom_client) {
	this.nom_client = nom_client;
}




public String getMatricule() {
	return matricule;
}




public void setMatricule(String matricule) {
	this.matricule = matricule;
}




public String getPolice() {
	return police;
}




public void setPolice(String police) {
	this.police = police;
}




public String getUsage() {
	return usage;
}




public void setUsage(String usage) {
	this.usage = usage;
}




public Integer getId_contrat() {
	return id_contrat;
}

public void setId_contrat(Integer id_contrat) {
	this.id_contrat = id_contrat;
}

public String getDate_debut() {
	return datedebut;
}

public void setDate_debut(String date_debut) {
	this.datedebut = date_debut;
}

public String getDate_fin() {
	return datefin;
}

public void setDate_fin(String date_fin) {
	this.datefin = date_fin;
}

public Integer getIdclient() {
	return idclient;
}

public void setIdclient(Integer idclient) {
	this.idclient = idclient;
}

public WAFAIMA_Contrats() {
	super();

}



}
