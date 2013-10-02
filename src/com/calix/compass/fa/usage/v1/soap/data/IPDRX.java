/**
 * IPDRX.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.calix.compass.fa.usage.v1.soap.data;

public class IPDRX  extends com.calix.compass.fa.usage.v1.soap.data.IPDR  implements java.io.Serializable {
    private java.lang.String mappingType;

    private double maxInputRate;

    private double maxOutputRate;

    private java.lang.String toDim;

    public IPDRX() {
    }

    public IPDRX(
           double avgInputRate,
           double avgOutputRate,
           java.util.Calendar creationTime,
           java.util.Calendar endTime,
           double inputOctets,
           double outputOctets,
           long seqNum,
           java.util.Calendar startTime,
           java.lang.String subscriberID,
           java.lang.String mappingType,
           double maxInputRate,
           double maxOutputRate,
           java.lang.String toDim) {
        super(
            avgInputRate,
            avgOutputRate,
            creationTime,
            endTime,
            inputOctets,
            outputOctets,
            seqNum,
            startTime,
            subscriberID);
        this.mappingType = mappingType;
        this.maxInputRate = maxInputRate;
        this.maxOutputRate = maxOutputRate;
        this.toDim = toDim;
    }


    /**
     * Gets the mappingType value for this IPDRX.
     * 
     * @return mappingType
     */
    public java.lang.String getMappingType() {
        return mappingType;
    }


    /**
     * Sets the mappingType value for this IPDRX.
     * 
     * @param mappingType
     */
    public void setMappingType(java.lang.String mappingType) {
        this.mappingType = mappingType;
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
        _equals = super.equals(obj) && 
            ((this.mappingType==null && other.getMappingType()==null) || 
             (this.mappingType!=null &&
              this.mappingType.equals(other.getMappingType()))) &&
            this.maxInputRate == other.getMaxInputRate() &&
            this.maxOutputRate == other.getMaxOutputRate() &&
            ((this.toDim==null && other.getToDim()==null) || 
             (this.toDim!=null &&
              this.toDim.equals(other.getToDim())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getMappingType() != null) {
            _hashCode += getMappingType().hashCode();
        }
        _hashCode += new Double(getMaxInputRate()).hashCode();
        _hashCode += new Double(getMaxOutputRate()).hashCode();
        if (getToDim() != null) {
            _hashCode += getToDim().hashCode();
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
        elemField.setFieldName("mappingType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "mappingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("toDim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://data.soap.v1.usage.fa.compass.calix.com", "toDim"));
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
