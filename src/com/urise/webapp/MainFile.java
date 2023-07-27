package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("src");
        dirRecursion(dir);
    }

    public static void dirRecursion(File dir) {
        if(dir.isDirectory()){
            File[] files = dir.listFiles();
            assert files != null;
            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file + " hi!");
                }
                if(file.isDirectory()){
                    dirRecursion(file);
                }
            }
        }
    }
}
