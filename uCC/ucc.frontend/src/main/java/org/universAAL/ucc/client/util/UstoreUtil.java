package org.universAAL.ucc.client.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.xml.namespace.QName;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.universAAL.commerce.ustore.tools.OnlineStoreManager;
import org.universAAL.commerce.ustore.tools.OnlineStoreManagerService;
import org.universAAL.commerce.ustore.tools.UAALException;
import org.universAAL.commerce.ustore.tools.UAALException_Exception;
import org.universAAL.ucc.service.manager.Activator;
import org.universAAL.ucc.startup.api.Setup;
import org.universAAL.ucc.startup.model.UserAccountInfo;

/**
 * Client for the Webservice communication with uStore
 * 
 * @author Nicole Merkle
 *
 */
public class UstoreUtil {
	private BundleContext bc;
	private ServiceReference ref;
	private Setup setup;
	private OnlineStoreManager client;
	private static final QName SERVICE_NAME = new QName(
			"http://tools.ustore.commerce.universaal.org/",
			"OnlineStoreManagerService");

	/**
	 * Creates an new UstoreUtil instance and connects to
	 * the uStore webservice.
	 */
	public UstoreUtil() {
		bc = FrameworkUtil.getBundle(getClass()).getBundleContext();
		ref = bc.getServiceReference(Setup.class.getName());
		setup = (Setup)bc.getService(ref);
		bc.ungetService(ref);
		URL wsdlURL = OnlineStoreManagerService.WSDL_LOCATION;
		OnlineStoreManagerService ss = new OnlineStoreManagerService(wsdlURL,
				SERVICE_NAME);
		client = ss.getOnlineStoreManagerPort();
	}

	/**
	 * Registers user to uStore
	 * 
	 * @return answer of the uStore registration
	 * @throws UAALException
	 */
	public void registerUser(String sessionKey) {
		Reader reader = null;
		try {
			reader = new FileReader(Activator.getModuleConfigHome().getAbsolutePath()+"/setup/setup.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String adminUserName = prop.getProperty("admin");
		String adminPassword = prop.getProperty("pwd");
		
		String portNum = prop.getProperty("uccPort");
		String idAddr = prop.getProperty("uccUrl");
		
		System.err.println(adminUserName+" "+adminPassword+" "+sessionKey+" "+portNum+ " "+" "+idAddr);
		try {
			client.registerDeployManager(sessionKey, adminUserName, adminPassword, idAddr, portNum);
		} catch (UAALException_Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets a session key from uStore.
	 * @return session key from uStore
	 */
	public String getSessionKey() {
		//Calling registered Users from Database
		List<UserAccountInfo>list = setup.getUsers(Activator.getModuleConfigHome().getAbsolutePath()+"/user/users.xml");
		String sessionKey = "";
		//Connection to uStore to get a session key for one user
		for(UserAccountInfo info : list) {
			if(info.getName() != null && !info.getName().equals("") && info.getPassword() != null && !info.getPassword().equals("")) {
				try {
					sessionKey = client.getSessionKey(info.getName(), info.getPassword());
				} catch (UAALException_Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sessionKey;
	}

}
