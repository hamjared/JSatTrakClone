package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import objects.GroundStation;
import objects.Orbit;
import objects.Position;
import objects.Satellite;

public class GUI {
	public static DefaultComboBoxModel<Satellite> satellites = new DefaultComboBoxModel<Satellite>();
	public static DefaultComboBoxModel<GroundStation> groundStations = new DefaultComboBoxModel<GroundStation>();
	public static List<Orbit> transferOrbits = new ArrayList<Orbit>();
	
	public GUI() {
		satellites.addElement(new Satellite("GEO", new Orbit(0, 42.164e6, 30, 180, 0)));
		satellites.addElement(new Satellite("ISS", new Orbit(0, 6.79e6, 66.5, 225.16, 96.2)));
		satellites.addElement(new Satellite("Molniya", new Orbit(0.74, 26.6e6, 63.4, 0, 270)));
		groundStations.addElement(new GroundStation("Cape Canveral", new Position(28.45, -80)));
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(1920, 900);
		jframe.add(new ButtonPanel(), BorderLayout.NORTH);
		jframe.add(new MapPanel(), BorderLayout.WEST);
		jframe.add(new OrbitalPanel(), BorderLayout.EAST);
		jframe.setVisible(true);
	}
}
