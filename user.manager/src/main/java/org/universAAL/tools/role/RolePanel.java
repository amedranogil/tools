package org.universAAL.tools.role;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import org.universAAL.ontology.profile.User;
import org.universAAL.tools.CHeQuerrier;
import org.universAAL.tools.ProjectActivator;
import org.universAAL.tools.user.UserPanel;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		btnAddRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttomPanel.add(btnAddRole);
		
		JButton btnRemoveRole = new JButton("Remove Role");
		btnRemoveRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				EventQueue.invokeLater(new Runnable() {
//					
//					public void run() {
//						int viewRow = userTable.getSelectedRow();
//		                if (viewRow >= 0) {
//		                    int modelRow = 
//		                    		userTable.convertRowIndexToModel(viewRow);
//		                    User u = (User) tableModel.getUser(modelRow);
//		                    // delete confirmation
//		                    int opt = JOptionPane.showConfirmDialog
//		                    		(UserPanel.this, "Are you sure you want to removed the user: \n" + u.getURI() + " ?" ,
//		                    				"Removing User confirmation", JOptionPane.YES_NO_OPTION);
//		                	if (opt == JOptionPane.YES_OPTION) {
//								CHeQuerrier querier = new CHeQuerrier(
//										ProjectActivator.context);
//								querier.query("DELETE {<" + u.getURI() + ">}");
//								tableModel.updated();
//							}
//		                }
//		            }
//				});
			}
		});
		buttomPanel.add(btnRemoveRole);
		
		JButton btnEditRole = new JButton("Edit Role");
		btnEditRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttomPanel.add(btnEditRole);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		add(scrollPane);

	}

}
