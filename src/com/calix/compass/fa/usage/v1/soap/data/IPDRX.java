/**
 * IPDRX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap.data;

public class IPDRX  implements java.io.Serializable {
    private long seqNum;

    private java.lang.String subscriberID;

    private java.util.Calendar startTime;

    private java.util.Calendar endTime;

    private java.lang.String toDim;

    private double inputOctets;

    private double outputOctets;

    private double avgInputRate;

    private double avgOutputRate;

    private double maxInputRate;

    private double maxOutputRate;

    private java.util.Calendar creationTime;

    public IPDRX() {
    }

    public IPDRX(
           long seqNum,
           java.lang.String subscriberID,
           java.util.Calendar startTime,
           java.util.Calendar endTime,
           java.lang.String toDim,
           double inputOctets,
           double outputOctets,
           double avgInputRate,
           double avgOutputRate,
           double maxInputRate,
           double maxOutputRate,
           java.util.Calendar creationTime) {
           this.seqNum = seqNum;
           this.subscriberID = subscriberID;
           this.startTime = startTime;
           this.endTime = endTime;
           this.toDim = toDim;
           this.inputOctets = inputOctets;
           this.outputOctets = outputOctets;
           this.avgInputRate = avgInputRate;
           this.avgOutputRate = avgOutputRate;
           this.maxInputRate = maxInputRate;
           this.maxOutputRate = maxOutputRate;
           this.creationTime = creationTime;
    }


    /**
     * Gets the seqNum value for this IPDRX.
     * 
     * @return seqNum
     */
    public long getSeqNum() {
        return seqNum;
    }


    /**
     * Sets the seqNum value for this IPDRX.
     * 
     * @param seqNum
     */
    public void setSeqNum(long seqNum) {
        this.seqNum = seqNum;
    }


    /**
     * Gets the subscriberID value for this IPDRX.
     * 
     * @return subscriberID
     */
    public java.lang.String getSubscriberID() {
        return subscriberID;
    }


    /**
     * Sets the subscriberID value for this IPDRX.
     * 
     * @param subscriberID
     */
    public void setSubscriberID(java.lang.String subscriberID) {
        this.subscriberID = subscriberID;
    }


    /**
     * Gets the startTime value for this IPDRX.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this IPDRX.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the endTime value for this IPDRX.
     * 
     * @return endTime
     */
    public java.util.Calendar getEndTime() {
        return endTime;
    }


    /**
     * Sets the endTime value for this IPDRX.
     * 
     * @param endTime
     */
    public void setEndTime(java.util.Calendar endTime) {
        this.endTime = endTime;
    }


    /**
     * Gets the toDim value for this IPDRX.
     * 
     * @return toDim
     */
    public java.lang.String getToDim() {
        return toDim;
    }


    /**
     * Sets the toDim value for this IPDRX.
     * 
     * @param toDim
     */
    public void setToDim(java.lang.String toDim) {
        this.toDim = toDim;
    }


    /**
     * Gets the inputOctets value for this IPDRX.
     * 
     * @return inputOctets
     */
    public double getInputOctets() {
        return inputOctets;
    }


    /**
     * Sets the inputOctets value for this IPDRX.
     * 
     * @param inputOctets
     */
    public void setInputOctets(double inputOctets) {
        this.inputOctets = inputOctets;
    }


    /**
     * Gets the outputOctets value for this IPDRX.
     * 
     * @return outputOctets
     */
    public double getOutputOctets() {
        return outputOctets;
    }


    /**
     * Sets the outputOctets value for this IPDRX.
     * 
     * @param outputOctets
     */
    public void setOutputOctets(double outputOctets) {
        this.outputOctets = outputOctets;
    }


    /**
     * Gets the avgInputRate value for this IPDRX.
     * 
     * @return avgInputRate
     */
    public double getAvgInputRate() {
        return avgInputRate;
    }


    /**
     * Sets the avgInputRate value for this IPDRX.
     * 
     * @param avgInputRate
     */
    public void setAvgInputRate(double avgInputRate) {
        this.avgInputRate = avgInputRate;
    }


    /**
     * Gets the avgOutputRate value for this IPDRX.
     * 
     * @return avgOutputRate
     */
    public double getAvgOutputRate() {
        return avgOutputRate;
    }


    /**
     * Sets the avgOutputRate value for this IPDRX.
     * 
     * @param avgOutputRate
     */
    public void setAvgOutputRate(double avgOutputRate) {
        this.avgOutputRate = avgOutputRate;
    }


    /**
     * Gets the maxInputRate value for this IPDRX.
     * 
     * @return maxInputRate
     */
    public double getMaxInputRate() {
        return maxInputRate;
    }


    /**
     * Sets the maxInputRate value for this IPDRX.
     * 
     * @param maxInputRate
     */
    public void setMaxInputRate(double maxInputRate) {
        this.maxInputRate = maxInputRate;
    }


    /**
     * Gets the maxOutputRate value for this IPDRX.
     * 
     * @return maxOutputRate
     */
    public double getMaxOutputRate() {
        return maxOutputRate;
    }


    /**
     * Sets the maxOutputRate value for this IPDRX.
     * 
     * @param maxOutputRate
     */
    public void setMaxOutputRate(double maxOutputRate) {
        this.maxOutputRate = maxOutputRate;
    }


    /**
     * Gets the creationTime value for this IPDRX.
     * 
     * @return creationTime
     */
    public java.util.Calendar getCreationTime() {
        return creationTime;
    }


    /**
     * Sets the creationTime value for this IPDRX.
     * 
     * @param creationTime
     */
    public void setCreationTime(java.util.Calendar creationTime) {
        this.creationTime = creationTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IPDRX)) return false;
        IPDRX other = (IPDRX) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.seqNum == other.getSeqNum() &&
            ((this.subscriberID==null && other.getSubscriberID()==null) || 
             (this.subscriberID!=null &&
              this.subscriberID.equals(other.getSubscriberID()))) &&
            ((this.startTime==null && other.getStartTime()==null) || 
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.endTime==null && other.getEndTime()==null) || 
             (this.endTime!=null &&
              this.endTime.equals(other.getEndTime()))) &&
            ((this.toDim==null && other.getToDim()==null) || 
             (this.toDim!=null &&
              this.toDim.equals(other.getToDim()))) &&
            this.inputOctets == other.getInputOctets() &&
            this.outputOctets == other.getOutputOctets() &&
            this.avgInputRate == other.getAvgInputRate() &&
            this.avgOutputRate == other.getAvgOutputRate() &&
            this.maxInputRate == other.getMaxInputRate() &&
            this.maxOutputRate == other.getMaxOutputRate() &&
            ((this.creationTime==null && other.getCreationTime()==null) || 
             (this.creationTime!=null &&
              this.creationTime.equals(other.getCreationTime())));
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
        _hashCode += new Long(getSeqNum()).hashCode();
        if (getSubscriberID() != null) {
            _hashCode += getSubscriberID().hashCode();
        }
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getEndTime() != null) {
            _hashCode += getEndTime().hashCode();
        }
        if (getToDim() != null) {
            _hashCode += getToDim().hashCode();
        }
        _hashCode += new Double(getInputOctets()).hashCode();
        _hashCode += new Double(getOutputOctets()).hashCode();
        _hashCode += new Double(getAvgInputRate()).hashCode();
        _hashCode += new Double(getAvgOutputRate()).hashCode();
        _hashCode += new Double(getMaxInputRate()).hashCode();
        _hashCode += new Double(getMaxOutputRate()).hashCode();
        if (getCreationTime() != null) {
            _hashCode += getCreationTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IPDRX.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "IPDRX"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seqNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "seqNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "subscriberID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "startTime"));
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
        elemField.setFieldName("toDim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "toDim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("maxInputRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "maxInputRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxOutputRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "maxOutputRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "creationTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
