package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.IChooseAircraftController;
import controller.ISoundButtonHover;
import model.AircraftObserver;
import model.IModelAircraft;
import model.strategypattern.Aircraft;
import utils.Music;

public class ChoosePanel extends JPanel implements AircraftObserver {
	private JComboBox<String> comboBox;
	private JLabel imageLabel;
	private JButton startButton;
	private JButton backButton;

	private String[] aircraftOptions = { "Hoàng tử sa mạc", "Đại bàng xanh" };
	private String[] imagePaths = { "/res/942346.png", "/res/jet.png" };

	private IModelAircraft model;
	private IChooseAircraftController controller;
	private ISoundButtonHover soundController;

	public ChoosePanel(IModelAircraft model, IChooseAircraftController controller, ISoundButtonHover soundButtonHover) {
		this.model = model;
		this.controller = controller;
		this.soundController = soundButtonHover;
		if (this.model != null) {
			this.model.addObserver(this);
		}

		setLayout(new BorderLayout());

		// TOP panel
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		JLabel titleLabel = new JLabel("Chọn máy bay:");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		titleLabel.setForeground(new Color(255, 215, 0));
		titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		topPanel.add(titleLabel);

		comboBox = new JComboBox<>(aircraftOptions);
		topPanel.add(comboBox);


		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setPreferredSize(new Dimension(300, 200));
		updateImage(0);


		startButton = createStyledButton("Start");
		backButton = createStyledButton("Back");

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
		bottomPanel.setOpaque(false);
		bottomPanel.add(backButton);
		bottomPanel.add(startButton);

		comboBox.addActionListener(e -> controller.onAircraftSelected(comboBox.getSelectedIndex()));
		startButton.addActionListener(e -> controller.onConfirmAircraftSelection());
		add(topPanel, BorderLayout.NORTH);
		add(imageLabel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(140, 40));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setOpaque(false);
		button.setFont(new Font("Arial", Font.BOLD, 18));
		button.setForeground(Color.WHITE);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				button.setForeground(Color.YELLOW);
				soundController.playHoverSound();
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				button.setForeground(Color.WHITE);
			}
		});

		return button;
	}

	public void updateAircraftImage(int index) {
		updateImage(index);
	}

	private void updateImage(int index) {
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[index]));
		Image scaled = icon.getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH);
		imageLabel.setIcon(new ImageIcon(scaled));
	}

	public String getSelectedAircraftName() {
		return (String) comboBox.getSelectedItem();
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ImageIcon bgImage = new ImageIcon(getClass().getResource("/res/background.jpg"));
		g2d.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void onAircraftConfirmed(Aircraft selectedAircraft) {
//		JOptionPane.showMessageDialog(this, "Máy bay đã chọn: " + selectedAircraft.getClass().getSimpleName(),
//				"Thông báo", JOptionPane.INFORMATION_MESSAGE);
	}

}
