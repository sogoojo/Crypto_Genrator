package org.etz.ui.screens;

import java.util.Calendar;
import java.util.Date;

import org.etz.core.Security;
import org.etz.core.StoreManager;
import org.etz.ui.component.InfoLabel;
 
import org.etz.core.NVPair;
import org.etz.core.model.PayloadModel;
import org.etz.core.util.Util;
import org.etz.lagacy.LegacyEncoding;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.component.RadioButtonField;
import net.rim.device.api.ui.component.RadioButtonGroup;
import net.rim.device.api.util.DateTimeUtilities;

import com.blackberry.toolkit.ui.component.BorderedEditField;
import com.etz.mobile.security.AccessEncoder;
 

public class RegisterScreen extends AppScreen {

	private BorderedEditField bPhoneNumber;
	private BorderedEditField bCardAlias;
	private BorderedEditField bCardNumber;
	private PasswordEditField bPin;
	private BorderedEditField bPassword;
	private BorderedEditField bexpDate;
	private DateField df;
	private ObjectChoiceField ocfNetworks;
	
	//private BorderedEditField bLastName;
	//private BorderedEditField bFirstName;
	//private BorderedEditField mMiddleMan;
	
	private RadioButtonGroup rbgSex;

	public RegisterScreen() {
		super();
		setTitle("Register");
		cv.add(new InfoLabel(0, "Please fill the form below", 0));
		
		cv.add(new LabelField("PhoneNumber :"));
		bPhoneNumber = new BorderedEditField("", "", 15,BorderedEditField.FILTER_NUMERIC);
		cv.add(bPhoneNumber);
		
		cv.add(new LabelField("Card Alias:"));
		bCardAlias = new BorderedEditField("", "", 15, BorderedEditField.FILTER_DEFAULT);
		cv.add(bCardAlias);
		
		cv.add(new LabelField("Card Number :"));
		bCardNumber = new BorderedEditField("", "", 18,BorderedEditField.FILTER_NUMERIC);
		cv.add(bCardNumber);
		
		cv.add(new LabelField("Pin :"));
		bPin = new PasswordEditField("", "", 4, BorderedEditField.FILTER_NUMERIC);
		cv.add(bPin);
		
		cv.add(new LabelField("Enter Password"));
		bPassword = new BorderedEditField("", "", 15, BorderedEditField.FILTER_DEFAULT);
		cv.add(bPassword);
		
		cv.add(new LabelField("Expiry Date- MMYYYY"));
		bexpDate = new BorderedEditField("", "", 15, BorderedEditField.FILTER_NUMERIC);
		cv.add(bexpDate);
		
		
	/*	
		cv.add(new LabelField(""Enter Expiration Date :"));
		Date d = new Date();
		df = new DateField("", d.getTime(),new SimpleDateFormat("dd-MMMM-yyyy"));
		cv.add(df);*/
		
		cv.add(new LabelField("Network: "));
		ocfNetworks = new ObjectChoiceField("", networkTypes);
		cv.add(ocfNetworks);
		
		 
		
		
		/*cv.add(new LabelField("Sex :"));
		rbgSex = new RadioButtonGroup();
		RadioButtonField rbfMale = new RadioButtonField("Male", rbgSex, true);
		RadioButtonField rbfFemale = new RadioButtonField("Female", rbgSex,	false);
		cv.add(rbfFemale);
		cv.add(rbfMale);*/
		
		
		
		fm.add(cv);
		setupActionButton(fm, "Register", "", this);
		add(fm);

	}
	
	

	public String getAction() {
		LegacyEncoding lgc = new LegacyEncoding();
	    
		
		String p_in = bPin.getText();
		String f_num = bPhoneNumber.getText();
		String cardNumber = bCardNumber.getText();
		String cardName = bCardAlias.getText();
		
		StoreManager.setPhoneNumber("234"+f_num.substring(1));
		StoreManager.setCardNumber(cardNumber);
		StoreManager.setCardName(cardName);
		
		String keyy = lgc.generateKey(p_in, f_num);	
		
		keyy = lgc.getOTP(keyy, new Date());		
		StoreManager.setDEK(keyy);
		
		p_in = lgc.scramblePin(p_in);		
		keyy = lgc.encBase64(keyy);
 
		String dateFormat = DateFormat.getInstance(DateFormat.DATE_SHORT)
		.formatLocal(df.getDate());
		String expDate = lgc.encBase64(bexpDate.getText());		
		//String expDate = lgc.encBase64("052014");
        
		String retVal = "SYNC " + keyy+ " "+ lgc.encBase64(cardNumber) + " " +p_in+ " " + expDate +" "+Util.getAppId()+ " "+ new AccessEncoder().getSystemDate();
	    PayloadModel.setData(retVal);
		return retVal;    

	}
	
	 
	private Object[] networkTypes={
			new org.etz.core.NVPair("Virgin", "Virgin"),
			new NVPair("orange", "Orange"),
			new NVPair("o2", "O2"),
			new NVPair("vodafone", "Vodafone"),
			new NVPair("t-mobile", "T-Mobile"),
			new NVPair("others", "Others")
			
	};

	public String getActivityTitle() {
		return "Register";
	}

	public String getSummary() {
		// TODO Auto-generated method stub
		String date = DateFormat.getInstance(DateFormat.DATE_SHORT)
		.formatLocal(df.getDate());
		
		StringBuffer sb = new StringBuffer();
		sb.append(Util.confirmMessage() + "\r\n");
		sb.append("Pin: " +bPin.getText() + "\r\n");
		sb.append("Phone Number: " + bPhoneNumber.getText()+ "\r\n");
		sb.append("Expiry Date: "+ bexpDate.getText()+ "\r\n");
		sb.append("Network : " + networkTypes[ocfNetworks.getSelectedIndex()]+ "\r\n");
		sb.append("Memorable Word: "  + bPassword.getText()+ "\r\n");
		sb.append("Card Alias: " + bCardAlias.getText()+"\r\n");		
		return sb.toString();
	}

	public void handleResponse(Object responseData) {
		String resp = (String) responseData;
		// if(resp == "301"){
		StoreManager.setRegisteredStatus(Boolean.TRUE);
		UiApplication.getUiApplication().popScreen(this);	 
		//UiApplication.getUiApplication().pushScreen(new VerificationScreen());
		UiApplication.getUiApplication().pushScreen(new MainMenuScreen());
 

	}

	public boolean onClose() {
		// TODO Auto-generated method stub
		clearFields();
		setDirty(false);
		if (askQuit())
			return super.onClose();
		return false;

	}

	public boolean isDataValid() {
		// TODO Auto-generated method stub
		long today = new Date().getTime();
		long bday = df.getDate();
		long diff = today - bday;
		if (diff > DateTimeUtilities.ONEYEAR * 9) {
			return true;
		}
		return true;
	}

	protected void clearFields() {
		// TODO Auto-generated method stub

	}

	public String getInvalidDataSummary() {
		// TODO Auto-generated method stub
		return "Your date of birth may be incorrect";
	}
}
