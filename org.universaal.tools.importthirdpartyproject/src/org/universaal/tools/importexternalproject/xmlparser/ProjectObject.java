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
package org.universaal.tools.importexternalproject.xmlparser;

import java.util.ArrayList;

/**
 * Collects all data about projects described in the projects.xml-file.
 * Implements comparable so that objects marked that their name matched a 
 * search-string will always be displayed before objects that only matched by 
 * tags after sorting the list.
 * @author Adrian
 *
 */
public class ProjectObject implements Comparable<ProjectObject>{
	
	
	private String name;
	private String url;
	private String svnurl;
	private String description;
	private String developer;
	private String date;
	private ArrayList<String> tags;
	private boolean nameMatch;
	
	/**
	 * All parameters should be self-explanatory, except the namematch-boolean.
	 * If the name of this project matched the search-string, namematch must be
	 * set to True, so that, after sorting the list, this will always be placed
	 * before objects that only matched the search-string by tags.
	 * @param iName
	 * @param iUrl
	 * @param iSvnUrl
	 * @param iDesc
	 * @param iDev
	 * @param iDate
	 * @param nameMatch
	 */
	public ProjectObject(String iName, String iUrl, String iSvnUrl, String iDesc, String iDev,
			String iDate, boolean nameMatch){
		this.name = iName;
		this.url = iUrl;
		this.svnurl = iSvnUrl;
		this.description = iDesc;
		this.developer = iDev;
		this.date = iDate;
		this.nameMatch = nameMatch;
		this.tags = new ArrayList<String>();
	}
	
	public String getName(){
		return name;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getSvnUrl(){
		return svnurl;
	}
	
	public String getDesc(){
		return description;
	}
	
	public String getDeveloper(){
		return developer;
	}
	
	public String getDate(){
		return date;
	}
	
	public void addTag(String tag){
		this.tags.add(tag);
	}
	
	public ArrayList<String> getTags(){
		return tags;
	}
	
	public void print(){
		System.out.println(name +" "+url+" "+description+" "+developer+" "+date);
	}
	
	public boolean getNameMatch(){
		return nameMatch;
	}

	@Override
	public int compareTo(ProjectObject arg0) {
		if((this.nameMatch && arg0.getNameMatch()) ||
				(!this.nameMatch && !arg0.getNameMatch())){
			return 0;
		}else if(this.nameMatch && !arg0.getNameMatch()){
			return 1;
		}else{
			return -1;
		}
	}
	
	/**
	 * Returns the SVN URL without everything after the third slash.
	 * @return
	 */
	public String getHostingSite(){
		int temp = "https://".length()+1;
		int SLASH_INDEX = svnurl.indexOf('/', temp);
		if(SLASH_INDEX >0){
			String result = svnurl.substring(0, SLASH_INDEX);
			return result;
		}else{
			return svnurl;
		}
	}
	
	public String getHomePage(){
		return url;
	}

}
