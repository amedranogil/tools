/*TAG:PACKAGE*/

/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_8 */
import org.osgi.framework.BundleContext;
import org.universAAL.middleware.output.OutputEvent;
import org.universAAL.middleware.output.OutputEventPattern;
import org.universAAL.middleware.output.OutputSubscriber;
import org.universAAL.middleware.rdf.Resource;

public class /*TAG:CLASSNAME*/ extends OutputSubscriber{

    protected /*TAG:CLASSNAME*/(BundleContext context,
	    OutputEventPattern initialSubscription) {
		super(context, initialSubscription);
		// TODO Auto-generated constructor stub
    }
    
    protected /*TAG:CLASSNAME*/(BundleContext context) {
		super(context, getPermanentSubscriptions());
		// TODO Auto-generated constructor stub
    }
    
    private static OutputEventPattern getPermanentSubscriptions() {
		// TODO Auto-generated method stub
		return null;
    }

    public void adaptationParametersChanged(String dialogID,
	    String changedProp, Object newVal) {
		// TODO Auto-generated method stub
	
    }

    public void communicationChannelBroken() {
		// TODO Auto-generated method stub
	
    }

    public Resource cutDialog(String dialogID) {
		// TODO Auto-generated method stub
		return null;
    }

    public void handleOutputEvent(OutputEvent event) {
		// TODO Auto-generated method stub
	
    }

}