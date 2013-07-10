package com.blackberry.toolkit.ui.container;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class CurvedSideFieldManager extends VerticalFieldManager {

	private static final int MARGIN = 15;
	
	public CurvedSideFieldManager()
	{
		super(NO_VERTICAL_SCROLL | USE_ALL_WIDTH );
		setPadding(5, 5, 5, 5);
		setMargin(5,5,5,5);
	}
	public  CurvedSideFieldManager(long style){
		super(style);
		}
	// TODO Auto-generated constructor stub}

	protected void sublayout(int maxWidth, int maxHeight) {
		// TODO Auto-generated method stub
		super.sublayout(maxWidth, maxHeight);
		/*	int numofChildren = getFieldCount();
		if(numofChildren > 0)
		{
			if(numofChildren == 1)
			{
				Field field = getField(0);
				field.setMargin(15, 15, 5, 5);				
			}
			else{
				Field field = getField(0);
				field.setMargin(15, 15, 5, 5);	
				for(int i = 1; i < numofChildren -1; i++)
				{
					field = getField(i);
					field.setMargin(0, 0, 5, 5);
					
				//	field.setBorder(BorderFactory.createSimpleBorder(new XYEdges(0,0,15,15)));
				}			
				
				field = getField(numofChildren -1);
				field.setPadding(0, 0, 0, 5);
			}
		}*/
	}
	protected void paintBackground( Graphics g )
    {
        int oldColor = g.getColor();
        try {
            g.setColor( 0xFFFFFF );
            //g.fillRect( 0, getVerticalScroll(), getWidth(), getHeight() );
            g.fillRoundRect(0, getVerticalScroll(), getWidth(), getHeight(), 15, 15);
        } finally {
            g.setColor( oldColor );
        }
    }

}
