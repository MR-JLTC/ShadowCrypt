/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package gocipher.ENUM_Utilities;

import java.util.InputMismatchException;

/**
 *
 * @author MR_JLTC
 */
public enum COLOR_TEXT {
    TEXT;
    COLOR_TEXT(){}
    
    public String getColored(CTYPE colorType, String msg){
        return switch(colorType){
            case GRAY -> CONSOLE_COLOR.GRAY.getColor()+msg+CONSOLE_COLOR.WHITE.getColor();
            case RED -> CONSOLE_COLOR.RED.getColor()+msg+CONSOLE_COLOR.WHITE.getColor();
            case WHITE -> CONSOLE_COLOR.WHITE.getColor()+msg;
            case BLUE -> CONSOLE_COLOR.BLUE.getColor()+msg+CONSOLE_COLOR.WHITE.getColor();
            default -> throw new InputMismatchException("Invalid Color Type");
        };
    }
}

