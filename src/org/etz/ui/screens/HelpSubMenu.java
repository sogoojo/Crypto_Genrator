package org.etz.ui.screens;

import org.etz.core.PushScreenEventListener;

import com.blackberry.toolkit.ui.container.ListStyleButtonSet;

public class HelpSubMenu extends AppScreen {
	
	public HelpSubMenu() {
		super();
		setTitle("Help Menu");
		ListStyleButtonSet buttonSet = new ListStyleButtonSet();
		buttonSet.addCustom(null, _caret, "About");
		buttonSet.addCustom(null, _caret, "Help");
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
