package org.universAAL.tools;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.universAAL.tools.role.RolePanel;
import org.universAAL.tools.user.UserPanel;

public class MainToolFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainToolFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Users", new UserPanel());
		tabbedPane.addTab("Roles", new RolePanel());
	}

}
