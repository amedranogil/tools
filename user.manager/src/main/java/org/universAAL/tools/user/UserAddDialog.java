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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.universAAL.ioc.dependencies.impl.PassiveDependencyProxy;
import org.universAAL.middleware.serialization.MessageContentSerializer;
import org.universAAL.middleware.util.Constants;
import org.universAAL.ontology.profile.AssistedPerson;
import org.universAAL.ontology.profile.User;
import org.universAAL.ontology.profile.UserProfile;
import org.universAAL.ontology.security.SecuritySubprofile;
import org.universAAL.ontology.security.UserPasswordCredentials;
import org.universAAL.tools.CHeQuerrier;
import org.universAAL.tools.ProjectActivator;

/**
 * @author amedrano
 *
 */
public class UserAddDialog {

    JFrame frame;
    private UserEditingPane editPanel;
    private static int count = 0;

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
    public UserAddDialog() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 641, 300);
	
	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.SOUTH);
	
	JButton btnAdd = new JButton("Add");
	panel.add(btnAdd);
	btnAdd.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			addUser(editPanel.getUser(),editPanel.getCred());
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
	
	User u = new AssistedPerson(Constants.uAAL_MIDDLEWARE_LOCAL_ID_PREFIX + "user" + Integer.toString(count++));
	UserPasswordCredentials credentials = new UserPasswordCredentials();
	
	editPanel = new UserEditingPane(u, credentials);
	frame.getContentPane().add(editPanel, BorderLayout.CENTER);
	
	
    }
    
    static void addUser(User u, UserPasswordCredentials credendtials){
    	UserProfile up = new UserProfile(u.getURI()+"addedBySecurityTester");
    	u.setProfile(up);
    	SecuritySubprofile sp = new SecuritySubprofile(u.getURI()+"SecuritySubprofile");
    	up.setSubProfile(sp);
    	UserPasswordCredentials cred = new UserPasswordCredentials(u.getURI()+"UserPasswordCredentials");
    	cred.setUsername(credendtials.getUsername());
    	cred.setpassword(credendtials.getPassword());
    	cred.setDigestAlgorithm(credendtials.getDigestAlgorithm());
    	sp.changeProperty(SecuritySubprofile.PROP_CREDENTIALS, cred);

    	String serialization = new PassiveDependencyProxy<MessageContentSerializer>
    	(ProjectActivator.context, new Object[] { MessageContentSerializer.class.getName() })
    	.getObject().serialize(u);
    	String[] split = CHeQuerrier.splitPrefixes(serialization);

    	String prefixes = split[0];
    	String serialValue = split[1];
    	CHeQuerrier querier = new CHeQuerrier(ProjectActivator.context);
    	querier.query(CHeQuerrier.getQuery(CHeQuerrier.getResource("insertFullObject.sparql"), new String[]{prefixes,serialValue}));
    }
}
