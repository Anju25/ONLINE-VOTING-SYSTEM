package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CandidateDAO;

public class Candidate 
{

	public static List<Candidate> candidatelist=new ArrayList<Candidate>();
	
 private int candidate_id;
 private int candidate_id1;
 private String voter_id;
 private String name;
 private LocalDate date;
 private String gender;
 private String email;
 private String symbol;
 private String user_id;
 private String password;
 public Candidate(String voter_id, String name, LocalDate date, String gender, String email,
		String symbol, String user_id, String password,int candidate_id) {
	super();
	this.voter_id = voter_id;
	this.name = name;
	this.date = date;
	this.gender = gender;
	this.email = email;
	this.symbol = symbol;
	this.user_id = user_id;
	this.password = password;
	this.candidate_id = candidate_id;
}
public Candidate(String canname, LocalDate dob, String gender2, String email2, String symbol2) {
	this.name = canname;
	this.date = dob;
	this.gender = gender2;
	this.email = email2;
	this.symbol = symbol2;
}
public Candidate(int id11, int id22) 
{
	this.candidate_id=id11;
	this.candidate_id1=id22;
}
public Candidate(int id11) {
	this.candidate_id=id11;
}
public Candidate() {
	// TODO Auto-generated constructor stub
}
public int getCandidate_id1() {
	return candidate_id1;
}
public void setCandidate_id1(int candidate_id1) {
	this.candidate_id1 = candidate_id1;
}
public int getCandidate_id() {
	return candidate_id;
}
public void setCandidate_id(int candidate_id) {
	this.candidate_id = candidate_id;
}
public String getVoter_id() {
	return voter_id;
}
public void setVoter_id(String voter_id) {
	this.voter_id = voter_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getSymbol() {
	return symbol;
}
public void setSymbol(String symbol) {
	this.symbol = symbol;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public static void candidate() throws ClassNotFoundException, SQLException 
{
	   CandidateDAO candidatedao=new CandidateDAO();
	   System.out.printf("%55s","_______________________________");
	   System.out.println();
	   System.out.println();
	   System.out.printf("%50s",">>>> CANDIDATE-LIST <<<<");
	   System.out.println();
	   System.out.printf("%55s","_______________________________");
	   System.out.println();
	   System.out.println();
	   candidatelist=candidatedao.displaycandidatelist();
       System.out.println("\t"+"------------------------------------------------------------------");
       System.out.println("\t\t"+"ID"+"\t\t"+"NAME"+"\t\t"+"DOB"+"\t\t"+"SYMBOL");
       System.out.println("\t"+"------------------------------------------------------------------");
       for(Candidate candidate1:candidatelist)
       {
     	  System.out.printf("%20s %16s %17s %13s",candidate1.getCandidate_id(),candidate1.getName(),candidate1.getDate(),candidate1.getSymbol());
           System.out.println();
       }
       System.out.println("\t"+"------------------------------------------------------------------");
       System.out.println();
}

}
