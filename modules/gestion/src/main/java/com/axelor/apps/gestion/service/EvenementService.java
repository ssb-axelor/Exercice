package com.axelor.apps.gestion.service;
import com.axelor.gestion.db.Evenement;
import com.google.inject.persist.Transactional;
import org.joda.time.Duration;
import org.joda.time.Interval;
public class EvenementService {

	
	@Transactional
	public Evenement calculerDuree(Evenement evenement){

		long seconds =computeDuration(evenement).getStandardSeconds();
		evenement.setDuree(seconds);
		return evenement;
	}
	
	
	@Transactional
	public Duration computeDuration(Evenement evenement)  {
		
		return new Interval(evenement.getDebutDateT().toDateTime(), evenement.getFinDateT().toDateTime()).toDuration();
		
	}
}
