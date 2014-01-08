package com.calix.compass.fa.usage.v1.soap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.calix.compass.fa.usage.v1.soap.data.IPDRX;
import com.calix.compass.fa.usage.v1.soap.data.IpdrComparator;

public class GetUse {
	private static final SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat dateTimeFormatGmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final DecimalFormat decimalFormat = new DecimalFormat("##.00");
	static SimpleDateFormat csvFriendlyDateFormatGmt = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	static SimpleDateFormat simpleDateFosormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final int ONE_HOUR_IN_MILLISECONDS = 1 * 3600 * 1000;
	private static final int ONE_DAY_IN_MILLISECONDS = ONE_HOUR_IN_MILLISECONDS * 24;
	private static final int DAILY_REQUEST_INTERVAL = 2;
	private static final int HOURLY_REQUEST_INTERVAL = 24;
	private static final int RAW_REQUEST_INTERVAL = 1;
	static final Calendar NOW_CAL = Calendar.getInstance(TimeZone
			.getTimeZone("GMT"));
	static boolean isHeaderPrinted = false;

	static {
		csvFriendlyDateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		dateTimeFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	private static String target = "localhost";
	private static String endpoint;
	private static Date startTime;
	private static Date endTime;
	private static String interval;
	private static String ftpHost;
	private static String ftpUser;
	private static String ftpPass;
	private static String ftpFile;
	private static String entityType;
	private static String entityId;
	private static List entityIdList = new ArrayList();
	private static List unknownEntityList = new ArrayList();
	private static String entityListFile;
	private static String dimension;
	private static String aggregate;
	private static boolean mappingDetail = false;
	private static boolean isAggr = false;
	private static IpdrComparator ipdrComparator = new IpdrComparator();
	

	public static void main(String[] args) throws Exception {
		/**
		 * After generating client soap stubs, change -
		 * UsageServiceLocator.Usage_address to localhost -
		 * UsageSoapBindingStub._call.setTimeout(new Integer(1000*60*60*3)) and
		 * remove enclosing brackets
		 */
		parseOptions(args);
		UsageServiceLocator locator = new UsageServiceLocator();
		Usage usage = locator.getUsage(new URL("https://" + target
				+ "/soap/services/Usage"));
		InstallCert.generateCert(target, 443, "changeit");

		try {
			if (ftpHost == null) {
				// TODO: Break the request in smaller chunks.
				List iPDRsList = new ArrayList();
                long startTime = System.currentTimeMillis();
				if ("daily".equalsIgnoreCase(interval)) {
					processDailyReport(usage, iPDRsList);
				} else if ("hourly".equalsIgnoreCase(interval)) {
					processHourlyReport(usage, iPDRsList);
				} else if ("monthly".equalsIgnoreCase(interval)) {
					processMonthlyReport(usage, iPDRsList);
				} else if ("raw".equalsIgnoreCase(interval)) {
					processRawReport(usage, iPDRsList);
				}
				long endTime = System.currentTimeMillis();
				long elapsedTimeInSec = (endTime - startTime) / 1000;
				System.out.println("Query execution time: " + elapsedTimeInSec + " Seconds." );
			    System.out.println("Number of items: " + iPDRsList.size());
				System.out.println("Completed");
			} else {
				usage.ftpUse(entityType, entityId, startTime, endTime,
						interval, dimension, mappingDetail, ftpHost, ftpUser, ftpPass, ftpFile);
			}
		} catch (Exception e) {
			System.err.println("Failed to execute.  Reason: " + e);
			System.exit(2);
		}
	}

	private static void processMonthlyReport(Usage usage, List iPDRsList)
			throws RemoteException {
		//TODO: get an array of entityId.
		
		if(entityIdList.size() > 0){
			Iterator it = entityIdList.iterator();
			while(it.hasNext()){
				String entityName = (String)it.next();
				try {
					getUsageIntoList(usage, iPDRsList, startTime, endTime, entityName);
				} catch (RemoteException e) {
					if (!unknownEntityList.contains(entityName)){
						System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
						unknownEntityList.add(entityName);
					}
				}
			}
		}else{
			try {
				getUsageIntoList(usage, iPDRsList, startTime, endTime, entityId);
			} catch (RemoteException e) {
				System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
			}
		}
		
		Collections.sort(iPDRsList, ipdrComparator);
		if (isAggr) {
			printAggregateResult(iPDRsList);
		} else {
			printResult(iPDRsList);
		}
	}

	private static void processHourlyReport(Usage usage, List iPDRsList)
			throws RemoteException {
		
		while (endTime != null && startTime.compareTo(endTime) <= 0) {
			long hourOffset = (endTime.getTime() - startTime.getTime())
					/ ONE_HOUR_IN_MILLISECONDS;
			if (hourOffset > HOURLY_REQUEST_INTERVAL) {
				Date tempStartTime = new Date(startTime.getTime());
				startTime.setTime(startTime.getTime() + HOURLY_REQUEST_INTERVAL
						* ONE_HOUR_IN_MILLISECONDS);
				Date tempEndTime = new Date(startTime.getTime());
				if (entityIdList.size() > 0){
					Iterator it = entityIdList.iterator();
					while(it.hasNext()){
						String entityName = (String)it.next();
						try {
							getUsageIntoList(usage, iPDRsList, tempStartTime,tempEndTime, entityName);
						} catch (RemoteException e) {
							if (!unknownEntityList.contains(entityName)){
								System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
								unknownEntityList.add(entityName);
							}
						}
					}
				}else{
					try {
						getUsageIntoList(usage, iPDRsList, tempStartTime, tempEndTime, entityId);
					} catch (RemoteException e) {
						System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
					}
				}
				
			} else {
				if (entityIdList.size() > 0){
					Iterator it = entityIdList.iterator();
					while(it.hasNext()){
						String entityName = (String)it.next();
						try {
							getUsageIntoList(usage, iPDRsList, startTime, endTime, entityName);
						} catch (RemoteException e) {
							if (!unknownEntityList.contains(entityName)){
								System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
								unknownEntityList.add(entityName);
							}
						}
					}
				}else{
					try {
						getUsageIntoList(usage, iPDRsList, startTime, endTime, entityId);
					} catch (RemoteException e) {
						System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
					}
				}
				Collections.sort(iPDRsList, ipdrComparator);
				if (isAggr) {
					printAggregateResult(iPDRsList);
				} else {
					printResult(iPDRsList);
				}
				break;
			}
		}
	}

	private static void processRawReport(Usage usage, List iPDRsList)
			throws RemoteException {
		IPDRX[] iPDRs = null;
		if ("true".equalsIgnoreCase(System.getProperty("Debug_Raw_TimeSpan", "false"))) {
            //For test purpose: time span > 1hr.
			iPDRs = usage.getUse(entityType, entityId, startTime, endTime,
					interval, dimension,mappingDetail);
			addToList(iPDRsList, iPDRs);
			printResult(iPDRsList);
		} else {
			while (endTime != null && startTime.compareTo(endTime) <= 0) {
				long rawOffset = endTime.getTime() - startTime.getTime();
				if (rawOffset > ONE_HOUR_IN_MILLISECONDS) {
					Date tempStartTime = new Date(startTime.getTime());
					startTime.setTime(startTime.getTime()
							+ RAW_REQUEST_INTERVAL * ONE_HOUR_IN_MILLISECONDS);
					Date tempEndTime = new Date(startTime.getTime());
					
					if (entityIdList.size() > 0){
						Iterator it = entityIdList.iterator();
						while(it.hasNext()){
							String entityName = (String)it.next();
							try {
								getUsageIntoList(usage, iPDRsList, tempStartTime, tempEndTime, entityName);
							} catch (RemoteException e) {
								if (!unknownEntityList.contains(entityName)){
									System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
									unknownEntityList.add(entityName);
								}
							}
						}
					}else{
						try {
							getUsageIntoList(usage, iPDRsList, tempStartTime, tempEndTime, entityId);
						} catch (RemoteException e) {
							System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
						}
					}
				} else {
					if (entityIdList.size() > 0){
						Iterator it = entityIdList.iterator();
						while(it.hasNext()){
							String entityName = (String)it.next();
							try {
								getUsageIntoList(usage, iPDRsList, startTime, endTime, entityName);
							} catch (RemoteException e) {
								if (!unknownEntityList.contains(entityName)){
									System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
									unknownEntityList.add(entityName);
								}
							}
						}
					}else{
						try {
							getUsageIntoList(usage, iPDRsList, startTime, endTime, entityId);
						} catch (RemoteException e) {
							System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
						}
					}
					Collections.sort(iPDRsList, ipdrComparator);
					printResult(iPDRsList);
					break;
				}
			}
		}

	}

	private static void processDailyReport(Usage usage, List iPDRsList)
			throws RemoteException {
		
			while (endTime != null && startTime.compareTo(endTime) <= 0) {
				// 2-day chunk as we did in R2.0.
				if (timeInterval(startTime, endTime) > DAILY_REQUEST_INTERVAL) {
					Date tempStartTime = new Date(startTime.getTime());
					startTime.setTime(startTime.getTime() + DAILY_REQUEST_INTERVAL
							* ONE_DAY_IN_MILLISECONDS);
					Date tempEndTime = new Date(startTime.getTime());
					if (entityIdList.size() > 0){
						Iterator it = entityIdList.iterator();
						while(it.hasNext()){
						    String entityName = (String)it.next();
						    try {
								getUsageIntoList(usage, iPDRsList, tempStartTime,
										tempEndTime, entityName);
							} catch (RemoteException e) {
								if (!unknownEntityList.contains(entityName)){
									System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
									unknownEntityList.add(entityName);
								}
							}
						}
					}else{
						try {
							getUsageIntoList(usage, iPDRsList, tempStartTime, tempEndTime, entityId);
						} catch (RemoteException e) {
							System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
						}
					}
					
				} else {
					if (entityIdList.size() > 0){
						Iterator it = entityIdList.iterator();
						while(it.hasNext()){
							String entityName = (String)it.next();
							try {
								getUsageIntoList(usage, iPDRsList, startTime, endTime, entityName);
							} catch (RemoteException e) {
								if (!unknownEntityList.contains(entityName)){
									System.err.println("Remote exception: endpoint \"" + entityName+ "\" does not exist.");
									unknownEntityList.add(entityName);
								}
							}
						}
					}else{
						try {
							getUsageIntoList(usage, iPDRsList, startTime, endTime, entityId);
						} catch (RemoteException e) {
							System.err.println("Remote exception: endpoint \"" + entityId+ "\" does not exist.");
						}
					}
					
					if (isAggr) {
						printAggregateResult(iPDRsList);
					} else {
						Collections.sort(iPDRsList, ipdrComparator);
						printResult(iPDRsList);
					}
					break;
				}
			}
		

	}

	
	private static void getUsageIntoList(Usage usage, List iPDRsList,
			Date startTime, Date endTime, String entityName)
			throws RemoteException {
		IPDRX[] iPDRs;
		iPDRs = usage.getUse(entityType, entityName, startTime, endTime, interval, dimension, mappingDetail);
		addToList(iPDRsList, iPDRs);
	}
	
	
	private static void addToList(List iPDRsList, IPDRX[] iPDRs) {
		if (null != iPDRs) {
			Collections.addAll(iPDRsList, iPDRs);
		}
	}

	private static int timeInterval(Date startTime, Date endTime) {
		return (int) TimeUnit.MILLISECONDS.toDays(endTime.getTime()
				- startTime.getTime());
	}

	private static void printResult(IPDRX[] iPDRs) {
		// Process the response
		if (null != iPDRs) {
			if (!isHeaderPrinted) {
				printHeader(isAggr);
			}
			for (int i = 0; i < iPDRs.length; i++) {
				IPDRX ipdrx = (IPDRX) iPDRs[i];
				StringBuffer result = new StringBuffer();
				result.append(
						csvFriendlyDateFormatGmt.format(ipdrx.getStartTime()
								.getTime()) + ",")
						.append(ipdrx.getSubscriberID() + ",")
						.append(decimalFormat.format(ipdrx.getInputOctets()) + ",")
						.append(decimalFormat.format(ipdrx.getOutputOctets()) + ",")
						.append(decimalFormat.format(ipdrx.getAvgInputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getAvgOutputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getMaxInputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getMaxOutputRate()));
				if (!StringUtils.isEmpty(dimension)) {
					result.append("," + ipdrx.getToDim());
				}
				
				if (mappingDetail){
					result.append("," + ipdrx.getMappingType());
				}
				
				System.out.println(result);
			}
		}
	}

	private static void printResult(List iPDRs) {
		// Process the response

		if (iPDRs.size() > 0) {
			if (!isHeaderPrinted) {
				printHeader(isAggr);
			}
			
			for (int i = 0; i < iPDRs.size(); i++) {
				IPDRX ipdrx = (IPDRX) iPDRs.get(i);
				StringBuffer result = new StringBuffer();
				result.append(
						csvFriendlyDateFormatGmt.format(ipdrx.getStartTime()
								.getTime()) + ",")
						.append(ipdrx.getSubscriberID() + ",")
						.append(decimalFormat.format(ipdrx.getInputOctets()) + ",")
						.append(decimalFormat.format(ipdrx.getOutputOctets()) + ",")
						.append(decimalFormat.format(ipdrx.getAvgInputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getAvgOutputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getMaxInputRate()) + ",")
						.append(decimalFormat.format(ipdrx.getMaxOutputRate()));
				if (!StringUtils.isEmpty(dimension)) {
					result.append("," + ipdrx.getToDim());
				}
				
				if (mappingDetail){
					result.append("," + ipdrx.getMappingType());
				}
				
				System.out.println(result);
			}
		}
	}

	private static void printHeader(boolean isAggr) {
	   String header = "";
	   if (isAggr){
		   header = entityType.toUpperCase()
					+ ",Total Down,Total Up,Avg Rate Down,Avg Rate Up,Max Rate Down,Max Rate Up";
	   }else{
		   header = "StartTime,"
					+ entityType.toUpperCase()
					+ ",Total Down,Total Up,Avg Rate Down,Avg Rate Up,Max Rate Down,Max Rate Up"; 
		   isHeaderPrinted = true;
	   }
	   
	   if (!StringUtils.isEmpty(dimension)) {
		   header += ",Dimension";
	   }
	   if (mappingDetail){
		   header += ",MappingType";
	   }
	   System.out.println(header);
	}

	private static int subscriberCount(String subscriberId, List iPDRs) {
		int result = 0;
		for (int i = 0; i < iPDRs.size(); ++i) {
			IPDRX ipdrx = (IPDRX) iPDRs.get(i);
			if (subscriberId.equalsIgnoreCase(ipdrx.getSubscriberID())) {
				++result;
			}
		}
		return result;
	}

	private static void printAggregateResult(List iPDRs) {
		// Process the response
		if (iPDRs == null) {
			return;
		}
        printHeader(isAggr);

		/*
		 * TODO: loop through the iPDRs array and aggregate the result based on
		 * different subscribers.
		 */

		Map subscriberDataMap = new HashMap();
		Map subscriberDataCountMap = new HashMap();
		SubscriberData subscriberData = null;

		for (int i = 0; i < iPDRs.size(); i++) {
			IPDRX ipdrx = (IPDRX) iPDRs.get(i);
			subscriberData = new SubscriberData(ipdrx.getSubscriberID(), ipdrx
					.getStartTime().getTime(), ipdrx.getInputOctets(),
					ipdrx.getOutputOctets(), ipdrx.getMaxInputRate(),
					ipdrx.getMaxOutputRate(), ipdrx.getToDim(), ipdrx.getMappingType());
			if (null == subscriberDataMap.get(ipdrx.getSubscriberID())) {
				subscriberDataMap.put(ipdrx.getSubscriberID(), subscriberData);
				subscriberDataCountMap.put(
						ipdrx.getSubscriberID(),
						new Integer(subscriberCount(ipdrx.getSubscriberID(),
								iPDRs)));
			} else {
				SubscriberData existingSubscriberData = (SubscriberData) subscriberDataMap
						.get(ipdrx.getSubscriberID());
				existingSubscriberData.setInputOctets(existingSubscriberData
						.getInputOctets() + ipdrx.getInputOctets());
				existingSubscriberData.setOutputOctets(existingSubscriberData
						.getOutputOctets() + ipdrx.getOutputOctets());
				if (ipdrx.getMaxInputRate() > existingSubscriberData
						.getMaxInputRate()) {
					existingSubscriberData.setMaxInputRate(ipdrx
							.getMaxInputRate());
				}
				if (ipdrx.getMaxOutputRate() > existingSubscriberData
						.getMaxOutputRate()) {
					existingSubscriberData.setMaxOutputRate(ipdrx
							.getMaxOutputRate());
				}
			}

		}

		Iterator iter = subscriberDataMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			SubscriberData data = (SubscriberData) entry.getValue();
			StringBuffer result = new StringBuffer();
			result.append(data.getSubscriberId() + ",")
					.append(decimalFormat.format(data.getInputOctets()) + ",")
					.append(decimalFormat.format(data.getOutputOctets()) + ",");
			Integer subscriberDataCount = (Integer) subscriberDataCountMap
					.get(data.getSubscriberId());
			double avgInputRate = data.getInputOctets() / subscriberDataCount.intValue();
			double avgOutputRate = data.getOutputOctets()
					/ subscriberDataCount.intValue();
			result.append(decimalFormat.format(avgInputRate) + ",").append(decimalFormat.format(avgOutputRate) + ",")
					.append(decimalFormat.format(data.getMaxInputRate()) + ",")
					.append(decimalFormat.format(data.getMaxOutputRate()));
			if (!StringUtils.isEmpty(dimension)) {
				result.append("," + data.getDimension());
			}
			if (mappingDetail){
				result.append("," + data.getMappingType());
			}
			System.out.println(result);
		}
	}

