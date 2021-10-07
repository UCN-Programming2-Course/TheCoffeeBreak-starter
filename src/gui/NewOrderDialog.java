package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.OrdersController;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

public class NewOrderDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private Order order;
	private boolean accepted;

	private final JPanel contentPane;
	private final JPanel buttonPane;
	private final JTextField txtCustomerName;
	private final JTextField txtDiscount;
	private final JTextField txtTotalPrice;
	private final JComboBox<Product> cboProducts;
	private final JList<OrderLine> lstOrderlines;
	private final JButton btnAddProduct;
	private final JButton btnOk;
	private final JButton btnCancel;

	private final OrdersController ordersCtrl;

	public NewOrderDialog(OrdersController ordersCtrl) {

		this.ordersCtrl = ordersCtrl;

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
//		txtCustomerName.getDocument().addDocumentListener(new DocumentListener() {
//
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				addCustomer();
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				
//			}
//
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//
//			}
//		});
		contentPane.add(txtCustomerName);

		// order items
		JLabel lblOrderItems = new JLabel("Items: ");
		lblOrderItems.setBounds(10, 60, 70, 14);
		contentPane.add(lblOrderItems);

		cboProducts = new JComboBox<>();
		cboProducts.setBounds(110, 57, 150, 20);
		cboProducts.setRenderer(new ProductCellRenderer());
		contentPane.add(cboProducts);

		btnAddProduct = new JButton("+");
		btnAddProduct.setBounds(265, 57, 44, 20);
		btnAddProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addSelectedProductToOrder();
			}

		});
		contentPane.add(btnAddProduct);

		lstOrderlines = new JList<OrderLine>();
		lstOrderlines.setCellRenderer(new OrderLineCellRenderer());
		lstOrderlines.setEnabled(false);
		scrollPane.setBounds(110, 80, 200, 53);
		scrollPane.setViewportView(lstOrderlines);

		// order discount
		JLabel lblOrderDiscount = new JLabel("Discout (%): ");
		lblOrderDiscount.setBounds(10, 139, 75, 14);
		contentPane.add(lblOrderDiscount);

		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		txtDiscount.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {

				calculateDiscount();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				calculateDiscount();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

				calculateDiscount();
			}

		});
		txtDiscount.setBounds(110, 135, 200, 20);

		contentPane.add(txtDiscount);

		// total price
		JLabel lblTotalPrice = new JLabel("Total Price: ");
		lblTotalPrice.setBounds(10, 159, 75, 14);
		contentPane.add(lblTotalPrice);

		txtTotalPrice = new JTextField();
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setBounds(110, 157, 150, 20);
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

				ok();
			}
		});
		buttonPane.add(btnOk);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cancel();
			}
		});
		buttonPane.add(btnCancel);

		init();
	}

	private void init() {
		this.order = new Order();
		cboProducts.setModel(GuiHelpers.mapToComboBoxModel(ordersCtrl.getAllProducts()));
		lstOrderlines.setModel(new DefaultListModel<OrderLine>());
	}

	private void update() {

		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		
		lstOrderlines.setModel(GuiHelpers.mapToListModel(this.order.getOrderLines()));
		lstOrderlines.updateUI();
		txtTotalPrice.setText(currencyFormatter.format(this.order.getTotalPrice()));
	}

	private void calculateDiscount() {
		if (this.order != null) {
			double discount = Double.parseDouble(txtDiscount.getText());
			order.setDiscount(discount);		
			update();
		}
	}

	private void addSelectedProductToOrder() {

		Product product = (Product) cboProducts.getSelectedItem();
		boolean itemAdded = false;

		for (int idx = 0; idx < this.order.getOrderLines().size(); idx++) {
			OrderLine ol = this.order.getOrderLines().get(idx);
			if (ol.getItem().getName() == product.getName()) {
				ol.setQuantity(ol.getQuantity() + 1);
				itemAdded = true;
				break;
			}
		}

		if (!itemAdded) {
			OrderLine ol = new OrderLine();
			ol.setItem(product);
			ol.setQuantity(1);

			this.order.getOrderLines().add(ol);
		}

		update();
	}

	private void cancel() {

		this.order = null;
		this.accepted = false;
		setVisible(false);
	}

	private void ok() {
		
		Customer customer = new Customer();
		customer.setName(txtCustomerName.getText());
		this.order.setCustomer(customer);
		this.accepted = true;
		setVisible(false);
	}

	public Order getOrder() {
		return order;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public class OrderLineCellRenderer implements ListCellRenderer<OrderLine> {

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();

		@Override
		public Component getListCellRendererComponent(JList<? extends OrderLine> list, OrderLine value, int index,
				boolean isSelected, boolean cellHasFocus) {

			String cellText = value.getQuantity() + " " + value.getItem().getName();

			return renderer.getListCellRendererComponent(list, cellText, index, isSelected, cellHasFocus);
		}
	}

	public class ProductCellRenderer implements ListCellRenderer<Product> {

		DefaultListCellRenderer renderer = new DefaultListCellRenderer();

		@Override
		public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index,
				boolean isSelected, boolean cellHasFocus) {

			String renderedText = value.getName();

			return renderer.getListCellRendererComponent(list, renderedText, index, isSelected, cellHasFocus);
		}

	}
}
