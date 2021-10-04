package gui;


public class Program {

	public static void main(String args[]) {
		
		MainWindow main = new MainWindow(new OrdersControllerMock());
		main.setVisible(true);
	}
}
