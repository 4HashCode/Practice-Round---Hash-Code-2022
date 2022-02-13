/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;

/**
 *
 * @author samuel
 */
public class Pedidos {

    protected String ter;
    protected String naoter;

    public Pedidos(String podeTer, String naoPode) {
        this.ter = podeTer;
        this.naoter = naoPode;
    }

    public String getTer() {
        return ter;
    }

    public String getNaoter() {
        return naoter;
    }
    
    public void setTer(String valor) {
        this.ter = valor;
    }

    public void setNaoter(String valor) {
        this.naoter = valor;
    }




}
