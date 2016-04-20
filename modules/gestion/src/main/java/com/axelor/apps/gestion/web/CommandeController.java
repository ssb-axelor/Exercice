package com.axelor.apps.gestion.web;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.common.base.Joiner;

import java.util.List;

import javax.inject.Inject;

import com.axelor.apps.gestion.service.CommandeService;
import com.axelor.gestion.db.Commande;
import com.axelor.gestion.db.Facture;
import com.axelor.meta.schema.actions.ActionView;


public class CommandeController {
	
	
	@Inject
	private CommandeService commandeService;
	
	
	public void genererFacture(ActionRequest request, ActionResponse response){
		
		Commande commande = request.getContext().asType(Commande.class);
		Facture facture = commandeService.creerFacture(commande);
		response.setView ( ActionView.define( "facture" )
				.model(Facture.class.getName())
				.add("form", "facture-form")
				.add("grid", "facture-grid")
				.param("forceTitle", "true")
				.context("_showRecord", facture.getId().toString())
				.map() );
				
	}
	
	public void facturerCommandesRetard(ActionRequest request, ActionResponse response){
		
//		Commande commande = request.getContext().asType(Commande.class);
		List<Long> listId = commandeService.facturerCommandesRetard();
		if(!listId.isEmpty()){
		response.setView ( ActionView.define( "facture" )
				.model(Facture.class.getName())
				.add("grid", "facture-grid")
				.add("form", "facture-form")
				.param("forceTitle", "true")
				.domain("self.id in ("+Joiner.on(",").join(listId)+")")
				.map() );
		}
		else 
			response.setFlash("Aucune commande en retard à facturer");
			
				
	}
	
}
