package view.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.RoundRectangle2D;
import java.awt.geom.RoundRectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.Frame;

public class CardPanel extends JPanel {

	private PlayingCard card;
	private Player player;
	private String[] text;
	private String[] suit;
	private int index;
	private Double[] boxes;
	private int cardType;
	private HashMap<Player, Double[]> playerBox;
	private HashMap<Player, String[]> playerText;
	private HashMap<Player, String[]> playerSuit;
	private HashMap<Player, Integer> playerNum;

	public CardPanel() {
		this.player = null;
		this.playerText = new HashMap<Player, String[]>();
		this.playerSuit = new HashMap<Player, String[]>();
		this.playerNum = new HashMap<Player, Integer>();
		this.playerBox = new HashMap<Player, Double[]>();
		this.boxes = null;
		this.card = null;
		this.index = 0;
		this.cardType = 0;
		this.suit = new String[6];
		this.text = new String[6];

	}

	@Override
	public void paintComponent(Graphics g) {
		Double[] b = new Double[6];
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (this.player != null) {
			if (this.playerSuit.get(this.player) != null) {

				int width = (int) (getWidth() / 6.5);
				int height = (int) (width * 1.5);

				for (int i = 0; i < b.length; i++) {
					int x = 0;
					if (i == 0) {
						x = 10;
					} else if (i == 1) {
						x = width + 20;
					} else if (i == 2) {
						x = width * 2 + 30;
					} else if (i == 3) {
						x = width * 3 + 40;
					} else if (i == 4) {
						x = width * 4 + 50;
					} else if (i == 5) {
						x = width * 5 + 60;
					}
					b[i] = new RoundRectangle2D.Double(x, 50, width, height, 20, 20);
				}

				BufferedImage img;
				try {
					int z = 0;
					String[] formalSuit = this.playerSuit.get(this.player);
					String[] formalText = this.playerText.get(this.player);
					Double[] formalBox = this.playerBox.get(this.player);
					while (z < this.playerNum.get(this.player)) {
						if (z == 0) {
							this.boxes[z] = b[z];
						} else if (z == 1) {
							this.boxes[z] = b[z];
						} else if (z == 2) {
							this.boxes[z] = b[z];
						} else if (z == 3) {
							this.boxes[z] = b[z];
						} else if (z == 4) {
							this.boxes[z] = b[z];
						} else if (z == 5) {
							this.boxes[z] = b[z];

						}

						if (this.cardType == 2 && z == this.boxes.length - 1) {
							g2.setColor(Color.LIGHT_GRAY);
						} else
							g2.setColor(Color.WHITE);

						g2.fill(this.boxes[z]);

						img = ImageIO.read(new File(formalSuit[z]));

						int preferX = (int) (this.boxes[z].getWidth() / 3);
						int preferY = (int) (this.boxes[z].getHeight() / 3);

						FontMetrics fm = g2.getFontMetrics();

						int x = (int) (this.boxes[z].x + ((this.boxes[z].width - fm.stringWidth(formalText[z])) / 10));
						int y = (int) (this.boxes[z].y + (((this.boxes[z].height - fm.getHeight()) / 10)));
						int x1 = (int) (this.boxes[z].x + (this.boxes[z].width - fm.stringWidth(formalText[z]))
								- ((this.boxes[z].width - fm.stringWidth(formalText[z])) / 10));
						int y1 = (int) (this.boxes[z].y + (this.boxes[z].height - fm.getHeight()));

						int xIm = (int) (this.boxes[z].x + ((this.boxes[z].width - preferX) / 2));
						int yIm = (int) (this.boxes[z].y + (((this.boxes[z].height - preferY) / 2)));

						g = this.resizeImage(img, preferX, preferY).getGraphics();
						g.drawImage(img, 0, 0, null);

						/* Draw the image, applying the filter */
						g2.drawImage(this.resizeImage(img, preferX, preferY), null, xIm, yIm);

						g2.setColor(Color.BLACK);
						g2.setFont(new Font("TimesRoman", Font.PLAIN, (int) b[z].getHeight() / 14));
						g2.drawString(formalText[z], x, y);
						g2.drawString(formalText[z], x1, y1);

						z++;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else {
			g2.dispose();
		}
	}

	public void updateCardPanel(Player player, PlayingCard card, int cardType) {
		this.player = player;
		this.cardType = cardType;
		this.card = card;
//		cardPosition();
		text();
		suit();
	}

	public void getPlayer(Player player) {
		this.player = player;
	}

	public void text() {
		for (int i = 0; i < this.index; i++) {
			if (this.card.getValue().equals(Value.EIGHT) && this.text[i] == null) {
				this.text[i] = "8";
			} else if (this.card.getValue().equals(Value.NINE) && this.text[i] == null) {
				this.text[i] = "9";
			} else if (this.card.getValue().equals(Value.TEN) && this.text[i] == null) {
				this.text[i] = "T";
			} else if (this.card.getValue().equals(Value.JACK) && this.text[i] == null) {
				this.text[i] = "T";
			} else if (this.card.getValue().equals(Value.QUEEN) && this.text[i] == null) {
				this.text[i] = "Q";
			} else if (this.card.getValue().equals(Value.KING) && this.text[i] == null) {
				this.text[i] = "K";
			} else if (this.card.getValue().equals(Value.ACE) && this.text[i] == null) {
				this.text[i] = "A";
			}
		}
		this.playerText.put(this.player, this.text);
	}

	public void suit() {
		for (int i = 0; i < this.index; i++) {
			if (this.card.getSuit().equals(Suit.SPADES) && this.suit[i] == null) {
				this.suit[i] = "images/circle_blue.png";
			} else if (this.card.getSuit().equals(Suit.CLUBS) && this.suit[i] == null) {
				this.suit[i] = "images/circle_green.png";
			} else if (this.card.getSuit().equals(Suit.DIAMONDS) && this.suit[i] == null) {
				this.suit[i] = "images/circle_red.png";
			} else if (this.card.getSuit().equals(Suit.HEARTS) && this.suit[i] == null) {
				this.suit[i] = "images/circle_yellow.png";
			}
		}
		this.playerSuit.put(this.player, this.suit);
	}

	public void addCard() {
		this.index++;
		this.boxes = new Double[this.index];
		this.playerNum.put(this.player, this.index);
	}

	public void reset(Player player, Frame frame) {
		this.index = 0;
		this.suit = new String[6];
		this.text = new String[6];
		this.boxes = new Double[6];
	}

	public void removePlayer(Player player) {
		this.playerSuit.remove(player);
		this.playerNum.remove(player);
		this.playerSuit.remove(player);
	}

	public BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
			throws IOException {
		BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		graphics2D.dispose();
		return resizedImage;
	}

}

//public void cardPosition() {
//int width = getWidth() / 7;
//int height = getHeight() - 10;
//
//Double box = new RoundRectangle2D.Double(10, 10, width, height, 20, 20);
//Double box1 = new RoundRectangle2D.Double(width + 20, 10, width, height, 20, 20);
//Double box2 = new RoundRectangle2D.Double(width * 2 + 30, 10, width, height, 20, 20);
//Double box3 = new RoundRectangle2D.Double(width * 3 + 40, 10, width, height, 20, 20);
//Double box4 = new RoundRectangle2D.Double(width * 4 + 50, 10, width, height, 20, 20);
//Double box5 = new RoundRectangle2D.Double(width * 5 + 60, 10, width, height, 20, 20);
//// && this.boxes[z] != null
//for (int z = 0; z < this.index; z++) {
//	if (z == 0 && this.boxes[z] == null) {
//		this.boxes[z] = box;
//	} else if (z == 1 && this.boxes[z] == null) {
//		this.boxes[z] = box1;
//	} else if (z == 2 && this.boxes[z] == null) {
//		this.boxes[z] = box2;
//	} else if (z == 3 && this.boxes[z] == null) {
//		this.boxes[z] = box3;
//	} else if (z == 4 && this.boxes[z] == null) {
//		this.boxes[z] = box4;
//	} else if (z == 5 && this.boxes[z] == null) {
//		this.boxes[z] = box5;
//
//	}
//
//}
//this.playerBox.put(this.player, this.boxes);
//
//}
