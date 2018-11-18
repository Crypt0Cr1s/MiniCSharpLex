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
  Tsimbols prev;  

  public Tsimbols(Tsimbols p) {
	table = new HashMap();
	prev = p;     
  }

  public static int putClass(String c, String sc, Symb s) {
	if(root.table.containsKey(c)){ System.out.print("CLASS ENTRY: "+c);
	  					 push();
	  					 return 1;
	}
	if(sc == null){ root.table.put(c,s);
	  		    System.out.print("CLASS ENTRY: "+c);
	  		    top.table.put(c, s);
	  		    push();
	  		    return 0;
	}
	if(!root.table.containsKey(sc)){ System.out.print("CLASS ENTRY: "+c);
						   push();
						   return 2;
	}
	else { root.table.put(c,s);
		 System.out.print("CLASS ENTRY: "+c);
		 top.table.put(c, s);
		 push();
		 return 0;
	}    
  }

  public static boolean put(String name, Symb s) {
	if(!top.table.containsKey(name)) { top.table.put(name,s);
	  					     System.out.println("  NEW IDENTIFIER: "+name+" -> CURRENT ENVIRONMENT: "+top);
	  					     return true;
	}
	return false;    
  }

  public static Symb get(String name) {
 	for(Tsimbols e = top; e != null; e = e.prev) { Symb found = (Symb)(e.table.get(name));
	  							if (found != null) return found;
	}
	return null;   
  }

  public static void push() {
	top = new Tsimbols(top);
	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public static void pop() {
	top = top.prev;
   	System.out.println(" -> CURRENT ENVIRONMENT: "+top);
  }

  public String toString() {
	if(prev != null) return prev.toString()+table;
	else return ""+table; 
  }
    
}
