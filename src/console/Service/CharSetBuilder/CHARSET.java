/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package gocipher.Service.CharSetBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MR_JLTC
 * @SingletonType
 */
public enum CHARSET {
    INSTANCE;
    
    public final ArrayList<Character> getASCII_CHARS(){
        return ASCII_CHAR_GENERATOR();
    }
    
    private ArrayList<Character> ASCII_CHAR_GENERATOR(){
        final ArrayList<Character> charset = new ArrayList<>();
        for(int i=32;i<127;i++) charset.add((char) i);
        return charset;
    }
    
    public final char[] getALPHABETS(){
        return ALPHABET_CHAR_GENERATOR();
    }
    
    private char[] ALPHABET_CHAR_GENERATOR(){
        return new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'
        };
    }
    
    public final Map<Character, Integer> getCHALDEAN_CHARS(){
        return CHALDEAN_CHAR_GENERATOR();
    }
    
    private Map<Character, Integer> CHALDEAN_CHAR_GENERATOR(){
        final Map<Character, Integer> chaldeanMapper = new HashMap<>();
        chaldeanMapper.put('A', 1); chaldeanMapper.put('B', 2);
        chaldeanMapper.put('C', 3); chaldeanMapper.put('D', 4);
        chaldeanMapper.put('E', 5); chaldeanMapper.put('F', 8); 
        chaldeanMapper.put('G', 3); chaldeanMapper.put('H', 5);
        chaldeanMapper.put('I', 1); chaldeanMapper.put('J', 1);
        chaldeanMapper.put('K', 2); chaldeanMapper.put('L', 3);
        chaldeanMapper.put('M', 4); chaldeanMapper.put('N', 5);
        chaldeanMapper.put('O', 7); chaldeanMapper.put('P', 8);
        chaldeanMapper.put('Q', 1); chaldeanMapper.put('R', 2);
        chaldeanMapper.put('S', 3); chaldeanMapper.put('T', 4);
        chaldeanMapper.put('U', 6); chaldeanMapper.put('V', 6);
        chaldeanMapper.put('W', 6); chaldeanMapper.put('X', 5);
        chaldeanMapper.put('Y', 1); chaldeanMapper.put('Z', 7);
        return chaldeanMapper;
    }
}
