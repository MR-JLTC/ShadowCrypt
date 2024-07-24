/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.Service.Formula_Based_Types;

import code.ENUM_Utilities.TYPE;
import code.Service.CharSetBuilder.CHARSET;
import code.Service.Cipher;
import java.util.Arrays;

/**
 *
 * @author MR_JLTC
 */
public class ShiftCipher_ASCII implements Cipher{
    private int shiftKey;
    private char[] letters;
    private final int based=95;
    public ShiftCipher_ASCII(){}
    
    @Override
    public String begun_process(TYPE process, String msg, Object key) {
        setShiftKey(key);
        return switch(process){
            case ENCRYPTION -> this.encrypt(msg);
            case DECRYPTION -> this.decrypt(msg);
            default ->"Key argument is invalid";
        };
    }
    
    private void setShiftKey(Object shiftKey) {
         if(shiftKey instanceof Integer shift) this.shiftKey=shift;
         else throw new NumberFormatException("Argument is NaN");
    }
    
    private String encrypt(String plainText) {
        return Msg_Encryptor(plainText);
    }
    
    private String Msg_Encryptor(String plainText){
        letters = plainText.toCharArray();
        sensitiveDataWiper(plainText);
        for(int index=0; index<letters.length; index++){
           letters[index] = getChar(
               (NumericVal(letters[index]) + shiftKey) % based
           );
        }
        return charArrayConcatenator(letters);
    }
    
    private String decrypt(String enc_msg) {
        return MsgDecryptor(enc_msg);
    }
    
    private String MsgDecryptor(String enc_msg){
        letters = enc_msg.toCharArray();
        sensitiveDataWiper(enc_msg);
        shiftKey = (shiftKey>95) ? (shiftKey%based) : shiftKey;
        for(int index=0; index<letters.length; index++){
           letters[index] = getChar(
                (NumericVal(letters[index]) - shiftKey + based) % based
           );
        }
        return charArrayConcatenator(letters);
    }  
    
    private int NumericVal(char c){
        int size = CHARSET.INSTANCE.getASCII_CHARS().size();
        for(int index=0; index<size; index++){
            String character = String.valueOf(CHARSET.INSTANCE.getASCII_CHARS().get(index));
            if(String.valueOf(c).equals(character)) return index;
        }
        return 0;
    }
    
    private char getChar(int rs){
        return CHARSET.INSTANCE.getASCII_CHARS().get(rs);
    }
    
    private String charArrayConcatenator(char[] chars){
        String msg="";
        for(char c: chars){
            msg+=c;
        }
        sensitiveDataWiper(chars);
        return msg;
    }
    
    //PREVENT MEMORY LEAK
    private void sensitiveDataWiper(char[] ref_ar){
        Arrays.fill(ref_ar, '\0');// clear sensitive data
        ref_ar = null; //clear reference to give hint to the Garbage_Collector
    }
    
    private void sensitiveDataWiper(String ref_str){
         ref_str = null; //clear reference to give hint to the Garbage_Collector
    }
}
