package com.axelor.apps.gestion.service;

import com.axelor.app.gestion.db.ICommande;
import com.axelor.gestion.db.Commande;
import com.axelor.gestion.db.repo.CommandeRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class CommandeStatusService {
	
	
	@Inject
	CommandeRepository commandeRepo;
	
	
	@Transactional
	public void valider(Commande commande)  {
		
		if(commande.getStatusSelect() == ICommande.STATUS_BROUILLON)
			commande.setStatusSelect(ICommande.STATUS_VALIDE);		
	
	}
		
	@Transactional
	public void facturer(Commande commande)  {
		
		if(commande.getStatusSelect() == ICommande.STATUS_VALIDE)
			commande.setStatusSelect(ICommande.STATUS_FACTURE);		
	}
	
	
	
	
	
	
	
	

}
