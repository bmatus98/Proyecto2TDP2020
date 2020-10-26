package Sudoku;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Logica {

	protected int cantFilas, celdasFaltantes;
	protected boolean terminado;
	protected Celda[][] matriz;
	
	public Logica() {
		cantFilas = 9;
		celdasFaltantes = 0;
		terminado = false;
		matriz = new Celda[cantFilas][cantFilas];
	}
	
	
	public void inicializarMatriz() throws InvalidSolutionException{
		FileOpener fo = new FileOpener("Solucion/Solucion.txt");
		try {
			fo.cargarMatriz(matriz);
			boolean t = validarTodos();
			//System.out.println("Solucion cargada. Es valida? "+t);  para el testeo
			if (t == false) {
				throw new InvalidSolutionException("La solución cargada es incorrecta");
			}
			eliminarVarias();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}
	
	/* devuelve true si no causa repetidos. En caso de causarlos, asigna repetido=true 
	 * donde corresponda. y devuelve false 
	 * REVISAR: puede que no necesite ser public
	 */
	public boolean validarCelda(Celda c) { 
		List<Celda> relevantes = listaRelevantes(c.getValor(),c.getFila(),c.getColumna());
		if (relevantes.size() == 1 && relevantes.contains(c))
			c.setRepetido(false);
		else if (relevantes.size()>1) {
			for(Celda celdaEnRelevantes : relevantes) {
				celdaEnRelevantes.setRepetido(true);
			}
		}
		return !c.estaRepetido();
	}
	
	public boolean validarTodos() {
		boolean todosValidos = true;
		for (int i=0; i<cantFilas; i++) {
			for(int j=0; j<cantFilas; j++) {
				if (validarCelda(matriz[i][j]) == false) todosValidos=false;
			}
		}
		return todosValidos;
	}
	
	public Celda getCelda(int fil, int col) {
		return matriz[fil-1][col-1];
	}
	
	public boolean actualizarValor(int valorNuevo, int fil, int col) {
		if (intEntre(valorNuevo,0,cantFilas) && intEntre(fil,1,cantFilas) && intEntre(col,1,cantFilas)) {
			Celda c = matriz[fil-1][col-1];
			boolean eraRepetido= c.estaRepetido();
			int valorViejo = c.getValor();
			c.setValor(valorNuevo);
			validarCelda(c); 
			if (eraRepetido) {
				/* Si la celda tenía un valor repetido, hay que revisar si los conflictos previos se 
				 * solucionaron. Se hace con la lista porque pueden ser más de uno.
				 */
				List<Celda> relevantes = listaRelevantes(valorViejo,c.getFila(),c.getColumna());
				if (relevantes.size()>=1) {
					for (Celda celdaEnRelevantes : relevantes) {
						validarCelda(celdaEnRelevantes);
					}
				}
			}
			if (valorViejo == 0 && valorNuevo != 0) { //si se escribió sobre una celda vacía
				celdasFaltantes--;
				if (celdasFaltantes == 0) terminado=true;
			}
			if (valorViejo !=0 && valorNuevo == 0) //si se eliminó una celda
				celdasFaltantes++;
		}
		return terminado;
	}
	
	public int getCantFilas() {
		return cantFilas;
	}
	
	private void eliminarVarias() {
		int cantAEliminar = 40;
		int eliminadas = 0;
		Random rand = new Random();
		while (eliminadas < cantAEliminar) {
			int randF = rand.nextInt(9);
			int randC = rand.nextInt(9);
			if (matriz[randF][randC].getValor() != 0) {
				eliminarCelda(randF+1, randC+1);
				eliminadas++;
			}
		}
		celdasFaltantes = eliminadas;
	}
	
	private void eliminarCelda(int fil, int col) {
		Celda c = matriz[fil-1][col-1];
		c.setEditable(true);
		actualizarValor(0,fil,col);
	}
	
	/* retorna lista con todas las celdas con dicho valor, que estén en posiciones
	 * relevantes (misma fila, misma columna, mismo panel) a la que se pasa por 
	 * parámetro.
	 */
	private List<Celda> listaRelevantes(int valor, int fil, int col){
		LinkedList<Celda> lista = new LinkedList<Celda>();
		checkFila(lista,valor,fil);
		checkColumna(lista,valor,col);
		checkPanel(lista,valor,fil,col);
		return lista;
	}
	private void checkFila(List<Celda> lista, int valor, int fil) {
		Celda celdaComparada;
		for (int i=0; i<cantFilas; i++) {
			celdaComparada = matriz[fil-1][i];
			if ( !lista.contains(celdaComparada) && valor == celdaComparada.getValor() && celdaComparada.getValor() > 0) { 
				//si tienen valores iguales y la celda no se está comparando consigo misma
				lista.add(celdaComparada);
			}
		}
	}
	private void checkColumna(List<Celda> lista, int valor, int col) {
		Celda celdaComparada;
		for (int i=0; i<cantFilas; i++) {
			celdaComparada = matriz[i][col-1];
			if ( !lista.contains(celdaComparada) && valor == celdaComparada.getValor() && celdaComparada.getValor() > 0) { 
				//si tienen valores iguales y la celda no se está comparando consigo misma
				lista.add(celdaComparada);
			}
		}
	}
	private void checkPanel(List<Celda> lista, int valor, int fil, int col) {
		int primerFilaPanel =  (((fil-1)/3)*3);
		int primerColumnaPanel =  (((col-1)/3)*3);
		Celda celdaComparada;
		for (int i=primerFilaPanel; i<=primerFilaPanel+2; i++) {
			for (int j=primerColumnaPanel; j<=primerColumnaPanel+2; j++) {
				celdaComparada = matriz[i][j];
				if( !lista.contains(celdaComparada) && valor == celdaComparada.getValor() && celdaComparada.getValor() > 0) {
					//si tienen valores iguales y la celda no se está comparand consigo misma
					lista.add(celdaComparada);
				}
			}
		}
	}
	private boolean intEntre(int x, int min, int max) {
		return (x>=min && x<=max);
	}
	
	
}
