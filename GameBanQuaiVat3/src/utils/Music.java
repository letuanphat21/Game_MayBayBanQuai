package utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public class Music {

	private Clip clip;
	private FloatControl volumeControl;

	// Hàm phát âm thanh của sự vật khi hover í
	public void playSound(String soundFile) {
		try {
			URL url = getClass().getResource(soundFile);
			if (url == null) {
				System.err.println("Không tìm thấy file âm thanh: " + soundFile);
				return;
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void playBackgroundMusic(String soundFile) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(soundFile);
			if (inputStream == null) {
				System.err.println("Không tìm thấy file: " + soundFile);
				return;
			}

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
			clip = AudioSystem.getClip();
			clip.open(audioStream);

			// Lấy bộ điều khiển âm lượng
			volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// Phát nhạc nền lặp vô hạn
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Đặt âm lượng cho nhạc nền (mượt hơn)
	public void setClipVolume(int value) {
		if (volumeControl != null) {
			float min = Math.max(volumeControl.getMinimum(), -30.0f); // Giới hạn tối đa -30dB
			float max = volumeControl.getMaximum();
			float volume = min + (max - min) * (value / 100f);

			// Cập nhật âm lượng
			Timer timer = new Timer(10, e -> volumeControl.setValue(volume));
			timer.setRepeats(false);
			timer.start();
		}
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public FloatControl getVolumeControl() {
		return volumeControl;
	}

	public void setVolumeControl(FloatControl volumeControl) {
		this.volumeControl = volumeControl;
	}

}
