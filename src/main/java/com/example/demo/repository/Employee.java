package com.example.demo.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "select s from Employee s")
})
@Entity
public class Employee extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSPORT")
	private String passport;

	public Employee() {
		super();
	}

	public Employee(Long id, String name, String passportNumber) {
		super();
		this.id = id;
		this.name = name;
		this.passport = passportNumber;
	}

	public Employee(String name, String passportNumber) {
		super();
		this.name = name;
		this.passport = passportNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%s, name=%s, passportNumber=%s]", id, name, passport);
	}

}
