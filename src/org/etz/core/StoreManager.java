package org.etz.core;

import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;

public class StoreManager {
	
	public static SettingsInfo _settingsdata;/* org.readycash.core.StoreManager.SettingsInfo 0xbef6af99bf90169fL  */
	
	public static PersistentObject settingsstore;

	static {
		//
		settingsstore = PersistentStore
				.getPersistentObject(0xbef6af99bf90169fL);
		synchronized (settingsstore) {
			if (settingsstore.getContents() == null) {
				settingsstore.setContents(new SettingsInfo());
				settingsstore.commit();
			}
		}
	//	_settingsdata = new SettingsInfo();
		_settingsdata = (SettingsInfo) settingsstore.getContents();
	}
	
	public static Boolean getRegisteredStatus()
	{
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo )settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s = (SettingsInfo)_settingsdata;
				return s.isRegistered();
			}
			return Boolean.FALSE;
		}
	}
	
	public static Boolean getVerifiedStatus()
	{
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo )settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s = (SettingsInfo)_settingsdata;
				return s.isVerified();
			}
			return Boolean.FALSE;
		}
	}
	
	public static void setRegisteredStatus(Boolean b)
	{
		SettingsInfo info = _settingsdata;
		info.setRegistered(b);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
		}
	}
	
	public static void setVerifiedStatus(Boolean b)
	{
		SettingsInfo info = _settingsdata;
		info.setVerified(b);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
		}
	}
	
	public static boolean isDEKSet()
	{
		return !getDEK().equalsIgnoreCase("");
	}
	
	public static void setDEK(String key)
	{
		SettingsInfo info = _settingsdata;
		//String dek = Security.buildDEK();
		info.setEncryptionKey(key);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
		}
	}
	
	
	
	public static String getDEK()
	{
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo )settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s = (SettingsInfo)_settingsdata;
				return s.getEncyptionKey();
			}
			return "";
		}
		
	}
	
	public static void setName(String Name)
	{
		SettingsInfo info = _settingsdata;
		info.setName(Name);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
			
		}
		
	}
	
	public static void setCardNumber(String cardNumber)
	{
		SettingsInfo info = _settingsdata;
		
		info.setCardNumber(cardNumber);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();			
		}	    
	}
	
	public static void setCardName(String cardName)
	{
		SettingsInfo info = _settingsdata;		
		info.setCardName(cardName);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
			
		}
	}

	public static void setPhoneNumber(String phone) {
		SettingsInfo info = _settingsdata;
		String dek = Security.buildDEK();
		info.setPhoneNumber(phone);
		_settingsdata = info;
		synchronized (settingsstore) {
			settingsstore.setContents(_settingsdata);
			settingsstore.commit();
		}
	}
	
	public static String getName()
	{
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo)settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s = (SettingsInfo)_settingsdata;
				return s.getName();
			}
			return null;
		}
	}
	
	public static String getPhoneNumber(){
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo )settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s = (SettingsInfo)_settingsdata;
				return s.getPhoneNumber();
			}
			return null;
		}
	}
	
	
	public static String getCardName()
	{
		synchronized (settingsstore) {
			 _settingsdata = (SettingsInfo )settingsstore.getContents();
			 if(!(_settingsdata == null))
			 {
				 SettingsInfo s =(SettingsInfo )_settingsdata;
				 return s.getCardName();
				 
			 }
			return null;
		}
	}
	
	public static String getCardNumber()
	{
		synchronized (settingsstore) {
			_settingsdata = (SettingsInfo )settingsstore.getContents();
			if(!(_settingsdata == null))
			{
				SettingsInfo s =(SettingsInfo)_settingsdata;
				return s.getCardNumber();
			}
			return null;
		}
	}
	
	
}
