package org.universAAL.ucc.service.impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.universAAL.middleware.container.utils.ModuleConfigHome;
import org.universAAL.ucc.service.api.IServiceModel;
import org.universAAL.ucc.service.api.IServiceManagement;
import org.universAAL.ucc.service.api.IServiceRegistration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Model implements IServiceModel {

	private IServiceRegistration srvReg;
	private IServiceManagement srvMan;
	public static String SERVICEFILENAME;
	private static Document doc;
	
	public Model() {
		srvReg = (IServiceRegistration)(new ServiceRegistration());
		srvMan = (IServiceManagement) (new ServiceManagment());
		ModuleConfigHome mc = new ModuleConfigHome(" ", "");
		String temp = mc.getAbsolutePath().trim();
		SERVICEFILENAME = temp +"/services.xml";
	}

	public IServiceRegistration getServiceRegistration() {
		return srvReg;
	}
	
	public IServiceManagement getServiceManagment() {
		return srvMan;
	}
	
	static protected Document getSrvDocument(){
		if(doc == null){
		File file = new File(SERVICEFILENAME);
		try {
			if(file.exists()){
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db;
				db = dbf.newDocumentBuilder();
				doc =  db.parse(file);
			}else{
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element appRoot = doc.createElement("services");
				doc.appendChild(appRoot);
				try {
					TransformerFactory.newInstance().newTransformer().transform(
					            new DOMSource(doc), new StreamResult(Model.SERVICEFILENAME));
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("[Model.getSrvDocument] can not create the root element!");
				}
			}

		} catch (Exception e) {
			return null;
		}
	}
		return doc;
	}
}
