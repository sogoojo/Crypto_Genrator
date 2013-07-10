package org.etz.lagacy;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.microedition.lcdui.DateField;

public class PopBin {

	
	 private Calendar cally;
	    private Random randi;
	    //private Keeper keepa;
	    private Date dd;
	    DateField dafe;
	    String xx, yy, mm, jj, ll, pp, hh;

	    public PopBin() {
	    }

	    public String getOTP2(String key, Date dd1) {
	        String returnVal = "";

	        dd1= new Date(new Date().getTime() + (24 * 365 * 60 * 60 * 12));
	        Date ddd = dd1;
	        //Date ddd =



	        dafe = new DateField("  \nTodays Date:\n  ", DateField.DATE_TIME);

	        System.out.println(dd1.toString() + "Entered genrating zone......" + dafe.getDate());

	        cally = Calendar.getInstance();

	        cally.setTime(dd1);
	        randi = new Random();

	        try {
	            if (key != null || (!key.equals(""))) {
	                System.out.println("key for genrating zoor valid......" + key);

	                System.out.println("Current time is ::> " + cally.DAY_OF_MONTH);
	                System.out.println("Current time is ::> " + cally.HOUR);
	                System.out.println("Current time is ::> " + cally.HOUR_OF_DAY);
	                System.out.println("Current time is ::> " + cally.MINUTE);
	                System.out.println("Current time is ::> " + cally.getTime());

	                String seeda = "" + randi.nextLong();

	                int seeda_length = seeda.length();
	                int keya = randi.nextInt();

	                System.out.println("key for genrating index first......" + key + " --- " + keya);

	                String gg = new String("" + keya);
	                keya = Integer.parseInt(gg.substring(gg.length() - 1));

	                System.out.println("keya for genratingmodified to.--- " + keya);
	                if ((seeda_length - 5) < keya) {
	                    keya = seeda_length - 5;
	                }

	                System.out.println(seeda + "key for indexing is ....." + keya + " ...seda length is  :::" + seeda_length);

	                int x = cally.get(cally.MONTH);

	                if (x < 10) {
	                    xx = "M" + x;
	                } else {
	                    xx = "" + x;
	                }


	                int y1 = cally.get(cally.MINUTE);

	                if (y1 < 10) {
	                    yy = "0" + y1;
	                } else {
	                    yy = "" + y1;
	                }

//		String xx,yy,mm,jj,ll,pp;

	                int m1 = cally.get(cally.DATE);

	                if (m1 < 10) {
	                    mm = "0" + m1;
	                } else {
	                    mm = "" + m1;
	                }


	                int h1 = cally.get(cally.HOUR);

	                if (h1 < 10) {
	                    hh = "0" + h1;
	                } else {
	                    hh = "" + h1;
	                }


	                String sub_seeda = seeda.substring(keya, (keya + 4));

	                returnVal = (key.substring(3, 4) + "" + key.substring(0, 2) + "" + yy + "" + sub_seeda + "" + mm + "" + key.substring(2, 4) + "" + hh + "" + key.substring(1, 2));
	                //returnVal = ("##"+key.substring(0,2)+""+yy+""+sub_seeda+""+mm+""+key.substring(2,4)+""+hh+""+key.substring(1,2));
	                System.out.println("Current time is ::> " + returnVal);
	            }


	        } catch (Exception ex) {
	            System.err.println("Error generating OTP is  ::> " + ex.toString());

	        }


	        return returnVal;

	    }

	    public int validatePeriod(long lastdate, Date daysDate) {
	        int y = 0;
	        //long perrosecs = 1209600 * 1000;  //14 days in millisecondsseconds.
	        long perrosecs = (86400 * 1000) * 14;  //14 days in millisecondsseconds.
	        System.out.println("MILLISECONDS FOR 14DAYS IS ::> " + perrosecs);

	        if (((daysDate).getTime() - lastdate) >= perrosecs) {
	            System.out.println("READY FOR UPDATE :::>" + lastdate);
	            y = 1;
	        } else {
	            y = 0;
	        }


	        return y;
	    }
}
