package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import controllerLayer.OrdersController;
import model.Order;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JList<Order> listActiveOrders;
	private JButton btnNewOrder;
	private JButton btnFinishOrder;

	private OrdersController orderCtrl;

	public MainWindow(OrdersController orderCtrl) {
		super("Orders");
		this.orderCtrl = orderCtrl;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		FlowLayout layout = (FlowLayout) panel.getLayout();
		layout.setAlignment(FlowLayout.RIGHT);

		contentPane.add(panel, BorderLayout.SOUTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		listActiveOrders = new JList<>();
		listActiveOrders.setCellRenderer(new OrderCellRenderer());
		scrollPane.setViewportView(listActiveOrders);

		btnNewOrder = new JButton("New Order");
		btnNewOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openNewOrderDialog();
			}
			
		});
		panel.add(btnNewOrder);

		btnFinishOrder = new JButton("Finish Order");
		btnFinishOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				finishSelectedOrder();
			}
		});
		panel.add(btnFinishOrder);

		update();
	}

	private void finishSelectedOrder() {

		if (orderCtrl.setOrderStatusToFinished(listActiveOrders.getSelectedValue())) {

			update();
		}
	}

	private void openNewOrderDialog() {

		NewOrderDialog window = new NewOrderDialog();
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		if(orderCtrl.createNewOrder(window.getOrder())) {
			
			update();
		}		
	}

	public void update() {
		List<Order> activeOrders = orderCtrl.getActiveOrders();
		listActiveOrders.setModel(GuiHelpers.mapToListModel(activeOrders));
	}

	// Renderer for active orders list
	public class OrderCellRenderer implements ListCellRenderer<Order> {

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();

		@Override
		public Component getListCellRendererComponent(JList<? extends Order> list, Order value, int index,
				boolean isSelected, boolean cellHasFocus) {

			String cellText = value.getCustomerName();

			return renderer.getListCellRendererComponent(list, cellText, index, isSelected, cellHasFocus);
		}

	}
}
