package org.etz.ui.screens;

import org.etz.core.PushScreenEventListener;

import com.blackberry.toolkit.ui.container.ListStyleButtonSet;

public class InquirySubMenu extends AppScreen {

	public InquirySubMenu() {
		super();
		setTitle("Inquiry Sub Menu");				
		ListStyleButtonSet buttonSet = new ListStyleButtonSet();
		buttonSet.addCustom(null, _caret, "Balance").setChangeListener(new PushScreenEventListener(new BalanceScreen()));
		buttonSet.addCustom(null, _caret, "Last 5 Transactions").setChangeListener(new PushScreenEventListener(new MiniStatementScreen()));
		//buttonSet.addCustom(null, _caret, "Within a Period").setChangeListener(new PushScreenEventListener(new FullStatementScreen()));
		
		fm.add(buttonSet);
		add(fm);

	}
	protected void clearFields() {
		// TODO Auto-generated method stub

	}

	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getActivityTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	public void handleResponse(Object responseData) {
		// TODO Auto-generated method stub

	}

}
