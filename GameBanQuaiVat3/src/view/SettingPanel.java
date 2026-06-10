package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.ISettingController;
import controller.ISoundButtonHover;
import model.IModel;
import model.ObserverVolume;

public class SettingPanel extends JPanel implements ObserverVolume {

	private JSlider volumeSlider;
	private ISettingController controller;
	private ISoundButtonHover controller2;
	private IModel model; // để lấy lại volume khi update
	private JLabel volumeLabel;
	private JButton backButton;

	public SettingPanel(IModel model, ISettingController controller, ISoundButtonHover controller2) {
		this.model = model;
		this.controller = controller;
		this.controller2 = controller2;
		this.model.registerObserverVolume(this); // đăng ký nhận cập nhật

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, model.getVolume());
		volumeSlider.setOpaque(false);
		volumeSlider.setPreferredSize(new Dimension(200, 40));
		volumeSlider.addChangeListener(e -> {
			int value = volumeSlider.getValue();
			controller.changeVolume(value);
		});

		volumeLabel = new JLabel("Volume:");
		volumeLabel.setForeground(Color.WHITE);
		volumeLabel.setFont(new Font("Arial", Font.BOLD, 16));

		backButton = new JButton("Back");
		backButton.setFont(new Font("Arial", Font.BOLD, 50));
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setOpaque(false);
		backButton.setForeground(Color.WHITE);
		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				backButton.setForeground(Color.YELLOW);
				controller2.playHoverSound();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				backButton.setForeground(Color.WHITE);
			}
		});
		gbc.gridy = 0;
		add(volumeLabel, gbc);

		gbc.gridy = 1;
		add(volumeSlider, gbc);

		gbc.gridy = 2;
		add(backButton, gbc);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ImageIcon bgImage = new ImageIcon(getClass().getResource("/res/background.jpg"));
		g2d.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	public JButton getBackButton() {
		return backButton;
	}

	@Override
	public void updateVolume() {
		int updatedVolume = model.getVolume();
		volumeSlider.setValue(updatedVolume);
	}

}
