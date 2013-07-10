package com.etz.mobile.security;
 

import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.engines.*;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.util.encoders.*;

public class Decryptor extends Encryptor
{
    public Decryptor(String key)
    {
        super(false, key);
    }

    public Decryptor(byte[] key)
    {
        super(false, key);
    }

    public String decrypt(String encodedText)
    {
        try {
            byte[] encodedBytes = encodedText.getBytes();
            byte[] cipherBytes = Hex.decode(encodedBytes); //.decode(encodedBytes);
            
            // to decode the cipher in base64
           // byte[] cipherBytes = Base64.decode(encodedBytes);
            
            byte[] plainBytes = new byte[cipher.getOutputSize(cipherBytes.length)];

            cipher.processBytes(cipherBytes, 0, cipherBytes.length, plainBytes, 0);
            cipher.doFinal(plainBytes, 0);

            return new String(plainBytes);
        } catch (DataLengthException ex) {
            throw new RuntimeException(ex.toString());
        } catch (IllegalStateException ex) {
            throw new RuntimeException(ex.toString());
        } catch (InvalidCipherTextException ex) {
            throw new RuntimeException(ex.toString());
        }
    }
}

