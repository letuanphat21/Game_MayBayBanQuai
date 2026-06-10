package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ISoundButtonHover;
import utils.Music;

public class IntroducePanel extends JPanel {
	private JButton backButton;
	private ISoundButtonHover soundController;

	public IntroducePanel(ISoundButtonHover is) {
		this.soundController = is;
		setLayout(null);
		setOpaque(false);
		backButton = new JButton("Back");
		backButton.setFont(new Font("Arial", Font.BOLD, 50));
		backButton.setBackground(new Color(200, 50, 50));
		backButton.setBounds(200, 300, 200, 50);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setOpaque(false);
		backButton.setForeground(Color.WHITE);

		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				backButton.setForeground(Color.YELLOW);
				soundController.playHoverSound();
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				backButton.setForeground(Color.WHITE);
			}
		});
		this.add(backButton);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Vẽ background
		ImageIcon bgImage = new ImageIcon(getClass().getResource("/res/background.jpg"));
		g2d.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);

		// Vẽ chữ lên background
		g2d.setFont(new Font("Arial", Font.BOLD, 24));
		g2d.setColor(Color.WHITE); // Chữ màu trắng để nổi bật trên nền
		g2d.drawString("Giới Thiệu Game Bắn Quái Vật", 50, 50);

		g2d.setFont(new Font("Arial", Font.PLAIN, 16));
		int startY = 100; // Vị trí dòng đầu tiên
		int lineSpacing = 25; // Khoảng cách giữa các dòng

		String[] lines = { "Game Bắn quái vật là trò chơi hành động vui nhộn, nơi bạn sẽ điều khiển phi thuyền",
				"và tiêu diệt các con quái xâm lược Trái Đất.", "", "- Điều khiển bằng chuột.",
				"- Nhấn chuột phải hoặc trái để bắn.", "- Tiêu diệt quái vật và né tránh đạn để giành chiến thắng!", "",
				"Hãy sẵn sàng bảo vệ hành tinh khỏi đội quân quái vật xâm lược!" };

		for (String line : lines) {
			g2d.drawString(line, 50, startY);
			startY += lineSpacing;
		}
	}

	public JButton getBackButton() {
		return backButton;
	}

}
