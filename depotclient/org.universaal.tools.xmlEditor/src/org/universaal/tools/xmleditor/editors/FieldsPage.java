/*
	Copyright 2011 SINTEF, http://www.sintef.no
	
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
package org.universaal.tools.xmleditor.editors;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.universaal.tools.xmleditor.model.ProjectModel;

/**
 * Fields-page of the XML-editor. Contains textfields that the user can enter
 * information into, and it will update the XML automatically.
 * @author Adrian
 *
 */
public class FieldsPage extends Composite {

	private XmlEditor parent;
	private Text projectName, developerName, date, url, svnUrl, tags;
	private StyledText description;
	private ProjectModel model;
	private Text license;
	private Text licenseUrl;
	private Button containsSubProjects;
	
	private boolean subProjects;

	
	/**
	 * @wbp.parser.constructor
	 */
	public FieldsPage(Composite container){
		super(container, SWT.NONE);
		subProjects = false;
	}
	
	/**
	 * Creates the layout of the page
	 * @param parent - The XMLEditor-object that created this page.
	 * @param model - The ProjectObject that is the model.
	 * @param comp - Container that contains the SWT items.
	 */
	public FieldsPage(XmlEditor parent, ProjectModel model, Composite container){
		super(container, SWT.NONE);
		subProjects = false;
		this.parent = parent;
		this.model = model;

		FieldListener listen = new FieldListener();

		setLayout(new GridLayout(2, false));

		Label lblProjectName = new Label(this, SWT.NONE);
		lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProjectName.setText("Project Name");

		projectName = new Text(this, SWT.BORDER);
		projectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblDeveloper = new Label(this, SWT.NONE);
		lblDeveloper.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDeveloper.setText("Developer");

		developerName = new Text(this, SWT.BORDER);
		developerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


		Label lblDate = new Label(this, SWT.NONE);
		lblDate.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDate.setText("Date");

		date = new Text(this, SWT.BORDER);
		date.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


		Label lblUrl = new Label(this, SWT.NONE);
		lblUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUrl.setText("URL");

		url = new Text(this, SWT.BORDER);
		url.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));


		Label lblSvnUrl = new Label(this, SWT.NONE);
		lblSvnUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSvnUrl.setText("SVN URL");

		svnUrl = new Text(this, SWT.BORDER);
		svnUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLicense = new Label(this, SWT.NONE);
		lblLicense.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLicense.setText("License");
		
		license = new Text(this, SWT.BORDER);
		license.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblLicenseUrl = new Label(this, SWT.NONE);
		lblLicenseUrl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLicenseUrl.setText("License URL");
		
		licenseUrl = new Text(this, SWT.BORDER);
		licenseUrl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblTags = new Label(this, SWT.NONE);
		lblTags.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTags.setText("Tags");
		lblTags.setToolTipText("Enter project tags, separated by commas. E.g. \"tag, tag2\"");

		tags = new Text(this, SWT.BORDER);
		tags.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblSubProjects = new Label(this, SWT.NONE);
		lblSubProjects.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSubProjects.setText("Sub-projects");
		
		
		containsSubProjects = new Button(this, SWT.CHECK);
		containsSubProjects.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		containsSubProjects.setText("Project contains subprojects.");


		Label lblDescription = new Label(this, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescription.setText("Description");

		description = new StyledText(this, SWT.BORDER | SWT.WRAP);
		description.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));


		setFields();

		
		description.addModifyListener(listen);
		containsSubProjects.addSelectionListener(new CheckBoxListener());
		licenseUrl.addModifyListener(listen);
		license.addModifyListener(listen);
		svnUrl.addModifyListener(listen);
		url.addModifyListener(listen);
		date.addModifyListener(listen);
		developerName.addModifyListener(listen);
		projectName.addModifyListener(listen);
		tags.addModifyListener(listen);
	}
	
	/**
	 * Sets the value of the fields to the values in the ProjectObject-model
	 */
	public void setFields(){
		projectName.setText(model.getName());
		developerName.setText(model.getDev());
		date.setText(model.getDate());
		url.setText(model.getUrl());
		svnUrl.setText(model.getSvnUrl());
		license.setText(model.getLicense());
		licenseUrl.setText(model.getLicenseUrl());
		containsSubProjects.setSelection(model.getContainsSubProjects());
		description.setText(model.getDesc());
		tags.setText(getTagsStringFromModel());
	}
	
	/**
	 * Listens to the fields, and updates the model when changes are made.
	 * @author Adrian
	 *
	 */
	class FieldListener implements ModifyListener{

		@Override
		public void modifyText(ModifyEvent e) {
			parent.fieldsModified();
			
			if(e.getSource()==projectName){
				model.setName(projectName.getText());
			}else if(e.getSource() == developerName){
				model.setDev(developerName.getText());
			}else if(e.getSource()==date){
				model.setDate(date.getText());
			}else if(e.getSource()==url){
				model.setUrl(url.getText());
			}else if(e.getSource()==svnUrl){
				model.setSvnUrl(svnUrl.getText());
			}else if(e.getSource()==description){
				model.setDesc(description.getText());
			}else if(e.getSource()==tags){
				model.setTags(getTagsFromFields());
			}else if(e.getSource()==license){
				model.setLicense(license.getText());
			}else if(e.getSource()==licenseUrl){
				model.setLicenseUrl(licenseUrl.getText());
			}
		}
	}
	
	/**
	 * Parses the string in the tags-field, and returns them as separate Strings 
	 * in an ArrayList.
	 * @return ArrayList containing the tags in the Tags-field.
	 */
	private ArrayList<String> getTagsFromFields(){
		ArrayList<String> result = new ArrayList<String>();
		String string = tags.getText();
		String tempString="";
		int prevComma=0;
		int nextComma=string.indexOf(',');
		while(nextComma>=0){
			tempString=string.substring(prevComma, nextComma);
			prevComma=nextComma+1;
			nextComma = string.indexOf(',', prevComma);
			result.add(tempString.trim());
		}
		result.add((string.substring(prevComma)).trim());
		return result;
	}
	
	/**
	 * Gets the ArrayList of tags from the model, and creates a String where 
	 * the tags are separated by commas.
	 * @return String containing all the tags, separated by commas.
	 */
	private String getTagsStringFromModel(){
		String result="";
		ArrayList<String> tags = model.getTags();
		for(int i=0; i<tags.size();i++){
			result += tags.get(i);
			if(i!=tags.size()-1){
				result += ", ";
			}
		}
		return result;
	}
	
	/**
	 * Listens to the checkbox, and updates the model if it is changed.
	 * @author Adrian
	 *
	 */
	private class CheckBoxListener implements SelectionListener{

		@Override
		public void widgetSelected(SelectionEvent e) {
			subProjects = !subProjects;
			model.setContainsSubProjects(subProjects);			
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			
		}
		
	}


}