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
public class numero {
       
    public static Object isStringDoubleEnt(String s, String s2, String op)
{
       double num1=0;
       double num2=0;
       boolean falso=false;
       boolean falso2=false;
       int ent1=0;
       int ent2=0;
       boolean isint=false;
       boolean isint2=false;
       boolean bandera=false;
       boolean bandera2=false;
       double result=0;
    
    try
    {
        num1=Double.parseDouble(s);
        falso=true;
        
    } catch (NumberFormatException ex)
    {
        bandera2=true;
        return falso;
        
    }
    
    try
    {
        num2=Double.parseDouble(s2);
        falso2=true;
        
    } catch (NumberFormatException ex)
    {
        bandera2=true;
        return falso2;
    }
    
    if (falso==true){
        if (num1 % 1 == 0){
            
           ent1=(int) num1;
           isint=true;
           
        }
           
    
    }
    
    if (falso2==true){
        if (num2 % 1 == 0){
            
           ent2=(int) num2;
           isint2=true;
           
        }
           
    
    }
    
    
    switch (op){
        case "+":
            if(isint==false && isint2==false){
                result=num1+num2;
                
            }
            
            if(isint==true && isint2==false){
                result=ent1+num2;
                
            }
            
            if(isint==false && isint2==true){
                result=num1+ent2;
                
            }
            
            if(isint==true&&isint==true){
             ent1=ent1+ent2;
             bandera=true;
            }
            break;
        case "-":
            if(isint==false && isint2==false){
                result=num1-num2;
                
            }
            
            if(isint==true && isint2==false){
                result=ent1-num2;
                
            }
            
            if(isint==false && isint2==true){
                result=num1-ent2;
                
            }
            
            if(isint==true&&isint==true){
             ent1=ent1-ent2;
             bandera=true;
            }
            break;
        case "*":
            if(isint==false && isint2==false){
                result=num1*num2;
                
            }
            
            if(isint==true && isint2==false){
                result=ent1*num2;
                
            }
            
            if(isint==false && isint2==true){
                result=num1*ent2;
                
            }
            
            if(isint==true&&isint==true){
             ent1=ent1*ent2;
             bandera=true;
            }
            break;
        case "/":
            if(isint==false && isint2==false){
                result=num1/num2;
                
            }
            
            if(isint==true && isint2==false){
                result=ent1/num2;
                
            }
            
            if(isint==false && isint2==true){
                result=num1/ent2;
                
            }
            
            if(isint==true&&isint==true){
             ent1=ent1/ent2;
             bandera=true;
            }
            break;
    
    }
    if (bandera==true&& bandera2==false){
        return ent1;
    }
    if (bandera==false&& bandera2==false) {
        return result;
    }
           return null;
    
    
}        

    
}
    
    
