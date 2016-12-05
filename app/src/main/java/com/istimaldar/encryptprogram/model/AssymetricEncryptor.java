package com.istimaldar.encryptprogram.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by Istimaldar on 05.12.2016.
 */

public interface AssymetricEncryptor extends Encryptor {

    abstract public SecretKey generateKeys() throws NoSuchAlgorithmException;
    abstract public void saveKey(SecretKey key, String path) throws FileNotFoundException,
            IOException;
    abstract public SecretKey loadKey(String path) throws FileNotFoundException, IOException,
            ClassNotFoundException;
    abstract public byte[] encrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException;
    abstract public byte[] decrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException;
    abstract public  byte[] encryptWithNewKey(byte[] data, String name) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException;
    abstract public byte[] decrypt(byte[] data, String path) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException;
    abstract public  byte[] encryptWithOldKey(byte[] data, String path) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException, ClassNotFoundException;

}
