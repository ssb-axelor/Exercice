package com.axelor.gestion.db;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.joda.time.LocalDateTime;

import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.User;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GESTION_EVENEMENT")
public class Evenement extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_EVENEMENT_SEQ")
	@SequenceGenerator(name = "GESTION_EVENEMENT_SEQ", sequenceName = "GESTION_EVENEMENT_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String sujet;

	private String contenu;

	@Widget(title = "Date de début")
	@NotNull
	private LocalDateTime debutDateT;

	@Widget(title = "Date de fin")
	@NotNull
	private LocalDateTime finDateT;

	@Widget(title = "Durée (hh:mm)")
	private Long duree = 0L;

	@Widget(title = "Statut", readonly = true, selection = "evenement.status.select")
	private Integer statusSelect = 1;

	@Widget(title = "Assigné à")
	@Index(name = "GESTION_EVENEMENT_UTILISATEUR_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User utilisateur;

	@Widget(title = "Date limite")
	@NotNull
	private LocalDateTime limiteDateT;

	@Widget(title = "Associé à", selection = "evenement.related.to.select")
	private String relatedTo1Select;

	private Integer relatedTo1SelectId = 0;

	@Widget(title = "Associé à", selection = "evenement.related.to.select")
	private String relatedTo2Select;

	private Integer relatedTo2SelectId = 0;

	public Evenement() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getDebutDateT() {
		return debutDateT;
	}

	public void setDebutDateT(LocalDateTime debutDateT) {
		this.debutDateT = debutDateT;
	}

	public LocalDateTime getFinDateT() {
		return finDateT;
	}

	public void setFinDateT(LocalDateTime finDateT) {
		this.finDateT = finDateT;
	}

	public Long getDuree() {
		return duree == null ? 0L : duree;
	}

	public void setDuree(Long duree) {
		this.duree = duree;
	}

	public Integer getStatusSelect() {
		return statusSelect == null ? 0 : statusSelect;
	}

	public void setStatusSelect(Integer statusSelect) {
		this.statusSelect = statusSelect;
	}

	public User getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}

	public LocalDateTime getLimiteDateT() {
		return limiteDateT;
	}

	public void setLimiteDateT(LocalDateTime limiteDateT) {
		this.limiteDateT = limiteDateT;
	}

	public String getRelatedTo1Select() {
		return relatedTo1Select;
	}

	public void setRelatedTo1Select(String relatedTo1Select) {
		this.relatedTo1Select = relatedTo1Select;
	}

	public Integer getRelatedTo1SelectId() {
		return relatedTo1SelectId == null ? 0 : relatedTo1SelectId;
	}

	public void setRelatedTo1SelectId(Integer relatedTo1SelectId) {
		this.relatedTo1SelectId = relatedTo1SelectId;
	}

	public String getRelatedTo2Select() {
		return relatedTo2Select;
	}

	public void setRelatedTo2Select(String relatedTo2Select) {
		this.relatedTo2Select = relatedTo2Select;
	}

	public Integer getRelatedTo2SelectId() {
		return relatedTo2SelectId == null ? 0 : relatedTo2SelectId;
	}

	public void setRelatedTo2SelectId(Integer relatedTo2SelectId) {
		this.relatedTo2SelectId = relatedTo2SelectId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Evenement)) return false;

		final Evenement other = (Evenement) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		final MoreObjects.ToStringHelper tsh = MoreObjects.toStringHelper(this);

		tsh.add("id", this.getId());
		tsh.add("sujet", this.getSujet());
		tsh.add("contenu", this.getContenu());
		tsh.add("debutDateT", this.getDebutDateT());
		tsh.add("finDateT", this.getFinDateT());
		tsh.add("duree", this.getDuree());
		tsh.add("statusSelect", this.getStatusSelect());
		tsh.add("limiteDateT", this.getLimiteDateT());
		tsh.add("relatedTo1Select", this.getRelatedTo1Select());
		tsh.add("relatedTo1SelectId", this.getRelatedTo1SelectId());
		tsh.add("relatedTo2Select", this.getRelatedTo2Select());
		tsh.add("relatedTo2SelectId", this.getRelatedTo2SelectId());

		return tsh.omitNullValues().toString();
	}
}
