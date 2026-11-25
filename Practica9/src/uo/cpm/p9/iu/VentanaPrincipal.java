package uo.cpm.p9.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import uo.cpm.p9.player.MusicPlayer;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Color FONDO = Color.BLACK;
	private static final Color ROSA = new Color(255, 0, 255);
	private static final Color AZUL = new Color(0, 191, 255);
	private static final String CARPETA_MUSICA = "Music";

	private JPanel contentPane;
	private MusicPlayer mp;
	private JFileChooser selector;

	// Menú
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JSeparator separatorFile;
	private JMenuItem mntmExit;
	private JMenu mnPlay;
	private JMenuItem mntmPlay;
	private JMenuItem mntmStop;
	private JMenuItem mntmPrevious;
	private JMenuItem mntmNext;
	private JSeparator separatorPlay;
	private JMenuItem mntmRandom;
	private JMenu mnOptions;
	private JMenuItem mntmClearLibrary;
	private JMenuItem mntmClearPlayList;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;

	// Norte
	private JPanel panelNorte;
	private JLabel lblTitulo;
	private JSlider sliderVolumen;
	private JPanel panelVolumen;
	private JLabel lblVol;
	private JLabel lblVolNumero;

	// Centro
	private JPanel panelCentro;
	private JPanel panelLibreria;
	private JPanel panelPlay;
	private JLabel lblLibreria;
	private JLabel lblPlayList;
	private JScrollPane scrollPaneLibrary;
	private JScrollPane scrollPanePlayList;
	private DefaultListModel<File> modeloListLibrary;
	private DefaultListModel<File> modeloListPlayList;
	private JList<File> listLibrary;
	private JList<File> listPlayList;

	// Botones
	private JPanel panelBotonesLibreria;
	private JButton btnAddPlayList;
	private JButton btnDelLibrary;
	private JPanel panelBotonesPlayList;
	private JButton btnRetroceder;
	private JButton btnPlay;
	private JButton btnParar;
	private JButton btnSiguiente;
	private JButton btnDelPlayList;

	/**
	 * Create the frame.
	 *
	 * @param mP
	 */
	public VentanaPrincipal(MusicPlayer mP) {
		this.mp = mP;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 400));
		setBounds(100, 100, 800, 560);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(FONDO);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 5));
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
		mp.setVolume(getSliderVolumen().getValue());
		actualizarControles();
	}

	/**********************************************
	 * MENÚ *
	 **********************************************/

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparatorFile());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open...");
			mntmOpen.setMnemonic('O');
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirCanciones();
				}
			});
		}
		return mntmOpen;
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
					salir();
				}
			});
		}
		return mntmExit;
	}

	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('P');
			mnPlay.add(getMntmPlay());
			mnPlay.add(getMntmStop());
			mnPlay.add(getMntmPrevious());
			mnPlay.add(getMntmNext());
			mnPlay.add(getSeparatorPlay());
			mnPlay.add(getMntmRandom());
		}
		return mnPlay;
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
					parar();
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
					aleatoria();
				}
			});
		}
		return mntmRandom;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
			mnOptions.add(getMntmClearLibrary());
			mnOptions.add(getMntmClearPlayList());
		}
		return mnOptions;
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

	/**********************************************
	 * PANEL NORTE: logo y volumen *
	 **********************************************/

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBackground(FONDO);
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
			lblTitulo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lblTitulo;
	}

	private JSlider getSliderVolumen() {
		if (sliderVolumen == null) {
			sliderVolumen = new JSlider((int) MusicPlayer.VOLUME_MIN, (int) MusicPlayer.VOLUME_MAX, mp.getVolume());
			sliderVolumen.setToolTipText("Volume");
			sliderVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					pintaYCambiaVol();
				}
			});
			sliderVolumen.setPaintLabels(true);
			sliderVolumen.setPaintTicks(true);
			sliderVolumen.setMajorTickSpacing(20);
			sliderVolumen.setMinorTickSpacing(5);
			sliderVolumen.setForeground(Color.WHITE);
			sliderVolumen.setFont(new Font("Arial", Font.BOLD, 11));
			sliderVolumen.setBackground(FONDO);
		}
		return sliderVolumen;
	}

	private void pintaYCambiaVol() {
		int vol = getSliderVolumen().getValue();
		getLblVolNumero().setText(String.valueOf(vol));
		mp.setVolume(vol);
	}

	private JPanel getPanelVolumen() {
		if (panelVolumen == null) {
			panelVolumen = new JPanel();
			panelVolumen.setBackground(FONDO);
			panelVolumen.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
			panelVolumen.add(getLblVol());
			panelVolumen.add(getLblVolNumero());
		}
		return panelVolumen;
	}

	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(AZUL);
			lblVol.setFont(new Font("Arial", Font.BOLD, 30));
		}
		return lblVol;
	}

	private JLabel getLblVolNumero() {
		if (lblVolNumero == null) {
			lblVolNumero = new JLabel(String.valueOf(mp.getVolume()));
			lblVolNumero.setForeground(Color.WHITE);
			lblVolNumero.setFont(new Font("Arial", Font.BOLD, 36));
		}
		return lblVolNumero;
	}

	/**********************************************
	 * PANEL CENTRO: librería y lista de reproducción *
	 **********************************************/

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBackground(FONDO);
			panelCentro.setLayout(new GridLayout(1, 2, 10, 0));
			panelCentro.add(getPanelLibreria());
			panelCentro.add(getPanelPlay());
		}
		return panelCentro;
	}

	private JPanel getPanelLibreria() {
		if (panelLibreria == null) {
			panelLibreria = new JPanel();
			panelLibreria.setBackground(FONDO);
			panelLibreria.setLayout(new BorderLayout(0, 0));
			panelLibreria.add(getLblLibreria(), BorderLayout.NORTH);
			panelLibreria.add(getScrollPaneLibrary(), BorderLayout.CENTER);
			panelLibreria.add(getPanelBotonesLibreria(), BorderLayout.SOUTH);
		}
		return panelLibreria;
	}

	private JPanel getPanelPlay() {
		if (panelPlay == null) {
			panelPlay = new JPanel();
			panelPlay.setBackground(FONDO);
			panelPlay.setLayout(new BorderLayout(0, 0));
			panelPlay.add(getLblPlayList(), BorderLayout.NORTH);
			panelPlay.add(getScrollPanePlayList(), BorderLayout.CENTER);
			panelPlay.add(getPanelBotonesPlayList(), BorderLayout.SOUTH);
		}
		return panelPlay;
	}

	private JLabel getLblLibreria() {
		if (lblLibreria == null) {
			lblLibreria = new JLabel("♪ Library:");
			lblLibreria.setDisplayedMnemonic('L');
			lblLibreria.setLabelFor(getListLibrary());
			lblLibreria.setForeground(ROSA);
			lblLibreria.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblLibreria;
	}

	private JLabel getLblPlayList() {
		if (lblPlayList == null) {
			lblPlayList = new JLabel("♪ PlayList:");
			lblPlayList.setDisplayedMnemonic('y'); // la P ya la usa el menú Play
			lblPlayList.setLabelFor(getListPlayList());
			lblPlayList.setForeground(ROSA);
			lblPlayList.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblPlayList;
	}

	private JScrollPane getScrollPaneLibrary() {
		if (scrollPaneLibrary == null) {
			scrollPaneLibrary = new JScrollPane();
			scrollPaneLibrary.setBorder(new LineBorder(AZUL, 4));
			scrollPaneLibrary.setViewportView(getListLibrary());
		}
		return scrollPaneLibrary;
	}

	private JScrollPane getScrollPanePlayList() {
		if (scrollPanePlayList == null) {
			scrollPanePlayList = new JScrollPane();
			scrollPanePlayList.setBorder(new LineBorder(AZUL, 4));
			scrollPanePlayList.setViewportView(getListPlayList());
		}
		return scrollPanePlayList;
	}

	private JList<File> getListLibrary() {
		if (listLibrary == null) {
			modeloListLibrary = new DefaultListModel<File>();
			listLibrary = new JList<File>(modeloListLibrary);
			listLibrary.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			listLibrary.setCellRenderer(new SongRenderer()); // solo el nombre de la canción
			listLibrary.setForeground(Color.WHITE);
			listLibrary.setBackground(FONDO);
			listLibrary.setFont(new Font("Arial", Font.PLAIN, 14));
			listLibrary.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					actualizarControles();
				}
			});
			listLibrary.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2 && !listLibrary.isSelectionEmpty())
						anadirAPlayList(); // doble clic = Add to PlayList
				}
			});
		}
		return listLibrary;
	}

	private JList<File> getListPlayList() {
		if (listPlayList == null) {
			modeloListPlayList = new DefaultListModel<File>();
			listPlayList = new JList<File>(modeloListPlayList);
			listPlayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlayList.setCellRenderer(new SongRenderer()); // solo el nombre de la canción
			listPlayList.setForeground(Color.WHITE);
			listPlayList.setBackground(FONDO);
			listPlayList.setFont(new Font("Arial", Font.PLAIN, 14));
			listPlayList.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					actualizarControles();
				}
			});
			listPlayList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2 && !listPlayList.isSelectionEmpty())
						reproducir(); // doble clic = Play
				}
			});
		}
		return listPlayList;
	}

	/**********************************************
	 * BOTONES *
	 **********************************************/

	private JPanel getPanelBotonesLibreria() {
		if (panelBotonesLibreria == null) {
			panelBotonesLibreria = new JPanel();
			panelBotonesLibreria.setBackground(FONDO);
			panelBotonesLibreria.setLayout(new GridLayout(1, 2, 0, 0));
			panelBotonesLibreria.add(getBtnAddPlayList());
			panelBotonesLibreria.add(getBtnDelLibrary());
		}
		return panelBotonesLibreria;
	}

	private JButton getBtnAddPlayList() {
		if (btnAddPlayList == null) {
			btnAddPlayList = new JButton("Add to PlayList");
			btnAddPlayList.setMnemonic('A');
			btnAddPlayList.setToolTipText("Add the selected songs to the playlist");
			btnAddPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					anadirAPlayList();
				}
			});
		}
		return btnAddPlayList;
	}

	private JButton getBtnDelLibrary() {
		if (btnDelLibrary == null) {
			btnDelLibrary = new JButton("Del");
			btnDelLibrary.setMnemonic('D');
			btnDelLibrary.setToolTipText("Delete the selected songs from the library");
			btnDelLibrary.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.deleteListLibrary(getListLibrary().getSelectedValuesList());
					refrescarLibreria();
				}
			});
		}
		return btnDelLibrary;
	}

	private JPanel getPanelBotonesPlayList() {
		if (panelBotonesPlayList == null) {
			panelBotonesPlayList = new JPanel();
			panelBotonesPlayList.setBackground(FONDO);
			panelBotonesPlayList.setLayout(new GridLayout(1, 5, 0, 0));
			panelBotonesPlayList.add(getBtnRetroceder());
			panelBotonesPlayList.add(getBtnPlay());
			panelBotonesPlayList.add(getBtnParar());
			panelBotonesPlayList.add(getBtnSiguiente());
			panelBotonesPlayList.add(getBtnDelPlayList());
		}
		return panelBotonesPlayList;
	}

	private JButton getBtnRetroceder() {
		if (btnRetroceder == null) {
			btnRetroceder = new JButton("◄◄");
			btnRetroceder.setToolTipText("Previous song");
			btnRetroceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					retroceder();
				}
			});
		}
		return btnRetroceder;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("►");
			btnPlay.setToolTipText("Play the selected song");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reproducir();
				}
			});
		}
		return btnPlay;
	}

	private JButton getBtnParar() {
		if (btnParar == null) {
			btnParar = new JButton("■");
			btnParar.setToolTipText("Stop");
			btnParar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parar();
				}
			});
		}
		return btnParar;
	}

	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("►►");
			btnSiguiente.setToolTipText("Next song");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					avanzar();
				}
			});
		}
		return btnSiguiente;
	}

	private JButton getBtnDelPlayList() {
		if (btnDelPlayList == null) {
			btnDelPlayList = new JButton("Del");
			btnDelPlayList.setMnemonic('e');
			btnDelPlayList.setToolTipText("Delete the selected song from the playlist");
			btnDelPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.deleteListPlay(getListPlayList().getSelectedValue());
					refrescarPlayList();
				}
			});
		}
		return btnDelPlayList;
	}

	/**********************************************
	 * ACCIONES *
	 **********************************************/

	/**
	 * Selector de ficheros: solo deja ver mp3 y empieza en la carpeta de música
	 * por defecto. Se crea una vez para que recuerde la última carpeta usada.
	 */
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			selector.setAcceptAllFileFilterUsed(false);
			selector.setCurrentDirectory(directorioPorDefecto());
		}
		return selector;
	}

	/**
	 * Carpeta "Music" del proyecto si existe; si no, la carpeta del usuario.
	 */
	private File directorioPorDefecto() {
		File musica = new File(System.getProperty("user.dir"), CARPETA_MUSICA);
		if (musica.isDirectory())
			return musica;
		return new File(System.getProperty("user.home"));
	}

	private void abrirCanciones() {
		if (getSelector().showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			mp.addListLibrary(getSelector().getSelectedFiles());
			refrescarLibreria();
		}
	}

	private void anadirAPlayList() {
		List<File> seleccion = getListLibrary().getSelectedValuesList();
		int anadidas = mp.addListPlay(seleccion);
		refrescarPlayList();
		int repetidas = seleccion.size() - anadidas;
		if (repetidas > 0)
			JOptionPane.showMessageDialog(this,
					repetidas == 1 ? "La canción ya estaba en la lista de reproducción y no se ha vuelto a añadir."
							: repetidas + " canciones ya estaban en la lista de reproducción y no se han vuelto a añadir.",
					"Canciones repetidas", JOptionPane.INFORMATION_MESSAGE);
	}

	private void reproducir() {
		File cancion = getListPlayList().getSelectedValue();
		if (cancion == null && !modeloListPlayList.isEmpty())
			cancion = modeloListPlayList.getElementAt(0);
		if (cancion == null)
			return;
		mp.setCurrentSong(cancion);
		mp.play();
		seleccionarCancionActual();
	}

	private void parar() {
		mp.stop();
	}

	private void retroceder() {
		prepararCancionActual();
		mp.rewind();
		seleccionarCancionActual();
	}

	private void avanzar() {
		prepararCancionActual();
		mp.forward();
		seleccionarCancionActual();
	}

	private void aleatoria() {
		mp.random();
		seleccionarCancionActual();
	}

	/**
	 * Si todavía no ha sonado nada, << y >> parten de la canción seleccionada.
	 */
	private void prepararCancionActual() {
		if (mp.getCurrentSong() == null)
			mp.setCurrentSong(getListPlayList().getSelectedValue());
	}

	private void vaciarLibreria() {
		int r = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres vaciar la librería?", "Clear Library",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			mp.clearListLibrary();
			refrescarLibreria();
		}
	}

	private void vaciarPlayList() {
		int r = JOptionPane.showConfirmDialog(this, "¿Seguro que quieres vaciar la lista de reproducción?",
				"Clear PlayList", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) {
			mp.clearListPlay();
			refrescarPlayList();
		}
	}

	private void salir() {
		mp.stop();
		System.exit(0);
	}

	/**********************************************
	 * SINCRONIZACIÓN MODELO <-> INTERFAZ *
	 **********************************************/

	private void refrescarLibreria() {
		modeloListLibrary.clear();
		for (File f : mp.getListLibrary())
			modeloListLibrary.addElement(f);
		actualizarControles();
	}

	private void refrescarPlayList() {
		modeloListPlayList.clear();
		for (File f : mp.getListPlay())
			modeloListPlayList.addElement(f);
		seleccionarCancionActual();
		actualizarControles();
	}

	private void seleccionarCancionActual() {
		if (mp.getCurrentSong() != null)
			getListPlayList().setSelectedValue(mp.getCurrentSong(), true);
	}

	/**
	 * Habilita o deshabilita botones y opciones de menú según el estado de las
	 * listas (tarea de la práctica 8).
	 */
	private void actualizarControles() {
		boolean hayLibreria = !modeloListLibrary.isEmpty();
		boolean seleccionLibreria = !getListLibrary().isSelectionEmpty();
		boolean hayPlayList = !modeloListPlayList.isEmpty();
		boolean seleccionPlayList = !getListPlayList().isSelectionEmpty();

		getBtnAddPlayList().setEnabled(seleccionLibreria);
		getBtnDelLibrary().setEnabled(seleccionLibreria);

		getBtnPlay().setEnabled(hayPlayList);
		getBtnParar().setEnabled(hayPlayList);
		getBtnRetroceder().setEnabled(hayPlayList);
		getBtnSiguiente().setEnabled(hayPlayList);
		getBtnDelPlayList().setEnabled(seleccionPlayList);

		getMntmPlay().setEnabled(hayPlayList);
		getMntmStop().setEnabled(hayPlayList);
		getMntmPrevious().setEnabled(hayPlayList);
		getMntmNext().setEnabled(hayPlayList);
		getMntmRandom().setEnabled(hayPlayList);

		getMntmClearLibrary().setEnabled(hayLibreria);
		getMntmClearPlayList().setEnabled(hayPlayList);
	}
}
