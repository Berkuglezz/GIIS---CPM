package uo.cpm.p8.player;

import java.io.File;
import java.util.*;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	public static final double VOLUME_MAX = 100;
	public static final double VOLUME_MIN = 0;

	private BasicPlayer basicPlayer = null;
	private List<File> listLibrary = null;
	private List<File> listPlay = null;
	private File currentSong; // representa la cancion que está seleccionada en el ListPlay
	private int volume = 0; // representa el volumen actual
	private Random random = new Random();

	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
		listLibrary = new ArrayList<File>();
		listPlay = new ArrayList<File>();
		currentSong = null;
		volume = 50;
	}

	public List<File> getListLibrary() {
		return listLibrary;
	}

	public List<File> getListPlay() {
		return listPlay;
	}

	public File getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(File f) {
		this.currentSong = f;
	}

	public int getVolume() {
		return volume;
	}

	/**********************************************
	 * LIBRERÍA *
	 **********************************************/

	/**
	 * Añade a la librería los ficheros seleccionados que no estén ya en ella.
	 */
	public void addListLibrary(File[] selectedFiles) {
		for (File f : selectedFiles)
			if (!listLibrary.contains(f))
				listLibrary.add(f);
	}

	public void deleteListLibrary(List<File> selectedValuesList) {
		listLibrary.removeAll(selectedValuesList);
	}

	/**
	 * Vacía la librería. La lista de reproducción no se toca.
	 */
	public void clearListLibrary() {
		listLibrary.clear();
	}

	/**********************************************
	 * LISTA DE REPRODUCCIÓN *
	 **********************************************/

	/**
	 * Añade a la lista de reproducción las canciones que todavía no estén en
	 * ella (no se permiten duplicados).
	 *
	 * @return número de canciones que se han añadido realmente
	 */
	public int addListPlay(List<File> selectedValuesList) {
		int added = 0;
		for (File f : selectedValuesList)
			if (!listPlay.contains(f)) {
				listPlay.add(f);
				added++;
			}
		return added;
	}

	public void deleteListPlay(File selectedValue) {
		if (selectedValue == null)
			return;
		if (selectedValue.equals(currentSong)) {
			stop();
			currentSong = null;
		}
		listPlay.remove(selectedValue);
	}

	/**
	 * Vacía la lista de reproducción, parando antes la canción que suene.
	 */
	public void clearListPlay() {
		stop();
		currentSong = null;
		listPlay.clear();
	}

	/**********************************************
	 * REPRODUCTOR *
	 **********************************************/

	public void play() {
		if (currentSong == null)
			return;
		try {
			stop(); // si ya sonaba otra canción, se para antes de abrir la nueva
			basicPlayer.open(currentSong);
			basicPlayer.play();
			setVolume(volume);
		} catch (BasicPlayerException e) {
			System.out.println("Error en play");
		}
	}

	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
			System.out.println("Error en stop");
		}
	}

	public void rewind() {
		if (listPlay.isEmpty())
			return;
		int position = listPlay.indexOf(currentSong);
		if (position > 0)
			currentSong = listPlay.get(position - 1);
		else
			currentSong = listPlay.get(listPlay.size() - 1); // si estamos en la primera posición, pasamos a la última
		play();
	}

	public void forward() {
		if (listPlay.isEmpty())
			return;
		int position = listPlay.indexOf(currentSong);
		if (position != listPlay.size() - 1)
			currentSong = listPlay.get(position + 1); // con position == -1 (sin canción) empieza por la primera
		else
			currentSong = listPlay.get(0); // si estamos en la última posición, pasamos a la primera
		play();
	}

	/**
	 * Reproduce una canción aleatoria de la lista de reproducción. Si hay más de
	 * una, nunca repite la que está sonando.
	 */
	public void random() {
		if (listPlay.isEmpty())
			return;
		int position = listPlay.indexOf(currentSong);
		int next;
		do {
			next = random.nextInt(listPlay.size());
		} while (listPlay.size() > 1 && next == position);
		currentSong = listPlay.get(next);
		play();
	}

	public void setVolume(int vol) {
		try {
			this.volume = vol;
			if (basicPlayer.getStatus() == BasicPlayer.PLAYING)
				basicPlayer.setGain(vol / VOLUME_MAX);
		} catch (BasicPlayerException e) {
			System.out.println("Error en volumen");
		}
	}
}
