package org.universaal.tools.dashboard.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;
import org.universaal.tools.dashboard.buttonlisteners.CommandCallingListener;
import org.universaal.tools.dashboard.buttonlisteners.TemporaryListener;

public class ApplicationView extends Composite {

	private Button btnCreate;
	private Button btnImportExample;
	private Button btnTransform;
	private Button btnCreateClass;
	//private Button btnImportClass;
	private Button btnBuild;
	private Button btnTestConformance;
	private Button btnRun;
	private Button btnDebug;	
	private Button btnCombine;
	private Button btnPublishToUStore;
	private Button btnPublishOpenSource;
	private Button btnCreateAndEdit;
	private Group grpConfiguration;
	private Button btnNewConfiguration;
	private Button btnOpenConfiguration;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ApplicationView(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(7, false));
		
		Group grpProject = new Group(this, SWT.NONE);
		grpProject.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpProject.setBackground(SWTResourceManager.getColor(135, 206, 250));
		grpProject.setLayout(new FillLayout(SWT.VERTICAL));
		grpProject.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		grpProject.setText("Project");
		
		btnCreate = new Button(grpProject, SWT.NONE);
		btnCreate.setText("Create");
		
		btnImportExample = new Button(grpProject, SWT.NONE);
		btnImportExample.setText("Import Example");
		
		Composite composite = new Composite(this, SWT.NONE);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.center = true;
		composite.setLayout(rl_composite);
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		Label lblAbc = new Label(composite, SWT.NONE);
		lblAbc.setImage(ResourceManager.getPluginImage("org.universaal.tools.dashboard", "icons/next.png"));
		
		btnTransform = new Button(composite, SWT.NONE);
		btnTransform.setEnabled(false);
		btnTransform.setText("Transform");
		
		Group grpJavaClasses = new Group(this, SWT.NONE);
		grpJavaClasses.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpJavaClasses.setBackground(SWTResourceManager.getColor(135, 206, 250));
		grpJavaClasses.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_grpJavaClasses = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpJavaClasses.widthHint = 92;
		grpJavaClasses.setLayoutData(gd_grpJavaClasses);
		grpJavaClasses.setText("Java Classes");
		
		btnCreateClass = new Button(grpJavaClasses, SWT.NONE);
		btnCreateClass.setText("Create");
		
