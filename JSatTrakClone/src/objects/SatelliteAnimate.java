package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.time.LocalDateTime;
import java.time.Month;

import javax.swing.JPanel;
import javax.swing.Timer;

import GUI.OrbitalPanel;

public class SatelliteAnimate {

	private Satellite sat;
	private int panelWidth;
	private int panelHeight;
	private LocalDateTime curTime;
	private LocalDateTime endTime;
	private LocalDateTime startTime;
	public static int timeStep = 777;
	public static boolean orbitChanged = false;
	public static boolean onTransferOrbit = false;

	public SatelliteAnimate(Satellite sat, int panelWidth, int panelHeight) {
		System.out.println("Construct called");
		System.out.print(panelWidth);
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		startTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 2);
		curTime = startTime;
		endTime = curTime.plusSeconds((long) sat.getOrbit().calcOrbitalPeriod());
		this.sat = sat;

	}

	public Ellipse2D drawSatellite() {

		if (sat == null) {
			return null;
		}
		if (curTime.isAfter(endTime)) {
			curTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 2);
		}


		Orbit orbit = sat.getOrbit();

		if (onTransferOrbit) {
			endTime = startTime.plusSeconds((long) GUI.GUI.transferOrbits.get(1).calcOrbitalPeriod());
			if (GUI.GUI.transferOrbits.size() > 0) {
				System.out.println(curTime);
				System.out.println(endTime);
				Orbit tranOrbit = GUI.GUI.transferOrbits.get(1);
				double tranTrueAnamoly = tranOrbit.calculateTrueAnamoly(curTime);

				if (tranTrueAnamoly < (Math.PI ) && tranTrueAnamoly > 0) {

					double tranA = tranOrbit.getSemiMajorAxis();
					double tranE = tranOrbit.getEccentricity();

					double r = tranA * (1 - tranE * tranE) / (1 + tranE * Math.cos(tranTrueAnamoly));

					double x = r * Math.sin(tranTrueAnamoly) * OrbitalPanel.METERS_TO_PIXELS + panelWidth / 2;
					double y = r * Math.cos(tranTrueAnamoly) * OrbitalPanel.METERS_TO_PIXELS + panelHeight / 2;

					Ellipse2D ellipse = drawCenteredEllipse((int) x, (int) y, 10, 10, true, Color.GREEN);

					curTime = curTime.plusSeconds(timeStep);

					return ellipse;

				} else {
					onTransferOrbit = false;
					curTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 2).plusSeconds((long) (orbit.calcOrbitalPeriod()/2));
					GUI.GUI.transferOrbits.clear();
					endTime = startTime.plusSeconds((long) orbit.calcOrbitalPeriod());
				}
			}

		}
		
		

		double e = orbit.getEccentricity();
		double a = orbit.getSemiMajorAxis();
		double trueAnamoly = orbit.calculateTrueAnamoly(curTime);

		double r = a * (1 - e * e) / (1 + e * Math.cos(trueAnamoly));

		double x = r * Math.sin(trueAnamoly + orbit.getArgumentOfPeriapsisRadians()) * OrbitalPanel.METERS_TO_PIXELS + panelWidth / 2 ;
		double y = r * Math.cos(trueAnamoly + orbit.getArgumentOfPeriapsisRadians()) * OrbitalPanel.METERS_TO_PIXELS + panelHeight / 2;

		Ellipse2D ellipse = drawCenteredEllipse((int) x, (int) y, 10, 10, true, Color.GREEN);

		curTime = curTime.plusSeconds(timeStep);

		return ellipse;
	}

	private Ellipse2D drawCenteredEllipse(int x, int y, int width, int height, boolean filled, Color color) {

		int centeredX = x - width / 2;
		int centeredY = y - height / 2;

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
		if (this.sat != satellite || orbitChanged) {
			curTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 2);
			endTime = curTime.plusSeconds((long) satellite.getOrbit().calcOrbitalPeriod());
			orbitChanged = false;

		}
		this.sat = satellite;

	}
	
	public void reset() {
		curTime = startTime;
	}

}
