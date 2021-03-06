/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author samuel
 */
public class PedidosFavoraveis {
    private String ter;
    private int qtdRepeticoes;
    private int qtdEstimada;

    public PedidosFavoraveis(String podeTer) {
        this.ter = podeTer;
        this.qtdRepeticoes = 1;
        this.qtdEstimada = 0;
    }

    public String getTer() {
        return this.ter;
    }

    public int getQtdRepeticoes() {
        return this.qtdRepeticoes;
    }

    public void setQtdRepeticoes() {
        this.qtdRepeticoes += 1;
    }

    public int getQtdEstimada() {
        return this.qtdEstimada;
    }

    public void setQtdEstimada(int totalClientes) {
        this.qtdEstimada = totalClientes - this.qtdRepeticoes;
    }
    
}
