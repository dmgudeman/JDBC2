import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class CView {
	CButtonHandler buttonHandler = new CButtonHandler();
	CController ccontroller;
	
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
	
    

	CView(CController ccontroller) {
		this.ccontroller = ccontroller;
        BuildGUI();
	};

	public void BuildGUI() {
		
		setLayout(new GridLayout(6, 12));
		MainWindow = new JFrame();
		MainWindow.setSize(650, 300);
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
		B_NEXT.addActionListener(buttonHandler);
		
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

		

		// use button group to keep 2 buttons from being pushed simultaneously
		SearchChoices.add(RB_ID);
		SearchChoices.add(RB_NAME);
		SearchChoices.add(RB_SSN);
		SearchChoices.add(RB_DOB);
		// start radio button search off on name
		RB_NAME.setSelected(true);

		MainWindow.add(BG);

		MainWindow.setVisible(true);
	}

	private void setLayout(GridLayout gridLayout) {
		// TODO Auto-generated method stub
		
	}

}
