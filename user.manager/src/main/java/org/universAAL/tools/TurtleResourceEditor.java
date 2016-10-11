package org.universAAL.tools;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TurtleResourceEditor extends JPanel {

	private JTextPane textEdit;
	private JComboBox uriBar;
	private CHeQuerrier querier;
	private JLabel statusLabel;

	public TurtleResourceEditor(){
		this("");
	}
	
	/**
	 * Create the panel.
	 */
	public TurtleResourceEditor(String URI) {
		setLayout(new BorderLayout(0, 0));
		querier = new CHeQuerrier(ProjectActivator.context);
		
		JPanel statuspanel = new JPanel();
		add(statuspanel, BorderLayout.SOUTH);
		statuspanel.setLayout(new BoxLayout(statuspanel, BoxLayout.X_AXIS));
		
		statusLabel = new JLabel("status");
		statuspanel.add(statusLabel);
		
		textEdit = new JTextPane();
		
		JScrollPane scrollPane = new JScrollPane(textEdit);
		scrollPane.setAutoscrolls(true);
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel navpanel = new JPanel();
		add(navpanel, BorderLayout.NORTH);
		navpanel.setLayout(new BoxLayout(navpanel, BoxLayout.X_AXIS));
		
		uriBar = new JComboBox();
		uriBar.setEditable(true);
		navpanel.add(uriBar);
		
		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(
				new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					  public void run() {
					    loadFromURI(null);
					  }
					});
			}
		});
		navpanel.add(btnLoad);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					  public void run() {
					    updateObject();
					  }
					});
			}
		});
		navpanel.add(btnSave);

		uriBar.setSelectedItem(URI);
		loadFromURI(URI);
	}

	private void loadFromURI(String uri){
		try {
			if (uri == null){
				uri = (String) uriBar.getSelectedItem();
			}
			
			String serialization = querier.unserialisedQuery("DESCRIBE <" + uri + ">");
			textEdit.setText(serialization);
			statusLabel.setText("Loaded " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		} catch (Exception e) {
			statusLabel.setText("Unable to load " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
	}
	
	private void updateObject() {
		
		try {
			String serialization = textEdit.getText();
			String uri = (String) uriBar.getSelectedItem();
			String[] split = CHeQuerrier.splitPrefixes(serialization);
			
			String prefixes = split[0];
			String serialValue = split[1];
			querier.query(CHeQuerrier.getQuery(CHeQuerrier.getResource("updateFullObject.sparql"), new String[]{prefixes,uri, serialValue}));
			statusLabel.setText("Uploaded to DB " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		} catch (Exception e) {
			statusLabel.setText("Unable to Upload " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		
		
	}
}
