package org.etz.ui.screens;

import org.etz.core.Security;
import org.etz.core.StoreManager;
import org.etz.core.model.PayloadModel;
import org.etz.lagacy.LegacyEncoding;

import net.rim.device.api.ui.UiApplication;

public class MiniStatementScreen extends AppScreen {

	public MiniStatementScreen() {
		super();
		setTitle("Mini Statement");
		pickAccount(cv);
		setupAccountType(cv);
		setupPIN(cv);
		fm.add(cv);
		setupActionButton(fm, "Mini Statement", "", this);
		add(fm);

	}

	protected void clearFields() {

		super.pPIN.setText(null);
	}

	public String getAction() {
		   LegacyEncoding lgc = new LegacyEncoding();
		   String scramPin = lgc.scramblePin(pPIN.getText());
		   String payload = "L" + " "+ "Default"+ " "+ getSelectedAccountType()+ " " + scramPin;
		   PayloadModel.setData(payload);
		   return payload;
	}

	public String getActivityTitle() {
		// TODO Auto-generated method stub
		return "last 5 transactions";
	}

	

	public String getSummary() {
		// TODO Auto-generated method stub
		return "Would you like to retrieve your last 5 transactions?";
	}

	public void handleResponse(Object responseData) {
		// TODO Auto-generated method stub
		clearFields();
		UiApplication.getUiApplication().popScreen(this);

	}

	public boolean onClose() {
		clearFields();
		setDirty(false);
		return super.onClose();
	}

}
