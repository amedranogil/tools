package org.universAAL.tools;

import org.universAAL.middleware.container.ModuleActivator;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.container.utils.LogUtils;

public class ProjectActivator implements ModuleActivator {

	public static ModuleContext context; //TODO: set PrivatePackage!
	private MainToolFrame frame;
	
	public void start(ModuleContext ctxt) throws Exception {	
		context = ctxt;
		LogUtils.logDebug(context, getClass(), "start", "Starting.");
		/*
		 * uAAL stuff
		 */
		frame = new MainToolFrame();
		LogUtils.logDebug(context, getClass(), "start", "Started.");
	}


	public void stop(ModuleContext ctxt) throws Exception {
		LogUtils.logDebug(context, getClass(), "stop", "Stopping.");
		/*
		 * close uAAL stuff
		 */
		frame.dispose();
		frame = null;
		LogUtils.logDebug(context, getClass(), "stop", "Stopped.");

	}

}
