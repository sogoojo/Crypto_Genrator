package com.etz.mobile.security;

import java.util.Calendar;

public class AccessEncoder {

	 String[] mmonth = null;

	  public int xor(int val1, int val2)
	  {
	    return val1 ^ val2;
	  }

	  public String isESACodeOkay(String webaccesscode, String data) {
	    String finalStr2 = "";
	    String finalStr3 = "";
	    boolean status = false;
	    webaccesscode = webaccesscode.substring(6, 7) + 
	      webaccesscode.substring(1, 2) + 
	      webaccesscode.substring(2, 3) + 
	      webaccesscode.substring(0, 1) + 
	      webaccesscode.substring(3, 4) + 
	      webaccesscode.substring(4, 5) + 
	      webaccesscode.substring(5, 6) + 
	      webaccesscode.substring(7);

	    char[] param = webaccesscode.toCharArray();

	    int i = 1; for (int j = 0; i < param.length; j += 2)
	    {
	      finalStr2 = finalStr2 + param[i];
	      finalStr3 = finalStr3 + param[j];

	      i += 2;
	    }

	    if (webaccesscode.length() == 9) {
	      finalStr3 = finalStr3 + webaccesscode.substring(8);
	    }

	    String xpin = xor(finalStr3, data.substring(3, 7));

	    if (finalStr3.equals("0001")) return finalStr3;

	    int code = Integer.parseInt("1" + data.substring(data.length() - 3));
	    int ret1 = xor(Integer.parseInt(finalStr3), code);

	    String ret = ret1+"";
	    if (ret1 == 0) xpin = "0000";
	   
	    else
	      xpin = format4(4 -  ret.length()) + ret1;
	    return xpin;
	  }

	  public String getSystemDate() {
	    Calendar mydate = Calendar.getInstance();
	    return padZero(mydate.get(2) + 1) + padZero(mydate.get(5)) + padZero(mydate.get(11)) + padZero(mydate.get(12));
	  }

	  public String validateESATimeCode2(String webaccesscode, String data, int timediff) throws Exception
	  {
	    String finalStr2 = "";
	    String finalStr3 = "";
	    boolean status = false;
	    String now = getSystemDate();
	    int timenow = Integer.parseInt(now) + timediff;

	    System.out.println("ESACodeTime:" + timenow);
	    System.out.println("ServerTime:" + now);

	    int esacode = xor(Integer.parseInt(webaccesscode), timenow);
	    System.out.println("ESACode:" + esacode);
	    webaccesscode = esacode+"";

	    webaccesscode = webaccesscode.substring(6, 7) + 
	      webaccesscode.substring(1, 2) + 
	      webaccesscode.substring(2, 3) + 
	      webaccesscode.substring(0, 1) + 
	      webaccesscode.substring(3, 4) + 
	      webaccesscode.substring(4, 5) + 
	      webaccesscode.substring(5, 6) + 
	      webaccesscode.substring(7);

	    char[] param = webaccesscode.toCharArray();

	    int i = 1; for (int j = 0; i < param.length; j += 2)
	    {
	      finalStr2 = finalStr2 + param[i];
	      finalStr3 = finalStr3 + param[j];

	      i += 2;
	    }

	    String xpin = "";

	    if (finalStr3.equals("0001")) return finalStr3;

	    int code = Integer.parseInt("1" + data.substring(data.length() - 3));
	    int ret1 = xor(Integer.parseInt(finalStr3), code);

	    String ret = ret1+"";
	    if (ret1 == 0) xpin = "0000";
	    else
	      xpin = format4(4 - ret.length()) + ret1;
	    return xpin;
	  }

	  public String getTimeESACode2(String randomseed, String cPin) throws Exception {
	    String esacode = "";
	    while ((esacode = generateESACode(randomseed, cPin)).length() != 8);
	    while ((esacode = generateESACode(randomseed, cPin)).substring(0, 1).equals("0"));
	    return esacode;
	  }

