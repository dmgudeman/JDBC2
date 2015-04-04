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

	

	
	// --
}
