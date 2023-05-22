package editor;

import static java.awt.BorderLayout.PAGE_END;
import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_Z;


import commands.ColorCommand;
import commands.MoveCommand;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import shapes.Shape;

public class Canvas extends java.awt.Canvas {
	private Editor editor;
	private JFrame frame;
	private static final int PADDING = 10;

	public Canvas(Editor editor) {
		this.editor = editor;
		createFrame();
		attachKeyboardListeners();
		attachMouseListeners();
		refresh();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel contentPanel = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		frame.setContentPane(contentPanel);

		contentPanel.add(new JLabel("Select and drag to move."), PAGE_END);
		contentPanel.add(new JLabel("Right click to change color."), PAGE_END);
		contentPanel.add(new JLabel("Undo: Ctrl+Z, Redo: Ctrl+R"), PAGE_END);
		contentPanel.add(this);
		frame.setVisible(true);
		contentPanel.setBackground(Color.LIGHT_GRAY);
	}

	private void attachKeyboardListeners() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
					switch (e.getKeyCode()) {
						case VK_Z:
							editor.undo();
							break;
						case VK_R:
							editor.redo();
							break;
					}
				}
			}
		});
	}

	private void attachMouseListeners() {
		MouseAdapter colorizer = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON3) {
					return;
				}
				Shape target = editor.getShapes().getChildAt(e.getX(), e.getY());
				if (target != null) {
					editor.execute(new ColorCommand(editor, new Color(
							(int) (Math.random() * 0x1000000))));
					repaint();
				}
			}
		};

		addMouseListener(colorizer);

		MouseAdapter selector = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1) {
					return;
				}

				Shape target = editor.getShapes().getChildAt(e.getX(), e.getY());
				boolean ctrl = (e.getModifiers() & CTRL_MASK) == CTRL_MASK;

				if (target == null) {
					if (!!ctrl) {
						editor.getShapes().unSelect();
					}
				} else {
					if (ctrl) {
						if (target.isSelected()) {
							target.unSelect();
						} else {
							target.select();
						}
					} else {
						if (!target.isSelected()) {
							editor.getShapes().unSelect();
						}
						target.select();
					}
				}
				repaint();
			}
		};

		addMouseListener(selector);

		MouseAdapter dragger = new MouseAdapter() {
			MoveCommand moveCommand;

			@Override
			public void mouseDragged(MouseEvent e) {
				if ((e.getModifiers() & BUTTON1_DOWN_MASK) != BUTTON1_DOWN_MASK) {
					return;
				}

				if (moveCommand == null) {
					moveCommand = new MoveCommand(editor);
					moveCommand.start(e.getX(), e.getY());
				}
				moveCommand.move(e.getX(), e.getY());
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1 || moveCommand == null) {
					return;
				}

				moveCommand.stop(e.getX(), e.getY());
				editor.execute(moveCommand);
				this.moveCommand = null;
				repaint();
			}
		};

		addMouseListener(dragger);
		addMouseMotionListener(dragger);
	}

	@Override
	public int getWidth() {
		return editor.getShapes().getX() + editor.getShapes().getWidth() + PADDING;
	}

	@Override
	public int getHeight() {
		return editor.getShapes().getY() + editor.getShapes().getHeight() + PADDING;
	}

	void refresh() {
		this.setSize(getWidth(), getWidth());
		frame.pack();
	}

	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics graphics) {
		BufferedImage buffer =
				new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D ig2 = buffer.createGraphics();
		ig2.setBackground(Color.WHITE);
		ig2.clearRect(0, 0, this.getWidth(), getHeight());

		editor.getShapes().paint(buffer.getGraphics());

		graphics.drawImage(buffer, 0, 0, null);
	}
}
