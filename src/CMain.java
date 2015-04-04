import java.sql.DriverManager;
import java.sql.SQLException;


public class CMain {
	
	    

	
	 public static void main(String[] args) throws Exception
	    {    
		      
		      Connect();
		      CView cview = new CView(null);
		      CModel cmodel = new CModel();
		      cmodel.SelectData();
			  cmodel.DisplayData();
           
			
			CController ccontroller = new CController(cview, cmodel);
	        
	    }
	 public static void Connect() throws Exception {

			try {
				Class.forName("org.postgresql.Driver");
				CController.CONNEX = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5433/EHR", "postgres",
						"Printer238");
			} catch (SQLException e) {
				System.out.println("Connection Failed!");
				e.printStackTrace();
				return;
			}
		}
	
	
}
