package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Candidate;
import model.Election;
import model.Voter;
import oracle.sql.DATE;
import utility.ConnectionManager;

public class ElectionDAO 
{
public static List<Election> electionlist=new ArrayList<Election>();
public int insertintoelection(Election election) throws ClassNotFoundException, SQLException 
	{
		 Connection con=ConnectionManager.getConnection();
		 Date date=Date.valueOf(election.getDate());
		 int row=0; int r=0;
		 String Electionid=generateElectionid();
		 String sql="insert into election values(?,?,?,?)";
		 PreparedStatement st1=con.prepareStatement(sql);
		 st1.setString(1,Electionid);
		 st1.setString(2, election.getName());
		 st1.setString(3,election.getDesc());
		 st1.setDate(4, date);
		 row=st1.executeUpdate();
		 return row;
	}


	private static String generateElectionid() {
		String number="0123456789";
		StringBuilder sb=new StringBuilder(number.length());
		for(int i=0;i<=3;i++)
		{
			int index=(int)(number.length()*Math.random());
			sb.append(number.charAt(index));
		}
		return sb.toString();
	}



	public int update(String id, LocalDate d1) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionManager.getConnection();
		 Date date=Date.valueOf(d1);
		PreparedStatement stmt = con.prepareStatement("update election set dateofelection = ? where election_id = ?");

		stmt.setDate(1, date);
		stmt.setString(2, id);

		int result = stmt.executeUpdate();
		con.close();
		return result;
	}


	public List<Election> display() throws ClassNotFoundException, SQLException
	{
		Connection con = ConnectionManager.getConnection();
		String sql="select * from election";
		Statement st=con.createStatement();
		   ResultSet rs=st.executeQuery(sql);
		   while(rs.next())
		   {
			   String id=rs.getString(1);
			   String name=rs.getString(2);
			   String desc=rs.getString(3);
			   LocalDate d=rs.getDate(4).toLocalDate();
			   Election e=new Election(id,name,d,desc);
			   electionlist.add(e);
		   }
		return electionlist;
	}
	
	
}
/*public static List<Candidate> allcandidid=new ArrayList<Candidate>();
public static List<Candidate> particularcandidid=new ArrayList<Candidate>();	

public List<Candidate> particularcandidate(int id1) throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();
	 String sql="select candidate_id from candidate where candidate_id='"+id1+"'";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   int id11=0; int id22=0;
	   while(rs.next())
	   {
		   id11=rs.getInt("candidate_id");
		   Candidate id=new Candidate(id11);
		   particularcandidid.add(id);	
	   }
	   st.close();
	return particularcandidid;
}

public List<Candidate> allcandidate() throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();
	 String sql="select  candidate_id from candidate";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   
	   while(rs.next())
	   {
		   int id11=rs.getInt("candidate_id");
		   Candidate id=new Candidate(id11);
		   allcandidid.add(id);	   
	   }
	return allcandidid;
}*/


/*	private void votedb1(String electionid,List<Candidate> allcandidid2) throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();
	 String sql1="insert into votedb values(?,?)";
	 PreparedStatement st3=con.prepareStatement(sql1);
	 int r=0;
	 for(Candidate candid:allcandidid2)
	 {
		 st3.setString(1,electionid);
         st3.setInt(2, candid.getCandidate_id());
		 r= st3.executeUpdate();
	 }
	 if(r==0)
	 {
		 System.out.println("not added to votedb");
	 }
	 else
	 {
		 System.out.println("added");
	 }
	
}



public int insertintoelection2(Election election, List<Candidate> particularcandidid2) throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();
	 String Electionid=generateElectionid();
	 String sql="insert into election values(?,?,?,?,?)";
	 PreparedStatement st1=con.prepareStatement(sql);
	 st1.setString(1,Electionid);
	 st1.setString(2, election.getName());
	 st1.setTimestamp(3,Timestamp.valueOf(election.getStartdatetime()));
	 st1.setTimestamp(4,Timestamp.valueOf(election.getEnddatetime()));
	 st1.setString(5, election.getDesc());
	 int row1=st1.executeUpdate();
	 votedb2(Electionid,particularcandidid2);
	 return row1;
}

private void votedb2(String electionid, List<Candidate> particularcandidid2) throws ClassNotFoundException, SQLException
{
	 Connection con=ConnectionManager.getConnection();
	 Statement st2=con.createStatement();
	 ResultSet rs=st2.executeQuery("commit");
	 
	 String sql1="insert into votedb values(?,?)";
	 PreparedStatement st3=con.prepareStatement(sql1);
	 
	 for(Candidate candid:particularcandidid2)
	 {
		 st3.setString(1,electionid);
		 st3.setInt(2, candid.getCandidate_id());
	 }
	
}*/

