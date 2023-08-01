package com.urise.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));
        File dirSrc = new File("src");
        dirRecursion(dirSrc, 0);
    }

    public static void dirRecursion(File dir, int level) {
        final String indent = indented(level, "     ");
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.getName().endsWith(".java")) {
                            System.out.println(indent + "File: " + file.getName());
                        }
                    }else if(file.isDirectory()) {
                        System.out.println(indent + "Directory: " + file.getName());
                        dirRecursion(file, level + 1);
                    }
                }
            }
        }
    }

    public static String indented(int i, String value) {
        return new String(new char[i]).replace("\0", value);
    }
}
