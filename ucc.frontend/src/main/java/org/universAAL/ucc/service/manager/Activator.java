package org.universAAL.ucc.service.manager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.universAAL.ucc.api.IInstaller;
import org.universAAL.ucc.frontend.api.IFrontend;
import org.universAAL.ucc.frontend.api.impl.FrontendImpl;

public class Activator implements BundleActivator {
	private static IInstaller installer;
	private static ServiceReference ref;
	private static BundleContext bc;

	@Override
	public void start(BundleContext context) throws Exception {
		Activator.bc = context;
		ref = context.getServiceReference(IInstaller.class.getName());
		installer = (IInstaller) context.getService(ref);
	}
	
	public static IInstaller getInstaller() {
		if(installer == null) {
			installer = (IInstaller) bc.getService(ref);
		}
		return installer;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		context.ungetService(ref);
		
	}

}
