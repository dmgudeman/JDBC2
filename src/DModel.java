import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class DModel {
    
	 //Globals
    Connection CONNEX;
    Statement STATE;
    ResultSet RESULT;
    DDelegate ddelegate;
  
	
	public static void main(String[] args) throws Exception
	{
       
      new DModel();
      
       
    }
    //-----------------------------------------------------------
    public DModel() throws Exception
    {
    	
    	ddelegate = new DDelegate(this);
        Connect();
        SelectData();
        
        DisplayData();
    }

	
	
	 //--------------------------------------------------------------
    public void Connect() throws Exception
    {

        try{   
            Class.forName("org.postgresql.Driver");
            CONNEX = DriverManager.getConnection
                    ("jdbc:postgresql://127.0.0.1:5433/EHR", "postgres",
                            "Printer238");
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;
        }
    }   
    //----------------------------------------------------------------------
    public void SelectData()
    {
        try
        {
            STATE = CONNEX.createStatement();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL = "Select * FROM patients ORDER BY patientID";
            RESULT = STATE.executeQuery(SQL);
        }
        catch(Exception X){}
    }
    //--------------------------------------------------------------------------       
    public void DisplayData() throws Exception
    {
        try
        {
            RESULT.next();

            ddelegate.TF_ID.setText(RESULT.getString("patientID"));
            ddelegate.TF_NAME.setText(RESULT.getString("patientName"));
            ddelegate.TF_SSN.setText(RESULT.getString("ssn"));
            ddelegate.TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) {}
    }
    //---------------------------------------------------------------------------
   
    //---------------------------------------------------------------------------
    public void B_NEXT_ACTION()
    {
        try
        {  System.out.println("NEXT Button has been clicked");
            if( RESULT.next() );
    {
            	
            	
           ddelegate.TF_ID.setText(RESULT.getString("patientid"));
               ddelegate.TF_NAME.setText(RESULT.getString("patientName"));
               ddelegate.TF_SSN.setText(RESULT.getString("ssn"));
               ddelegate.TF_DOB.setText(RESULT.getString("dob")); 
            } 

        }
        catch(Exception X) { System.out.println(X + "B_NEXT_ACTION");
        }
    }

   public void B_PREV_ACTION()
    {
        try
        {
            if( RESULT.previous() );
            {
            	 ddelegate.TF_ID.setText(RESULT.getString("patientID"));
            	 ddelegate.TF_NAME.setText(RESULT.getString("patientName"));
            	 ddelegate.TF_SSN.setText(RESULT.getString("ssn"));
            	 ddelegate.TF_DOB.setText(RESULT.getString("dob"));
            }  
        }
        catch(Exception X) { System.out.println(X);
        }
    }

    public void B_FIRST_ACTION()
    {
        try
        {
            RESULT.first();
            ddelegate.TF_ID.setText(RESULT.getString("patientID"));
            ddelegate.TF_NAME.setText(RESULT.getString("patientName"));
            ddelegate.TF_SSN.setText(RESULT.getString("ssn"));
            ddelegate.TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //-----------------------------------------------------------------------
    public void B_LAST_ACTION()
    {
        try
        {
            RESULT.last();
            ddelegate.TF_ID.setText(RESULT.getString("patientID"));
            ddelegate.TF_NAME.setText(RESULT.getString("patientName"));
            ddelegate.TF_SSN.setText(RESULT.getString("ssn"));
            ddelegate.TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //----------------------------------------------------------------------------
    public void B_UPDATE_ACTION()
    {
        try
        {
            RESULT.updateInt("patientId", Integer.parseInt( ddelegate.TF_ID.getText()));
            RESULT.updateString("patientName",  ddelegate.TF_NAME.getText());
            RESULT.updateInt("ssn", Integer.parseInt( ddelegate.TF_SSN.getText()));
            RESULT.updateString("dob", ( ddelegate.TF_DOB.getText()));
            RESULT.updateRow();
            JOptionPane.showMessageDialog(null, "Updated!");
        }
        catch(Exception X) { System.out.println(X);}
    }

    //----------------------------------------------------------------------------
    public void B_DELETE_ACTION()
    {
        try
        {
            RESULT.deleteRow();
            RESULT.previous();
            DisplayData();

            JOptionPane.showMessageDialog(null, "Deleted!");
        }
        catch(Exception X) { System.out.println(X);}
    }
    //-------------------------------------------------------------------------
    public void B_NEW_ACTION()
    {
    	 ddelegate.TF_ID.setText("");
    	 ddelegate.TF_NAME.setText("");
    	 ddelegate.TF_SSN.setText("");
    	 ddelegate.TF_DOB.setText("");
    }
    //----------------------------------------------------------------------
    public void B_SAVE_ACTION()
    {
        try
        {
            RESULT.moveToInsertRow();

            RESULT.updateInt("patientId", Integer.parseInt( ddelegate.TF_ID.getText()));
            RESULT.updateString("patientName",  ddelegate.TF_NAME.getText());
            RESULT.updateInt("ssn", Integer.parseInt( ddelegate.TF_SSN.getText()));
            RESULT.updateString("dob",( ddelegate.TF_DOB.getText()));
            RESULT.insertRow();

            STATE.close();
            SelectData();

            RESULT.last();
            DisplayData();

            JOptionPane.showMessageDialog(null, "Updated!");
        }
        catch(Exception X) { System.out.println(X);}
    }
 /*********************************--------------------------
    public void B_SEARCH_ACTION()
    {
        String SearchItem = TF_SEARCH.getText();
        System.out.println(TF_SEARCH.getText());
        try
        {
            STATE.close();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL_COMMAND = "SELECT * FROM patients WHERE ";

            if(RB_ID.isSelected()) {SQL_COMMAND = SQL_COMMAND + "patientid = " 
                    + SearchItem;}


            if(RB_NAME.isSelected()) {SQL_COMMAND = SQL_COMMAND + "patientName "
                    + "LIKE '%"+ SearchItem +"%'" ;}
            if(RB_SSN.isSelected()) {SQL_COMMAND = SQL_COMMAND + "ssn = " 
                    + SearchItem;}
            if(RB_DOB.isSelected()) {SQL_COMMAND = SQL_COMMAND + "dob = " 
                    + SearchItem;}

            RESULT = STATE.executeQuery(SQL_COMMAND);


            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(RESULT.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        RESULT.getString("patientid") + " " +
                        RESULT.getString("patientName") + " " +
                        RESULT.getString("ssn") + " " +
                        RESULT.getString("dob");
            }
            if(TEST != 0)
            {
                RESULT.absolute(TEST); //Move to last of found items
                DisplayData();
                RESPONSE = "Number of Records that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            SelectData();
            RESULT.first();
            DisplayData();

        }
        catch(Exception X){ System.out.print(X);}

        TF_SEARCH.setText("");
    }

    //--------------------SCHEDULE LOOKUP--------------------------------
    public void B_SEARCH_ACTION2()
    {
        String SearchItem = TF_SEARCH2.getText();
        System.out.println(TF_SEARCH2.getText());

        try
        {
            STATE.close();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL_COMMAND = "SELECT * FROM patients, schedules WHERE "
                    + "patients.patientid=schedules.patientid"
                    + " AND patientName LIKE '%"+ SearchItem +"%'" ;

            RESULT = STATE.executeQuery(SQL_COMMAND);

            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(RESULT.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        RESULT.getString("patientName") + " " +
                        RESULT.getString("scheduleddate") + " " +
                        RESULT.getString("scheduledtime");
            }
            if(TEST != 0)
            {
                RESULT.absolute(TEST); //Move to last of found items
                DisplayData2();
                RESPONSE = "Number of Recoreds that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            // SelectData();
            RESULT.first();
            DisplayData2();

        }
        catch(Exception X){ System.out.print(X);}

        TF_SEARCH2.setText("");
    }
    //--------------------------------------------------------------------------       
    public void DisplayData2() throws Exception
    {
        try
        {
            RESULT.next();

            TF_NAME2.setText(RESULT.getString("patientName"));
            TF_SCHEDULEDDATE2.setText(RESULT.getString("scheduleddate"));
            TF_SCHEDULEDTIME2.setText(RESULT.getString("scheduleddtime"));
        }
        catch(Exception X) {}
    }

    //--------------------SCHEDULE LOOKUP--------------------------------
    public void B_SEARCH_ACTION4()
    {
        String SearchItem = TF_SEARCH4.getText();
        System.out.println(TF_SEARCH4.getText());
        String SearchItem2 = TF_DRSEARCH4.getText();

        try
        {
            STATE.close();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL_COMMAND = "SELECT  Count(*) count, drname, patientname "
                    + "FROM doctors, patients, schedules WHERE schedules.patientid "
                    + "= patients.patientid AND doctors.drid = schedules.drid " 
                    +" AND patientName LIKE '%"+SearchItem+"%' AND drname LIKE "
                    + "'%"+SearchItem2+"%' "
                    + "GROUP BY patientname, drname" ;

            RESULT = STATE.executeQuery(SQL_COMMAND);

            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(RESULT.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        RESULT.getString("patientName") + " " +
                        RESULT.getString("drname") + " " +
                        RESULT.getString("count");
            }
            if(TEST != 0)
            {
                RESULT.absolute(TEST); //Move to last of found items
                DisplayData4();
                RESPONSE = "Number of Recoreds that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            // SelectData();
            RESULT.first();
            DisplayData4();

        }
        catch(Exception X){ System.out.print(X);}

        TF_SEARCH2.setText("");
    }
    //--------------------------------------------------------------------------       
    public void DisplayData4() throws Exception
    {
        try
        {
            RESULT.next();

            TF_NAME4.setText(RESULT.getString("patientName"));
            TF_DRNAME4.setText(RESULT.getString("drname"));
            TF_COUNT4.setText(RESULT.getString("count"));
        }
        catch(Exception X) {}
    }
*/


}
