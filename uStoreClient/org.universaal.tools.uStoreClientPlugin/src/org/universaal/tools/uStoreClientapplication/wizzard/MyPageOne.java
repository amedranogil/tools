/*
	Copyright 2011-2014 CERTH-ITI, http://www.iti.gr
	Information Technologies Institute (ITI)
	Centre For Research and Technology Hellas (CERTH)
	
	
	See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	  http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package org.universaal.tools.uStoreClientapplication.wizzard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.universaal.tools.uStoreClientapplication.Activator;

public class MyPageOne extends WizardPage {
	private Text usernameText;
	private Text passwordText;
	private Composite container;
private String username;
private String password;


	public MyPageOne(String username,String password) {
		super("Publish to uStore");
		setTitle("Publish to uStore");
		setDescription("Provide uStore credentials");
		this.username=username;
		this.password=password;
		
	}

	@Override
	public void createControl(Composite parent) {
		 PlatformUI.getWorkbench().getHelpSystem()
		   .setHelp(parent, Activator.PLUGIN_ID + ".help_item");
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		Label label1 = new Label(container, SWT.NULL);
		label1.setText("Username*");

		usernameText = new Text(container, SWT.BORDER | SWT.SINGLE);
		usernameText.setText("");
		usernameText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!usernameText.getText().isEmpty()
						&& !passwordText.getText().isEmpty()) {
					setPageComplete(true);

				} else {
					setPageComplete(false);
				}
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		usernameText.setLayoutData(gd);

		// password field
		Label label2 = new Label(container, SWT.NULL);
		label2.setText("Password*");

		passwordText = new Text(container, SWT.PASSWORD | SWT.BORDER
				| SWT.SINGLE);
		passwordText.setText("");
		passwordText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!usernameText.getText().isEmpty()
						&& !passwordText.getText().isEmpty()) {
					setPageComplete(true);
				} else {
					setPageComplete(false);
				}
			}

		});

		passwordText.setLayoutData(gd);

		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);
		if(username!=null&&!username.equals("")){
		usernameText.setText(username);
		}
		if(password!=null&&!password.equals("")){
		passwordText.setText(password);
		}
		
	}
	
	public boolean canFlipToNextPage(){
		if (!usernameText.getText().isEmpty()
				&& !passwordText.getText().isEmpty()) {
		   return true;
		}else{
			return false;
		}
		   
		}

	public Text getUsernameText() {
		return usernameText;
	}

	public void setUsernameText(Text usernameText) {
		this.usernameText = usernameText;
	}

	public Text getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(Text passwordText) {
		this.passwordText = passwordText;
	}

}