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
import javax.swing.JOptionPane;

/**
 *
 * @author samuel
 */
public class ConfigPedidos {

    private ArrayList<Pedidos> listaPedidos;
    private ArrayList<PedidosFavoraveis> pedidosFavoraveis;
    private ArrayList<PedidosNaoTer> listaPedidosNaoTer;

    private String path;

    public ConfigPedidos() {
        this.listaPedidos = new ArrayList<Pedidos>();
        this.pedidosFavoraveis = new ArrayList<PedidosFavoraveis>();
        this.listaPedidosNaoTer = new ArrayList<PedidosNaoTer>();
        this.path = "";
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
        //Variável para lista os ingredientes
        String ingredientes = "";
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            boolean isExists = false;
            //Se o primeiro caracter da lista de ingredientes necessarios é igual a 1
            if (this.listaPedidos.get(i).getTer().substring(0, 1).equals("1")) {
                //Pega o ingrediente e verifica se ele está na lista de favoraveis
                for (int j = 0; j < this.pedidosFavoraveis.size(); j++) {
                    isExists = false;

                    // SE O INGREDIENTE EXISTE, É INCREMENTRADO
                    //É feito um recorte na string da posição 2 até o valor do tamanho dela, ou seja o final, delimitando o ingrediente
                    //Nesse caso para apenas um ingrediente no pedido
                    //É adicionado a lista de favoráveis o nome do ingrediente recortado, assim como na comparação feita na condição anterior
                    if (isExists == false) {
                        //Verifica se o ingrediente não existe mesmo na lista
                        if (!ingredientes.contains((this.listaPedidos.get(i).getTer().substring(2, this.listaPedidos.get(i).getTer().length())))) {
                            this.pedidosFavoraveis.add(new PedidosFavoraveis(this.listaPedidos.get(i).getTer().substring(2, this.listaPedidos.get(i).getTer().length()).replace(" ", "")));
                            ingredientes = ingredientes + this.listaPedidos.get(i).getTer().substring(2, this.listaPedidos.get(i).getTer().length()) + " ";
                        }
                    }

                }
            } else {
                //Começa após a quantidade de ingredientes e o espaço
                int inicio = 2;

                //Repete para cada caracter da string
                for (int j = 3; j <= this.listaPedidos.get(i).getTer().length(); j++) {
                    //System.out.print("\n\nLinha: " + i+ " -> "+inicio+ " "+ j);
                    //Verifica se o caracter é um espaço, se for o caso, delimitamos o tamanho do ingrediente com o final em j e o início como 2 
                    //(o valor de início muda para o número da posição do novo espaço mais 1, para saber o começo do próximo ingrediente)
                    if (this.listaPedidos.get(i).getTer().substring(j - 1, j).equals(" ") || (j == this.listaPedidos.get(i).getTer().length())) {
                        //System.out.print("\n"+inicio+ " "+ (j - 1));
                        //System.out.print("\n" + this.listaPedidos.get(i).getTer().substring(inicio, j));
                        for (int h = 0; h < this.pedidosFavoraveis.size(); h++) {
                            //Verifica se o ingrediente não existe mesmo na lista
                            if (!ingredientes.contains((this.listaPedidos.get(i).getTer().substring(inicio, j)))) {
                                this.pedidosFavoraveis.add(new PedidosFavoraveis(this.listaPedidos.get(i).getTer().substring(inicio, j).replace(" ", "")));
                                ingredientes = ingredientes + this.listaPedidos.get(i).getTer().substring(inicio, j) + " ";
                            }
                        }
                        if (isExists == false) {
                            //Verifica se o ingrediente não existe mesmo na lista
                            if (!ingredientes.contains((this.listaPedidos.get(i).getTer().substring(inicio, j)))) {
                                this.pedidosFavoraveis.add(new PedidosFavoraveis(this.listaPedidos.get(i).getTer().substring(inicio, j).replace(" ", "")));
                                ingredientes = ingredientes + this.listaPedidos.get(i).getTer().substring(inicio, j) + " ";
                            }
                        }
                        inicio = j;
                    }
                }
            }
        }

