/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class FileManager {
    
    public static ArrayList<String> stringReader (String path){ 
        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(new FileReader(path));
            ArrayList<String> text = new ArrayList<>();
            String line = buffRead.readLine();
            while (line != null) {
                text.add(line);
                line = buffRead.readLine();
            }   buffRead.close();
            return text;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                buffRead.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }   
    
    public static void writer (String path, String text){ 
        BufferedWriter buffWrite = null; 
        try {
            buffWrite = new BufferedWriter(new FileWriter(path));
            buffWrite.append(text);
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    public static void writerAppend (String path, String text){ 
        BufferedWriter buffWrite = null; 
        try {
            buffWrite = new BufferedWriter(new FileWriter(path, true));
            buffWrite.append(text);
            buffWrite.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                buffWrite.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static int[] intToBinary(int value, int size) {
        if (value > Math.pow(2, size) - 1) {
            return null;
        }
        int bin[] = new int[size];
        int i = 0;
        while (value > 0 && i < size) {
            int num = value % 2;
            value = value / 2;
            bin[i] = num;
            i++;
        }
        for (int j = 0; j <= size / 2; j++) {
            int temp = bin[j];
            bin[j] = bin[size - j - 1];
            bin[size - j - 1] = temp;
        }
        return bin;
    }

    public static String intToBinaryString(int value, int size) {
        if (value > Math.pow(2, size) - 1) {
            return null;
        }
        char bin[] = new char[size];
        for (int i = 0; i < size; i++) {
            bin[i] = '0';
        }
        int i = 0;
        while (value > 0 && i < size) {
            int num = value % 2;
            value = value / 2;
            bin[i] = (num + "").charAt(0);
            i++;
        }
        for (int j = 0; j <= size / 2; j++) {
            char temp = bin[j];
            bin[j] = bin[size - j - 1];
            bin[size - j - 1] = temp;
        }
        String nova = new String(bin);
        return nova;
    }
    
}
