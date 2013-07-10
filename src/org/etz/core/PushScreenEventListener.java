package org.etz.core;

import org.etz.ui.screens.MainMenuScreen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class PushScreenEventListener implements FieldChangeListener {

	MainScreen toPush = null;
	public PushScreenEventListener(MainScreen toPush)
	{
		this.toPush = toPush;
		
	}
	public void fieldChanged(Field field, int context) {
		UiApplication.getUiApplication().pushScreen(toPush);
		
	}

}
