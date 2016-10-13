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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Set;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import org.universAAL.middleware.owl.ManagedIndividual;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.middleware.xsd.Base64Binary;
import org.universAAL.ontology.cryptographic.Digest;
import org.universAAL.ontology.cryptographic.digest.SecureHashAlgorithm;
import org.universAAL.ontology.profile.User;
import org.universAAL.ontology.security.UserPasswordCredentials;

/**
 * @author amedrano
 *
 */
public class UserEditingPane extends JPanel {
	private JTextField userName;
	private JTextField uri;
	private JPasswordField password;
	private JComboBox uTypes;
	private JComboBox digests;
	private User usr;
	private UserPasswordCredentials cred;

    /**
     * Create the panel.
     */
    public UserEditingPane(final User u, final UserPasswordCredentials credendtials ) {
	
	this.usr = u;
	if (credendtials != null)
	    this.cred = credendtials;
	else
	    this.cred = new UserPasswordCredentials();
    	
    	JLabel lblUserType = new JLabel("User Type:");
    	
    	JLabel lblUserUri = new JLabel("User URI:");
    	
    	JLabel lblUsername = new JLabel("Username:");
    	
    	JLabel lblPassword = new JLabel("Password:");
    	
    	JLabel lblDigesAlg = new JLabel("Diges Alg.: ");
    	
    	userName = new JTextField();
    	userName.setText(cred.getUsername());
    	userName.addFocusListener(new FocusListener() {
	    public void focusLost(FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
	    		    cred.setUsername(userName.getText());
		    }
		});
	    }
	    public void focusGained(FocusEvent e) {
	    }
	});

    	
    	uri = new JTextField();
    	uri.setText(usr.getURI());
    	uri.addFocusListener(new FocusListener() {
	    public void focusLost(FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
	    		    usr = (User) Resource.getResource(usr.getClassURI(), uri.getText());
		    }
		});
	    }
	    public void focusGained(FocusEvent e) {
	    }
	});

    	UserTypesComboItem ci = new UserTypesComboItem();
    	uTypes = new JComboBox(ci.getAvailableUserTypes());
    	uTypes.setSelectedItem(ci.get(usr.getClassURI()));
    	uTypes.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//(User) Resource.getResource(((UserTypesComboItem.ComboItem) uTypes.getSelectedItem()).getUri(), usr.getURI());
    		    usr = 
    		    (User) ManagedIndividual.toManagedIndividual(((UserTypesComboItem.ComboItem) uTypes.getSelectedItem()).getUri(), usr);
    		}
    	});
    	
    	
    	password = new JPasswordField();
    	Base64Binary pwd = cred.getPassword();
    	password.setText(pwd==null?"":pwd.toString());
    	password.addFocusListener(new FocusListener() {
	    public void focusLost(FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			updatePassword();
		    }
		});
	    }
	    public void focusGained(FocusEvent e) {
	    }
	});
    	
    	digests = new JComboBox(getAvailableDigests());
    	if (cred.getDigestAlgorithm() != null) {
			digests.setSelectedItem(cred.getDigestAlgorithm().getClassURI());
		}
		digests.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	Digest sel = (Digest) digests.getSelectedItem();
	    	cred.setDigestAlgorithm(sel);
		if (password.getPassword() != null
			&& cred.getPassword() != null
			&& password.getPassword().equals(cred.getPassword().getVal())){
		    password.setText("");
		}
		updatePassword();
	    }
	});
    	
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addContainerGap()
    				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(lblUsername)
    							.addComponent(lblUserType)
    							.addComponent(lblUserUri))
    						.addGap(17)
    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    							.addComponent(uri, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
    							.addComponent(userName, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
    							.addComponent(uTypes, 0, 332, Short.MAX_VALUE)))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(lblDigesAlg)
    						.addPreferredGap(ComponentPlacement.RELATED)
    						.addComponent(digests, 0, 334, Short.MAX_VALUE))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addComponent(lblPassword)
    						.addPreferredGap(ComponentPlacement.UNRELATED)
    						.addComponent(password, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)))
    				.addContainerGap())
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addContainerGap()
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addComponent(lblUserType)
    					.addComponent(uTypes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblUserUri)
    					.addComponent(uri, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    					.addComponent(lblUsername)
    					.addComponent(userName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblDigesAlg)
    					.addComponent(digests, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblPassword)
    					.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addContainerGap(141, Short.MAX_VALUE))
    	);
    	setLayout(groupLayout);
    }
    
    private void updatePassword() {
	try {
	    MessageDigest dig = getMD(cred.getDigestAlgorithm());
	    byte[] byteArray  = new String(password.getPassword()).getBytes("UTF-8");  
	    cred.setpassword(new Base64Binary(dig.digest(byteArray)));
	} catch (Exception e1) {
	    try {
		cred.setpassword(new Base64Binary( new String (password.getPassword()).getBytes("UTF-8")));
	    } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	    }
	}         
    }
    
	static private MessageDigest getMD(Digest digestAlgorithm)
			throws NoSuchAlgorithmException {
		if (digestAlgorithm.equals(org.universAAL.ontology.cryptographic.digest.MessageDigest.IND_MD2)) {
			return MessageDigest.getInstance("MD2");
		}
		if (digestAlgorithm.equals(org.universAAL.ontology.cryptographic.digest.MessageDigest.IND_MD5)) {
			return MessageDigest.getInstance("MD5");
		}
		if (digestAlgorithm.equals(SecureHashAlgorithm.IND_SHA)) {
			return MessageDigest.getInstance("SHA");
		}
		if (digestAlgorithm.equals(SecureHashAlgorithm.IND_SHA256)) {
			return MessageDigest.getInstance("SHA-256");
		}
		if (digestAlgorithm.equals(SecureHashAlgorithm.IND_SHA384)) {
			return MessageDigest.getInstance("SHA-384");
		}
		if (digestAlgorithm.equals(SecureHashAlgorithm.IND_SHA512)) {
			return MessageDigest.getInstance("SHA-512");
		}
		throw new NoSuchAlgorithmException();
	}
    
    private static Vector<Digest> getAvailableDigests(){
    	Vector<Digest> messageDigests = new Vector<Digest>();
    	OntologyManagement.getInstance().getOntClassInfo(Digest.MY_URI).getInstances();
    	Set<String> clases = OntologyManagement.getInstance().getNamedSubClasses(Digest.MY_URI, true, false);
    	for (String classURI : clases) {

        	Resource[] inst = OntologyManagement.getInstance().getOntClassInfo(classURI).getInstances();
        	for (int i = 0; i < inst.length; i++) {
				Resource in = inst[i];
				messageDigests.add((Digest) in);
			}
		}
    	
    	return messageDigests;
    }
    
    public User getUser(){
	return usr;
    }

    public UserPasswordCredentials getCred(){
	return cred;
    }
}
