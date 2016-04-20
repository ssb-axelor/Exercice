package com.axelor.apps.gestion.web;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import javax.inject.Inject;
import com.axelor.apps.gestion.service.FactureService;
import com.axelor.gestion.db.Facture;
import com.axelor.gestion.db.repo.FactureRepository;
import com.axelor.inject.Beans;
import com.axelor.gestion.db.Ecriture;
import com.axelor.meta.schema.actions.ActionView;


public class FactureController {
	
	
	@Inject
	private FactureService factureService;
	
	@Inject
	private FactureRepository factureRepo;
	
	
public void calculer(ActionRequest request, ActionResponse response){
		
		Facture facture = request.getContext().asType(Facture.class);
		FactureService factureService = Beans.get(FactureService.class);
		facture  = factureRepo.find(facture.getId());
		factureService.calculer(facture);
		response.setReload(true);
	}

	public void genererEcriture(ActionRequest request, ActionResponse response){
		
		Facture facture = request.getContext().asType(Facture.class);
		Ecriture ecriture = factureService.genererEcriture(facture);
		response.setView ( ActionView.define( "Ecriture Comptable" )
				.model(Ecriture.class.getName())
				.add("form", "ecriture-form")
				.add("grid", "ecriture-grid")
				.param("forceTitle", "true")
				.context("_showRecord", ecriture.getId().toString())
				.map() );

		
	}
	


}
