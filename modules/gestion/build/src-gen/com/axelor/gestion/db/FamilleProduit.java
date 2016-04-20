package com.axelor.gestion.db;

import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "GESTION_FAMILLE_PRODUIT")
public class FamilleProduit extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_FAMILLE_PRODUIT_SEQ")
	@SequenceGenerator(name = "GESTION_FAMILLE_PRODUIT_SEQ", sequenceName = "GESTION_FAMILLE_PRODUIT_SEQ", allocationSize = 1)
	private Long id;

	@Widget(title = "Code de la catégorie")
	@NotNull
	private String codeCategorie;

	@Widget(title = "Libellé de la catégorie")
	@NameColumn
	@NotNull
	@Index(name = "GESTION_FAMILLE_PRODUIT_LIBELLE_CATEGORIE_IDX")
	private String libelleCategorie;

	public FamilleProduit() {
	}

	public FamilleProduit(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof FamilleProduit)) return false;

		final FamilleProduit other = (FamilleProduit) obj;
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
		tsh.add("codeCategorie", this.getCodeCategorie());
		tsh.add("libelleCategorie", this.getLibelleCategorie());

		return tsh.omitNullValues().toString();
	}
}
