package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.IGameController;
import model.Enemy;
import model.GameObserver;
import model.IGameModel;
import model.strategypattern.Aircraft;
import model.strategypattern.Laser;

public class GamePanel extends JPanel implements GameObserver {
	private IGameModel model;
	private IGameController controller;

	public GamePanel(IGameModel model, IGameController controller) {
		this.model = model;
		this.controller = controller;

		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));

		this.setFocusable(true);
		this.requestFocusInWindow();

		this.model.addObserver(this);

		// Lắng nghe sự kiện phím
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Phím được nhấn: " + KeyEvent.getKeyText(e.getKeyCode())); 
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					controller.outGame();
				}
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					controller.firePrimary();
				} else if (SwingUtilities.isRightMouseButton(e)) {
					controller.fireSecondary();
				}
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				controller.movePlayer(e.getPoint());
			}
		});

		// Bắt đầu game loop
		new javax.swing.Timer(16, e -> model.gameTick()).start();
	}

	@Override
	public void update() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ImageIcon bgImage = new ImageIcon(getClass().getResource("/res/background.jpg"));
		g2d.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
		Aircraft player = model.getPlayer();
		if (player != null && player.getPosition() != null) {
			player.draw(g2d, this);
		}

		g2d.setColor(Color.RED);
		for (Laser laser : model.getLasers1()) {
			g2d.fillRect(laser.getX(), laser.getY(), 4, 15);
		}

		g2d.setColor(Color.GREEN);
		for (Laser laser : model.getLasers2()) {
			g2d.fillRect(laser.getX(), laser.getY(), 4, 15);
		}

		g2d.setColor(Color.BLUE);
		for (Laser bullet : model.getEnemyBullets()) {
			g2d.fillRect(bullet.getX(), bullet.getY(), 4, 15);
		}

		for (Enemy enemy : model.getEnemies()) {
			g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), 40, 40, this);
		}
	}

}
