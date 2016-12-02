package com.istimaldar.encryptprogram.model;


import android.provider.MediaStore;

import java.io.IOException;

public class Model {

    private static Model instance = new Model();
    private byte[] data, proceededData;

    private Model() {

    }

    public static Model getInstance() {
        return instance;
    }

    public void loadFile(String path) {
        try {
            data = IOUtil.readFile(path);
        }
        catch (IOException e) {
            System.out.print("File Error!");
        }
    }

    public void loadText(String text) {
        data = text.getBytes();
    }

    public void encrypt(Encryptor encrypter) {
        try {
            proceededData = encrypter.encrypt(data);
        }
        catch (Throwable e) {
            System.out.print("BAKA!!!");
        }
    }

    public void decrypt(Encryptor decrypter) {
        proceededData = decrypter.decrypt(data);
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getProceededData() {
        return proceededData;
    }

    public String getProceededDataString() {
        return new String(proceededData);
    }

}
