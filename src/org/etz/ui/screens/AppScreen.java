//#preprocessor
package org.etz.ui.screens;

import org.etz.core.BackEndProcessorEventListener;
import org.etz.core.FieldValidator;
import org.etz.core.StoreManager;
import org.etz.core.Validate;
import org.etz.core.NVPair;
import org.etz.lagacy.LegacyEncoding;

import com.blackberry.toolkit.ui.ForegroundManager;
import com.blackberry.toolkit.ui.component.BorderedEditField;
import com.blackberry.toolkit.ui.component.BorderedPasswordField;
import com.blackberry.toolkit.ui.component.ListStyleButtonField;
import com.blackberry.toolkit.ui.container.CurvedSideFieldManager;
import com.blackberry.toolkit.ui.container.HorizontalListStyleButtonSet;
 
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font; 
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager; 
import net.rim.device.api.ui.Ui; 
import net.rim.device.api.ui.component.BasicEditField; 
import net.rim.device.api.ui.component.Dialog; 
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
 
import net.rim.device.api.ui.container.MainScreen;
 

//#ifndef VER_4.5.0
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

//#endif

public abstract class AppScreen extends MainScreen implements Validate {
	// Border _menuBorder;

	private ObjectChoiceField ocfAccounts;
	private ObjectChoiceField ocfAccountTypes;
	Font _menuFont;
	protected BorderedPasswordField pPIN;
	protected BorderedEditField bAmount;
	protected BorderedEditField bPhoneNumber;
	ForegroundManager fm;
	CurvedSideFieldManager cv;
	FieldValidator fv;
	protected Bitmap _caret = Bitmap.getBitmapResource("menu-bullet.png");

	public AppScreen() {
		this(0);
	}

	public boolean onClose() {
		// TODO Auto-generated method stub
		super.onClose();
		setDirty(false);
		return true;
	}

	protected void makeMenu(Menu menu, int context) {
		// #ifndef VER_4.6.1 | VER_4.6.0 | VER_4.5.0 | VER_4.2.1 | VER_4.2.0
		// menu.setBorder(_menuBorder);
		// menu.setBackground(_menuBackground);
		// #endif

		// menu.setFont(_menuFont);
		super.makeMenu(menu, context);

	}

	public AppScreen(long style) {
		super(style | NO_VERTICAL_SCROLL | USE_ALL_HEIGHT);
		fm = new ForegroundManager();
		cv = new CurvedSideFieldManager();
		fv = new FieldValidator();

	}
	public String getScrambledPin(String pin)
	{
		LegacyEncoding enc = new LegacyEncoding();
		String scramPin = enc.scramblePin(pin);
		return scramPin;
		
	}

	public void setBackgroundTitle(String title) {
		Bitmap bmp = Bitmap.getBitmapResource("title-bar.png");
		TitleLabel lf = new TitleLabel(bmp, title, LabelField.FIELD_BOTTOM);
		Font f = Font.getDefault().derive(Font.PLAIN, 4, Ui.UNITS_pt);
		lf.setText(title);
		lf.setHeight(bmp.getHeight());
		// lf.setBackground(BackgroundFactory.createBitmapBackground(bmp));
		lf.setFont(f);
		lf.setPosition(0);
		super.setTitle(lf);
	}

	public void setTitle(String title) {

		setBackgroundTitle(title);

	}

	public boolean isDataValid() {
		if (pPIN != null) {
			fv.checkLenght("PIN ", (BasicEditField) pPIN, 4,
					FieldValidator.EXACT);
		}
		if (bPhoneNumber != null) {
			fv.checkLenght("Phone # ", bPhoneNumber, 10,
					FieldValidator.GREATER_THAN);
		}
		if (bAmount != null) {
			fv.checkMoney(bAmount);
		}
		if (fv.isValid()) {
			return super.isDataValid();
		} else
			return false;

	}

	public abstract String getActivityTitle();

	public abstract String getSummary();

	public abstract void handleResponse(Object responseData);

	protected abstract void clearFields();

	public abstract String getAction();

	public String getInvalidDataSummary() {
		return fv.getErrorSummary();
	}

	public void clearErrors() {
		fv.clear();
	}
	
