package uo.cpm.calculadora.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uo.cpm.calculadora.model.Calculadora;
import uo.cpm.calculadora.model.Operador;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// Colores del enunciado
	private static final Color COLOR_FONDO = new Color(74, 74, 74);
	private static final Color COLOR_PANTALLA = new Color(212, 230, 165);
	private static final Color COLOR_TEXTO_PANTALLA = new Color(40, 48, 30);
	private static final Color COLOR_DIGITO = new Color(122, 122, 122);
	private static final Color COLOR_OPERADOR = new Color(91, 192, 208);
	private static final Color COLOR_IGUAL = new Color(232, 90, 90);
	private static final Font FUENTE_BOTON = new Font("Dialog", Font.BOLD, 26);
	private static final String COLOR_ORIGINAL = "colorOriginal";

	private Calculadora calculadora;

	private JPanel pnPrincipal;
	private JPanel pnPantalla;
	private JLabel lblOperacion;
	private JTextField txtPantalla;
	private JPanel pnBotones;
	private JButton[] btDigitos;
	private JButton btSuma;
	private JButton btResta;
	private JButton btMultiplicacion;
	private JButton btIgual;

	// Un único receptor para las diez cifras y otro para los tres operadores
	private ProcesaDigito pD = new ProcesaDigito();
	private ProcesaOperador pO = new ProcesaOperador();

	public VentanaPrincipal(Calculadora calculadora) {
		this.calculadora = calculadora;
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 480);
		setResizable(false);
		setContentPane(getPnPrincipal());
		asociarTeclado();
		actualizar();
		setLocationRelativeTo(null);
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setBackground(COLOR_FONDO);
			pnPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
			pnPrincipal.setLayout(new BorderLayout(0, 20));
			pnPrincipal.add(getPnPantalla(), BorderLayout.NORTH);
			pnPrincipal.add(getPnBotones(), BorderLayout.CENTER);
		}
		return pnPrincipal;
	}

	private JPanel getPnPantalla() {
		if (pnPantalla == null) {
			pnPantalla = new JPanel();
			pnPantalla.setBackground(COLOR_PANTALLA);
			pnPantalla.setBorder(new EmptyBorder(6, 10, 4, 10));
			pnPantalla.setLayout(new BorderLayout(0, 0));
			pnPantalla.add(getLblOperacion(), BorderLayout.NORTH);
			pnPantalla.add(getTxtPantalla(), BorderLayout.CENTER);
		}
		return pnPantalla;
	}

	private JLabel getLblOperacion() {
		if (lblOperacion == null) {
			lblOperacion = new JLabel(" ");
			lblOperacion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOperacion.setForeground(COLOR_TEXTO_PANTALLA);
			lblOperacion.setFont(new Font("Monospaced", Font.PLAIN, 14));
		}
		return lblOperacion;
	}

	private JTextField getTxtPantalla() {
		if (txtPantalla == null) {
			txtPantalla = new JTextField();
			// Solo lectura, pero se puede seleccionar y copiar el resultado
			txtPantalla.setEditable(false);
			txtPantalla.setFocusable(false);
			txtPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPantalla.setFont(new Font("Monospaced", Font.BOLD, 30));
			txtPantalla.setForeground(COLOR_TEXTO_PANTALLA);
			txtPantalla.setBackground(COLOR_PANTALLA);
			txtPantalla.setBorder(null);
		}
		return txtPantalla;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setOpaque(false);
			pnBotones.setLayout(new GridBagLayout());
			// Fila a fila, como en el enunciado: 7 8 9 + / 4 5 6 - / 1 2 3 x / 0 =
			int[][] cifras = { { 7, 8, 9 }, { 4, 5, 6 }, { 1, 2, 3 } };
			for (int fila = 0; fila < cifras.length; fila++)
				for (int col = 0; col < cifras[fila].length; col++)
					pnBotones.add(getBtDigito(cifras[fila][col]), restricciones(col, fila, 1));
			pnBotones.add(getBtSuma(), restricciones(3, 0, 1));
			pnBotones.add(getBtResta(), restricciones(3, 1, 1));
			pnBotones.add(getBtMultiplicacion(), restricciones(3, 2, 1));
			pnBotones.add(getBtDigito(0), restricciones(0, 3, 1));
			pnBotones.add(getBtIgual(), restricciones(1, 3, 3));
		}
		return pnBotones;
	}

	private GridBagConstraints restricciones(int x, int y, int ancho) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = ancho;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		return gbc;
	}

	private JButton getBtDigito(int digito) {
		if (btDigitos == null)
			btDigitos = new JButton[10];
		if (btDigitos[digito] == null) {
			JButton bt = crearBoton(String.valueOf(digito), COLOR_DIGITO);
			bt.setActionCommand(String.valueOf(digito));
			bt.setMnemonic(KeyEvent.VK_0 + digito);
			bt.setToolTipText("Escribir " + digito + " (tecla " + digito + ")");
			bt.addActionListener(pD);
			btDigitos[digito] = bt;
		}
		return btDigitos[digito];
	}

	private JButton getBtSuma() {
		if (btSuma == null) {
			btSuma = crearBotonOperador(Operador.SUMA, "Sumar (tecla +)");
		}
		return btSuma;
	}

	private JButton getBtResta() {
		if (btResta == null) {
			btResta = crearBotonOperador(Operador.RESTA, "Restar (tecla -)");
		}
		return btResta;
	}

	private JButton getBtMultiplicacion() {
		if (btMultiplicacion == null) {
			btMultiplicacion = crearBotonOperador(Operador.MULTIPLICACION, "Multiplicar (tecla * o x)");
		}
		return btMultiplicacion;
	}

	private JButton getBtIgual() {
		if (btIgual == null) {
			btIgual = crearBoton("=", COLOR_IGUAL);
			btIgual.setToolTipText("Calcular el resultado (tecla = o Intro)");
			btIgual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calcular();
				}
			});
		}
		return btIgual;
	}

	private JButton crearBotonOperador(Operador operador, String tooltip) {
		JButton bt = crearBoton(operador.getSimbolo(), COLOR_OPERADOR);
		bt.setActionCommand(operador.name());
		bt.setToolTipText(tooltip);
		bt.addActionListener(pO);
		return bt;
	}

	private JButton crearBoton(String texto, Color fondo) {
		JButton bt = new JButton(texto);
		bt.setFont(FUENTE_BOTON);
		bt.setForeground(Color.WHITE);
		bt.setBackground(fondo);
		bt.putClientProperty(COLOR_ORIGINAL, fondo);
		bt.setBorderPainted(false);
		return bt;
	}

	/** Habilita o deshabilita un botón y apaga su color si no se puede pulsar. */
	private void habilitar(JButton bt, boolean habilitado) {
		bt.setEnabled(habilitado);
		Color original = (Color) bt.getClientProperty(COLOR_ORIGINAL);
		bt.setBackground(habilitado ? original : apagar(original));
	}

	private Color apagar(Color c) {
		// mitad del color del botón, mitad del fondo de la calculadora
		return new Color((c.getRed() + COLOR_FONDO.getRed()) / 2, (c.getGreen() + COLOR_FONDO.getGreen()) / 2,
				(c.getBlue() + COLOR_FONDO.getBlue()) / 2);
	}

	// ---- Teclado: todas las funciones también sin ratón ----

	private void asociarTeclado() {
		for (int d = 0; d <= 9; d++) {
			asociar(KeyStroke.getKeyStroke((char) ('0' + d)), getBtDigito(d));
			asociar(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0 + d, 0), getBtDigito(d));
		}
		asociar(KeyStroke.getKeyStroke('+'), getBtSuma());
		asociar(KeyStroke.getKeyStroke('-'), getBtResta());
		asociar(KeyStroke.getKeyStroke('*'), getBtMultiplicacion());
		asociar(KeyStroke.getKeyStroke('x'), getBtMultiplicacion());
		asociar(KeyStroke.getKeyStroke('X'), getBtMultiplicacion());
		asociar(KeyStroke.getKeyStroke('='), getBtIgual());
		asociar(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), getBtIgual());
	}

	private void asociar(KeyStroke tecla, JButton boton) {
		InputMap im = getPnPrincipal().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		String nombre = "pulsar-" + tecla;
		im.put(tecla, nombre);
		getPnPrincipal().getActionMap().put(nombre, new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				// doClick no hace nada si el botón está deshabilitado
				boton.doClick();
			}
		});
	}

	// ---- Eventos ----

	class ProcesaDigito implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int digito = Integer.parseInt(e.getActionCommand());
			calculadora.pulsarDigito(digito);
			actualizar();
		}
	}

	class ProcesaOperador implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			calculadora.elegirOperador(Operador.valueOf(e.getActionCommand()));
			actualizar();
		}
	}

	private void calcular() {
		calculadora.calcular();
		actualizar();
	}

	/** Pinta la pantalla y habilita solo lo que se puede pulsar en este momento. */
	private void actualizar() {
		getTxtPantalla().setText(calculadora.getPantalla());
		String operacion = calculadora.getOperacion();
		getLblOperacion().setText(operacion.isEmpty() ? " " : operacion);

		boolean digitos = calculadora.isDigitoPermitido();
		for (JButton bt : btDigitos)
			habilitar(bt, digitos);
		boolean operadores = calculadora.isOperadorPermitido();
		habilitar(getBtSuma(), operadores);
		habilitar(getBtResta(), operadores);
		habilitar(getBtMultiplicacion(), operadores);
		habilitar(getBtIgual(), calculadora.isIgualPermitido());

		if (calculadora.isTerminada())
			getPnPantalla().setToolTipText("Operación terminada: cierra la calculadora para hacer otra");
	}
}
