package gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class GuiHelpers {

	public static <T> ListModel<T> mapToListModel(List<T> list){
				
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		for (int idx = 0; idx < list.size(); idx++) {
			listModel.addElement(list.get(idx));
		}
		return listModel;		
	}
}
