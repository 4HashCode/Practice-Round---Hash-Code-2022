/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

import FrontEnd.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel
 */
public class ConfigPedidos {

    private ArrayList<Pedidos> listaPedidos;
    private ArrayList<PedidosFavoraveis> pedidosFavoraveis;
    private ArrayList<PedidosNaoTer> listaPedidosNaoTer;
    private String podemosTirar;
    private String path;

    public ConfigPedidos() {
        this.listaPedidos = new ArrayList<Pedidos>();
        this.pedidosFavoraveis = new ArrayList<PedidosFavoraveis>();
        this.listaPedidosNaoTer = new ArrayList<PedidosNaoTer>();
        this.path = "";
        this.podemosTirar = "";
    }

    public void leitor(String path) throws IOException {
        this.path = path;
        BufferedReader buffRead = new BufferedReader(new FileReader(this.path));
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
        buffRead.close();
    }

    //Procedimento que preenche os ingredientes favoraveis
    public void setListarFavoraveis() {
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            String ingredientes = this.listaPedidos.get(i).getTer().trim();

            // TRANSFORMA A LINHA EM VALORES
            final Pattern separarPorEspaco = Pattern.compile(" ");
            String[] listaIngredientes = separarPorEspaco.split(ingredientes);

            for (int j = 1; j < listaIngredientes.length; j++) {
                // VALIDA SE ESSE VALOR JA FOI ADD
                boolean isExists = false;

                for (int nLista = 0; nLista < this.pedidosFavoraveis.size(); nLista++) {
                    // SE A LISTA JÁ CONTÊM O INGREDIENTE DEFINE isExists COMO TRUE E INCREMENTA O VETOR
                    if (this.pedidosFavoraveis.get(nLista).getTer().equalsIgnoreCase(listaIngredientes[j])) {
                        this.pedidosFavoraveis.get(nLista).setQtdRepeticoes();
                        isExists = true;
                    }
                }

                // SE NÃO EXISTIR, CRIA UMA NOVA POSIÇÃO
                if (isExists == false) {
                    this.pedidosFavoraveis.add(new PedidosFavoraveis(listaIngredientes[j]));
                }
            }
        }
    }

    //Procedimento que preenche os ingredientes favoraveis
    public void setListarDesfavoraveis() {
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            String ingredientes = this.listaPedidos.get(i).getNaoter().trim();

            // TRANSFORMA A LINHA EM VALORES
            final Pattern separarPorEspaco = Pattern.compile(" ");
            String[] listaIngredientes = separarPorEspaco.split(ingredientes);

            for (int j = 1; j < listaIngredientes.length; j++) {
                // VALIDA SE ESSE VALOR JA FOI ADD
                boolean isExists = false;

                for (int nLista = 0; nLista < this.listaPedidosNaoTer.size(); nLista++) {
                    // SE A LISTA JÁ CONTÊM O INGREDIENTE DEFINE isExists COMO TRUE E INCREMENTA O VETOR
                    if (this.listaPedidosNaoTer.get(nLista).getNaoter().equalsIgnoreCase(listaIngredientes[j])) {
                        this.listaPedidosNaoTer.get(nLista).setQtdRepeticoes();
                        isExists = true;
                    }
                }

                // SE NÃO EXISTIR, CRIA UMA NOVA POSIÇÃO
                if (isExists == false) {
                    this.listaPedidosNaoTer.add(new PedidosNaoTer(listaIngredientes[j]));
                }
            }
        }
        System.out.println("\n\n");
    }

    public String getIngredientes() {
        // total de clientes
        //int pontos = this.listaPedidos.size();
        int pontos = 0;
        int qtdIngredientes = 0;
        String pizzaFinal = "";

        for (int i = 0; i < this.pedidosFavoraveis.size(); i++) {
            if (!(this.podemosTirar.contains(this.pedidosFavoraveis.get(i).getTer()))) {
                pizzaFinal += " " + this.pedidosFavoraveis.get(i).getTer();
                qtdIngredientes++;
                continue;
            }
        }
        // TRANSFORMA A LINHA EM VALORES
        final Pattern separarPorEspaco = Pattern.compile(" ");
        String[] listaIngredientes = separarPorEspaco.split(pizzaFinal);

        // ----------------- PONTUAÇÃO ---------------------------------------------------
        //Loop para ler as linhas dos pedidos
        for (int j = 0; j < this.listaPedidos.size(); j++) {
            //Armazena os ingredientes de cada pedido(linha) no vetor a cada repetição
            //A cada repetição o valor da variável Pedidos é sobreposto para os ingredientes da nova linha
            String[] Pedidos = separarPorEspaco.split(this.listaPedidos.get(j).getTer().substring(2, this.listaPedidos.get(j).getTer().length()));
            //Contador para impedir a remoção de mais de um ponto a cada linha
            int c = 0;
            //Loop para alternar os ingredientes do pedido presente na linha atual
            for (int k = 0; k < Pedidos.length; k++) {
                //Loop para alternar os ingredientes que devem ter na pizza
                for (int l = 0; l < listaIngredientes.length - 1; l++) {
                    //Verifica se cada ingrediente do pedido é igual aos presentes da pizza
                    if (Pedidos[k].equals(listaIngredientes[l + 1])) {

                        boolean tem = false;
                        //Loop para verificar se o ingrediente faz parte de um pedido que o ingrediente deve ser removido(assim não deve contar o "lixo", teoricamente)
                        for (int m = 0; m < this.listaPedidosNaoTer.size(); m++) {

                            if (this.listaPedidos.get(j).getNaoter().contains(this.listaPedidosNaoTer.get(m).getNaoter())) {
                                //pizzaFinal.replace(listaIngredientes[l + 1], "");

                                //Informa se o pedido tem o ingrediente que será removido,
                                //se for o caso não deve ser contabilizado o pedido como 1 ponto
                                tem = true;
                            }
                        }

                        c++;

                        //Se todos os ingredientes do pedido estiverem na pizza e o pedido não tiver na linha de baixo o ingrediente a ser removido
                        if (c == Pedidos.length && tem == false) {
                            pontos += 1;
                            break;
                        }

                    }
                }
            }

        }

        System.out.println("\n\n-------------------------");
        System.out.println("Pontuação: " + pontos);

        return String.valueOf(qtdIngredientes) + pizzaFinal;

    }

    public void setSelecionarRentaveis() {

        // DEFINE QUANTOS CLIENTES QUE CADA INGREDIENTE FARÁ PERDER, SE FOR TIRADO DA PIZZA
        for (int i = 0; i < this.pedidosFavoraveis.size(); i++) {
            // DEFINE QUANTOS CLIENTES VÃO FICAR, SE O INGREDIENTE FOR TIRADO
            this.pedidosFavoraveis.get(i).setQtdEstimada(this.listaPedidos.size());
            System.out.println(" Se tirar o " + this.pedidosFavoraveis.get(i).getTer() + ", ficará " + this.pedidosFavoraveis.get(i).getQtdEstimada() + " clientes");
        }

        // RETIRA OS INGREDIENTES DESNCESSARIOS, PARA QUE AGRADE O MAIOR NUMERO DE CLIENTES POSSIVEIS
        try {
            this.setSepararIngredientes();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSepararIngredientes() throws IOException {
        
        // PEGA TDS OS INGREDIENTES QUE NÃO PODEM
        for (int i = 0; i < this.listaPedidosNaoTer.size(); i++) {
            boolean isExists = false;
            
            // VERIFICA NA LISTA DE PEDIDOS TEM O INGREDIENTE QUE NÃO PODE
            for (int j = 0; j < this.listaPedidos.size(); j++) {
                if(this.listaPedidos.get(j).getTer().contains(this.listaPedidosNaoTer.get(i).getNaoter())){
                    isExists = true;
                }
            }
            
            // ------------------

            // SE NGM PEDIU ESSA EXCEÇÃO
            if (isExists == false) {
                this.podemosTirar += (" " + this.listaPedidosNaoTer.get(i).getNaoter());
            }
        }

        System.out.println("\n\nO mais viavél é retirar o " + this.podemosTirar);
    }

}
