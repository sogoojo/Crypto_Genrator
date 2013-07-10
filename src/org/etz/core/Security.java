package org.etz.core;

import java.io.ByteArrayOutputStream;

import net.rim.device.api.crypto.BlockEncryptor;
import net.rim.device.api.crypto.DESEncryptorEngine;
import net.rim.device.api.crypto.DESKey;
import net.rim.device.api.crypto.PKCS5FormatterEngine;
import net.rim.device.api.crypto.RandomSource;
import net.rim.device.api.crypto.TripleDESEncryptorEngine;
import net.rim.device.api.crypto.TripleDESKey;

public class Security {

	static final String mk = "828C9E950EDEBB75FEF0C1343C1ABECE";
	public static final int KEY_USER = 0;
	public static final int KEY_MASTER = 1;

	public static boolean luhnCheck(String num) {

		final int[][] sumTable = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
				{ 0, 2, 4, 6, 8, 1, 3, 5, 7, 9 } };
		int sum = 0, flip = 0;

		for (int i = num.length() - 1; i >= 0; i--)
			sum += sumTable[flip++ & 0x1][Character.digit(num.charAt(i), 10)];
		return sum % 10 == 0;
	}
	public static String encrypt(int keyType, String val) {
		byte[] keybytes = null;
		if (keyType == KEY_USER)
			keybytes = StoreManager.getDEK().getBytes();
		else
			keybytes = mk.getBytes();
		TripleDESKey deskey = new TripleDESKey(keybytes);

		try {
			
			TripleDESEncryptorEngine enc = new TripleDESEncryptorEngine(deskey);

			PKCS5FormatterEngine formatterEngine = new PKCS5FormatterEngine(enc);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BlockEncryptor encryptor = new BlockEncryptor(formatterEngine,
					outputStream);
			// Encrypt the actual data.
			byte[] ascii = val.getBytes("ASCII");
			encryptor.write(ascii);
			// Close the stream.
			encryptor.close();

			byte[] encryptedData = outputStream.toByteArray();
			String hex_crypted = byteArrayToHexString(encryptedData);
			return hex_crypted;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return null;

	}

	static String byteArrayToHexString(byte in[]) {
		byte ch = 0x00;
		int i = 0;
		if (in == null || in.length <= 0) {
			return null;
		}
		String pseudo[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"A", "B", "C", "D", "E", "F" };
		StringBuffer out_str_buf = new StringBuffer(in.length * 2);
		while (i < in.length) {
			ch = (byte) (in[i] & 0xF0); // Strip off high nibble
			ch = (byte) (ch >>> 4); // shift the bits down
			ch = (byte) (ch & 0x0F); // must do this is high order bit is on!
			out_str_buf.append(pseudo[(int) ch]); // convert the nibble to a
			// String Character
			ch = (byte) (in[i] & 0x0F); // Strip off low nibble
			out_str_buf.append(pseudo[(int) ch]); // convert the nibble to a
			// String Character
			i++;
		}
		String rslt = new String(out_str_buf);
		return rslt;
	}

	public static String padleft(String s, int len, String c) throws Exception {
		s = s.trim();
		if (s.length() > len)
			throw new Exception("invalid len " + s.length() + "/" + len);
		StringBuffer d = new StringBuffer(len);
		int fill = len - s.length();
		while (fill-- > 0)
			d.append(c);
		d.append(s);
		return d.toString();
	}

	public static String buildDEK() {
		StringBuffer digit_key = new StringBuffer();
		try {

			for (int i = 0; i < 3; i++) {
				int pad = RandomSource.getInt(9);
				digit_key.append(padleft(Long.toString(RandomSource
						.getLong(99999999)), 8, Integer.toString(pad)));

			}

		} catch (Exception ex) {
			System.out.println("Error");
		}
		return digit_key.toString();

	}

}
