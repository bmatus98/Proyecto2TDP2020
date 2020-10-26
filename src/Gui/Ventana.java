package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Sudoku.Celda;
import Sudoku.InvalidSolutionException;
import Sudoku.Logica;
import Timer.Reloj;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private Logica juego;
	private int valorGuardado;
	protected Reloj reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		valorGuardado = 0;
		juego = new Logica();
		try {
			juego.inicializarMatriz();
		}catch (InvalidSolutionException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(contentPane,
				    "El tablero cargado no corresponde a una solución válida",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			}
		
		JPanel panelTablero = new JPanel();
		panelTablero.setBounds(10, 11, 450, 450);
		contentPane.add(panelTablero);
		panelTablero.setLayout(new GridLayout(juego.getCantFilas(), 0, 0, 0));
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				Celda c = juego.getCelda(i+1,j+1);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel(); //cambiar por grafico
				label.setBorder(new LineBorder(Color.BLACK));
				panelTablero.add(label);
				label.setVisible(true);
				
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
			    });
				
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(c.esEditable()) {
							boolean terminado = juego.actualizarValor(valorGuardado, c.getFila(), c.getColumna());
							ImageIcon graficoNuevo = c.getEntidadGrafica().getGrafico();
							reDimensionar(label, graficoNuevo);
							repaint();
							if (terminado) {
								if(juego.validarTodos()==true) ganar();
							}
						}
					}
				});
			}
		}
		
		JPanel panelTimer = new JPanel();
		panelTimer.setBounds(484, 11, 240, 70);
		contentPane.add(panelTimer);
		panelTimer.setLayout(new GridLayout(0, 5, 0, 0));
		
		reloj = new Reloj(panelTimer);
		dibujarTimer(panelTimer);
		reloj.iniciar();
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(484, 157, 240, 251);
		contentPane.add(panelBotones);
		panelBotones.setLayout(new GridLayout(3, 3, 0, 0));
		
		
		JButton btnNro1 = new JButton("1");
		btnNro1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 1;
			}
		});
		panelBotones.add(btnNro1);
		
		JButton btnNro2 = new JButton("2");
		btnNro2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 2;
			}
		});
		panelBotones.add(btnNro2);
		
		JButton btnNro3 = new JButton("3");
		btnNro3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 3;
			}
		});
		panelBotones.add(btnNro3);
		
		JButton btnNro4 = new JButton("4");
		btnNro4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 4;
			}
		});
		panelBotones.add(btnNro4);
		
		JButton btnNro5 = new JButton("5");
		btnNro5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 5;
			}
		});
		panelBotones.add(btnNro5);
		
		JButton btnNro6 = new JButton("6");
		btnNro6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 6;
			}
		});
		panelBotones.add(btnNro6);
		
		JButton btnNro7 = new JButton("7");
		btnNro7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 7;
			}
		});
		panelBotones.add(btnNro7);
		
		JButton btnNro8 = new JButton("8");
		btnNro8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 8;
			}
		});
		panelBotones.add(btnNro8);
		
		JButton btnNro9 = new JButton("9");
		btnNro9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 9;
			}
		});
		panelBotones.add(btnNro9); 
		
		JPanel panelBorrar = new JPanel();
		panelBorrar.setBounds(556, 426, 98, 35);
		contentPane.add(panelBorrar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valorGuardado = 0;
			}
		});
		panelBorrar.add(btnBorrar);
		
		//vincular con juego acá
	}
	
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
	
	private void ganar() {
		reloj.detener();
		JOptionPane.showMessageDialog(contentPane,
			    "¡Ha ganado!",
			    "Fin del juego",
			    JOptionPane.PLAIN_MESSAGE);
		 Window win = SwingUtilities.getWindowAncestor(contentPane);
		 win.dispose();
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
