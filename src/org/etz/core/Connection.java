package org.etz.core;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLANInfo;

public class Connection {
	
		public static final String USE_DEVICE_SIDE_STRING = getConnectionParams();
		public static String platform = System.getProperty("microedition.platform");

	/*	public static String MTN_APN = USE_DEVICE_SIDE_STRING + ";apn=web.gprs.mtnnigeria.net;"+"tunnelauthusername=web;tunnelauthpassword=web";
		public static String ZAIN_APN = USE_DEVICE_SIDE_STRING + ";apn=internet.ng.zain.com;" + "tunnelauthusername=web;tunnelauthpassword=web";
		public static String GLO_APN = USE_DEVICE_SIDE_STRING + ";apn=glowap;"+ "tunnelauthusername=wap;tunnelauthpassword=wap";		
		*/
		
		public static String VIRGIN = USE_DEVICE_SIDE_STRING + ";apn=goto.virginmobile.uk;"+"tunnelauthusername=user;tunnelauthpassword=";
		//orangeinternet
		public static String ORANGE = USE_DEVICE_SIDE_STRING + ";apn=orangeinternet;"+"tunnelauthusername=;tunnelauthpassword=";
		public static String O2 = USE_DEVICE_SIDE_STRING + ";apn=mobile.o2.co.uk;"+"tunnelauthusername=web;tunnelauthpassword=web";
		public static String VODAFONE = USE_DEVICE_SIDE_STRING + ";apn=internet;"+"tunnelauthusername=web;tunnelauthpassword=web";
		//general.t-mobile.uk
		public static String TMOBILE = USE_DEVICE_SIDE_STRING + ";apn=general.t-mobile.uk;"+"tunnelauthusername=;tunnelauthpassword=";
		public static String getAPN(String not_used) {
			String network = RadioInfo.getCurrentNetworkName();
			if (USE_DEVICE_SIDE_STRING.equals(";deviceside=true")) {
				if (network.toLowerCase().indexOf("VIRGIN".toLowerCase()) > 0) {
					return VIRGIN;
				} else if (network.toLowerCase().equals("ORANGE".toLowerCase())) {
					return ORANGE;
				} else if (network.toLowerCase().equals("O2".toLowerCase())) {
					return O2;
					
				}else if (network.toLowerCase().equals("VODAFONE".toLowerCase())) {
						return VODAFONE;
						
				}else if (network.toLowerCase().equals("TMOBILE".toLowerCase())) {
							return TMOBILE;
							
					
				} else {
					return VIRGIN;
				}
			} else {
				return USE_DEVICE_SIDE_STRING;
			}
		}
		
		
		
		public String get(String msg) throws IOException {

            String url = "http://www.etranzact.net/MobileGate/MessageReceiver?";
	    
	        HttpConnection connection = (HttpConnection) Connector.open(url);

	        connection.setRequestMethod(HttpConnection.GET);
	        //connection.setRequestProperty("User-Agent", userAgent);
	        connection.setRequestProperty("Connection", "close");
	        int rc = connection.getResponseCode();
	        if (rc == HttpConnection.HTTP_OK) {
	            InputStream is = connection.openInputStream();
	            int ch;
	            StringBuffer buffer = new StringBuffer();

	            while ((ch = is.read()) != -1) {
	                buffer.append((char) ch);
	            }

	            try {
	                is.close();
	                connection.close();
	            } catch (Exception e) {
	            }
	            return buffer.toString();
	        } else {
	            try {
	                connection.close();
	            } catch (Exception e) {
	            }
	            return null;
	        }
	    }

		public static String getConnectionParams() {
			boolean TEST_MODE = false;
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
				if ((coverageStatus & CoverageInfo.COVERAGE_BIS_B) == CoverageInfo.COVERAGE_BIS_B) {
					// Have an MDS Service book and network coverage
					connectionParams = ";deviceside=false;ConnectionType=mds-public";
				} else if (record != null && (coverageStatus & 1) == 1) { // 1 =>
					// CoverageInfo.COVERAGE_DIRECT
					// for
					// compatibility
					// with < JDE4.5
					// Have network coverage and a WAP 2.0 service book record
					connectionParams = ";deviceside=true;ConnectionUID="
							+ record.getUid();
				} else if ((coverageStatus & 1) == 1) {
					// Have network coverage but no WAP 2.0 service book record
					connectionParams = ";deviceside=true";
				}
			}
			//System.out.println("Connection Params: " + connectionParams);		
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
	}


