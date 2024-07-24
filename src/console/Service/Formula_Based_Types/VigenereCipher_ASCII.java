/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gocipher.Service.Formula_Based_Types;

import gocipher.Service.CharSetBuilder.CHARSET;
import gocipher.Service.Cipher;
import gocipher.ENUM_Utilities.TYPE;
import static gocipher.ENUM_Utilities.TYPE.DECRYPTION;
import static gocipher.ENUM_Utilities.TYPE.ENCRYPTION;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author MR_JLTC
 */
public class VigenereCipher_ASCII implements Cipher{
    private final ArrayList<Integer> shift_keys;
    private final int based = 95;
    private char[] letters;
    
    public VigenereCipher_ASCII(){shift_keys = new ArrayList<>();}
    
    @Override
    public String begun_process(TYPE process, String msg, Object key) {
        setShiftKey(key);
        return switch(process){
            case ENCRYPTION -> this.encrypt(msg);
            case DECRYPTION -> this.decrypt(msg);
            default -> throw new InputMismatchException("Key argument is invalid");
        };
    }
    
    private void setShiftKey(Object shiftKey) {
        shift_keys.clear();
        if(shiftKey instanceof String keys){
             ShiftKeysInitializer(keys.toCharArray());
             sensitiveDataWiper(keys);
        } else throw new InputMismatchException("Key arrgument is invalid");
    }
    
    private void ShiftKeysInitializer(char[] keys){
        for(char key : keys) shift_keys.add(numericVal(key));
        sensitiveDataWiper(keys);
    }
    
    private String encrypt(String plainText) {
        return MsgEncryptor(plainText);
    }
    
    private String MsgEncryptor(String plainText){
        letters = plainText.toCharArray();
        if(isKeyLengthGreater(letters.length)) modifyKeys();
        sensitiveDataWiper(plainText);
        for(int index=0; index<letters.length; index++){
            letters[index] = getChar(
                (numericVal(letters[index]) + shift_keys.get(0)) % based
            );
            shift_keys.add(shift_keys.remove(0));
        }
        return charArrayConcatenator(letters);
    }
    
    private String decrypt(String enc_msg) {
        return MsgDecryptor(enc_msg);
    }
    
    private String MsgDecryptor(String enc_msg){
        letters = enc_msg.toCharArray();
        if(isKeyLengthGreater(letters.length)) modifyKeys();
        sensitiveDataWiper(enc_msg);
        for(int index=0; index<letters.length; index++){
           letters[index] = getChar(
                      ((numericVal(letters[index]) - shift_keys.get(0)) + based) % based
           );
           shift_keys.add(shift_keys.remove(0));
        }
        return charArrayConcatenator(letters);
    }
    
    private char getChar(int rs_index){
        return CHARSET.INSTANCE.getASCII_CHARS().get(rs_index);
    }
    
    private int numericVal(char c){
        int size = CHARSET.INSTANCE.getASCII_CHARS().size();
        for(int index=0; index<size; index++){
            String character = String.valueOf(CHARSET.INSTANCE.getASCII_CHARS().get(index));
            if(String.valueOf(c).equals(character)) return index;
        }
        return 0;
    }
    
    private String charArrayConcatenator(char[] chars){
        String msg="";
        for(char c: chars){
            msg+=c;
        }
        sensitiveDataWiper(chars);
        sensitiveDataWiper(CHARSET.INSTANCE.getASCII_CHARS());
        return msg;
    }
    
    private int lengthGap=0;
    private int textLength=0;
    private boolean isKeyLengthGreater(int textlength){
        int keylength=0;
        keylength = shift_keys.size();
        textLength = textlength;
        lengthGap = keylength-textLength;
        textlength=0;//to refresh the value
        return keylength>textLength;
    }
    
    private void modifyKeys(){
        int sumOfExtraKeys=0;
        int iteration=0;
        int additionalKey = 0;
        for(int reverseIndex=shift_keys.size()-1; reverseIndex>=0; reverseIndex--){
            iteration++;
            sumOfExtraKeys+=shift_keys.get(reverseIndex);
            shift_keys.remove(reverseIndex);
            if(iteration==lengthGap) break;
        }
        additionalKey = sumOfExtraKeys/textLength;
        for(int index=0; index<shift_keys.size(); index++){
            shift_keys.set(index, (shift_keys.get(index)+additionalKey) % based);
        }
        lengthGap=0;
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
    
    private void sensitiveDataWiper(ArrayList<Character> ref_list){
        //PREVENTS MEMORY LEAK
         ref_list.clear(); //clear reference to give hint to the Garbage_Collector
    }
}
