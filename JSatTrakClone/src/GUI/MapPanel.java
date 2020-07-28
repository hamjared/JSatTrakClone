package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import objects.Position;
import objects.Satellite;

public class MapPanel extends JPanel {

	private static final String mapImageFile = "resource/Mercator_projection_Square.jpg";
	private static final int mapWidth = 800;
	private static final int mapHeight = 800;

	public MapPanel() {
		super();
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(960, 800));
		

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(getImage(), 0, 0, mapWidth, mapHeight, null);

	}

	public void paint(Graphics g) {
		Position denver = new Position(39.7392, -104.9903);
		g.drawImage(getImage(), 0, 0, mapWidth, mapHeight, null);
		drawCenteredEllipse(denver.getMercatorLongitude(mapWidth, mapHeight),
				denver.getMercatorLatitude(mapWidth, mapHeight), 20, 20, (Graphics2D) g);
		Satellite sat = (Satellite) GUI.satellites.getSelectedItem();
		if(sat == null) {
			System.out.println("Satellite  is null");
			return;
		}
		System.out.println("ISS Ground Track:");
		System.out.println(sat.groundTrack(LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
		drawGroundTrack(sat.groundTrack(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),g );
		

	}

	private Image getImage() {
		Image image = null;
		try {
			image = javax.imageio.ImageIO.read(new File(mapImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void drawGroundTrack(List<Position> positions, Graphics g) {

		for( int i = 1; i < positions.size(); i ++) {
			g.drawLine(positions.get(i-1).getMercatorLongitude(mapWidth, mapHeight),
					positions.get(i-1).getMercatorLatitude(mapWidth, mapHeight), 
					positions.get(i).getMercatorLongitude(mapWidth, mapHeight),
					positions.get(i).getMercatorLatitude(mapWidth, mapHeight));
		}
	}

	private void drawCenteredEllipse(int x, int y, int width, int height, Graphics2D g) {

		int centeredX = x - width / 2;
		int centeredY = y - height / 2;

		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);

		g.draw(ellipse);

	}

	private List<Position> readGroundTrack() {
		String filename = "resource/ISS.csv";
		List<Position> positions = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int lineNumber = 0;
			String line;
			while ((line = br.readLine()) != null) {
				if(lineNumber == 0) {
					lineNumber++;
					continue;
				}
				lineNumber++;
				String[] cardInfo = line.split(",");
				if (cardInfo.length == 0) {
					br.close();

				}
				
				positions.add(new Position(Double.parseDouble(cardInfo[1]), Double.parseDouble(cardInfo[2])));
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return positions;
	}

}
