/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;

/**
 *
 * @author cristobal
 */
public class Tsimbols {
    String simbolo;
    String tipo;
    String ambito;
    
   /* public t_simbolo (){
    
    this.simbolo = "NA";
    this.ambito = "NA";
    this.tipo = "NA";
}*/

/*public t_simbolo(String simbolo, String tipo, String ambito){

    this.simbolo = simbolo;
    this.ambito = ambito;
    this.tipo = tipo;
    
} */

public Tsimbols t_simbolo(String token, String type){

    simbolo = token;
    ambito = "Sin ambito";
    tipo = type;
    return null;
}
    
}
