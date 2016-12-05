package com.istimaldar.encryptprogram.model;


import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public void encrypt(Encryptor encrypter, boolean isKeyNew, String name) {
        try {
            if (isKeyNew) {
                proceededData = encrypter.encryptWithNewKey(data, name);
            }
            else {
                proceededData = encrypter.encryptWithOldKey(data, name);
            }
        }
        catch (Throwable e) {
            System.out.print("BAKA!!!");
        }
    }

    public void decrypt(Encryptor decrypter, String path) {
        try {
            proceededData = decrypter.decrypt(data, path);
        }
        catch (Throwable e) {
            System.out.print("BAKA!!!");
        }
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

    public void saveToFile(String path) {
        FileOutputStream out = null;
        try {
            File file = createFile(path, false);
            out = new FileOutputStream(file);
            out.write(proceededData);
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private File createFile(String name, boolean isKey) throws IOException {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String folder;
        if (isKey) {
            folder = "/key/";
        }
        else {
            folder = "/results/";
        }
        File file = new File(filePath + folder, name);
        if (!file.exists() || file.isDirectory()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    File createFile(String name) throws IOException {
        return createFile(name, true);
    }

}
