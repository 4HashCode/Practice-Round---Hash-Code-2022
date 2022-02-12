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

    private ArrayList<Pedidos> listaPedidos;

    public ConfigPedidos() {
        this.listaPedidos = new ArrayList<Pedidos>();
    }

    public void getPedidos() {
        System.out.println("--- INGREDIENTES NECESSÁRIOS ---");
        for(int i = 0; i < this.listaPedidos.size(); i++){
            System.out.println("Pedido "+(i+1)+" ---> "+this.listaPedidos.get(i).getTer());
            
        }
        
        System.out.println("-- INGREDIENTES DESNECESSÁRIOS --");
        for(int i = 0; i < this.listaPedidos.size(); i++){
            System.out.println("Pedido "+(i+1)+" ---> "+this.listaPedidos.get(i).getNaoter());
        }
    }

    public void leitor(String path) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        int i = -1;
        String[] pedidos = new String[2];
        while (true) {

            i++;
            if (linha != null) {
                if (i % 2 == 0) {
                    pedidos[0] = linha;
                } else if (i % 2 != 0 && i != 1) {
                    pedidos[1] = linha;

                    if(this.listaPedidos.size() <= 100000)
                        listaPedidos.add(new Pedidos(pedidos[0], pedidos[1]));
                    else
                        JOptionPane.showMessageDialog(null, "Muitos pedidos no momento, tente novamente mais tarde");
                }
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
    }

}
