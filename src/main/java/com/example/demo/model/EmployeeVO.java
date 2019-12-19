package com.example.demo.model;

public class EmployeeVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String passport;

	public EmployeeVO() {
		super();
	}

	public EmployeeVO(Long id, String name, String passportNumber) {
		super();
		this.id = id;
		this.name = name;
		this.passport = passportNumber;
	}

	public EmployeeVO(String name, String passportNumber) {
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
		return String.format("Employee VO [id=%s, name=%s, passportNumber=%s]", id, name, passport);
	}

}
