package com.axelor.gestion.db;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "GESTION_PRODUIT")
public class Produit extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_PRODUIT_SEQ")
	@SequenceGenerator(name = "GESTION_PRODUIT_SEQ", sequenceName = "GESTION_PRODUIT_SEQ", allocationSize = 1)
	private Long id;

	@NameColumn
	@NotNull
	@Index(name = "GESTION_PRODUIT_PRODUCT_NAME_IDX")
	private String productName;

	@NotNull
	@Digits(integer = 6, fraction = 2)
	private BigDecimal price = BigDecimal.ZERO;

	@NotNull
	@Index(name = "GESTION_PRODUIT_COMPTE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Compte compte;

	@NotNull
	@Index(name = "GESTION_PRODUIT_CATEGORIE_IDX")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private FamilleProduit categorie;

	public Produit() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public FamilleProduit getCategorie() {
		return categorie;
	}

	public void setCategorie(FamilleProduit categorie) {
		this.categorie = categorie;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Produit)) return false;

		final Produit other = (Produit) obj;
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
		tsh.add("productName", this.getProductName());
		tsh.add("price", this.getPrice());

		return tsh.omitNullValues().toString();
	}
}
