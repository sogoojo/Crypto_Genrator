package org.etz.ui;

import javax.microedition.pim.Contact;

import org.etz.core.StoreManager;
import net.rim.blackberry.api.menuitem.ApplicationMenuItem;
import net.rim.device.api.ui.component.Dialog;

public class EtzSystemMenu extends ApplicationMenuItem {

	public EtzSystemMenu(int order) {
		super(20);
	}

	// TODO Auto-generated constructor stub}

	public EtzSystemMenu() {
		this(0);
		// TODO Auto-generated constructor stub
	}
	
	public Object run(Object context)
	{
		Contact recipient;
		if(context instanceof Contact)
		{
			recipient = (Contact) context;
			if (0 < recipient.countValues(Contact.TEL))
			{
				String phone = recipient.getString(Contact.TEL, 0);
				if(StoreManager.getRegisteredStatus().booleanValue() && StoreManager.getVerifiedStatus().booleanValue())
				{
					//do nothing really
				}
				else{
					Dialog.alert("After doing nothing");
				}
			}
		}
		return null;
	}

	/*public Object run2(Object context) {
		Contact recipient;
		// Dialog.alert("Sending Money");
		// return null;
		if (context instanceof Contact) {
			recipient = (Contact) context;
			if (0 < recipient.countValues(Contact.TEL)) {
				String phone = recipient.getString(Contact.TEL, 0);
				if (StoreManager.getRegisteredStatus().booleanValue()
						&& StoreManager.getVerifiedStatus().booleanValue()) {
					UiApplication.getUiApplication().pushScreen(
							new BalanceScreen(phone));
				} else {
					Dialog.alert("You would need to register or verify first");
				}

			} else {
				Dialog.alert("No Phone number for contact");
			 
			}
		}
		return null;
	}*/

	public String toString() {
		return "Union Bank UK";
	}

}
