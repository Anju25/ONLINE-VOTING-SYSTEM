package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class countDAO {

	public boolean check1(String voterid) throws ClassNotFoundException, SQLException 
	{
		 Connection con=ConnectionManager.getConnection();
		 boolean val=false;
		 String sql="select voter_id from voter_id";
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(sql);
		 while(rs.next())
		 {
			 if(rs.getString("voter_id").equals(voterid))
			 {
				 val=true;
				 break;
			 }
			 else
			 {
				 val=false;
				 break;
			 }
		 }
		 st.close();
		return val;
	}

	public boolean check2(int candidateid) throws ClassNotFoundException, SQLException
	{
		 Connection con=ConnectionManager.getConnection();
		 boolean val=false;
		 String sql="select candidate_id from candidate";
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(sql);
		 while(rs.next())
		 {
			 int id=rs.getInt(1);
			 if(id==candidateid)
			 {
				val=true;
				 break;
			 }
			 else
			 {
				 val=false;
				 break;
			 }
		 }
		 st.close();
		return val;
	}

public boolean check3(String electionid) throws ClassNotFoundException, SQLException 
	{
		 Connection con=ConnectionManager.getConnection();
		 boolean val=false;
		 String sql="select election_id from election";
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(sql);
		 while(rs.next())
		 {
			 String eid=rs.getString(1);
			 if(eid.equals(electionid))
			 {
				 val=true;
				 break;
			 }
			 else
			 {
				 val=false;
				 break;
			 }
		 }
		 st.close();
		return val;
	}

	public int insertintocounting(String voterid, int candidateid, String electionid) throws ClassNotFoundException, SQLException
	{
		 int i=0;
		 Connection con=ConnectionManager.getConnection();
		 String sql="insert into counting values(?,?,?,?)";
		 PreparedStatement st1=con.prepareStatement(sql);
		 st1.setInt(1,i);
		 st1.setString(2, electionid);
		 st1.setString(3, voterid);
		 st1.setInt(4,candidateid);
		 int row=st1.executeUpdate();
		 System.out.println(row);
		return 0;
	}

	public boolean duplicatecheck(String voterid) throws ClassNotFoundException, SQLException 
	{ 
	Connection con=ConnectionManager.getConnection();
	 String sql="select voter_id from counting";
	 Statement st=con.createStatement();
	 ResultSet rs=st.executeQuery(sql);
	 if(rs==null)
	 {
		 System.out.println("fist record to insert");
	 }
	 else
	 {
		 while(rs.next())
		 {
			 String vid=rs.getString(3);
			 if(rs.getString(3).equals(voterid))
			 {
				 return true;
			 }
		 }
	}
	 return false;
	}

	

}
