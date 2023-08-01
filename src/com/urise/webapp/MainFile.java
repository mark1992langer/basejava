package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));
        dirRecursion(dir);
    }

    public static void dirRecursion(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(".java")) {
                            System.out.println(file.getName());
                        }
                    }
                    if (file.isDirectory()) {
                        dirRecursion(file);
                    }
                }
            }
        }
    }
}
