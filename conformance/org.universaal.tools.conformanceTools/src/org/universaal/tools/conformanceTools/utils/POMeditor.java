package org.universaal.tools.conformanceTools.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.w3c.dom.Document;

public class POMeditor {

	private File pom;
	public Document parsedPom;

	public POMeditor(File pom){
		this.pom = pom;
	}

	public Model getPomContent(){

		try{
			Model model;
			Reader reader = new FileReader(pom);
			MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
			model = xpp3Reader.read(reader);
			reader.close();

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document parsedPom = docBuilder.parse(pom);
			parsedPom.getDocumentElement().normalize();

			this.parsedPom = parsedPom;

			return model;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		return null;
	}

	public void writePom(Model model){

		try {
			MavenXpp3Writer writer = new MavenXpp3Writer();		
			writer.write(new FileOutputStream(this.pom), model);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public INSERT_OUTPUT insertNode(String nodeName){

		try{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document parsedPom = docBuilder.parse(pom);
			parsedPom.getDocumentElement().normalize();

			parsedPom.createElement(nodeName);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(parsedPom);
			StreamResult result = new StreamResult(pom);
			transformer.transform(source, result);

			return INSERT_OUTPUT.SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		return INSERT_OUTPUT.FAILED;
	}

	public Document getParsedPom() {
		return parsedPom;
	}

	public enum INSERT_OUTPUT{

		SUCCESS, FAILED, FATHER_NOT_FOUND, TOO_MANY_FATHERS
	}
}