/**
 * Usage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap;

public interface Usage extends java.rmi.Remote {
    public com.calix.compass.fa.usage.v1.soap.data.IPDRX[] getUse(java.lang.String entityType, java.lang.String entityId, java.util.Date startTime, java.util.Date endTime, java.lang.String interval, java.lang.String dimension) throws java.rmi.RemoteException;
    public com.calix.compass.fa.usage.v1.soap.data.IPDR[] getIPDR(java.lang.String endpoint, java.util.Calendar startTime, java.util.Calendar endTime, java.lang.String interval) throws java.rmi.RemoteException;
    public void ftpUse(java.lang.String entityType, java.lang.String entityId, java.util.Date startTime, java.util.Date endTime, java.lang.String interval, java.lang.String dimension, java.lang.String hostname, java.lang.String username, java.lang.String password, java.lang.String filename) throws java.rmi.RemoteException;
    public void ftpIPDR(java.lang.String endpoint, java.util.Calendar startTime, java.util.Calendar endTime, java.lang.String interval, java.lang.String hostname, java.lang.String username, java.lang.String password, java.lang.String filename) throws java.rmi.RemoteException;
}