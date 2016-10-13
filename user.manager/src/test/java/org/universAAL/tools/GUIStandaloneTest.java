/*******************************************************************************
 * Copyright 2016 Universidad Politécnica de Madrid UPM
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.universAAL.tools;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.universAAL.container.JUnit.JUnitModuleContext;
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.owl.DataRepOntology;
import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.serialization.MessageContentSerializer;
import org.universAAL.middleware.serialization.turtle.TurtleSerializer;
import org.universAAL.middleware.service.owl.ServiceBusOntology;
import org.universAAL.ontology.cryptographic.CryptographicOntology;
import org.universAAL.ontology.location.LocationOntology;
import org.universAAL.ontology.phThing.PhThingOntology;
import org.universAAL.ontology.profile.ProfileOntology;
import org.universAAL.ontology.security.SecurityOntology;
import org.universAAL.ontology.shape.ShapeOntology;
import org.universAAL.ontology.space.SpaceOntology;
import org.universAAL.ontology.vcard.VCardOntology;

/**
 * @author amedrano
 *
 */
public class GUIStandaloneTest {

	/**
	 * 
	 */
	public GUIStandaloneTest() {
	ModuleContext mc = new JUnitModuleContext();
	ProjectActivator.context = mc;
	
	mc.getContainer().shareObject(mc,
			new TurtleSerializer(),
			new Object[] { MessageContentSerializer.class.getName() });
	
	
	OntologyManagement.getInstance().register(mc, new DataRepOntology());
	OntologyManagement.getInstance().register(mc, new ServiceBusOntology());
//	OntologyManagement.getInstance().register(mc, new UIBusOntology());
    OntologyManagement.getInstance().register(mc, new LocationOntology());
//	OntologyManagement.getInstance().register(mc, new SysinfoOntology());
    OntologyManagement.getInstance().register(mc, new ShapeOntology());
    OntologyManagement.getInstance().register(mc, new PhThingOntology());
    OntologyManagement.getInstance().register(mc, new SpaceOntology());
    OntologyManagement.getInstance().register(mc, new VCardOntology());
	OntologyManagement.getInstance().register(mc, new ProfileOntology());
//	OntologyManagement.getInstance().register(mc, new MenuProfileOntology());
	OntologyManagement.getInstance().register(mc, new CryptographicOntology());		
	OntologyManagement.getInstance().register(mc, new SecurityOntology());

	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				JFrame f = new MainToolFrame();
				f.pack();
				f.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GUIStandaloneTest();
	}

}