	  public String generateESACode(String randomseed, String cPin) throws Exception {
	    String finalStr = "";
	    String datePartStr = getVal();
	    char[] datePart = datePartStr.toCharArray();
	    String pinPartStr = getWebAccessCode(randomseed, cPin);

	    if (pinPartStr.length() == 1) pinPartStr = "000" + pinPartStr;
	    if (pinPartStr.length() == 2) pinPartStr = "00" + pinPartStr;
	    if (pinPartStr.length() == 3) pinPartStr = "0" + pinPartStr;
	    char[] pinPart = pinPartStr.toCharArray();

	    int i = 0; for (int j = 0; i < datePart.length; j++)
	    {
	      finalStr = finalStr + pinPart[j] + datePart[i];

	      i++;
	    }

	    finalStr = finalStr.substring(3, 4) + 
	      finalStr.substring(1, 2) + 
	      finalStr.substring(2, 3) + 
	      finalStr.substring(4, 5) + 
	      finalStr.substring(5, 6) + 
	      finalStr.substring(6, 7) + 
	      finalStr.substring(0, 1) + 
	      finalStr.substring(7);

	    return finalStr;
	  }

	  public String getESACode(String randomseed, String cPin)
	  {
	    String finalStr = "";
	    String datePartStr = getVal();

	    char[] datePart = datePartStr.toCharArray();
	    String pinPartStr = getWebAccessCode(randomseed, cPin);

	    if (pinPartStr.length() == 1) pinPartStr = "000" + pinPartStr;
	    if (pinPartStr.length() == 2) pinPartStr = "00" + pinPartStr;
	    if (pinPartStr.length() == 3) pinPartStr = "0" + pinPartStr;
	    char[] pinPart = pinPartStr.toCharArray();

	    int i = 0; for (int j = 0; i < datePart.length; j++)
	    {
	      finalStr = finalStr + pinPart[j] + datePart[i];

	      i++;
	    }

	    if (pinPart.length == 5) {
	      finalStr = finalStr + pinPart[4];
	    }

	    finalStr = finalStr.substring(3, 4) + 
	      finalStr.substring(1, 2) + 
	      finalStr.substring(2, 3) + 
	      finalStr.substring(4, 5) + 
	      finalStr.substring(5, 6) + 
	      finalStr.substring(6, 7) + 
	      finalStr.substring(0, 1) + 
	      finalStr.substring(7);

	    return finalStr;
	  }

	  private String arrange(String finalStr) {
	    String arrange = finalStr;
	    arrange = arrange.substring(3, 4) + 
	      arrange.substring(1, 2) + 
	      arrange.substring(2, 3) + 
	      arrange.substring(4, 5) + 
	      arrange.substring(5, 6) + 
	      arrange.substring(6, 7) + 
	      arrange.substring(0, 1) + 
	      arrange.substring(7);
	    return arrange;
	  }

	  private String reArrange(String finalStr)
	  {
	    String arrange = finalStr;
	    arrange = arrange.substring(6, 7) + 
	      arrange.substring(1, 2) + 
	      arrange.substring(2, 3) + 
	      arrange.substring(0, 1) + 
	      arrange.substring(3, 4) + 
	      arrange.substring(4, 5) + 
	      arrange.substring(5, 6) + 
	      arrange.substring(7);
	    return arrange;
	  }

	  private static String format4(int i) {
	    String intzero = "";
	    for (int j = 0; j < i; j++) intzero = intzero + "0";
	    return intzero;
	  }

	  private String padZero(int in) {
	    String actValue = in+"";
	    if (actValue.length() < 2) actValue = "0" + in;
	    return actValue;
	  }

