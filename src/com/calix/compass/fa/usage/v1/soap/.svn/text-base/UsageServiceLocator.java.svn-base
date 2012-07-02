/**
 * UsageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap;

public class UsageServiceLocator extends org.apache.axis.client.Service implements com.calix.compass.fa.usage.v1.soap.UsageService {

    public UsageServiceLocator() {
    }


    public UsageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UsageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Usage
    private java.lang.String Usage_address = "http://localhost:8080/soap/services/Usage";

    public java.lang.String getUsageAddress() {
        return Usage_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UsageWSDDServiceName = "Usage";

    public java.lang.String getUsageWSDDServiceName() {
        return UsageWSDDServiceName;
    }

    public void setUsageWSDDServiceName(java.lang.String name) {
        UsageWSDDServiceName = name;
    }

    public com.calix.compass.fa.usage.v1.soap.Usage getUsage() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Usage_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUsage(endpoint);
    }

    public com.calix.compass.fa.usage.v1.soap.Usage getUsage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.calix.compass.fa.usage.v1.soap.UsageSoapBindingStub _stub = new com.calix.compass.fa.usage.v1.soap.UsageSoapBindingStub(portAddress, this);
            _stub.setPortName(getUsageWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUsageEndpointAddress(java.lang.String address) {
        Usage_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.calix.compass.fa.usage.v1.soap.Usage.class.isAssignableFrom(serviceEndpointInterface)) {
                com.calix.compass.fa.usage.v1.soap.UsageSoapBindingStub _stub = new com.calix.compass.fa.usage.v1.soap.UsageSoapBindingStub(new java.net.URL(Usage_address), this);
                _stub.setPortName(getUsageWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Usage".equals(inputPortName)) {
            return getUsage();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.v1.usage.fa.compass.calix.com", "UsageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.v1.usage.fa.compass.calix.com", "Usage"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Usage".equals(portName)) {
            setUsageEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
