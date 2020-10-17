package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Date;
//import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AdminDAO;
import dao.CandidateDAO;
import dao.ElectionDAO;
import dao.ResultDAO;
import dao.VoterDAO;
import dao.countDAO;
import model.Candidate;
import model.Election;
//import model.Excelgeneration;
import model.Voter;

public class Main 
{
	public static List<Candidate> candidatelist=new ArrayList<Candidate>();
	public static List<Voter> citizenlist=new ArrayList<Voter>();
	public static List<Voter> citizenlist1=new ArrayList<Voter>();
	public static List<Voter> list=new ArrayList<Voter>();
	public static List<Election> electionlist=new ArrayList<Election>();
	//public static List<String> reply=new ArrayList<String>();
	
	public static List<Candidate> allcandidid=new ArrayList<Candidate>();
	public static List<Candidate> particularcandidid=new ArrayList<Candidate>();	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
	{
		int msg1=0;
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	   int choice=0; int n=2; 
	   System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
	   System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   System.out.printf("%85s","ONLINE VOTING SYSTEM");
	   System.out.println();
	   System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   System.out.printf("%84s","*******************");
	   System.out.println();
	   System.out.printf("%79s","YOUR VOICE");
	   System.out.println();
	   System.out.printf("%79s","----------");
	   System.out.println();
	   System.out.printf("%79s","YOUR VOTE ");
	   System.out.println();
	   System.out.printf("%79s","----------");
	   System.out.println();
	   System.out.printf("%84s","*******************");
	   System.out.println();
	   System.out.printf("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
	   System.out.println();
	   System.out.println();
	   do {
	   System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   System.out.println("\t\t"+"1.ADMIN"+"\t\t"+"2.VOTER"+"\t\t"+"3.CANDIDATE"+"\t\t"+"4.ELECTIONS"+"\t\t"+"5.RESULTS"+"\t\t"+"6.EXIT");
	   System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	   System.out.println();
	   try
	   {
	   System.out.print(">>SELECT THE CHOICE:");
	   int ch1=Integer.parseInt(br.readLine());
	   System.out.println();
	  
	   switch(ch1)
			   {
		
				  case 1: 
					   System.out.print(">>ENTER THE USERNAME:");
			           String username=br.readLine();
					   System.out.print(">>ENTER THE PASSWORD:");
					   String password=br.readLine();
					   AdminDAO admindao=new AdminDAO();
					   boolean value=admindao.check(username,password);
					   if(value)
					   {
						   admin();
					   }  
					   else
					   {
						   System.out.println("CHECK YOUR USERNAME AND PASSWORD");
					   } 
					   break;
			   case 2:voter(); break;
			   case 3:Candidate.candidate(); break;
			   case 4:elections(); break;
			   case 5:ResultDAO resultdao=new ResultDAO();
				       resultdao.result();
				       break; 
			   case 6:
				   // Excelgeneration e=new Excelgeneration();
			       // Voter vote=new Voter();
				 //Excelgeneration.generate(vote ,citizenlist);
			   break;
			   case 7:System.exit(0);
			   default:System.out.println("wrong choice");
			   }
	            }catch(Exception e)
	              {
		              System.out.println("ONLY NUMBERS ARE ACCEPTED PLEASE PROVIDE VALID NUMBER");
	              }
	  
	      }while(choice!=7);
	}
	
private static void elections() throws NumberFormatException, IOException, ClassNotFoundException, SQLException 
	{
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 CandidateDAO candidatedao=new CandidateDAO();
		 // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  ElectionDAO electiondao=new ElectionDAO();
		  System.out.println("1.ADD 2.MODIFY THE DATE OF ELECTION 3.VIEW 4.EXIT");
		  System.out.println("ENTER YOUR CHOICE");
		  int choice=Integer.parseInt(br.readLine());
		switch(choice)
		{
		case 1: System.out.println("provide election details");
		        System.out.println("Enter Election name");
		        String name=br.readLine();
		        System.out.println("Enter the date of Election");
		        String date=br.readLine();
		        LocalDate d=LocalDate.parse(date,dtf);
		        System.out.println("provide short description of election");
		        String desc=br.readLine();
		        Election election=new Election(name,d,desc);
		        int row=electiondao.insertintoelection(election);
		        if(row==1)
		        {
		        	System.out.println("ELECTIOIN DETAILS ADDED SUCCESSFULLY");
		        }
		        else
		        {
		        	System.out.println("NOT ADDED");
		        }
		     break;
		case 2: System.out.println("MODIFYING");
		        System.out.println("YOU CAN MODIFY DATE");
		        System.out.println("Enter the election id");
		        String id=br.readLine();
		        System.out.println("Enter the date");
	            String date1=br.readLine();
	            LocalDate d1=LocalDate.parse(date1,dtf);
	            int row1=electiondao.update(id,d1);
	            if(row1==1)
		        {
		        	System.out.println("ELECTIOIN DETAILS UPDATED SUCCESSFULLY");
		        }
		        else
		        {
		        	System.out.println("ELECTION DETAILS NOT UPDATED");
		        }
	            break;
		case 3: System.out.println("ELECTION DETAILS");
		        System.out.println("-----------------------------------");
		        System.out.println("ELECTION ID"+"\t"+"ELECTION NAME"+"\t"+"ELECTION DATE"+"\t"+"DESCRIPTION");
		        electionlist=electiondao.display();
		        for(Election ele:electionlist)
		        {
		        	System.out.println(ele.getEleid()+"\t"+ele.getName()+"\t"+ele.getDate()+"\t"+ele.getDesc());
		        }
		break;
		case 4:return;
		default:System.out.println("wrong choice");
		}
		}
	//**********************************************************ADMIN*******************************************************//
	
	/*private static void candidate() throws ClassNotFoundException, SQLException 
	{
		CandidateDAO candidatedao=new CandidateDAO();
		  System.out.printf("%80s","_______________________________");
		   System.out.println();
		   System.out.println();
		   System.out.printf("%75s",">>>> CANDIDATE-LIST <<<<");
		   System.out.println();
		   System.out.printf("%80s","_______________________________");
		   System.out.println();
		   System.out.println();
  	    candidatelist=candidatedao.displaycandidatelist();
        System.out.println("\t"+"------------------------------------------------------------------");
        System.out.println("\t"+"ID"+"\t\t"+"NAME"+"\t\t"+"DOB"+"\t\t"+"SYMBOL");
        System.out.println("\t"+"------------------------------------------------------------------");
        for(Candidate candidate1:candidatelist)
        {
      	  System.out.println("\t"+candidate1.getCandidate_id()+"\t\t"+candidate1.getName()+"\t\t"+candidate1.getDate()+"\t\t"+candidate1.getSymbol());
        }
        System.out.println();
		
	}*/

	private static void admin() throws NumberFormatException, IOException, ClassNotFoundException, SQLException 
	{
		   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		   int choice=0;
		   do
		   {
		   System.out.println("_____________________________________________________________________________________________________________________________________________________________________________");
		   System.out.println();
		   System.out.printf("%80s","ADMIN PANEL");
		   System.out.println();
		   System.out.println("_____________________________________________________________________________________________________________________________________________________________________________");
		   System.out.println();
		   System.out.println("  "+"1.NEW REGISTARTION REQUESTES"+"     "+"2.CITIZENS-INFO"+"    "+"3.CANDIDATE-INFO"+"    "+"4.ELECTION-INFO"+"\t"+"5.COUNTING-INFO"+"   "+"6.RESULTS-INFO"+"   "+"7.EXIT");
		   System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		   System.out.println();
		   System.out.print(" "+">>CHOOSE YOUR OPTION:");
		   choice=Integer.parseInt(br.readLine());
		   System.out.println();
		   adminpanel(choice);
		   }while(choice!=7);
	}
//******************************************ADMIN PANEL*************************************************************//
	private static void adminpanel(int choice1) throws ClassNotFoundException, SQLException, NumberFormatException, IOException 
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 
		VoterDAO voterdao=new VoterDAO();
		CandidateDAO candidatedao=new CandidateDAO();
		int choice2=0;
		String name=null;  int n=2; int row=0; int column=0;
		switch(choice1)
		   {
		   case 1: if(list.size()==1)
		           {
		          System.out.println("CHECK FOR NEW REQUEST FOR REGISTRATION"+" "+"("+list.size()+")"); 
		          Iterator<Voter> itr =list.iterator();
		                   while(itr.hasNext())
				          {
				        	  Voter vote=itr.next();
				        	   System.out.println("--------------------------------------------------");
				        	   System.out.println(">> NAME:"+vote.getName());
				        	   System.out.println(">> DOB:"+vote.getDob());
				        	   System.out.println(">> AGE:"+vote.getAge());
				        	   System.out.println(">> GENDER:"+vote.getGender());
				        	   System.out.println(">> ADDRESS:"+vote.getAddress());
				        	   System.out.println(">> ADHAR NUMBER:"+vote.getAdhar());
				        	   System.out.println(">> PHONE NUMBER:"+vote.getPhone());
				        	   System.out.println(">> EMAIL:"+vote.getEmail());
				        	   System.out.println("---------------------------------------------------");
				        	   name=vote.getName();
				        	   System.out.println("\t"+"1.REGISTER"+"\t"+"2.REJECT");
				        	   System.out.println("\t"+"..........."+"\t"+"............");
				        	   n=Integer.parseInt(br.readLine());
				        	   if(n==1)
						       {
				        		   //System.out.println("hello");
				        		   row=voterdao.insert(vote);
				        		   if(row==1)
				        		   {
				        			   System.out.println("\t\t"+"||*** REGISTRATION DONE ***||");
									   System.out.println("\t"+"--------------------------------------------");
									   System.out.println();
				        		       itr.remove();
				        		   }
						       }
				        	   else
				        	   {
				        		   System.out.println("\t\t"+"||*** REGISTRATION NOT DONE ***||");
								   System.out.println("\t"+"--------------------------------------------");
								   System.out.println();
				        	   }
				        	}
		                 }
			       else
		           {
		           System.out.println("\t"+">>>>>  NO NEW REGISTRATION REQUEST  <<<<<");
		           }
		           
		           break;
		   case 2:
			   System.out.printf("%90s","_______________________________");
			   System.out.println();
			   System.out.println();
			   System.out.printf("%85s",">>>> CITITZENS-LIST <<<<");
			   System.out.println();
			   System.out.printf("%90s","_______________________________");
			   System.out.println();
			   System.out.println();
			   System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		       citizenlist=voterdao.displaycitizenlist();
		       System.out.println("\t"+"VOTER-ID"+"\t"+"NAME"+"\t\t"+"DOB"+"\t\t"+"AGE"+"\t\t"+"GENDER"+"\t\t"+"ADHAR-NUMBER"+"\t\t"+"ADDRESS"+"\t\t"+"PHONE"+"\t\t"+"EMALIL");
		       System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		       for(Voter vote:citizenlist)
		       {
		    	   System.out.printf("%16s %11s %19s %9s %19s %19s %21s %16s %16s",vote.getVoter_id(),vote.getName(),vote.getDob(),vote.getAge(),vote.getGender(),vote.getAdhar(),vote.getAddress(),vote.getPhone(),vote.getEmail());
		       }
		       System.out.println();
		       System.out.println();
		       System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		       System.out.println();
		       System.out.println(">>DO YOU WANT TO DELETE ANY CITIZEN RECORD");
		       System.out.println();
		       System.out.println("\t"+"1.YES"+"\t"+"2.NO");
		       System.out.println("\t"+"......"+"\t"+".....");
		       int yesorno=Integer.parseInt(br.readLine());
		    		   switch(yesorno)
		    		   {
		    		   case 1:System.out.print(">> PLEASE PROVIDE CITIZEN ID:");
		    		          String cid=br.readLine();
		    		          int row4=voterdao.deletecitizen(cid);
		    		          if(row4==1)
		    		         { System.out.println("\t\t"+"||*** DETAILS REMOVED SUCCESSFULLY ***||");
							   System.out.println("\t\t"+"--------------------------------------------");
							   System.out.println();}
		    		        else
		    		          {System.out.println("\t\t"+"||*** DETAILS REMOVED UNSUCCESSFULLY ***||");
						       System.out.println("\t\t"+"--------------------------------------------");
						       System.out.println();}
		    		          break;
		    		   case 2: return; 
		    		   default:System.out.println("WRONG CHOICE");
		    		  
		    		   }
		    		   break;
		   case 3:
			   do 
			   {
				   System.out.printf("%80s","_______________________________");
				   System.out.println();
				   System.out.println();
				   System.out.printf("%75s",">>>> CANDIDATE-INFO <<<<");
				   System.out.println();
				   System.out.printf("%80s","_______________________________");
				   System.out.println();
				   System.out.println("\t\t"+"-----------------------------------------------------------------------------------------------------");
				   System.out.println();
				   //System.out.println();
				   System.out.println("\t\t\t\t"+"1.ADD"+"\t\t"+"2.REMOVE"+"\t"+"3.UPDATE"+"\t"+"4.VIEW"+"\t\t"+"5.EXIT");
				   System.out.println();
				   System.out.println("\t\t"+"-----------------------------------------------------------------------------------------------------");
				   System.out.println();
				   System.out.println();
				   System.out.print(" "+">>CHOOSE YOUR OPTION:");
				   choice2=Integer.parseInt(br.readLine());
				   System.out.println();
				   switch(choice2)
				    {
				    case 1: 
				    	System.out.println("            "+"||==> PROVIDE CANDIDATE DETAILS <==||");
				    	System.out.println("\t"+"--------------------------------------------");
				    	System.out.println();
				        System.out.printf(">>ENTER THE CANDIDATE NAME:");
				        String canname=br.readLine();
				        System.out.printf(">>ENTER THE CANDIDATE DOB (DD-MM-YYYY):");
						String date=br.readLine();
						LocalDate dob=LocalDate.parse(date,dtf);
						System.out.printf(">>ENTER THE CANDIDATE GENDER:");
						String gender=br.readLine();
						System.out.printf(">>ENTER THE CANDIDATE EMAIL:");
						String email=br.readLine();
						System.out.printf(">>ENTER THE PARTY SYMBOL:");
						String symbol=br.readLine();
						System.out.println();
						Candidate candidate=new Candidate(canname,dob,gender,email,symbol);
				        candidatelist.add(candidate);
				        int canrow=candidatedao.addcandidatedetails(candidate);
				        if(canrow==1)
				        {
				        	System.out.println("\t\t"+"||*** DETAILS ADDED SUCCESSFULLY ***||");
				        	System.out.println("\t\t"+"------------------------------------------");
				        	System.out.println();
				        	
				        }
				        else
				        {
				        	System.out.println("\t\t"+"||*** DETAILS NOT ADDED ***||");
				        	System.out.println("\t\t"+"----------------------------------------------------------------------------------------------------------------------------------");
				        	System.out.println();
				        } 
				        break;
				    case 2: Candidate.candidate();
				    	  System.out.print(">>CANDIDATE-ID:");
				          int id=Integer.parseInt(br.readLine());
				          System.out.println();
				          int remove=candidatedao.removecandidate(id);
				          if(remove==1)
				          {
				        	    System.out.println("\t\t"+"||*** CANDIDATE DETAILS REMOVED ***||");
					        	System.out.println("\t\t"+"------------------------------------------");
					        	System.out.println();
				          }
				          else
				          {
				        	    System.out.println("\t\t"+"||*** CANDIDATE DETAILS NOT REMOVED ***||");
					        	System.out.println("\t\t"+"------------------------------------------");
					        	System.out.println();
				          }
				    	  break;
				    	
				    	
				    case 3: Candidate.candidate();
			          System.out.println(">>PROVIDE THE CANDIDATE-ID WHICH YOU WANT TO UPDATE(YOU CAN MODIFY ONLY NAME AND DOB)");
			          int id3=Integer.parseInt(br.readLine());
			          do 
			          {
			          System.out.println("\t"+"1.NAME"+"\t"+"2.DOB"+"\t"+"3.EXIT");
			          System.out.println("\t"+"........"+"\t"+"......"+"\t"+"......");
			          System.out.println(">> CHOOSE YOUR OPTION");
			          column=Integer.parseInt(br.readLine());
			          switch(column)
			          {
			          case 1:
			        	  System.out.print(">>PROVIDE NAME:");
			        	  String candidatename=br.readLine();
			        	  int row3=candidatedao.updatecandidatelistname(id3,candidatename);
			        	  if(row3==1)
			        	  {
			        		    System.out.println("\t\t"+"||*** CANDIDATE NAME UPDATE SUCCESSFULLY ***||");
					        	System.out.println("\t\t"+"------------------------------------------");
					        	System.out.println();
			              }
			        	  else
			        	  {
			        		    System.out.println("\t\t"+"||*** CANDIDATE NAME UPDATE UNSUCCESSFULLY ***||");
					        	System.out.println("\t\t"+"------------------------------------------");
					        	System.out.println();
			        	  } break;
			          case 2: System.out.println(">>PROVIDE DOB");
			                  String dob3=br.readLine();
			                  int row4=candidatedao.updatecandidatelistdob(id3, dob3);
			                  if(row4==1)
				        	  {
			                	    System.out.println("\t\t"+"||*** CANDIDATE DOB UPDATE SUCCESSFULLY ***||");
						        	System.out.println("\t\t"+"------------------------------------------");
						        	System.out.println();
				        	  }
				        	  else
				        	        {
				        		    System.out.println("\t\t"+"||*** CANDIDATE DOB UPDATE UNSUCCESSFULLY ***||");
						        	System.out.println("\t\t"+"------------------------------------------");
						        	System.out.println();}
			                  break;
			           case 3: break;
		               default:System.out.println(" >>>>> PLEASE PROVIDE VALID COLUMN NAME <<<<<");
			          }
			          }while(column!=3);
			          break;
				   case 4:Candidate.candidate(); break;
				    case 5: return;
				    default: System.out.println("wrong choice");
				    }
			     }while(choice2!=5);
			     break;
		   case 4: System.out.println("ELECTION");
		           System.out.println("1.ADD\n 2.REMVOE\n 3.UPDATE\n 4.VIEW");
		           System.out.println("Enter your choice");
		           int choice=Integer.parseInt(br.readLine());
		           do
		           {
			           switch(choice)
			           {
			           case 1:System.out.println("ADD");
			                         
			           
			           break;
			           case 2:System.out.println("REMOVE");break;
			           case 3:System.out.println("UPDATE THE ELECTION DETAILS"); break;
			           case 4:System.out.println("VIEW THE ELECTION DETAILS");
			           case 5:return;
			           default:System.out.println("WRONG CHOICE");
			           }
		           }while(choice!=4);
			   break;
		   case 5: 
			   
			   break;
		   case 6: break;
		   case 7: return;
		   default:System.out.println("Wrong choice");
		    }
	}
	

	
	
	

