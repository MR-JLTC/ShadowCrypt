<div align="center">
▒█▀▀▀█ █░░█ █▀▀█ █▀▀▄ █▀▀█ █░░░█ ▒█▀▀█ █▀▀█ █░░█ █▀▀█ ▀▀█▀▀ <br>
░▀▀▀▄▄ █▀▀█ █▄▄█ █░░█ █░░█ █▄█▄█ ▒█░░░ █▄▄▀ █▄▄█ █░░█ ░░█░░ <br>
▒█▄▄▄█ ▀░░▀ ▀░░▀ ▀▀▀░ ▀▀▀▀ ░▀░▀░ ▒█▄▄█ ▀░▀▀ ▄▄▄█ █▀▀▀ ░░▀░░<br>
<h4>Available Versions:</h4>
  
[![Static Badge](https://img.shields.io/badge/Android-%20Termux-green)](https://github.com/MR-JLTC/ShadowCrypt/releases/download/v3.0/ShadowCrypt.zip)
[![Static Badge](https://img.shields.io/badge/Windows-%20Console-blue)](https://github.com/MR-JLTC/ShadowCrypt/releases/download/v3.0/Setup_ShadowCryptV3.0.exe)
[![Static Badge](https://img.shields.io/badge/Windows-%20DesktopApp-blue)](https://github.com/MR-JLTC/ShadowCrypt/releases/download/v3.0/ShadowCryptAppV3.7.exe)
</div>

<h4>:eye_speech_bubble:Beta-Release Version:</h4>

[![Static Badge](https://img.shields.io/badge/Android-%20MobileApp-green?style=flat&logo=android&logoColor=white)](https://github.com/MR-JLTC/ShadowCrypt/releases/download/v3.7/ShadowCrypt.apk)

--------------
### :octocat:Termux Installation
1. Download the zip file, then extract it or follow this command.
```
cd storage/downloads
```
```
unzip ShadowCrypt.zip
```   
2. After extracting the file, run the following command:
```
cd ShadowCrypt
```
```
bash run.sh
```
-------------
### :octocat:Windows Installation
1. Download the system installer file
2. Click the installer, then let it finish the installation process
3. To run just click the `.exe` file

--------------
### :octocat: JAR Library Integration Guide
1. Click [here](https://github.com/MR-JLTC/ShadowCrypt/releases/download/v3.7/ShadowCryptLibV1.5.jar) to download the library
2. Create/Open a new Java Application project.
3. Right-click the project → Select Properties.
4. Go to Libraries → Click Add JAR/Folder.
5. Locate and select ShadowCrypt_LibV1.5.jar
6. Click OK.

```pascal
/* NEW FEATURE <<-BINARY CONVERSION->> */

//-> Text to Binary
System.out.println("Result: "+ ShadowCrypt.ProcessCipher(
  CIPHER_TYPE.BINARY, 
  TYPE.ENCRYPTION, 
  "JLTC", 
  "none")
);
//-> Binary to Text
System.out.println("Result: "+ ShadowCrypt.ProcessCipher(
   CIPHER_TYPE.BINARY, 
   TYPE.DECRYPTION, 
   "01001010 01001100 01010100 01000011", 
   "none")
);
```

```pascal
/* EXAMPLE Encryption & Decryption */
String enc_text = ShadowCrypt.ProcessCipher(
  CIPHER_TYPE.SHIFT_CIPHER_ASCII, 
  TYPE.ENCRYPTION, 
  "Hello World", 
  13)
);

System.out.println("Result: "+ ShadowCrypt.ProcessCipher(
  CIPHER_TYPE.SHIFT_CIPHER_ASCII, 
  TYPE.DECRYPTION, 
  enc_text, 
  13)
);
-------------------->
String enc_text = ShadowCrypt.ProcessCipher(
  CIPHER_TYPE.VIGENERE_CIPHER_ASCII, 
  TYPE.ENCRYPTION, 
  "Hello World", 
  "cipher")
);

System.out.println("Result: "+ ShadowCrypt.ProcessCipher(
  CIPHER_TYPE.VIGENERE_CIPHER_ASCII, 
  TYPE.DECRYPTION, 
  enc_text, 
  "cipher")
);
```

```pascal
/* Other method*/
ShadowCrypt.developerInfo();
ShadowCrypt.DevInfo();
```

```pascal
/* Available Cipher Types*/
CIPHER_TYPE.BINARY
CIPHER_TYPE.SHIFT_CIPHER
CIPHER_TYPE.SHIFT_CIPHER_ASCII
CIPHER_TYPE.VIGENERE_CIPHER
CIPHER_TYPE.VIGENERE_CIPHER_ASCII
CIPHER_TYPE.VERNAM_CIPHER_ASCII
```
--------------
## :octocat:SYSTEM PREVIEW
`Windows App`

![Screenshot (2323)](https://github.com/user-attachments/assets/dfd41964-6bb6-4d6a-9f1d-580657731327)
![Screenshot 2024-07-25 092122](https://github.com/user-attachments/assets/1416e7f2-c54e-4fd5-8ed1-d11a7f79b8c6)

--------------
`Mobile App`

<img src="https://github.com/user-attachments/assets/6b5e8886-871d-4977-acc9-0b61bfbab726" width="300"/>
<img src="https://github.com/user-attachments/assets/aaafb993-510b-4248-84e7-a553c8ddb392" width="300"/>
<img src="https://github.com/user-attachments/assets/40c4ba52-7477-4ee8-b8a7-e45d45a1b5ae" width="300"/>
<img src="https://github.com/user-attachments/assets/c06b46cb-44aa-47e9-aad5-518bd3db2239" width="300"/>






