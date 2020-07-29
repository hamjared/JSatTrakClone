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

		Graphics2D g2 = (Graphics2D) g;
		
		Satellite satellite = (Satellite) GUI.satellites.getSelectedItem();
		Orbit orbit = null;
		if(satellite != null) {
			orbit = satellite.getOrbit();
		}
		
		if(orbit == null) {
			return;
		}
		
		

		
		this.drawScaledCenteredEllipse(this.getWidth()/2, this.getHeight()/2, 
				orbit.getSemiMajorAxis(), orbit.getEccentricity(), g2);
		
		int fociScaled = (int) (this.calcEllipseFoci(orbit.getSemiMajorAxis(), orbit.getEccentricity()) * METERS_TO_PIXELS);
		
		this.drawScaledCenteredEllipse(this.getWidth()/2,this.getHeight()/2 + fociScaled , 
				EARTH_RADIUS, 0 , g2);
		
	

	
	}
	
	private void drawCenteredEllipse(int x, int y, int width, int height, Graphics2D g) {
		
		int centeredX = x - width/2;
		int centeredY = y - height/2;
		
		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);
		
		g.draw(ellipse);
		
	}
	
	private void drawScaledCenteredEllipse(int x, int y, double semiMajorAxis, double eccentricity, Graphics2D g) {
		double e = eccentricity;
		double height = semiMajorAxis * 2 * METERS_TO_PIXELS;
		System.out.println("Height: ");
		System.out.println(height);
		double width = 2.0* semiMajorAxis * Math.sqrt(1-e*e) * METERS_TO_PIXELS;
		System.out.println("Width:");
		System.out.println(width);
		
		drawCenteredEllipse(x, y, (int)width, (int)height, g);
		
	}
	
	private double calcEllipseFoci(double semiMajorAxis, double eccentricty) {
		
		return semiMajorAxis*eccentricty;
	}
	

	

	


}
