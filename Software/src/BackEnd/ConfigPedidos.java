/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel
 */

public class ConfigPedidos {
    
    public static void leitor(String path) throws IOException {
        
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        int i = -1;
        String[] pedidos = new String[2];
        while (true) {
            
            i++;
            if (linha != null) {
                if(i%2 == 0){
                    pedidos[0] = linha;
                }
                else if(i%2 != 0 && i != 1){
                    pedidos[1] = linha;
                    Pedidos.addPedidos(pedidos[0], pedidos[1]);
                }
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
    }

}