	  public String getVal()
	  {
	    Calendar mydate = Calendar.getInstance();
	    String firstresult = mydate.get(5) + padZero(mydate.get(11)) + padZero(mydate.get(12)) + padZero(mydate.get(13)) + padZero(mydate.get(14));

	    String secondresult = Integer.parseInt(firstresult.substring(0, 2)) + Integer.parseInt(firstresult.substring(2, 4)) + firstresult.substring(4);

	    String thirdresult2 = padZero(Integer.parseInt(secondresult.substring(1, 2)) + Integer.parseInt(secondresult.substring(3, 4)) + Integer.parseInt(secondresult.substring(5, 6))) + padZero(Integer.parseInt(secondresult.substring(0, 1)) + Integer.parseInt(secondresult.substring(2, 3)) + Integer.parseInt(secondresult.substring(4, 5)));

	    return thirdresult2;
	  }

	  private String applyTime(String m)
	  {
	    String currentTime = getSystemDate();
	    System.out.println("Current Time:" + currentTime);
	    int xcode = xor(Integer.parseInt(currentTime), Integer.parseInt(m));
	    System.out.println("ESAToken+Time:" + xcode);
	    return xcode+"";
	  }

	  private String applyTime(String m, int diff) {
	    String now = getSystemDate();
	    int currentTime = Integer.parseInt(now) + diff;
	    System.out.println("Current Time:" + currentTime);
	    int xcode = xor(currentTime, Integer.parseInt(m));
	    System.out.println("ESAToken+Time:" + xcode);
	    return xcode+"";
	  }

	  public String getWebAccessCode(String data, String pin)
	  {
	    String code = "1" + data.substring(data.length() - 3);

	    int ret1 = xor(Integer.parseInt(code), Integer.parseInt(pin));

	    String ret = "";
	    ret = ret1+"";

	    if (ret1 == 0) ret = "0000";

	    return ret;
	  }

	  public String validateESATimeCode(String webaccesscode, String phoneNumber, int timediff) throws Exception {
	    System.out.println("WebAccess ESA::" + webaccesscode);
	    System.out.println("Phone Number::" + phoneNumber);
	    String result = parseESAToken(webaccesscode, phoneNumber, timediff);
	    return result.substring(4);
	  }

	  public String getTimeESACode(String phoneNumber, String cPin) throws Exception {
	    return giveMeESA(phoneNumber, cPin);
	  }

	  public String giveMeESA(String phoneNumber, String pin) {
	    String esa = "";
	    while ((esa = genESAToken(phoneNumber, pin)).equals("-1"));
	    return esa;
	  }

	  public String genESAToken(String phoneNumber, String pin) {
	    String ret = "";

	    String phoneblock = phoneNumber.substring(phoneNumber.length() - 8);
	    String pinblock = getVal() + pin;

	    pinblock = arrange(pinblock);

	    int ret1 = xor(Integer.parseInt(phoneblock), Integer.parseInt(pinblock));

	    ret = String.valueOf(ret1);
	    if (ret.length() != 8) return "-1";

	    return ret;
	  }

	  public String parseESAToken(String esatoken, String phoneNumber, int diff) {
	    String ret = "";
	    String pin = "";

	    String phoneblock = phoneNumber.substring(phoneNumber.length() - 8);

	    String pinblock = String.valueOf(xor(Integer.parseInt(esatoken), Integer.parseInt(phoneblock)));
	    pinblock = reArrange(pinblock);

	    return pinblock;
	  }

	  private byte[] getBytes(String s) {
	    byte[] ret = new byte[s.length()];

	    for (int i = 0; i < s.length(); i++)
	    {
	      ret[i] = Byte.parseByte(s.substring(i, i + 1));
	    }
	    return ret;
	  }

	  private String xor(String str1, String str2)
	  {
	    byte[] b = str1.getBytes();
	    byte[] b1 = str2.getBytes();

	    int len = b.length > b1.length ? b.length : b1.length;

	    byte[] res = new byte[len];

	    for (int i = 0; i < len; i++)
	    {
	      if (i < b.length)
	      {
	        if (i < b1.length)
	          res[i] = (byte)(b[i] ^ b1[i]);
	        else
	          res[i] = (byte)b[i];
	      }
	      else
	        res[i] = (byte)b1[i];
	    }
	    String ttt = "";
	    for (int l = 0; l < len; l++) ttt = ttt + res[l];

	    return ttt;
	  }
	
}
