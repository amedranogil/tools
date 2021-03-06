/*
	Copyright 2011 FZI, http://www.fzi.de

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
package org.universaal.tools.configurationEditor.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AttributeDialog extends TitleAreaDialog  {

	private  Text attributeText;
	private String attribute;
	
	public AttributeDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	public void create() {
		
		super.create();

		// Set the title
		setTitle("Add attribute");
		
		
		// Set the message
		setMessage("Add an attribute to the validator", IMessageProvider.NONE);

	}

	
	 protected Control createDialogArea(Composite parent) {
		 
	 	Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
	 
	    GridLayout layout = new GridLayout(2, false);
	    container.setLayout(layout);

	   //  The text fields will grow with the size of the dialog
	    GridData gridData = new GridData();
	    gridData.grabExcessHorizontalSpace = true;
	    gridData.horizontalAlignment = GridData.FILL;
	   // gridData.horizontalSpan = 2;

	    Label label1 = new Label(container, SWT.NONE);
	    label1.setText("Attribute:");

	    attributeText = new Text(container, SWT.BORDER);
	    attributeText.setLayoutData(gridData);
	    
	    return area;
	 }
	 
	 
	  @Override
	  protected void createButtonsForButtonBar(Composite parent) {

	    // Create Add button
	    // Own method as we need to overview the SelectionAdapter
	    createOkButton(parent, OK, "Add", true);
	    // Add a SelectionListener

	    // Create Cancel button
	    Button cancelButton = 
	        createButton(parent, CANCEL, "Cancel", false);
	    // Add a SelectionListener
	    cancelButton.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        setReturnCode(CANCEL);
	        close();
	      }
	    });
	  }
	  
	  
	  protected Button createOkButton(Composite parent, int id, 
		      String label,
		      boolean defaultButton) {
		    // increment the number of columns in the button bar
		    ((GridLayout) parent.getLayout()).numColumns++;
		    Button button = new Button(parent, SWT.PUSH);
		    button.setText(label);
		    button.setFont(JFaceResources.getDialogFont());
		    button.setData(new Integer(id));
		    button.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		        //if (isValidInput()) {
		          okPressed();
		       // }
		      }
		    });
		    if (defaultButton) {
		      Shell shell = parent.getShell();
		      if (shell != null) {
		        shell.setDefaultButton(button);
		      }
		    }
		    setButtonLayoutData(button);
		    return button;
		  }
	  
		@Override
		protected void okPressed() {
			saveInput();
			super.okPressed();
		}

		private void saveInput() {
			this.attribute = attributeText.getText();
		}
		
		public String getAttribute(){
			return this.attribute;
		}
}
