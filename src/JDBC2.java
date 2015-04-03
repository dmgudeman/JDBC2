//Making GUI for a database
import java.sql.*;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;

public class JDBC2 extends JFrame implements ActionListener
{
     //Globals
    Connection CONNEX;
    Statement STATE;
    ResultSet RESULT;
	
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 500;

	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	// initialize the elements in the frame

	private JPanel panel;
	 //GUI Globals
    JFrame MainWindow;
    private JLabel JL_TITLE;
    JLabel JL_ID;
    JLabel JL_NAME;
    JLabel JL_SSN;
    JLabel JL_DOB;

    JTextField TF_ID;
    JTextField TF_NAME;
    JTextField TF_SSN;
    JTextField TF_DOB;
    
    JButton B_NEXT;
    JButton B_PREV;
    JButton B_FIRST;
    JButton B_LAST;
    JButton B_UPDATE;
    JButton B_DELETE;
    JButton B_NEW;
    JButton B_SAVE;
  //----------------------------------------------------------
    public static void main(String[] args) throws Exception
    {
        new JDBC2();
    }
    //-----------------------------------------------------------
    public JDBC2() throws Exception
    {
        Connect();
        SelectData();
        BuildGUI();
        DisplayData();
    }
    //-
    public void BuildGUI() {
		
		// specifics for the JFrame of this class DG
		//this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	//	this.setResizable(false);
    	 MainWindow = new JFrame();
         MainWindow.setSize(800, 400);
         MainWindow.setTitle("EHR DATABASE");
         MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// creates panel DG
		panel = new JPanel(new GridBagLayout());
		panel.setSize(700, 300);
		Color purpleMedium = new Color(93, 119, 178, 150);
		panel.setBackground(purpleMedium);

		// declares the elements in the frame DG
		 JL_TITLE = new JLabel("Input Patient Data");
		 JL_TITLE.setFont(new Font("Geneva", Font.ROMAN_BASELINE, 30));
		
		 JL_ID = new JLabel("Input Patient ID: ");
         JL_NAME = new JLabel("Input Patient Name: ");
         JL_SSN = new JLabel("Input Patient SSN: ");
         JL_DOB = new JLabel("Input Patient DOB: ");
         
		 TF_ID = new JTextField(10);
         TF_NAME = new JTextField(10);
         TF_SSN = new JTextField(10);
         TF_DOB = new JTextField(10);
	
         B_NEXT = new JButton("NEXT");
         B_PREV = new JButton("PREV");
         B_FIRST = new JButton("FIRST");		
         B_LAST = new JButton("LAST");
         
         B_UPDATE = new JButton("UPDATE");
         B_DELETE = new JButton("DELETE");
         B_NEW = new JButton("NEW");
         B_SAVE = new JButton("SAVE");
         
         
         




		/**
		 * Uses Layout tool to position the elements in the panel Gudeman
		 */
		// creates an object to hold the gridBaglayout constraints DG
         GridBagConstraints c = new GridBagConstraints();

		// sets the distance between elements DG
		c.insets = new Insets(5, 5, 5, 5);

		// use GridBagLayout specifications to position the components DG
		
		c.anchor = GridBagConstraints.CENTER ;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		panel.add(JL_TITLE, c);

		c.weightx = 0.5;
		c.anchor = GridBagConstraints.EAST ;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 10;
		panel.add(JL_ID, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 1;
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(TF_ID, c);
		
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST ;
		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 1;
		panel.add(JL_NAME, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 2;
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(TF_NAME, c);
		
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST ;
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 10;
		panel.add(JL_SSN, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 3;
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(TF_SSN, c);
		
		c.weightx = 0.5;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST ;
		c.gridy = 4;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 10;
		panel.add(JL_DOB, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 4;
		c.gridx = 1;
		c.gridwidth = 3;
		panel.add(TF_DOB, c);
		
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 1;
		c.ipady = 20;
		panel.add(B_NEXT, c);

		c.gridy = 5;
		c.gridx = 1;
		c.gridwidth = 1;
		panel.add(B_PREV, c);

		c.gridy = 5;
		c.gridx = 2;
		c.gridwidth = 1;
		panel.add(B_FIRST, c);

		c.gridy = 5;
		c.gridx = 3;
		c.gridwidth = 1;
		panel.add(B_LAST, c);
		
		c.gridy = 6;
		c.gridx = 0;
		c.gridwidth = 1;
		panel.add(B_UPDATE, c);

		c.gridy = 6;
		c.gridx = 1;
		c.gridwidth = 1;
		panel.add(B_DELETE, c);

		c.gridy = 6;
		c.gridx = 2;
		c.gridwidth = 1;
		panel.add(B_NEW, c);

		c.gridy = 6;
		c.gridx = 3;
		c.gridwidth = 1;
		panel.add(B_SAVE, c);
		
		 B_NEXT.addActionListener(this);
	        B_PREV.addActionListener(this);        
	        B_FIRST.addActionListener(this);        
	        B_LAST.addActionListener(this);      
	        B_UPDATE.addActionListener(this);
	        B_DELETE.addActionListener(this);        
	        B_NEW.addActionListener(this);        
	        B_SAVE.addActionListener(this); 
	       
		
		      
            MainWindow.add(panel, BorderLayout.CENTER);

            MainWindow.setVisible(true);        
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

            TF_ID.setText(RESULT.getString("patientID"));
            TF_NAME.setText(RESULT.getString("patientName"));
            TF_SSN.setText(RESULT.getString("ssn"));
            TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) {}
    }
    //---------------------------------------------------------------------------
    public void actionPerformed(ActionEvent X)
    {
        Object SRC = X.getSource();

        if(SRC == B_NEXT)
        { B_NEXT_ACTION(); }

        if(SRC == B_PREV)
        { B_PREV_ACTION(); }

        if(SRC == B_FIRST)
        { B_FIRST_ACTION(); }

        if(SRC == B_LAST)
        { B_LAST_ACTION(); }

        if(SRC == B_UPDATE)
        { B_UPDATE_ACTION(); }

        if(SRC == B_DELETE)
        { B_DELETE_ACTION(); }

        if(SRC == B_NEW)
        { B_NEW_ACTION(); }

        if(SRC == B_SAVE)
        { B_SAVE_ACTION(); }

   /*     if (SRC == B_SEARCH)
        { B_SEARCH_ACTION();}

        if (SRC == B_SEARCH2)
        { B_SEARCH_ACTION2();}

        if (SRC == B_SEARCH4)
        { B_SEARCH_ACTION4();} */


    }
    //---------------------------------------------------------------------------
	public void B_NEXT_ACTION() {
		try {
			if (RESULT.next())
				;
			{
				TF_ID.setText(RESULT.getString("patientid"));
				TF_NAME.setText(RESULT.getString("patientName"));
				TF_SSN.setText(RESULT.getString("ssn"));
				TF_DOB.setText(RESULT.getString("dob"));
			}

		} catch (Exception X) {
			System.out.println(X + "B_NEXT_ACTION");
		}
	}
    //-------------------------------------------------------------------
    public void B_PREV_ACTION()
    {
        try
        {
            if( RESULT.previous() );
            {
                TF_ID.setText(RESULT.getString("patientID"));
                TF_NAME.setText(RESULT.getString("patientName"));
                TF_SSN.setText(RESULT.getString("ssn"));
                TF_DOB.setText(RESULT.getString("dob"));
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
            RESULT.first();
            TF_ID.setText(RESULT.getString("patientID"));
            TF_NAME.setText(RESULT.getString("patientName"));
            TF_SSN.setText(RESULT.getString("ssn"));
            TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //-----------------------------------------------------------------------
    public void B_LAST_ACTION()
    {
        try
        {
            RESULT.last();
            TF_ID.setText(RESULT.getString("patientID"));
            TF_NAME.setText(RESULT.getString("patientName"));
            TF_SSN.setText(RESULT.getString("ssn"));
            TF_DOB.setText(RESULT.getString("dob"));
        }
        catch(Exception X) { System.out.println(X);}
    }
    //----------------------------------------------------------------------------
    public void B_UPDATE_ACTION()
    {
        try
        {
            RESULT.updateInt("patientId", Integer.parseInt(TF_ID.getText()));
            RESULT.updateString("patientName", TF_NAME.getText());
            RESULT.updateInt("ssn", Integer.parseInt(TF_SSN.getText()));
            RESULT.updateString("dob", (TF_DOB.getText()));
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
            RESULT.moveToInsertRow();

            RESULT.updateInt("patientId", Integer.parseInt(TF_ID.getText()));
            RESULT.updateString("patientName", TF_NAME.getText());
            RESULT.updateInt("ssn", Integer.parseInt(TF_SSN.getText()));
            RESULT.updateString("dob",(TF_DOB.getText()));
            RESULT.insertRow();

            STATE.close();
            SelectData();

            RESULT.last();
            DisplayData();

            JOptionPane.showMessageDialog(null, "Updated!");
        }
        catch(Exception X) { System.out.println(X);}
    }
    //--------------------------------------------------------------------------
  /*  public void B_SEARCH_ACTION()
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
    } */
    //--------------------DR-PT LOOKUP--------------------------------


}
