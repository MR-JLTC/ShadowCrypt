/*
 * The MIT License
 *
 * Copyright 2024 MR_JLTC.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gocipher.Controller;

import gocipher.ENUM_Utilities.CIPHER_TYPE;
import gocipher.ENUM_Utilities.CONSOLE_COLOR;
import gocipher.ENUM_Utilities.CONSTRAIN_TYPE;
import gocipher.ENUM_Utilities.TEXT_TYPE;
import gocipher.ServiceHelper.CipherService;
import gocipher.View.Console;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

/**
 *
 * @author hunter
 */
public class ServiceRequestController {
    private final Console console;
    private final CipherService cipher_service;
    
    public ServiceRequestController(Console console, CipherService cipherService){
        this.console = console;
        this.cipher_service = cipherService;
    }
    
    public void start_process(){
        String rs="";
        try{
            while(true){
                console.clearScreen();
                console.printMainPage();
                String opt = String.valueOf(console.getInput(CONSTRAIN_TYPE.OPTION_KEYS)).toUpperCase();
                switch(opt){
                        case "1E","2E","3E","4E","5E" -> rs = doEncryption(opt.split("")[0]);
                        case "1D","2D","3D","4D","5D" -> rs = doDecryption(opt.split("")[0]);
                        case "6" -> doBruteForce();
                        case "7" -> {
                            console.print("GOODBYE!\n", TEXT_TYPE.OUTPUT_INFO);
                            System.exit(0);
                        }
                        default -> rs = "Invalid";
                }
                console.print(rs ,(rs.equalsIgnoreCase("Invalid") ? TEXT_TYPE.OUTPUT_ERROR : TEXT_TYPE.OUTPUT));
                console.print("press enter key to proceed...", TEXT_TYPE.OUTPUT_INFO);
                console.getNextLine();
            }
        }catch(NoSuchElementException ex){
            console.print("\nForced exit detected", TEXT_TYPE.OUTPUT_INFO);
        }
    }
    
