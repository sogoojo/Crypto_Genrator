package org.etz.lagacy;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.microedition.lcdui.DateField;

import org.etz.core.StoreManager;

 
import com.etz.mobile.security.AccessEncoder;
import com.etz.mobile.security.Base64Encoder;

public class LegacyEncoding {
	
	  String xx, yy, mm, jj, ll, pp, hh;
	 public String scramblePinl(String initpin) {

	        Random ra = new Random();
	        int lenta = 0;

	        System.out.println("PIN VALUES ARE :::> " + initpin);

	        String pinn = initpin;

	        String randnum = "" + ra.nextInt();
	        String juggled = "";

	        System.out.println("Scramble pin rando noooonmber is ........." + randnum);
	        if (randnum.charAt(0) == '-') {
	            System.out.println("got a negative randnum");
	            randnum = randnum.substring(1);
	        }
	        System.out.println("Scramble pin rando mnmber is ........." + randnum);
	        String hut = "" + randnum.charAt(3);
	        System.out.println("Scramble pin rando first character mnmber is ........." + hut);

	        int randynum = Integer.parseInt(hut);
	        System.out.println(hut + "Scramble pin mnmber is ........." + randynum);
	        if (randynum == 0) {
	            randynum = 1;
	        }
	        randynum = randynum % 3;
	        juggled = getJugglerText(pinn);

	        System.out.println("Pin is " + pinn + "nexted integer issss..........=" + randynum + " and jugglar text izz " + juggled);

	        //for(int p=0; p< pinn.length(); 

	        pinn = getMixedPin(pinn, juggled, randynum);

	        System.out.println("scrambled pin is------------->> " + pinn);

	        return pinn;

	    }
	 
	public String encBase64(String plain) {
		return Base64Encoder.encode(plain);
	}

