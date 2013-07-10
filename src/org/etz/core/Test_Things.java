package org.etz.core;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
 

 

public class Test_Things {
	
	  public static byte[] hmac_sha1(byte[] keyBytes, byte[] text)
	  {
		  
	    HMac hmac = new HMac(new SHA1Digest());
        
	    byte[] resBuf = new byte[hmac.getMacSize()];
        
	    CipherParameters pm = new KeyParameter(keyBytes); 
	    hmac.init(pm);
	    hmac.update(text, 0, text.length);
	    hmac.doFinal(resBuf, 0);

	    return resBuf;
	  }

}
