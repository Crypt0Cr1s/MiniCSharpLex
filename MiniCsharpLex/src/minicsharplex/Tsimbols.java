/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicsharplex;
import java.util.*;

/**
 *
 * @author cristobal
 */
public class Tsimbols {
    static Tsimbols root = new Tsimbols(null);
    static Tsimbols top = root;
    HashMap table;
    String Operacion;
    Tsimbols prev;
    
    public Tsimbols (Tsimbols p)
    {
        table = new HashMap();
        prev = p;
    }
    
    public static int putClass(String c, String sc, String s)
    {
        if(root.table.containsKey(c))
        {
            System.out.println("  Entrando a la Clase: " + c);
            push();
            return 1;
        }
        if(sc == null)
        {
            root.table.put(c, s);
            System.out.println("  Entrando a la Clase: " + c);
            top.table.put(c, s);
            push();
            return 0;
        }
        if (!root.table.containsKey(sc))
        {
            root.table.put(c, s);
            System.out.println("  Entrando a la Clase: " + c);
            top.table.put(c, s);
            push();
            return 2;
        }
        else
        {
            root.table.put(c, s);
            System.out.println("  Entrando a la Clase: " + c);
            top.table.put(c, s);
            push();
            return 0;
        }
    }
    
    public static boolean getInterfaces(String I)
    {
        if(!root.table.containsKey(I))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static boolean put(Object name, Object tipo)
    {
        if(!top.table.containsKey(name))
        {
            Info Data = new Info(tipo.toString(),null);            
            top.table.put(name, Data);
            System.out.println("  Nuevo Identificador: "+name+" -> Ambito Actual: "+top);
            return true;
        }
        return false;
    }
    
    public static sym get(String name)
    {
        for(Tsimbols e = top; e != null; e = e.prev)
        {
            sym found = (sym) (e.table.get(name));
            if (found != null)
            {
                return found;
            }            
        }
        return null;
    }
    
    public static void push()
    {
        top = new Tsimbols(top);
        System.out.println(" -> Ambito Actual: "+top);
    }
    
    public static void pop()
    {
        top = top.prev;
        System.out.println(" -> Ambito Actual: "+top);
    }
    

    public String toString()
    {
        if(prev != null)
        {
            return prev.toString() + table;            
        }
        else
        {
            return ""+table;
        }
    }
    
    public static void Validar(String Lvalue, String Valor)
    {
        if(top.table.containsKey(Lvalue))
        {
            Info aux = (Info) (top.table.get(Lvalue));
            if(Valor.contains("+") || Valor.contains("-") || Valor.contains("*") || Valor.contains("/"))
            {
                String Resultado = Resolve(Valor);

                if(Resultado.equals("-1"))
                {
                    System.out.println("  Error: No se puede realizar la operacion de asignacion para el valor de  " + Lvalue + " -> Ambito Actual: "+top);
                }
                else
                {               
                    if(aux.type == "string" )
                    {                    
                       System.out.println("  Error: No se puede asignar un valor numerico a un string " + Lvalue + " -> Ambito Actual: "+top);
                     }
                    else
                    {                     
                         String tipo;
                        if(RevFloat(Resultado))
                        {                        
                            tipo = "integer";
                        }
                        else
                        {
                             tipo = "double";
                         }
                        if(aux.type == tipo)
                        {
                            aux.value = Resultado;
                            top.table.replace(Lvalue, aux);
                            System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Resultado + " -> Ambito Actual: "+top);
                        }
                        else
                        {
                           System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + aux.type + " & " + tipo + " no son compatibles" + " -> Ambito Actual: "+top);   
                        }
                    }
                }
            }
            else
            {
                    boolean Variable = isNumeric(Valor);
                    String tipo;
                    if (Variable == true)
                    {
                        if(aux.type == "string" )
                        {
                            System.out.println("  Error: No se puede asignar un valor numerico a un string " + Lvalue + " -> Ambito Actual: "+top);
                        }
                        else
                        {
                            if(RevFloat(Valor))
                            {
                                tipo = "integer";
                            }
                            else
                            {
                                tipo = "double";
                            }
                            if(aux.type == tipo)
                            {
                                aux.value = Valor;
                                top.table.replace(Lvalue, aux);            
                                System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Valor + " -> Ambito Actual: "+top);
                            }
                            else
                            {
                               System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + aux.type + " & " + tipo + " no son compatibles" + " -> Ambito Actual: "+top);  
                            }
                        }
                    }
                    else
                    {
                        String tipoLval = aux.type;
                        if(top.table.containsKey(Valor))
                        {
                            //Verificar tipos
                             Info tmp = (Info) (top.table.get(Valor));
                             if(tipoLval == tmp.type)
                             {
                                 if(tmp.value == null)
                                 {
                                     System.out.println("  Error: No se puede asignar un valor nulo a la variable " + Lvalue + " -> Ambito Actual: "+top);
                                 }
                                 else
                                 {
                                    aux.value = tmp.value;
                                    top.table.replace(Lvalue, aux.value);
                                    System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+aux.value + " -> Ambito Actual: "+top);
                                 }
                             }
                             else
                             {
                                 System.out.println("  Error: No se puede asignar un valor a la variable " +Lvalue + " -> " + tipoLval + " & " + tmp.type + " no son compatibles" + " -> Ambito Actual: "+top);
                             }

                        }
                        else
                        {
                            if(Valor.contains("\""))
                            {
                                if(aux.type == "string")
                                {
                                     aux.value = Valor;
                                     top.table.replace(Lvalue, aux);            
                                     System.out.println("  Nuevo valor para el identificador "+Lvalue+ " -> Valor: "+Valor + " -> Ambito Actual: "+top);

                                }
                                else
                                {
                                    System.out.println("  Error: No se puede asignar un valor String a la variable " + Lvalue + " -> Ambito Actual: "+top);
                                }
                            }
                            else
                            {
                                System.out.println("  Error: No se puede asignar el valor de una variable no existente a la variable " + Lvalue + " -> Ambito Actual: "+top);
                            }
                        }
                    }
            }
        }
        else
        {
            System.out.println("  Error: No se puede asignar realizar la asignacion de valor a " + Lvalue + " ya que no esta declarada -> Ambito Actual: "+top);
        }
    }
    
    public static boolean isNumeric(String S)
    {
        try
        {
            double d = Double.parseDouble(S);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    
    public static String ReturnVal(String S)
    {
        Info aux = (Info) (top.table.get(S));       
        return aux.value.toString();
    }
    

    public static String Resolve(String S)
    {
        Postfijo Stage1 = new Postfijo(S);
        String Postfijo = Stage1.getPostFix();    
        Solucionador Stage2 = new Solucionador(Postfijo);
        return Stage2.getResult();
    }
    
   private static boolean RevFloat(String Result)
  {
      float N = Float.parseFloat(Result);
      
      if(N % 1 == 0)
      {
         return true;
      }
      else
      {
          return false;
      }
} 
    
}
