
import java.io.*;
/* Realiza una clase Java llamada DuplicarPalabrasTexto. 
 * Esta clase leerá el contenido de un archivo de texto y creará otro archivo donde cada palabra 
 * del texto original se duplicará (por ejemplo, "Hola mundo" se transformará en "Hola Hola mundo mundo"). 
 * Si el archivo no existe, deberá mostrar un mensaje de error. Además, imprimirá el contenido 
 * modificado en pantalla.*/
public class EJ01 {
	public static void main(String[] args) {
		// Definir las rutas del archivo de entrada y salida
        String archivoEntrada = "entrada.txt";
        String archivoSalida = "salida.txt";

        // Lectura del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            // Leer la primera línea del archivo
            String linea;

            while ((linea = br.readLine()) != null) {
                
                String[] palabras = linea.split("\\s+");
                StringBuilder lineaDuplicada = new StringBuilder();
                for (String palabra : palabras) {
                    lineaDuplicada.append(palabra).append(" ").append(palabra).append(" ");
                }
                bw.write(lineaDuplicada.toString().trim());
                System.out.println(lineaDuplicada.toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
