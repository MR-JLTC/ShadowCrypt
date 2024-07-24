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
import java.util.regex.Pattern;

/**
 *
 * @author MR_JLTC
 */
public class VigenereCipher implements Cipher{
    private final ArrayList<Integer> shift_keys;
    private final int based = 26;
    private char[] letters;
    
    public VigenereCipher(){shift_keys = new ArrayList<>();}
    
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
        autoRefresh();
        if(shiftKey instanceof String keys) {
            ShiftKeysInitializer(keys.toCharArray());
            sensitiveDataWiper(keys);
        } else throw new InputMismatchException("Key argument is invalid");
    }
    
    private void ShiftKeysInitializer(char[] keys){
        for(char key : keys) {
            if(!String.valueOf(key).isBlank()) shift_keys.add(numericVal(key));
        }
        sensitiveDataWiper(keys);
    }
    
    private void autoRefresh(){
        shift_keys.clear();
    }
    
    private String encrypt(String plainText) {
        return MsgEncryptor(plainText);
    }
    
    private String MsgEncryptor(String plaintext){
        letters = plaintext.toCharArray();
        if(isKeyLengthGreater(plaintext.split(" "))) modifyKeys();
        sensitiveDataWiper(plaintext);
        for(int index=0; index<letters.length; index++){
            if(lettersOnly(letters[index])){
                letters[index] = getChar(
                      (numericVal(letters[index]) + shift_keys.get(0)) % based
                );
                shift_keys.add(shift_keys.remove(0));
            }
        }
        return charArrayConcatenator(letters);
    }
    
    private String decrypt(String enc_msg) {
        return MsgDecryptor(enc_msg);
    }
    
    private String MsgDecryptor(String enc_msg){
        letters = enc_msg.toCharArray();
        if(isKeyLengthGreater(enc_msg.split(" "))) modifyKeys();
        sensitiveDataWiper(enc_msg);
        for(int index=0; index<letters.length; index++){
            if(lettersOnly(letters[index])){
                letters[index] = getChar(
                      ((numericVal(letters[index]) - shift_keys.get(0)) + based) % based
                );
                shift_keys.add(shift_keys.remove(0));
            }
        }
        return charArrayConcatenator(letters);
    }
    
    private boolean lettersOnly(char c){
        Pattern pattern = Pattern.compile("^[a-z][a-z]*+$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(String.valueOf(c)).matches();
    }
    
    private char getChar(int rs_index){
        return CHARSET.INSTANCE.getALPHABETS()[rs_index];
    }
    
    private int numericVal(char c){
        int size = CHARSET.INSTANCE.getALPHABETS().length;
        for(int index=0; index<size; index++){
            String character = String.valueOf(CHARSET.INSTANCE.getALPHABETS()[index]);
            if(String.valueOf(c).equalsIgnoreCase(character)) return index;
        }
        return 0;
    }
    
    private String charArrayConcatenator(char[] chars){
        String msg="";
        for(char c: chars){
            msg+=c;
        }
        sensitiveDataWiper(chars);
        sensitiveDataWiper(CHARSET.INSTANCE.getALPHABETS());
        return msg;
    }
    
    private int lengthGap=0;
    private int textlength=0;
    private boolean isKeyLengthGreater(String[] texts){
        int keylength=0;
        String text="";
        keylength = shift_keys.size();
        for(String s : texts) text+=s;
        textlength = text.toCharArray().length;
        lengthGap = keylength-textlength;
        sensitiveDataWiper(texts);
        sensitiveDataWiper(text);
        return keylength>textlength;
    }
    
    private void modifyKeys(){
        int sumOfExtraKeys=0;
        int iteration=0;
        for(int reverseIndex=shift_keys.size()-1; reverseIndex>=0; reverseIndex--){
            iteration++;
            sumOfExtraKeys+=shift_keys.get(reverseIndex);
            shift_keys.remove(reverseIndex);
            if(iteration==lengthGap) break;
        }
        int additionalKey = sumOfExtraKeys/textlength;
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
    
    private void sensitiveDataWiper(String[] ref_data){
        Arrays.fill(ref_data, "\0");
        ref_data = null;//clear reference to give hint to the Garbage_Collector
    }
    
    private void sensitiveDataWiper(String ref_str){
        //PREVENTS MEMORY LEAK
         ref_str = null; //clear reference to give hint to the Garbage_Collector
    }
}