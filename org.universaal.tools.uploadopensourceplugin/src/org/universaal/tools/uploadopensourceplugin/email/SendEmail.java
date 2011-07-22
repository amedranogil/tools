/*
	Copyright 2011 SINTEF, http://www.sintef.no
	
	See the NOTICE file distributed with this work for additional 
	information regarding copyright ownership
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	  http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package org.universaal.tools.uploadopensourceplugin.email;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
/**
 * Class that handles the opening of the default mail-client and also generating
 * an email that can be sent to the AAL Studio team containing all important 
 * information about a project.
 * @author Adrian
 *
 */
public class SendEmail {

	private final static String EMAIL = "universAAL@something.org";
	private Desktop desktop;
	private File file;
	private Document doc;
	private String str;
	private String uri;
	private IProject project;

	/**
	 * Constructor that finds the currently selected project, and then the file
	 * "aalapp.xml" at its root.
	 */
	public SendEmail(){
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelectionService service = PlatformUI.getWorkbench().
				getActiveWorkbenchWindow().getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) service
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		IProject project=null;
		Object element;
		IPath path;

		element = structured.getFirstElement();

		if(element instanceof IResource){
			project = ((IResource)element).getProject();
		}else if (element instanceof PackageFragment){
			IJavaProject jProject = ((PackageFragment)element).getJavaProject();
			project = jProject.getProject();
		}else if (element instanceof IJavaElement){
			IJavaProject jProject = ((IJavaElement)element).getJavaProject();
			project = jProject.getProject();
		}

		String string = project.getLocation().toPortableString();

		this.file = new File(string+"/aalapp.xml");
		
		if(file!=null){
			generateURI();
		}
		
		
	}

	/**
	 * Constructor that takes the currently selected projects as input, and then
	 * finds the file "aalapp.xml" at its root.
	 * @param project - The currently selected project in the package explorer.
	 */
	public SendEmail(IProject project){
		this.project = project;
		String string = project.getLocation().toPortableString();
		this.file = new File(string+"/aalapp.xml");
		if(file!=null){
			generateURI();
		}
		
	}

	/**
	 * Generates an URI that is used to fill out address fields and the body of
	 * the email. The method needs to replace all characters that is not allowed
	 * in a URI with another one that is allowed.
	 */
	private void generateURI(){
		try{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(this.file);

			Source source = new DOMSource(doc);
			StringWriter stringWriter = new StringWriter();
			Result result = new StreamResult(stringWriter);
			TransformerFactory factory2 = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = factory2.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			transformer.transform(source, result);
			str = stringWriter.getBuffer().toString();

			uri = "mailto:"+EMAIL+"?SUBJECT=New universAAL Project&BODY="+str;


			uri = uri.replace(" ", "%20");
			uri = uri.replace("/", "%2F");
			uri = uri.replace("<", "%3C");
			uri = uri.replace(">", "%3E");
			
			//Carriage return and new line feed
			char lineBreak = 13;
			char lineBreak2 = 10;

			uri = uri.replace(""+lineBreak+lineBreak2, "");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Open the default mail client and if the field this.uri is set, attempts 
	 * to fill in address field and body of the email. If the URI is malformed,
	 * it instead opens a blank email.
	 */
	public void sendEmail(){
		if(Desktop.isDesktopSupported()){
			desktop = Desktop.getDesktop();
			try {
				if(uri!=null)
					desktop.mail(new URI(uri));
				else
					desktop.mail();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				System.out.println("Parseerror at: "+e.getIndex());
				e.printStackTrace();
				try {
					desktop.mail();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		}
	}

}
