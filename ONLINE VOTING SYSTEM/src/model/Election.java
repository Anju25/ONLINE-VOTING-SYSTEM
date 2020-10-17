package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.CandidateDAO;
import dao.ElectionDAO;


public class Election 
{
	public static List<Candidate> allcandidid=new ArrayList<Candidate>();
	public static List<Candidate> particularcandidid=new ArrayList<Candidate>();	
	
   private String eleid;
   private String name;
   private LocalDate date;
  // private LocalDateTime startdatetime;
   //private LocalDateTime enddatetime;
   private String desc;
   private int canid;
   
   
   
public Election(String name, LocalDate date, String desc) {
	super();
	this.name = name;
	this.date=date;
	this.desc = desc;
}
public Election(String eleid, String name, LocalDate date, String desc) {
	super();
	this.eleid = eleid;
	this.name = name;
	this.date=date;
	this.desc = desc;
}
public Election(String eleid, int canid) {
	super();
	this.eleid = eleid;
	this.canid = canid;
}
public Election() {
	// TODO Auto-generated constructor stub
}
public String getEleid() {
	return eleid;
}
public void setEleid(String eleid) {
	this.eleid = eleid;
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

public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public int getCanid() {
	return canid;
}
public void setCanid(int canid) {
	this.canid = canid;
}
   
/*public static void elections() throws NumberFormatException, IOException, ClassNotFoundException, SQLException 
{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	CandidateDAO candidatedao=new CandidateDAO();
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  
	  ElectionDAO electiondao=new ElectionDAO();
	  System.out.println("1.ADD 2.MODIFY THE DATE TIME OF ELECTION 3.REMOVE 4.VIEW");
	  System.out.println("ENTER YOUR CHOICE");
	  int choice=Integer.parseInt(br.readLine());
	switch(choice)
	{
	case 1: System.out.println("provide election details");
	        System.out.println("Enter Election name");
	        String name=br.readLine();
	        System.out.println("Enter the start date and time(dd-mm-yyyy HH.mm.ss)");
	        String startdateandtime=br.readLine();
	        LocalDateTime start=LocalDateTime.parse(startdateandtime,formatter);
	        System.out.println("Enter the end date and time");
	        String enddateandtime=br.readLine();
	        LocalDateTime end=LocalDateTime.parse(enddateandtime,formatter);
	        System.out.println("provide short description of election");
	        String desc=br.readLine();
	        Election election=new Election(name,start,end,desc);
	        System.out.println("SELECT THE CANDIDATE FOR ELECTION");
	        System.out.println("DISPLAYING THE CANDIDATE DETAILS");
	        System.out.println();
	     
	      /*  candidatelist=candidatedao.displaycandidatelist();
	        System.out.println("\t"+"------------------------------------------------------------------");
	        System.out.println("\t"+"ID"+"\t\t"+"NAME"+"\t\t"+"DOB"+"\t\t"+"SYMBOL");
	        System.out.println("\t"+"------------------------------------------------------------------");
	        for(Candidate candidate1:candidatelist)
	        {
	      	  System.out.println("\t"+candidate1.getCandidate_id()+"\t\t"+candidate1.getName()+"\t\t"+candidate1.getDate()+"\t\t"+candidate1.getSymbol());
	        }*/
	       /* System.out.println();
	        System.out.println("1.ALL CANDIDATE 2.PARTICULAR CANDIDATES");
	        System.out.println("Enter your choice");
	        int choose=Integer.parseInt(br.readLine());
	        switch(choose)
	        {
	        case 1: allcandidid=electiondao.allcandidate();
	                System.out.println("ALL CANDIDATES ARE CHOOSEN");
	                int row=electiondao.insertintoelection1(election,allcandidid);
			        if(row==1)
			        {
			        	System.out.println("ELECTIOIN DETAILS ADDED SUCCESSFULLY");
			        }
			        else
			        {
			        	System.out.println("NOT ADDED");
			        }
			        for(Candidate candi:allcandidid)
			        {
			        	System.out.println(candi.getCandidate_id());
			        }
	                 break;
	        case 2: System.out.println("Enter the candidate id's for election");
	                int id1=Integer.parseInt(br.readLine());
	                int id2=Integer.parseInt(br.readLine());
	                particularcandidid=electiondao.particularcandidate(id1, id2);
	                System.out.println("particular candidate choosen");
	                int row1=electiondao.insertintoelection2(election,particularcandidid);
			        if(row1==1)
			        {
			        	System.out.println("ELECTIOIN DETAILS ADDED SUCCESSFULLY");
			        }
			        else
			        {
			        	System.out.println("NOT ADDED");
			        }
			        for(Candidate candi:particularcandidid)
			        {
			        	System.out.println(candi.getCandidate_id());
			        }
	                break;
	        }
	        
	}

}*/
   
}
