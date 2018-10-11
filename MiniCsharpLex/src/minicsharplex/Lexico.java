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
    
   public static void main(String[] args) throws Exception {
        String ruta = "/home/cristobal/Documentos/Proyectos Lenguajes/MiniCsharpLex/MiniCsharpLex/src/minicsharplex/Lexer.flex";
        String[] ruta2 = {"-parser", "AnalizadorSintactico", "/home/cristobal/Documentos/Proyectos Lenguajes/MiniCsharpLex/MiniCsharpLex/src/minicsharplex/Sintax.cup"};
        Generador(ruta,ruta2);
    }

    public static void Generador(String ruta,String[] ruta2) throws IOException, Exception {
        File archivo = new File(ruta);
        jflex.Main.generate(archivo);
        java_cup.Main.main(ruta2);
    }
}
