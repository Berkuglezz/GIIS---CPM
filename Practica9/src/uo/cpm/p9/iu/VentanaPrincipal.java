package uo.cpm.p9.iu;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p9.player.MusicPlayer;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String CARPETA_MUSICA = "Music"; // carpeta por defecto del selector (dentro del proyecto)
	private JPanel contentPane;
	private MusicPlayer mp;
	private JFileChooser selector;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntm;
	private JSeparator separatorFile;
	private JMenuItem mntmExit;
	private JMenu mnplay;
	private JMenuItem mntmPlay;
	private JMenuItem mntmStop;
	private JMenuItem mntmPrevious;
	private JMenuItem mntmNext;
	private JSeparator separatorPlay;
	private JMenuItem mntmRandom;
	private JMenu mnOption;
	private JMenuItem mntmClearLibrary;
	private JMenuItem mntmClearPlayList;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JPanel panelNorte;
	private JLabel lblTitulo;
	private JSlider sliderVolumen;
	private JPanel panelVolumen;
	private JLabel lblVol;
	private JLabel lblVolNumero;
	private JPanel panelCentro;
	private JPanel panelLibreria;
	private JPanel panelPlay;
	private JLabel lblLibreria;
	private JLabel lblplayList;
	private JPanel panelBotonesLibreria;
	private JButton btnAddPlaylist;
	private JButton btnDel;
	private JPanel panelBotonesPlayList;
	private JButton btnRetroceder;
	private JButton btnPlay;
	private JScrollPane scrollPaneLibrary;
	private JScrollPane scrollPaneListPlayList;
	private DefaultListModel<File> modeloListlibrary;
	private DefaultListModel<File> modeloListPlayList;
	private JList<File> listLibrary;
	private JList<File> listPlaylist;
	private JButton btnParar;
	private JButton btnSigueinte;
	private JButton btnBorrar;
	/**
	 * Create the frame.
	 * @param mP
	 */
	public VentanaPrincipal(MusicPlayer mP) {
		setMinimumSize(new Dimension(150, 300));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("MusicPlayer");
		this.mp = mP;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 497);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		mp.setVolume(getSliderVolumen().getValue());
		actualizarBotones();
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(new Color(0, 0, 0));
			menuBar.add(getMnFile());
			menuBar.add(getMnplay());
			menuBar.add(getMnOption());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntm());
			mnFile.add(getSeparatorFile());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenuItem getMntm() {
		if (mntm == null) {
			mntm = new JMenuItem("Open...");
			mntm.setMnemonic('O');
			mntm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
			mntm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirCanciones();
				}
			});
		}
		return mntm;
	}
	private JSeparator getSeparatorFile() {
		if (separatorFile == null) {
			separatorFile = new JSeparator();
		}
		return separatorFile;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setMnemonic('x');
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}
	private JMenu getMnplay() {
		if (mnplay == null) {
			mnplay = new JMenu("Play");
			mnplay.setMnemonic('P');
			mnplay.add(getMntmPlay());
			mnplay.add(getMntmStop());
			mnplay.add(getMntmPrevious());
			mnplay.add(getMntmNext());
			mnplay.add(getSeparatorPlay());
			mnplay.add(getMntmRandom());
		}
		return mnplay;
	}
	private JMenuItem getMntmPlay() {
		if (mntmPlay == null) {
			mntmPlay = new JMenuItem("Play");
			mntmPlay.setMnemonic('P');
			mntmPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reproducir();
				}
			});
		}
		return mntmPlay;
	}
	private JMenuItem getMntmStop() {
		if (mntmStop == null) {
			mntmStop = new JMenuItem("Stop");
			mntmStop.setMnemonic('S');
			mntmStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
				}
			});
		}
		return mntmStop;
	}
	private JMenuItem getMntmPrevious() {
		if (mntmPrevious == null) {
			mntmPrevious = new JMenuItem("Previous");
			mntmPrevious.setMnemonic('v');
			mntmPrevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					retroceder();
				}
			});
		}
		return mntmPrevious;
	}
	private JMenuItem getMntmNext() {
		if (mntmNext == null) {
			mntmNext = new JMenuItem("Next");
			mntmNext.setMnemonic('N');
			mntmNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					avanzar();
				}
			});
		}
		return mntmNext;
	}
	private JSeparator getSeparatorPlay() {
		if (separatorPlay == null) {
			separatorPlay = new JSeparator();
		}
		return separatorPlay;
	}
	private JMenuItem getMntmRandom() {
		if (mntmRandom == null) {
			mntmRandom = new JMenuItem("Random");
			mntmRandom.setMnemonic('R');
			mntmRandom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
			mntmRandom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.random();
					seleccionarCancionActual();
				}
			});
		}
		return mntmRandom;
	}
	private JMenu getMnOption() {
		if (mnOption == null) {
			mnOption = new JMenu("Option");
			mnOption.setMnemonic('O');
			mnOption.add(getMntmClearLibrary());
			mnOption.add(getMntmClearPlayList());
		}
		return mnOption;
	}
	private JMenuItem getMntmClearLibrary() {
		if (mntmClearLibrary == null) {
			mntmClearLibrary = new JMenuItem("Clear Library");
			mntmClearLibrary.setMnemonic('L');
			mntmClearLibrary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaciarLibreria();
				}
			});
		}
		return mntmClearLibrary;
	}
	private JMenuItem getMntmClearPlayList() {
		if (mntmClearPlayList == null) {
			mntmClearPlayList = new JMenuItem("Clear PlayList");
			mntmClearPlayList.setMnemonic('P');
			mntmClearPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vaciarPlayList();
				}
			});
		}
		return mntmClearPlayList;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('A');
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(VentanaPrincipal.this,
							"EII Music Player\nComunicación Persona Máquina - Práctica 9", "About",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAbout;
	}
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBackground(new Color(0, 0, 0));
			panelNorte.setLayout(new GridLayout(1, 3, 0, 0));
			panelNorte.add(getLblTitulo());
			panelNorte.add(getSliderVolumen());
			panelNorte.add(getPanelVolumen());
		}
		return panelNorte;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("");
			lblTitulo.setForeground(new Color(0, 0, 0));
			lblTitulo.setBackground(new Color(0, 0, 0));
			lblTitulo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lblTitulo;
	}
	private JSlider getSliderVolumen() {
		if (sliderVolumen == null) {
			sliderVolumen = new JSlider();
			sliderVolumen.setValue(mp.getVolume());
			sliderVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					pintaYCambiaVol();
				}
			});
			sliderVolumen.setPaintLabels(true);
			sliderVolumen.setPaintTicks(true);
			sliderVolumen.setMajorTickSpacing(20);
			sliderVolumen.setMinorTickSpacing(10);
			sliderVolumen.setForeground(new Color(255, 255, 255));
			sliderVolumen.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
			sliderVolumen.setBackground(new Color(0, 0, 0));
		}
		return sliderVolumen;
	}
	protected void pintaYCambiaVol() {
		getLblVolNumero().setText(String.valueOf(getSliderVolumen().getValue()));
		mp.setVolume(getSliderVolumen().getValue());
	}
	private JPanel getPanelVolumen() {
		if (panelVolumen == null) {
			panelVolumen = new JPanel();
			panelVolumen.setBackground(new Color(0, 0, 0));
			FlowLayout fl_panelVolumen = new FlowLayout(FlowLayout.CENTER, 15, 15);
			panelVolumen.setLayout(fl_panelVolumen);
			panelVolumen.add(getLblVol());
			panelVolumen.add(getLblVolNumero());
		}
		return panelVolumen;
	}
	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(new Color(255, 0, 128));
			lblVol.setBackground(new Color(0, 0, 0));
			lblVol.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 19));
		}
		return lblVol;
	}
	private JLabel getLblVolNumero() {
		if (lblVolNumero == null) {
			lblVolNumero = new JLabel(String.valueOf(mp.getVolume()));
			lblVolNumero.setForeground(new Color(0, 255, 64));
			lblVolNumero.setBackground(new Color(0, 0, 0));
			lblVolNumero.setFont(new Font("Tahoma", Font.PLAIN, 23));
		}
		return lblVolNumero;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBackground(new Color(0, 0, 0));
			panelCentro.setLayout(new GridLayout(0, 2, 3, 0));
			panelCentro.add(getPanelLibreria());
			panelCentro.add(getPanelPlay());
		}
		return panelCentro;
	}
	private JPanel getPanelLibreria() {
		if (panelLibreria == null) {
			panelLibreria = new JPanel();
			panelLibreria.setBackground(new Color(0, 0, 0));
			panelLibreria.setLayout(new BorderLayout(0, 0));
			panelLibreria.add(getLblLibreria(), BorderLayout.NORTH);
			panelLibreria.add(getPanelBotonesLibreria(), BorderLayout.SOUTH);
			panelLibreria.add(getScrollPaneLibrary(), BorderLayout.CENTER);
		}
		return panelLibreria;
	}
	private JPanel getPanelPlay() {
		if (panelPlay == null) {
			panelPlay = new JPanel();
			panelPlay.setBackground(new Color(0, 0, 0));
			panelPlay.setLayout(new BorderLayout(0, 0));
			panelPlay.add(getLblplayList(), BorderLayout.NORTH);
			panelPlay.add(getPanelBotonesPlayList(), BorderLayout.SOUTH);
			panelPlay.add(getScrollPaneListPlayList(), BorderLayout.CENTER);
		}
		return panelPlay;
	}
	private JLabel getLblLibreria() {
		if (lblLibreria == null) {
			lblLibreria = new JLabel("Library");
			lblLibreria.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblLibreria.setForeground(new Color(255, 0, 128));
		}
		return lblLibreria;
	}
	private JLabel getLblplayList() {
		if (lblplayList == null) {
			lblplayList = new JLabel("PlayList");
			lblplayList.setForeground(new Color(255, 0, 128));
			lblplayList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblplayList;
	}
	private JPanel getPanelBotonesLibreria() {
		if (panelBotonesLibreria == null) {
			panelBotonesLibreria = new JPanel();
			panelBotonesLibreria.setBackground(new Color(0, 0, 0));
			panelBotonesLibreria.setLayout(new GridLayout(1, 2, 0, 0));
			panelBotonesLibreria.add(getBtnAddPlaylist());
			panelBotonesLibreria.add(getBtnDel());
		}
		return panelBotonesLibreria;
	}
	private JButton getBtnAddPlaylist() {
		if (btnAddPlaylist == null) {
			btnAddPlaylist = new JButton("Add playList");
			btnAddPlaylist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirAPlayList();
				}
			});
		}
		return btnAddPlaylist;
	}
	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton("Del");
			btnDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.deleteListLibrary(getListLibrary().getSelectedValuesList());
					refrescarLibreria();
				}
			});
		}
		return btnDel;
	}
	private JPanel getPanelBotonesPlayList() {
		if (panelBotonesPlayList == null) {
			panelBotonesPlayList = new JPanel();
			panelBotonesPlayList.setLayout(new GridLayout(0, 5, 0, 0));
			panelBotonesPlayList.add(getBtnRetroceder());
			panelBotonesPlayList.add(getButton_1());
			panelBotonesPlayList.add(getBtnParar());
			panelBotonesPlayList.add(getBtnSigueinte());
			panelBotonesPlayList.add(getBtnBorrar());
		}
		return panelBotonesPlayList;
	}
	private JButton getBtnRetroceder() {
		if (btnRetroceder == null) {
			btnRetroceder = new JButton("<<");
			btnRetroceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					retroceder();
				}
			});
		}
		return btnRetroceder;
	}
	private JButton getButton_1() {
		if (btnPlay == null) {
			btnPlay = new JButton(">");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reproducir();
				}
			});
		}
		return btnPlay;
	}
	private JScrollPane getScrollPaneLibrary() {
		if (scrollPaneLibrary == null) {
			scrollPaneLibrary = new JScrollPane();
			scrollPaneLibrary.setBorder(new LineBorder(new Color(128, 0, 64), 5));
			scrollPaneLibrary.setViewportView(getListLibrary());
		}
		return scrollPaneLibrary;
	}
	private JScrollPane getScrollPaneListPlayList() {
		if (scrollPaneListPlayList == null) {
			scrollPaneListPlayList = new JScrollPane();
			scrollPaneListPlayList.setBorder(new LineBorder(new Color(128, 0, 64), 5));
			scrollPaneListPlayList.setViewportView(getListPlaylist());
		}
		return scrollPaneListPlayList;
	}
	private JList<File> getListLibrary() {
		if (listLibrary == null) {
			modeloListlibrary = new DefaultListModel<File>();
			listLibrary = new JList<File>(modeloListlibrary);
			listLibrary.setCellRenderer(new SongRenderer()); // P9: solo se ve el nombre de la canción
			listLibrary.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					actualizarBotones();
				}
			});
			listLibrary.setForeground(new Color(255, 255, 255));
			listLibrary.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
			listLibrary.setBackground(new Color(0, 0, 0));
		}
		return listLibrary;
	}
	private JList<File> getListPlaylist() {
		if (listPlaylist == null) {
			modeloListPlayList = new DefaultListModel<File>();
			listPlaylist = new JList<File>(modeloListPlayList);
			listPlaylist.setCellRenderer(new SongRenderer()); // P9: solo se ve el nombre de la canción
			listPlaylist.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					actualizarBotones();
				}
			});
			listPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlaylist.setForeground(new Color(255, 255, 255));
			listPlaylist.setBackground(new Color(0, 0, 0));
			listPlaylist.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		}
		return listPlaylist;
	}
	private JButton getBtnParar() {
		if (btnParar == null) {
			btnParar = new JButton("o");
			btnParar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
				}
			});
		}
		return btnParar;
	}
	private JButton getBtnSigueinte() {
		if (btnSigueinte == null) {
			btnSigueinte = new JButton(">>");
			btnSigueinte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					avanzar();
				}
			});
		}
		return btnSigueinte;
	}
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Del");
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.deleteListPlay(getListPlaylist().getSelectedValue());
					refrescarPlayList();
				}
			});
		}
		return btnBorrar;
	}

	/**********************************************
	 * ACCIONES *
	 **********************************************/

	/**
	 * P9: selector que solo deja ver mp3 y empieza en la carpeta de música por
	 * defecto. Se crea una sola vez para que recuerde la última carpeta usada.
	 */
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			selector.setAcceptAllFileFilterUsed(false);
			File musica = new File(System.getProperty("user.dir"), CARPETA_MUSICA);
			selector.setCurrentDirectory(musica.isDirectory() ? musica : new File(System.getProperty("user.home")));
		}
		return selector;
	}

	private void abrirCanciones() {
		if (getSelector().showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			mp.addListLibrary(getSelector().getSelectedFiles());
			refrescarLibreria();
		}
	}

	/**
	 * P9: no se añaden canciones que ya estén en la lista de reproducción.
	 */
	private void anadirAPlayList() {
		List<File> seleccion = getListLibrary().getSelectedValuesList();
		int repetidas = seleccion.size() - mp.addListPlay(seleccion);
		refrescarPlayList();
		if (repetidas > 0)
			JOptionPane.showMessageDialog(this,
					repetidas == 1 ? "La canción ya estaba en la lista de reproducción y no se ha vuelto a añadir."
							: repetidas + " canciones ya estaban en la lista de reproducción y no se han vuelto a añadir.",
					"Canciones repetidas", JOptionPane.INFORMATION_MESSAGE);
	}

	private void reproducir() {
		File cancion = getListPlaylist().getSelectedValue();
		if (cancion == null && !modeloListPlayList.isEmpty())
			cancion = modeloListPlayList.getElementAt(0);
		if (cancion == null)
			return;
		mp.setCurrentSong(cancion);
		mp.play();
		seleccionarCancionActual();
	}

	private void retroceder() {
		if (mp.getCurrentSong() == null)
			mp.setCurrentSong(getListPlaylist().getSelectedValue());
		mp.rewind();
		seleccionarCancionActual();
	}

	private void avanzar() {
		if (mp.getCurrentSong() == null)
			mp.setCurrentSong(getListPlaylist().getSelectedValue());
		mp.forward();
		seleccionarCancionActual();
	}

	private void vaciarLibreria() {
		if (JOptionPane.showConfirmDialog(this, "¿Seguro que quieres vaciar la librería?", "Clear Library",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			mp.clearListLibrary();
			refrescarLibreria();
		}
	}

	private void vaciarPlayList() {
		if (JOptionPane.showConfirmDialog(this, "¿Seguro que quieres vaciar la lista de reproducción?",
				"Clear PlayList", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			mp.clearListPlay();
			refrescarPlayList();
		}
	}

	/**********************************************
	 * MODELO -> INTERFAZ *
	 **********************************************/

	private void refrescarLibreria() {
		modeloListlibrary.clear();
		for (File f : mp.getListLibrary())
			modeloListlibrary.addElement(f);
		actualizarBotones();
	}

	private void refrescarPlayList() {
		modeloListPlayList.clear();
		for (File f : mp.getListPlay())
			modeloListPlayList.addElement(f);
		seleccionarCancionActual();
		actualizarBotones();
	}

	private void seleccionarCancionActual() {
		if (mp.getCurrentSong() != null)
			getListPlaylist().setSelectedValue(mp.getCurrentSong(), true);
	}

	/**
	 * P8: habilita o deshabilita los botones y opciones según el estado de las
	 * listas.
	 */
	private void actualizarBotones() {
		boolean seleccionLibreria = !getListLibrary().isSelectionEmpty();
		boolean hayPlayList = !modeloListPlayList.isEmpty();

		getBtnAddPlaylist().setEnabled(seleccionLibreria);
		getBtnDel().setEnabled(seleccionLibreria);

		getBtnRetroceder().setEnabled(hayPlayList);
		getButton_1().setEnabled(hayPlayList);
		getBtnParar().setEnabled(hayPlayList);
		getBtnSigueinte().setEnabled(hayPlayList);
		getBtnBorrar().setEnabled(!getListPlaylist().isSelectionEmpty());

		getMntmPlay().setEnabled(hayPlayList);
		getMntmStop().setEnabled(hayPlayList);
		getMntmPrevious().setEnabled(hayPlayList);
		getMntmNext().setEnabled(hayPlayList);
		getMntmRandom().setEnabled(hayPlayList);

		getMntmClearLibrary().setEnabled(!modeloListlibrary.isEmpty());
		getMntmClearPlayList().setEnabled(hayPlayList);
	}
}
