package com.axelor.gestion.db;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.HashKey;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "GESTION_CLIENT")
public class Client extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GESTION_CLIENT_SEQ")
	@SequenceGenerator(name = "GESTION_CLIENT_SEQ", sequenceName = "GESTION_CLIENT_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String firstName;

	@NameColumn
	@NotNull
	@Index(name = "GESTION_CLIENT_LAST_NAME_IDX")
	private String lastName;

	private LocalDate dateOfBirth;

	@HashKey
	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String email;

	@Widget(massUpdate = true)
	@Size(max = 20)
	private String phone;

	@Widget(title = "About me")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "org.hibernate.type.TextType")
	private String notes;

	public Client() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof Client)) return false;

		final Client other = (Client) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		if (!Objects.equals(getEmail(), other.getEmail())) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(2021122027, this.getEmail());
	}

	@Override
	public String toString() {
		final MoreObjects.ToStringHelper tsh = MoreObjects.toStringHelper(this);

		tsh.add("id", this.getId());
		tsh.add("firstName", this.getFirstName());
		tsh.add("lastName", this.getLastName());
		tsh.add("dateOfBirth", this.getDateOfBirth());
		tsh.add("email", this.getEmail());
		tsh.add("phone", this.getPhone());

		return tsh.omitNullValues().toString();
	}
}
