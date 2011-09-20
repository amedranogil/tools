package org.universaal.tools.buildserviceapplication.actions;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressConstants;
import org.apache.maven.Maven;
import org.apache.maven.cli.MavenCli;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequestPopulator;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.settings.building.DefaultSettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuilder;
import org.apache.maven.settings.building.SettingsBuildingRequest;

public class RunFelix {
	public static MavenExecutionRequestPopulator populator;
	public static DefaultPlexusContainer container;
	public static Maven maven;
	static public SettingsBuilder settingsBuilder;
	private Shell activeShell = null;
	private String executionError = "";

	public void runFelix(final boolean isDebugMode) {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				activeShell = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell();
			}
		});
		if (!BuildAction.getSelectedProjectPath().equals("")) {
			if (BuildAction.buildedProjects.contains(BuildAction
					.getSelectedProjectPath())) {
				final String projectName = BuildAction.getSelectedProjectName();
				Job job = new Job("AAL Studio") {
					protected IStatus run(IProgressMonitor monitor) {
						if (!isDebugMode) {
							monitor.beginTask("Running application \""
									+ projectName + "\"...", 50);
						} else {
							monitor.beginTask("Debuging application \""
									+ projectName + "\"...", 50);
						}
						setProperty(IProgressConstants.KEEP_PROPERTY,
								Boolean.FALSE);

						try {
							URL url = Platform.getBundle(
									"org.universaal.tools.buildPlugin")
									.getEntry("icons/run.png");
							setProperty(IProgressConstants.ICON_PROPERTY,
									ImageDescriptor.createFromURL(url));
							// create configurations for launching on Eclipse
							// 3.6
							CreateFelixPropertiesFile fel = new CreateFelixPropertiesFile();
							fel.createFile();
							monitor.worked(10);
							if (checkIfFelixJarsExistInLocalRepo()) {
								monitor.worked(20);
								ILaunchConfiguration config = createJavaApplicationLaunchConfiguration();

								// refresh the eclipse workspace
								ResourcesPlugin
										.getWorkspace()
										.getRoot()
										.refreshLocal(IResource.DEPTH_INFINITE,
												null);
								if (!isDebugMode) {
									config.launch(ILaunchManager.RUN_MODE, null);
								} else {
									config.launch(ILaunchManager.DEBUG_MODE,
											null);
								}
								monitor.worked(50);
								return Status.OK_STATUS;
							} else {
								return Status.CANCEL_STATUS;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
							return Status.CANCEL_STATUS;
						}
					}
				};

				job.setUser(true);
				job.schedule();
				job.addJobChangeListener(new JobChangeAdapter() {
					public void done(final IJobChangeEvent event) {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (event.getResult().isOK())
									MessageDialog
											.openInformation(
													activeShell,
													"BuildServiceApplication",
													"Felix execution environment has started. Please wait until all bundles are loaded...");
								else
									MessageDialog.openInformation(activeShell,
											"BuildServiceApplication",
											"The following error occured while running the application: \n\n"
													+ executionError);
							}
						});

					}
				});
			} else {
				MessageDialog.openInformation(null, "BuildServiceApplication",
						"Please build the project first.");
			}
		} else {
			MessageDialog.openInformation(null, "BuildServiceApplication",
					"Please select a project in the Project Explorer tab.");
		}
	}

	private ILaunchConfiguration createJavaApplicationLaunchConfiguration() {
		try {
			ILaunchManager manager = DebugPlugin.getDefault()
					.getLaunchManager();
			ILaunchConfigurationType type = manager
					.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
			ILaunchConfigurationWorkingCopy wc = type.newInstance(null,
					CreateFelixPropertiesFile.artifactId);
			wc.setAttribute(
					"org.eclipse.debug.core.MAPPED_RESOURCE_PATHS",
					new ArrayList(Arrays.asList(new String[] { "/"
							+ CreateFelixPropertiesFile.artifactId })));
			wc.setAttribute("org.eclipse.debug.core.MAPPED_RESOURCE_TYPES",
					new ArrayList(Arrays.asList(new String[] { "4" })));

			wc.setAttribute("org.eclipse.jdt.debug.ui.INCLUDE_EXTERNAL_JARS",
					true);

			wc.setAttribute(
					IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME,
					CreateFelixPropertiesFile.artifactId);
			wc.setAttribute(
					IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME,
					"org.apache.felix.main.Main");
			wc.setAttribute(
					IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS,
					"-Djava.protocol.handler.pkgs=org.ops4j.pax.url"
							+ " -Dfelix.config.properties=file:"
							+ Platform.getLocation().toString()
							+ "/.felix/conf/"
							+ CreateFelixPropertiesFile.artifactId
							+ "/config.properties"
							+ " -Dbundles.configuration.location=${workspace_loc}/rundir/confadmin");

			IPath toolsPath = new Path(
					MavenCli.userMavenConfigurationHome.getAbsolutePath()
							+ File.separator + "repository" + File.separator
							+ "org" + File.separator + "apache"
							+ File.separator + "felix" + File.separator
							+ "org.apache.felix.main" + File.separator
							+ "3.2.2" + File.separator
							+ "org.apache.felix.main-3.2.2.jar");
			IRuntimeClasspathEntry toolsEntry = JavaRuntime
					.newArchiveRuntimeClasspathEntry(toolsPath);

			IPath toolsPath2 = new Path(
					MavenCli.userMavenConfigurationHome.getAbsolutePath()
							+ File.separator + "repository" + File.separator
							+ "org" + File.separator + "ops4j" + File.separator
							+ "pax" + File.separator + "url" + File.separator
							+ "pax-url-mvn" + File.separator + "1.3.3"
							+ File.separator + "pax-url-mvn-1.3.3.jar");
			IRuntimeClasspathEntry toolsEntry2 = JavaRuntime
					.newArchiveRuntimeClasspathEntry(toolsPath2);
			toolsEntry2
					.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);

			IPath toolsPath3 = new Path(
					MavenCli.userMavenConfigurationHome.getAbsolutePath()
							+ File.separator + "repository" + File.separator
							+ "org" + File.separator + "ops4j" + File.separator
							+ "pax" + File.separator + "url" + File.separator
							+ "pax-url-wrap" + File.separator + "1.3.3"
							+ File.separator + "pax-url-wrap-1.3.3.jar");

			IRuntimeClasspathEntry toolsEntry3 = JavaRuntime
					.newArchiveRuntimeClasspathEntry(toolsPath3);
			toolsEntry3
					.setClasspathProperty(IRuntimeClasspathEntry.USER_CLASSES);

			IPath systemLibsPath = new Path(JavaRuntime.JRE_CONTAINER);
			IRuntimeClasspathEntry systemLibsEntry = JavaRuntime
					.newRuntimeContainerClasspathEntry(systemLibsPath,
							IRuntimeClasspathEntry.STANDARD_CLASSES);
			ArrayList classpath = new ArrayList();
			classpath.add(toolsEntry.getMemento());
			classpath.add(toolsEntry2.getMemento());
			classpath.add(toolsEntry3.getMemento());

			classpath.add(systemLibsEntry.getMemento());
			// wc.setAttribute("org.eclipse.jdt.launching.ATTR_CLASSPATH",
			// classpath);
			wc.setAttribute("org.eclipse.jdt.launching.CLASSPATH", classpath);

			IPath toolsPath5 = new Path(
					MavenCli.userMavenConfigurationHome.getAbsolutePath()
							+ File.separator + "repository" + File.separator
							+ "org" + File.separator + "apache"
							+ File.separator + "felix" + File.separator
							+ "org.apache.felix.main" + File.separator
							+ "3.2.2" + File.separator
							+ "org.apache.felix.main-3.2.2.jar");
			IRuntimeClasspathEntry toolsEntry5 = JavaRuntime
					.newArchiveRuntimeClasspathEntry(toolsPath5);

			ArrayList classpath2 = new ArrayList();
			classpath2.add(toolsEntry5.getMemento());
			classpath2.add(systemLibsEntry.getMemento());
			wc.setAttribute("org.eclipse.jdt.launching.ATTR_CLASSPATH",
					classpath2);

			wc.setAttribute("org.eclipse.jdt.launching.ATTR_DEFAULT_CLASSPATH",
					false);
			wc.setAttribute("org.eclipse.jdt.launching.DEFAULT_CLASSPATH",
					false);

			ILaunchConfiguration config = wc.doSave();
			return config;
		} catch (Exception ex) {
			ex.printStackTrace();
			executionError = executionError + "\n" + ex.getMessage();
			return null;
		}
	}

	protected MavenExecutionResult downloadArtifact(String artifact)
			throws Exception {
		File basedir = new File(BuildAction.getSelectedProjectPath());
		setUpMavenBuild();
		MavenExecutionRequest request = createExecutionRequest();
		request.setPom(new File(basedir, "pom.xml"));
		request.setGoals(Arrays
				.asList("org.apache.maven.plugins:maven-dependency-plugin:2.1:get"));
		java.util.Properties prop = new java.util.Properties();
		prop.setProperty("artifact", artifact);
		prop.setProperty("repoUrl", "http://download.java.net/maven/2/");
		request.setUserProperties(prop);
		BuildAction.populator.populateDefaults(request);
		MavenExecutionResult result = BuildAction.maven.execute(request);
		return result;
	}

	/**
	 * Sets up Maven embedder for execution.
	 */
	protected void setUpMavenBuild() {
		try {
			container = new DefaultPlexusContainer();
			maven = container.lookup(Maven.class);
			populator = container.lookup(MavenExecutionRequestPopulator.class);
			settingsBuilder = container.lookup(SettingsBuilder.class);
		} catch (Exception ex) {
			// ex.printStackTrace();
			maven = BuildAction.getMaven();
			populator = BuildAction.getMavenPopulator();
			settingsBuilder = BuildAction.getSettingsBuilder();
		}
	}

	/**
	 * Maven Excecution request.
	 */
	public MavenExecutionRequest createExecutionRequest() throws Exception {
		SettingsBuildingRequest settingsRequest = new DefaultSettingsBuildingRequest();
		settingsRequest
				.setUserSettingsFile(MavenCli.DEFAULT_USER_SETTINGS_FILE);
		settingsRequest
				.setGlobalSettingsFile(MavenCli.DEFAULT_GLOBAL_SETTINGS_FILE);
		MavenExecutionRequest request = new DefaultMavenExecutionRequest();
		request.setUserSettingsFile(settingsRequest.getUserSettingsFile());
		request.setGlobalSettingsFile(settingsRequest.getGlobalSettingsFile());
		request.setSystemProperties(System.getProperties());
		populator.populateFromSettings(request,
				settingsBuilder.build(settingsRequest).getEffectiveSettings());
		return request;
	}

	/**
	 * Runs maven -clean/install command which builds the project and installs
	 * artifact to the local repository
	 */
	protected MavenExecutionResult install(String path) throws Exception {
		File basedir = new File(BuildAction.getSelectedProjectPath());
		setUpMavenBuild();
		MavenExecutionRequest request = createExecutionRequest();
		request.setPom(new File(basedir, "pom.xml"));
		request.setGoals(Arrays.asList("clean"));
		request.setGoals(Arrays.asList("install"));
		populator.populateDefaults(request);
		MavenExecutionResult result = maven.execute(request);
		return result;

	}

	private boolean checkIfFelixJarsExistInLocalRepo() {
		File felixFile = new File(
				MavenCli.userMavenConfigurationHome.getAbsolutePath()
						+ File.separator + "repository" + File.separator
						+ "org" + File.separator + "apache" + File.separator
						+ "felix" + File.separator + "org.apache.felix.main"
						+ File.separator + "3.2.2" + File.separator
						+ "org.apache.felix.main-3.2.2.jar");
		if (!felixFile.exists()) {
			MavenExecutionResult installResult = null;
			try {
				installResult = downloadArtifact("org.apache.felix:org.apache.felix.main:3.2.2");
			} catch (Exception ex) {
				executionError = executionError
						+ "\nFelix execution environment is missing from your local Maven repository.";
				return false;
			}
			if (installResult.hasExceptions() || !felixFile.exists()) {
				executionError = executionError
						+ "\nFelix execution environment is missing from your local Maven repository.";
				return false;
			}

		}
		File mvnFile = new File(
				MavenCli.userMavenConfigurationHome.getAbsolutePath()
						+ File.separator + "repository" + File.separator
						+ "org" + File.separator + "ops4j" + File.separator
						+ "pax" + File.separator + "url" + File.separator
						+ "pax-url-mvn" + File.separator + "1.3.3"
						+ File.separator + "pax-url-mvn-1.3.3.jar");
		if (!mvnFile.exists()) {
			MavenExecutionResult installResult = null;
			try {
				installResult = downloadArtifact("org.ops4j.pax.url:pax-url-mvn:1.3.3");
			} catch (Exception ex) {
				executionError = executionError
						+ "\nMvn pax url protocol is missing from your local Maven repository.";
				return false;
			}
			if (installResult.hasExceptions() || !mvnFile.exists()) {
				executionError = executionError
						+ "\nMvn pax url protocol is missing from your local Maven repository.";
				return false;
			}
		}
		File wrapFile = new File(
				MavenCli.userMavenConfigurationHome.getAbsolutePath()
						+ File.separator + "repository" + File.separator
						+ "org" + File.separator + "ops4j" + File.separator
						+ "pax" + File.separator + "url" + File.separator
						+ "pax-url-wrap" + File.separator + "1.3.3"
						+ File.separator + "pax-url-wrap-1.3.3.jar");
		if (!wrapFile.exists()) {
			MavenExecutionResult installResult = null;
			try {
				installResult = downloadArtifact("org.ops4j.pax.url:pax-url-wrap:1.3.3");
			} catch (Exception ex) {
				executionError = executionError
						+ "\nWrap pax url protocol is missing from your local Maven repository.";
				return false;
			}
			if (installResult.hasExceptions() || !wrapFile.exists()) {
				executionError = executionError
						+ "\nWrap pax url protocol is missing from your local Maven repository.";
				return false;
			}
			return true;
		}
		return true;
	}

}