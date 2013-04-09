package org.universAAL.ucc.controller.install;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.universAAL.middleware.deploymaneger.uapp.model.DeploymentUnit;
//import org.universAAL.middleware.interfaces.mpa.model.DeploymentUnit;
import org.universAAL.middleware.interfaces.PeerCard;
import org.universAAL.middleware.interfaces.PeerRole;
//import org.universAAL.middleware.interfaces.mpa.model.Part;
import org.universAAL.middleware.deploymaneger.uapp.model.Part;
import org.universAAL.middleware.managers.api.InstallationResults;
import org.universAAL.middleware.managers.api.InstallationResultsDetails;
import org.universAAL.middleware.managers.api.UAPPPackage;
import org.universAAL.ucc.api.IInstaller;
import org.universAAL.ucc.configuration.configdefinitionregistry.interfaces.ConfigurationDefinitionRegistry;
import org.universAAL.ucc.configuration.model.configurationdefinition.Configuration;
import org.universAAL.ucc.configuration.view.ConfigurationOverviewWindow;
import org.universAAL.ucc.frontend.api.impl.FrontendImpl;
import org.universAAL.ucc.model.AALService;
import org.universAAL.ucc.model.UAPP;
import org.universAAL.ucc.model.UAPPPart;
import org.universAAL.ucc.service.api.IServiceRegistration;
import org.universAAL.ucc.service.manager.Activator;
import org.universAAL.ucc.windows.DeployConfigView;
import org.universAAL.ucc.windows.DeployStrategyView;
import org.universAAL.ucc.windows.DeploymentInformationView;
import org.universAAL.ucc.windows.NoConfigurationWindow;
import org.universAAL.ucc.windows.UccUI;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class DeploymentInfoController implements Button.ClickListener,
		ValueChangeListener {
	private DeploymentInformationView win;
	private AALService aal;
	private UccUI app;
	private HashMap<String, DeployStrategyView> dsvMap;
	private HashMap<String, DeployConfigView> dcvMap;
	private String selected = "";
	private String base = "";
	private ResourceBundle bundle;
	private VerticalLayout actVL;
	private Map<String, PeerCard> peers;
	private IInstaller installer;
	private IServiceRegistration srvRegistration;
	private BundleContext bc;
	private List<Part>parts;
	private Map<Part, List<PeerCard>> mapLayout;

	public DeploymentInfoController(UccUI app, AALService aal,
			DeploymentInformationView win) {
		base = "resources.ucc";
		bundle = ResourceBundle.getBundle(base);
		installer = Activator.getInstaller();
		srvRegistration = Activator.getReg();
		bc = FrameworkUtil.getBundle(getClass()).getBundleContext();
		this.aal = aal;
		this.win = win;
		this.app = app;
		parts = new ArrayList<Part>();
		dsvMap = new HashMap<String, DeployStrategyView>();
		dcvMap = new HashMap<String, DeployConfigView>();
		mapLayout = new HashMap<Part, List<PeerCard>>();
		int i = 0;

		for (UAPPPart uapp : aal.getUaapList()) {
			i++;
			if (i == 1) {
				selected = uapp.getPart().getPartId();
			}
			System.err.println(uapp.getPart().getPartId());
			win.getTree().addItem(uapp.getPart().getPartId());
			win.getTree().setChildrenAllowed(uapp.getPart().getPartId(), false);
			DeployStrategyView dsv = new DeployStrategyView(aal.getName(),
					aal.getServiceId(), uapp.getUappLocation());
			dsv.getOptions().addListener(this);
			dsvMap.put(uapp.getPart().getPartId(), dsv);

			DeployConfigView dcv = new DeployConfigView(app,
					aal.getServiceId(), uapp.getUappLocation());
			dcv.getTxt().setValue(uapp.getPart().getPartId());
			dcv.getTxt().setEnabled(false);
			dcv.getSelect().setEnabled(false);
			dcv.setEnabled(false);
			dcvMap.put(uapp.getPart().getPartId(), dcv);
		}
		win.getTree().select(selected);
		for (UAPPPart ua : aal.getUaapList()) {
			if (ua.getPart().getPartId().equals(selected)) {
				DeployStrategyView dsv = dsvMap.get(ua.getPart().getPartId());
				DeployConfigView dcv = dcvMap.get(ua.getPart().getPartId());
				actVL = win.createSecondComponent(dsv, dcv);
			}
		}
		win.getTree().addListener(this);
		win.getOk().addListener((Button.ClickListener) this);
		win.getCancel().addListener((Button.ClickListener) this);
		win.createFirstComponent(win.getTree());
	}

	public void buttonClick(ClickEvent event) {
		if (event.getButton() == win.getOk()) {
			Map<Part, List<PeerCard>> config = null;
			// TODO: Deployment
			peers = installer.getPeers();
			for (UAPPPart uapp : aal.getUaapList()) {

				// Selected part in tree
				if (uapp.getPart().getPartId().equals(selected)) {
					
					// Default Deployment Strategy
					if (dsvMap.get(selected).getOptions().getValue().toString()
							.equals(bundle.getString("opt.available.nodes"))) {
						config = buildDefaultInstallationLayout(uapp);
					}
					// User selection strategy
					else if (dsvMap.get(selected).getOptions().getValue()
							.toString()
							.equals(bundle.getString("opt.selected.nodes"))) {
						System.err.println("User Installation for part: "+uapp.getPart().getPartId());
						config = buildUserInstallationLayout(uapp);
						if (config.isEmpty())
							return;
					}

				}
			}
			// Remove installed part view and item from tree
			win.getHp().removeComponent(actVL);
			win.getTree().removeListener(this);
			win.getTree().removeItem(selected);
			win.getTree().addListener(this);
			System.err.println("Selected and removed node is: " + selected);
			System.err.println("Tree node was removed");
			dsvMap.remove(selected);
			dcvMap.remove(selected);
			if (dsvMap.isEmpty() && dcvMap.isEmpty()) {
				// app.getMainWindow().showNotification(
				// bundle.getString("success.install.msg"),
				// Notification.TYPE_HUMANIZED_MESSAGE);
				
				//Change Map<Part, List<PeerCard>> to Map<PeerCard, List<Part>>
				Map<PeerCard, List<Part>> peerMap = new HashMap<PeerCard, List<Part>>();
				for(Part key : config.keySet()) {
					List<PeerCard> val = config.get(key);
					for(PeerCard pc : val) {
						if(!peerMap.containsKey(pc)) {
							peerMap.put(pc, new ArrayList<Part>());
						} peerMap.get(pc).add(key);
					}
					
				}
				// Deploy to MW
				for(UAPPPart uapp : aal.getUaapList()) {
				UAPPPackage uapack = null;
				// Get uapp location uri
				String appLocation = uapp.getUappLocation();
				System.err.println(uapp.getUappLocation());
				String p = appLocation.substring(appLocation.indexOf("bin/"));
				appLocation = /*System.getenv("systemdrive")
						+ "/tempUsrvFiles"
						+ appLocation
								.substring(appLocation.indexOf("./") + 1);*/ FrontendImpl.getUappURI()+"/"+p;
				System.err.println("LOCATION URI: "+appLocation);
				File uf = uf = new File(appLocation.trim());
				for (PeerCard pc: peerMap.keySet()) {
					List<Part> parts = peerMap.get(pc);
					
					System.out.println("[deploymentInfoController.peerMap] for peer with id: " + pc.getPeerID());
					for (int i=0; i<parts.size(); i++) 
						System.out.println("[deploymentInfoController.peerMap] has part: " + parts.get(i).getPartId());
				}
				
				uapack = new UAPPPackage(aal.getServiceId(),
						uapp.getAppId(), uf.toURI(), peerMap);
				// Not work with uri, MW not implemented yet
				 InstallationResultsDetails res =
				 installer.requestToInstall(uapack);
				 // add app and bundles to "services.xml" file.
				if (res.equals(InstallationResults.SUCCESS)) {
					srvRegistration.registerApp(aal.getServiceId(),
							uapp.getAppId());
					// get bundles for each part in the appId;
					// for each bundle:
					srvRegistration.registerBundle(aal.getServiceId(),
							uapp.getBundleId(), uapp.getBundleVersion());
					// TODO: Call configurator to configure the uapps, after
					// uapp is running (for every uapp)
					ServiceReference configRef = bc
							.getServiceReference(ConfigurationDefinitionRegistry.class
									.getName());
					ConfigurationDefinitionRegistry reg = (ConfigurationDefinitionRegistry) bc
							.getService(configRef);
					Configuration conf = null;
					System.err.println("Size of all APP-Configurations: "
							+ reg.getAllConfigDefinitions().size());
					for (Configuration configurator : reg
							.getAllConfigDefinitions()) {
						if (configurator.getBundlename() != null
								&& configurator.getBundlename().equals(
										uapp.getBundleId())) {
							conf = configurator;
						}
					}

					if (conf != null) {
						ConfigurationOverviewWindow cow = new ConfigurationOverviewWindow(
								conf);
						cow.center();
						app.getMainWindow().addWindow(cow);
					} else {
						NoConfigurationWindow ncw = new NoConfigurationWindow(
								bundle.getString("installed.note"));
						app.getMainWindow().addWindow(ncw);
					}
					bc.ungetService(configRef);

				} else {
					// get parts mapping from config
					System.out.println("[DeploymentInfoController] global result: " + res.getGlobalResult().toString());
					for(Part key : config.keySet()) {
						List<PeerCard> pcards = config.get(key);
						for(PeerCard card: pcards) {
							System.out.println("[DeploymentInfoController] detailed results for part-" + key.getPartId()
									+ "/peer-" + card.getPeerID() + " is: " + res.getDetailedResult(card, key));
						}
					}
					NoConfigurationWindow ncw = new NoConfigurationWindow(
							bundle.getString("install.error"));
					app.getMainWindow().addWindow(ncw);
					
					
				}
				app.getMainWindow().removeWindow(win);
				File f = new File(System.getenv("systemdrive")
						+ "/tempUsrvFiles/");
				deleteFiles(f);

			}
			}

			selected = (String) win.getTree().getItemIds().iterator().next();
			win.getTree().select(selected);
		}

		// Installation was canceled by user
		if (event.getButton() == win.getCancel()) {
			app.getMainWindow().removeWindow(win);
			app.getMainWindow().showNotification(
					bundle.getString("break.note"),
					Notification.TYPE_HUMANIZED_MESSAGE);
			File f = new File(System.getenv("systemdrive") + "/tempUsrvFiles/");
			deleteFiles(f);
		}
	}

	private void deleteFiles(File path) {
		File[] files = path.listFiles();
		for (File del : files) {
			if (del.isDirectory()
					&& !del.getPath().substring(del.getPath().indexOf(".") + 1)
							.equals("usrv")) {
				deleteFiles(del);
			}
			if (!del.getPath().substring(del.getPath().indexOf(".") + 1)
					.equals("usrv"))
				del.delete();
		}

	}

	public void valueChange(ValueChangeEvent event) {
		for (UAPPPart ua : aal.getUaapList()) {
			System.err.println(aal.getUaapList().size());
			if (ua.getPart().getPartId().equals(event.getProperty().toString())) {
				selected = event.getProperty().toString();
				DeployStrategyView dsv = dsvMap.get(ua.getPart().getPartId());
				DeployConfigView dcv = dcvMap.get(ua.getPart().getPartId());
				actVL = win.createSecondComponent(dsv, dcv);
			}
		}

		if (event.getProperty().getValue().toString()
				.equals(bundle.getString("opt.selected.nodes"))) {
			dcvMap.get(selected).setEnabled(true);
			dcvMap.get(selected).getTxt().setEnabled(true);
			dcvMap.get(selected).getSelect().setEnabled(true);
		}
		if (event.getProperty().getValue().toString()
				.equals(bundle.getString("opt.available.nodes"))) {
			dcvMap.get(selected).setEnabled(false);
			dcvMap.get(selected).getTxt().setEnabled(false);
			dcvMap.get(selected).getSelect().setEnabled(false);
		}

	}

	/*
	 * TODO: this method needs to be updated with MW for the new .uapp file -
	 * currently use some pre-assigned values
	 */
	private Map<Part, List<PeerCard>> buildDefaultInstallationLayout(UAPPPart uapp) {
		// assign some values for testing
		// ArrayList<Part> parts = new ArrayList();
		// Part part1 = new Part();
		// part1.setPartId("part1");
		// Part part2 = new Part();
		// part2.setPartId("part2");
		// parts.add(part1);
		// parts.add(part2);
		// uapp.setPart(parts);
		// TODO: Do we need to check AAL space first (aalSpaceCheck)?
		
//		Map<Part, List<PeerCard>> mpaLayout = new HashMap<Part, List<PeerCard>>();
		//Map<String, PeerCard> peersToCheck = new HashMap<String, PeerCard>();
		//peersToCheck.putAll(peers);
		//List<PeerCard> peerList = new ArrayList<PeerCard>();
		for (UAPPPart ua : aal.getUaapList()) {
			System.out.println("[DeployInfoController] build default layout for: " + ua.getPart().getPartId());
			Map<String, PeerCard> peersToCheck = new HashMap<String, PeerCard>();
			peersToCheck.putAll(peers);
			// check: deployment units
			for (String key : peersToCheck.keySet()) {
				PeerCard peer = peersToCheck.get(key);
				System.out.println("[DeployInfoController.buildDefaultLayout] test layout for peer: " + peer.getPeerID());						
				if (ua.getPart().getDeploymentUnit() == null) {
					// use any peer for testing
					System.out
							.println("[DeploymentInfoController] DeploymentUnit is null, use any peer!");
					List<PeerCard> peerList = mapLayout.get(ua.getPart());
					if (peerList==null)
						peerList = new ArrayList<PeerCard>();
					peerList.add(peer);
					System.out.println("[DeploymentInfoController] add peer " + peer.getPeerID() + " to " + ua.getPart().getPartId());
					mapLayout.put(ua.getPart(), peerList);
					peersToCheck.remove(key);
					break;
				}
				// TODO: wait for MW to see how to check
				if (/*checkDeployementUnit(ua.getPart().getDeploymentUnit(), peer)*/ true) {
					System.err.println("IN CHECK DEPLOYMENT UNIT!");
					
					List<PeerCard> peerList = mapLayout.get(ua.getPart());
					if (peerList==null)
						peerList = new ArrayList<PeerCard>();
					peerList.add(peer);
					System.out.println("[DeploymentInfoController] add peer " + peer.getPeerID() + " to " + ua.getPart().getPartId());					
					mapLayout.put(ua.getPart(), peerList);
					peersToCheck.remove(key);
					break;
				} else {
					app.getMainWindow().showNotification(
							bundle.getString("peer.available.not"),
							Notification.TYPE_WARNING_MESSAGE);
				}
			}
		}
		
		return mapLayout;
	}

	private Map<Part, List<PeerCard>> buildUserInstallationLayout(UAPPPart uapp) {
		Map<String, PeerCard> peersToCheck = new HashMap<String, PeerCard>();
		List<PeerCard> peerList = new ArrayList<PeerCard>();
		// Create peer from user selection and test if peer fits for deployment
		peersToCheck.putAll(peers);
		// Extract Peer Info from user selection
		if (dcvMap.get(selected).getSelect().getValue() != null
				&& !(dcvMap.get(selected).getSelect().getValue().toString()
						.equals(""))) {
			Collection<String> selPeer = (Collection<String>) dcvMap
					.get(selected).getSelect().getValue();
			System.out.println("[DeploymentInfoController.buildUserLayout] user has selected " + selPeer.toArray().length + " peers");
			for (int i = 0; i < selPeer.toArray().length; i++) {
				String value = dcvMap.get(selected).getPeerNodes()
						.get(selPeer.toArray()[i]);
				System.err.println("The user selected value: " + value);
				String key = value.substring(0, value.indexOf("="));
				System.err.println("KEY is: " + key);
				String id = peers.get(key).getPeerID();
				PeerRole role = peers.get(key).getRole();
				System.err.println("Peer-ROLE: " + role);
				System.err.println("ID: " + id);
				PeerCard peer = new PeerCard(id, role);
				//TODO: update when MW has the right info to check
				if (/*checkDeployementUnit(uapp.getPart().getDeploymentUnit(),
						peer)*/ true) {
					System.err.println("[buildUserInstallationLayout] In CHECKDEPLOYMENTUNIT!");
					peerList.add(peer);
					System.out.println("[DeploymentInfoController.buildUserLayout] add one peer " + peer.getPeerID() + " to part " + uapp.getPart().getPartId());
					peersToCheck.remove(key);
				} else {
					app.getMainWindow().showNotification(
							bundle.getString("peer.available.not"),
							Notification.TYPE_WARNING_MESSAGE);
				}
			}
			mapLayout.put(uapp.getPart(), peerList);
			
			
		} else {
			app.getMainWindow().showNotification(
					bundle.getString("select.node.msg"),
					Notification.TYPE_ERROR_MESSAGE);

		}
		return mapLayout;
	}

	public static boolean checkDeployementUnit(List<DeploymentUnit> list,
			PeerCard peer) {
		String osUnit;
		String pUnit;
		for (DeploymentUnit deployementUnit : list) {
			// check the existence of: osUnit and platformUnit
			if (deployementUnit.getOsUnit() != null) {
				osUnit = deployementUnit.getOsUnit().value();
				if (osUnit == null || osUnit.isEmpty()) {
					System.out
							.println("[DeployStrategyView.checkDeploymentUnit] OSunit is present but not consistent. OSUnit is null or empty");
					return false;
				}
				// Check if compatible?
				if (!osUnit.equals("any")) {
					// only considers equal definition
					// if (!osUnit.equalsIgnoreCase(peer.getOS())) return false;
					System.out.println("osUnit: " + osUnit);
					if (!(peer.getOS().contains(osUnit)))
						return false;
				}
			} else if (deployementUnit.getPlatformUnit() != null) {
				pUnit = deployementUnit.getPlatformUnit().value();
				if (pUnit == null || pUnit.isEmpty()) {
					System.out
							.println("[DeployStrategyView.checkDeploymentUnit] PlatformUnit is present but not consistent. Plaform is null or empty");
					return false;

				}
				// check if compatible?
				if (!pUnit.equalsIgnoreCase(peer.getPLATFORM_UNIT()))
					return false;
			}
			// TODO: check containerUnit?
		}
		return true;
	}

}
