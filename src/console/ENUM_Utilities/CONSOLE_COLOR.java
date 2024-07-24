/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package gocipher.ENUM_Utilities;

import java.util.Random;

/**
 *
 * @author MR_JLTC
 */
public enum CONSOLE_COLOR {
    GRAY("\033[38;5;244m"),
    RED("\u001b[38;2;255;13;13m"),
    WHITE("\033[1;37m"),
    BLUE("\u001b[38;2;0;191;255m"),
    YELLOW("\033[1;33m"),
    GREEN("\u001B[1;92m");
    
    private final String c;
    CONSOLE_COLOR(String c){
        this.c = c;
    }
    
    public static String getRandomColor(){
        CONSOLE_COLOR colors[] = {
            GRAY, RED, WHITE, BLUE, YELLOW, GREEN
        };
        return colors[new Random().nextInt(6)].getColor();
    }
    
    public String getColor(){
        return this.c;
    }
}
