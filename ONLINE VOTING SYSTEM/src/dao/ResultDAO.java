package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class ResultDAO {

	public void result() throws ClassNotFoundException, SQLException 
	{
		int row1=0;
		 Connection con=ConnectionManager.getConnection();
		 String sql=" select candidate_id,count(*) as votes from counting group by candidate_id";
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery(sql);
		 String sql1="insert into results values(?,?)";
		 PreparedStatement st1=con.prepareStatement(sql1);
		 System.out.println("CANDIDATE_ID"+" "+"VOTES");
		 while(rs.next())
		 {
		     int id=rs.getInt(1);
		     int count=rs.getInt(2);
		     st1.setInt(1,id);
		     st1.setInt(2,count);
		     row1=st1.executeUpdate();
		     
		  }
		if(row1==1)
		{
			//System.out.println("inserted");
			
			display();
			//System.out.println("********");
		}
		else
		{
			System.out.println("not inserted");
		}
	}

	public void display() throws ClassNotFoundException, SQLException
	{
		Connection con=ConnectionManager.getConnection();
		String sql="select results.cid,results.votes, candidate.name ,candidate.dob,candidate.gender,candidate.symbol from candidate,results where candidate.candidate_id=results.cid";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		if(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getDate(4)+" "+rs.getNString(5)+" "+rs.getString(6));
		}
	}

}
