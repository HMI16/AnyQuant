package com.bigwork.sql;

import java.io.*;

/**
 * Created by 15HR-1528SS on 2016/6/2.
 */
public class FileRead {
    public static String read(String filename) {
        String readTxt = "";
        try {
            File f = new File("src/main/resources/" + filename);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    readTxt += line;
                }
                read.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readTxt;
    }

    public static void main(String[] args) {
        System.out.print(FileRead.read("ip"));
    }
}
