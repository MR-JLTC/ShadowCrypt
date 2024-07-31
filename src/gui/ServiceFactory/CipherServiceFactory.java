/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.ServiceFactory;

import code.ENUM_Utilities.CIPHER_TYPE;
import code.Service.Cipher;
import code.Service.Formula_Based_Types.ShiftCipher;
import code.Service.Formula_Based_Types.ShiftCipher_ASCII;
import code.Service.Formula_Based_Types.VernamCipher_ASCII;
import code.Service.Formula_Based_Types.VigenereCipher;
import code.Service.Formula_Based_Types.VigenereCipher_ASCII;

/**
 *
 * @author hunter
 * @DP Factory Pattern
 */
public class CipherServiceFactory {
    private CipherServiceFactory(){}
    public static Cipher getCipher(CIPHER_TYPE type){
        return switch(type){
            case SHIFT_CIPHER -> new ShiftCipher();  
            case SHIFT_CIPHER_ASCII -> new ShiftCipher_ASCII();
            case VIGENERE_CIPHER -> new VigenereCipher();
            case VIGENERE_CIPHER_ASCII -> new VigenereCipher_ASCII();
            case VERNAM_CIPHER_ASCII -> new VernamCipher_ASCII();
            default -> throw new RuntimeException("KeyType argument is invalid");
        };
    }
}
