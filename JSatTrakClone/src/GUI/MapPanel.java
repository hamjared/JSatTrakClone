package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import objects.GroundStation;
import objects.Position;
import objects.Satellite;

public class MapPanel extends JPanel {

	private static final String mapImageFile = "images/Mercator_projection_Square.jpg";
	private static final int mapWidth = 800;
	private static final int mapHeight = 800;
	private static final long serialVersionUID = 4482363922601778730L;
	private Image map = null;

	public MapPanel() {
		super();
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(960, 800));
		this.setLayout(new FlowLayout());
		map = getImage();
	}
	
	@Override
	public void paint(Graphics g) {
		try {
			g.drawImage(map, 0, 0, mapWidth, mapHeight, this);
			plotGroundStations(g);
			Satellite sat = (Satellite) GUI.satellites.getSelectedItem();
			if(sat != null) {
				System.out.println("ISS Ground Track:");
				List<Position> groundTrack = sat.groundTrack(LocalDateTime.now().minusMinutes(0), LocalDateTime.now().plusMinutes(24*60));
				drawGroundTrack(groundTrack, (Graphics2D)g);
			}
		} finally {
			g.dispose();
		}
	}
	
	private void plotGroundStations(Graphics g) {
		GroundStation gs = (GroundStation) GUI.groundStations.getSelectedItem();
		Position gsPos = gs.getPosition();
		drawCenteredEllipse(gsPos.getMercatorLongitude(mapWidth, mapHeight),
		gsPos.getMercatorLatitude(mapWidth, mapHeight), 10, 10, true, (Graphics2D) g);
	}

	private Image getImage() {
		Image image = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream(mapImageFile);
			image = javax.imageio.ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	private void drawGroundTrack(List<Position> positions, Graphics2D g) {

		for( int i = 1; i < positions.size(); i ++) {
			g.drawLine(positions.get(i-1).getMercatorLongitude(mapWidth, mapHeight),
					positions.get(i-1).getMercatorLatitude(mapWidth, mapHeight), 
					positions.get(i).getMercatorLongitude(mapWidth, mapHeight),
					positions.get(i).getMercatorLatitude(mapWidth, mapHeight));
		}
	}

	private void drawCenteredEllipse(int x, int y, int width, int height, boolean filled, Graphics2D g) {

		int centeredX = x - width / 2;
		int centeredY = y - height / 2;

		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);
		
		if(filled) {
			Color prevColor = g.getColor();
			g.setPaint(Color.RED);
			g.fill(ellipse);
			g.setPaint(prevColor);
		}
		else {
			g.draw(ellipse);

		}		
	}

	@SuppressWarnings("unused")
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