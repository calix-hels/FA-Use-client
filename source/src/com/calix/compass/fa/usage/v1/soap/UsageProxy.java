package com.calix.compass.fa.usage.v1.soap;

public class UsageProxy implements com.calix.compass.fa.usage.v1.soap.Usage {
  private String _endpoint = null;
  private com.calix.compass.fa.usage.v1.soap.Usage usage = null;
  
  public UsageProxy() {
    _initUsageProxy();
  }
  
  public UsageProxy(String endpoint) {
    _endpoint = endpoint;
    _initUsageProxy();
  }
  
  private void _initUsageProxy() {
    try {
      usage = (new com.calix.compass.fa.usage.v1.soap.UsageServiceLocator()).getUsage();
      if (usage != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)usage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)usage)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (usage != null)
      ((javax.xml.rpc.Stub)usage)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.calix.compass.fa.usage.v1.soap.Usage getUsage() {
    if (usage == null)
      _initUsageProxy();
    return usage;
  }
  
  public void ftpUse(java.lang.String entityType, java.lang.String entityId, java.util.Date startTime, java.util.Date endTime, java.lang.String interval, java.lang.String dimension, boolean mappingDetail, java.lang.String hostname, java.lang.String username, java.lang.String password, java.lang.String filename) throws java.rmi.RemoteException{
    if (usage == null)
      _initUsageProxy();
    usage.ftpUse(entityType, entityId, startTime, endTime, interval, dimension, mappingDetail, hostname, username, password, filename);
  }
  
  public void ftpIPDR(java.lang.String endpoint, java.util.Calendar startTime, java.util.Calendar endTime, java.lang.String interval, java.lang.String hostname, java.lang.String username, java.lang.String password, java.lang.String filename) throws java.rmi.RemoteException{
    if (usage == null)
      _initUsageProxy();
    usage.ftpIPDR(endpoint, startTime, endTime, interval, hostname, username, password, filename);
  }
  
  public com.calix.compass.fa.usage.v1.soap.data.IPDR[] getIPDR(java.lang.String endpoint, java.util.Calendar startTime, java.util.Calendar endTime, java.lang.String interval) throws java.rmi.RemoteException{
    if (usage == null)
      _initUsageProxy();
    return usage.getIPDR(endpoint, startTime, endTime, interval);
  }
  
  public com.calix.compass.fa.usage.v1.soap.data.IPDRX[] getUse(java.lang.String entityType, java.lang.String entityId, java.util.Date startTime, java.util.Date endTime, java.lang.String interval, java.lang.String dimension, boolean mappingDetail) throws java.rmi.RemoteException{
    if (usage == null)
      _initUsageProxy();
    return usage.getUse(entityType, entityId, startTime, endTime, interval, dimension, mappingDetail);
  }
  
  
}