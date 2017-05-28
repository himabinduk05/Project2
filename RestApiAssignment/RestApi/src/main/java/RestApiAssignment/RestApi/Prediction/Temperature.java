package RestApiAssignment.RestApi.Prediction;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/historical")

public class Temperature {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response posting(output res)
	{	
		
		try {
			        Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherreport",
					        "root","Rashmitha@05");
					PreparedStatement pstmt=null;
					PreparedStatement pstmt1=null;
					PreparedStatement pstmt2=null;
					 String date=res.getDate();
					pstmt=conn.prepareStatement("SELECT date,tmax,tmin  FROM report where date=?");
					pstmt.setString(1,date);
					ResultSet rs = null;
					 rs=pstmt.executeQuery();				 
				     System.out.println(rs);
				      if(rs.next()==false){				    	  
				    	  pstmt1=conn.prepareStatement("insert into report values(?,?,?)");
				 		 pstmt1.setString(1, res.getDate());
				 		 pstmt1.setFloat(2, res.getTmax());;
				 		pstmt1.setFloat(3, res.getTmin());				 		 
				 		 pstmt1.executeUpdate();				    	  
				      }
				      else{		
				    	  System.out.println("hello");
				    	  pstmt2= conn.prepareStatement("update report set tmax=?,tmin=? where date=?");						   
				    	  pstmt2.setFloat(1, res.getTmax());
				    	  pstmt2.setFloat(2, res.getTmin());
				    	  pstmt2.setString(3, res.getDate());					 		
					 	  pstmt2.executeUpdate();
					 						 		 	
				      }
				      rs.close();
				      pstmt.close();
				      pstmt1.close();
				      pstmt2.close();
				      conn.close();
				} catch (Exception ex) {					
					 System.out.println("Exception: " + ex.getMessage());					   
				}	
				           	   
		return Response.status(javax.ws.rs.core.Response.Status.CREATED).entity(res.getDate()).build();
		              
				
	}
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<DateOutput> history()
	{
		ArrayList<DateOutput> list = new ArrayList<DateOutput>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherreport",
			        "root","Rashmitha@05");
			 Statement stmt = conn.createStatement();
			 String sql;
		      sql = "SELECT date FROM report";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){		          
		    	  String  date=rs.getString("date");		         
		          DateOutput r = new DateOutput(date);		          
		         list.add(r);
		         
		       }
		      rs.close();
		      stmt.close();
		      conn.close();
		} catch (Exception ex) {			
			 System.out.println("Exception: " + ex.getMessage());			    
		}
		
		return list;
		
	}
	@GET
	@Path("/{yyyymmdd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response temperature(@PathParam("yyyymmdd") String date)
	{
		
		output r=new output();		
try {
	
	         Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherreport",
			        "root","Rashmitha@05");
			PreparedStatement pstmt=null;			
			pstmt=conn.prepareStatement("SELECT date,tmax,tmin  FROM report where date=?");
			 pstmt.setString(1,date);ResultSet rs = null;
			 rs=pstmt.executeQuery();				 
		      
		      while(rs.next()){		          
		    	  String  datevalue=rs.getString("date");
		          float tmax=Float.parseFloat(rs.getString("tmax"));
		          float tmin=Float.parseFloat(rs.getString("tmin"));
		          r = new output(datevalue,tmax,tmin);	         
		        
		       }
		      rs.close();
		      pstmt.close();
		      conn.close();
		} catch (Exception ex) {			
			 System.out.println("Exception: " + ex.getMessage());
			   
		}		

       if(r.getDate()!=null){    	   
		return Response.status(javax.ws.rs.core.Response.Status.OK).entity(r).build();
       }
       else{    	   
    	   return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
	}
	}
	@DELETE
	@Path("/{yyyymmdd}")
	@Produces(MediaType.APPLICATION_JSON)
	public String del_record(@PathParam("yyyymmdd") String date)
	{
		int rowsaffected=0;
		
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weatherreport",
				        "root","Rashmitha@05");
				PreparedStatement pstmt=null;
				 pstmt=conn.prepareStatement("delete from report where date=?");
				 pstmt.setString(1,date);
				 rowsaffected=pstmt.executeUpdate();			      
			      pstmt.close();
			      conn.close();
			} catch (Exception ex) {				
				 System.out.println("Exception: " + ex.getMessage());
				   
			}
		 if(rowsaffected!=0){
		 return "Deleted Date::"+date;
		 }
		 else{
		  return "Date Value Doesnot Exist";
		 }
		
		
	}
	


}