	public void setupPIN(Manager parent, String title,
			BorderedPasswordField pinField) {
		if (title == null)
			parent.add(new LabelField("PIN: "));
		else
			parent.add(new LabelField(title + ": "));
		if (pinField == null) {
			pPIN = new BorderedPasswordField();
			parent.add(pPIN);
		} else {

			parent.add(pinField);
		}
	}

	public void setupPIN(Manager parent) {
		setupPIN(parent, null, null);
	}
	
	public String getAccountName()
	{
		String cardAlias =StoreManager.getCardName();
		return cardAlias;
	}
	
	public void pickAccount(Manager parent)
	{
		parent.add(new LabelField("Select Card to use"));
		ocfAccounts = new ObjectChoiceField("", accounts);
		parent.add(ocfAccounts);
	}    
	
	private Object[] accounts = {
			new NVPair("Main",getAccountName())
			 
	};
	
	public void setupAccountType(Manager parent)
	{
		parent.add(new LabelField("Select Account Type"));
		ocfAccountTypes = new ObjectChoiceField("", accountTypes);
		parent.add(ocfAccountTypes);
	}
	
	private Object[] accountTypes= {
			new NVPair("CA", "Current"),
			new NVPair("SA", "Savings")
	};

	public void setupAmount(Manager parent) {
		parent.add(new LabelField("Amount"));
		bAmount = new BorderedEditField(BasicEditField.FILTER_NUMERIC);
		parent.add(bAmount);
	}
	
	public String getSelectedAccountType()
	{
		String voucher = ((NVPair)accountTypes[ocfAccountTypes.getSelectedIndex()]).getKey();
		return voucher;
	}

	public void setupAccountNumber(Manager parent) {
		parent.add(new LabelField("Account #"));
		parent.add(new LabelField(StoreManager.getPhoneNumber()));
		parent.add(new SeparatorField());
	}

	public void setupPhoneNumber(Manager parent) {
		parent.add(new LabelField("Phone Number"));
		bPhoneNumber = new BorderedEditField(BasicEditField.FILTER_NUMERIC);
		parent.add(bPhoneNumber);
	}

	public void setupActionButton(Manager parent, String title, String url,	AppScreen owner, boolean failover) {
		ListStyleButtonField btnWithdraw = new ListStyleButtonField(title,
				DrawStyle.HCENTER);

		BackEndProcessorEventListener listener = new BackEndProcessorEventListener(
				title, url, owner, failover);
		btnWithdraw.setChangeListener(listener);

		HorizontalListStyleButtonSet btnSet = new HorizontalListStyleButtonSet();
		btnSet.add(btnWithdraw);
		parent.add(btnSet);

		((MainScreen) owner).addMenuItem(listener);
	}

	public void setupActionButton(Manager parent, String title, String url,
			AppScreen owner) {
		setupActionButton(parent, title, url, owner, false);
	}

	protected boolean askQuit() {
		int resp = Dialog
				.ask(Dialog.D_YES_NO, "Do you want to quit ?");
		return resp == Dialog.YES;

	}

	private final class TitleLabel extends LabelField {

		private int prefHeight;

		private Bitmap bg;

		public TitleLabel(Bitmap bg, String title, long style) {

			super(title, style);
			this.bg = bg;
		}

		public void setPosition(int position) {
			int adv = this.getFont().getAdvance(this.getText());
			int pos = Display.getWidth() - adv;
			super.setPosition(pos);
		}

		protected void layout(int width, int height) {
			// TODO Auto-generated method stub
			int prefWidth = Display.getWidth();
			super.layout(prefWidth, prefHeight);
			setExtent(prefWidth, prefHeight);
		}

		protected void setHeight(int height) {
			prefHeight = height;
		}

		protected void paintBackground(Graphics graphics) {
			// TODO Auto-generated method stub
			if (null != bg) {
				graphics.drawBitmap(0, 0, bg.getWidth(), bg.getHeight(), bg, 0,
						0);
			}

			super.paintBackground(graphics);
		}

		protected void paint(Graphics g) {
			// TODO Auto-generated method stub
			g.setColor(Color.BLACK);
			super.paint(g);
		}

	}

}
