package com.istimaldar.encryptprogram.controller;

import java.io.File;

/**
 * Created by Istimaldar on 02.12.2016.
 */

public class Storage {

    public static final String[] ACTIONS = {"Шифрование", "Дешифровка"};
    public static final String[] SOURCES = {"Текст", "Файл"};
    public static final String[] TYPES = {"Симметричное", "Ассиметричное"};
    public static final String[] OUTPUT = {"Сохранить в файл", "Сохранить в поле"};
    public static final String[] ASYMETRIC_ALGORITHMS = {"DES"};
    public static final String[] SYMMETRIC_ALGORITHMS = {"RSA"};
    public static final String[] DES_SIZES = {"52"};
    public static final String[] RSA_SIZES = {"512"};
    public static final String[] METHODS_ENCRYPT = {"Загрузка из файла", "Генерация"};
    public static final String[] METHODS_DECRYPT = {"Загрузка из файла"};
    public static final String ACTION = "action";
    public static final String SOURCE = "source";

}
