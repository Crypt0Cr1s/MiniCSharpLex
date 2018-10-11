/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;

import java.io.File;
import java.io.IOException;
        
/**
 *
 * @author cristobal
 */
public class Lexico {
    
   public static void main(String[] args) {
        String ruta = "/home/cristobal/Escritorio/MiniCsharpLex/MiniCsharpLex/src/minicsharplex/Lexer.flex";

        Generador(ruta);
    }

    public static void Generador(String ruta) {
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);

    }
}
