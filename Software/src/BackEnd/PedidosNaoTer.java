/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author Programador
 */
public class PedidosNaoTer {

    private String naoter;
    private int qtdRepeticoes;

    public PedidosNaoTer(String naoPode) {
        this.naoter = naoPode;
        this.qtdRepeticoes = 0;
    }

    public String getNaoter() {
        return this.naoter;
    }

    public void setNaoter(String valor) {
        this.naoter = valor;
    }

    public int getQtdRepeticoes() {
        return this.qtdRepeticoes;
    }

    public void setQtdRepeticoes() {
        this.qtdRepeticoes += 1;
    }
}
