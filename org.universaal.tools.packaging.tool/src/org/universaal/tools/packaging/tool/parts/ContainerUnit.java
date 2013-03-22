package org.universaal.tools.packaging.tool.parts;


public class ContainerUnit {

	private Container container;

	private Embedding embedding;
	private String karafFeatures; //XML

	private Android androidPart;

	public ContainerUnit(Embedding embedding, String karafFeatures){
		this.container = Container.KARAF;
		this.embedding = embedding;
		this.karafFeatures = karafFeatures;

		this.androidPart = null;
	}

	public ContainerUnit(Android androidPart){
		this.container = Container.ANDROID;
		this.androidPart= androidPart;

		this.embedding = null;
		this.karafFeatures = null;
	}

	public ContainerUnit(Container container){
		if(container != Container.KARAF && container != Container.ANDROID)
			this.container = container;
		else
			throw new IllegalArgumentException("Please consider using proper constructor if container is Karaf or Android!");

		this.androidPart = null;
		this.embedding = null;
		this.karafFeatures = null;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public Embedding getEmbedding() {
		return embedding;
	}

	public Android getAndroidPart() {
		return androidPart;
	}

	public void setAndroidPart(Android androidPart) {
		this.androidPart = androidPart;
	}

	public String getKarafFeatures() {
		return karafFeatures;
	}

	public void setKarafFeatures(String karafFeatures) {
		this.karafFeatures = karafFeatures;
	}

	public String getXML(){

		String r = "";

		if(container != Container.KARAF && container != Container.ANDROID)
			return "<"+container.toString()+"/>";
		else{
			if(container == Container.KARAF){
				r = r.concat("<karaf>");
				r = r.concat("<embedding>"+embedding.toString()+"</embedding>");

				r = r.concat(karafFeatures); // XML generated by features-maven-plugin

				r = r.concat("</karaf>");
			}
			if(container == Container.ANDROID){
				r = r.concat("<android>");
				r = r.concat(androidPart.getXML());
				r = r.concat("</android>");
			}
		}

		return r;
	}
}