package Timer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class Reloj {
	
	protected LocalTime start;
	protected Timer t;
	protected TimerTask tt;
	protected boolean enUso;
	protected JPanel contenedor;
	protected RelojGrafico RGrafico;

	public Reloj(JPanel cont) {
		contenedor = cont;
		RGrafico = new RelojGrafico();
		start = LocalTime.now( );
		t = new Timer();
		enUso = true;
		tt = new TimerTask() {
		    @Override
		    public void run() {
		    	if (enUso) {
			        LocalTime stop = LocalTime.now( );
					Duration d = Duration.between( start , stop );
					/*String hms = String.format("%02d:%02d:%02d", 
					                d.toHours(), 
					                d.toMinutesPart(), 
					                d.toSecondsPart()); 
					System.out.println(hms); */
					actualizarGraficos(d.toMinutesPart(),d.toSecondsPart());
					if (contenedor != null) contenedor.repaint();
		    	}
		    };
		};
	}
	
	public RelojGrafico getRGRafico() {
		return RGrafico;
	}
	
	public void iniciar() {
		enUso = true;
		start = LocalTime.now( );
		t.schedule(tt, 0, 1000); 
	}
	
	public void detener() {
		enUso = false;
	}
	
	public void actualizarGraficos(int minutos, int segundos) {
		int minDec = minutos/10;
		int minUni = minutos % 10;
		int segDec = segundos/10;
		int segUni = segundos % 10;
		RGrafico.actualizar(minDec, minUni, segDec, segUni);
	}
	
}
