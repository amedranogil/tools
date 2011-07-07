package org.universaal.tools.dashboard.buttonlisteners;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class CreateNewProjectListener implements SelectionListener {

	ViewPart view;

	public CreateNewProjectListener(ViewPart view){
		this.view = view;
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		IHandlerService handlerService = (IHandlerService)view.getSite().getService(IHandlerService.class);

		try {
			handlerService.executeCommand("org.universaal.tools.newwizard.plugin.command.startNewWizard", null);

		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDefinedException e) {
			MessageDialog.openInformation(view.getSite().getShell(),
					"Command not defined.",
					"This command was not available. " +
					"Please install AAL Studio New Wizards Plugin.");
			e.printStackTrace();
		} catch (NotEnabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotHandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		

	}

}
