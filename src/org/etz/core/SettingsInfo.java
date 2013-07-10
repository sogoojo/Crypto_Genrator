package org.etz.core;

import java.util.Vector;

import net.rim.device.api.util.Persistable;

 public class SettingsInfo implements Persistable {

	 private Vector _elements;
	 private static final int REGISTER_STATUS = 0;
	 private static final int PHONE_NUMBER = 1;	 
	 private static final int VERIFIED_STATUS = 2;
	 private static final int DEK = 3;
	 private static final int FIRST_NAME = 4;
	 private static final int NUMBER = 5;
	 private static final int CARD_NUMBER = 6;
	 private static final int CARD_NAME = 7;
	 
	 
	 public SettingsInfo (){
		 _elements = new Vector();
		 for(int i = 0 ; i < 8 ; i ++){
			 _elements.addElement(new Object());
		 }
		 _elements.setElementAt(Boolean.FALSE,REGISTER_STATUS);
		 _elements.setElementAt( Boolean.FALSE,VERIFIED_STATUS);
		 _elements.setElementAt("", PHONE_NUMBER);
		 _elements.setElementAt("", DEK);
		 _elements.setElementAt("", CARD_NUMBER);
		 _elements.setElementAt("", CARD_NAME);
		 _elements.setElementAt("", FIRST_NAME);
		 _elements.setElementAt("", NUMBER); 
		 
		 
	 }
	 
	 public Boolean isRegistered()
	 {
		return (Boolean) _elements.elementAt(REGISTER_STATUS);
	 }
	 
	 public void setRegistered(Boolean registered)
	 {
		 _elements.setElementAt( registered,REGISTER_STATUS);
	 }
	 
	 public String getPhoneNumber()
	 {
		 return (String) _elements.elementAt(PHONE_NUMBER);
	 }
	 
	  public String getCardName()
	 {
		return (String) _elements.elementAt(CARD_NAME); 
	 }
	 
	 public String getCardNumber()
	 {
		 return (String) _elements.elementAt(CARD_NUMBER);
	 }
	 
	 public String getName()
	 {
		 return (String) _elements.elementAt(FIRST_NAME); 
	 }
	 
	 public void setPhoneNumber(String phonenumber)
	 {
		 _elements.setElementAt(phonenumber, PHONE_NUMBER);
	 }
	 
	 public void setCardNumber(String cardNumber)
	 {
		 _elements.setElementAt(cardNumber, CARD_NUMBER);
	 }
	 
	 public void setCardName(String cardName)
	 {
		 _elements.setElementAt(cardName, CARD_NAME);
	 }
	 
	 
	 public void setName(String Name)
	 {
		 _elements.setElementAt(Name, FIRST_NAME);
	 }
	 
	 public Boolean isVerified()
	 {
		 return (Boolean) _elements.elementAt(VERIFIED_STATUS);
	 }
	 
	 public void setVerified(Boolean verified)
	 {
		 _elements.setElementAt( verified,VERIFIED_STATUS);
	 }
	 
	 public void setEncryptionKey(String encKey)
	 {
		 _elements.setElementAt(encKey, DEK);
	 }
	 
	 public String getEncyptionKey()
	 {
		 return (String)_elements.elementAt(DEK);
	 }
	 
}
