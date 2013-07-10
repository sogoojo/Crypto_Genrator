//#preprocess
package com.blackberry.toolkit.ui.component;

import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
//#ifndef VER_4.5.0
import net.rim.device.api.ui.decor.BorderFactory;
//#endif



public class BorderedEditField extends EditField {

	public BorderedEditField() {
		this("", "", 50, 0);
	}

	public BorderedEditField(long style) {
		this("", "", 50, style);
	}

	public BorderedEditField(String label, String initialValue) {
		this(label, initialValue, 50, 0);
	}

	public BorderedEditField(String label, String initialValue,
			int maxNumChars, long style) {
		super(label, initialValue, maxNumChars, style
				| BorderedEditField.NO_NEWLINE);
		//#ifndef VER_4.5.0
		XYEdges xy = new XYEdges(10, 10, 10, 10);
		setBorder(BorderFactory.createRoundedBorder(xy));
		//#endif
		
	}

}