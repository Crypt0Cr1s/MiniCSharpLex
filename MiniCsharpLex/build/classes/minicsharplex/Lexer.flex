package minicsharplex;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
%%

%public
%class AnalizadorLexico
%unicode
%line
%column

%standalone

%{

 ArrayList<String> tokens = new ArrayList();

%}

%{
public ArrayList<String> analizar(String archivoEspecPath) throws IOException {

    FileReader in = null;

    try {

        in = new FileReader(archivoEspecPath);

        AnalizadorLexico analizador = new AnalizadorLexico(in);

        while (!analizador.zzAtEOF) {

        analizador.yylex();
        
        }

    } catch (FileNotFoundException ex) {

        Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);

    } finally {

    try {

        in.close();

        return tokens;

    } catch     (IOException ex) {

        Logger.getLogger(AnalizadorLexico.class.getName()).log(Level.SEVERE, null, ex);

        return null;

        }

    }

   }

%}
Hex = 0[xX][0-9a-fA-F]+
Int = [0-9][0-9]*
Bool ="true"|"false"|"TRUE"|"FALSE"
String = \" [^\r\n]+  \"
Double= [-+]?[0-9]+"."|[-+]?[0-9]+"."([0-9]+|("E"|"e")[-+]?[0-9]+|[0-9]+("E"|"e")[-+]?[0-9]+)
C = "void"|"int"|"double"|"bool"|"string"|"class"|"interface"|"null"|"this"|"extends"|"implements"|"for"|"while"|"if"|"else"|"return"|"break"|"New"|"NewArray"
TC = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EC = "//" [^\r\n]* [\r|\n|\r\n]?
Punt = "+"|"-"|"*"|"/"|"%"|"<"|"<="|">"|">="|"="|"=="|"!="|"&&"|"||"|"!"|";"|","|"."|"["|"]"|"("|")"|"{"|"}"|"[]"|"()"|"{}"
in = "/*" [^*]+ 

%%






{Hex} { tokens.add("Hexadecimal " + yytext() + " en linea:"+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
{Int} { tokens.add("Entero " + yytext() + " en linea:"+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength()-1));}
{Bool} { tokens.add("Booleano " + yytext() + " en linea:"+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
{String} { tokens.add("Cadena " + yytext() + " en linea:"+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
{Double} { tokens.add("Double " + yytext() + " en linea:"+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
{C} {tokens.add("palabraclave: " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
[a-zA-Z][a-zA-Z0-9_]* { if(yytext().length()<=31){tokens.add("identificador: " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));} else{String cortador=yytext().substring(0,31); tokens.add("identificador truncado " + cortador + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}}
[\s]+  { /*se ignoran los espacios*/}
{in} { tokens.add("ERROR Comentario no cerrado " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
{TC}|{EC} {/*se ignoran los comentario*/}
{Punt} {tokens.add("puntuacion: " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() - 1));}
. { tokens.add("ERROR Caracter no valido " + yytext() + " en linea: "+(yyline+1)+ " columna: "+ (yycolumn + 1) + " - " + ((yycolumn+1) + yylength() -1));}
