/*******************************************************************************
 * Copyright 2013 Universidad Politécnica de Madrid
 * Copyright 2013 Fraunhofer-Gesellschaft - Institute for Computer Graphics Research
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.universAAL.tools.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.universAAL.ontology.profile.User;
import org.universAAL.ontology.security.Credentials;
import org.universAAL.ontology.security.SecuritySubprofile;
import org.universAAL.ontology.security.UserPasswordCredentials;
import org.universAAL.tools.ProjectActivator;

/**
 * @author amedrano
 *
 */
public class UserEditDialog {

    JFrame frame;
    private User u;
    private User oldU;
    private UserPasswordCredentials credentials;
    private UserEditingPane editPane;

    /**
     * Launch the application.
     */
    public void show() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		   frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public UserEditDialog(User usr , UserPasswordCredentials cred) {
	u = usr;
	oldU = (User) usr.copy(false);
	credentials = cred;
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 640, 300);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.SOUTH);
	
	JButton btnAdd = new JButton("Edit");
	panel.add(btnAdd);
	btnAdd.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			updateUser();
			frame.setVisible(false);
			frame.dispose();
		    }
		});
	    }
	});
	
	JButton btnCancel = new JButton("Cancel");
	panel.add(btnCancel);
	btnCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			frame.setVisible(false);
			frame.dispose();
		    }
		});
	    }
	});
	
	editPane = new UserEditingPane(u, credentials);
	frame.getContentPane().add(editPane, BorderLayout.CENTER);
	
	
    }
    
    private void updateUser(){
	u = editPane.getUser();
	credentials = editPane.getCred();
//	if (oldU.getURI().equals(u.getURI())){
//	    ProfilingServerHelper psh = new ProfilingServerHelper(ProjectActivator.context);
//	    SecuritySubprofile sp = psh.getSecuritySubprofileForUser(u);
//	    List<Credentials> creds = sp.getCredentials();
//	    UserPasswordCredentials upc = null;
//	    List<Credentials> newCreds = new ArrayList<Credentials>();
//	    for (Credentials c : creds) {
//		if (c instanceof UserPasswordCredentials){
//		    upc = (UserPasswordCredentials) c;
//		}else {
//		    newCreds.add(c);
//		}
//	    }
//	    newCreds.add(credentials);
//	    sp.changeProperty(SecuritySubprofile.PROP_CREDENTIALS, newCreds);
//	    psh.changeSubProfile(sp);
//	}
//	else {
//	    // remove old user add new user.
//	    SecurityProfileEditor.removeUser(oldU);
//	    UserAddDialog.addUser(u, credentials);
//	}
    }
}
