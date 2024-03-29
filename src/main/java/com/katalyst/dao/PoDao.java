package com.katalyst.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.katalyst.model.CreateNewPO;
import com.katalyst.model.CreateNewPO1;


@Repository
@PropertySource("application.properties")
public class PoDao {
	
	public Connection conn;


	 
	  
	 
	  public void createConnection()
	  {
	    try
	    {
	    	
	    	
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      String url = "jdbc:mysql://katalyst.ciuhq69jhwir.us-east-2.rds.amazonaws.com:3306/Katalyst";
	      conn = DriverManager.getConnection(url, "baba", "babababa");
	      
	      System.out.println("COnnection Successful");
	      
	      
	    }
	    catch (Exception e)     
	    {
	    	System.err.println(e.getMessage());
	    }
	  
	  }

	  
	  // Following method is used to select from table using preparedStatement
	  public void select(String first_name, String last_name)
	  {
		  
		  PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("Select * from customer where First_Name like ? and last_name like ?");
			  
			//Initialize first parameter with email address
			pstmt.setString(1, first_name);
			pstmt.setString(2, last_name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String s = rs.getString("First_Name");
				String n = rs.getString("Last_Name");
				System.out.println(s + "   " + n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	  }
	  

	  // Following method is a simple select
	public void doSelectTest( )
	  {
	    System.out.println("[OUTPUT FROM SELECT]");
	  
	    try
	    {
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery("Select * from PO");
	      while (rs.next())
	      {
	        int s = rs.getInt("PO");
	       String n = rs.getString("date_due");
	       String m = rs.getString("Podate");
	       String o = rs.getString("warehouse_id");
	       String p = rs.getString("vendor_id");
	       String q = rs.getString("terms_id");
	        System.out.println(s + "   " + n + " "+m+" "+o);
	      }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }
	
	public String getPO(int poid)
	  {
	    System.out.println("[OUTPUT FROM Slect of PO]");
	    Integer s = null;
	  
	    try
	    {
	    	PreparedStatement st = conn.prepareStatement("Select PO from PO where PO = ?");
	    	st.setInt(1, poid);
	      ResultSet rs = st.executeQuery();
	      
	      while (rs.next())
	      {
	        s = rs.getInt("PO");
	      }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	    return s+"";
	  }

	  
	  // Following method inserts


	public void doInsertPO(CreateNewPO purchaseorder)
	{
	  System.out.print("\n[Performing INSERT] ... ");
	  try
	  {
	    PreparedStatement st = conn.prepareStatement("INSERT INTO PO (PO, date_due, POdate, warehouse_id, Ship_via, vendor_id, terms_id) VALUES (?,?,?,?,?,?,?)");
	    st.setInt(1, Integer.parseInt(purchaseorder.getPurchase_order_id()));
		st.setString(2, purchaseorder.getDate_due());
		st.setString(3, purchaseorder.getDate());
		st.setString(4, purchaseorder.getWarehouse_id());
		st.setString(5, purchaseorder.getShip_via());
		st.setString(6, purchaseorder.getVendor_id());
		st.setString(7, purchaseorder.getTerms_id());
		
	    st.executeUpdate();
	    System.out.println("Insert succesful");
	  }
	  catch (SQLException ex)
	  {
	    System.err.println(ex.getMessage());
	  }
	}
	
	
	public void doInsertPurchase_order_item(CreateNewPO1 poi)
	{
	  System.out.print("\n[Performing INSERT] ... ");
	  try
	  {
	    PreparedStatement st = conn.prepareStatement("INSERT INTO purchase_order_items (po_id, style_number, attr2, po_size, qty, amount) VALUES (?,?,?,?,?,?)");
	    st.setInt(1, Integer.parseInt(poi.getPo_id()));
		st.setString(2, poi.getStyle_number());
		st.setString(3, poi.getAttr2());
		st.setString(4, poi.getSize());
		st.setString(5, poi.getQty());
		st.setString(6, poi.getAmount());
	    st.executeUpdate();
	    System.out.println("Insert succesful");
	  }
	  catch (SQLException ex)
	  {
	    System.err.println(ex.getMessage());
	  }
	}


	  
	  public void doInsertTest()
	  {
	    System.out.print("\n[Performing INSERT] ... ");
	    try
	    {
	      Statement st = conn.createStatement();
	      st.executeUpdate("INSERT INTO customer VALUES ('Anurag11', 'Bhusari11')");
	      System.out.println("Insert succesful");
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }

	  
	  // Following method updates
	  public void doUpdateTest()
	  {
	    System.out.print("\n[Performing UPDATE] ... ");
	    try
	    {
	      Statement st = conn.createStatement();
	      st.executeUpdate("UPDATE customer SET Last_Name='Bhu' WHERE Last_Name='Bhusari'");
	      System.out.println("Update succesful");
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }

	  
	  
	  // Following method delete
	  public void doDeleteTest()
	  {
	    System.out.print("\n[Performing DELETE] ... ");
	    try
	    {
	      Statement st = conn.createStatement();
	      st.executeUpdate("delete from customer  WHERE Last_Name='Bhusari11'");
	      
	      System.out.println("Delete succesful");
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
	  }
	  
	  
	  public void closeConnection()
	  {
		  
		  try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	

}
