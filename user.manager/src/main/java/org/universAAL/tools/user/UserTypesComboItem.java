/*******************************************************************************
 * Copyright 2013 Universidad Politécnica de Madrid
 * Copyright 2013 Fraunhofer-Gesellschaft - Institute for Computer Graphics Research
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

package org.universAAL.tools.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.universAAL.middleware.owl.OntologyManagement;
import org.universAAL.middleware.rdf.Resource;
import org.universAAL.ontology.profile.User;

/**
 * @author amedrano
 * 
 */
public class UserTypesComboItem {

    Map<String, ComboItem> available;

    public UserTypesComboItem() {
	available = new HashMap<String, ComboItem>();
	Set<String> list = OntologyManagement.getInstance().getNamedSubClasses(
		User.MY_URI, true, true);
	for (String uri : list) {
	    String label = Resource.getResource(uri, null).getOrConstructLabel(
		    null);
	    available.put(uri, new ComboItem(uri, label));
	}
	
    }

   static class ComboItem{

	String uri;
	String label;

	/**
	 * @param uri
	 * @param label
	 */
	public ComboItem(String uri, String label) {
	    super();
	    this.uri = uri;
	    this.label = label;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
	    return uri;
	}

	/** {@ inheritDoc} */
	public boolean equals(ComboItem obj) {
	    return uri.equals(obj.uri);
	}

	/** {@ inheritDoc} */
	public String toString() {
	    return label;
	}
    }

    public ComboItem get(String uri) {
	return available.get(uri);
    }

    public Vector<ComboItem> getAvailableUserTypes() {
	return new Vector<ComboItem>(available.values());
    }
}
