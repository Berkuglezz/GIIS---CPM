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

	/**
	 * Create the frame.
	 * @param macdonalds
	 */
	public VentanaPrincipal(McDonalds macdonalds) {
		this.mc = macdonalds;
		setResizable(false);
		setTitle("McDonald's España");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 432);
		setLocationRelativeTo(null);
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
		actualizarEstado();
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblLogo.setBounds(20, 55, 144, 130);
		}
		return lblLogo;
	}
	private JLabel getLblMacdonalds() {
		if (lblMacdonalds == null) {
			lblMacdonalds = new JLabel("McDonald's");
			lblMacdonalds.setFont(new Font("Arial", Font.BOLD, 44));
			lblMacdonalds.setBounds(190, 135, 300, 60);
		}
		return lblMacdonalds;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Artículos:");
			lblArticulos.setDisplayedMnemonic('r');
			lblArticulos.setLabelFor(getCbArticulos());
			lblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
			lblArticulos.setBounds(25, 205, 100, 20);
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
			cbArticulos.setBounds(25, 237, 390, 25);
		}
		return cbArticulos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades:");
			lblUnidades.setDisplayedMnemonic('U');
			lblUnidades.setLabelFor(getSpUnidades());
			lblUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			lblUnidades.setBounds(428, 205, 80, 20);
		}
		return lblUnidades;
	}
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			spUnidades.setBounds(428, 237, 60, 25);
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
			btnAnadir.setBounds(514, 237, 90, 25);
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
			btnEliminar.setBounds(641, 237, 90, 25);
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
		getBtnEliminar().setEnabled(mc.isArticuloEnPedido(seleccionado));
		getBtnSiguiente().setEnabled(!mc.isPedidoVacio());
		getTextFieldPrecio().setText(mc.isPedidoVacio() ? "" : String.format(ES, "%.2f €", mc.getImportePedido()));
		getTxtAreaPedido().setText(mc.getTextoPedido());
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
			btnSiguiente.setBounds(514, 357, 90, 25);
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
			btnNewButton.setBounds(641, 357, 90, 25);
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
					getScrollPedido().setVisible(tglbtnPedido.isSelected());
				}
			});
			tglbtnPedido.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/pedido.png")));
			tglbtnPedido.setToolTipText("Muestra u oculta el pedido actual");
			tglbtnPedido.setMnemonic('P');
			tglbtnPedido.setBackground(new Color(255, 170, 40));
			tglbtnPedido.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnPedido.setBounds(586, 5, 145, 52);
		}
		return tglbtnPedido;
	}
	private JScrollPane getScrollPedido() {
		if (scrollPedido == null) {
			scrollPedido = new JScrollPane();
			scrollPedido.setVisible(false);
			scrollPedido.setBounds(513, 62, 218, 120);
			scrollPedido.setViewportView(getTxtAreaPedido());
		}
		return scrollPedido;
	}
	private JTextArea getTxtAreaPedido() {
		if (txtAreaPedido == null) {
			txtAreaPedido = new JTextArea();
			txtAreaPedido.setEditable(false);
			txtAreaPedido.setLineWrap(true);
			txtAreaPedido.setWrapStyleWord(true);
			txtAreaPedido.setFont(new Font("Arial", Font.PLAIN, 12));
		}
		return txtAreaPedido;
	}
}
