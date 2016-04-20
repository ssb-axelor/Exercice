package com.axelor.apps.gestion.service;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.joda.time.LocalDate;

import com.axelor.app.gestion.db.ICommande;
import com.axelor.app.gestion.db.IFacture;
import com.axelor.gestion.db.Commande;
import com.axelor.gestion.db.Facture;
import com.axelor.gestion.db.LigneCommande;
import com.axelor.gestion.db.LigneFacture;
import com.axelor.gestion.db.repo.CommandeRepository;
import com.axelor.gestion.db.repo.FactureRepository;
import com.google.inject.persist.Transactional;

public class CommandeService {

	@Inject
	FactureRepository factureRepo;
	
	@Inject
	CommandeRepository commandeRepo; 

	
	@Transactional
	public Facture creerFacture(Commande commande){
		
		Facture facture= new Facture();
		facture.setStatusSelect(IFacture.STATUS_BROUILLON);
		facture.setDateFacture(LocalDate.now());
		facture.setNom(commande.getNom());
		facture.setFactureHT(commande.getCommandeHT());
		facture.setFactureTTC(commande.getCommandeTTC());
	
			
		for ( LigneCommande c : commande.getProduits()) {
			LigneFacture ligneF = new LigneFacture();
			ligneF.setQuantite(c.getQuantity());
			ligneF.setPrice(c.getPrice());
			ligneF.setPrixHT(c.getPriceHT());
			ligneF.setPrixTTC(c.getPriceTTC());
			ligneF.setTaux(c.getTaux());
			ligneF.setProduit(c.getProduit());
			ligneF.setCompte(c.getProduit().getCompte().getCompte());
			facture.addProduit(ligneF);
		}
		return factureRepo.save(facture);
				
	}	
	
	public List<Long> facturerCommandesRetard(){	
		List<Commande> commandeList  = (List<Commande>) commandeRepo.all().filter("self.datePrevFacture< ?1 AND self.factureGeneree = ?2 AND self.statusSelect = ?3",LocalDate.now(),0, ICommande.STATUS_VALIDE).fetch();
		List<Long> listId = new ArrayList<Long>();
		
		for(Commande commande : commandeList)  {
			commandeRepo.find(commande.getId()).setFactureGeneree(true);
			commandeRepo.find(commande.getId()).setStatusSelect(ICommande.STATUS_FACTURE);			
			Facture facture = creerFacture(commande);
			listId.add(facture.getId());
			
		}
		return listId;
	}
}
