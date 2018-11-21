/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author cristobal
 */
public class Includes {
    
     public static void main(String archivo) throws IOException {
        // TODO code application logic here
        // Se leen los includes del archivo padre
       
        lectorDeArchivo();
        if (listadoIncludes.size() > 0)
        {
            for (int i = 0; i < listadoIncludes.size(); i++) {
                analizadorIncludes(listadoIncludes.get(i));
            }
        }
    }
     
     static ArrayList<String> listadoIncludes = new ArrayList<String>();
    static String PATH;
    public static void lectorDeArchivo() throws FileNotFoundException, IOException
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            PATH = selectedFile.getParent();
            try(BufferedReader br = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()))) 
            {
                String line = br.readLine();

                while (line != null) {
                    if (line.contains("#include"))
                    {
                        listadoIncludes.add(line);
                    }
                    line = br.readLine();
                }
            }
        }
        
        System.out.println("El archivo ha sido analizado con éxito.");
    }
    
    public static void analizadorIncludes(String includeString) throws FileNotFoundException, IOException
    {
        String[] includeSplit = includeString.split(" ");
        //#include <nombreArchivo>;
        String includeFileName = includeSplit[1];
        includeFileName = includeFileName.substring(1, includeFileName.length() - 1);
        try(BufferedReader br = new BufferedReader(new FileReader(PATH + "//" + includeFileName))) 
        {
            String line = br.readLine();

            while (line != null) {
                if (line.contains("#include"))
                {
                    for (int i = 0; i < listadoIncludes.size(); i++) {
                        if(listadoIncludes.get(i).equals(line))
                        {
                            System.out.println("Error, el archivo " + includeFileName + " ya se encuentra incluido. Genera un loop infinito");
                            return;
                        }
                    }
                    
                    listadoIncludes.add(line);
                }
                line = br.readLine();
            }
            System.out.println("El archivo ha sido analizado con éxito.");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Error, el archivo " + includeFileName + " no existe.");
        }
    }
    
}
