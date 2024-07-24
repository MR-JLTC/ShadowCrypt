/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package gocipher.ENUM_Utilities;

import java.util.Collections;

/**
 *
 * @author MR_JLTC
 */
public enum STYLE {
    HEADER("""
         \u250f\u2513\u2513   \u2513    \u250f\u2513      
         \u2517\u2513\u2523\u2513\u250f\u2513\u250f\u252b\u250f\u2513\u2513\u250f\u250f\u2503.\u250f\u2513\u250f\u250f\u2513\u254b 
          \u2517\u251b\u251b\u2517\u2517\u253b\u2517\u253b\u2517\u251b\u2517\u253b\u251b\u2517\u251b\u251b\u2517\u252b\u2523\u251b\u2517 
                       \u251b\u251b   """
    ),
    HEADER2(CONSOLE_COLOR.RED.getColor()+"["+CONSOLE_COLOR.WHITE.getColor()+"=="+
          CONSOLE_COLOR.RED.getColor()+"|"+CONSOLE_COLOR.YELLOW.getColor()+"----"+CONSOLE_COLOR.WHITE.getColor()+"<"+
          CONSOLE_COLOR.getRandomColor()+" $HAD0WCRY4T "+CONSOLE_COLOR.WHITE.getColor()+">"+CONSOLE_COLOR.YELLOW.getColor()+"----"+
          CONSOLE_COLOR.RED.getColor()+"|"+CONSOLE_COLOR.WHITE.getColor()+"=="+CONSOLE_COLOR.RED.getColor()+"]"+CONSOLE_COLOR.WHITE.getColor()
    ),
    CLOSE(CONSOLE_COLOR.RED.getColor()+"["+CONSOLE_COLOR.WHITE.getColor()+"=="+CONSOLE_COLOR.RED.getColor()+"|"+
            CONSOLE_COLOR.YELLOW.getColor()+"-----------------------"+CONSOLE_COLOR.RED.getColor()+"|"+
            CONSOLE_COLOR.WHITE.getColor()+"=="+CONSOLE_COLOR.RED.getColor()+"]"+CONSOLE_COLOR.WHITE.getColor()
    ),
    
    OPTIONS;
    STYLE(){}
    
    private String logo;
    STYLE(String logo){
        this.logo = logo;
    }
    
    public String getLogo(){
        return this.logo;
    }
    
    private static int padding=0;
    public static void setLeftPadding(int pv){
        padding = pv;
    }
    
    public String getStyled(Object optN, String label){
        String space = String.join("", Collections.nCopies(padding," "));
        return CONSOLE_COLOR.YELLOW.getColor()+space+"["+
               CONSOLE_COLOR.GREEN.getColor()+optN+
               CONSOLE_COLOR.YELLOW.getColor()+"]"+
               CONSOLE_COLOR.BLUE.getColor()+" "+label;
    }
}
