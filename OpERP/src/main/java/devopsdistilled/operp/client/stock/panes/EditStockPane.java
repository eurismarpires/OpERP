package devopsdistilled.operp.client.stock.panes;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import devopsdistilled.operp.client.abstracts.SubTaskPane;
import devopsdistilled.operp.client.items.models.observers.ItemModelObserver;
import devopsdistilled.operp.client.stock.models.observers.EditStockPaneModelObserver;
import devopsdistilled.operp.client.stock.models.observers.WarehouseModelObserver;
import devopsdistilled.operp.server.data.entity.items.Item;
import devopsdistilled.operp.server.data.entity.stock.Stock;
import devopsdistilled.operp.server.data.entity.stock.Warehouse;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class EditStockPane extends SubTaskPane
		implements EditStockPaneModelObserver,ItemModelObserver,WarehouseModelObserver{

	
	private JPanel pane;
	private JTextField stockIdField;
	private JTextField quantityField;
	private JTextField dateField;
	
	private Stock stock;
	private JComboBox<Item> comboItems;
	private JComboBox<Warehouse> comboWarehouses;
	
	
	public EditStockPane(){
		pane=new JPanel();
		pane.setLayout(new MigLayout("debug", "[][grow]", "[][][][][][][]"));
		
		JLabel lblStockId = new JLabel("Stock Id");
		pane.add(lblStockId, "cell 0 0,alignx trailing");
		
		stockIdField = new JTextField();
		stockIdField.setEditable(false);
		pane.add(stockIdField, "cell 1 0,growx");
		stockIdField.setColumns(10);
		
		JLabel lblItemName = new JLabel("Item Name");
		pane.add(lblItemName, "cell 0 1,alignx trailing");
		
		comboItems = new JComboBox<Item>();
		comboItems.setSelectedItem(null);
		pane.add(comboItems, "flowx,cell 1 1,growx");
		
		JButton btnNewItem = new JButton("New Item");
		pane.add(btnNewItem, "cell 1 1");
		
		JLabel lblWarehouseName = new JLabel("Warehouse  Name");
		pane.add(lblWarehouseName, "cell 0 2,alignx trailing");
		
		comboWarehouses = new JComboBox<Warehouse>();
		comboWarehouses.setSelectedItem(null);
		pane.add(comboWarehouses, "flowx,cell 1 2,growx");
		
		JButton btnNewWarehouse = new JButton("New Warehouse");
		pane.add(btnNewWarehouse, "cell 1 2");
		
		JLabel lblQuantity = new JLabel("Quantity");
		pane.add(lblQuantity, "cell 0 3,alignx trailing");
		
		quantityField = new JTextField();
		pane.add(quantityField, "cell 1 3,growx");
		quantityField.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		pane.add(lblDate, "cell 0 4,alignx trailing");
		
		dateField = new JTextField();
		dateField.setEditable(false);
		pane.add(dateField, "cell 1 4,growx");
		dateField.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		pane.add(btnCancel, "flowx,cell 1 6");
		
		JButton btnReset = new JButton("Reset");
		pane.add(btnReset, "cell 1 6");
		
		JButton btnUpdate = new JButton("Update");
		pane.add(btnUpdate, "cell 1 6");
		
	}
	
	@Override
	public JComponent getPane() {
		return pane;
	}
	
	@Override
	public void updateWarehouse(List<Warehouse> warehouses) {
		Warehouse prevSelected=(Warehouse) comboWarehouses.getSelectedItem();
		comboWarehouses.removeAllItems();
		
		for(Warehouse warehouse: warehouses){
			comboWarehouses.addItem(warehouse);
				if(prevSelected!=null)
					if(prevSelected.compareTo(warehouse)==0)
						comboWarehouses.setSelectedItem(warehouse);
		}
		
	}

	@Override
	public void updateItems(List<Item> items) {
		Item prevSelected=(Item) comboItems.getSelectedItem();
		comboItems.removeAllItems();
		
		for(Item item: items){
			comboItems.addItem(item);
				if(prevSelected != null)
					if(prevSelected.compareTo(item) == 0)
						comboItems.setSelectedItem(item);
		}
	}
	
	@Override
	public void updateEntity(Stock stock) {
		this.stock=	stock;
		stockIdField.setText(stock.getStockId().toString());
		comboItems.setSelectedItem(stock.getItem());
		comboWarehouses.setSelectedItem(stock.getWarehouse());
		quantityField.setText(stock.getQuantity().toString());
		dateField.setText(stock.getDate().toString());

	}

	

}
