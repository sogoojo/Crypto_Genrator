package org.etz.ui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Field;
import java.lang.Throwable;
import java.lang.RuntimeException;

import com.blackberry.toolkit.ui.component.ProgressAnimationField;
import com.blackberry.toolkit.ui.container.EvenlySpacedHorizontalFieldManager;

public class PleaseWaitPopupScreen extends PopupScreen {

    //statics ------------------------------------------------------------------

    private AnimatedGIFField _ourAnimation = null;
    private LabelField _ourLabelField = null;

    
    private PleaseWaitPopupScreen(String text) {
        super(new VerticalFieldManager(VerticalFieldManager.VERTICAL_SCROLL | VerticalFieldManager.VERTICAL_SCROLLBAR));
     //   GIFEncodedImage ourAnimation = (GIFEncodedImage) GIFEncodedImage.getEncodedImageResource("cycle.agif");
    //    _ourAnimation = new AnimatedGIFField(ourAnimation, Field.FIELD_HCENTER);
    //    this.add(_ourAnimation);
        EvenlySpacedHorizontalFieldManager spinners = new EvenlySpacedHorizontalFieldManager( USE_ALL_WIDTH );
        addSpinner( spinners, new ProgressAnimationField( Bitmap.getBitmapResource( "spinner.png" ), 5, Field.FIELD_HCENTER ) );
        this.add(spinners);
        _ourLabelField = new LabelField(text, Field.FIELD_HCENTER);
        this.add(_ourLabelField);
    }
    
    

    private void addSpinner( Manager parent, Field spinner )
    {
        spinner.setMargin( 15, 15, 15, 15 );
        parent.add( spinner );
    }
    public static void showScreenAndWait(final Runnable runThis, String text) {
        final PleaseWaitPopupScreen thisScreen = new PleaseWaitPopupScreen(text);
        Thread threadToRun = new Thread() {
            public void run() {
                // First, display this screen
                UiApplication.getUiApplication().invokeLater(new Runnable() {
                    public void run() {
                        UiApplication.getUiApplication().pushScreen(thisScreen);
                    }
                });
                // Now run the code that must be executed in the Background
                try {
                    runThis.run();
                } catch (Throwable t) {
                    t.printStackTrace();
                    throw new RuntimeException("Exception detected while waiting: " + t.toString());
                }
                // Now dismiss this screen
                UiApplication.getUiApplication().invokeLater(new Runnable() {
                    public void run() {
                        UiApplication.getUiApplication().popScreen(thisScreen);
                    }
                });
            }
        };
        threadToRun.start();
    }

}



