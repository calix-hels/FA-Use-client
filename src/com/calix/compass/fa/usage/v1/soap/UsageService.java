/**
 * UsageService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap;

public interface UsageService extends javax.xml.rpc.Service {
    public java.lang.String getUsageAddress();

    public com.calix.compass.fa.usage.v1.soap.Usage getUsage() throws javax.xml.rpc.ServiceException;

    public com.calix.compass.fa.usage.v1.soap.Usage getUsage(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
