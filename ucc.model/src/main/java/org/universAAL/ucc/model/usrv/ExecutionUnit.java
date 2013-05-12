//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.12 at 11:35:50 AM CEST 
//


package org.universAAL.ucc.model.usrv;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deploymentUnit" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="configFiles" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="spaceStartLevel" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deploymentUnit",
    "configFiles",
    "spaceStartLevel"
})
@XmlRootElement(name = "executionUnit")
public class ExecutionUnit
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object deploymentUnit;
    @XmlElement(required = true)
    protected Object configFiles;
    protected Object spaceStartLevel;

    /**
     * Gets the value of the deploymentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDeploymentUnit() {
        return deploymentUnit;
    }

    /**
     * Sets the value of the deploymentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDeploymentUnit(Object value) {
        this.deploymentUnit = value;
    }

    public boolean isSetDeploymentUnit() {
        return (this.deploymentUnit!= null);
    }

    /**
     * Gets the value of the configFiles property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getConfigFiles() {
        return configFiles;
    }

    /**
     * Sets the value of the configFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setConfigFiles(Object value) {
        this.configFiles = value;
    }

    public boolean isSetConfigFiles() {
        return (this.configFiles!= null);
    }

    /**
     * Gets the value of the spaceStartLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSpaceStartLevel() {
        return spaceStartLevel;
    }

    /**
     * Sets the value of the spaceStartLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSpaceStartLevel(Object value) {
        this.spaceStartLevel = value;
    }

    public boolean isSetSpaceStartLevel() {
        return (this.spaceStartLevel!= null);
    }

}
