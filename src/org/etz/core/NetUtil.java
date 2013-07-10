package org.etz.core;

 
import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.Radio;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLANInfo;

public class NetUtil {
	
	private static String getConnectionString(){
		String userNetwork = RadioInfo.getCurrentNetworkName();
	    String connectionString="";
	    if(WLANInfo.getWLANState()==WLANInfo.WLAN_STATE_CONNECTED){
	        connectionString="&interface=wifi";
	    }   
	     else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS){
	         connectionString = "&deviceside=false";
	    }
	        else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_DIRECT)==CoverageInfo.COVERAGE_DIRECT){
	        	
	            String carrierUid =  "";        //getCarrierBIBSUid();
	            
	            if(carrierUid == null) {
	                connectionString = "&deviceside=true";
	            }
	            else{
	                 connectionString = "&deviceside=false&connectionUID="+carrierUid + "&ConnectionType=mds-public";
	                }

	            }
	     else if(CoverageInfo.getCoverageStatus() == CoverageInfo.COVERAGE_NONE)
	        {

	        }
	    return connectionString;
	    }
	
	
    public static String getConnectionParams() {
        boolean TEST_MODE = true;
        String connectionParams = "";
        if (TEST_MODE) {
            return "";
        }

        if (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED) {
            // Connected to a WIFI access point
            connectionParams = ";interface=wifi";
        } else {
            int coverageStatus = CoverageInfo.getCoverageStatus();
            ServiceRecord record = getWAP2ServiceRecord();
            if (record != null && (coverageStatus & 1) == 1) { // 1 =>
                // CoverageInfo.COVERAGE_DIRECT
                // for
                // compatibility
                // with < JDE4.5
                // Have network coverage and a WAP 2.0 service book record
                connectionParams = ";deviceside=true;ConnectionUID="
                        + record.getUid();
            } else if ((coverageStatus & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS) {
                // Have an MDS Service book and network coverage
                connectionParams = ";deviceside=false";
            } else if ((coverageStatus & 1) == 1) {
                // Have network coverage but no WAP 2.0 service book record
                connectionParams = ";deviceside=true";
            }
        }
        System.out.println("Connection Params: " + connectionParams);
        return connectionParams;
    }

    private static ServiceRecord getWAP2ServiceRecord() {
        ServiceBook sb = ServiceBook.getSB();
        ServiceRecord[] records = sb.getRecords();
   
        for (int i = 0; i < records.length; i++) {
            String cid = records[i].getCid().toLowerCase();
            String uid = records[i].getUid().toLowerCase();
            if (cid.indexOf("wptcp") != -1 && uid.indexOf("wifi") == -1
                    && uid.indexOf("mms") == -1) {
                return records[i];
            }
        }
        return null;
    }
    
    private String getAPN(String telco)
    {
    	
    	return "";
    }
	
	
	
	
}
