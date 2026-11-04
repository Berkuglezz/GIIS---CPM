package uo.cpm.p6.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p6.model.Nivel;
import uo.cpm.p6.service.SpaceInvaders;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

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
	private ProcesaBtTablero procesaBtTablero = new ProcesaBtTablero();
	private JMenu mnNivel;
	private JRadioButtonMenuItem rdFacil;
	private JRadioButtonMenuItem rdInermedio;
	private JRadioButtonMenuItem rdDificil;
	private JSeparator separator;
	private JSeparator separator_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 * @param spaceInvaders 
	 */
	public VentanaPrincipal(SpaceInvaders spaceInvaders) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmaSalida();
			}
		});
		setResizable(false);
		this.spaceInvaders = spaceInvaders;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1080, 400);
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

	protected void confirmaSalida() {
		if (JOptionPane.showConfirmDialog(this,
				"¿Estas seguro de que quieres salir de la aplicacion?") == JOptionPane.YES_OPTION)
			System.exit(0);
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
			btnDado.setBounds(12, 12, 109, 113);
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
			lblNave.setBounds(442, 32, 146, 76);
		}
		return lblNave;
	}
	private JLabel getLblTierra() {
		if (lblTierra == null) {
			lblTierra = new JLabel("");
			lblTierra.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lblTierra.setBounds(877, -20, 203, 243);
		}
		return lblTierra;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score:");
			lblScore.setForeground(new Color(255, 255, 255));
			lblScore.setBackground(new Color(238, 238, 238));
			lblScore.setBounds(727, 45, 62, 15);
		}
		return lblScore;
	}
	private JPanel getPanelTablero() {
		if (panelTablero == null) {
			panelTablero = new JPanel();
			panelTablero.setBorder(new LineBorder(new Color(28, 113, 216), 5));
			panelTablero.setBackground(new Color(28, 113, 216));
			panelTablero.setBounds(147, 253, 800, 100);
			panelTablero.setLayout(new GridLayout(1, 0, 8, 0));
			redimensionarTablero(spaceInvaders.getNivel());
			habilitaTablero(false);
		}
		return panelTablero;
	}
	

private void redimensionarTablero(Nivel nivel) {
		int ancho = 0;
		int x = 0;
		switch (nivel) {
			case FACIL: {
				x = 20;
				ancho = 1010;
				break;
			}
			case INTERMEDIO: {
				x = 120;
				ancho = 815;
				break;
			}
			case DIFICIL: {
				x = 190;
				ancho = 610;
				break;
			}
		}
		getPanelTablero().setBounds(x, 210, ancho, 105);
		getPanelTablero().setLayout(new GridLayout(1, spaceInvaders.getDimension(), 4, 0));
		getPanelTablero().removeAll();
		// Se añaden los botones al tablero
		addBotones();
	}

	private JPanel getPanelDisparos() {
		if (panelDisparos == null) {
			panelDisparos = new JPanel();
			panelDisparos.setBackground(new Color(0, 0, 0));
			panelDisparos.setBounds(342, 148, 340, 75);
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
			textAreaPuntos.setBounds(727, 60, 102, 26);
		}
		return textAreaPuntos;
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
	
	private void addBotones() {
		for (int i = 0; i < spaceInvaders.getDimension(); i++) {
			getPanelTablero().add(nuevoBoton(i));
		}
	}
	
	private JButton nuevoBoton(int i) {
		JButton bt = new JButton("");
		bt.setActionCommand(String.valueOf(i));
		bt.setBackground(Color.BLACK);
		bt.addActionListener(procesaBtTablero);
		bt.setToolTipText("Haz click para disparar");
		return bt;
	}

	private void nuevaPartida() {
		spaceInvaders.inicializar(Nivel.INTERMEDIO);
		getPanelDisparos().removeAll();
		getPanelDisparos().repaint();
		destaparTablero();
		habilitaTablero(false);
		getBtnDado().setEnabled(true);
		pintaPuntos();
	}

	private void destaparTablero() {
		for (int i = 0; i < getPanelTablero().getComponentCount(); i++) {
			JButton casilla = (JButton) getPanelTablero().getComponent(i);
			casilla.setIcon(null);
			casilla.setDisabledIcon(null);
		}
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
			menuBar.add(getMnNivel());
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
					confirmaSalida();
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

	class ProcesaBtTablero implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton botonPulsado = (JButton) e.getSource();
			int posicion = Integer.parseInt(botonPulsado.getActionCommand());
			dispara(posicion);
		}
	}
	
	
	class ProcesaBtTablero1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton botonPulsado = (JButton) e.getSource();
			int posicion = Integer.parseInt(botonPulsado.getActionCommand());
			dispara(posicion);
		}
	}
	private JMenu getMnNivel() {
		if (mnNivel == null) {
			mnNivel = new JMenu("Nivel");
			mnNivel.add(getRdFacil());
			mnNivel.add(getSeparator());
			mnNivel.add(getRdInermedio());
			mnNivel.add(getSeparator_1());
			mnNivel.add(getRdDificil());
		}
		return mnNivel;
	}
	private JRadioButtonMenuItem getRdFacil() {
		if (rdFacil == null) {
			rdFacil = new JRadioButtonMenuItem("Facil");
			rdFacil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarJuego(Nivel.FACIL);
				}
			});
			buttonGroup.add(rdFacil);
		}
		return rdFacil;
	}
	private JRadioButtonMenuItem getRdInermedio() {
		if (rdInermedio == null) {
			rdInermedio = new JRadioButtonMenuItem("Intermedio");
			rdInermedio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarJuego(Nivel.INTERMEDIO);
				}
			});
			rdInermedio.setSelected(true);
			buttonGroup.add(rdInermedio);
		}
		return rdInermedio;
	}
	private JRadioButtonMenuItem getRdDificil() {
		if (rdDificil == null) {
			rdDificil = new JRadioButtonMenuItem("Dificil");
			rdDificil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarJuego(Nivel.DIFICIL);
				}
			});
			buttonGroup.add(rdDificil);
		}
		return rdDificil;
	}
	
	private void inicializarJuego(Nivel nivel) {
		spaceInvaders.inicializar(nivel); // 1. logica

		getBtnDado().setEnabled(true); // 2. Interfaz
		pintaPuntos();
		eliminaDisparos();
		redimensionarTablero(nivel);
	}
	
	private void eliminaDisparos() {
		// TODO -generated method stub
		getPanelDisparos().removeAll();
		getPanelDisparos().repaint();
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	
	
}
