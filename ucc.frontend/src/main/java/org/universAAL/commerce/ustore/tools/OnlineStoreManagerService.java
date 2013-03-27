
/*
 * 
 */

package org.universAAL.commerce.ustore.tools;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.3.1
 * Wed Mar 27 12:58:11 CET 2013
 * Generated source version: 2.3.1
 * 
 */


@WebServiceClient(name = "OnlineStoreManagerService", 
                  wsdlLocation = "http://srv-ustore.haifa.il.ibm.com:9060/universAAL/OnlineStoreManagerService/OnlineStoreManagerService.wsdl",
                  targetNamespace = "http://tools.ustore.commerce.universaal.org/") 
public class OnlineStoreManagerService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://tools.ustore.commerce.universaal.org/", "OnlineStoreManagerService");
    public final static QName OnlineStoreManagerPort = new QName("http://tools.ustore.commerce.universaal.org/", "OnlineStoreManagerPort");
    static {
        URL url = null;
        try {
            url = new URL("http://srv-ustore.haifa.il.ibm.com:9060/universAAL/OnlineStoreManagerService/OnlineStoreManagerService.wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://srv-ustore.haifa.il.ibm.com:9060/universAAL/OnlineStoreManagerService/OnlineStoreManagerService.wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public OnlineStoreManagerService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public OnlineStoreManagerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OnlineStoreManagerService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public OnlineStoreManagerService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }
    public OnlineStoreManagerService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public OnlineStoreManagerService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OnlineStoreManager
     */
    @WebEndpoint(name = "OnlineStoreManagerPort")
    public OnlineStoreManager getOnlineStoreManagerPort() {
        return super.getPort(OnlineStoreManagerPort, OnlineStoreManager.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OnlineStoreManager
     */
    @WebEndpoint(name = "OnlineStoreManagerPort")
    public OnlineStoreManager getOnlineStoreManagerPort(WebServiceFeature... features) {
        return super.getPort(OnlineStoreManagerPort, OnlineStoreManager.class, features);
    }

}
