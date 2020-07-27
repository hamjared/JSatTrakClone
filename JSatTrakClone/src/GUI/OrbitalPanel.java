package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import objects.Orbit;
import objects.Satellite;

import javax.swing.JComboBox;

public class OrbitalPanel extends JPanel {
	
	private static final double METERS_TO_PIXELS = 0.000005;
	private static final double EARTH_RADIUS = 6.3781e6;

	/**
	 * Create the panel.
	 */
	public OrbitalPanel() {
		super();
		setBackground(Color.RED);
		setPreferredSize(new Dimension(960, 800));
		setLayout(null);
		
		


	}
	
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("Paint!");
		Graphics2D g2 = (Graphics2D) g;
		
		Satellite satellite = (Satellite) GUI.satellites.getSelectedItem();
		Orbit orbit = null;
		if(satellite != null) {
			orbit = satellite.getOrbit();
		}
		
		if(orbit == null) {
			orbit = new Orbit(0.5, 40e6, 5, 5, 5);
		}
		
		
		System.out.println(orbit.getSemiMajorAxis()*2 * orbit.getEccentricity() * METERS_TO_PIXELS);
		System.out.println(orbit.getSemiMajorAxis()*2 * METERS_TO_PIXELS);
		
		Ellipse2D ellipse = new Ellipse2D.Double(480, 400, 
				orbit.getSemiMajorAxis()*2 * orbit.getEccentricity() * METERS_TO_PIXELS, 
				orbit.getSemiMajorAxis()*2 * METERS_TO_PIXELS
				);

		g2.translate((int)(-orbit.getSemiMajorAxis()*2 * orbit.getEccentricity() * METERS_TO_PIXELS / 2), 
				(int)(-orbit.getSemiMajorAxis()*2 * METERS_TO_PIXELS / 2));
		

		double focusYPosition = Math.sqrt(Math.pow(orbit.getSemiMajorAxis(), 2.0) - 
				Math.pow(orbit.getSemiMajorAxis()*orbit.getEccentricity(), 2.0));
		Ellipse2D earth = new Ellipse2D.Double(((int) (focusYPosition * METERS_TO_PIXELS)) + 400,
				 600,
				(int) (EARTH_RADIUS*METERS_TO_PIXELS),
				(int) (EARTH_RADIUS*METERS_TO_PIXELS) );
		
		g2.translate((int)(-EARTH_RADIUS*METERS_TO_PIXELS)/2, 
				(int)(-EARTH_RADIUS*METERS_TO_PIXELS)/2);
		
		System.out.println( ((int) (focusYPosition * METERS_TO_PIXELS)) + 400);
		
		g2.draw(ellipse);
		g2.draw(earth);

	
	}
	

	

	


}
