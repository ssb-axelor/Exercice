package com.axelor.apps.gestion.service;
import java.math.BigDecimal;
import javax.inject.Inject;

import com.axelor.inject.Beans;
import com.axelor.gestion.db.Ecriture;
import com.axelor.gestion.db.Compte;
import com.axelor.gestion.db.LigneFacture;
import com.axelor.gestion.db.LigneEcriture;
import com.axelor.gestion.db.Facture;
import com.axelor.gestion.db.repo.CompteRepository;
import com.axelor.gestion.db.repo.EcritureRepository;
import com.google.inject.persist.Transactional;

public class FactureService {

	@Inject
	EcritureRepository ecritureRepo;
	
	
	
	public Compte addCompteClient(){
		Compte compte =Beans.get(CompteRepository.class).findByLibelle("Client");
		if (compte == null){
			Compte compteClient= new Compte();
			compteClient.setCode("411");
			compteClient.setLibelle("Client");
			compteClient.setCompte("411-Client");
			return compteClient;
		}
		else
			return compte;
		
	}
	
	@Transactional
	public Facture calculer(Facture facture){
		BigDecimal sommeHT = new BigDecimal(0); 	
		BigDecimal sommeTTC =  new BigDecimal(0);
		for(LigneFacture ligneF : facture.getProduits())  {
			sommeHT= sommeHT.add(ligneF.getPrixHT());
			sommeTTC= sommeTTC.add(ligneF.getPrixTTC());
			
		}
		facture.setFactureHT(sommeHT);
		facture.setFactureTTC(sommeTTC);
		
		return facture;
	}
	
	
	
	@Transactional
	public Ecriture genererEcriture(Facture facture){
		
		Ecriture ecriture= new Ecriture();
		ecriture.setReferencePiece("Achat");
		ecriture.setDateOperation(facture.getDateFacture());	
			
		for ( LigneFacture f : facture.getProduits()) {
			LigneEcriture ligneE = new LigneEcriture();			
			ligneE.setCompte(f.getProduit().getCompte());
			ligneE.setCredit(f.getPrixTTC());
			
			ecriture.addEcriture(ligneE);
		}
			
		LigneEcriture ligneE = new LigneEcriture();
		ligneE.setCompte(addCompteClient());
		ligneE.setDebit(facture.getFactureTTC());
		
		ecriture.addEcriture(ligneE);
		
		return ecritureRepo.save(ecriture);
		
		
	}	
}
