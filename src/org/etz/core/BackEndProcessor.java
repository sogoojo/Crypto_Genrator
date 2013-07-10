package org.etz.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import javax.microedition.io.HttpConnection;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

import org.etz.core.model.PayloadModel;
import org.etz.core.util.Util;
import org.etz.ui.screens.AppScreen;
import com.etz.mobile.security.*;

import net.rim.blackberry.api.browser.URLEncodedPostData;
import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

public class BackEndProcessor implements Runnable {

	String url = null;
	String msg = null;
	AppScreen owner = null;
	boolean failOver = false;
	String  MTN_APN = "mtn.apn.com";
	String  GLO_APN = "glo.apn.com";
	String  ZAIN_APN = "zain.apn.com";
	//final String radio = RadioInfo.getCurrentNetworkName();
	//final String rdn = RadioInfo.getNetworkName();
	
	/// working on this at the point

	public BackEndProcessor(String url, String msg, AppScreen owner,
			boolean failOver) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.msg = msg;
		this.owner = owner;
		this.failOver = failOver;

	}

	private String readResponse(DataInputStream is) throws IOException {
		StringBuffer sb = new StringBuffer();

		int chr;
		while ((chr = is.read()) != -1)
			sb.append((char) chr);
		return sb.toString();
	}

	private String smsSend() throws IOException {
        
		String payload = PayloadModel.getData();
        payload = payload.trim();
        
        int firstSpace = payload.indexOf(" ");
        String start  = payload.substring(0, firstSpace);
        String replacer = start.concat("*" +Util.getAppId() + " ");
        
        payload = replacer + payload.substring(firstSpace + 1, payload.length());
        
        String msg = "&msg="+"?=" + URLEncoder.encode(payload);
        String PhoneNumeber = StoreManager.getPhoneNumber();
		 
        Connection conn = new Connection();
        String apn = conn.getAPN("getapn");
        //String urlDeviceSide = "http://www.etranzact.net/MobileGate/MessageReceiver?id="+PhoneNumeber+msg+";deviceside=true;apn=web.gprs.mtnnigeria.net;"+"tunnelauthusername=web;tunnelauthpassword=web";
	
        String urlDeviceSide = "http://www.etranzact.net/MobileGate/MessageReceiver?id="+PhoneNumeber+msg+apn;
        //String url = "http://www.etranzact.net/MobileGate/MessageReceiver?id=448038001242&msg=%3f%3dSYNC*UnionMobileUK+MDEzMDMyNzAyMTU4MDAxMw%3d%3d+NDM3MzQ3NzczNDM0ODM0MzQzNA%3d%3d+MjEwMDE4MDE%3d+MTIyMDEy+UnionMobileUK+06101556";
		
        
        
		PayloadModel.setData("");
        
		HttpConnection connection = (HttpConnection) Connector.open(urlDeviceSide);
		connection.setRequestMethod(HttpConnection.GET);
	
		// connection.setRequestProperty("User-Agent", userAgent);
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
				return "An exception was thrown " + e.getMessage();
			}
			return buffer.toString();
		} else {
			try {
				connection.close();
			} catch (Exception e) {
				 
			}
			return null;
		}
	    
	
		
		
		
		
		
		// Actual method for sending sms
//		MessageConnection conn = (MessageConnection) Connector
//				.open("sms://30012");
//		// (MessageConnection)Connector.open("sms://08076666600");
//		TextMessage msgOut = (TextMessage) conn
//				.newMessage(MessageConnection.TEXT_MESSAGE);
//		msgOut.setPayloadText("RC " + url);
//		conn.send(msgOut);
//		return msg
//				+ " was sucessfully sent Oluwasogo, check your SMS Inbox for the response";
	 
	}

	private String wapSend() throws Exception {
		ServiceBook sb = ServiceBook.getSB();
		ServiceRecord[] records = sb.findRecordsByCid("WPTCP");		
		String uid = null;
		String responseMessage = "";
		HttpConnection httpConn = null;
		int responseCode = -1;
		DataInputStream is = null;

		for (int i = 0; i < records.length; i++) {
			if (records[i].isValid() && !records[i].isDisabled()) {

				if (records[i].getUid() != null
						&& records[i].getUid().length() != 0) {
					if ((records[i].getUid().toLowerCase().indexOf("wifi") == -1)
							&& (records[i].getUid().toLowerCase()
									.indexOf("mms") == -1)) {
						uid = records[i].getUid();
						break;
					}
				}
			}
		}
		if (uid != null) {
			try {
				String surl ="http://62.173.32.27:8080/rest"
								+ "url.substring(1)"+"\\;ConnectionUID=\\" + uid;
				Dialog.inform(surl);
				httpConn = (HttpConnection) Connector
						.open(surl);
				responseCode = httpConn.getResponseCode();
				if (responseCode == httpConn.HTTP_OK) {
					is = httpConn.openDataInputStream();
					responseMessage = readResponse(is);
					return responseMessage;
					// open a WAP 2 connection

				} else {
					// Consider another transport or alternative action.
					throw new Exception("failed gprs");
				}
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (httpConn != null) {
						httpConn.close();
					}

				} catch (Exception e) {
				}
				httpConn = null;
			}
		}
		return null;
	}

	private String httpSend() throws Exception {
		String responseMessage = "";
		HttpConnection httpConn = null;
		int responseCode = -1;
		DataInputStream is = null;
		responseMessage = "Your request failed!";
		try {
			// force ";deviceside=true" to make easy to try in Simulator
			httpConn = (HttpConnection) Connector
					.open(url + ";deviceside=true");
			responseCode = httpConn.getResponseCode();
			if (responseCode == httpConn.HTTP_OK) {
				is = httpConn.openDataInputStream();
				responseMessage = readResponse(is);
			}
		} catch (IOException ioe) {
			throw ioe;
			// responseMessage = "IOException: " + url + " : " + ioe.toString();
		} catch (Exception e) {
			// responseMessage = "Exception: " + url + " : " + e.toString();
			throw e;
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (httpConn != null) {
					httpConn.close();
				}

			} catch (Exception e) {
			}
			httpConn = null;
		}
		// System.out.println(responseMessage);
		// On the Simulator, this is typically too fast, so we slow it down a
		// bit

		try {
			Thread.sleep(8000);
		} catch (Exception e) {
		}
		return responseMessage;

	}

	public void run() {

		String responseMessage = null;
		boolean sent = false;
		try {
			if (failOver) {
				try {
					responseMessage = wapSend();
					responseMessage = httpSend();

					sent = true;
				} catch (Exception ex) {
					try {
						responseMessage = smsSend();
						sent = true;
					} catch (Exception e) {
						sent = false;
					}
				}
			} else {
				responseMessage = smsSend();
				sent = true;
			}

		} catch (IOException io) {
			sent = false;
			//Dialog.inform(io.getMessage());
		}

		final String textString = responseMessage;
		final String prev = msg;
		final boolean fsent = sent;
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				if (fsent) {
					Dialog.inform(textString);
					owner.handleResponse(textString);
				} else {
					Dialog.inform(prev + "failed, Pls try again later");
				}

			}
		});

	}
}
