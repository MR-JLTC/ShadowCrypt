/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gocipher.View;

import gocipher.ENUM_Utilities.COLOR_TEXT;
import gocipher.ENUM_Utilities.CONSOLE_COLOR;
import gocipher.ENUM_Utilities.CONSTRAIN_TYPE;
import gocipher.ENUM_Utilities.CTYPE;
import gocipher.ENUM_Utilities.STYLE;
import gocipher.ENUM_Utilities.TEXT_TYPE;
import static gocipher.ENUM_Utilities.TEXT_TYPE.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author MR_JLTC
 */
public class Console {
    private void printHeader(){
        System.out.println(STYLE.HEADER2.getLogo());
    }
    
    public void print(String msg, TEXT_TYPE type){
        switch(type){
            case INPUT -> System.out.print(msg);
            case OUTPUT -> System.out.println(msg);
            case OUTPUT_ERROR -> System.out.println(CONSOLE_COLOR.RED.getColor()+msg+CONSOLE_COLOR.WHITE.getColor());
            case OUTPUT_INFO -> System.out.print(CONSOLE_COLOR.BLUE.getColor()+msg+CONSOLE_COLOR.WHITE.getColor());
            default -> throw new InputMismatchException("Invalid Argument for Text Type");
        }
    }
    
    public void clearScreen(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
              new ProcessBuilder("cmd","/c", "cls").inheritIO().start().waitFor();
            }else{
              new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(IOException | InterruptedException e){
            this.print("System clear interrupted", OUTPUT_ERROR);
            this.print(e.getMessage(), OUTPUT_INFO);
        }
    }
    
    private void spaceLine(){
        System.out.println();
    }
    
    private void printInTable(String[] texts){
        print(COLOR_TEXT.TEXT.getColored(CTYPE.WHITE,"┌─────────────────────────────┐"),OUTPUT);
        for(String text: texts){
            print(text,OUTPUT);
        }
        print(COLOR_TEXT.TEXT.getColored(CTYPE.WHITE,"└─────────────────────────────┘"), OUTPUT);
    }
    
    public void printMainPage(){
        printHeader();
        STYLE.setLeftPadding(2);
        printInTable(new String[]{
            STYLE.OPTIONS.getStyled(1, "Shift Cipher (ASCII)"),
            STYLE.OPTIONS.getStyled(2, "Vigenere Cipher (ASCII)"),
            STYLE.OPTIONS.getStyled(3, "Vernam Cipher (ASCII)"),
            STYLE.OPTIONS.getStyled(4, "Shift Cipher"),
            STYLE.OPTIONS.getStyled(5, "Vigenere Cipher"),
            STYLE.OPTIONS.getStyled(6, "BruteForce"),
            STYLE.OPTIONS.getStyled(7, "Exit")
        });
        print(STYLE.CLOSE.getLogo(),OUTPUT);
        print(COLOR_TEXT.TEXT.getColored(CTYPE.GRAY, "    [Ne]ncrypt, [Nd]ecrypt"),OUTPUT);
        spaceLine();
        print(COLOR_TEXT.TEXT.getColored(CTYPE.WHITE, "Opt-> "), INPUT);
    }
    
    public void printBruteForcePage(){
        printHeader();
        STYLE.setLeftPadding(6);
        printInTable(new String[]{
                STYLE.OPTIONS.getStyled("g", "Vigenere Cipher"),
                STYLE.OPTIONS.getStyled("s", "Shift Cipher"),
                STYLE.OPTIONS.getStyled("r", "Vernam Cipher"),
                STYLE.OPTIONS.getStyled("b", "Back to Main"),
        });
        print(STYLE.CLOSE.getLogo(),OUTPUT);
        print(COLOR_TEXT.TEXT.getColored(CTYPE.GRAY, "For [v], enter the test words separated by comma\nor enter the textfile name for you dictionary"),OUTPUT);
        print(COLOR_TEXT.TEXT.getColored(CTYPE.GRAY, "Ex. key1, key2, key3 / <textfilename>.txt"),OUTPUT);
        spaceLine();
        print(COLOR_TEXT.TEXT.getColored(CTYPE.WHITE, "Type-> "), INPUT);
    }
    
    private String numericKeysOnly(String input){
        return Pattern.matches("^\\d+$", input) ? 
               input : "Invalid";
    }
    
    private String mainOptionKeysOnly(String input){
        Pattern pattern = Pattern.compile("^[1-7][ed]*$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches() ? input : "Invalid";
    }
    
    private String bruteforcePageKeysOnly(String input){
        Pattern pattern = Pattern.compile("^[gsrb]$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches() ? input : "Invalid";
    }
    
    private String alphabeticalKeysOnly(String input){
        Pattern pattern = Pattern.compile("^[a-z][a-z ]*+$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches() ? input : "Invalid";
    }
    
    public void getNextLine(){
        new Scanner(System.in).nextLine();
    }
    
    public Object getInput(CONSTRAIN_TYPE type){
       return switch(type){
           case NUMERICS -> Integer.parseInt(numericKeysOnly(new Scanner(System.in).nextLine()));
           case OPTION_KEYS -> mainOptionKeysOnly(new Scanner(System.in).nextLine());
           case OPTION_KEYS_LTRS ->bruteforcePageKeysOnly(new Scanner(System.in).nextLine());
           case ALPHABETS -> alphabeticalKeysOnly(new Scanner(System.in).nextLine());
           case NONE -> new Scanner(System.in).nextLine();
           default -> throw new InputMismatchException("Invalid Constrain type");
       };
    }
}