		//btnImportClass = new Button(grpJavaClasses, SWT.NONE);
		//btnImportClass.setEnabled(false);
		//btnImportClass.setText("Import");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		RowLayout rl_composite_1 = new RowLayout(SWT.VERTICAL);
		rl_composite_1.center = true;
		composite_1.setLayout(rl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setImage(ResourceManager.getPluginImage("org.universaal.tools.dashboard", "icons/next.png"));
		
		btnBuild = new Button(composite_1, SWT.NONE);
		btnBuild.setText("Build");
		
		Group grpApplicationBinary = new Group(this, SWT.NONE);
		grpApplicationBinary.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpApplicationBinary.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpApplicationBinary.setBackground(SWTResourceManager.getColor(135, 206, 250));
		grpApplicationBinary.setText("Application Binary");
		grpApplicationBinary.setLayout(new FillLayout(SWT.VERTICAL));
		
		btnTestConformance = new Button(grpApplicationBinary, SWT.NONE);
		btnTestConformance.setEnabled(true);
		btnTestConformance.setText("Test Conformance");
		
		btnRun = new Button(grpApplicationBinary, SWT.NONE);
		btnRun.setText("Run");
		
		btnDebug = new Button(grpApplicationBinary, SWT.NONE);
		btnDebug.setText("Debug");
		
		Composite composite_2 = new Composite(this, SWT.NONE);
		RowLayout rl_composite_2 = new RowLayout(SWT.VERTICAL);
		rl_composite_2.center = true;
		composite_2.setLayout(rl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setImage(ResourceManager.getPluginImage("org.universaal.tools.dashboard", "icons/next.png"));
		
		btnCombine = new Button(composite_2, SWT.NONE);
		btnCombine.setEnabled(true);
		btnCombine.setText("Package");
		
		Group grpPublishableOntology = new Group(this, SWT.BORDER);
		grpPublishableOntology.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpPublishableOntology.setBackground(SWTResourceManager.getColor(135, 206, 250));
		grpPublishableOntology.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_grpPublishableOntology = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		gd_grpPublishableOntology.widthHint = 154;
		grpPublishableOntology.setLayoutData(gd_grpPublishableOntology);
		grpPublishableOntology.setText("Publishable Application");
		
		btnPublishToUStore = new Button(grpPublishableOntology, SWT.NONE);
		btnPublishToUStore.setText("Publish to uStore");
		
		btnPublishOpenSource = new Button(grpPublishableOntology, SWT.NONE);
		btnPublishOpenSource.setText("Publish Open Source");
		
		grpConfiguration = new Group(this, SWT.NONE);
		grpConfiguration.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpConfiguration.setBackground(SWTResourceManager.getColor(135, 206, 250));
		grpConfiguration.setLayout(new FillLayout(SWT.VERTICAL));
		grpConfiguration.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpConfiguration.setText("Configuration");
		
		btnNewConfiguration = new Button(grpConfiguration, SWT.NONE);
		btnNewConfiguration.setText("Create");
		btnNewConfiguration.setEnabled(true);
		
		btnOpenConfiguration = new Button(grpConfiguration, SWT.NONE);
		btnOpenConfiguration.setText("Open");
		btnOpenConfiguration.setEnabled(true);
		
		Group grpApplicationDescription = new Group(this, SWT.NONE);
		grpApplicationDescription.setFont(SWTResourceManager.getFont("Lucida Grande", 11, SWT.BOLD));
		grpApplicationDescription.setBackground(SWTResourceManager.getColor(135, 206, 250));
		GridData gd_grpApplicationDescription = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpApplicationDescription.widthHint = 152;
		grpApplicationDescription.setLayoutData(gd_grpApplicationDescription);
		grpApplicationDescription.setText("Application Description");
		grpApplicationDescription.setLayout(new FillLayout(SWT.VERTICAL));
		
		btnCreateAndEdit = new Button(grpApplicationDescription, SWT.NONE);
		btnCreateAndEdit.setText("Create and Edit");
		

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	DashboardView containingPart;
	
	private void addCommandCallingListener(Button btn, String commandID, String featureName) {
		btn.addSelectionListener(new CommandCallingListener(containingPart, commandID, featureName));		
	}
	
	/**
	 * Use this method to create all the buttonlisteners, and assign them to 
	 * their respective buttons.
	 */
	protected void createActions(DashboardView containingPart) {
		this.containingPart = containingPart;

		// Project
		addCommandCallingListener(btnCreate,"org.universaal.tools.newwizard.plugin.command.startNewWizard", "AAL Studio Project Wizards");
		addCommandCallingListener(btnImportExample,"org.universaal.importexternalproject.commands.importexample", "AAL Studio integration with Developer Depot");

		//Java Classes
		addCommandCallingListener(btnCreateClass,"org.universaal.tools.newwizard.plugin.command.startNewItemWizard", "AAL Studio Project Wizards");
		//btnImportClass.addSelectionListener(new TemporaryListener(this, "Import Class"));

		//Configuration Editor
		addCommandCallingListener(btnNewConfiguration, "org.universaal.tools.configurationEditor.newConfiguration", "AAL Studio New Configuration Wizard");
		addCommandCallingListener(btnOpenConfiguration, "org.universaal.tools.configurationEditor.openConfiguration", "AAL Studio Open Configuration Wizard");

		//Package
		addCommandCallingListener(btnCombine, "org.universaal.tools.packaging.tool.commands.MPAaction", "AAL Studio Application Packager");
		
		
		//Application Binary
		addCommandCallingListener(btnTestConformance, "org.universaal.tools.conformanceTools.commands.ConformanceToolsRun", "AAL Studio Conformance Tools");
		addCommandCallingListener(btnRun,"org.universaal.tools.buildserviceapplication.actions.RunAction", "AAL Studio Build");
		addCommandCallingListener(btnDebug,"org.universaal.tools.buildserviceapplication.actions.DebugAction", "AAL Studio Build");

		//Project Description
		addCommandCallingListener(btnCreateAndEdit,"org.universaal.tools.uploadopensourceplugin.generateaalapp", "AAL Studio integration with Developer Depot");

		//Publishable Application
		addCommandCallingListener(btnPublishOpenSource,"org.universaal.tools.uploadopensourceplugin.commands.uploadopensource", "AAL Studio integration with Developer Depot");
		addCommandCallingListener(btnPublishToUStore,"org.universaal.tools.uStoreClienteapplication.actions.PublishAction", "AAL Studio uStore Client");

		// Transitions
		addCommandCallingListener(btnBuild,"org.universaal.tools.buildserviceapplication.actions.BuildAction", "AAL Studio Build");
		//addCommandCallingListener(btnTransform,"org.universaal.tools.transformationcommand.commands.ontUML2Java", "AAL Studio Transformations");
		
	}	
}
