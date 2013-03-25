//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.12 at 04:29:40 PM MESZ 
//


package org.universAAL.ucc.configuration.model.configurationinstances;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.cas.merlin.aal.configuration.model.configurationinstances package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Configuration_QNAME = new QName("http://uaal.cas.de/uaalconfiguration", "configuration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.cas.merlin.aal.configuration.model.configurationinstances
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConfigOption }
     * 
     */
    public ConfigOption createConfigOption() {
        return new ConfigOption();
    }

    /**
     * Create an instance of {@link Value }
     * 
     */
    public Value createValue() {
        return new Value();
    }

    /**
     * Create an instance of {@link ConfigurationInstance }
     * 
     */
    public ConfigurationInstance createConfigurationInstance() {
        return new ConfigurationInstance();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigurationInstance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://uaal.cas.de/uaalconfiguration", name = "configuration")
    public JAXBElement<ConfigurationInstance> createConfiguration(ConfigurationInstance value) {
        return new JAXBElement<ConfigurationInstance>(_Configuration_QNAME, ConfigurationInstance.class, null, value);
    }

}
