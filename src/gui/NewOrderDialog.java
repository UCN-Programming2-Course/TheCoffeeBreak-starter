package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import model.Order;
import model.OrderLine;

public class NewOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private Order order;


	private final JPanel contentPane;
	private final JPanel buttonPane;
	private final JTextField txtCustomerName;
	private final JTextField txtDiscount;
	private final JTextField txtTotalPrice;
	private final JList<OrderLine> lstItems;
	private final JButton btnOk;
	private final JButton btnCancel;

	public NewOrderDialog() {

		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(350, 300);
		getContentPane().setLayout(new BorderLayout());
		setTitle("New Order");

		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// customer name
		JLabel lblCustomerName = new JLabel("Customer Name: ");
		lblCustomerName.setBounds(10, 30, 100, 14);
		contentPane.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(110, 27, 150, 20);
		contentPane.add(txtCustomerName);
		
		

		// order items
		JLabel lblOrderItems = new JLabel("Items: ");
		lblOrderItems.setBounds(10, 60, 70, 14);
		contentPane.add(lblOrderItems);

		lstItems = new JList<OrderLine>();
		lstItems.setCellRenderer(new ItemCellRenderer());
		lstItems.setEnabled(false);
		scrollPane.setBounds(110, 57, 150, 53);
		scrollPane.setViewportView(lstItems);

		// order discount
		JLabel lblOrderDiscount = new JLabel("Discout(%): ");
		lblOrderDiscount.setBounds(10, 115, 70, 14);
		contentPane.add(lblOrderDiscount);

		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(110, 112, 150, 20);
		contentPane.add(txtDiscount);

		// total price
		JLabel lblTotalPrice = new JLabel("Total Price: ");
		lblTotalPrice.setBounds(10, 137, 70, 14);
		contentPane.add(lblTotalPrice);

		txtTotalPrice = new JTextField();
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(110, 134, 150, 20);
		txtTotalPrice.setEditable(false);
		txtTotalPrice.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtTotalPrice);

		// button pane
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	
				createOrder();
				setVisible(false);
			}
		});
		buttonPane.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cancelOrder();
				setVisible(false);
			}
		});
		buttonPane.add(btnCancel);
	}

	private void update() {


	}
	
	private void cancelOrder() {
		
		this.order = null;
	}
	
	private void createOrder() {
		
		this.order = new Order();
		this.order.setCustomerName(txtCustomerName.getText());
		this.order.setDiscount(Double.parseDouble(txtDiscount.getText()));
		this.order.setStatus(Order.ACTIVE);		
	}

	public Order getOrder() {
		return order;
	}
	
	public class ItemCellRenderer implements ListCellRenderer<OrderLine> {

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();

		@Override
		public Component getListCellRendererComponent(JList<? extends OrderLine> list, OrderLine value, int index,
				boolean isSelected, boolean cellHasFocus) {

			String cellText = value.getQuantity() + " " + value.getItem().getName();

			return renderer.getListCellRendererComponent(list, cellText, index, isSelected, cellHasFocus);
		}
	}

}
