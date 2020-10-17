package model;

import java.time.LocalDate;

public class Voter 
{
   private String name;
   private String email;
   private String voter_id;
   private String gender;
   private String address;
   private LocalDate dob;
   private Long phone;
   private Long adhar;
   private int age;
   private String username;
   private String password;
public Voter(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Voter(String name, String email, String voter_id, String gender, String address,  LocalDate dob, Long phone,
		int age,Long adhar) {
	super();
	this.name = name;
	this.email = email;
	this.voter_id = voter_id;
	this.gender = gender;
	this.address = address;
	this.dob = dob;
	this.phone = phone;
	this.age = age;
	this.adhar=adhar;
}
   
public Voter(String name, String email, String gender, String address, LocalDate dob, Long phone, int age, Long adhar) {
	super();
	this.name = name;
	this.email = email;
	this.gender = gender;
	this.address = address;
	this.dob = dob;
	this.phone = phone;
	this.age = age;
	this.adhar=adhar;
}

public Voter() {
	// TODO Auto-generated constructor stub
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getVoter_id() {
	return voter_id;
}
public void setVoter_id(String voter_id) {
	this.voter_id = voter_id;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public LocalDate getDob() {
	return dob;
}
public void setDob(LocalDate dob) {
	this.dob = dob;
}
public Long getPhone() {
	return phone;
}
public void setPhone(Long phone) {
	this.phone = phone;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public Long getAdhar() {
	return adhar;
}

public void setAdhar(Long adhar) {
	this.adhar = adhar;
}
   
}
