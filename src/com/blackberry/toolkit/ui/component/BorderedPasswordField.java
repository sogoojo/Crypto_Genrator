//#preprocess
package com.blackberry.toolkit.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.PasswordEditField;
//#ifndef VER_4.5.0
import net.rim.device.api.ui.decor.BorderFactory;
//#endif

public class BorderedPasswordField extends PasswordEditField {

	public BorderedPasswordField() {
		// TODO Auto-generated constructor stub
		super("", "", 4, FILTER_NUMERIC);
		//#ifndef VER_4.5.0
        XYEdges xy = new XYEdges(10, 10, 10, 10);
		setBorder(BorderFactory.createRoundedBorder(xy));
		//#endif
	}
	//#ifdef VER_4.5.0
	public void paint(Graphics g) {
    
        int oldColor = g.getColor();
        g.setColor(0x00000000);
        g.drawRect(0, 0, getWidth() + 1, getHeight() + 1);
        g.setColor(oldColor);
        super.paint(g);

    }
    //#endif
}
