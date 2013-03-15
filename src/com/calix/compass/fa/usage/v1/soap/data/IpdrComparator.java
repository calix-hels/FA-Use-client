package com.calix.compass.fa.usage.v1.soap.data;

import java.util.Comparator;

public class IpdrComparator implements Comparator {

	public int compare(Object ipdr1, Object ipdr2) {
		int result = 0;
		if(ipdr1.equals(ipdr2)){
			return 0 ;
	    }
		result = ((IPDRX)ipdr1).getSubscriberID().compareTo(((IPDRX)ipdr2).getSubscriberID());
		if (result == 0) {
			result = compareStartTime((IPDRX)ipdr1, (IPDRX)ipdr2);
		}
		
		return result;
    }

    private int compareStartTime(IPDRX ipdr1, IPDRX ipdr2){
        return ipdr1.getStartTime().after(ipdr2.getStartTime()) ? 1 : 0;
    }
    
}
