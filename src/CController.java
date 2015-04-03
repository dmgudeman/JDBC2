import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CController {

	// Globals
	static Connection CONNEX;
	static Statement STATE;
	private CModel cmodel;
	private CView cview;

	CController(CView cview, CModel cmodel) throws Exception {
		
		this.cmodel = cmodel;
		this.cview = cview;
		
		cmodel.SelectData();
		cmodel.DisplayData();
	}

	

	public void actionPerformed(ActionEvent ae) {

		if (ae.getActionCommand().equals("B_NEXT")) {
			System.out.println("NEXT Pressed");
			cmodel.B_NEXT_ACTION();
		}

		/*
		 * if(SRC == B_PREV) { B_PREV_ACTION(); }
		 * 
		 * if(SRC == B_FIRST) { B_FIRST_ACTION(); }
		 * 
		 * if(SRC == B_LAST) { B_LAST_ACTION(); }
		 * 
		 * if(SRC == B_UPDATE) { B_UPDATE_ACTION(); }
		 * 
		 * if(SRC == B_DELETE) { B_DELETE_ACTION(); }
		 * 
		 * if(SRC == B_NEW) { B_NEW_ACTION(); }
		 * 
		 * if(SRC == B_SAVE) { B_SAVE_ACTION(); }
		 */

	}
	// --
}
