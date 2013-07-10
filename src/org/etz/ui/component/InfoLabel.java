package org.etz.ui.component;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.LabelField;

public class InfoLabel extends Field {

	private static final int HPADDING = Display.getWidth() <= 320 ? 2 : 3;
	private static final int VPADDING = 1;
	private static final int INFO = 1;
	private static final int WARN = 2;
	private Bitmap _info_icon = Bitmap.getBitmapResource( "exclamation_icon.jpg" );

	private String label;
	private int type;

	public InfoLabel() {
		// TODO Auto-generated constructor stub
	}

	public InfoLabel(int type, String label, long style) {
		super(USE_ALL_WIDTH);
		this.label = label;
		this.type = type;

		// TODO Auto-generated constructor stub
	}

	protected void layout(int maxWidth, int maxHeight) {
		
		Font font = getFont();
		int width = font.getAdvance(label) + (HPADDING * 2);
		int height =  _info_icon.getHeight() + (VPADDING *2);// font.getHeight() + (VPADDING * 2);
		// Respect the maximum width and height available from our manager
		setExtent(maxWidth, Math.min(height, maxHeight));

	}

	protected void paint(Graphics g) {
		// TODO Auto-generated method stub
		int old = g.getColor();
		try{
		Font f = Font.getDefault().derive(Font.PLAIN,4,Ui.UNITS_pt);		
		g.drawBitmap(HPADDING, VPADDING, _info_icon.getWidth(), _info_icon.getHeight(), _info_icon,0, 0);
		g.setFont(f);
		g.setColor(Color.STEELBLUE);
		g.drawText(label,(HPADDING  * 2)+ _info_icon.getWidth(),3);
		}
		finally{
			g.setColor(old);
		}
		

	}
 

}
