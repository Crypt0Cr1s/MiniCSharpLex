ruta1=/home/cristobal/Documentos/ProyectosLenguajes/MiniCSharpLex/MiniCsharpLex/src/minicsharplex
ruta2=/home/cristobal/Documentos/ProyectosLenguajes/MiniCSharpLex/MiniCsharpLex
cd $ruta1
rm AnalizadorSintactico.java
rm sym.java
cd $ruta2
mv AnalizadorSintactico.java $ruta1
mv sym.java $ruta1
