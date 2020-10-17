package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Candidate;
import model.Voter;
import utility.ConnectionManager;

public class VoterDAO 
{

public static List<Voter> citizenlist=new ArrayList<Voter>();
public static List<Voter> list=new ArrayList<Voter>();
public static List<String> reply=new ArrayList<String>();
public static List<Voter> citizenlist1=new ArrayList<Voter>();
 public int insert(Voter vote) throws ClassNotFoundException, SQLException 
    {
	
        Connection con=ConnectionManager.getConnection();
	    char[] randompassword=generaterandompassword();
	    String pwd=new String(randompassword);
	    char[] voterid=generatevoterid();
	    String vid=new String(voterid);
	    Date date=Date.valueOf(vote.getDob());
	    
	    // INSERTING INTO VOTER_ID TABLE//
	    
	    String sql="insert into voter_id values(?,?,?,?)";
	    PreparedStatement st1=con.prepareStatement(sql);
	    st1.setLong(1,vote.getAdhar());
	    st1.setString(2, vid);
	    st1.setString(3, vote.getEmail());
	    st1.setString(4, pwd);
	    int row=st1.executeUpdate();
	    System.out.println(row);
	    
	   //INSERTING INTO CITIZEN TABLE// 
	    
	   String sql1="insert into citizen values(?,?,?,?,?,?,?,?,?)";
	   PreparedStatement st=con.prepareStatement(sql1);
	    st.setString(1, vote.getName());
	    st.setDate(2,date);
	    st.setInt(3,vote.getAge());
	    st.setString(4,vote.getGender());
	    st.setLong(5, vote.getAdhar());
	    st.setLong(6,vote.getPhone());
	    st.setString(7,vote.getEmail());
	    st.setString(8,vote.getAddress());
	    st.setString(9,vid);
	    int r=st.executeUpdate();
	    //System.out.println(r);
	    Statement st2=con.createStatement();
		ResultSet rs=st2.executeQuery("commit");
	    st.close();
	    return r;
	}
	private static char[] generatevoterid() {
		String capitalletters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String number="0123456789";
		char[] voter_id=new char[9];
	    Random random=new Random();
		int i=0;
		while(i<9)
		{
			if(i>=3)
			{
				int randomnum=random.nextInt(number.length());
				voter_id[i]=number.charAt(randomnum);
			}
			else
			{
				int randomnum =random.nextInt(capitalletters.length());
				voter_id[i]=capitalletters.charAt(randomnum);
			}
			i++;
		}
		return voter_id;
	}
		