	protected static void parseOptions(String[] args) {
		Options options = getOptions();

		if (args.length == 0) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("GetUse", options);
			System.exit(1);
		}

		CommandLineParser parser = new GnuParser();
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption("target")) {
				target = line.getOptionValue("target");
			}
			if (line.hasOption("name")) {
				endpoint = line.getOptionValue("name");
			}
			if (line.hasOption("uname")) {
				System.setProperty("ServiceUsername",
						line.getOptionValue("uname"));
			}
			if (line.hasOption("psw")) {
				System.setProperty("ServicePassword",
						line.getOptionValue("psw"));
			}

			if (line.hasOption("interval")) {
				interval = line.getOptionValue("interval");
				if (!validateIntervalOption()) {
					throw new Exception(
							"interval can only be 'daily' or 'monthly' or 'hourly' or 'raw'.");
				}
				if (line.hasOption("starttime")) {

					String value = (String) line.getOptionValue("starttime");
					try {
						if ("hourly".equalsIgnoreCase(interval)
								|| "raw".equalsIgnoreCase(interval)) {
							startTime = dateTimeFormatGmt.parse(value);

						} else {
							startTime = dateFormatGmt.parse(value);
						}

					} catch (Exception e) {
						throw new Exception("starttime format incorrect: "
								+ value);
					}
				}
				if (line.hasOption("endtime")) {

					String value = line.getOptionValue("endtime");
					try {
						if ("hourly".equalsIgnoreCase(interval)
								|| "raw".equalsIgnoreCase(interval)) {
							endTime = dateTimeFormatGmt.parse(value);
						} else {
							endTime = dateFormatGmt.parse(value);
						}

					} catch (Exception e) {
						throw new Exception("endtime format incorrect: "
								+ value);
					}
				}
			}
			if (line.hasOption("ftphost")) {
				ftpHost = line.getOptionValue("ftphost");
			}
			if (line.hasOption("ftpuser")) {
				ftpUser = line.getOptionValue("ftpuser");
			} else if (ftpHost != null) {
				throw new Exception(
						"ftpuser is required when ftphost is specified");
			}
			if (line.hasOption("ftppassword")) {
				ftpPass = line.getOptionValue("ftppassword");
			} else if (ftpHost != null) {
				throw new Exception(
						"ftppassword is required when ftphost is specified");
			}
			if (line.hasOption("ftpfile")) {
				ftpFile = line.getOptionValue("ftpfile");
			} else if (ftpHost != null) {
				throw new Exception(
						"ftpfile is required when ftphost is specified");
			}

			if (line.hasOption("entityType")) {
				entityType = line.getOptionValue("entityType");
			}

			if (line.hasOption("entityId")) {
				entityId = line.getOptionValue("entityId");
			}
			
			if (line.hasOption("entityListFile")) {
				if (line.hasOption("entityId")){
					throw new Exception("entityListFile(-ef) cannot be used when entityId is specified.");
				}else{
					//TODO: Parse the file to extract a list of endpoint names and then insert into entityIdList.
					/*Format of the file:
						#Format of the endpoint file: list ONE endpoint on each line.
						xyz
						abc
						#comments         <- Comments line, should be skipped.
						                  <- Blank line, should be skipped.
						#damonx           <- We could comment out one or more lines.
						endpoint123 endpoint456      <- Invalid line which violates the format will be skipped. 
					 * */
					entityListFile = line.getOptionValue("entityListFile");
					String entityListFileFullName = "." + File.separatorChar + entityListFile;
					BufferedReader br = null;
					try {
						String sCurrentLine;
						br = new BufferedReader(new FileReader(entityListFileFullName));
						while ((sCurrentLine = br.readLine()) != null) {
						    if (sCurrentLine.trim().length() != 0 && !sCurrentLine.startsWith("#")){
						    	String endpoint = sCurrentLine.trim();
						    	if (!entityIdList.contains(endpoint)){
						    		entityIdList.add(endpoint);
						    	}
						    }else{
						    	System.err.println("Skipped an invalid or comment line");
						    }
						}
						if (entityIdList.size()==0){
							System.err.println("No specified endpoints");
							System.exit(1);
						}
					} catch (IOException e) {
						System.err.println("Failed to open the entity list file.");
						System.exit(1);
					} finally {
						try {
							if (br != null)
								br.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}

				
				
			}

			if (line.hasOption("dimension")) {
				dimension = line.getOptionValue("dimension");
			}

			if (line.hasOption("aggregate")) {
				isAggr = true;
			}
			
			if (line.hasOption("mappingdetail")) {
				mappingDetail = true;
			}

		} catch (Exception exp) {
			System.err.println("Failed to start.  Reason: " + exp.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("GetUse", options);
			System.exit(1);
		} finally {
			if ("true".equalsIgnoreCase(System.getProperty("Debug_GetUse",
					"false"))) {
				System.out.println("target: " + target);
				System.out.println("endpoint: " + endpoint);
				System.out.println("startTime: "
						+ csvFriendlyDateFormatGmt.format(startTime));
				System.out.println("endTime: "
						+ csvFriendlyDateFormatGmt.format(endTime));
				System.out.println("interval: " + interval);
				System.out.println("ftpHost: " + ftpHost);
				System.out.println("ftpUser: " + ftpUser);
				System.out.println("ftpPass: " + ftpPass);
				System.out.println("ftpFile: " + ftpFile);
				System.out.println("entityType: " + entityType);
				System.out.println("entityId: " + entityId);
				System.out.println("entityListFile: " + entityListFile);
				System.out.println("dimension: " + dimension);
				System.out.println("aggregate: " + aggregate);
				System.out.println("mappingdetail: " + mappingDetail);
			}
		}
	}

	private static boolean validateIntervalOption() {
		return "daily".equalsIgnoreCase(interval)
				|| "monthly".equalsIgnoreCase(interval)
				|| "hourly".equalsIgnoreCase(interval)
				|| "raw".equalsIgnoreCase(interval);
	}

	static Options getOptions() {
		Options options = new Options();
		options.addOption(OptionBuilder.withArgName("target").hasArg()
				.withDescription("flow analyze host name")
				.withLongOpt("target").create("fa"));
		options.addOption(OptionBuilder.withArgName("username").hasArg()
				.withDescription("the username of the soap service")
				.withLongOpt("username").create("uname"));
		options.addOption(OptionBuilder.withArgName("password").hasArg()
				.withDescription("the password of the soap service")
				.withLongOpt("password").create("psw"));
		options.addOption(OptionBuilder.withArgName("endpoint").hasArg()
				.withDescription("the name of the endpoint")
				.withLongOpt("name").create("n"));
		options.addOption(OptionBuilder
				.withArgName("time")
				.hasArg()
				.withDescription(
						"get information from this UTC time. format: 2000-12-31 or \"2000-12-31 12:00:00\"")
				.withLongOpt("starttime").withValueSeparator().isRequired()
				.create("s"));
		options.addOption(OptionBuilder
				.withArgName("time")
				.hasArg()
				.withDescription(
						"get information to this UTC time. format: 2000-12-31 or \"2000-12-31 12:00:00\"")
				.withLongOpt("endtime").withValueSeparator().create("e"));
		options.addOption(OptionBuilder.withArgName("period").hasArg()
				.withDescription("must be 'daily'or 'hourly' or 'monthly' or 'raw'")
				.withLongOpt("interval").isRequired().create("i"));
		options.addOption(OptionBuilder.withArgName("ftphost").hasArg()
				.withDescription("host or IP of the ftp server")
				.withLongOpt("ftphost").create("t"));
		options.addOption(OptionBuilder.withArgName("ftpuser").hasArg()
				.withDescription("ftp username").withLongOpt("ftpuser")
				.create("u"));
		options.addOption(OptionBuilder.withArgName("ftppassword").hasArg()
				.withDescription("ftp password").withLongOpt("ftppassword")
				.create("p"));
		options.addOption(OptionBuilder.withArgName("ftpfile").hasArg()
				.withDescription("ftp file name").withLongOpt("ftpfile")
				.create("f"));
		options.addOption(OptionBuilder.withArgName("entityType").hasArg()
				.withDescription("entity type").withLongOpt("entityType")
				.create("et"));
		options.addOption(OptionBuilder.withArgName("entityId").hasArg()
				.withDescription("entity id").withLongOpt("entityId")
				.create("ei"));
		options.addOption(OptionBuilder.withArgName("entityListFile").hasArg()
				.withDescription("entity list file").withLongOpt("entityListFile")
				.create("ef"));
		options.addOption(OptionBuilder.withArgName("dimension").hasArg()
				.withDescription("dimension of the data")
				.withLongOpt("dimension").create("d"));
		options.addOption(OptionBuilder
				.withArgName("aggregate")
				.withDescription(
						"aggregate subscriber's hourly|daily|monthly data")
				.withLongOpt("aggregate").create("aggr"));
		options.addOption(OptionBuilder
				.withArgName("mappingdetail")
				.withDescription(
						"show the mapping method of the endpoints")
				.withLongOpt("mappingdetail").create("map"));
		options.addOption(OptionBuilder.withDescription("display this message")
				.withLongOpt("help").create("h"));
		return options;
	}
}