package org.universAAL.tools.user;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.RowFilter;

import org.universAAL.ontology.profile.User;
import org.universAAL.tools.CHeQuerrier;
import org.universAAL.tools.MainToolFrame;
import org.universAAL.tools.ProjectActivator;
import org.universAAL.tools.subprofiles.UserEditor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class UserPanel extends JPanel implements WindowListener{
	private JTable userTable;
	private JTextField searchField;
	private TableRowSorter<UserListTableModel> tableSorter;
	private UserListTableModel tableModel;

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
		
		UserTypesComboItem ci = new UserTypesComboItem();
		JComboBox userTypeSelector = new JComboBox(ci.getAvailableUserTypes());
		userTypeSelector.setSelectedItem(ci.get(User.MY_URI));
		
		JLabel lblSearch = new JLabel("Search:");
		
		searchField = new JTextField();
		searchField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						newFilter();
					}
				});
			}
		});
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
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAddDialog ua = new UserAddDialog();
				ua.addWindowListener(UserPanel.this);
				ua.show();
             	tableModel.updated();
			}
		});
		buttonPanel.add(addButton);
		
		JButton rmButton = new JButton("Remove User");
		rmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						int viewRow = userTable.getSelectedRow();
		                if (viewRow >= 0) {
		                    int modelRow = 
		                    		userTable.convertRowIndexToModel(viewRow);
		                    User u = (User) tableModel.getUser(modelRow);
		                    // delete confirmation
		                    int opt = JOptionPane.showConfirmDialog
		                    		(UserPanel.this, "Are you sure you want to removed the user: \n" + u.getURI() + " ?" ,
		                    				"Removing User confirmation", JOptionPane.YES_NO_OPTION);
		                	if (opt == JOptionPane.YES_OPTION) {
								CHeQuerrier querier = new CHeQuerrier(
										ProjectActivator.context);
								querier.query("DELETE { <" + u.getURI() + "> ?p ?o} WHERE {<" + u.getURI() + "> ?p ?o .}");
								tableModel.updated();
							}
		                }
		            }
				});
			}
		});
		buttonPanel.add(rmButton);
		
		JButton editButton = new JButton("Edit User");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open UserEditor
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						int viewRow = userTable.getSelectedRow();
		                if (viewRow >= 0) {
		                    int modelRow = 
		                    		userTable.convertRowIndexToModel(viewRow);
		                    User u = (User) tableModel.getUser(modelRow);
		                    	JFrame f =	new UserEditor(u);
		                    	f.pack();
		                    	f.setVisible(true);
		                }
		            }
				});
			}
		});
		buttonPanel.add(editButton);
		
		tableModel = new UserListTableModel(ProjectActivator.context);
		userTable = new JTable(tableModel);
		tableSorter = new TableRowSorter<UserListTableModel>(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(userTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);
		

	}

	private void newFilter() {
	    RowFilter<UserListTableModel, Object> rf = null;
	    //If current expression doesn't parse, don't update.
	    try {
	        rf = RowFilter.regexFilter(searchField.getText(), 1);
	        //TODO add another filter by user Type.
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	    tableSorter.setRowFilter(rf);
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		tableModel.updated();
	}

	public void windowClosed(WindowEvent e) {
		tableModel.updated();
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
