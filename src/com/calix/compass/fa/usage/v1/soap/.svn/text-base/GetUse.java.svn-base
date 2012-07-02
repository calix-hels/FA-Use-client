package com.calix.compass.fa.usage.v1.soap;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.calix.compass.fa.usage.v1.soap.data.IPDR;

public class GetUse {
	private static final SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat completeDateFormatGmt = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	static SimpleDateFormat csvFriendlyDateFormatGmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	static {
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		completeDateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		csvFriendlyDateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	private static String target = "localhost";
    private static String endpoint;
    private static Calendar startCal;
    private static Calendar endCal;
    private static String interval;
    private static String ftpHost;
    private static String ftpUser;
    private static String ftpPass;
    private static String ftpFile;

    public static void main(String[] args) throws Exception {
    	/**  
    	 * After generating client soap stubs, change
    	 *  - UsageServiceLocator.Usage_address to localhost
    	 *  - UsageSoapBindingStub._call.setTimeout(new Integer(1000*60*60*3)) and remove enclosing brackets
    	 */
		parseOptions(args);
	        
		UsageServiceLocator locator = new UsageServiceLocator();
		Usage usage = locator.getUsage(new URL("http://" + target + "/soap/services/Usage"));
		
		try {
			if (ftpHost == null) {
				System.out.println("Date,Endpoint,Total Down,Total Up,Avg Rate Down,Avg Rate Up");
			    if (endCal != null && (endCal.get(Calendar.DAY_OF_MONTH) - startCal.get(Calendar.DAY_OF_MONTH)> 2)){
				  while (startCal.getTime().compareTo(endCal.getTime()) <= 0){
                      if (endCal.get(Calendar.DAY_OF_MONTH) - startCal.get(Calendar.DAY_OF_MONTH) > 2){
                    	Calendar tmpstartCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                    	tmpstartCal.setTime(startCal.getTime());
					    Calendar tmpendCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
					    startCal.add(Calendar.DAY_OF_MONTH, 2);
					    tmpendCal.setTime(startCal.getTime());
						IPDR[] iPDRs = usage.getIPDR(endpoint, tmpstartCal, tmpendCal, interval);
                        printResult(iPDRs);
                      } else {
                    	  IPDR[] iPDRs = usage.getIPDR(endpoint, startCal, endCal, interval); 
                    	  printResult(iPDRs);
                    	  break;
                      }
				  } 
				}else{
					IPDR[] iPDRs = usage.getIPDR(endpoint, startCal, endCal, interval);
					// Process the response
					printResult(iPDRs);
				}
			} else {
				usage.ftpIPDR(endpoint, startCal, endCal, interval, ftpHost,
						ftpUser, ftpPass, ftpFile);
			}
			System.out.println("Completed");
		} catch (Exception e) {
			System.err.println("Failed to execute.  Reason: " + e);
			e.printStackTrace();
			System.exit(2);
		}
	}
    
    private static void printResult(IPDR[] iPDRs){
		// Process the response
		if (iPDRs == null) {
			return;
		}
		
		for (int i = 0; i < iPDRs.length; i++) {
			IPDR ipdr = (IPDR) iPDRs[i];
			System.out.println(csvFriendlyDateFormatGmt.format(ipdr
					.getStartTime().getTime())
					+ ","
					+ ipdr.getSubscriberID()
					+ ","
					+ ipdr.getInputOctets()
					+ ","
					+ ipdr.getOutputOctets()
					+ ","
					+ ipdr.getAvgInputRate()
					+ ","
					+ ipdr.getAvgOutputRate());
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
	        if (line.hasOption("starttime")) {
				Date startTime;
				String value = line.getOptionValue("starttime");
				try {
					startTime = dateFormatGmt.parse(value);
				} catch (Exception e) {
					throw new Exception("starttime format incorrect: " + value);
				}
				startCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				startCal.setTime(startTime);
	        }
	        if (line.hasOption("endtime")) {
				Date endTime;
				String value = line.getOptionValue("endtime");
				try {
					endTime = dateFormatGmt.parse(value);
				} catch (Exception e) {
					throw new Exception("endtime format incorrect: " + value);
				}
				endCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				endCal.setTime(endTime);
	        }
	        if (line.hasOption("interval")) {
	        	interval = line.getOptionValue("interval");
	        	if (!"daily".equalsIgnoreCase(interval)) {
	        		throw new Exception("interval can only be 'daily'");
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
			}
	    }
	}

	static Options getOptions() {
		Options options = new Options();
		options.addOption(OptionBuilder.withArgName("target")
				.hasArg()
				.withDescription("flow analyzer host name")
				.withLongOpt("target")
				.create("fa"));
		options.addOption(OptionBuilder.withArgName("endpoint")
				.hasArg()
				.withDescription("the name of the endpoint")
				.withLongOpt("name")
				.create("n"));
		options.addOption(OptionBuilder.withArgName("time")
				.hasArg()
				.withDescription("get information from this UTC time. format: 2000-12-31")
				.withLongOpt("starttime")
				.isRequired()
				.create("s"));
		options.addOption(OptionBuilder.withArgName("time")
				.hasArg()
				.withDescription("get information to this UTC time. format: 2000-12-31")
				.withLongOpt("endtime")
				.create("e"));
		options.addOption(OptionBuilder.withArgName("period")
				.hasArg()
				.withDescription("must be 'daily'")
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
		options.addOption(OptionBuilder
				.withDescription("display this message")
				.withLongOpt("help")
				.create("h"));
		return options;
	}
}
