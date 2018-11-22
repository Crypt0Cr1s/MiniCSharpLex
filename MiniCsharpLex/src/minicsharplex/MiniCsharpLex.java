/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        
        System.out.print("Ingrese la ruta del archivo a analizar: ");
        String path = terminal.next();
        String [] path2 ={path};
        
        Reader reader;
        
        reader = new BufferedReader(new FileReader(path));
      
        AnalizadorLexico miAnalizer = new AnalizadorLexico(reader);
        AnalizadorSintactico Sintactico= new AnalizadorSintactico(miAnalizer);
        Sintactico.parse();
        
        System.out.print("Se ha terminado de analizar.");
        
        //abro codigo para leer archivo y obtener una lista con los tokens a volcar. 
        
        String Valor;
        // codigo para volcar lista tokens a un archivo.
         System.out.print("\n");
        System.out.print("Ingrese el nombre del archivo a salir: ");
        String archivosal = terminal.next();
        archivosal = archivosal+".txt"; 
      
        FileWriter ef = new FileWriter(archivosal);
        BufferedWriter e = new BufferedWriter(ef);
        e.write("Identifier                 Type                Description         Valor               Scope");
        e.newLine();
        
        
        
        for (int i = 0; i < Tsimbols.output.size(); i++) 
            {
               Salida aux = Tsimbols.output.get(i);
               String Tipo = aux.simbolo.type;
               String Descripcion = aux.simbolo.elementType;
               if(aux.simbolo.value != null)
               {
                    Valor = aux.simbolo.value.toString();
               }
               else
               {
                   Valor = "??";
               }
               String Current = aux.simbolo.ambito;
               e.write(aux.nombre + Output(aux.nombre,30,0) + Tipo +Output(Tipo, 50,30)+ Descripcion+Output(Descripcion, 70,50)+Valor+Output(Valor, 90,70)+Current);
               e.newLine();
            }
            e.close();
            ef.close(); 
       
            
            
        
        
       
        
        
    }
    
    static public  String Output(String size, int separador, int columnas)
    {
            String nuevoEspacio = "";
            int t = size.length();
            int ciclos = separador - (t+columnas);
            for (int i = 0; i < ciclos; i++) 
            {
                nuevoEspacio+=" ";
            }
            return nuevoEspacio;
    }
   
    
}
