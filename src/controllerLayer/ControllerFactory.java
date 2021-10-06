package controllerLayer;

import controllerLayer.implementations.OrdersControllerImplementation;

public class ControllerFactory {

	public static OrdersController getOrdersController() {
		
		// TODO instantiate the concrete implementation of the controller and return it as an interface
		
		return new OrdersControllerImplementation(new Database());
	}
}
