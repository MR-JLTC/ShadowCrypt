/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code.Controller;

import code.ENUM_Utilities.CIPHER_TYPE;
import code.ENUM_Utilities.TYPE;
import static code.ENUM_Utilities.TYPE.DECRYPTION;
import static code.ENUM_Utilities.TYPE.ENCRYPTION;
import code.Service.CipherService;
import code.View.ParentForm;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author hunter
 */
public class ServiceRequestController {
    private final ParentForm parent_panel;
    private final CipherService service;
    
    public ServiceRequestController(ParentForm parent, CipherService service){
        this.parent_panel = parent;
        this.service = service;
    }
    
    private void viewMainPage(){
        parent_panel.setSysLogsColor(new Color(0,204,255));
        parent_panel.appendLogInfo("Intro Page Displayed\n");
        try {
            parent_panel.setUpIntro();
            Thread.sleep(2000);
            parent_panel.setUpOptionPage();
        } catch (InterruptedException ex) {
            parent_panel.setSysLogsColor(Color.RED);
            parent_panel.appendLogInfo("ERROR IN THREAD: "+ex.getMessage());
        }
        parent_panel.appendLogInfo("Option Page Displayed");
    }
    
    private void execChosenType(int type, TYPE processType, String cipherType){
        this.type = type;
        this.cipherType = cipherType;
        this.processType = processType;
        switch(type){
            case 0 -> service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER_ASCII);
            case 1 -> service.setCipherService(CIPHER_TYPE.SHIFT_CIPHER);
            case 2 -> service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER_ASCII);
            case 3 -> service.setCipherService(CIPHER_TYPE.VIGENERE_CIPHER);
            case 4 -> service.setCipherService(CIPHER_TYPE.VERNAM_CIPHER_ASCII);
        }
        
        if(processType.toString().equals("BRUTEFORCE")) {
            parent_panel.setKeyFieldPlaceHolder(
                switch(type){
                    case 0,1 -> "End Range";
                    case 2,3,4 -> "Ex key: key1,key2,key3";
                    default -> "";
                }
            );
            parent_panel.getKeyField().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    parent_panel.getKeyField().setToolTipText("Input keywords or textfile name for your dictionary");
                }
            });
        }
        else parent_panel.setKeyFieldPlaceHolder(
            switch(type){
                case 0,1 -> "ShiftOf";
                case 2,3,4 -> "Enter Keyword";
                default -> "";
            }
        );
    }
    
    private TYPE processType;
    private String cipherType;
    private int type;
    public void start(){
        parent_panel.setVisible(true);
        parent_panel.setInputFieldPlaceHolder("Input here...");
        parent_panel.setKeyFieldPlaceHolder("Input here...");
        viewMainPage();
        parent_panel.getOptionIF().getOptionList().addListSelectionListener((javax.swing.event.ListSelectionEvent evt) -> {
            parent_panel.getInputField().setText("");
            parent_panel.getKeyField().setText("");
            String option_selected = String.valueOf(parent_panel.getOptionIF().getOptionList().getSelectedValue());
            String process_type = parent_panel.getOptionIF().getType();
            parent_panel.setProcessLogsColor(new Color(0,204,255));
            parent_panel.clrProcessInfo();
            parent_panel.setProcessInfo("TYPE: "+option_selected+"\nPROCESS_TYPE: "+process_type);
            parent_panel.enableInputFields();
            parent_panel.getBtn().setEnabled(true);
            try{
                parent_panel.clrLogInfo();
                parent_panel.setSysLogsColor(new Color(0,204,255));
                parent_panel.setLogInfo("Excellent");
                execChosenType(
                        parent_panel.getOptionIF().getOptionList().getSelectedIndex(),
                        (process_type.equals("Encryption") ? TYPE.ENCRYPTION : (process_type.equals("Decryption") ? TYPE.DECRYPTION : TYPE.BRUTEFORCE)),
                        option_selected
                );
                parent_panel.setInputFieldPlaceHolder((process_type.equals("Encryption") ? "Enter PlainText" : "Enter Cipher Text"));
            }catch(NullPointerException ex){
                parent_panel.clrLogInfo();
                parent_panel.getBtn().setEnabled(false);
                parent_panel.disableInputFields();
                parent_panel.setSysLogsColor(Color.RED);
                parent_panel.setLogInfo(ex.getMessage()+"\n"+"Select the Cipher Type again\nafter process type selected");
            }
        });
        
        parent_panel.getBtn().addActionListener((java.awt.event.ActionEvent evt) -> {
            final String text = parent_panel.getInputField().getText();
            final String keyword = parent_panel.getKeyField().getText();
            ArrayList<String> emptyFields = new ArrayList<>();
            if(cipherType == null || text.isBlank() || keyword.isBlank()){
                parent_panel.appendProcessInfo("Missing required Field/s");
                if(cipherType==null)
                    emptyFields.add("Cipher Type");
                if(text.isBlank())
                    emptyFields.add("Text Field");
                if(keyword.isBlank())
                    emptyFields.add("Keyword Field");
                parent_panel.clrLogInfo();
                parent_panel.setSysLogsColor(Color.RED);
                parent_panel.setLogInfo("Empty Input Field/s: "+emptyFields.toString());
            }else{
                parent_panel.clrProcessInfo();
                parent_panel.clrLogInfo();
                parent_panel.setSysLogsColor(new Color(0,204,255));
                parent_panel.setLogInfo("Required Fields Meet");
                parent_panel.setProcessInfo(
                    "TYPE: "+cipherType+"\n"+
                    "TEXT: "+text+"\n"+
                    "KEY: "+keyword+"\n"+
                    "PROCESS_TYPE: "+cipherType+"\n"+
                    "STATUS: "+ (processType.toString().equals("ENCRYPTION") ? "Encrypted" : "Decrypted")
                );
                try{
                    if(processType.toString().equals("BRUTEFORCE")) bruteForce();
                    else{
                        parent_panel.setResult(
                           switch(processType){
                                case ENCRYPTION -> service.encrypt(text, (type==0 || type==1) ? Integer.valueOf(keyword) : keyword);
                                case DECRYPTION -> service.decrypt(text, (type==0 || type==1) ? Integer.valueOf(keyword) : keyword);
                                default -> "Null";
                           }
                        );
                    }
                }catch(NumberFormatException ex){
                    parent_panel.clrLogInfo();
                    parent_panel.setSysLogsColor(Color.RED);
                    parent_panel.setLogInfo(Arrays.toString(ex.getStackTrace())+"\nRequired key for Shift Cipher Types is number only");
                }
            }
        });
    }
    
    private void bruteForce(){
         switch(type){
                case 0,1 -> bruteForceShiftCipher();
                case 2,3,4 -> bruteForceCharBasedCipher();
          }
    }
    
    private void bruteForceShiftCipher(){
        int endRange = Integer.parseInt(parent_panel.getKeyField().getText());
        parent_panel.clrProcessInfo();
        parent_panel.clrResult();
        parent_panel.setSysLogsColor(new Color(0,204,255));
        parent_panel.setProcessLogsColor(new Color(0,204,255));
        for(int startRange=1; startRange<=endRange; startRange++){
           parent_panel.appendResult(startRange+"> "+service.decrypt(parent_panel.getInputField().getText(), startRange)+"\n");
           parent_panel.appendProcessInfo("Key> "+startRange+"\n");
       }
       parent_panel.clrLogInfo();
       parent_panel.setLogInfo("Iteration Ended");
    }
    
    private void bruteForceCharBasedCipher(){
        parent_panel.clrProcessInfo();
        parent_panel.clrResult();
        parent_panel.clrLogInfo();
        parent_panel.setSysLogsColor(new Color(0,204,255));
        parent_panel.setProcessLogsColor(new Color(0,204,255));
        ArrayList<String> keywords = new ArrayList<>();
        final String key = parent_panel.getKeyField().getText();
        if(!isThisATextFile(key).equals("Invalid")) keywords.addAll(read(key));
        else keywords.addAll(Arrays.asList(key.split(",")));
        int iteration=0;
        for(String keyword : keywords){
            parent_panel.appendResult(iteration+"> "+service.decrypt(parent_panel.getInputField().getText(), keyword)+"\n");
            parent_panel.appendProcessInfo("Key"+iteration+"> "+keyword+"\n");
            iteration++;
        }
        parent_panel.appendLogInfo("Iteration Ended");
    }
    
    private String isThisATextFile(String input){
        Pattern pattern = Pattern.compile("^[a-z]+.txt$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches() ? input : "Invalid";
    }
    
    private ArrayList<String> read(String fileName) {
        
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dictionary/"+fileName));
            String line;
            while((line=reader.readLine())!=null){
                data.add(line);
            }
        } catch (FileNotFoundException ex) {
            parent_panel.clrLogInfo();
            parent_panel.clrProcessInfo();
            parent_panel.setProcessLogsColor(new Color(0,204,255));
            parent_panel.setProcessInfo("Status: Failed");
            parent_panel.setResult("Failure");
            parent_panel.setSysLogsColor(Color.RED);
            parent_panel.appendLogInfo("FAILED TO READ DATA: "+ex.getMessage()+"\n");
        } catch (IOException ex) {
            parent_panel.clrLogInfo();
            parent_panel.clrProcessInfo();
            parent_panel.setProcessLogsColor(new Color(0,204,255));
            parent_panel.setProcessInfo("Status: Failed");
            parent_panel.setResult("Failure");
            parent_panel.setSysLogsColor(Color.RED);
            parent_panel.appendLogInfo("FAILED TO READ DATA: "+ex.getMessage()+"\n");
        }
        return data;
    }
}
