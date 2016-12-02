package com.istimaldar.encryptprogram.model;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public interface Encryptor {

    public  byte[] encrypt(byte[] data) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException;
    public byte[] decrypt(byte[] data);

}
