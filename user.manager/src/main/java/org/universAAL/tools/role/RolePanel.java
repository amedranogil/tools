package org.universAAL.tools.role;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.BorderLayout;

public class RolePanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RolePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel filterPanel = new JPanel();
		add(filterPanel, BorderLayout.NORTH);
		
		JLabel label_1 = new JLabel("Search:");
		filterPanel.add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		filterPanel.add(textField);
		
		JPanel buttomPanel = new JPanel();
		add(buttomPanel, BorderLayout.SOUTH);
		
		JButton btnAddRole = new JButton("Add Role");
		buttomPanel.add(btnAddRole);
		
		JButton btnRemoveRole = new JButton("Remove Role");
		buttomPanel.add(btnRemoveRole);
		
		JButton btnEditRole = new JButton("Edit Role");
		buttomPanel.add(btnEditRole);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		add(scrollPane);

	}

}