    private String doEncryption(String opt){
        boolean isInvalid=false;
        boolean isInAlphabets=false;
        String rs = "";
        Object key="";
        console.print("PlainText> ", TEXT_TYPE.INPUT);
        String msg = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        if(msg.isBlank()) console.print("Do not leave it blank", TEXT_TYPE.OUTPUT_ERROR);
        else{
            try{
                switch(opt){
                    case "1" -> {
                        cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER_ASCII);
                        console.print("ShiftOf> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.NUMERICS);
                    }
                    case "2" -> {
                        cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER_ASCII);
                        console.print("Keyword> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.NONE);
                    }
                    case "3" -> {
                        cipher_service.setCipherService(CIPHER_TYPE.VERNAM_CIPHER_ASCII);
                        console.print("Keyword> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.NONE);
                    }
                    case "4" -> {
                        cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER);
                        console.print("ShiftOf> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.NUMERICS);
                    }
                    case "5" -> {
                        isInAlphabets=true;
                        cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER);
                        console.print("Keyword> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.ALPHABETS);
                   }
                }
                if(!isInvalid) rs = cipher_service.encrypt(msg, key);//executes if the required parameters are valid
                if(msg.equals("Invalid")) {
                    isInvalid=false;
                    console.print("Only Alphabets for the text", TEXT_TYPE.OUTPUT_INFO);
                }
                if(key.equals("Invalid")) {
                    isInvalid=false;
                    console.print((isInAlphabets) ? "Special chars for key isn't allowed here\n" :
                    "Only Numerics for the key, its here", TEXT_TYPE.OUTPUT_INFO);
                }
           }catch(NumberFormatException ex){
                console.print("Only Numerics for the key", TEXT_TYPE.OUTPUT_INFO);
           }catch(IndexOutOfBoundsException ex){
                console.print("Key should not be blank", TEXT_TYPE.OUTPUT_ERROR);
           }
        }
        return rs;
    }
    
    private String doDecryption(String opt){
        boolean isInvalid=false;
        boolean isInAlphabets=false;
        String rs = "";
        console.print("CipherText> ", TEXT_TYPE.INPUT);
        String msg = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        if(msg.isBlank()) console.print("Do not leave it blank", TEXT_TYPE.OUTPUT_ERROR);
        else{
           Object key="";
           try{
              switch(opt){
                   case "1" -> {
                        cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER_ASCII);
                        console.print("ShiftOf> ", TEXT_TYPE.INPUT);
                        key = console.getInput(CONSTRAIN_TYPE.NUMERICS);
                   }
                   case "2" -> {
                       cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER_ASCII);
                       console.print("Keyword> ", TEXT_TYPE.INPUT);
                       key = console.getInput(CONSTRAIN_TYPE.NONE);
                   }
                   case "3" -> {
                       cipher_service.setCipherService(CIPHER_TYPE.VERNAM_CIPHER_ASCII);
                       console.print("Keyword> ", TEXT_TYPE.INPUT);
                       key = console.getInput(CONSTRAIN_TYPE.NONE);
                   }
                   case "4" -> {
                       cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER);
                       console.print("ShiftOf> ", TEXT_TYPE.INPUT);
                       key = console.getInput(CONSTRAIN_TYPE.NUMERICS);
                   }
                   case "5" -> {
                       isInAlphabets=false;
                       cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER);
                       console.print("Keyword> ", TEXT_TYPE.INPUT);
                       key = console.getInput(CONSTRAIN_TYPE.ALPHABETS);
                   }
             }
             if(!isInvalid) rs = cipher_service.decrypt(msg, key);//executes if the required parameters are valid
             if(msg.equals("Invalid")) {
                  isInvalid=true;
                  console.print("Only Alphabets for the text", TEXT_TYPE.OUTPUT_INFO);
             }
             if(key.equals("Invalid")) {
                  isInvalid=true;
                  console.print((isInAlphabets) ? 
                  "Special chars for key isn't allowed here\n" :
                  "Only Numerics for the key, its here", TEXT_TYPE.OUTPUT_INFO);
             }
           }catch(NumberFormatException ex){
                console.print("Only Numerics for the key", TEXT_TYPE.OUTPUT_INFO);
           }catch(IndexOutOfBoundsException ex){
                console.print("Key should not be blank", TEXT_TYPE.OUTPUT_ERROR);
           }
        }
        return rs;
    }
    
    private void doBruteForce(){
        String opt="";
        while(true){
            console.clearScreen();
            console.printBruteForcePage();
            opt = String.valueOf(console.getInput(CONSTRAIN_TYPE.OPTION_KEYS_LTRS)).toUpperCase();
            switch(opt){
                case "G" -> bruteForceVigenereCipher();
                case "S" -> bruteForceShiftCipher();
                case "R" -> bruteForceVernamCipher();
                case "B" -> start_process();
                default -> {
                    console.print("Invalid", TEXT_TYPE.OUTPUT_ERROR);
                    console.print("press enter key to proceed...", TEXT_TYPE.OUTPUT_INFO);
                    console.getNextLine();
                }
            }
        }
    }
    
    private void bruteForceVigenereCipher(){
        ArrayList<String> keys = new ArrayList<>();
        console.print("CipherText> ", TEXT_TYPE.INPUT);
        String enc_msg = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        console.print("Keywords> ", TEXT_TYPE.INPUT);
        String input = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        
        if(!isThisATextFile(input).equals("Invalid")) keys.addAll(read(input));
        else keys.addAll(Arrays.asList(input.split(", ")));
        
        for(String key: keys){
            try {
                cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER);
                console.print(CONSOLE_COLOR.GRAY.getColor()+"["+key+"]> "+CONSOLE_COLOR.GREEN.getColor()+cipher_service.decrypt(enc_msg, key)+CONSOLE_COLOR.WHITE.getColor(), TEXT_TYPE.OUTPUT);
                Thread.sleep(100);
                cipher_service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER_ASCII);
                console.print(CONSOLE_COLOR.GRAY.getColor()+"["+key+"(Ascii)]> "+CONSOLE_COLOR.GREEN.getColor()+cipher_service.decrypt(enc_msg, key)+CONSOLE_COLOR.WHITE.getColor(), TEXT_TYPE.OUTPUT);
            } catch (InterruptedException ex) {console.print("Error occured in thread", TEXT_TYPE.OUTPUT_INFO);}
        }
        console.print("press enter key to proceed...", TEXT_TYPE.OUTPUT_INFO);
        console.getNextLine();
    }
    
    private void bruteForceShiftCipher(){
        console.print("CipherText> ", TEXT_TYPE.INPUT);
        String enc_msg = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        console.print("EndRange> ", TEXT_TYPE.INPUT);
        int endRange = Integer.parseInt(String.valueOf(console.getInput(CONSTRAIN_TYPE.NUMERICS)));
        for(int startRange=1; startRange<=endRange; startRange++){
           try {
              cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER);
              console.print(CONSOLE_COLOR.GRAY.getColor()+"["+startRange+"]> "+CONSOLE_COLOR.GREEN.getColor()+cipher_service.decrypt(enc_msg, startRange)+CONSOLE_COLOR.WHITE.getColor(), TEXT_TYPE.OUTPUT);
              Thread.sleep(100);
              cipher_service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER_ASCII);
              console.print(CONSOLE_COLOR.GRAY.getColor()+"["+startRange+"(Ascii)]> "+CONSOLE_COLOR.GREEN.getColor()+cipher_service.decrypt(enc_msg, startRange)+CONSOLE_COLOR.WHITE.getColor(), TEXT_TYPE.OUTPUT);
           }catch (InterruptedException ex) {console.print("Error occured in thread", TEXT_TYPE.OUTPUT_INFO);}
       }
       console.print("press enter key to proceed...", TEXT_TYPE.OUTPUT_INFO);
       console.getNextLine();
    }
    
    private void bruteForceVernamCipher(){
        ArrayList<String> keys = new ArrayList<>();
        console.print("CipherText> ", TEXT_TYPE.INPUT);
        String enc_msg = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        console.print("Keywords> ", TEXT_TYPE.INPUT);
        String input = String.valueOf(console.getInput(CONSTRAIN_TYPE.NONE));
        
        if(!isThisATextFile(input).equals("Invalid")) keys.addAll(read(input));
        else keys.addAll(Arrays.asList(input.split(", ")));
        
        for(String key: keys){
            try {
                cipher_service.setCipherService(CIPHER_TYPE.VERNAM_CIPHER_ASCII);
                console.print(CONSOLE_COLOR.GRAY.getColor()+"["+key+"(ASCII)]> "+CONSOLE_COLOR.GREEN.getColor()+cipher_service.decrypt(enc_msg, key)+CONSOLE_COLOR.WHITE.getColor(), TEXT_TYPE.OUTPUT);
                Thread.sleep(100);
            } catch (InterruptedException ex) {console.print("Error occured in thread", TEXT_TYPE.OUTPUT_INFO);}
        }
        console.print("press enter key to proceed...", TEXT_TYPE.OUTPUT_INFO);
        console.getNextLine();
    }
    
    private String isThisATextFile(String input){
        Pattern pattern = Pattern.compile("^[a-z]+.txt$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches() ? input : "Invalid";
    }
    
    private ArrayList<String> read(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionary/"+fileName));
            String line;
            while((line=reader.readLine())!=null){
                data.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FAILED TO READ DATA: "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("FAILED TO READ DATA: "+ex.getMessage());
        }
        return data;
    }
}
