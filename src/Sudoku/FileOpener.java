package Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {
	
	protected String fileName;
	
	public FileOpener(String name) {
		fileName = name;
	}
	
	public void cargarMatriz(Celda[][] matriz) throws InvalidFormatException{
		String cadena;
		int fila = 0;
		try {
			FileReader f = new FileReader(fileName);
			BufferedReader b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				procesarLinea(cadena, fila, matriz);
				fila++;
				//System.out.println(); para el testeo
			}
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (fila != 9) {
			throw new InvalidFormatException("Formato inválido en el archivo :: cantidad inválida de filas");
		}
	}
	
	private void procesarLinea(String cadena, int fila, Celda[][] matriz) throws InvalidFormatException {
		String arr[] = cadena.split(" ");
		int columnasTotales = arr.length;
		if (columnasTotales == 9) {
			for (int columnaActual=0; columnaActual<columnasTotales; columnaActual++) {
				int valor=Integer.parseInt(arr[columnaActual]);
				if (valor>=1 && valor<=9) {
					matriz[fila][columnaActual] = new Celda(valor,fila+1,columnaActual+1);
					//System.out.print(valor+" "); para el testeo
				}
				else { //si valor no está entre 1 y 9
					throw new InvalidFormatException("Formato inválido en el archivo :: número inválido encontrado");
				}
			}
		}
		else { //si columnasTotales != 9
			throw new InvalidFormatException("Formato inválido en el archivo :: cantidad inválida de columnas");
		}
	}
}
