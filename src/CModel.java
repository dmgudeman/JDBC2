//Making GUI for a database
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class CModel {

    
	static Connection CONNEX;
	Statement STATE;
	private CController ccontroller;
    ResultSet resultSet;
	private CView cview;

    public CModel(CController ccontroller, CView cview ) throws Exception {
       this.ccontroller = ccontroller;
       this.cview = cview;   
    }
    
   
   
    public CModel() {
		// TODO Auto-generated constructor stub
	}

	public void SelectData() {
        try {
            STATE = CONNEX.createStatement();
            STATE = CONNEX.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            String SQL = "Select * FROM patients ORDER BY patientID";
            resultSet = STATE.executeQuery(SQL);
            System.out.println("SelectData called");
            System.out.println(resultSet.toString());
        }
        catch(Exception X){
        	
        }
    }
         
    public void DisplayData() throws Exception {
 
        try {
        	 System.out.println("DIsplayData Called");
          //   resultSet.next();
            
        	
          //  cview.TF_ID.setText(resultSet.getString("patientid"));
            
          //  cview.TF_NAME.setText(resultSet.getString("patientName"));
            System.out.println("DIsplayData Called 2");
          //  cview.TF_SSN.setText(resultSet.getString("ssn"));
          //  cview.TF_DOB.setText(resultSet.getString("dob"));
            System.out.println("is null" + (resultSet.toString()));
            
        }
        catch(Exception X) {
        	
        }
    }
   
/*    public void B_NEXT_ACTION() throws SQLException {
       
        try {
            if(resultSet.next() ) {
            	cview.TF_ID.setText(resultSet.getString("patientid"));
            	cview.TF_NAME.setText(resultSet.getString("patientName"));
            	cview.TF_SSN.setText(resultSet.getString("ssn"));
            	cview.TF_DOB.setText(resultSet.getString("dob"));
            }

        }
        catch(Exception X) { 
        	System.out.println(X + "B_NEXT_ACTION");
        	
        }
    } */
   //public String SelectXData()
    public void B_NEXT_ACTION() throws SQLException {
    
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
		
    }
}
    //-------------------------------------------------------------------
 /*   public void B_PREV_ACTION()
    {
        try
        {
            if( resultSet.previous() );
            {
                TF_ID.setText(resultSet.getString("patientID"));
                TF_NAME.setText(resultSet.getString("patientName"));
                TF_SSN.setText(resultSet.getString("ssn"));
                TF_DOB.setText(resultSet.getString("dob"));
            }  
        }
        catch(Exception X) { System.out.println(X);
        }
    }
    //-----------------------------------------------------------------------
    public void B_FIRST_ACTION()
    {
        try
        {
            resultSet.first();
            TF_ID.setText(resultSet.getString("patientID"));
            TF_NAME.setText(resultSet.getString("patientName"));
            TF_SSN.setText(resultSet.getString("ssn"));
            TF_DOB.setText(resultSet.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //-----------------------------------------------------------------------
    public void B_LAST_ACTION()
    {
        try
        {
            resultSet.last();
            TF_ID.setText(resultSet.getString("patientID"));
            TF_NAME.setText(resultSet.getString("patientName"));
            TF_SSN.setText(resultSet.getString("ssn"));
            TF_DOB.setText(resultSet.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //----------------------------------------------------------------------------
    public void B_UPDATE_ACTION()
    {
        try
        {
            resultSet.updateInt("patientId", Integer.parseInt(TF_ID.getText()));
            resultSet.updateString("patientName", TF_NAME.getText());
            resultSet.updateInt("ssn", Integer.parseInt(TF_SSN.getText()));
            resultSet.updateString("dob", (TF_DOB.getText()));
            resultSet.updateRow();
            JOptionPane.showMessageDialog(null, "Updated!");
        }
        catch(Exception X) { System.out.println(X);}
    }

    //----------------------------------------------------------------------------
    public void B_DELETE_ACTION()
    {
        try
        {
            resultSet.deleteRow();
            resultSet.previous();
            DisplayData();

            JOptionPane.showMessageDialog(null, "Deleted!");
        }
        catch(Exception X) { System.out.println(X);}
    }
    //-------------------------------------------------------------------------
    public void B_NEW_ACTION()
    {
        TF_ID.setText("");
        TF_NAME.setText("");
        TF_SSN.setText("");
        TF_DOB.setText("");
    }
    //----------------------------------------------------------------------
    public void B_SAVE_ACTION()
    {
        try
        {
            resultSet.moveToInsertRow();

            resultSet.updateInt("patientId", Integer.parseInt(TF_ID.getText()));
            resultSet.updateString("patientName", TF_NAME.getText());
            resultSet.updateInt("ssn", Integer.parseInt(TF_SSN.getText()));
            resultSet.updateString("dob",(TF_DOB.getText()));
            resultSet.insertRow();

            STATE.close();
            SelectData();

            resultSet.last();
            DisplayData();

            JOptionPane.showMessageDialog(null, "Updated!");
        }
        catch(Exception X) { System.out.println(X);}
    }
    //--------------------------------------------------------------------------
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

            resultSet = STATE.executeQuery(SQL_COMMAND);


            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(resultSet.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        resultSet.getString("patientid") + " " +
                        resultSet.getString("patientName") + " " +
                        resultSet.getString("ssn") + " " +
                        resultSet.getString("dob");
            }
            if(TEST != 0)
            {
                resultSet.absolute(TEST); //Move to last of found items
                DisplayData();
                RESPONSE = "Number of Records that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            SelectData();
            resultSet.first();
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

            resultSet = STATE.executeQuery(SQL_COMMAND);

            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(resultSet.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        resultSet.getString("patientName") + " " +
                        resultSet.getString("scheduleddate") + " " +
                        resultSet.getString("scheduledtime");
            }
            if(TEST != 0)
            {
                resultSet.absolute(TEST); //Move to last of found items
                DisplayData2();
                RESPONSE = "Number of Recoreds that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            // SelectData();
            resultSet.first();
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
            resultSet.next();

            TF_NAME2.setText(resultSet.getString("patientName"));
            TF_SCHEDULEDDATE2.setText(resultSet.getString("scheduleddate"));
            TF_SCHEDULEDTIME2.setText(resultSet.getString("scheduleddtime"));
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

            resultSet = STATE.executeQuery(SQL_COMMAND);

            int TEST =0;
            String STORE = "";
            String RESPONSE = "";

            while(resultSet.next())
            {
                TEST++;

                STORE = STORE + "\n" + TEST + ". " +
                        resultSet.getString("patientName") + " " +
                        resultSet.getString("drname") + " " +
                        resultSet.getString("count");
            }
            if(TEST != 0)
            {
                resultSet.absolute(TEST); //Move to last of found items
                DisplayData4();
                RESPONSE = "Number of Recoreds that match:  " + TEST + STORE;

                JOptionPane.showMessageDialog(null, RESPONSE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Could not find that.");
            }

            // SelectData();
            resultSet.first();
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
            resultSet.next();

            TF_NAME4.setText(resultSet.getString("patientName"));
            TF_DRNAME4.setText(resultSet.getString("drname"));
            TF_COUNT4.setText(resultSet.getString("count"));
        }
        catch(Exception X) {}
    }
    //--------------------DR-PT LOOKUP--------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} */



