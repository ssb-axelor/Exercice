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
@Table(name = "GESTION_COMPTE")
public class Compte extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_COMPTE_SEQ")
	@SequenceGenerator(name = "GESTION_COMPTE_SEQ", sequenceName = "GESTION_COMPTE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_COMPTE_CODE_IDX")
	private String code;

	@NotNull
	private String libelle;

	@Widget(readonly = true)
	@NameColumn
	@Index(name = "GESTION_COMPTE_COMPTE_IDX")
	private String compte;

	public Compte() {
	}

	public Compte(String code) {
		this.code = code;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Compte)) return false;

		final Compte other = (Compte) obj;
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
		tsh.add("code", this.getCode());
		tsh.add("libelle", this.getLibelle());
		tsh.add("compte", this.getCompte());

		return tsh.omitNullValues().toString();
	}
}
