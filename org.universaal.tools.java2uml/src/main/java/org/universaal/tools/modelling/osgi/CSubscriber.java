package org.universaal.tools.modelling.osgi;

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.context.ContextEvent;
import org.universAAL.middleware.context.ContextEventPattern;
import org.universAAL.middleware.context.ContextSubscriber;

public class CSubscriber extends ContextSubscriber {

    protected CSubscriber(ModuleContext context,
	    ContextEventPattern[] initialSubscriptions) {
	super(context, initialSubscriptions);
	// TODO Auto-generated constructor stub
    }

    protected CSubscriber(ModuleContext context) {
	super(context, getPermanentSubscriptions());
	// TODO Auto-generated constructor stub
    }

    private static ContextEventPattern[] getPermanentSubscriptions() {
	// TODO Auto-generated method stub
	return null;
    }

    public void communicationChannelBroken() {
	// TODO Auto-generated method stub

    }

    public void handleContextEvent(ContextEvent event) {
	// TODO Auto-generated method stub

    }

}
