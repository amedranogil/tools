//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.05 at 09:10:43 PM CEST 
//


package org.universAAL.ucc.model.usrv;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for logicalCriteriaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="logicalCriteriaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="not"/>
 *     &lt;enumeration value="equal"/>
 *     &lt;enumeration value="greater"/>
 *     &lt;enumeration value="greater-equal"/>
 *     &lt;enumeration value="less"/>
 *     &lt;enumeration value="less-equal"/>
 *     &lt;enumeration value="contain"/>
 *     &lt;enumeration value="doesn-not-contain"/>
 *     &lt;enumeration value="begin"/>
 *     &lt;enumeration value="end"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "logicalCriteriaType")
@XmlEnum
public enum LogicalCriteriaType {

    @XmlEnumValue("not")
    NOT("not"),
    @XmlEnumValue("equal")
    EQUAL("equal"),
    @XmlEnumValue("greater")
    GREATER("greater"),
    @XmlEnumValue("greater-equal")
    GREATER_EQUAL("greater-equal"),
    @XmlEnumValue("less")
    LESS("less"),
    @XmlEnumValue("less-equal")
    LESS_EQUAL("less-equal"),
    @XmlEnumValue("contain")
    CONTAIN("contain"),
    @XmlEnumValue("doesn-not-contain")
    DOESN_NOT_CONTAIN("doesn-not-contain"),
    @XmlEnumValue("begin")
    BEGIN("begin"),
    @XmlEnumValue("end")
    END("end");
    private final String value;

    LogicalCriteriaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LogicalCriteriaType fromValue(String v) {
        for (LogicalCriteriaType c: LogicalCriteriaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
