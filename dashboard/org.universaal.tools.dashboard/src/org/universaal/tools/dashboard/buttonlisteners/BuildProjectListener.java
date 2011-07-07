package org.universaal.tools.dashboard.buttonlisteners;


import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class BuildProjectListener implements MouseListener{

	ViewPart view;
	
	public BuildProjectListener(ViewPart view){
		this.view=view;
	}

	@Override
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp(MouseEvent arg0) {
		IHandlerService handlerService = (IHandlerService)view.getSite().getService(IHandlerService.class);
		
		try {
			handlerService.executeCommand("org.universaal.tools.buildserviceapplication.actions.BuildAction", null);
			
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotDefinedException e) {
			MessageDialog.openInformation(view.getSite().getShell(),
					"Command not defined.",
					"This command was not available. " +
					"Please install AAL Studio BuildPlugin.");
			e.printStackTrace();
		} catch (NotEnabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotHandledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
