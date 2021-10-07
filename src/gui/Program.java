package gui;

import controller.ControllerFactory;

public class Program {

	public static void main(String args[]) {
		
		MainWindow main = new MainWindow(ControllerFactory.getOrdersController()); // TODO replace the mocked controller with a real one from the controller layer
		main.setVisible(true);
	}
}
