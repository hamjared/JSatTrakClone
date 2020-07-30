package GUI;

import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import objects.GroundStation;
import objects.Orbit;
import objects.Position;
import objects.Satellite;

public class GUI {
	public static DefaultComboBoxModel<Satellite> satellites = new DefaultComboBoxModel<Satellite>();
	public static DefaultComboBoxModel<GroundStation> groundStations = new DefaultComboBoxModel<GroundStation>();
	
	public GUI() {
		satellites.addElement(new Satellite("ISS", new Orbit(0, 6.79e6, 66.5, 225.16, 96.2)));
		satellites.addElement(new Satellite("GEO", new Orbit(0, 42.164e6, 30, 180, 0)));
		groundStations.addElement(new GroundStation("Cape Canveral", new Position(28.45, -80.56)));
		JFrame jframe = new JFrame();
		jframe.setSize(1920, 900);
		jframe.add(new ButtonPanel(), BorderLayout.NORTH);
		jframe.add(new MapPanel(), BorderLayout.WEST);
		jframe.add(new OrbitalPanel(), BorderLayout.EAST);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
