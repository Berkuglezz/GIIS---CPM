package uo.cpm.p6.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p6.service.SpaceInvaders;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SpaceInvaders spaceInvaders;
	private JButton btnDado;
	private JLabel lblNave;
	private JLabel lblTierra;
	private JLabel lblScore;
	private JPanel panelTablero;
	private JPanel panelDisparos;
	private JTextArea textAreaPuntos;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenuItem mntmNuevo;
	private JSeparator separatorJuego;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JSeparator separatorAyuda;
	private JMenuItem mntmAcercaDe;

	/**
	 * Create the frame.
	 * @param spaceInvaders 
	 */
	public VentanaPrincipal(SpaceInvaders spaceInvaders) {
		setResizable(false);
		this.spaceInvaders = spaceInvaders;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 425);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnDado());
		contentPane.add(getLblNave());
		contentPane.add(getLblTierra());
		contentPane.add(getLblScore());
		contentPane.add(getPanelTablero());
		contentPane.add(getPanelDisparos());
		contentPane.add(getTextAreaPuntos());
		setJMenuBar(getMenuBar_1());

	}

	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarSpaceInvaders();
				}
			});
			btnDado.setToolTipText("Haz click para obtener el numero de disparos");
			btnDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btnDado.setBounds(12, 32, 109, 113);
		}
		return btnDado;
	}
	protected void iniciarSpaceInvaders() {
		// TODO Auto-generated method stub
		spaceInvaders.lanzarDado();
		pintaDisparos();
		habilitaTablero(true);
		getBtnDado().setEnabled(false);
	}


