package GUI;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

import objects.AstroConstants;
import objects.GroundStation;
import objects.Satellite;
import objects.SatelliteAnimate;

public class GroundStationAnimator {
	GroundStation gs;
	private int panelWidth;
	private int panelHeight;
	private LocalDateTime curTime;
	private LocalDateTime endTime;
	private LocalDateTime startTime;

	public GroundStation getGs() {
		return gs;
	}

	public void setGs(GroundStation gs) {
		this.gs = gs;
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

	public int getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}

	public GroundStationAnimator(GroundStation gs, int panelWidth, int panelHeight) {
		System.out.println("Construct called");
		System.out.print(panelWidth);
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		startTime = LocalDateTime.of(2020, Month.JANUARY, 1, 0, 2);
		curTime = startTime;
		endTime = curTime.plusNanos((long) 86164.0905e9);
		this.gs = gs;

	}

	public Ellipse2D drawGroundStation() {
		Duration TimeDifference = Duration.between(curTime, startTime);
		double difference = TimeDifference.toHours();
		
		double x = panelWidth / 2 - Math.cos(Math.toRadians(gs.getPosition().getLongitude() + 15.04*difference ))
				* AstroConstants.EARTH_RADIUS * OrbitalPanel.METERS_TO_PIXELS;
		double y = panelHeight / 2 - Math.sin(Math.toRadians(gs.getPosition().getLongitude() + 15.04*difference))
				* AstroConstants.EARTH_RADIUS * OrbitalPanel.METERS_TO_PIXELS;
		
		curTime = curTime.plusSeconds(SatelliteAnimate.timeStep);

		return drawCenteredEllipse((int) x, (int) y, 10, 10);

	}

	private Ellipse2D drawCenteredEllipse(int x, int y, int width, int height) {

		int centeredX = x - width / 2;
		int centeredY = y - height / 2;

		Ellipse2D ellipse = new Ellipse2D.Double(centeredX, centeredY, width, height);

		return ellipse;

	}
	
	public void reset() {
		curTime = startTime;
	}

}
