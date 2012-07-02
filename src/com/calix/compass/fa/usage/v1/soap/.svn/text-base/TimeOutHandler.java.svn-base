package com.calix.compass.fa.usage.v1.soap;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.utils.StringUtils;

/**@author dxu */
public class TimeOutHandler extends BasicHandler {

	private static final long serialVersionUID = -2722012430574852215L;

	/* Callback method to be invoked by the container when the client initiates a SOAP request. */
	public void invoke(MessageContext messageContext) throws AxisFault {   
        // Now parse the timeout value from client-config.wsdd    
        String timeout=(String)this.getOption("timeout");   
        if(StringUtils.isEmpty(timeout)){   
            timeout="60000"; // Time out is 1 minute by default.   
        }   
        messageContext.setTimeout(Integer.parseInt(timeout));   
    }   

}