 private static char[] generaterandompassword() 
	{
	String capitalletters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lowerletters="abcdefghijklmnopqrstuvwxyz";
	String specialCharacters="!@#$";
	String numbers="0123456789";
	String values=capitalletters+lowerletters+specialCharacters+numbers;
	Random random=new Random();
	char[] password=new char[10];
	for(int i=0;i<10;i++)
	{
		int randomchar =random.nextInt(values.length());
		//System.out.println("random number"+" "+randomchar);
		password[i]=values.charAt(randomchar);
	}
	return password;
	
		
	}
public List<Voter> usernameandpassword(Long adhar) throws ClassNotFoundException, SQLException 
{
   Connection con=ConnectionManager.getConnection();	
   String sql="select username,password from voter_id where adhar_number='"+adhar+"'";
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery(sql);
  // rs.setLong()
    if(rs.next())
   {
	   String username=rs.getString("username");
	   String password=rs.getString("password");
	   Voter vote=new Voter(username,password);
	   list.add(vote);
   }
	   else
	   {
		   System.out.println("PLEASE CHECK YOUR PHONE NUMBER");
	   }
   
	return list;
}
public boolean check(String un, String password) throws ClassNotFoundException, SQLException 
{      
	   Connection con=ConnectionManager.getConnection();	
	   String sql="select * from voter_id";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   while(rs.next())
	   {
		   if(rs.getString("username").equals(un) && rs.getString("password").equals(password))
		   {
			   return true;
		   }
		   
	   }
	return false;
}
public  List<Voter> dispalyvoterdetails(String voterid) throws ClassNotFoundException, SQLException
{
	   Connection con=ConnectionManager.getConnection();	
	  // String sql="select * from citizen where voter_id='"+voterid+"'";
	   String sql="select * from citizen";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   while(rs.next())
	   {
		     if(rs.getString("voter_id").equals(voterid))
		     {
	     	   String name=rs.getString(1);
			   LocalDate d=rs.getDate(2).toLocalDate();
			   int age=rs.getInt(3);
			   String gender=rs.getString(4);
			   Long adhar=rs.getLong(5);
			   Long phone=rs.getLong(6);
			   String email=rs.getString(7);
			   String address=rs.getString(8);
			   String voterid1=rs.getString(9);
			   System.out.println(name);
			   Voter vote=new Voter(name,email,voterid1,gender,address,d,phone,age,adhar);
			   citizenlist.add(vote);
		     }
		
		 }
	return citizenlist;
}
public boolean updatename(String name1, long adhar1) throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();	  
	 PreparedStatement st = con.prepareStatement("update citizen set name = ? where adhar_number = ?");
	 st.setString(2,name1);
	 st.setLong(2,adhar1);
	 int result=st.executeUpdate();
	 if(result==1)
	 {
		 return true;
	 }
	 return false;
}
public List<Voter> displaycitizenlist() throws ClassNotFoundException, SQLException 
{
	 Connection con=ConnectionManager.getConnection();	 
	 Statement st=con.createStatement();
	 ResultSet rs=st.executeQuery("select * from citizen");
	 while(rs.next())
	 {
		 String name=rs.getString(1);
		 LocalDate d=rs.getDate(2).toLocalDate();
		 int age=rs.getInt(3);
		 String gender=rs.getString(4);
		 long adhar=rs.getLong(5);
		 long phone=rs.getLong(6);
		 String email=rs.getString(7);
		 String address=rs.getString(8);
		 String voterid=rs.getString(9);
		 Voter vote=new Voter(name,email,voterid,gender,address,d,phone,age,adhar);
		 citizenlist.add(vote);
	 }
	return citizenlist;
}
public int deletecitizen(String cid) throws ClassNotFoundException, SQLException
{
	 Connection con=ConnectionManager.getConnection();	
	 PreparedStatement stmt = con.prepareStatement("delete from citizen where voter_id = ?");
     stmt.setString(1, cid);
     int result = stmt.executeUpdate();

	return result;
}
public String getvoterid(String un, String password) throws ClassNotFoundException, SQLException 
{
	  Connection con=ConnectionManager.getConnection();	
	   String voterid=null;
	   String sql="select * from voter_id";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   while(rs.next())
	   {
		   if(rs.getString("username").equals(un) && rs.getString("password").equals(password))
		   {
			   voterid=rs.getString(2);
		   }
		   
	   }
	return voterid;
}
public void displaydetails(String voterid) throws ClassNotFoundException, SQLException
{
	 Connection con=ConnectionManager.getConnection();	
	  // String sql="select * from citizen where voter_id='"+voterid+"'";
	   String sql="select * from citizen";
	   Statement st=con.createStatement();
	   ResultSet rs=st.executeQuery(sql);
	   while(rs.next())
	   {
		     if(rs.getString("voter_id").equals(voterid))
		     {
	     	   String name=rs.getString(1);
			   LocalDate d=rs.getDate(2).toLocalDate();
			   int age=rs.getInt(3);
			   String gender=rs.getString(4);
			   Long adhar=rs.getLong(5);
			   Long phone=rs.getLong(6);
			   String email=rs.getString(7);
			   String address=rs.getString(8);
			   String voterid1=rs.getString(9);
			   System.out.println(name);
			   System.out.println(d);
			   //Voter vote=new Voter(name,email,voterid1,gender,address,d,phone,age,adhar);
			   //citizenlist.add(vote);
		     }
		
		 }
}
}
