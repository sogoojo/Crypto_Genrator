package org.etz.core;

import org.etz.ui.PleaseWaitPopupScreen;
import org.etz.ui.screens.AppScreen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;

public class BackEndProcessorEventListener extends MenuItem implements
		FieldChangeListener, Runnable {

	private String url = null;
	private AppScreen owner = null;
	private boolean failover = false;

	public BackEndProcessorEventListener(String title, String url,
			AppScreen owner, boolean failover) {
		super(title, 0, 0);
		this.owner = owner;
		this.url = url;
		this.failover = failover;
	}

	public void performAction() {
		if (url != null) {
			if (owner.isDataValid()) {
				String msg = owner.getSummary();
				int ret = Dialog.ask(Dialog.D_YES_NO, msg);
				if (ret == Dialog.YES) {
					BackEndProcessor bep = new BackEndProcessor(owner.getAction(), "Your " + owner.getActivityTitle(), owner, failover);
					PleaseWaitPopupScreen.showScreenAndWait(bep,
							"Processing...");
				}
			} else {
				Dialog.inform(owner.getInvalidDataSummary());
				owner.clearErrors();
			}
		} else {
			Dialog.inform("This transaction is not available for the pilot!");
		}

	}

	public void fieldChanged(Field field, int context) {

		performAction();
	}

	public void run() {
		// TODO Auto-generated method stub
		performAction();

	}

}
