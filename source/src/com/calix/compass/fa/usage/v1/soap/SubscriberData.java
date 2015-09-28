package com.calix.compass.fa.usage.v1.soap;

import java.util.Date;


public class SubscriberData {
	private String subscriberId;
	private Date startTime;
	private double inputOctets;
	private double outputOctets;
	private double maxInputRate;
	private double maxOutputRate;
	private String dimension = "";
	private String mappingType = "";

	public SubscriberData(String subscriberId, Date startTime,
			double inputOctets, double outputOctets, double maxInputRate,
			double maxOutputRate, String dimension, String mappingType) {
		this.subscriberId = subscriberId;
		this.startTime = startTime;
		this.inputOctets = inputOctets;
		this.outputOctets = outputOctets;
		this.maxInputRate = maxInputRate;
		this.maxOutputRate = maxOutputRate;
		this.dimension = dimension;
		this.mappingType = mappingType;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}



	public double getInputOctets() {
		return inputOctets;
	}

	public void setInputOctets(double inputOctets) {
		this.inputOctets = inputOctets;
	}

	public double getOutputOctets() {
		return outputOctets;
	}

	public void setOutputOctets(double outputOctets) {
		this.outputOctets = outputOctets;
	}

	public double getMaxInputRate() {
		return maxInputRate;
	}

	public void setMaxInputRate(double maxInputRate) {
			this.maxInputRate = maxInputRate;
	}

	public double getMaxOutputRate() {
		return maxOutputRate;
	}

	public void setMaxOutputRate(double maxOutputRate) {
			this.maxOutputRate = maxOutputRate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getMappingType() {
		return mappingType;
	}

	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	

}
