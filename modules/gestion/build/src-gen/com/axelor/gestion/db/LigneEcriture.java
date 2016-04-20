package com.axelor.gestion.db;

import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import com.axelor.auth.db.AuditableModel;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "GESTION_LIGNE_ECRITURE")
public class LigneEcriture extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_LIGNE_ECRITURE_SEQ")
	@SequenceGenerator(name = "GESTION_LIGNE_ECRITURE_SEQ", sequenceName = "GESTION_LIGNE_ECRITURE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	@Index(name = "GESTION_LIGNE_ECRITURE_ECRITURE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Ecriture ecriture;

	@NotNull
	@Index(name = "GESTION_LIGNE_ECRITURE_COMPTE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Compte compte;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal debit = BigDecimal.ZERO;

	@DecimalMin("0.0")
	@Digits(integer = 16, fraction = 4)
	private BigDecimal credit = BigDecimal.ZERO;

	public LigneEcriture() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Ecriture getEcriture() {
		return ecriture;
	}

	public void setEcriture(Ecriture ecriture) {
		this.ecriture = ecriture;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public BigDecimal getDebit() {
		return debit == null ? BigDecimal.ZERO : debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit == null ? BigDecimal.ZERO : credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof LigneEcriture)) return false;

		final LigneEcriture other = (LigneEcriture) obj;
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
		tsh.add("debit", this.getDebit());
		tsh.add("credit", this.getCredit());

		return tsh.omitNullValues().toString();
	}
}
