package Timer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reloj r = new Reloj(null);
		r.iniciar();
	}

}

/*LocalTime start = LocalTime.now( );
Timer t = new Timer();
TimerTask tt = new TimerTask() {
    @Override
    public void run() {
        LocalTime stop = LocalTime.now( );
		Duration d = Duration.between( start , stop );
		System.out.println(d.toString());
		 String hms = String.format("%02d:%02d:%02d", 
		                d.toHours(), 
		                d.toMinutesPart(), 
		                d.toSecondsPart());
		System.out.println(hms);
    };
};
t.schedule(tt, 0, 1000); */
