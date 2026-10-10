package uo.cpm.p3.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import uo.cpm.p3.service.McDonalds;

import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Toolkit;

public class VentanaRegistro extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelDatosCliente;
	private JPanel panelPedido;
	private JButton btnCancelar;
	private JButton btnSiguiente;
	private JLabel lblNombreYApellidos;
	private JLabel lblAñoDeNacimiento;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JTextField textFieldNombreYapellidos;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> comboBoxAños;
	private McDonalds mc;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(McDonalds mc) {
		this.mc = mc;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/img/logo.png")));
		setTitle("MacDonald´s: Datos de registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//cierrate y elimin al cerrar
		setBounds(100, 100, 454, 312);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelDatosCliente());
		contentPane.add(getPanelPedido());
		contentPane.add(getBtnCancelar());
		contentPane.add(getBtnSiguiente());

	}

	private JPanel getPanelDatosCliente() {
		if (panelDatosCliente == null) {
			panelDatosCliente = new JPanel();
			panelDatosCliente.setBorder(new TitledBorder(null, "cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDatosCliente.setBounds(10, 11, 418, 185);
			panelDatosCliente.setLayout(null);
			panelDatosCliente.add(getLblNombreYApellidos());
			panelDatosCliente.add(getLblAñoDeNacimiento());
			panelDatosCliente.add(getLblPassword());
			panelDatosCliente.add(getLblRepeatPassword());
			panelDatosCliente.add(getTextFieldNombreYapellidos());
			panelDatosCliente.add(getPasswordField());
			panelDatosCliente.add(getRepeatPasswordField());
			panelDatosCliente.add(getComboBoxAños());
		}
		return panelDatosCliente;
	}
	private JPanel getPanelPedido() {
		if (panelPedido == null) {
			panelPedido = new JPanel();
			panelPedido.setBorder(new TitledBorder(null, "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPedido.setBounds(10, 207, 201, 60);
			panelPedido.setLayout(null);
			panelPedido.add(getRdbtnNewRadioButton());
			panelPedido.add(getRdbtnNewRadioButton_1());
		}
		return panelPedido;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('c');
			btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();//elimina el objeto actual. cierra la ventana registro pero el resto de objetos quedan como estan
				}
			});
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setBackground(new Color(255, 0, 0));
			btnCancelar.setBounds(334, 244, 94, 23);
		}
		return btnCancelar;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setMnemonic('S');
			btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 14));
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarValores()==true) {
						mostrarVentanaFinal();
					}
				}
			});
			btnSiguiente.setBackground(new Color(128, 255, 0));
			btnSiguiente.setForeground(new Color(255, 255, 255));
			btnSiguiente.setBounds(230, 244, 94, 23);
		}
		return btnSiguiente;
	}
	protected void mostrarVentanaFinal() {
		VentanaConfirmacion vc = new VentanaConfirmacion(mc);
		vc.setModal(true);
		vc.setVisible(true);
		
	}

	protected boolean validarValores() {
		if((validarNombre() && validarPassword())==true) {
			actualizarDatosPedido();
			return true;
		}
		return false;
	}

	private void actualizarDatosPedido() {
	    String nombre = getTextFieldNombreYapellidos().getText().trim();
	    int anio = Integer.parseInt((String) getComboBoxAños().getSelectedItem());
	    String password = new String(getPasswordField().getPassword());

	    mc.guardarCliente(nombre, anio, password);
	    mc.actualizarTipoPedido(getRdbtnNewRadioButton_1().isSelected());
	}

	private boolean validarPassword() {
		if(!String.valueOf(getPasswordField().getPassword()).equals(String.valueOf(getRepeatPasswordField().getPassword()))) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
			return false;
		}
		return true;
	}

	private boolean validarNombre() {
		if(getTextFieldNombreYapellidos().getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "El nombre esta vacio.");
			return false;
		}
		return true;
	}

	private JLabel getLblNombreYApellidos() {
		if (lblNombreYApellidos == null) {
			lblNombreYApellidos = new JLabel("nombre y apellidos: ");
			lblNombreYApellidos.setLabelFor(getTextFieldNombreYapellidos());
			lblNombreYApellidos.setDisplayedMnemonic('n');
			lblNombreYApellidos.setFont(new Font("Arial", Font.PLAIN, 14));
			lblNombreYApellidos.setBounds(10, 29, 140, 14);
		}
		return lblNombreYApellidos;
	}
	private JLabel getLblAñoDeNacimiento() {
		if (lblAñoDeNacimiento == null) {
			lblAñoDeNacimiento = new JLabel("Año de nacimiento:");
			lblAñoDeNacimiento.setDisplayedMnemonic('A');
			lblAñoDeNacimiento.setLabelFor(getComboBoxAños());
			lblAñoDeNacimiento.setFont(new Font("Arial", Font.PLAIN, 14));
			lblAñoDeNacimiento.setBounds(10, 69, 140, 14);
		}
		return lblAñoDeNacimiento;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setDisplayedMnemonic('P');
			lblPassword.setLabelFor(getPasswordField());
			lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
			lblPassword.setBounds(10, 108, 99, 14);
		}
		return lblPassword;
	}
	private JLabel getLblRepeatPassword() {
		if (lblRepeatPassword == null) {
			lblRepeatPassword = new JLabel("Repeat password:");
			lblRepeatPassword.setLabelFor(getRepeatPasswordField());
			lblRepeatPassword.setDisplayedMnemonic('R');
			lblRepeatPassword.setFont(new Font("Arial", Font.PLAIN, 14));
			lblRepeatPassword.setBounds(10, 145, 119, 14);
		}
		return lblRepeatPassword;
	}
	private JTextField getTextFieldNombreYapellidos() {
		if (textFieldNombreYapellidos == null) {
			textFieldNombreYapellidos = new JTextField();
			textFieldNombreYapellidos.setBounds(135, 26, 273, 20);
			textFieldNombreYapellidos.setColumns(10);
		}
		return textFieldNombreYapellidos;
	}

	
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(135, 105, 273, 20);
		}
		return passwordField;
	}
	private JPasswordField getRepeatPasswordField() {
		if (repeatPasswordField == null) {
			repeatPasswordField = new JPasswordField();
			repeatPasswordField.setBounds(135, 142, 273, 20);
			
		}
		return repeatPasswordField;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("Local");
			rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 14));
			rdbtnNewRadioButton.setSelected(true);
			buttonGroup.add(rdbtnNewRadioButton);
			rdbtnNewRadioButton.setBounds(21, 21, 88, 23);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Llevar");
			rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.PLAIN, 14));
			buttonGroup.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.setBounds(112, 21, 83, 23);
		}
		return rdbtnNewRadioButton_1;
	}
	private JComboBox<String> getComboBoxAños() {
		if (comboBoxAños == null) {
			comboBoxAños = new JComboBox<String>(mc.getAnos());
			comboBoxAños.setBounds(135, 65, 51, 22);
		}
		return comboBoxAños;
	}
}
