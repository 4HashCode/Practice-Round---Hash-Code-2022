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

                    if (this.listaPedidos.size() <= 100000) {
                        listaPedidos.add(new Pedidos(pedidos[0], pedidos[1]));
                    } else {
                        JOptionPane.showMessageDialog(null, "Muitos pedidos no momento, tente novamente mais tarde");
                    }
                }
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
    }

    public void getPedidos() {
        System.out.println("--- INGREDIENTES NECESSÁRIOS ---");
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            System.out.println("Pedido " + (i + 1) + " ---> " + this.listaPedidos.get(i).getTer());

        }

        System.out.println("-- INGREDIENTES DESNECESSÁRIOS --");
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            System.out.println("Pedido " + (i + 1) + " ---> " + this.listaPedidos.get(i).getNaoter());
        }
    }

    public String getIngredientes() {
        for (int i = 0; i < this.listaPedidos.size(); i++) {

            if (this.listaPedidos.get(i).getNaoter().substring(0, 1).equals("1")) {
                for (int j = 0; j < this.listaPedidos.size(); j++) {
                    if (this.listaPedidos.get(j).getTer().contains(this.listaPedidos.get(i).getNaoter().substring(2, this.listaPedidos.get(i).getNaoter().length()))) {
                        this.listaPedidos.get(j).setTer("");
                    }
                }
                this.listaPedidos.get(i).setNaoter("");
            } else if (this.listaPedidos.get(i).getNaoter().substring(0, 1).equals("0")) {
                continue;
            } else {
                int inicio = 2;
                for (int j = 3; j < this.listaPedidos.get(i).getNaoter().length(); j++) {

                    if (this.listaPedidos.get(i).getNaoter().substring(j - 1, j).equals(" ")) {
                        for (int h = 0; h < this.listaPedidos.size(); h++) {
                            if (this.listaPedidos.get(h).getTer().contains(this.listaPedidos.get(i).getNaoter().substring(inicio, j - 1))) {
                                this.listaPedidos.get(h).setTer("");
                            }
                        }
                        inicio = j + 1;
                    }
                }
            }
        }
        String ingredientes = " ";
        int quantidade = 0;

        for (int i = 0; i < this.listaPedidos.size(); i++) {
            if (this.listaPedidos.get(i).getTer() != "") {
                int inicio = 2;
                for (int j = 3; j <= this.listaPedidos.get(i).getTer().length(); j++) {
                    if (this.listaPedidos.get(i).getTer().substring(j - 1, j).equals(" ") || (j == this.listaPedidos.get(i).getTer().length())) {
                        if (!ingredientes.contains(this.listaPedidos.get(i).getTer().substring(inicio, j - 1))) {
                            ingredientes = ingredientes + this.listaPedidos.get(i).getTer().substring(inicio, j) + " ";
                            quantidade++;
                        }
                        inicio = j;
                    }
                }
            }
        }
        return quantidade + ingredientes;

    }
}
