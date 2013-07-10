package org.etz.core.util;

 

public class Util {
	
	private static String appId = "UnionBankUk";
	//UnionBankUk;
	
	public static String confirmMessage()

	{
		String confirmMsg = "Please confirm your enteries. You can go back if you need to make changes";
		return confirmMsg;
	}

	public static String getAppId() {
		return appId;
	}

	public static void setAppId(String appId) {
		Util.appId = appId;
	}

 
}
