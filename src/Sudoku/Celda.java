package Sudoku;

public class Celda {

	protected int valor, fila, columna;
	protected boolean repetido, editable;
	protected EntidadGrafica entGrafica;
	
	public Celda() {
		valor = 0;
		fila = 0;
		columna = 0;
		repetido = false;
		editable = false;
		entGrafica = new EntidadGrafica();
	}
	public Celda(int n, int fil, int col) {
		entGrafica = new EntidadGrafica();
		if (enteroValido(n)) {
			setValor(n);
		}
		else valor = 0;
		if (enteroValido(fil))
			fila = fil;
		else fila = 0;
		if (enteroValido(col))
			columna = col;
		else columna = 0;
		//asignarPanel();
		repetido = false;	
		editable=false;
	}
	
	public int getValor() {
		return valor;
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	public boolean estaRepetido() {
		return repetido;
	}
	public boolean esEditable() {
		return editable;
	}
	public EntidadGrafica getEntidadGrafica() {
		return entGrafica;
	}
	public int getCantElementos() {
		return this.entGrafica.getImagenes().length;
	}
	public boolean equals(Celda c) {
		return (valor == c.getValor() && fila == c.getFila() && columna == c.getColumna());
	}
	public void setValor(int n) {
		if(enteroValido(n) || n == 0) {
			valor = n;
			this.entGrafica.actualizar(n, repetido, editable);
		}
		else valor = 0;
	}
	public void setRepetido(boolean r) {
		repetido = r;
		entGrafica.actualizar(valor, repetido, editable);
	}
	public void setEditable(boolean e) {
		editable = e;
	}
	public void setGrafica(EntidadGrafica g) {
		this.entGrafica = g;
	}
	
	private boolean enteroValido(int n) { //verifica si el entero n está entre 1 y 9
		boolean toReturn = false;
		if (n>=1 && n<=9)
			toReturn = true;
		return toReturn;
	}
	
}
