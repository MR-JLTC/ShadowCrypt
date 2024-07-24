/*
 * The MIT License
 *
 * Copyright 2024 hunter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package code.Service;

import code.ENUM_Utilities.CIPHER_TYPE;
import code.ENUM_Utilities.TYPE;
import code.ServiceFactory.CipherServiceFactory;


/**
 *
 * @author MR_JLTC
 */
public class CipherService {
    private Cipher cipherService;
    
    public void setCipherService(CIPHER_TYPE type){
        cipherService = CipherServiceFactory.getCipher(type);
    }
    
    public String encrypt(String plain_msg, Object key){
        return cipherService.begun_process(TYPE.ENCRYPTION, plain_msg, key);
    }
    
    public String decrypt(String enc_msg, Object key){
        return cipherService.begun_process(TYPE.DECRYPTION, enc_msg, key);
    }
}
