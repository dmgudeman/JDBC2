import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CButtonHandler implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		CModel cmodel = new CModel();
		System.out.println(ae.getActionCommand());

		if (ae.getActionCommand().equals("B_NEXT")) {
			
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
	

}
