package org.universAAL.commerce.ustore.tools;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.6.8
 * 2013-06-11T10:49:36.554+02:00
 * Generated source version: 2.6.8
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
            java.util.logging.Logger.getLogger(OnlineStoreManagerService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://srv-ustore.haifa.il.ibm.com:9060/universAAL/OnlineStoreManagerService/OnlineStoreManagerService.wsdl");
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
