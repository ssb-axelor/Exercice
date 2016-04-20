package com.axelor.apps.gestion.web;

import javax.inject.Inject;

import com.axelor.apps.gestion.service.EvenementService;
import com.axelor.gestion.db.Evenement;
import com.axelor.gestion.db.repo.EvenementRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class EvenementController {

	
	
	@Inject
	private EvenementRepository evenementRepo;
	
	public void calculerDuree(ActionRequest request, ActionResponse response){
		
		Evenement evenement = request.getContext().asType(Evenement.class);
		EvenementService evenementService = Beans.get(EvenementService.class);
		if (evenement.getId() != null)	
			evenement  = evenementRepo.find(evenement.getId());					
		
		evenementService.calculerDuree(evenement);
		response.setValue("duree",evenement.getDuree());
			
	}

}
