import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DDelegate {
	ResultSet RESULT;
	DModel dmodel;
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 500;

	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);
	// initialize the elements in the frame

	private JPanel panel;
	// GUI Globals
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

	public DDelegate(DModel dmodel) throws Exception {
		this.dmodel = dmodel;
		BuildGUI();

	}

	public void BuildGUI() {

		// specifics for the JFrame of this class DG
		// this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// this.setResizable(false);
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

		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		panel.add(JL_TITLE, c);

		c.weightx = 0.5;
		c.anchor = GridBagConstraints.EAST;
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
		c.anchor = GridBagConstraints.EAST;
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
		c.anchor = GridBagConstraints.EAST;
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
		c.anchor = GridBagConstraints.EAST;
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

		MainWindow.add(panel, BorderLayout.CENTER);

		MainWindow.setVisible(true);

		// ActionListener added to NEXT button
		B_NEXT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_NEXT_ACTION();
					}
				});
			}
		});
		// ActionListener added to PREV button
		B_PREV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_PREV_ACTION();
					}
				});
			}
		});
		// ActionListener added to FIRST button
		B_FIRST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_FIRST_ACTION();
					}
				});
			}
		});
		// ActionListener added to LAST button
		B_LAST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_LAST_ACTION();
					}
				});
			}
		});
		// ActionListener added to UPDATE button
		B_UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_UPDATE_ACTION();
					}
				});
			}
		});
		// ActionListener added to DELETE button
		B_DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_DELETE_ACTION();
					}
				});
			}
		});
		// ActionListener added to NEW button
		B_NEW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_NEW_ACTION();
					}
				});
			}
		});
		// ActionListener added to SAVE button
		B_SAVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						dmodel.B_SAVE_ACTION();
					}
				});
			}
		});
	}
}
