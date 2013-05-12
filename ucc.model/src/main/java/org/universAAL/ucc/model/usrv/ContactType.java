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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="organizationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="certificate" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="contactPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="webAddress" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="otherChannel" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="channelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="channelDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contactType", propOrder = {
    "organizationName",
    "certificate",
    "contactPerson",
    "streetAddress",
    "email",
    "webAddress",
    "phone",
    "otherChannel"
})
public class ContactType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected String organizationName;
    @XmlSchemaType(name = "anyURI")
    protected String certificate;
    protected String contactPerson;
    protected String streetAddress;
    @XmlElement(required = true)
    protected String email;
    @XmlSchemaType(name = "anyURI")
    protected String webAddress;
    protected String phone;
    protected ContactType.OtherChannel otherChannel;

    /**
     * Gets the value of the organizationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Sets the value of the organizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationName(String value) {
        this.organizationName = value;
    }

    public boolean isSetOrganizationName() {
        return (this.organizationName!= null);
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificate(String value) {
        this.certificate = value;
    }

    public boolean isSetCertificate() {
        return (this.certificate!= null);
    }

    /**
     * Gets the value of the contactPerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * Sets the value of the contactPerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    public boolean isSetContactPerson() {
        return (this.contactPerson!= null);
    }

    /**
     * Gets the value of the streetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the value of the streetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddress(String value) {
        this.streetAddress = value;
    }

    public boolean isSetStreetAddress() {
        return (this.streetAddress!= null);
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    public boolean isSetEmail() {
        return (this.email!= null);
    }

    /**
     * Gets the value of the webAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebAddress() {
        return webAddress;
    }

    /**
     * Sets the value of the webAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebAddress(String value) {
        this.webAddress = value;
    }

    public boolean isSetWebAddress() {
        return (this.webAddress!= null);
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    public boolean isSetPhone() {
        return (this.phone!= null);
    }

    /**
     * Gets the value of the otherChannel property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType.OtherChannel }
     *     
     */
    public ContactType.OtherChannel getOtherChannel() {
        return otherChannel;
    }

    /**
     * Sets the value of the otherChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType.OtherChannel }
     *     
     */
    public void setOtherChannel(ContactType.OtherChannel value) {
        this.otherChannel = value;
    }

    public boolean isSetOtherChannel() {
        return (this.otherChannel!= null);
    }


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
     *         &lt;element name="channelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="channelDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "channelName",
        "channelDetails"
    })
    public static class OtherChannel
        implements Serializable
    {

        private final static long serialVersionUID = 12343L;
        @XmlElement(required = true)
        protected String channelName;
        @XmlElement(required = true)
        protected String channelDetails;

        /**
         * Gets the value of the channelName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChannelName() {
            return channelName;
        }

        /**
         * Sets the value of the channelName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChannelName(String value) {
            this.channelName = value;
        }

        public boolean isSetChannelName() {
            return (this.channelName!= null);
        }

        /**
         * Gets the value of the channelDetails property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChannelDetails() {
            return channelDetails;
        }

        /**
         * Sets the value of the channelDetails property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChannelDetails(String value) {
            this.channelDetails = value;
        }

        public boolean isSetChannelDetails() {
            return (this.channelDetails!= null);
        }

    }

}
