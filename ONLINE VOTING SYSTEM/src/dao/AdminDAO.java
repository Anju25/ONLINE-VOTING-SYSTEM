package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class AdminDAO
{
public boolean check(String username, String password) throws ClassNotFoundException, SQLException 
{
	Connection con=ConnectionManager.getConnection();
	boolean returnvalue=false;
	if(con!=null)
	{
		//System.out.println("connection established");
		String sql="select * from admin";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
	   while(rs.next())
		{
			if(rs.getString(1).equalsIgnoreCase(username) && rs.getString(2).equalsIgnoreCase(password))
			{
				returnvalue=true;
			}
			else
			{
				returnvalue=false;
			}
		}
		
	}
	return returnvalue;	
}
}