private void pintaDisparos() {
		for (int i = 0; i < spaceInvaders.getDisparos(); i++) {
			getPanelDisparos().add(nuevoDisparo());
		}
		validate(); // repinta la ventana en tiempo de ejecución 
					// para que se vean los disparos añadidos en el panel
	}
	
	private JLabel nuevoDisparo() {
		JLabel lbDisparo = new JLabel("");
		lbDisparo.setBorder(new LineBorder(Color.GREEN, 1));
		lbDisparo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/shoot.png")));
		return lbDisparo;
	}	

	private JLabel getLblNave() {
		if (lblNave == null) {
			lblNave = new JLabel("");
			lblNave.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
			lblNave.setBounds(303, 45, 146, 76);
		}
		return lblNave;
	}
	private JLabel getLblTierra() {
		if (lblTierra == null) {
			lblTierra = new JLabel("");
			lblTierra.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lblTierra.setBounds(682, 0, 203, 243);
		}
		return lblTierra;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score:");
			lblScore.setForeground(new Color(255, 255, 255));
			lblScore.setBackground(new Color(238, 238, 238));
			lblScore.setBounds(525, 59, 62, 15);
		}
		return lblScore;
	}
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBorder(new LineBorder(new Color(28, 113, 216), 5));
			panelTablero.setBackground(new Color(28, 113, 216));
			panelTablero.setBounds(29, 276, 800, 100);
			panelTablero.setLayout(new GridLayout(1, 0, 8, 0));
			panelTablero.add(getBtn0());
			panelTablero.add(getBtn1());
			panelTablero.add(getBtn2());
			panelTablero.add(getBtn3());
			panelTablero.add(getBtn4());
			panelTablero.add(getBtn5());
			panelTablero.add(getBtn6());
			panelTablero.add(getBtn7());
			habilitaTablero(false);

		}
		return panelTablero;
	}
	private JPanel getPanelDisparos() {
		if (panelDisparos == null) {
			panelDisparos = new JPanel();
			panelDisparos.setBackground(new Color(0, 0, 0));
			panelDisparos.setBounds(203, 148, 340, 75);
			panelDisparos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return panelDisparos;
	}
	private JTextArea getTextAreaPuntos() {
		if (textAreaPuntos == null) {
			textAreaPuntos = new JTextArea();
			textAreaPuntos.setForeground(new Color(46, 194, 126));
			textAreaPuntos.setFont(new Font("Font Awesome 7 Brands", Font.BOLD, 14));
			textAreaPuntos.setText("1000");
			textAreaPuntos.setBackground(new Color(0, 0, 0));
			textAreaPuntos.setEditable(false);
			textAreaPuntos.setBounds(525, 74, 102, 26);
		}
		return textAreaPuntos;
	}
	private JButton getBtn0() {
		if (btn0 == null) {
			btn0 = new JButton("");
			btn0.setToolTipText("Haz click para desbloquear");
			btn0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(0);
				}
			});
			btn0.setBackground(new Color(0, 0, 0));
		}
		return btn0;
	}
	private void dispara(int i) {
		spaceInvaders.dispara(i);
		representaspaceInvaders(i);
	}
			
	private void representaspaceInvaders(int i) {
		pintaPuntos(); //getTxPuntos().setText(String.valueOf(spaceInvaders.getPuntuacion()));
		despintaDisparo();
		pintaCasilla(i);
		compruebaFin();
		deshabilitaBoton(i); //((JButton) getPnTablero().getComponent(i)).setEnabled(false);
	}		


	private void deshabilitaBoton(int i) {
		((JButton) getPanelTablero().getComponent(i)).setEnabled(false);
	}

	private void compruebaFin() {
		if (spaceInvaders.isFinJuego()) {
			habilitaTablero(false);
			descubreTablero();
			JOptionPane.showMessageDialog(this,
					spaceInvaders.getMotivoFin() + "\nPuntuacion final: " + spaceInvaders.getPuntuacion(),
					"Invasion espacial", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void descubreTablero() {
		for (int i = 0; i < getPanelTablero().getComponentCount(); i++) {
			pintaCasilla(i);
		}
	}

	private void pintaCasilla(int i) {
		ImageIcon imagen = new ImageIcon(VentanaPrincipal.class.getResource(spaceInvaders.obtenerImagen(i)));
		// La imagen a pintar sobre el botón presionado hay que solicitársela a spaceInvaders (spaceInvaders.obtenerImagen(i))
	
		((JButton) getPanelTablero().getComponent(i)).setIcon(imagen);  //accedemos al botón
		((JButton) getPanelTablero().getComponent(i)).setDisabledIcon(imagen); //imagen a mostrar en el botón cuando esté deshabilitado
		
	}	

	private void despintaDisparo() {
		getPanelDisparos().remove(0);
		getPanelDisparos().repaint(); //para que el panel se repinte en el caso de eliminar
								  // elementos del panel
	}	

	private void pintaPuntos() {
		getTextAreaPuntos().setText(String.valueOf(spaceInvaders.getPuntuacion()));
	}

	private JButton getBtn1() {
		if (btn1 == null) {
			btn1 = new JButton("");
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(1);
				}
			});
			btn1.setBackground(new Color(0, 0, 0));
		}
		return btn1;
	}
	private JButton getBtn2() {
		if (btn2 == null) {
			btn2 = new JButton("");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(2);
				}
			});
			btn2.setBackground(new Color(0, 0, 0));
		}
		return btn2;
	}
	private JButton getBtn3() {
		if (btn3 == null) {
			btn3 = new JButton("");
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(3);
				}
			});
			btn3.setBackground(new Color(0, 0, 0));
		}
		return btn3;
	}
	private JButton getBtn4() {
		if (btn4 == null) {
			btn4 = new JButton("");
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(4);
				}
			});
			btn4.setBackground(new Color(0, 0, 0));
		}
		return btn4;
	}
	private JButton getBtn5() {
		if (btn5 == null) {
			btn5 = new JButton("");
			btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(5);
				}
			});
			btn5.setBackground(new Color(0, 0, 0));
		}
		return btn5;
	}
	private JButton getBtn6() {
		if (btn6 == null) {
			btn6 = new JButton("");
			btn6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(6);
				}
			});
			btn6.setBackground(new Color(0, 0, 0));
		}
		return btn6;
	}
	private JButton getBtn7() {
		if (btn7 == null) {
			btn7 = new JButton("");
			btn7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(7);
				}
			});
			btn7.setBackground(new Color(0, 0, 0));
		}
		return btn7;
	}
	
	private void nuevaPartida() {
		spaceInvaders.inicializar();
		getPanelDisparos().removeAll();
		getPanelDisparos().repaint();
		for (int i = 0; i < getPanelTablero().getComponentCount(); i++) {
			JButton casilla = (JButton) getPanelTablero().getComponent(i);
			casilla.setIcon(null);
			casilla.setDisabledIcon(null);
		}
		habilitaTablero(false);
		getBtnDado().setEnabled(true);
		pintaPuntos();
	}

	private void mostrarAcercaDe() {
		JOptionPane.showMessageDialog(this,
				"Invasion Espacial\nVersion 1.0\nRealizada por Berku Gonzalez Fernandez\nPracticas CPM 26-27\nEII Oviedo",
				"Acerca de", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('J');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparatorJuego());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					nuevaPartida();
				}
			});
			mntmNuevo.setMnemonic('N');
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNuevo;
	}
	private JSeparator getSeparatorJuego() {
		if (separatorJuego == null) {
			separatorJuego = new JSeparator();
		}
		return separatorJuego;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmSalir.setMnemonic('S');
		}
		return mntmSalir;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('y');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparatorAyuda());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setEnabled(false);
			mntmContenidos.setMnemonic('C');
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmContenidos;
	}
	private JSeparator getSeparatorAyuda() {
		if (separatorAyuda == null) {
			separatorAyuda = new JSeparator();
		}
		return separatorAyuda;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarAcercaDe();
				}
			});
			mntmAcercaDe.setMnemonic('A');
		}
		return mntmAcercaDe;
	}

	private void habilitaTablero(boolean estado) {
		for(int i = 0; i < panelTablero.getComponents().length; i++) {
			panelTablero.getComponents()[i].setEnabled(estado);
			
		}
	}
}
