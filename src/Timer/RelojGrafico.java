package Timer;

import java.awt.Image;

import javax.swing.ImageIcon;

public class RelojGrafico {

	protected ImageIcon segUnidad, segDecena, minUnidad, minDecena, separador;
	protected String[] imagenes;
	
	public RelojGrafico() {
		this.segUnidad = new ImageIcon();
		this.segDecena = new ImageIcon();
		this.minUnidad = new ImageIcon();
		this.minDecena = new ImageIcon();
		this.separador = new ImageIcon("img/timepuntos.png");
		separador.setImage(separador.getImage());
		this.imagenes = new String[]{"img/time0.png", "img/time1.png", "img/time2.png", "img/time3.png", "img/time4.png", "img/time5.png", "img/time6.png", "img/time7.png", "img/time8.png", "img/time9.png"};
	}
	
	public void actualizar(int minDec, int minUni, int segDec, int segUni) {
		ImageIcon imageIconMD = new ImageIcon(imagenes[minDec]); 
		Image newImgMD = imageIconMD.getImage().getScaledInstance(48, 70,  java.awt.Image.SCALE_SMOOTH);
		this.minDecena.setImage(newImgMD);
		
		ImageIcon imageIconMU = new ImageIcon(imagenes[minUni]); 
		Image newImgMU = imageIconMU.getImage().getScaledInstance(48, 70,  java.awt.Image.SCALE_SMOOTH);
		this.minUnidad.setImage(newImgMU);
		
		ImageIcon imageIconSD = new ImageIcon(imagenes[segDec]); 
		Image newImgSD = imageIconSD.getImage().getScaledInstance(48, 70,  java.awt.Image.SCALE_SMOOTH);
		this.segDecena.setImage(newImgSD);
		
		ImageIcon imageIconSU = new ImageIcon(imagenes[segUni]); 
		Image newImgSU = imageIconSU.getImage().getScaledInstance(48, 70,  java.awt.Image.SCALE_SMOOTH);
		this.segUnidad.setImage(newImgSU);
	}
	
	public ImageIcon getSegUnidad() {
		return this.segUnidad;
	}
	
	public ImageIcon getSegDecena() {
		return this.segDecena;
	}
	
	public ImageIcon getMinUnidad() {
		return this.minUnidad;
	}
	
	public ImageIcon getMinDecena() {
		return this.minDecena;
	}
	
	public ImageIcon getSeparador() {
		return this.separador;
	}
}
