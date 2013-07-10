package org.etz.ui.screens;

import org.etz.core.PushScreenEventListener;
 

import net.rim.device.api.system.Bitmap; 
import net.rim.device.api.ui.component.Dialog; 

import com.blackberry.toolkit.ui.ForegroundManager;
import com.blackberry.toolkit.ui.container.ListStyleButtonSet;

public class MainMenuScreen extends AppScreen {

	// private Bitmap _caret = Bitmap.getBitmapResource(
	// "chevron_right_black_15x22.png" );

	ForegroundManager foreground;

	public MainMenuScreen() {

		super();
		setTitle("Main Menu");
		foreground = new ForegroundManager();

		ListStyleButtonSet buttonSet = new ListStyleButtonSet();

		 //List of menu found on the main menu ....
		buttonSet.addCustom(Bitmap.getBitmapResource("icon-balance-32.png"),_caret, "Menu1").setChangeListener(new PushScreenEventListener(new InquirySubMenu()));
		buttonSet.addCustom(Bitmap.getBitmapResource("icon-baalance-32.png"),_caret, "Menu2").setChangeListener(new PushScreenEventListener(new InquirySubMenu()));
		buttonSet.addCustom(Bitmap.getBitmapResource("icon-balance-32.png"),_caret, "Menu3").setChangeListener(new PushScreenEventListener(new InquirySubMenu()));
		
 
     		 
		foreground.add(buttonSet);
		add(foreground);

		// TODO Auto-generated constructor stub
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

	protected void clearFields() {
		// TODO Auto-generated method stub

	}

	public boolean onClose() {
		int resp = Dialog
				.ask(Dialog.D_YES_NO, "Do you want to quit?");
		if (resp == Dialog.YES) {
			return super.onClose();
		}
		return false;

	}

	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getInvalidDataSummary() {
		// TODO Auto-generated method stub
		return null;
	}

}