	//*******************************************************VOTER*********************************************************//

	private static void voter() throws NumberFormatException, IOException, ClassNotFoundException, SQLException 
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    
	    VoterDAO voterdao=new VoterDAO(); int choice=0; int ch=0; int row=0; int msg=0;
		do 
		{
			System.out.printf("%80s","_______________________________");
			   System.out.println();
			   System.out.println();
			   System.out.printf("%75s",">>>> VOTER'S-PANEL <<<<");
			   System.out.println();
			   System.out.printf("%80s","_______________________________");
			   System.out.println();
			   System.out.println();
			   System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
			   System.out.println("  "+"1.NEW REGISTRATION"+"\t\t"+"2.CHECK WHETHER REGISTRATION IS DONE OR NOT"+"\t"+"3.CLICK TO VIEW THE DETAILS"+"\t"+"4.CAST A VOTE"+"\t\t"+"5.EXIT");
			   System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
			   System.out.println();
			   System.out.print(" "+">>CHOOSE YOUR OPTION:");
			   choice=Integer.parseInt(br.readLine());
			   System.out.println();
	switch(choice)
		{
		case 1:  voterpanel(); 
		         msg=1;
		         break;
		case 2: if(list.size()==1 && msg==1)
		        {
					System.out.println("\t"+">>>>> YOUR REQUEST IS PENDING <<<<<");
					System.out.println("\t"+"...........................................");
			     }
				else
				{
					System.out.println("\t"+">>>>> YOUR REQUEST GOT REGISTERED <<<<<");
					System.out.println("\t"+"...........................................");
				}
		break;
	    case 3:
			   do
			   {
			   System.out.println("\t"+"1.GET YOUR USERID AND PASSWORD"+"\t\t"+"2.LOGIN TO VIEW YOUR DETAILS"+"\t\t"+"3.EXIT");
		     //  System.out.println("\t"+"_______________________________"+"\t\t"+"______________________________"+"\t\t"+"_______");
		       System.out.println("\t"+"-------------------------------"+"\t\t"+"------------------------------"+"\t\t"+"-------");
		       ch=Integer.parseInt(br.readLine());
		       switch(ch)
		       {
		       case 1:  
		    	        System.out.println();
				    	System.out.println("\t"+">>>>>  PROVIDE YOUR ADHAR-NUMBER TO GET YOUR USERID AND PASSWORD <<<<<");
				        System.out.print(">> ");
				    	Long adhar=Long.parseLong(br.readLine());
				        list=voterdao.usernameandpassword(adhar);
				        System.out.println();
				        System.out.println("\t\t"+"USERNAME"+"\t\t"+"PASSWORD");
				        System.out.println("\t"+"....................................................");
				        for(Voter vote:list)
				        {
	                    System.out.printf("%24s %24s",vote.getUsername(),vote.getPassword());
				        }
				        System.out.println();
				        System.out.println("\t"+"....................................................");
				        System.out.println();
				        break;
		       case 2:
				       System.out.println();
		    	       System.out.println("\t"+">>>>>  PROVIDE YOUR USERNAME AND PASSWORD <<<<<");
					   System.out.print(" >> USERNAME:");
				       String un=br.readLine();
				       System.out.print(" >> PASSWORD:");
					   String password=br.readLine();
					   boolean value=voterdao.check(un,password);
						    if(value)
						    {
						    	String voterid=voterdao.getvoterid(un,password);
						    	System.out.println();
							    System.out.println("YOUR DETAILS");
							    voterdao.displaydetails(voterid);
						    }
						    else
						    {
						    	System.out.println("ENTERED USRENAME AND PASSWORD IS INCORRECT");	
						    }
				          break;
		          case 3:break;
		       default :System.out.println("Wrong choice");
		       }
		       }while(ch!=3);
			   break;
		case 4: System.out.println("cast your vote"); 
		        System.out.println("print the candidate list");
		        System.out.println("PROVIDE YOUR VOTERID");
		        String voterid=br.readLine();
		        System.out.println("PROVIDE YOUR CANDIDATE-ID");
		        int candidateid=Integer.parseInt(br.readLine());
		        System.out.println("PROVIDE YOUR ELECTION-ID");
		        String electionid=br.readLine();
		        countDAO count=new countDAO();
		        boolean check1=count.check1(voterid);
		        boolean check2=count.check2(candidateid);
		        boolean check3=count.check3(electionid);
		        if(check1 && check2 && check3)
		        {
		        	boolean duplirecord=count.duplicatecheck(voterid);
		        	if(duplirecord)
		        	{
		        		System.out.println("YOU ARE ALREADY VOTED");
		        	}
		        	else
		        	{
		        		row=count.insertintocounting(voterid, candidateid, electionid);
		        	}
		        }
		        else
		        {
		        	System.out.println(check1);
		        	System.out.println(check2);
		        	System.out.println(check3);
		        }
		        if(row==1)
		        {
		        	System.out.println("THANKS FOR VOTING");
		        }
		        else
		        {
		        	System.out.println("............");
		        }
		 break;
		case 5: return;
		default:System.out.println("Wrong choice");
		}
		}while(choice!=5);
	}

	
	private static void updatevotervalues(long adhar1) throws IOException, ClassNotFoundException, SQLException 
	{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	String name=null;
	VoterDAO voterdao=new VoterDAO();
	do
	{
	System.out.println("YOU CAN UPDATE YOUR DETAILS");
	System.out.println("PROVIDE THE COLUMN NAME WHICH YOU WANT TO  MODIFY");
    name=br.readLine();
	switch(name)
	{
	   case "NAME": System.out.println("ENTER THE NAME"); 
	                String name1=br.readLine();
	                boolean value=voterdao.updatename(name1,adhar1);
	                if(value)
	                {
	                	System.out.println("UPDATED");
	                }
	                else
	                {
	                	System.out.println("NOT UPDATED");
	                }
	                break;
	   case "AGE" :System.out.println("ENTER THE AGE");
	               int age=Integer.parseInt(br.readLine());
	              /* boolean value1=voterdao.updateage(age,adhar1);
	               if(value1)
	               {
	            	   System.out.println("UPDATED");
	                }
	                else
	                {
	                	System.out.println("NOT UPDATED");
	                }
	                break;*/
	   case "":
	   case "NO": return;
	   default : System.out.println("wrong choice");
	}
	}while(!name.equals("no"));
    }

	private static void voterpanel() throws IOException 
	{
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 String regex="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		 Pattern p=Pattern.compile(regex);
		 //System.out.println("KEEP CALM AND REGISTER TO VOTE");
		 System.out.println("\t\t"+"||==> PROVIDE YOUR DETAILS <==||");
	     System.out.println("\t"+"-------------------------------------------------");
	     System.out.println();
		 System.out.print(">>ENTER YOUR NAME:");
		 String name=br.readLine();
		 System.out.print(">>ENTER YOUR DOB (DD-MM-YYYY):");
		 String date=br.readLine();
		 LocalDate dob=LocalDate.parse(date,dtf);
         System.out.print(">>ENTER YOUR AGE:");
         int age=Integer.parseInt(br.readLine());
         System.out.print(">>ENTER YOUR GENDER:");
         String gender=br.readLine();
         System.out.print(">>ENTER YOUR ADHAR-NUMBER:");
         Long adhar=Long.parseLong(br.readLine());
         System.out.print(">>ENTER YOUR ADDRESS:");
         String address=br.readLine();
		 System.out.print(">>ENTER YOUR PHONE NUMBER:");
		 Long phone=Long.parseLong(br.readLine());
		 System.out.print(">>ENTER THE EMAIL ID:");
		 String email=br.readLine();
		 System.out.println();
		 Matcher m=p.matcher(email);
		 if(age>18)
		 {
					 if(phone.toString().length()==10)
					 {
					     if(m.matches()) 
					     {
					      Voter voter=new Voter(name,email,gender,address,dob,phone,age,adhar);
						 list.add(voter);
						 System.out.println("\t"+">>>>> THANKS FOR YOUR PROVIDING YOUR DETAILS ,YOUR REGISTRATION WILL BE DONE SOON  <<<<<");
						 System.out.println("\t"+".............................................................................................");
						 //System.out.println("list size"+" "+list.size());
						 //System.out.println("list index"+" "+list.indexOf(voter));
						// for(Voter vote:list)
						 //{
							 //System.out.println(vote.getName()+" "+vote.getAdhar());
						 //}
					     }
					     else
					     {
						 System.out.println("please provide valid email address");
					     }
					 }
					 else
					 {
						 System.out.println("please provide valid number");
					 }
					
		 }
		 else
		 {
		    System.out.println("You are not eligble to caste a vote");
		 }

	}
}

