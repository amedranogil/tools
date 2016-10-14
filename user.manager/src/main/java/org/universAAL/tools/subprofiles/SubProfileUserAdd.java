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
package org.universAAL.tools.subprofiles;

import javax.swing.JPanel;

import org.universAAL.ontology.profile.SubProfile;
import org.universAAL.ontology.profile.User;

/**
 * @author amedrano
 *
 */
public interface SubProfileUserAdd {

	public JPanel getMinimalUserDataForm(User u);
	
	public SubProfile getNewSubProfile(User u);
	
	public String suggestUserURI();
	
}