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
    String podeTer, naoPode;
    public static String[] ter = new String[100];
    public static String[] naoter = new String[100];
    public static int posicao = 0;


    static public void addPedidos(String podeTer, String naoPode) {
        ter[posicao] = podeTer;
        naoter[posicao] = naoPode;
        posicao++;
    }
    
    static public int getPosicao(){
        return posicao;
    }
    
    static public void getPedidos(){
        System.out.print("\n---- Ingredientes que podem ter ---");
        for(int i = 0; i < posicao; i++){
             System.out.print("\nPedido " + i + " ---> " + ter[i]);
        }
        
        System.out.print("\n\n---- Ingredientes que não podem ter ---");
        for(int i = 0; i < posicao; i++){
             System.out.print("\nPedido " + i + " ---> " + naoter[i]);
        }
    }
  

}
