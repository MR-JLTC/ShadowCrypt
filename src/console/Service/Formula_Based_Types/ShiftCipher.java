/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gocipher.Service.Formula_Based_Types;

import gocipher.Service.CharSetBuilder.CHARSET;
import gocipher.Service.Cipher;
import gocipher.ENUM_Utilities.TYPE;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author MR_JLTC
 */
public class ShiftCipher implements Cipher{
    private int shiftKey;
    private final int based=26;
    private char[] letters;
    
    public ShiftCipher(){}
    
    @Override
    public String begun_process(TYPE process, String msg, Object key) {
        return switch(process){
            case ENCRYPTION -> this.encrypt(msg, key);
            case DECRYPTION -> this.decrypt(msg, key);
            default -> "Key argument is invalid";
        };
    }
    
    void setShiftKey(Object shiftKey) {
        if(shiftKey instanceof Integer shift) this.shiftKey = shift;
        else throw new NumberFormatException("Argument is NaN");
    }
    
    private String encrypt(String plainText, Object key) {
        setShiftKey(key);
        return Msg_Encryptor(plainText);
    }
    
    private String Msg_Encryptor(String plainText){
        letters = plainText.toCharArray();
        sensitiveDataWiper(plainText);
        for(int keyIndex=0; keyIndex<letters.length; keyIndex++){
            if(lettersOnly(letters[keyIndex])){
                letters[keyIndex] = getChar(
                   (NumericVal(letters[keyIndex]) + shiftKey) % based
                );
            }
        }
        return charArrayConcatenator(letters);
    }
    
    private String decrypt(String enc_msg, Object key) {
        setShiftKey(key);
        return MsgDecryptor(enc_msg);
    }
    
    private String MsgDecryptor(String enc_msg){
        letters = enc_msg.toCharArray();
        sensitiveDataWiper(enc_msg);
        shiftKey = (shiftKey>26) ? (shiftKey%based) : shiftKey;
        for(int index=0; index<letters.length; index++){
            if(lettersOnly(letters[index])){
                letters[index] = getChar(
                   (NumericVal(letters[index]) - shiftKey  + based) % based
                );
            }
        }
        return charArrayConcatenator(letters);
    }
    
    private int NumericVal(char c){
        int size = CHARSET.INSTANCE.getALPHABETS().length;
        for(int index=0; index<size; index++){
            String character = String.valueOf(CHARSET.INSTANCE.getALPHABETS()[index]);
            if(String.valueOf(c).equalsIgnoreCase(character)){
                return index;
            }
        }
        return 0;
    }
    
    private boolean lettersOnly(char c){
        Pattern pattern = Pattern.compile("^[a-z][a-z ]*+$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(String.valueOf(c)).matches();
    }
    
    private char getChar(int rs){
        return CHARSET.INSTANCE.getALPHABETS()[rs];
    }
    
    private String charArrayConcatenator(char[] chars){
        String msg="";
        for(char c: chars){
            msg+=c;
        }
        sensitiveDataWiper(chars);
        return msg;
    }
    
    private void sensitiveDataWiper(char[] ref_ar){
        //PREVENTS MEMORY LEAK
        Arrays.fill(ref_ar, '\0');// clear sensitive data
        ref_ar = null; //clear reference to give hint to the Garbage_Collector
    }
    
    private void sensitiveDataWiper(String ref_str){
        //PREVENTS MEMORY LEAK
         ref_str = null; //clear reference to give hint to the Garbage_Collector
    }
}   
