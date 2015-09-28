/**
 * IPDR.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap.data;

public class IPDR  implements java.io.Serializable {
    private double avgInputRate;

    private double avgOutputRate;

    private java.util.Calendar creationTime;

    private java.util.Calendar endTime;

    private double inputOctets;

    private double outputOctets;

    private long seqNum;

    private java.util.Calendar startTime;

    private java.lang.String subscriberID;

    public IPDR() {
    }

    public IPDR(
           double avgInputRate,
           double avgOutputRate,
           java.util.Calendar creationTime,
           java.util.Calendar endTime,
           double inputOctets,
           double outputOctets,
           long seqNum,
           java.util.Calendar startTime,
           java.lang.String subscriberID) {
           this.avgInputRate = avgInputRate;
           this.avgOutputRate = avgOutputRate;
           this.creationTime = creationTime;
           this.endTime = endTime;
           this.inputOctets = inputOctets;
           this.outputOctets = outputOctets;
           this.seqNum = seqNum;
           this.startTime = startTime;
           this.subscriberID = subscriberID;
    }


    /**
     * Gets the avgInputRate value for this IPDR.
     * 
     * @return avgInputRate
     */
    public double getAvgInputRate() {
        return avgInputRate;
    }


    /**
     * Sets the avgInputRate value for this IPDR.
     * 
     * @param avgInputRate
     */
    public void setAvgInputRate(double avgInputRate) {
        this.avgInputRate = avgInputRate;
    }


    /**
     * Gets the avgOutputRate value for this IPDR.
     * 
     * @return avgOutputRate
     */
    public double getAvgOutputRate() {
        return avgOutputRate;
    }


    /**
     * Sets the avgOutputRate value for this IPDR.
     * 
     * @param avgOutputRate
     */
    public void setAvgOutputRate(double avgOutputRate) {
        this.avgOutputRate = avgOutputRate;
    }


    /**
     * Gets the creationTime value for this IPDR.
     * 
     * @return creationTime
     */
    public java.util.Calendar getCreationTime() {
        return creationTime;
    }


    /**
     * Sets the creationTime value for this IPDR.
     * 
     * @param creationTime
     */
    public void setCreationTime(java.util.Calendar creationTime) {
        this.creationTime = creationTime;
    }


    /**
     * Gets the endTime value for this IPDR.
     * 
     * @return endTime
     */
    public java.util.Calendar getEndTime() {
        return endTime;
    }


    /**
     * Sets the endTime value for this IPDR.
     * 
     * @param endTime
     */
    public void setEndTime(java.util.Calendar endTime) {
        this.endTime = endTime;
    }


    /**
     * Gets the inputOctets value for this IPDR.
     * 
     * @return inputOctets
     */
    public double getInputOctets() {
        return inputOctets;
    }


    /**
     * Sets the inputOctets value for this IPDR.
     * 
     * @param inputOctets
     */
    public void setInputOctets(double inputOctets) {
        this.inputOctets = inputOctets;
    }


    /**
     * Gets the outputOctets value for this IPDR.
     * 
     * @return outputOctets
     */
    public double getOutputOctets() {
        return outputOctets;
    }


    /**
     * Sets the outputOctets value for this IPDR.
     * 
     * @param outputOctets
     */
    public void setOutputOctets(double outputOctets) {
        this.outputOctets = outputOctets;
    }


    /**
     * Gets the seqNum value for this IPDR.
     * 
     * @return seqNum
     */
    public long getSeqNum() {
        return seqNum;
    }


    /**
     * Sets the seqNum value for this IPDR.
     * 
     * @param seqNum
     */
    public void setSeqNum(long seqNum) {
        this.seqNum = seqNum;
    }


    /**
     * Gets the startTime value for this IPDR.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this IPDR.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the subscriberID value for this IPDR.
     * 
     * @return subscriberID
     */
    public java.lang.String getSubscriberID() {
        return subscriberID;
    }


    /**
     * Sets the subscriberID value for this IPDR.
     * 
     * @param subscriberID
     */
    public void setSubscriberID(java.lang.String subscriberID) {
        this.subscriberID = subscriberID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IPDR)) return false;
        IPDR other = (IPDR) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.avgInputRate == other.getAvgInputRate() &&
            this.avgOutputRate == other.getAvgOutputRate() &&
            ((this.creationTime==null && other.getCreationTime()==null) || 
             (this.creationTime!=null &&
              this.creationTime.equals(other.getCreationTime()))) &&
            ((this.endTime==null && other.getEndTime()==null) || 
             (this.endTime!=null &&
              this.endTime.equals(other.getEndTime()))) &&
            this.inputOctets == other.getInputOctets() &&
            this.outputOctets == other.getOutputOctets() &&
            this.seqNum == other.getSeqNum() &&
            ((this.startTime==null && other.getStartTime()==null) || 
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.subscriberID==null && other.getSubscriberID()==null) || 
             (this.subscriberID!=null &&
              this.subscriberID.equals(other.getSubscriberID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Double(getAvgInputRate()).hashCode();
        _hashCode += new Double(getAvgOutputRate()).hashCode();
        if (getCreationTime() != null) {
            _hashCode += getCreationTime().hashCode();
        }
        if (getEndTime() != null) {
            _hashCode += getEndTime().hashCode();
        }
        _hashCode += new Double(getInputOctets()).hashCode();
        _hashCode += new Double(getOutputOctets()).hashCode();
        _hashCode += new Long(getSeqNum()).hashCode();
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getSubscriberID() != null) {
            _hashCode += getSubscriberID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IPDR.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "IPDR"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avgInputRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "avgInputRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avgOutputRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "avgOutputRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "creationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "endTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputOctets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "inputOctets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputOctets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "outputOctets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seqNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "seqNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "startTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "subscriberID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
