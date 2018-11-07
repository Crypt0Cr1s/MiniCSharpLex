/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cristobal
 */
public class MiniCsharpLex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        
        Scanner terminal = new Scanner(System.in);
        ArrayList<Object> tsimbol = new ArrayList();
        System.out.print("Ingrese la ruta del archivo a analizar: ");
        String path = terminal.next();
        String [] path2 ={path};
        
        Reader reader;
        
        reader = new BufferedReader(new FileReader(path));
      
        AnalizadorLexico miAnalizer = new AnalizadorLexico(reader);
        AnalizadorSintactico Sintactico= new AnalizadorSintactico(miAnalizer);
        tsimbol.add(Sintactico.parse().value);
        
        System.out.print("Se ha terminado de analizar.");
        //abro codigo para leer archivo y obtener una lista con los tokens a volcar. 
        
       
        // codigo para volcar lista tokens a un archivo.
        /* System.out.print("Ingrese el nombre del archivo a salir: ");
        String archivosal = terminal.next();
        archivosal = archivosal+".out"; 
        
       /* PrintWriter escribir = new PrintWriter(archivosal, "UTF-8");
        /
        for (int i = 0;i <= (tokens.size()-1);i++){
            String agregar=tokens.get(i);
            escribir.println(agregar);
            
        }
        escribir.close();*/
        
        
       
        
        
    }
   
    
}
