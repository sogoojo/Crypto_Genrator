package org.etz.core;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessageListener;
import javax.wireless.messaging.TextMessage;

import org.etz.ui.EtzSystemMenu;
import org.etz.ui.screens.AppScreen;
import org.etz.ui.screens.MainMenuScreen;
import org.etz.ui.screens.RegisterScreen;


import net.rim.blackberry.api.menuitem.ApplicationMenuItemRepository;
import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

public class App extends UiApplication {

	/**
	 * @param args
	 */
	MessageConnection _mc = null;

	public App() {

		new StoreManager();
		 // Testing set and get from record store using Name	
	    
		
		//System.out.println("Picking name from the record store ");
		
	/*	if (!StoreManager.isDEKSet()) {

			StoreManager.setDEK();
		}*/
		
		if (StoreManager.getRegisteredStatus().booleanValue()) {
			if (StoreManager.getVerifiedStatus().booleanValue()) {
				pushScreen(new MainMenuScreen());
			} else {
				pushScreen(new MainMenuScreen());
			}
		} else
			pushScreen(new RegisterScreen());
			 //pushScreen(new MainMenuScreen());

	}

	// Additional code required for complete sample.

	public static void main(String[] args) {

		if (args != null && args.length > 0 && args[0].equals("autostartup")) {
			// Keep this instance around for rendering
			// Notification dialogs.
			EtzSystemMenu menuitem = new EtzSystemMenu();
			ApplicationMenuItemRepository
					.getInstance()
					.addMenuItem(
							ApplicationMenuItemRepository.MENUITEM_ADDRESSCARD_VIEW
									| ApplicationMenuItemRepository.MENUITEM_ADDRESSCARD_VIEW,
							menuitem);
			// new SMSNotifier().enterEventDispatcher();
		}

		/*
		 * else if (args != null && args.length > 0 && args[0].equals("sms")) {
		 * new SMSNotifier().enterEventDispatcher(); } else { new
		 * App().enterEventDispatcher(); }
		 */new App().enterEventDispatcher();
	}
}
