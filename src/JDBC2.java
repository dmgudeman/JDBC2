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

    //GUI Globals
    JFrame MainWindow;
    JLabel JL_ID;
    JLabel JL_NAME;
    JLabel JL_SSN;
    JLabel JL_DOB;

    JLabel JL_NAME2;
    JLabel JL_NAME4;
    JLabel JL_DRNAME4;

    JTextField TF_ID;
    JTextField TF_NAME;
    JTextField TF_SSN;
    JTextField TF_DOB;

    JTextField TF_SEARCH = new JTextField(10);

    JTextField TF_SEARCH2 = new JTextField(20);
    JTextField TF_NAME2;
    JTextField TF_SCHEDULEDDATE2;
    JTextField TF_SCHEDULEDTIME2;

    JTextField TF_SEARCH4 = new JTextField(20);
    JTextField TF_DRSEARCH4 = new JTextField(20);
    JTextField TF_NAME4;
    JTextField TF_DRNAME4;
    JTextField TF_COUNT4;

    JButton B_NEXT = new JButton("NEXT");
    JButton B_PREV = new JButton("PREV");
    JButton B_FIRST = new JButton("FIRST");
    JButton B_LAST = new JButton("LAST");
    JButton B_UPDATE = new JButton("UPDATE");
    JButton B_DELETE = new JButton("DELETE");
    JButton B_NEW = new JButton("NEW");
    JButton B_SAVE = new JButton("SAVE");


    JButton B_SEARCH = new JButton("SEARCH");
    ButtonGroup SearchChoices = new ButtonGroup();
    JRadioButton RB_ID = new JRadioButton("patientId");
    JRadioButton RB_NAME = new JRadioButton("patientName");
    JRadioButton RB_SSN = new JRadioButton("ssn");
    JRadioButton RB_DOB = new JRadioButton("dob");

    JButton B_SEARCH2 = new JButton("SHOW LIST OF PATIENT VISITS");
    JButton B_SEARCH4 = new JButton("SHOW LIST OF PATIENT DR");

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
    //-------------------------------------------------------------
    public void BuildGUI()
    {
        setLayout(new GridLayout(6, 12));
        MainWindow = new JFrame();
        MainWindow.setSize(650,300);
        MainWindow.setTitle("EHR DATABASE");
        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JL_ID = new JLabel("ID: ");
        JL_NAME = new JLabel("NAME: ");
        JL_SSN = new JLabel("SSN: ");
        JL_DOB = new JLabel("DOB: ");


        JL_NAME2 = new JLabel("TYPE IN PART OF NAME: ");
        JL_NAME4 = new JLabel("Input Patient: ");
        JL_DRNAME4 = new JLabel("Input Doctor: ");

        TF_ID = new JTextField(10);
        TF_NAME = new JTextField(10);
        TF_SSN = new JTextField(10);
        TF_DOB = new JTextField(10);




        JPanel BG = new JPanel();

        BG.add(JL_ID);
        BG.add(TF_ID);
        BG.add(JL_NAME);
        BG.add(TF_NAME);
        BG.add(JL_SSN);
        BG.add(TF_SSN);
        BG.add(JL_DOB);
        BG.add(TF_DOB);

        BG.add(B_NEXT);
        BG.add(B_PREV);
        BG.add(B_FIRST);
        BG.add(B_LAST);
        BG.add(B_UPDATE);
        BG.add(B_DELETE);
        BG.add(B_NEW);
        BG.add(B_SAVE);

        BG.add(TF_SEARCH);
        BG.add(B_SEARCH);
        BG.add(RB_ID);
        BG.add(RB_NAME);
        BG.add(RB_SSN);
        BG.add(RB_DOB);

        BG.add(JL_NAME2);
        BG.add(TF_SEARCH2);
        BG.add(B_SEARCH2);

        BG.add(JL_NAME4);
        BG.add(TF_SEARCH4);
        BG.add(JL_DRNAME4);
        BG.add(TF_DRSEARCH4);
        BG.add(B_SEARCH4);

        B_NEXT.addActionListener(this);
        B_PREV.addActionListener(this);        
        B_FIRST.addActionListener(this);        
        B_LAST.addActionListener(this);      
        B_UPDATE.addActionListener(this);
        B_DELETE.addActionListener(this);        
        B_NEW.addActionListener(this);        
        B_SAVE.addActionListener(this); 
        B_SEARCH.addActionListener(this);
        B_SEARCH2.addActionListener(this);
        B_SEARCH4.addActionListener(this);


        //use button group to keep 2 buttons from being pushed simultaneously
        SearchChoices.add(RB_ID);
        SearchChoices.add(RB_NAME);
        SearchChoices.add(RB_SSN);
        SearchChoices.add(RB_DOB);
        //start radio button search off on name
        RB_NAME.setSelected(true);

        MainWindow.add(BG);

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

        if (SRC == B_SEARCH)
        { B_SEARCH_ACTION();}

        if (SRC == B_SEARCH2)
        { B_SEARCH_ACTION2();}

        if (SRC == B_SEARCH4)
        { B_SEARCH_ACTION4();}


    }
    //---------------------------------------------------------------------------
    public void B_NEXT_ACTION()
    {
        try
        {
            if( RESULT.next() );
            {
                TF_ID.setText(RESULT.getString("patientid"));
                TF_NAME.setText(RESULT.getString("patientName"));
                TF_SSN.setText(RESULT.getString("ssn"));
                TF_DOB.setText(RESULT.getString("dob"));
            }

        }
        catch(Exception X) { System.out.println(X + "B_NEXT_ACTION");
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
    //--------------------DR-PT LOOKUP--------------------------------


}
