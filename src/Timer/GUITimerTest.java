package Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Sudoku.Celda;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUITimerTest extends JFrame {

	private JPanel contentPane;
	private Reloj reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITimerTest frame = new GUITimerTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUITimerTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(91, 137, 240, 70);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 5, 0, 0));
		
		reloj = new Reloj(panel);
		
		dibujarTimer(panel);
		
		reloj.iniciar();
		
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
	
	private void dibujarTimer(JPanel panel) {
		
		ImageIcon graficoMD = reloj.getRGRafico().getMinDecena();
		JLabel labelMD = new JLabel(); //cambiar por grafico
		panel.add(labelMD);
		labelMD.setVisible(true);
		
		labelMD.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(labelMD, graficoMD);
				labelMD.setIcon(graficoMD);
			}
	    });
		
		ImageIcon graficoMU = reloj.getRGRafico().getMinUnidad();
		JLabel labelMU = new JLabel(); //cambiar por grafico
		panel.add(labelMU);
		labelMU.setVisible(true);
		
		labelMU.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(labelMU, graficoMU);
				labelMU.setIcon(graficoMU);
			}
	    });
		
		ImageIcon graficoSep = reloj.getRGRafico().getSeparador();
		JLabel labelSep = new JLabel(); //cambiar por grafico
		panel.add(labelSep);
		labelSep.setVisible(true);
		
		labelSep.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(labelSep, graficoSep);
				labelSep.setIcon(graficoSep);
			}
	    });
		
		ImageIcon graficoSD = reloj.getRGRafico().getSegDecena();
		JLabel labelSD = new JLabel(); //cambiar por grafico
		panel.add(labelSD);
		labelSD.setVisible(true);
		
		labelSD.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(labelSD, graficoSD);
				labelSD.setIcon(graficoSD);
			}
	    });
		ImageIcon graficoSU = reloj.getRGRafico().getSegUnidad();
		JLabel labelSU = new JLabel(); //cambiar por grafico
		panel.add(labelSU);
		labelSU.setVisible(true);
		
		labelSU.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDimensionar(labelSU, graficoSU);
				labelSU.setIcon(graficoSU);
			}
	    });
	}

}
