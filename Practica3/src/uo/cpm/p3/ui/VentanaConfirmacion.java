package uo.cpm.p3.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p3.service.McDonalds;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmacion extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogoTick;
	private JLabel lblMensajeFinalizar;
	private JTextField textFieldCodigo;
	private JLabel lblCodigo;
	private JButton btnFinalizar;
	private JTextField textFieldPrecioFinal;
	private JLabel lblNewLabel;
	private VentanaRegistro vr;
	private McDonalds mc;
	/**
	 * Create the frame.
	 * @param ventanaRegistro 
	 */
	public VentanaConfirmacion(McDonalds mc) {
		this.mc = mc;
		setTitle("McDonals:Confirmacion");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogoTick());
		contentPane.add(getLblMensajeFinalizar());
		contentPane.add(getTextFieldCodigo());
		contentPane.add(getLblCodigo());
		contentPane.add(getBtnFinalizar());
		contentPane.add(getTextFieldPrecioFinal());
		contentPane.add(getLblNewLabel());

	}
	private JLabel getLblLogoTick() {
		if (lblLogoTick == null) {
			lblLogoTick = new JLabel("");
			lblLogoTick.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lblLogoTick.setBounds(10, 47, 106, 75);
		}
		return lblLogoTick;
	}
	private JLabel getLblMensajeFinalizar() {
		if (lblMensajeFinalizar == null) {
			lblMensajeFinalizar = new JLabel("Pulsa finalizar para confirmar: ");
			lblMensajeFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
			lblMensajeFinalizar.setBounds(94, 81, 287, 14);
		}
		return lblMensajeFinalizar;
	}
	private JTextField getTextFieldCodigo() {
		if (textFieldCodigo == null) {
			textFieldCodigo = new JTextField();
			textFieldCodigo.setEnabled(false);
			textFieldCodigo.setBounds(170, 141, 96, 20);
			textFieldCodigo.setColumns(10);
		}
		return textFieldCodigo;
	}
	private JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("Cogido pedido: ");
			lblCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
			lblCodigo.setBounds(65, 143, 142, 14);
		}
		return lblCodigo;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
			btnFinalizar.setBackground(new Color(0, 255, 0));
			btnFinalizar.setForeground(new Color(255, 255, 255));
			btnFinalizar.setFont(new Font("Arial", Font.PLAIN, 14));
			btnFinalizar.setBounds(339, 231, 89, 23);
		}
		return btnFinalizar;
	}
	protected void finalizar() {
		mc.grabarPedido();
		JOptionPane.showMessageDialog(null, "Muchas gracias por su compra");
		System.exit(0);
	}
	private JTextField getTextFieldPrecioFinal() {
		if (textFieldPrecioFinal == null) {
			textFieldPrecioFinal = new JTextField();
			textFieldPrecioFinal.setEditable(false);
			textFieldPrecioFinal.setBounds(170, 119, 96, 20);
			textFieldPrecioFinal.setColumns(10);
			textFieldPrecioFinal.setText(String.format("%.2f €", mc.getImportePedido()));
		}
		return textFieldPrecioFinal;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Precio final: ");
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
			lblNewLabel.setBounds(65, 121, 106, 14);
		}
		return lblNewLabel;
	}
}
