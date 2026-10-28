package uo.cpm.p4.ui;

import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p4.model.Articulo;
import uo.cpm.p4.service.McDonalds;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Locale ES = Locale.of("es", "ES");
	private JPanel contentPanePrincipal;
	private JLabel lblLogo;
	private JLabel lblMacdonalds;
	private JLabel lblArticulos;
	private JComboBox<Articulo> cbArticulos;
	private JLabel lblUnidades;
	private JSpinner spUnidades;
	private JButton btnAnadir;
	private JLabel lblPrecioPedido;
	private JTextField textFieldPrecio;
	private JButton btnNewButton;
	private JButton btnSiguiente;
	private McDonalds mc;
	private JLabel lblUnidadesAnadidas;
	private JButton btnEliminar;
	private JToggleButton tglbtnPedido;
	private JScrollPane scrollPedido;
	private JTextArea txtAreaPedido;
	private JLabel lblImagenArticuloSelecionado;
	private JMenuBar menuBar;
	private JMenu mnPedido;
	private JMenuItem mntmNuevo;
	private JSeparator separatorPedido;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JSeparator separatorAyuda;
	private JMenuItem mntmAcercaDe;
	private JPanel panelFiltro;
	private JButton btnTodos;
	private JButton btnHamburguesa;
	private JButton btnBebida;
	private JButton btnComplemento;
	private JButton btnPostre;
	private ProcesaFiltro procesaFiltro;

	private static final String TODOS = "Todos";

	// Un solo receptor para todos los botones del filtro: el tipo llega en el actionCommand
	class ProcesaFiltro implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			filtrar(e.getActionCommand());
		}
	}

	/**
	 * Create the frame.
	 * @param macdonalds
	 */
	public VentanaPrincipal(McDonalds macdonalds) {
		this.mc = macdonalds;
		procesaFiltro = new ProcesaFiltro();
		setResizable(false);
		setTitle("McDonald's España");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 420);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBackground(new Color(255, 255, 255));
		contentPanePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePrincipal);
		contentPanePrincipal.setLayout(null);
		contentPanePrincipal.add(getTglbtnPedido());
		contentPanePrincipal.add(getScrollPedido());
		contentPanePrincipal.add(getLblLogo());
		contentPanePrincipal.add(getLblMacdonalds());
		contentPanePrincipal.add(getLblArticulos());
		contentPanePrincipal.add(getCbArticulos());
		contentPanePrincipal.add(getLblUnidades());
		contentPanePrincipal.add(getSpUnidades());
		contentPanePrincipal.add(getBtnAnadir());
		contentPanePrincipal.add(getBtnEliminar());
		contentPanePrincipal.add(getLblUnidadesAnadidas());
		contentPanePrincipal.add(getLblPrecioPedido());
		contentPanePrincipal.add(getTextFieldPrecio());
		contentPanePrincipal.add(getBtnSiguiente());
		contentPanePrincipal.add(getBtnNewButton());
		contentPanePrincipal.add(getLblImagenArticuloSelecionado());
		contentPanePrincipal.add(getTxtAreaPedido());
		contentPanePrincipal.add(getPanelFiltro());
		actualizarEstado();
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblLogo.setBounds(124, 55, 144, 130);
		}
		return lblLogo;
	}
	private JLabel getLblMacdonalds() {
		if (lblMacdonalds == null) {
			lblMacdonalds = new JLabel("McDonald's");
			lblMacdonalds.setFont(new Font("Arial", Font.BOLD, 44));
			lblMacdonalds.setBounds(331, 126, 300, 60);
		}
		return lblMacdonalds;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Artículos:");
			lblArticulos.setDisplayedMnemonic('r');
			lblArticulos.setLabelFor(getCbArticulos());
			lblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
			lblArticulos.setBounds(124, 205, 100, 20);
		}
		return lblArticulos;
	}
	private JComboBox<Articulo> getCbArticulos() {
		if (cbArticulos == null) {
			cbArticulos = new JComboBox<Articulo>();
			cbArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpUnidades().setValue(1);
					actualizarEstado();
				}
			});
			cbArticulos.setModel(new DefaultComboBoxModel<Articulo>(mc.getArticulosCarta()));
			cbArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
			cbArticulos.setBounds(127, 237, 390, 25);
		}
		return cbArticulos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades:");
			lblUnidades.setDisplayedMnemonic('U');
			lblUnidades.setLabelFor(getSpUnidades());
			lblUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			lblUnidades.setBounds(523, 205, 80, 20);
		}
		return lblUnidades;
	}
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					actualizarEstado();
				}
			});
			spUnidades.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			spUnidades.setBounds(529, 237, 60, 25);
		}
		return spUnidades;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Añadir");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirApedido();
				}
			});
			btnAnadir.setMnemonic('A');
			btnAnadir.setBackground(new Color(0, 160, 0));
			btnAnadir.setForeground(new Color(255, 255, 255));
			btnAnadir.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAnadir.setBounds(618, 237, 90, 25);
		}
		return btnAnadir;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarDePedido();
				}
			});
			btnEliminar.setMnemonic('E');
			btnEliminar.setBackground(new Color(0, 160, 0));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Arial", Font.PLAIN, 12));
			btnEliminar.setBounds(743, 237, 90, 25);
		}
		return btnEliminar;
	}

	// Refleja en la interfaz el estado actual del pedido
	private void actualizarEstado() {
		Articulo seleccionado = getArticuloSeleccionado();
		int unidades = mc.getUnidadesArticulo(seleccionado);
		getLblUnidadesAnadidas().setText(unidades > 0
				? "Hay " + unidades + " uds. de este artículo en el pedido"
				: "");
		getBtnEliminar().setEnabled(mc.sePuedeEliminar(seleccionado, (int) getSpUnidades().getValue()));
		getBtnSiguiente().setEnabled(!mc.isPedidoVacio());
		getTextFieldPrecio().setText(mc.isPedidoVacio() ? "" : String.format(ES, "%.2f €", mc.getImportePedido()));
		getTxtAreaPedido().setText(mc.getTextoPedido());
		mostrarImagenArticulo();
	}

	private Articulo getArticuloSeleccionado() {
		return (Articulo) getCbArticulos().getSelectedItem();
	}

	protected void anadirApedido() {
		int unidadesSeleccionados = (int) getSpUnidades().getValue();
		mc.aniadirAPedido(getArticuloSeleccionado(), unidadesSeleccionados);
		actualizarEstado();
	}

	protected void eliminarDePedido() {
		int unidadesSeleccionados = (int) getSpUnidades().getValue();
		mc.eliminarDePedido(getArticuloSeleccionado(), unidadesSeleccionados);
		actualizarEstado();
	}

	private JLabel getLblUnidadesAnadidas() {
		if (lblUnidadesAnadidas == null) {
			lblUnidadesAnadidas = new JLabel("");
			lblUnidadesAnadidas.setForeground(Color.RED);
			lblUnidadesAnadidas.setFont(new Font("Arial", Font.BOLD, 12));
			lblUnidadesAnadidas.setBounds(40, 272, 375, 20);
		}
		return lblUnidadesAnadidas;
	}
	private JLabel getLblPrecioPedido() {
		if (lblPrecioPedido == null) {
			lblPrecioPedido = new JLabel("Precio Pedido:");
			lblPrecioPedido.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPrecioPedido.setBounds(511, 290, 120, 20);
		}
		return lblPrecioPedido;
	}
	private JTextField getTextFieldPrecio() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setEditable(false);
			textFieldPrecio.setFont(new Font("Serif", Font.BOLD, 14));
			textFieldPrecio.setBounds(513, 313, 90, 24);
			textFieldPrecio.setColumns(10);
		}
		return textFieldPrecio;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaRegistro();
				}
			});
			btnSiguiente.setMnemonic('S');
			btnSiguiente.setBackground(new Color(0, 160, 0));
			btnSiguiente.setForeground(new Color(255, 255, 255));
			btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 12));
			btnSiguiente.setBounds(618, 357, 90, 25);
		}
		return btnSiguiente;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Cancelar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					resetearVentanaPrincipal();
				}
			});
			btnNewButton.setMnemonic('C');
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBackground(new Color(255, 0, 0));
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
			btnNewButton.setBounds(743, 357, 90, 25);
		}
		return btnNewButton;
	}

	protected void mostrarVentanaRegistro() {
		VentanaRegistro vp = new VentanaRegistro(mc,this);
		vp.setModal(true);
		vp.setVisible(true);
	}

	public McDonalds getMc() {
		return mc;
	}

	// Deja la ventana preparada para el pedido del siguiente cliente
	public void resetearVentanaPrincipal() {
		mc.inicializarPedido();
		getTglbtnPedido().setSelected(false);
		getScrollPedido().setVisible(false);
		getCbArticulos().setSelectedIndex(0);	// su actionPerformed actualiza el estado
		getSpUnidades().setValue(1);
		actualizarEstado();
	}

	private JToggleButton getTglbtnPedido() {
		if (tglbtnPedido == null) {
			tglbtnPedido = new JToggleButton("Pedido");
			tglbtnPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verPedido();
				}
			});
			tglbtnPedido.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/pedido.png")));
			tglbtnPedido.setToolTipText("Muestra u oculta el pedido actual");
			tglbtnPedido.setMnemonic('P');
			tglbtnPedido.setBackground(new Color(255, 170, 40));
			tglbtnPedido.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnPedido.setBounds(688, -2, 145, 52);
		}
		return tglbtnPedido;
	}
	
	private void verPedido() {
		boolean mostrar = getTglbtnPedido().isSelected();
		if (mostrar) {
			getTxtAreaPedido().setText(mc.getTextoPedido());
		}
		getScrollPedido().setVisible(mostrar);
	}
	private JScrollPane getScrollPedido() {
		if (scrollPedido == null) {
			scrollPedido = new JScrollPane();
			scrollPedido.setVisible(false);
			scrollPedido.setBounds(618, 65, 218, 120);
		}
		return scrollPedido;
	}
	private JTextArea getTxtAreaPedido() {
		if (txtAreaPedido == null) {
			txtAreaPedido = new JTextArea();
			txtAreaPedido.setBounds(618, 68, 215, 117);
			txtAreaPedido.setEditable(false);
			txtAreaPedido.setLineWrap(true);
			txtAreaPedido.setWrapStyleWord(true);
			txtAreaPedido.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return txtAreaPedido;
	}
	private JLabel getLblImagenArticuloSelecionado() {
		if (lblImagenArticuloSelecionado == null) {
			lblImagenArticuloSelecionado = new JLabel("");
			lblImagenArticuloSelecionado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/HA01.png")));
			lblImagenArticuloSelecionado.setBounds(159, 302, 145, 118);
		}
		return lblImagenArticuloSelecionado;
	}
	private void mostrarImagenArticulo() {
		String fotoArticulo = "/img/" + ((Articulo) getCbArticulos().getSelectedItem()).getCodigo() + ".png";
		getLblImagenArticuloSelecionado().setIcon(new ImageIcon(VentanaPrincipal.class.getResource(fotoArticulo)));

	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnPedido());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnPedido() {
		if (mnPedido == null) {
			mnPedido = new JMenu("Pedido");
			mnPedido.setMnemonic('d');
			mnPedido.add(getMntmNuevo());
			mnPedido.add(getSeparatorPedido());
			mnPedido.add(getMntmSalir());
		}
		return mnPedido;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					resetearVentanaPrincipal();
				}
			});
			mntmNuevo.setMnemonic('N');
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNuevo;
	}
	private JSeparator getSeparatorPedido() {
		if (separatorPedido == null) {
			separatorPedido = new JSeparator();
		}
		return separatorPedido;
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
			mntmContenidos.setEnabled(false);	// la ayuda en línea se hace más adelante
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
	private void mostrarAcercaDe() {
		JOptionPane.showMessageDialog(null,"Aplicacion para TPV de comida rapida \n Realizada por ...\n Practicas CPM 26-27 \n EII Oviedo","Acerca de", JOptionPane.INFORMATION_MESSAGE);
	}
	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			panelFiltro = new JPanel();
			panelFiltro.setBackground(new Color(255, 255, 255));
			panelFiltro.setBounds(12, 12, 103, 370);
			panelFiltro.setLayout(new GridLayout(5, 1, 0, 0));
			panelFiltro.add(getBtnTodos());
			panelFiltro.add(getBtnHamburguesa());
			panelFiltro.add(getBtnBebida());
			panelFiltro.add(getBtnComplemento());
			panelFiltro.add(getBtnPostre());
		}
		return panelFiltro;
	}
	private JButton getBtnTodos() {
		if (btnTodos == null) {
			btnTodos = new JButton("");
			btnTodos.addActionListener(procesaFiltro);
			btnTodos.setActionCommand(TODOS);
			btnTodos.setToolTipText("Todos los artículos");
			btnTodos.setBackground(new Color(255, 255, 255));
			btnTodos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Todos.png")));
		}
		return btnTodos;
	}
	private JButton getBtnHamburguesa() {
		if (btnHamburguesa == null) {
			btnHamburguesa = new JButton("");
			btnHamburguesa.addActionListener(procesaFiltro);
			btnHamburguesa.setActionCommand("Hamburguesa");
			btnHamburguesa.setToolTipText("Hamburguesas");
			btnHamburguesa.setBackground(new Color(255, 255, 255));
			btnHamburguesa.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Hamburguesa.png")));
		}
		return btnHamburguesa;
	}
	private JButton getBtnBebida() {
		if (btnBebida == null) {
			btnBebida = new JButton("");
			btnBebida.addActionListener(procesaFiltro);
			btnBebida.setActionCommand("Bebida");
			btnBebida.setToolTipText("Bebidas");
			btnBebida.setBackground(new Color(255, 255, 255));
			btnBebida.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Bebida.png")));
		}
		return btnBebida;
	}
	private JButton getBtnComplemento() {
		if (btnComplemento == null) {
			btnComplemento = new JButton("");
			btnComplemento.addActionListener(procesaFiltro);
			btnComplemento.setActionCommand("Complemento");
			btnComplemento.setToolTipText("Complementos");
			btnComplemento.setBackground(new Color(255, 255, 255));
			btnComplemento.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Complemento.png")));
		}
		return btnComplemento;
	}
	private JButton getBtnPostre() {
		if (btnPostre == null) {
			btnPostre = new JButton("");
			btnPostre.addActionListener(procesaFiltro);
			btnPostre.setActionCommand("Postre");
			btnPostre.setToolTipText("Postres");
			btnPostre.setBackground(new Color(255, 255, 255));
			btnPostre.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Postre.png")));
		}
		return btnPostre;
	}

	// Deja en el combo solo los artículos del tipo elegido
	private void filtrar(String tipo) {
		Articulo[] articulos = TODOS.equals(tipo) ? mc.getArticulosCarta() : mc.getArticulosCarta(tipo);
		getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(articulos));
		getCbArticulos().setSelectedIndex(0);	// su actionPerformed carga la imagen y actualiza el estado
	}
}
