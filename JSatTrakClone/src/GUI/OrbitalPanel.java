package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.time.LocalDateTime;


import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import objects.GroundStation;
import objects.Orbit;
import objects.Satellite;
import objects.SatelliteAnimate;
import javax.swing.JButton;

public class OrbitalPanel extends JPanel {
	
	private static final long serialVersionUID = 5552924959315298327L;
	public static final double METERS_TO_PIXELS = 0.000005;
	private static final double EARTH_RADIUS = 6.3781e6;
	private SatelliteAnimate satAnimate;
	private GroundStationAnimator gsAnimator;
	

	/**
	 * Create the panel.
	 */
	public OrbitalPanel() {
		super();
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(960, 800));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satAnimate.reset();
				gsAnimator.reset();
			}
		});
		btnNewButton.setBounds(10, 11, 133, 23);
		add(btnNewButton);
		satAnimate = new SatelliteAnimate( (Satellite) GUI.satellites.getSelectedItem(),  960, 800);
		gsAnimator = new GroundStationAnimator((GroundStation) GUI.groundStations.getSelectedItem(), 960, 800);
		ActionListener actList = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				
			}
			
		};
		
		Timer timer = new Timer(200, actList);
		timer.start();
		
		

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
		
		GroundStation gs = (GroundStation) GUI.groundStations.getSelectedItem();
		
		
	
		int fociScaled = (int) (this.calcEllipseFoci(orbit.getSemiMajorAxis(), orbit.getEccentricity()) * METERS_TO_PIXELS);

		this.drawScaledCenteredEllipse(this.getWidth()/2, this.getHeight()/2 - fociScaled, 
				orbit.getSemiMajorAxis(), orbit.getEccentricity(), false, null, g2);

		
		

		
		this.drawScaledCenteredEllipse(this.getWidth()/2,this.getHeight()/2  , 
				EARTH_RADIUS, 0 , true, Color.BLUE, g2);
		
		satAnimate.setSatellite(satellite);
		satAnimate.setPanelHeight(this.getHeight());
		satAnimate.setPanelWidth(this.getWidth());
		
		gsAnimator.setGs(gs);
		gsAnimator.setPanelHeight(this.getHeight());
		gsAnimator.setPanelWidth(this.getWidth());
		
		Ellipse2D satEllipse = satAnimate.drawSatellite();
		g2.draw(satEllipse);
		
		Ellipse2D gsEllipse = gsAnimator.drawGroundStation();
		Color prevColor = g2.getColor();
		g2.setColor(Color.RED);
		g2.fill(gsEllipse);
		g2.setColor(prevColor);
		
		for(Orbit o: GUI.transferOrbits) {
			this.drawScaledCenteredEllipse(this.getWidth()/2, 
					this.getHeight()/2 - (int) (this.calcEllipseFoci(o.getSemiMajorAxis(), o.getEccentricity()) * METERS_TO_PIXELS), 
					o.getSemiMajorAxis(), o.getEccentricity(), false, null, g2);
		}
		
		
		


	}
	


	private void drawCenteredEllipse(int x, int y, int width, int height, boolean filled, Color color, Graphics2D g) {
		
		int centeredX = x - width/2;
		int centeredY = y - height/2;
		
		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);
		
		if(filled) {
			Color prevColor = g.getColor();
			g.setPaint(color);
			g.fill(ellipse);
			g.setPaint(prevColor);
		}
		else {
			g.draw(ellipse);
		}
		
				
	}
	
	private void drawScaledCenteredEllipse(int x, int y, double semiMajorAxis, double eccentricity, boolean filled, Color color, Graphics2D g) {
		double e = eccentricity;
		double height = semiMajorAxis * 2 * METERS_TO_PIXELS;

		double width = 2.0* semiMajorAxis * Math.sqrt(1-e*e) * METERS_TO_PIXELS;

		
		drawCenteredEllipse(x, y, (int)width, (int)height, filled, color, g);
		
	}
	
	private double calcEllipseFoci(double semiMajorAxis, double eccentricty) {
		
		return semiMajorAxis*eccentricty;
	}
}