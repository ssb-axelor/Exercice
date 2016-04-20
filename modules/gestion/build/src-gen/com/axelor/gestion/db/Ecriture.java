package com.axelor.gestion.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.joda.time.LocalDate;

import com.axelor.auth.db.AuditableModel;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "GESTION_ECRITURE")
public class Ecriture extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_ECRITURE_SEQ")
	@SequenceGenerator(name = "GESTION_ECRITURE_SEQ", sequenceName = "GESTION_ECRITURE_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private LocalDate dateOperation;

	@NotNull
	private String referencePiece;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ecriture", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LigneEcriture> ecritures;

	public Ecriture() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDate dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getReferencePiece() {
		return referencePiece;
	}

	public void setReferencePiece(String referencePiece) {
		this.referencePiece = referencePiece;
	}

	public List<LigneEcriture> getEcritures() {
		return ecritures;
	}

	public void setEcritures(List<LigneEcriture> ecritures) {
		this.ecritures = ecritures;
	}

	/**
	 * Add the given {@link LigneEcriture} item to the {@code ecritures}.
	 *
	 * <p>
	 * It sets {@code item.ecriture = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addEcriture(LigneEcriture item) {
		if (ecritures == null) {
			ecritures = new ArrayList<LigneEcriture>();
		}
		ecritures.add(item);
		item.setEcriture(this);
	}

	/**
	 * Remove the given {@link LigneEcriture} item from the {@code ecritures}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeEcriture(LigneEcriture item) {
		if (ecritures == null) {
			return;
		}
		ecritures.remove(item);
	}

	/**
	 * Clear the {@code ecritures} collection.
	 *
	 * <p>
	 * If you have to query {@link LigneEcriture} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearEcritures() {
		if (ecritures != null) {
			ecritures.clear();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Ecriture)) return false;

		final Ecriture other = (Ecriture) obj;
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
		tsh.add("dateOperation", this.getDateOperation());
		tsh.add("referencePiece", this.getReferencePiece());

		return tsh.omitNullValues().toString();
	}
}
