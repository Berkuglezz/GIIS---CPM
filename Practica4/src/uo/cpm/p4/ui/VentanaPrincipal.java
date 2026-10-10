package uo.cpm.p4.ui;

import java.awt.EventQueue;

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

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
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

	/**
	 * Create the frame.
	 * @param macdonalds 
	 */
	public VentanaPrincipal(McDonalds macdonalds) {
		this.mc = macdonalds;
		setResizable(false);
		setTitle("MacDonal´s españa");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 390);
		setLocationRelativeTo(null);
		contentPanePrincipal = new JPanel();
		contentPanePrincipal.setBackground(new Color(255, 255, 255));
		contentPanePrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePrincipal);
		contentPanePrincipal.setLayout(null);
		contentPanePrincipal.add(getLblLogo());
		contentPanePrincipal.add(getLblMacdonalds());
		contentPanePrincipal.add(getLblArticulos());
		contentPanePrincipal.add(getCbArticulos());
		contentPanePrincipal.add(getLblUnidades());
		contentPanePrincipal.add(getSpUnidades());
		contentPanePrincipal.add(getBtnAnadir());
		contentPanePrincipal.add(getLblPrecioPedido());
		contentPanePrincipal.add(getTextFieldPrecio());
		contentPanePrincipal.add(getBtnNewButton());
		contentPanePrincipal.add(getBtnSiguiente());
		contentPanePrincipal.add(getLblUnidadesAnadidas());
		contentPanePrincipal.add(getBtnEliminar());

	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblLogo.setBackground(new Color(255, 255, 255));
			lblLogo.setBounds(10, 11, 210, 130);
		}
		return lblLogo;
	}
	private JLabel getLblMacdonalds() {
		if (lblMacdonalds == null) {
			lblMacdonalds = new JLabel("McDonald´s");
			lblMacdonalds.setFont(new Font("Arial", Font.BOLD, 44));
			lblMacdonalds.setBackground(new Color(240, 240, 240));
			lblMacdonalds.setBounds(256, 36, 298, 103);
		}
		return lblMacdonalds;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Articulos:");
			lblArticulos.setDisplayedMnemonic('a');
			lblArticulos.setLabelFor(getCbArticulos());
			lblArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
			lblArticulos.setBounds(10, 165, 74, 21);
		}
		return lblArticulos;
	}
	private JComboBox<Articulo> getCbArticulos() {
		if (cbArticulos == null) {
			cbArticulos = new JComboBox<Articulo>();
			cbArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpUnidades().setValue(1);
					actualizarTexto();
				}
			});
			cbArticulos.setModel(new DefaultComboBoxModel<Articulo>(mc.getArticulosCarta()));
			cbArticulos.setFont(new Font("Arial", Font.PLAIN, 12));
			cbArticulos.setBounds(10, 184, 303, 22);
		}
		return cbArticulos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("Unidades:");
			lblUnidades.setDisplayedMnemonic('U');
			lblUnidades.setLabelFor(getSpUnidades());
			lblUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			lblUnidades.setBounds(345, 168, 69, 14);
		}
		return lblUnidades;
	}
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spUnidades.setFont(new Font("Arial", Font.PLAIN, 12));
			spUnidades.setBounds(345, 185, 49, 20);
		}
		return spUnidades;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("Anadir");
			btnAnadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirApedido();
					actualizarTexto();
				}
			});
			btnAnadir.setMnemonic('I');
			btnAnadir.setBackground(new Color(0, 255, 0));
			btnAnadir.setForeground(new Color(255, 255, 255));
			btnAnadir.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAnadir.setBounds(430, 184, 74, 23);
		}
		return btnAnadir;
	}
	
	protected void actualizarTexto() {
		getLblUnidadesAnadidas().setText("Usted tiene "+ articulosEnLaLista()+" unidades de este articulo");
	}

	private int articulosEnLaLista() {
	    Articulo seleccionado = (Articulo) getCbArticulos().getSelectedItem();
	    if (seleccionado != null) {
	        return mc.getUnidadesArticulo(seleccionado);
	    }
	    return 0;
	}

	protected void anadirApedido() {
		Articulo articuloSeleccionado = (Articulo) getCbArticulos().getSelectedItem();
		int unidadesSeleccionados = (int) getSpUnidades().getValue();
		mc.aniadirAPedido(articuloSeleccionado, unidadesSeleccionados);
		String precio = String.format("% .2f",mc.getImportePedido());
		getTextFieldPrecio().setText(precio+" €");
		if(!getBtnSiguiente().isEnabled()) {
			getBtnSiguiente().setEnabled(true);
		}	
	}

	private JLabel getLblPrecioPedido() {
		if (lblPrecioPedido == null) {
			lblPrecioPedido = new JLabel("Precio pedido:");
			lblPrecioPedido.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPrecioPedido.setBounds(345, 216, 118, 14);
		}
		return lblPrecioPedido;
	}
	private JTextField getTextFieldPrecio() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setEnabled(false);
			textFieldPrecio.setBounds(345, 230, 118, 29);
			textFieldPrecio.setColumns(10);
		}
		return textFieldPrecio;
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
			btnNewButton.setBounds(473, 321, 113, 23);
		}
		return btnNewButton;
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
			btnSiguiente.setBackground(new Color(0, 255, 0));
			btnSiguiente.setForeground(new Color(255, 255, 255));
			btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 12));
			btnSiguiente.setBounds(345, 321, 118, 23);
		}
		return btnSiguiente;
	}

	protected void mostrarVentanaRegistro() {
		VentanaRegistro vp = new VentanaRegistro(mc,this);
		vp.setModal(true);
		vp.setVisible(true);
	}
	
	public McDonalds getMc() {
		return mc;
	}
	private JLabel getLblUnidadesAnadidas() {
		if (lblUnidadesAnadidas == null) {
			lblUnidadesAnadidas = new JLabel("");
			lblUnidadesAnadidas.setFont(new Font("Arial", Font.PLAIN, 14));
			lblUnidadesAnadidas.setBounds(10, 216, 283, 14);
		}
		return lblUnidadesAnadidas;
	}
	
	private void resetearVentanaPrincipal() {
		mc.inicializarPedido();
		getCbArticulos().setSelectedIndex(0);
		getSpUnidades().setValue(1);
		getTextFieldPrecio().setText("");
		getLblUnidadesAnadidas().setText("");
		getBtnSiguiente().setEnabled(false);
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setMnemonic('E');
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Arial", Font.PLAIN, 12));
			btnEliminar.setBackground(new Color(255, 0, 0));
			btnEliminar.setBounds(514, 184, 74, 23);
		}
		return btnEliminar;
	}
}
