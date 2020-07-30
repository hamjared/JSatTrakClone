package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.time.LocalDateTime;


import javax.swing.JPanel;
import javax.swing.Timer;

import GUI.OrbitalPanel;

public class SatelliteAnimate {
	
	private Satellite sat;
	private int panelWidth;
	private int panelHeight;
	private LocalDateTime curTime;
	private LocalDateTime endTime;
	public static boolean orbitChanged = false;

	
	public SatelliteAnimate(Satellite sat, int panelWidth, int panelHeight) {
		System.out.println("Construct called");
		System.out.print(panelWidth);
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		curTime = LocalDateTime.now();
		endTime = curTime.plusSeconds((long) sat.getOrbit().calcOrbitalPeriod());
		this.sat = sat;

		
	}
	


	public Ellipse2D drawSatellite() {

		if(sat == null) {
			return null;
		}
		if(curTime.isAfter(endTime)) {
			curTime = LocalDateTime.now();
		}
		int timeStep = 1000;
		
		
		
		Orbit orbit = sat.getOrbit();
		
		double e = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double trueAnamoly = orbit.calculateTrueAnamoly(curTime);
		
		
		
		double r = a * (1-e*e)/(1+ e * Math.cos(trueAnamoly));
		
		double x = r * Math.sin(trueAnamoly) * OrbitalPanel.METERS_TO_PIXELS + panelWidth/2;
		double y = r * Math.cos(trueAnamoly) * OrbitalPanel.METERS_TO_PIXELS + panelHeight/2 + orbit.getSemiMajorAxis()*orbit.getEccentricity() * OrbitalPanel.METERS_TO_PIXELS;

		
		Ellipse2D ellipse = drawCenteredEllipse((int)x, (int)y, 10, 10, true, Color.GREEN);

		
		curTime = curTime.plusSeconds(timeStep);
		
		return ellipse;
	}

	
	private Ellipse2D drawCenteredEllipse(int x, int y, int width, int height, boolean filled, Color color) {
		
		int centeredX = x - width/2;
		int centeredY = y - height/2;
		
		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);
		
		return ellipse;
		

		
				
	}
	
	public void setPanelHeight(int height) {
		this.panelHeight = height;
	}
	
	public void setPanelWidth(int width) {
		this.panelWidth = width;
	}
	
	public void setSatellite(Satellite satellite) {
		if(this.sat != satellite || orbitChanged) {
			curTime = LocalDateTime.now();
			endTime = curTime.plusSeconds((long) satellite.getOrbit().calcOrbitalPeriod());
			orbitChanged = false;
			
		}
		this.sat = satellite;
		
		
	}
	

}
