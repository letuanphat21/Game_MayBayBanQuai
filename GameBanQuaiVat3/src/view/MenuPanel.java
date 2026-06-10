package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ISoundButtonHover;
import controller.MenuController;

public class MenuPanel extends JPanel {
	private JButton btnSettings;
	private JButton btnStart;
	private JButton btnGuide;
	private ISoundButtonHover soundController;
	private MenuController controller;

	public MenuPanel(MenuController ct, ISoundButtonHover st) {
		this.soundController = st;
		this.controller = ct;
		setLayout(null);
		setOpaque(false);

		// Tạo tiêu đề
		JLabel titleLabel1 = new JLabel("MONSTER SHOOT");
		JLabel titleLabel2 = new JLabel("GAME");

		try {
			Font marioFont = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/res/SuperMario256.ttf"));
			marioFont = marioFont.deriveFont(Font.BOLD, 36);
			titleLabel1.setFont(marioFont);
			titleLabel2.setFont(marioFont);
		} catch (Exception e) {
			e.printStackTrace();
		}

		titleLabel1.setForeground(Color.WHITE);
		titleLabel2.setForeground(Color.WHITE);
		titleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);

		titleLabel1.setBounds(200, 80 + 40, 400, 60);
		titleLabel2.setBounds(200, 80, 400, 40);

		this.add(titleLabel1);
		this.add(titleLabel2);

		// Tạo các nút chức năng
		btnStart = new JButton("GAME START");
		btnSettings = new JButton("SETTINGS");
		btnGuide = new JButton("INTRODUCE");

		JButton[] buttons = { btnStart, btnSettings, btnGuide };
		for (JButton btn : buttons) {
			btn.setBorderPainted(false);
			btn.setContentAreaFilled(false);
			btn.setFocusPainted(false);
			btn.setOpaque(false);
			btn.setForeground(Color.WHITE);

			try {
				Font marioFont = Font.createFont(Font.TRUETYPE_FONT,
						getClass().getResourceAsStream("/res/SuperMario256.ttf"));
				marioFont = marioFont.deriveFont(Font.BOLD, 20);
				btn.setFont(marioFont);
			} catch (Exception e) {
				e.printStackTrace();
			}

			btn.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					btn.setForeground(Color.YELLOW);
					soundController.playHoverSound();
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent evt) {
					btn.setForeground(Color.WHITE);
				}
			});
		}

		// Định vị trí các nút
		btnStart.setBounds(250, 300, 300, 40);
		btnSettings.setBounds(250, 360, 300, 40);
		btnGuide.setBounds(250, 420, 300, 40);

		this.add(btnStart);
		this.add(btnSettings);
		this.add(btnGuide);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		ImageIcon bgImage = new ImageIcon(getClass().getResource("/res/background.jpg"));
		g2d.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	public JButton getBtnSettings() {
		return btnSettings;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnGuide() {
		return btnGuide;
	}

}
