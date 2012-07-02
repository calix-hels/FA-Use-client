package com.calix.compass.fa.usage.v1.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.utils.StringUtils;

/**@author dxu */
public class AuthenticationHandler extends BasicHandler {

	private static final long serialVersionUID = 6359161518769189678L;

	public void invoke(MessageContext msgctx) throws AxisFault {
		
		String serviceUsername = System.getProperty("ServiceUsername");
		String servicePassword = System.getProperty("ServicePassword");
		if(!StringUtils.isEmpty(serviceUsername) && !StringUtils.isEmpty(servicePassword)){
			msgctx.setUsername(serviceUsername);
			msgctx.setPassword(servicePassword);
			setOption("username", serviceUsername);
	        setOption("password", servicePassword);
	        System.clearProperty("ServiceUsername");
	        System.clearProperty("ServicePassword");
		} else if(StringUtils.isEmpty((String)getOption("username")) && StringUtils.isEmpty((String)getOption("password"))){
			System.out.print("Please input your username: ");
	        String username = readLine();
	        System.out.print("Please input your password: ");
	        String password = readLine();
	        msgctx.setUsername(username);
	        msgctx.setPassword(password);
	        setOption("username", username);
	        setOption("password", password);
		} else {
			msgctx.setUsername((String)getOption("username"));
			msgctx.setPassword((String)getOption("password"));
		}
		
        /*Comments out the following code snippet if client needs to challenge the server.*/
        //System.setProperty("javax.net.ssl.keyStore", System.getProperty("user.home")+"/client.keystore");
        //System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
        //System.setProperty("javaL.net.ssl.trustStore", System.getProperty("user.home")+"/client.truststore");
        System.setProperty("javax.net.ssl.trustStore", InstallCert.CERT_FILE_PATH);
	}

	private String readLine(){
		String line = null;
		try {
			line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
		}
		return line;
	}
}
