package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Candidate;
import model.Voter;
import utility.ConnectionManager;

public class CandidateDAO 
{
	public static List<Candidate> candidatelist=new ArrayList<Candidate>();
	public List<Candidate> displaycandidatelist() throws ClassNotFoundException, SQLException
	{
		 Connection con=ConnectionManager.getConnection();	 
		 Statement st=con.createStatement();
		 ResultSet rs=st.executeQuery("select * from candidate");
		 while(rs.next())
		 {
		    int canid=rs.getInt(1);
		   String voterid=rs.getString(2);
		   String name=rs.getString(3);
		   LocalDate d=rs.getDate(4).toLocalDate();
		   String gender=rs.getString(5);
		   String email=rs.getString(6);
		   String symbol=rs.getString(7);
		   String user_id=rs.getString(8);
		   String password=rs.getString(9);
		  
		   Candidate candidate=new Candidate(voterid,name,d,gender,email,symbol,user_id,password,canid);
		   candidatelist.add(candidate);
		 }
		 return candidatelist;
		
	}
	public int addcandidatedetails(Candidate candidate) throws ClassNotFoundException, SQLException
	{
		    Connection con=ConnectionManager.getConnection();
		    char[] randompassword=generaterandompassword();
		    String pwd=new String(randompassword);
		    char[] voterid=generatevoterid();
		    String vid=new String(voterid);
		    int num=generatecandidateid();
		    Date date=Date.valueOf(candidate.getDate());	
		    String sql="insert into candidate values(?,?,?,?,?,?,?,?,?)";
		    PreparedStatement st1=con.prepareStatement(sql);
		    st1.setInt(1, num);
		    st1.setString(2, vid);
		    st1.setString(3, candidate.getName());
		    st1.setDate(4, date);
		    st1.setString(5, candidate.getGender());
		    st1.setString(6, candidate.getEmail());
		    st1.setString(7, candidate.getSymbol());
		    st1.setString(8, candidate.getEmail());
		    st1.setString(9,pwd);
		    
		    int row=st1.executeUpdate();
		    return row;
	}
	public int removecandidate(int id) throws ClassNotFoundException, SQLException 
	{
		 Connection con=ConnectionManager.getConnection();
		 PreparedStatement st = con.prepareStatement("delete from candidate where candidate_id = ?");
	     st.setInt(1, id);
	     int result = st.executeUpdate();
		 return result;
	}
	private static int generatecandidateid()
	{
		 Random rnd = new Random();
		// It will generate 6 digit random Number from 0 to 999999
		    int number = rnd.nextInt(999999);
		   // System.out.println(number);
		return number;
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

public int updatecandidatelistname(int id, String candidatename) throws ClassNotFoundException, SQLException {
	
	 Connection con=ConnectionManager.getConnection();	  
	 PreparedStatement st = con.prepareStatement("update candidate set name = ? where candidate_id = ?");
	 st.setString(1,candidatename);
	 st.setLong(2,id);
	 int result=st.executeUpdate();
	 st.close();
	 return result;
}
public int updatecandidatelistdob(int id3, String dob3) throws ClassNotFoundException, SQLException 
{
	/*String pattern = "yyyy-MM-dd";
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    String mysqlDateString = formatter.format(dob3);*/
	
	 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 LocalDate dob=LocalDate.parse(dob3,dtf);
     Date date=Date.valueOf(dob);	
	 Connection con=ConnectionManager.getConnection();	  
	 PreparedStatement st = con.prepareStatement("update candidate set dob = ? where candidate_id = ?");
     st.setDate(1,date);
	 st.setLong(2,id3);
	 int result=st.executeUpdate();
	 st.close();
	 return result;	
	
}

}
