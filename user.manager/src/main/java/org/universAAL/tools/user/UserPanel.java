package org.universAAL.tools.user;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.universAAL.tools.ProjectActivator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;

public class UserPanel extends JPanel {
	private JTable userTable;
	private JTextField searchField;

	/**
	 * Create the panel.
	 */
	public UserPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel filterPanel = new JPanel();
		filterPanel.setPreferredSize(new Dimension(30, 30));
		filterPanel.setMinimumSize(new Dimension(50, 50));
		add(filterPanel, BorderLayout.NORTH);
		
		JLabel lblFilter = new JLabel("Filter:");
		
		JComboBox userTypeSelector = new JComboBox();
		
		JLabel lblSearch = new JLabel("Search:");
		
		searchField = new JTextField();
		searchField.setColumns(10);
		GroupLayout gl_filterPanel = new GroupLayout(filterPanel);
		gl_filterPanel.setHorizontalGroup(
			gl_filterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFilter)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(userTypeSelector, 0, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSearch)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(searchField, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_filterPanel.setVerticalGroup(
			gl_filterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_filterPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_filterPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilter)
						.addComponent(userTypeSelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearch)))
		);
		filterPanel.setLayout(gl_filterPanel);
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton addButton = new JButton("Add User");
		buttonPanel.add(addButton);
		
		JButton rmButton = new JButton("Remove User");
		buttonPanel.add(rmButton);
		
		JButton editButton = new JButton("Edit User");
		buttonPanel.add(editButton);
		
		userTable = new JTable(new UserListTableModel(ProjectActivator.context));
		
		JScrollPane scrollPane = new JScrollPane(userTable);
		add(scrollPane, BorderLayout.CENTER);
		

	}

}
