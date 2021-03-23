package com.smoothstack.utopia.entities;

/**
 * @author dyltr Objects to hold travelers and employees
 */
public class User {
	// auto increment in SQL
	private int id;
	private int roleId;
	private String givenName;
	private String familyName;
	private String username;
	private String email;
	private String password;
	private String phone;

	public User(int id, int roleId, String givenName, String familyName, String username, String email, String password,
			String phone) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.givenName = givenName;
		this.familyName = familyName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public User(String givenName, String familyName, String username, String email, String password, String phone) {
		super();
		id = 0;
		roleId = 0;
		this.givenName = givenName;
		this.familyName = familyName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
