package com.istimaldar.encryptprogram.model;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public class DSA implements SymmetricEncryptor {
    @Override
    public SecretKey generateKeys() throws NoSuchAlgorithmException {
        return KeyGenerator.getInstance("DES").generateKey();
    }

    @Override
    public void saveKey(SecretKey key, String path) throws FileNotFoundException, IOException {
        File file = createFile(path);
        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(key);
    }

    @Override
    public SecretKey loadKey(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = createFile(path);
        FileInputStream fin = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fin);
        return  (SecretKey) ois.readObject();
    }

    @Override
    public byte[] encrypt(byte[] data, SecretKey key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        return ecipher.doFinal(data);
    }

    @Override
    public byte[] decrypt(byte[] data, SecretKey key)  throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {
        Cipher ecipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.DECRYPT_MODE, key);
        return ecipher.doFinal(data);
    }

    public byte[] encryptWithNewKey(byte[] data, String name) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        SecretKey key = generateKeys();
        saveKey(key, name +".dsakey");
        return encrypt(data, key);
    }

    public byte[] decrypt(byte[] data, String path) {
        try {
            SecretKey key = loadKey("DSAkey");
            return decrypt(data, key);
        }
        catch (Throwable e) {
            return new byte[0];
        }
    }

    public byte[] encryptWithOldKey(byte[] data, String path) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException, ClassNotFoundException {
        SecretKey key = loadKey(path);
        return encrypt(data, key);
    }

    private File createFile(String name) throws IOException {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(filePath + "/keys/", name);
        if (!file.exists() || file.isDirectory()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}
