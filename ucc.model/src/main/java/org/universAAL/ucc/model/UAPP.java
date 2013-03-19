package org.universAAL.ucc.model;

import java.util.ArrayList;

import org.universAAL.middleware.deploymaneger.uapp.model.*;


public class UAPP {
	private String name;
	private String appId;
	private String uappLocation;
	private int minor;
	private int major;
	private int micro;
	private String description;
	private String serviceId;
	private boolean multipart;
	private ArrayList<Part> parts;
	
	public UAPP() {
		parts = new ArrayList<Part>();
	}
	
	public UAPP(String serviceId, String appId, String name, String location, int major, int minor, int micro, String description, boolean multipart) {
		this.serviceId = serviceId;
		this.appId = appId;
		this.name = name;
		this.uappLocation = location;
		this.description = description;
		this.major = major;
		this.micro = micro;
		this.minor = minor;
		this.multipart = multipart;
		parts = new ArrayList<Part>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUappLocation() {
		return uappLocation;
	}
	public void setUappLocation(String uappLocation) {
		this.uappLocation = uappLocation;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMicro() {
		return micro;
	}
	public void setMicro(int micro) {
		this.micro = micro;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public boolean isMultipart() {
		return multipart;
	}

	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}
	
	public String getAppId()  {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ArrayList<Part> getParts() {
		return parts;
	}

	public void setParts(ArrayList<Part> parts) {
		this.parts = parts;
	}

	public void addPart(Part p) {
		if(parts != null) 
			parts.add(p);
	}
	
	public void removePart(Part p) {
		if(parts.contains(p)) {
			parts.remove(p);
		}
	}
	
	

}
