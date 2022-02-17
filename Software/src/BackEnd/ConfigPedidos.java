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
    private int qtdIngredientesDescartados;

    private String path;

    public ConfigPedidos() {
        this.listaPedidos = new ArrayList<Pedidos>();
        this.pedidosFavoraveis = new ArrayList<PedidosFavoraveis>();
        this.listaPedidosNaoTer = new ArrayList<PedidosNaoTer>();
        this.path = "";
        this.podemosTirar = "";
        this.qtdIngredientesDescartados = 0;
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
                    if (this.pedidosFavoraveis.get(nLista).getTer().equals(listaIngredientes[j])) {
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
                    if (this.listaPedidosNaoTer.get(nLista).getNaoter().equals(listaIngredientes[j])) {
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
            // QTD DE REPETIÇÕES REMETE AO NUMERO DE VEZES QUE O INGREDIENTE APARECE EM CADA PIZZA
            int prejuizo = this.listaPedidos.size() - this.pedidosFavoraveis.get(i).getQtdRepeticoes();
            this.pedidosFavoraveis.get(i).setQtdPrejuizo(prejuizo);
        }

        try {
            this.getSimulacao();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getSimulacao() throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(this.path));
        String linha = "";
        int i = -1;
        while (true) {
            i++;
            if (linha != null) {
                // PEGA OS INGREDIENTES PEDIDOS NA PIZZA
                if ((i % 2 == 0) && !(i % 2 != 0) && !(i != 1)) {
                    // PEGA QUANTOS CLIENTES SERÃO PERDIDOS, PARA CADA VALOR INDIVIDUAL
                    for (int j = 0; j < this.listaPedidosNaoTer.size(); j++) {
                        if (linha.contains(this.listaPedidosNaoTer.get(j).getNaoter())) {
                            System.out.println("Pedido " + linha + " cancelado, pois tem " + this.listaPedidosNaoTer.get(j).getNaoter());
                            this.listaPedidosNaoTer.get(j).setQtdRepeticoes();
                        }
                    }
                }
            } else {
                break;
            }
            linha = buffRead.readLine();
        }
        buffRead.close();

        // RESULTADO
        System.out.println("\n\nESTATISTICA");
        for (int j = 0; j < this.listaPedidosNaoTer.size(); j++) {
            System.out.println("Perderemos " + this.listaPedidosNaoTer.get(j).getQtdRepeticoes() + " clientes se tirar o " + this.listaPedidosNaoTer.get(j).getNaoter());
            System.out.println((this.listaPedidos.size() - this.listaPedidosNaoTer.get(j).getQtdRepeticoes()) + " clientes satisfeitos");
            System.out.println("------------------------------\n");

            // SE NGM PEDIU ESSA EXCEÇÃO
            if (this.listaPedidosNaoTer.get(j).getQtdRepeticoes() == 0) {
                this.podemosTirar += (" " + this.listaPedidosNaoTer.get(j).getNaoter());
                this.qtdIngredientesDescartados++;

            } // SE O INGREDIENTE NÃO PREJUDICA 50% OU DOS CLIENTES OU MAIS, É TIRADO
            else if (((this.listaPedidos.size() - this.listaPedidosNaoTer.get(j).getQtdRepeticoes())) < (this.listaPedidos.size() / 2)) {
                this.podemosTirar += (" " + this.listaPedidosNaoTer.get(j).getNaoter());
                this.qtdIngredientesDescartados++;
            }
        }

        System.out.println("\n\nO mais viavél é retirar o " + podemosTirar);
    }

}
