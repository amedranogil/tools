package org.universaal.tools.conformanceTools.run;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.IMavenProjectRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.universaal.tools.conformanceTools.Activator;
import org.universaal.tools.conformanceTools.utils.RunPlugin;

public class ToolsRun {

	private RunPlugin plugin;
	private IProject project;
	private IWorkbenchWindow window;
	private ISelection selection;

	public void run(IWorkbenchWindow window, RunPlugin plugin){//, ExecutionEvent event){

		this.plugin = plugin;
		this.window = window;

		this.selection = window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");

		if ((selection != null) && (selection instanceof StructuredSelection)) {
			Object selected = ((StructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof JavaProject) {
				this.project = ((JavaProject) selected).getProject();
			} else if (selected instanceof IProject) {
				this.project = ((IProject) selected);
			} 
			else {
				MessageDialog.openInformation(window.getShell(),
						"Conformance Tools", "Not a project.");

				return;
			}

			test((StructuredSelection) selection);

		}
	}

	private void test(IStructuredSelection selected){

		Job job = new Job("AAL Studio") {
			protected IStatus run(IProgressMonitor monitor) {

				try{
					// Continuous progress bar
					monitor.beginTask("Verifying conformance", IProgressMonitor.UNKNOWN);

					IMavenProjectRegistry projectManager = MavenPlugin.getMavenProjectRegistry();

					if (project != null && !project.hasNature(IMavenConstants.NATURE_ID)) {
						monitor.done();
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Not a Maven project.");
					}

					IFile pomResource = project.getFile(IMavenConstants.POM_FILE_NAME);
					if (pomResource == null) {
						monitor.done();
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Missing POM file.");
					}

					IMavenProjectFacade projectFacade = projectManager.create(project, monitor);
					IMaven maven = MavenPlugin.getMaven();
					MavenExecutionRequest request = projectManager.createExecutionRequest(pomResource,
							projectFacade.getResolverConfiguration(), monitor);

					List<String> goals = new ArrayList<String>();

					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IWorkspaceDescription description = workspace.getDescription();
					if (!description.isAutoBuilding())
						goals.add("compiler:compile"); // compile it if autobuilding is off

					if(plugin == RunPlugin.FindBugs)
						goals.add("findbugs:findbugs");

					else if(plugin == RunPlugin.CheckStyle)
						goals.add("checkstyle:checkstyle");

					//goals.add("dashboard:dashboard");

					request.setGoals(goals);
					MavenExecutionResult result = maven.execute(request, monitor);

					if(result.hasExceptions()) {
						String errors = "Results: \n";
						for(Throwable e : result.getExceptions()) {

							if(e.getCause() != null && e.getCause().getMessage() != null)
								errors += e.getCause().getMessage();
						}

						monitor.done();
						System.out.println("CT: report blocked - "+errors);
						if(errors.contains("java.lang.OutOfMemoryError"))
							System.out.println("CT: verify start parameter [-XX:MaxPermSize] of AAL Studio and increase value.");

						return new Status(Status.ERROR, Activator.PLUGIN_ID, errors);
					}
					else{
						monitor.done(); // old one

						// generate report and visualize it
						monitor.beginTask("Reporting conformance tests", IProgressMonitor.UNKNOWN);

						goals.clear();
						MavenExecutionRequest request2 = projectManager.createExecutionRequest(pomResource,
								projectFacade.getResolverConfiguration(), monitor);

						Properties props = new Properties();
						if(plugin == RunPlugin.FindBugs){

							goals.add("findbugs:check");
							props.setProperty("findbugs.failOnError", "false");
						}
						else if(plugin == RunPlugin.CheckStyle){

							goals.add("checkstyle:check");
							props.setProperty("checkstyle.failOnViolation", "false");
							props.setProperty("checkstyle.failsOnError", "false");
							//goals.add("checkstyle:checkstyle-aggregate");
							//props.setProperty("checkstyle.outputDirectory", Activator.absolutePath+"/target/");
						}

						//goals.add("dashboard:dashboard");
						//props.setProperty("dashboard.outputDirectory ", Activator.absolutePath+"/target/");

						request2.setUserProperties(props);
						request2.setGoals(goals);
						MavenExecutionResult result2 = maven.execute(request2, monitor);

						if(result2.hasExceptions()) {
							String errors = "Results: \n";
							for(Throwable e : result2.getExceptions()) {

								if(e.getCause() != null && e.getCause().getMessage() != null)
									errors += e.getCause().getMessage();
							}

							monitor.done();
							return new Status(Status.ERROR, Activator.PLUGIN_ID, "CT: "+errors);
						}

						// visualize report - open report file
						window.getWorkbench().getDisplay().syncExec(new Runnable() {

							@Override
							public void run() {

								try{
									IFile fileToOpen = null;
									IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject(project.getDescription().getName());

									if(plugin == RunPlugin.CheckStyle)
										fileToOpen = p.getFile("/target/site/checkstyle.html");

									if(plugin == RunPlugin.FindBugs)
										fileToOpen = p.getFile("/target/findbugsXml.xml");

									if (fileToOpen != null && fileToOpen.exists() ){
										org.eclipse.core.filesystem.IFileStore fileStore = EFS.getLocalFileSystem().getStore(fileToOpen.getLocationURI());
										IWorkbenchPage page = window.getActivePage();

										try {
											if(page != null && fileStore != null)
												IDE.openEditorOnFileStore( page, fileStore );
											else
												System.out.println("CT: can't open report file.");
										} 
										catch ( PartInitException e ) {
											e.printStackTrace();
										}
									} 
									else {
										if(plugin == RunPlugin.CheckStyle)
											System.out.println("CT: does file "+ResourcesPlugin.getWorkspace().getRoot().getProject(project.getDescription().getName())+"/target/site/checkstyle.html"+" exist?");
										if(plugin == RunPlugin.FindBugs)
											System.out.println("CT: does file "+ResourcesPlugin.getWorkspace().getRoot().getProject(project.getDescription().getName())+"/target/findbugsXml.xml"+" exist?");
									}
								}
								catch(Exception ex){
									ex.printStackTrace();
								}
							}
						});
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
					monitor.done();
					return new Status(Status.ERROR, Activator.PLUGIN_ID, "See console log.");
				}

				monitor.done();
				return new Status(Status.OK, Activator.PLUGIN_ID, "All ok.");
			}
		};

		job.setUser(true);
		job.schedule();	
	}
}