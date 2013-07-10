
package com.blackberry.toolkit.ui.component;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.PasswordEditField;

import net.rim.device.api.ui.decor.BorderFactory;


public class BorderedPasswordField extends PasswordEditField {

	public BorderedPasswordField() {
		// TODO Auto-generated constructor stub
		super("", "", 4, FILTER_NUMERIC);

        XYEdges xy = new XYEdges(10, 10, 10, 10);
		setBorder(BorderFactory.createRoundedBorder(xy));

	}











}
