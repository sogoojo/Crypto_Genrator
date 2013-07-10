package org.etz.ui.screens;

import org.etz.core.NVPair;
import org.etz.core.Security;
import org.etz.core.StoreManager;
import org.etz.core.model.PayloadModel;
import org.etz.lagacy.LegacyEncoding;

import com.blackberry.toolkit.ui.component.BorderedEditField;


import net.rim.device.api.ui.UiApplication; 
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import com.etz.mobile.security.*;


public class BalanceScreen extends AppScreen {

	 
	BorderedEditField brCheckItem;
	private BorderedEditField bPhoneNumber;
	private BorderedEditField bText;
	private EditField eField;

	public BalanceScreen() {
		super();
		setTitle("Balance");
		pickAccount(cv);
		Encryptor enc = new Encryptor("828C9E950EDEBB75FEF0C134");
		Decryptor dec = new Decryptor("828C9E950EDEBB75FEF0C134");
	    
		String plainText = "1234";
		String ciphered = enc.encrypt(plainText);
		cv.add(new LabelField("After encryption: " + ciphered));
		
		
		String deCiphered = dec.decrypt(ciphered);
		cv.add(new  LabelField("After decryption: "+ deCiphered));
		
	 
		
		setupAccountType(cv);	 
		
		// TODO Auto-generated constructor stub
		//setupAccountNumber(cv); 
		setupPIN(cv);
	
		fm.add(cv);
		
		setupActionButton(fm, "Get Balance", "", this,true);
		add(fm);

	}

	public String getSummary() {
		return "Would you like to retrieve your balance?";
	}

	public String getActivityTitle() {
		return "balance";
	}

	public void handleResponse(Object responseData) {
		clearFields();
		UiApplication.getUiApplication().popScreen(this);
		// TODO Auto-generated method stub
		
	}
	
	
	protected void clearFields() {
		// TODO Auto-generated method stub
		super.pPIN.setText("");
		
	}
	
	public boolean onClose() {
		// TODO Auto-generated method stub
		clearFields();
		setDirty(false);
		return super.onClose();
	}

	public String getAction() {
	   LegacyEncoding lgc = new LegacyEncoding();
	   String scramPin = lgc.scramblePin(pPIN.getText());
	   String payload = "B" + " "+ "Default"+ " "+ getSelectedAccountType()+ " " + scramPin;
	   PayloadModel.setData(payload);
	   return payload;
	}

	
}
