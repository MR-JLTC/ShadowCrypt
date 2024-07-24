/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gocipher.Service;

import gocipher.ENUM_Utilities.TYPE;

/**
 *
 * @author hunter
 */
@FunctionalInterface
public interface Cipher {
    String begun_process(TYPE process, String msg, Object key);
}
