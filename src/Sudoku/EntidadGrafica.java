package Sudoku;

import java.awt.Image;

import javax.swing.ImageIcon;

public class EntidadGrafica {
	
	protected ImageIcon grafico;
	protected String[] imagenes;
	protected String[] repetidos;
	protected String[] editables;
	
	public EntidadGrafica() {
		this.grafico = new ImageIcon();
		this.imagenes = new String[]{"img/0.png", "img/1.png", "img/2.png", "img/3.png", "img/4.png", "img/5.png", "img/6.png", "img/7.png", "img/8.png", "img/9.png"};
	    this.repetidos = new String[]{"img/r1.png", "img/r2.png", "img/r3.png", "img/r4.png", "img/r5.png", "img/r6.png", "img/r7.png", "img/r8.png", "img/r9.png"}; 
	    this.editables = new String[]{"img/e1.png", "img/e2.png", "img/e3.png", "img/e4.png", "img/e5.png", "img/e6.png", "img/e7.png", "img/e8.png", "img/e9.png"};
	}
	
	public void actualizar(int indice, boolean repetido, boolean editable) {
		if (!repetido || indice==0) { //no repetido => imagen blanca o azul
			if (editable && indice != 0) { //editable !=0  => imagen azul; al indice se le resta 1 porque este arreglo no tiene img para el 0
				if (indice-1 < editables.length) {
					ImageIcon imageIcon = new ImageIcon(editables[indice-1]); 
					this.grafico.setImage(imageIcon.getImage());
				}
			}
			else { //no editable o incice=0 => imagen blanca
				if (indice < this.imagenes.length) {
					ImageIcon imageIcon = new ImageIcon(imagenes[indice]); 
					this.grafico.setImage(imageIcon.getImage());
				}
			}
			
		}
		else { //repetido => imagen roja; al indice se le resta 1 porque este arreglo no tiene img para el 0
			if (indice-1 < this.repetidos.length) {
				ImageIcon imageIcon = new ImageIcon(repetidos[indice-1]); 
				this.grafico.setImage(imageIcon.getImage());
			}
		}
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
		}
	}
	
	public ImageIcon getGrafico() {
		return this.grafico;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.grafico = grafico;
	}
	
	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}

}
