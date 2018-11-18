package minicsharplex;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.*;
import java.io.Reader;
%%

%public
%class AnalizadorLexico
%unicode
%line
%column
%cup
   
/*
    Declaraciones

    El codigo entre %{  y %} sera copiado integramente en el 
    analizador generado.
*/
%{
    /*  Generamos un java_cup.Symbol para guardar el tipo de token 
        encontrado */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Generamos un Symbol para el tipo de token encontrado 
       junto con su valor */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%standalone

%{

 
 //static ArrayList<Tsimbols> tokens = new ArrayList<>();


%}

%{
/*public static void ingress (String simbolo, String tipo){
    boolean bandera = false;
    Tsimbols outsim = new Tsimbols();
    outsim.t_simbolo(simbolo, tipo);
        for (int i = 0; i < tokens.size();i++){
            if (outsim == tokens.get(i)){
            bandera = true;
            }
        }

    if (bandera == false){
        tokens.add(outsim);
     }
    
}*/


%}
Hex = 0[xX][0-9a-fA-F]+
Int = [0-9][0-9]*
Bool ="true"|"false"|"TRUE"|"FALSE"
String = \" [^\r\n]+  \"
Double= [-+]?[0-9]+"."|[-+]?[0-9]+"."([0-9]+|("E"|"e")[-+]?[0-9]+|[0-9]+("E"|"e")[-+]?[0-9]+)
//C = "void"|"int"|"double"|"bool"|"string"|"class"|"interface"|"null"|"this"|"extends"|"implements"|"for"|"while"|"if"|"else"|"return"|"break"|"New"|"NewArray"|"Print"|"ReadInteger"|"ReadLine"|"Malloc"
TC = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EC = "//" [^\r\n]* [\r|\n|\r\n]?
//Punt = "+"|"-"|"*"|"/"|"%"|"<"|"<="|">"|">="|"="|"=="|"!="|"&&"|"||"|"!"|";"|","|"."|"["|"]"|"("|")"|"{"|"}"|"[]"|"()"|"{}"
in = "/*" [^*]+ 

%%






    {Hex} { return symbol(sym.HEX);}
    {Int} { return symbol(sym.ENTERO,new Integer(yytext()));}
    {Bool} { return symbol(sym.BOOLEAN,new Boolean(yytext()));}
    {String} { return symbol(sym.CADENA,new String(yytext()));}
    {Double} { return symbol(sym.DOBLE,new Double(yytext()));}
    //{C} {tokens.add("palabraclave: " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1)); return symbol(sym.CLAVE);}
    ////////////////////////////////////////////////////////////////////////////////
    "void" { return symbol(sym.VOID,new String(yytext())); }
    "int" { return symbol(sym.INT,new String(yytext())); }
    "double" { return symbol(sym.DOUBLE,new String(yytext()));}
    "bool" { return symbol(sym.BOOL,new String(yytext()));}
    "string" { return symbol(sym.STRING,new String(yytext()));}
    "class" { return symbol(sym.CLASS,new String(yytext()));}
    "interface" { return symbol(sym.INTERFACE,new String(yytext()));}
    "null" { return symbol(sym.NULL,new String(yytext()));}
    "this" { return symbol(sym.THIS,new String(yytext()));}
    "extends" { return symbol(sym.EXTENDS,new String(yytext()));}
    "implements" { return symbol(sym.IMPLEMENTS,new String(yytext()));}
    "for" { return symbol(sym.FOR,new String(yytext()));}
    "while" { return symbol(sym.WHILE,new String(yytext()));}
    "if" { return symbol(sym.IF,new String(yytext()));}
    "else" { return symbol(sym.ELSE,new String(yytext()));}
    "return" { return symbol(sym.RETURN,new String(yytext()));}
    "break" { return symbol(sym.BREAK,new String(yytext()));}
    "New" { return symbol(sym.NEW,new String(yytext()));}
    "NewArray" { return symbol(sym.NEWARRAY,new String(yytext()));}
    "Print" { return symbol(sym.PRINT,new String(yytext()));}
    "ReadInteger" { return symbol(sym.READINT,new String(yytext()));}
    "ReadLine" { return symbol(sym.READLINE,new String(yytext()));}
    "Malloc" {return symbol(sym.MALLOC,new String(yytext()));}
    "GetByte" {return symbol(sym.GETBYTE,new String(yytext()));}
    "Setbyte" {return symbol(sym.SETBYTE,new String(yytext()));}

    ///////////////////////////////////////////////////////////////////////
    [a-zA-Z][a-zA-Z0-9_]* { if(yytext().length()<=31){return symbol(sym.IDENTIFIER,new String(yytext()));} else{String cortador=yytext().substring(0,31); /*System.out.print("identificador truncado " + cortador + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));*/ return symbol(sym.IDENTIFIER,new String(yytext()));}}
    [\s]+  { /*se ignoran los espacios*/}
    {in} { System.out.print("ERROR Comentario no cerrado " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1) + "\n");}
    {TC}|{EC} {/*se ignoran los comentario*/}
    //{Punt} {tokens.add("puntuacion: " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1)); return symbol(sym.PUNTUACION);}
    ///////////////////////////////////////////////////////////////////////
    "+" { return symbol(sym.PLUS,new String(yytext()));}
    "-" { return symbol(sym.MINUS,new String(yytext()));}
    "*" { return symbol(sym.MULTI,new String(yytext()));}
    "/" { return symbol(sym.DIV,new String(yytext()));}
    "%" { return symbol(sym.MOD,new String(yytext()));}
    "<" { return symbol(sym.MINOR,new String(yytext()));}
    "<=" { return symbol(sym.MINOREQ,new String(yytext()));}
    ">" { return symbol(sym.MAJOR,new String(yytext()));}
    ">=" { return symbol(sym.MAJOREQ,new String(yytext()));}
    "=" { return symbol(sym.ASIGNATION,new String(yytext()));}
    "==" { return symbol(sym.EQUAL,new String(yytext()));}
    "!=" { return symbol(sym.NOTEQUAL,new String(yytext()));}
    "&&" { return symbol(sym.AND,new String(yytext()));}
    "||" { return symbol(sym.OR,new String(yytext()));}
    "!" { return symbol(sym.ADMIRATION,new String(yytext()));}
    ";" { return symbol(sym.SEMICOLON,new String(yytext()));}
    "," { return symbol(sym.COMMA,new String(yytext()));}
    "." { return symbol(sym.POINT,new String(yytext()));}
    "[" { return symbol(sym.CORCHIZQ,new String(yytext()));}
    "]" { return symbol(sym.CORCHDER,new String(yytext()));}
    "(" { return symbol(sym.PARENIZQ,new String(yytext()));}
    ")" { return symbol(sym.PARENDER,new String(yytext()));}
    "{" { return symbol(sym.LLAVEIZQ,new String(yytext()));}
    "}" { return symbol(sym.LLAVEDER,new String(yytext()));}
    "[]" { return symbol(sym.CORCHDOBLE,new String(yytext()));}
    //"()" { return symbol(sym.PARENDOBLE,new String(yytext()));}
    


. { System.out.print("ERROR Caracter no valido " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() -1) +"\n");}
