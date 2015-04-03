//Making GUI for a database
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class JDBC2 
{
    //Globals
    Connection CONNEX;
    Statement STATE;
    ResultSet RESULT;



    //----------------------------------------------------------
    public static void main(String[] args) throws Exception
    {
        new JDBC2();
        System.out.println("it has started");
    }
    //-----------------------------------------------------------
    public JDBC2() throws Exception
    {
        Connect();
        SelectData();
        SelectXData();
        System.out.println("it has started 2nd method");
     
       
    }
    //-------------------------------------------------------------
  

    //--------------------------------------------------------------
    public void Connect() throws Exception
    {

        try{   
            Class.forName("org.postgresql.Driver");
            CONNEX = DriverManager.getConnection
                    ("jdbc:postgresql://127.0.0.1:5433/EHR", "postgres",
                            "Printer238");
           System.out.println("started");  
                            
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
    }   
    //----------------------------------------------------------------------
    public ResultSetMetaData SelectData()
    {
        try
        {    System.out.println("1");
            STATE = CONNEX.createStatement();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL = "Select * FROM patients ORDER BY patientid";
            RESULT = STATE.executeQuery(SQL);
            //System.out.println(RESULT.getNString(1));
            System.out.println("2");
            return RESULT.getMetaData();
            
        }
        catch(Exception X){}
		return null;
    }
    public String SelectXData()
    {
        try
        {   ArrayList <String[]> result = new ArrayList<String[]>();
      
        
        System.out.println("1");
        STATE = CONNEX.createStatement();// what is this statment for
        STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        String SQL = "Select * FROM patients ORDER BY patientid";
        ResultSet rs = STATE.executeQuery(SQL);
        int columnCount = rs.getMetaData().getColumnCount();
        while(rs.next())
        {
            String[] row = new String[columnCount];
            for (int i=0; i <columnCount ; i++)
            {
               row[i] = rs.getString(i + 1);
            System.out.println(row[i]);
            }
            result.add(row);
            
        }

         
            
        }
        catch(Exception X){}
		return null;
    }
    //-----
    //--------------------------------------------------------------------------       
   
   
  
}
