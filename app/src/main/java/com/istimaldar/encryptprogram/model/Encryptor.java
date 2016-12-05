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

    abstract public  byte[] encryptWithNewKey(byte[] data, String name) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException;
    abstract public byte[] decrypt(byte[] data, String path) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException;
    abstract public  byte[] encryptWithOldKey(byte[] data, String path) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException, ClassNotFoundException;
}
