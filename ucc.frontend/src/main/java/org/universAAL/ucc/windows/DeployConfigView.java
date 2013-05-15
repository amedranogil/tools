package org.universAAL.ucc.windows;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.universAAL.middleware.interfaces.PeerCard;
import org.universAAL.ucc.api.IInstaller;
import org.universAAL.ucc.model.UAPPReqAtom;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class DeployConfigView extends VerticalLayout {
	private Label label;
	private TextField txt;
	private ListSelect select;
	private String base;
	private ResourceBundle bundle;
	private UccUI app;
	private HashMap<String, String> peerNodes;

	public DeployConfigView(UccUI app, String serviceId, String uappPath) {
		base = "resources.ucc";
		bundle = ResourceBundle.getBundle(base);
		BundleContext bc = FrameworkUtil.getBundle(getClass())
				.getBundleContext();
		ServiceReference ref = bc.getServiceReference(IInstaller.class
				.getName());
		IInstaller installer = (IInstaller) bc.getService(ref);
		addComponent(new Label("<b>" + bundle.getString("deploy.config.capt")
				+ "</b>", Label.CONTENT_XHTML));
		this.app = app;
		// setSizeFull();
		setSpacing(true);
		setMargin(true);
		label = new Label(bundle.getString("app.part.label"));
		addComponent(label);
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		hl.setMargin(true);
		txt = new TextField(bundle.getString("app.part"));
		txt.setWidth("14em");
		hl.addComponent(txt);
		select = new ListSelect(bundle.getString("node.label"));
		select.setWidth("40em");
		// select.setHeight("2em");
		select.setRows(5);
		select.setImmediate(true);
		select.setNullSelectionAllowed(false);
		select.setMultiSelect(true);
//		for(Entry<String, String> pNode : peerNodes.entrySet()) {
//			select.addItem(pNode.getValue());
//		}
		// Add Nodes to dropdown box
		peerNodes = new HashMap<String, String>();
		// get valid peers for this part
////		List<PeerCard> validPeers = getValidPeers(List<UAPPReqAtom> reqs, String PartId)
////		for (Iterator entry = installer.getPeers().entrySet().iterator(); entry
////				.hasNext();) {
//			if (entry != null) {
//				
//				String all = entry.next().toString();
//				String item = all.substring(all.indexOf("=") + 1);
//				peerNodes.put(item, all);
//				select.addItem(item);
//			}
////		}
		hl.addComponent(select);
		addComponent(hl);
		setComponentAlignment(hl, Alignment.MIDDLE_CENTER);
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setMargin(true);
		hl2.setSpacing(true);
		addComponent(hl2);
		setComponentAlignment(hl2, Alignment.BOTTOM_CENTER);

	}

	public TextField getTxt() {
		return txt;
	}

	public void setTxt(TextField txt) {
		this.txt = txt;
	}

	public ListSelect getSelect() {
		return select;
	}

	public void setSelect(ListSelect select) {
		this.select = select;
	}

	public HashMap<String, String> getPeerNodes() {
		return peerNodes;
	}

}