        for (int i = 0; i < this.listaPedidos.size(); i++) {
            for (int j = 0; j < this.pedidosFavoraveis.size(); j++) {

                if (this.listaPedidos.get(i).getTer().contains(this.pedidosFavoraveis.get(j).getTer())) {
                    this.pedidosFavoraveis.get(j).setQtdRepeticoes();
                }
            }
        }
    }

    //Procedimento que preenche os ingredientes favoraveis
    public void setListar() {
        //Variável para lista os ingredientes
        String ingredientes = "";
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            boolean isExists = false;
            //Se o primeiro caracter da lista de ingredientes necessarios é igual a 1
            if (this.listaPedidos.get(i).getNaoter().substring(0, 1).equals("1")) {
                //Pega o ingrediente e verifica se ele está na lista de favoraveis
                for (int j = 0; j <= this.listaPedidosNaoTer.size() + 1; j++) {
                    // SE O INGREDIENTE EXISTE, É INCREMENTRADO
                    //É feito um recorte na string da posição 2 até o valor do tamanho dela, ou seja o final, delimitando o ingrediente
                    //Nesse caso para apenas um ingrediente no pedido
                    //É adicionado a lista de favoráveis o nome do ingrediente recortado, assim como na comparação feita na condição anterior
                    if (isExists == false) {
                        //Verifica se o ingrediente não existe mesmo na lista
                        if (!ingredientes.contains((this.listaPedidos.get(i).getNaoter().substring(2, this.listaPedidos.get(i).getNaoter().length())))) {
                            this.listaPedidosNaoTer.add(new PedidosNaoTer(this.listaPedidos.get(i).getNaoter().substring(2, this.listaPedidos.get(i).getNaoter().length()).replace(" ", "")));
                            ingredientes = ingredientes + this.listaPedidos.get(i).getNaoter().substring(2, this.listaPedidos.get(i).getNaoter().length()) + " ";
                        }
                    }

                }
            } else {
                //Começa após a quantidade de ingredientes e o espaço
                int inicio = 2;

                //Repete para cada caracter da string
                for (int j = 3; j <= this.listaPedidos.get(i).getNaoter().length(); j++) {
                    //System.out.print("\n\nLinha: " + i+ " -> "+inicio+ " "+ j);
                    //Verifica se o caracter é um espaço, se for o caso, delimitamos o tamanho do ingrediente com o final em j e o início como 2 
                    //(o valor de início muda para o número da posição do novo espaço mais 1, para saber o começo do próximo ingrediente)
                    if (this.listaPedidos.get(i).getNaoter().substring(j - 1, j).equals(" ") || (j == this.listaPedidos.get(i).getNaoter().length())) {
                        //System.out.print("\n"+inicio+ " "+ (j - 1));
                        //System.out.print("\n" + this.listaPedidos.get(i).getTer().substring(inicio, j));
                        for (int h = 0; h < this.pedidosFavoraveis.size(); h++) {
                            //Verifica se o ingrediente não existe mesmo na lista
                            if (!ingredientes.contains((this.listaPedidos.get(i).getNaoter().substring(inicio, j)))) {
                                this.listaPedidosNaoTer.add(new PedidosNaoTer(this.listaPedidos.get(i).getNaoter().substring(inicio, j).replace(" ", "")));
                                ingredientes = ingredientes + this.listaPedidos.get(i).getNaoter().substring(inicio, j) + " ";
                            }
                        }
                        if (isExists == false) {
                            //Verifica se o ingrediente não existe mesmo na lista
                            if (!ingredientes.contains((this.listaPedidos.get(i).getNaoter().substring(inicio, j)))) {
                                this.listaPedidosNaoTer.add(new PedidosNaoTer(this.listaPedidos.get(i).getNaoter().substring(inicio, j).replace(" ", "")));
                                ingredientes = ingredientes + this.listaPedidos.get(i).getNaoter().substring(inicio, j) + " ";
                            }
                        }
                        inicio = j;
                    }
                }
            }
        }

        for (int i = 0; i < this.listaPedidos.size(); i++) {
            for (int j = 0; j < this.listaPedidosNaoTer.size(); j++) {
                if (this.listaPedidos.get(i).getTer().contains(this.listaPedidosNaoTer.get(j).getNaoter())) {
                    this.listaPedidosNaoTer.get(j).setQtdRepeticoes();
                }
            }
        }
    }

    public String getIngredientes() {
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            //Se o primeiro caracter da lista de ingredientes desnecessário é igual a 1
            if (this.listaPedidos.get(i).getNaoter().substring(0, 1).equals("1")) {
                //Pega o ingrediente e verifica se ele está na lista dos necessários, 
                //se estiver na linha(no pedido) ele é removido da lista.
                for (int j = 0; j < this.listaPedidos.size(); j++) {
                    if (this.listaPedidos.get(j).getTer().contains(this.listaPedidos.get(i).getNaoter().substring(2, this.listaPedidos.get(i).getNaoter().length()))) {
                        this.listaPedidos.get(j).setTer("");
                    }
                }
                //Limpa o ingrediente já verificado
                this.listaPedidos.get(i).setNaoter("");
                //No caso do valor ser 0, simplesmente ignora e segue o código
            } else if (this.listaPedidos.get(i).getNaoter().substring(0, 1).equals("0")) {
                continue;
            } else {
                //Começa após a quantidade de ingredientes e o espaço
                int inicio = 2;
                //Repete para cada caracter da string
                for (int j = 3; j < this.listaPedidos.get(i).getNaoter().length(); j++) {
                    //Verifica se o caracter é um espaço, se for delimitamos o tamanho do ingrediente com o final em j
                    //e o início como 2 (o valor de início muda de acordo com a repetição ao encontrar um novo espaço)
                    if (this.listaPedidos.get(i).getNaoter().substring(j - 1, j).equals(" ")) {
                        for (int h = 0; h < this.listaPedidos.size(); h++) {
                            //Pega o ingrediente e verifica se ele está na lista dos necessários, 
                            //se estiver na linha(no pedido) ele é removido da lista.
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
                            ingredientes = ingredientes + this.listaPedidos.get(i).getTer().substring(inicio, j);
                            if (!ingredientes.substring(ingredientes.length() - 1, ingredientes.length()).equals(" ")) {
                                ingredientes = ingredientes + " ";
                            }
                            quantidade++;
                        }
                        inicio = j;
                    }
                }
            }
        }
        return quantidade + ingredientes.substring(0, ingredientes.length() - 1);

    }

    public String getPontos() {
        int pontos = 0;
        for (int i = 0; i < this.listaPedidos.size(); i++) {
            if (!(this.listaPedidos.get(i).getTer().equals(""))) {
                pontos++;
            }
        }
        return "Pontos: " + pontos;
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

    public int getSimulacao() throws IOException {
        // SELECIONA QUANTAS POSIÇÕES QUE OS RESULTADOS TERÃO
        int posicoes = this.listaPedidosNaoTer.size();
        int resultados[] = new int[posicoes];
        int posResultados = 0;

        // REALIZA A COMPARAÇÃO DOS VALORES INDIVIDUAIS;
        for (int pos = 0; pos < this.listaPedidosNaoTer.size(); pos++) {
            BufferedReader buffRead = new BufferedReader(new FileReader(this.path));
            String linha = "";
            int qtd = 0;
            int i = -1;
            while (true) {
                i++;
                if (linha != null) {
                    if (i % 2 == 0) {
                        if (linha.contains(this.listaPedidosNaoTer.get(pos).getNaoter())) {
                            System.out.println("Pedido " + linha + " cancelado :(");
                            resultados[posResultados] = qtd++;
                        }
                    }
                } else {
                    break;
                }
                linha = buffRead.readLine();
            }
            buffRead.close();
            qtd = 0;
        }

        // RESULTADO
        /*System.out.println("\n\nESTATISTICA");
        System.out.println("Perdemos " + qtd + " clientes");
        System.out.println("Ainda nos resta " + (this.listaPedidosNaoTer.size() - qtd) + " de " + (this.listaPedidosNaoTer.size()) + " clientes \n\n");
        System.out.println("------------------------");*/
        return 0;
    }

}
