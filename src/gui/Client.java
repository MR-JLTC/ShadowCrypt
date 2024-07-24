/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package code;

import code.Controller.ServiceRequestController;
import code.Service.CipherService;
import code.View.ParentForm;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 *
 * @author hunter
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        ParentForm parent_panel = new ParentForm();
        CipherService service = new CipherService();
        ServiceRequestController controller = new ServiceRequestController(parent_panel, service);
        controller.start();
    }
    
}
