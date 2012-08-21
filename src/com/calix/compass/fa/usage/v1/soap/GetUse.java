package com.calix.compass.fa.usage.v1.soap;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.calix.compass.fa.usage.v1.soap.data.IPDR;
import com.calix.compass.fa.usage.v1.soap.data.IPDRX;

public class GetUse {
	private static final SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dateTimeFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat csvFriendlyDateFormatGmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	static SimpleDateFormat simpleDateFosormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final int ONE_HOUR_IN_MILLISECONDS = 1 * 3600 * 1000;
	private static final int ONE_DAY_IN_MILLISECONDS = ONE_HOUR_IN_MILLISECONDS * 24;
	private static final int DAILY_REQUEST_INTERVAL = 10;
	private static final int HOURLY_REQUEST_INTERVAL = 24;
	static final Calendar NOW_CAL = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	static boolean isHeaderPrinted = false;
	
	static {
	    csvFriendlyDateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	    dateTimeFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	    dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	private static String target = "localhost";
    private static String endpoint;
    private static Calendar startCal;
    private static Calendar endCal;
    private static Date startTime;
    private static Date endTime;
    private static String interval;
    private static String ftpHost;
    private static String ftpUser;
    private static String ftpPass;
    private static String ftpFile;
    private static String entityType;
    private static String entityId;
    private static String dimension;

    public static void main(String[] args) throws Exception {
    	/**  
    	 * After generating client soap stubs, change
    	 *  - UsageServiceLocator.Usage_address to localhost
    	 *  - UsageSoapBindingStub._call.setTimeout(new Integer(1000*60*60*3)) and remove enclosing brackets
    	 */
    	parseOptions(args);
	    
		UsageServiceLocator locator = new UsageServiceLocator();
		Usage usage = locator.getUsage(new URL("https://" + target + "/soap/services/Usage"));
		InstallCert.generateCert(target, 443, "changeit");
		
		try {
			if (ftpHost == null) {
				//TODO: Break the request in smaller chunks.
				IPDR[] iPDRs = null;
        		while (endTime != null && startTime.compareTo(endTime) <= 0){
        			if ("daily".equalsIgnoreCase(interval)) {
	                    //2-day chunk as we did in R2.0.
		        		if (timeInterval(startTime, endTime) > DAILY_REQUEST_INTERVAL){
						    Date tempStartTime = new Date(startTime.getTime());
						    startTime.setTime(startTime.getTime() + DAILY_REQUEST_INTERVAL  * ONE_DAY_IN_MILLISECONDS);
						    Date tempEndTime = new Date(startTime.getTime());
						    startTime.setTime(startTime.getTime() + ONE_DAY_IN_MILLISECONDS);
							iPDRs = usage.getUse(entityType, entityId, tempStartTime, tempEndTime, interval, dimension);
	                        printResult(iPDRs);
		        		} else {
		        			iPDRs = usage.getUse(entityType, entityId, startTime, endTime, interval, dimension);
		        			printResult(iPDRs);
		                    if (iPDRs == null){
		                    	 System.out.println("Completed");
		                    }
		        			return;
		        		}	
        			} else if ("hourly".equalsIgnoreCase(interval)){
	        			long hourOffset = (endTime.getTime() - startTime.getTime()) / ONE_HOUR_IN_MILLISECONDS;
	        			if ( hourOffset > HOURLY_REQUEST_INTERVAL ){
	        				
	        				Date tempStartTime = new Date(startTime.getTime());
	        				startTime.setTime(startTime.getTime() + HOURLY_REQUEST_INTERVAL * ONE_HOUR_IN_MILLISECONDS );
	        				Date tempEndTime = new Date(startTime.getTime());
							iPDRs = usage.getUse(entityType, entityId, tempStartTime, tempEndTime, interval, dimension);
	                        printResult(iPDRs);
	        			} else {
		        			iPDRs = usage.getUse(entityType, entityId, startTime, endTime, interval, dimension);
		        			printResult(iPDRs);
		                    if (iPDRs == null){
		                    	 System.out.println("Completed");
		                    }
		        			return;
	        			}
        			}
		     } 
 	         //for monthly interval, we don't have to break the request so far.
      		 iPDRs = usage.getUse(entityType, entityId, startTime, endTime, interval, dimension);
 	         printResult(iPDRs);

		}else {
				usage.ftpUse(entityType, entityId, startTime, endTime, interval, dimension, 
						ftpHost, ftpUser, ftpPass, ftpFile);
		}
	 } catch (Exception e) {
		System.err.println("Failed to execute.  Reason: " + e);
		System.exit(2);
	 }
	}
    
    private static int timeInterval(Date startTime, Date endTime){
    	return (int)TimeUnit.MILLISECONDS.toDays(endTime.getTime() - startTime.getTime());
    }
    
    private static void printResult(IPDR[] iPDRs){
		// Process the response
		if (iPDRs == null) {
			return;
		}

    	if (!isHeaderPrinted){
			String header = "StartTime," + entityType.toUpperCase() + ",Total Down,Total Up,Avg Rate Down,Avg Rate Up,Max Rate Down,Max Rate Up";
			if(!StringUtils.isEmpty(dimension)){
				header += "," + dimension;
			}
			System.out.println(header);
			isHeaderPrinted = true;
    	}
    	for (int i = 0; i < iPDRs.length; i++) {
			IPDRX ipdrx = (IPDRX) iPDRs[i];
			StringBuffer result = new StringBuffer();
			result.append(csvFriendlyDateFormatGmt.format(ipdrx.getStartTime().getTime()) + ",")
			      .append(ipdrx.getSubscriberID() + ",")
			      .append(ipdrx.getInputOctets() + ",")
			      .append(ipdrx.getOutputOctets() + ",")
                  .append(ipdrx.getAvgInputRate() + ",")
				  .append(ipdrx.getAvgOutputRate() + ",")
				  .append(ipdrx.getMaxInputRate() + ",")
				  .append(ipdrx.getMaxOutputRate());
		    if(!StringUtils.isEmpty(dimension)){
		    	result.append("," + ipdrx.getToDim() );
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
	        CommandLine line = parser.parse( options, args );
	        if (line.hasOption("target")) {
	        	target = line.getOptionValue("target");
	        }
	        if (line.hasOption("name")) {
	        	endpoint = line.getOptionValue("name");
	        }
	        if (line.hasOption("uname")){
	        	System.setProperty("ServiceUsername", line.getOptionValue("uname"));
	        }
	        if (line.hasOption("psw")){
	        	System.setProperty("ServicePassword", line.getOptionValue("psw"));
	        }
            
	        
		    if (line.hasOption("interval")) {
	        	interval = line.getOptionValue("interval");
	        	if (!"daily".equalsIgnoreCase(interval) && !"monthly".equalsIgnoreCase(interval) && !"hourly".equalsIgnoreCase(interval)) {
	        		throw new Exception("interval can only be 'daily' or 'monthly' or 'hourly'.");
	        	}
		        if (line.hasOption("starttime")) {
					
					String value = (String)line.getOptionValue("starttime");
					try {
						if("hourly".equalsIgnoreCase(interval)){
							startTime = dateTimeFormatGmt.parse(value);
							
						}else{
							startTime = dateFormatGmt.parse(value);
						}
						
					} catch (Exception e) {
						throw new Exception("starttime format incorrect: " + value);
					}
		        }
		        if (line.hasOption("endtime")) {
					
					String value = line.getOptionValue("endtime");
					try {
						if("hourly".equalsIgnoreCase(interval)){
							endTime = dateTimeFormatGmt.parse(value);
						}else{
							endTime = dateFormatGmt.parse(value);
						}
						
					} catch (Exception e) {
						throw new Exception("endtime format incorrect: " + value);
					}
		        }
	        }
	        if (line.hasOption("ftphost")) {
	        	ftpHost = line.getOptionValue("ftphost");
	        }
	        if (line.hasOption("ftpuser")) {
	        	ftpUser = line.getOptionValue("ftpuser");
	        } else if (ftpHost != null) {
	        	throw new Exception("ftpuser is required when ftphost is specified");
	        }
	        if (line.hasOption("ftppassword")) {
	        	ftpPass = line.getOptionValue("ftppassword");
	        } else if (ftpHost != null) {
	        	throw new Exception("ftppassword is required when ftphost is specified");
	        }
	        if (line.hasOption("ftpfile")) {
	        	ftpFile = line.getOptionValue("ftpfile");
	        } else if (ftpHost != null) {
	        	throw new Exception("ftpfile is required when ftphost is specified");
	        }
	        
	        if (line.hasOption("entityType")){
	        	entityType = line.getOptionValue("entityType");
	        }
	        
	        if (line.hasOption("entityId")){
	        	entityId = line.getOptionValue("entityId");
	        }
	        
	        if (line.hasOption("dimension")){
	        	dimension = line.getOptionValue("dimension");
	        }
	        
	    } catch( Exception exp ) {
	    	System.err.println("Failed to start.  Reason: " + exp.getMessage());
	    	HelpFormatter formatter = new HelpFormatter();
	    	formatter.printHelp("GetUse", options);
	    	System.exit(1);
	    } finally {
			if ("true".equalsIgnoreCase(System.getProperty("Debug_GetUse", "false"))) {
				System.out.println("target: " + target);
				System.out.println("endpoint: " + endpoint);
				System.out.println("startTime: " + csvFriendlyDateFormatGmt.format(startCal.getTime()));
				System.out.println("endTime: " + csvFriendlyDateFormatGmt.format(endCal.getTime()));
				System.out.println("interval: " + interval);
				System.out.println("ftpHost: " + ftpHost);
				System.out.println("ftpUser: " + ftpUser);
				System.out.println("ftpPass: " + ftpPass);
				System.out.println("ftpFile: " + ftpFile);
				System.out.println("entityType: " + entityType);
				System.out.println("entityId: " + entityId);
				System.out.println("dimension: " + dimension);
			}
	    }
	}
	
	static Options getOptions() {
		Options options = new Options();
		options.addOption(OptionBuilder.withArgName("target")
				.hasArg()
				.withDescription("flow analyze host name")
				.withLongOpt("target")
				.create("fa"));
		options.addOption(OptionBuilder.withArgName("username")
				.hasArg()
				.withDescription("the username of the soap service")
				.withLongOpt("username")
				.create("uname"));
		options.addOption(OptionBuilder.withArgName("password")
				.hasArg()
				.withDescription("the password of the soap service")
				.withLongOpt("password")
				.create("psw"));
		options.addOption(OptionBuilder.withArgName("endpoint")
				.hasArg()
				.withDescription("the name of the endpoint")
				.withLongOpt("name")
				.create("n"));
		options.addOption(OptionBuilder.withArgName("time")
				.hasArg()
				.withDescription("get information from this UTC time. format: 2000-12-31 or \"2000-12-31 12:00:00\"")
				.withLongOpt("starttime")
				.withValueSeparator()
				.isRequired()
				.create("s"));
		options.addOption(OptionBuilder.withArgName("time")
				.hasArg()
				.withDescription("get information to this UTC time. format: 2000-12-31 or \"2000-12-31 12:00:00\"")
				.withLongOpt("endtime")
				.withValueSeparator()
				.create("e"));
		options.addOption(OptionBuilder.withArgName("period")
				.hasArg()
				.withDescription("must be 'daily','hourly' or 'monthly'")
				.withLongOpt("interval")
				.isRequired()
				.create("i"));
		options.addOption(OptionBuilder.withArgName("ftphost")
				.hasArg()
				.withDescription("host or IP of the ftp server")
				.withLongOpt("ftphost")
				.create("t"));
		options.addOption(OptionBuilder.withArgName("ftpuser")
				.hasArg()
				.withDescription("ftp username")
				.withLongOpt("ftpuser")
				.create("u"));
		options.addOption(OptionBuilder.withArgName("ftppassword")
				.hasArg()
				.withDescription("ftp password")
				.withLongOpt("ftppassword")
				.create("p"));
		options.addOption(OptionBuilder.withArgName("ftpfile")
				.hasArg()
				.withDescription("ftp file name")
				.withLongOpt("ftpfile")
				.create("f"));
		options.addOption(OptionBuilder.withArgName("entityType")
				.hasArg()
				.withDescription("entity type")
				.withLongOpt("entityType")
				.create("et"));
		options.addOption(OptionBuilder.withArgName("entityId")
				.hasArg()
				.withDescription("entity id")
				.withLongOpt("entityId")
				.create("ei"));
		options.addOption(OptionBuilder.withArgName("dimension")
				.hasArg()
				.withDescription("dimension of the data")
				.withLongOpt("dimension")
				.create("d"));
		options.addOption(OptionBuilder
				.withDescription("display this message")
				.withLongOpt("help")
				.create("h"));
		return options;
	}
}