	 //pin scrambling occurs here
	public String scramblePin(String initpin) {
	 
	 
		
    /*    String keygen = new AccessEncoder().getESACode((String) new Records().getData(2, "pddb"), initpin);
        System.out.println("ESA Code generated is " + keygen);
        String pinn = encrytData(keygen, keyy);
        System.out.println("pinn=" + pinn);*/
		
		// explain what happens here
		// get pdd adn pins from record store

		// String keygen = new AccessEncoder().getESACode((String) new
		// Records().getData(2, "pddb"), initpin);

		String keygen = new AccessEncoder().getESACode(StoreManager.getDEK(), initpin);
		System.out.println("ESA Code generated is " + keygen);
		String pinn = encrytData(keygen);
		return pinn;
	}
	
	
	    
 
	    static String encrytData(String keygen) {
	        String enc = "";
	         // get np  from record store
	       // String np = (String) new Records().getData(1, "scdb");
	        String np ="";
	        if (np.trim().equals("GLO")) {
	            enc = Base64Encoder.encode(keygen);
	        } else {
	            //enc = mp.encrytData(keygen, keyy);
	            enc = Base64Encoder.encode(keygen);
	        }
	        return enc;
	    }

/*	    public String encryptAccessCode(String initpin, String keyy) {
	        String pinn = encrytData(initpin, keyy);
	        return pinn;
	    }*/
	    
	    
	 
	    
	    
	    
	    public String getOTP(String key, Date dd1) {
	        String returnVal = "";

	        dd1= new Date(new Date().getTime() + (24 * 365 * 60 * 60 * 12));
	        Date ddd = dd1;
	        //Date ddd =



	        DateField dafe = new DateField("  \nTodays Date:\n  ", DateField.DATE_TIME);

	        System.out.println(dd1.toString() + "Entered genrating zone......" + dafe.getDate());

	        Calendar cally = Calendar.getInstance();

	        cally.setTime(dd1);
	        Random randi = new Random();

	        try {
	            if (key != null || (!key.equals(""))) { 
 

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
	    
	    
	    
	    
	   public String generateKey(String pin_num, String fone_num)
	    {
	    	try{
	    	  String keyy = pin_num.substring(0, 1) + "" + fone_num.substring(4, 8) + ""
              + pin_num.substring(1, 2) + "" + fone_num.substring(0, 2) + "" + pin_num.substring(2, 3)
              + "" + fone_num.substring(0, 4) + "" + pin_num.substring(1, 2) + "" + fone_num.substring(4, 6);
	    	  return keyy;
	    	}
	    	catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	return "Invalid data was sent to generateKey";
	    }
	   
	   
	    
	    private String getJugglerText(String pina) {
	        StringBuffer stb = new StringBuffer();
	        int dvalue = 0;
	        String pino = pina;
	        String gino = "";
	        System.out.println("NA HERE E DEY HAPPEN OO");

	        try {
	            dvalue = Integer.parseInt("" + pino.charAt(0)) + Integer.parseInt("" + pino.charAt(1));
	            stb.append(String.valueOf(valiAlgo(dvalue)));
	            /*	if(stb.charAt(0)== '-')
	            {
	            stb= null;
	            System.out.println("got a negative character");
	            stb = stb.append(stb.toString().substring(1));
	            }
	             */
	            dvalue = Integer.parseInt("" + pino.charAt(1)) + Integer.parseInt("" + pino.charAt(2));
	            stb.append(String.valueOf(valiAlgo(dvalue)));

	            /*	if(stb.charAt(1)== '-')
	            {
	            System.out.println("got a negative character");
	            stb = stb.substring(1);
	            }
	             */
	            dvalue = Integer.parseInt("" + pino.charAt(2)) + Integer.parseInt("" + pino.charAt(3));
	            stb.append(String.valueOf(valiAlgo(dvalue)));


	            /*	if(stb.charAt(2)== '-')
	            {
	            System.out.println("got a negative character");
	            stb = stb.substring(1);
	            }
	             */

	            dvalue = Integer.parseInt("" + pino.charAt(3)) + Integer.parseInt("" + pino.charAt(1));
	            stb.append(String.valueOf(valiAlgo(dvalue)));

	            /*	if(stb.charAt(3)== '-')
	            {
	            System.out.println("got a negative character");
	            stb = stb.append(stb.toString().substring(1));
	            }
	             */

	            System.out.println("printed out juggled characters areeee ........." + stb.toString());

	            gino = stb.toString();

	            System.out.println(" string isssssss........" + gino);

	            stb = null;
	            stb = new StringBuffer();

	            for (int i = 0; i < gino.length(); i++) {
	                if (gino.charAt(i) == '-') {
	                    char t = '+';
	                } else {
	                    stb.append(gino.charAt(i));
	                }
	            }

	            gino = stb.toString();
	            System.out.println("new final string isssssss........" + gino);

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            System.out.println("eror in jugglertext methd iss ....." + ex.toString());
	        }
	        return gino;

	    }
	    
	    public int valiAlgo(int u) {
	        int g = u;
	        if (g > 9) {
	            g = (10 - g);
	            return g;
	        }
	        return g;
	    }

	    public String getMixedPin(String pin, String jugtext, int rand) {
	        String pinne = pin;
	        String jugy = jugtext;
	        int goat = rand;
	        StringBuffer stub = new StringBuffer();
	        String mixed = "";

	        try {

	            if (rand == 1 || rand == 0) {
	                for (int i = 0; i < pin.length(); i++) {
	                    stub.append(pinne.charAt(i));
	                    stub.append(jugy.charAt(i));
	                    System.out.println("appending for rand 1 or 0.....");
	                }
	                mixed = stub.toString() + "" + rand;
	                System.out.println("for appending if its 1 or 0it will be ....." + mixed);

	                return mixed.trim();
	            }

	            if (rand == 2) {
	                stub.append(pinne.substring(0, 2));
	                stub.append(jugy.substring(0, 2));
	                stub.append(pinne.substring(2, 4));

	                stub.append(jugy.substring(2, 4));

	                System.out.println("appending for rand 2.....");

	                mixed = stub.toString() + "" + rand;
	                System.out.println("for appending if its 2 it will be ....." + mixed);

	                return mixed;
	            }

	        } catch (Exception ezt) {
	            ezt.printStackTrace();
	            System.out.println("Error in MixedPin method ..........." + ezt.toString());
	        }

	        return "";
	    }

	    public String unScramblePin(String pine) {

	        String piin = pine;

	        int pinlent = piin.length();

	        String scrambled_pin = "";

	        String original_pin = "";

	        StringBuffer stab = new StringBuffer();

	        int check_num = 0;

	        try {

	            check_num = Integer.parseInt("" + piin.charAt((pinlent - 1)));

	            scrambled_pin = piin.substring(0, (pinlent - 1));

	            //get unscrambled pin without check number



	            if (check_num == 1 || check_num == 0) {

	                for (int i = 0; i < scrambled_pin.length(); i += 2) {

	                    stab.append(scrambled_pin.charAt(i));

	                }

	                original_pin = stab.toString();

	            }



	            if (check_num == 2) {

	                stab.append(scrambled_pin.substring(0, 2));

	                stab.append(scrambled_pin.substring(4, 6));

	                original_pin = stab.toString();

	            }

	        } catch (Exception es) {
	            System.err.println("Error in unScramble method is ----> " + es.toString());
	            System.err.println("Error tarced is ----> ");
	            es.printStackTrace();
	        }

	        return original_pin;

	    }
	    
	    public String getNewATMAccessCodePin(String pin) {
	        java.util.Random generator = new java.util.Random();
	        int rand = Math.abs(generator.nextInt());

	        String value = rand + "";
	        int len = value.length();

	        if (len >= 4) {
	            value = value.substring(0, 4);
	        } else {
	            int n0 = 4 - len;
	            for (int i = 0; i < n0; i++) {
	                value = value + "" + i;
	            }
	        }


	        System.out.println("Main PIN:" + pin);
	        System.out.println("New PIN:::::::::::::::::::::::::" + value);
	        return value.trim();
	    }

	  
}

