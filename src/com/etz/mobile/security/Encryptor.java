 package com.etz.mobile.security;

import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.engines.*;
 
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.util.encoders.*;

public class Encryptor {
	protected BufferedBlockCipher cipher = new BufferedBlockCipher(
			new DESedeEngine());

	public Encryptor(byte[] key) {
		this(true, key);
	}

	public Encryptor(String key) {
		this(true, key);
	}

	public Encryptor(boolean forEncryption, String key) {
		this(forEncryption, key.getBytes());
	}

	public Encryptor(boolean forEncryption, byte[] key) {
		try {

			cipher.init(forEncryption, new KeyParameter(key));
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException(ex.toString());
		}
	}

	public String encrypt(String plainText) {
		System.out.println("Plain Text =" + plainText);
		return encrypt(plainText.getBytes());
	}

	public String encryptData(byte[] plainBytes, boolean isBase64)
	{
		try
		{
			int origSize = plainBytes.length;
			if(origSize % 8 !=0 )
			{
				int padding = 8 - origSize % 8;
				int newSize = origSize + padding;
				
				byte[] newInputBytes = new byte[newSize];
				System.arraycopy(plainBytes, 0, newInputBytes, 0, origSize);
				plainBytes = newInputBytes;
				
			}
			byte[] out = new byte[cipher.getOutputSize(plainBytes.length)];
			cipher.processBytes(plainBytes, 0, plainBytes.length, out, 0);
			if(isBase64)
			{
			return new String(Base64.encode(out));
			}
			else
			{
		     return new String(Hex.encode(out));
			}	
		
	} catch (DataLengthException ex) {
		throw new RuntimeException(ex.toString());
	} catch (IllegalStateException ex) {
		throw new RuntimeException(ex.toString());
	}
	}
	 
	
	public String encrypt(byte[] plainBytes) {
		try {

			int origSize = plainBytes.length;
			if (origSize % 8 != 0) {
				int padding = 8 - origSize % 8;

				int newSize = origSize + padding;

				byte[] newInputBytes = new byte[newSize];
				System.arraycopy(plainBytes, 0, newInputBytes, 0, origSize);
				plainBytes = newInputBytes;
			}
			byte[] out = new byte[cipher.getOutputSize(plainBytes.length)];
			cipher.processBytes(plainBytes, 0, plainBytes.length, out, 0);	
			// If base64 is the encoding method for the encryption 
			//new String(Base64.encode(out)); // add a flag to tell which to return

			return new String(Hex.encode(out));
			
		} catch (DataLengthException ex) {
			throw new RuntimeException(ex.toString());
		} catch (IllegalStateException ex) {
			throw new RuntimeException(ex.toString());

		}
	}

}