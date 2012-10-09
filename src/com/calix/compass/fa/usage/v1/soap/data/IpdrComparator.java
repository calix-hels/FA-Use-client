package com.calix.compass.fa.usage.v1.soap.data;

import java.util.Comparator;

public class IpdrComparator implements Comparator {

	public int compare(Object ipdr1, Object ipdr2) {
		if(ipdr1.equals(ipdr2)){
			return 0 ;
	    }else if(compareStartTime((IPDR)ipdr1, (IPDR)ipdr2)){
		    return 1 ;
	    }else{
			return -1 ;
	    }
    }

    private boolean compareStartTime(IPDR ipdr1, IPDR ipdr2){
        return ipdr1.getStartTime().after(ipdr2.getStartTime());
    }
}
