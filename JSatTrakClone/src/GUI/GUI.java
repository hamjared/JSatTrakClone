package GUI;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import objects.Orbit;
import objects.Satellite;

public class GUI {
	public static DefaultComboBoxModel<Satellite> satellites = new DefaultComboBoxModel<Satellite>();
	
	public GUI() {
		satellites.addElement(new Satellite("ISS", new Orbit(0, 6.79e6, 66.5, 225.16, 96.2)));
		JFrame jframe = new JFrame();
		jframe.setSize(1920, 900);
		jframe.add(new ButtonPanel(), BorderLayout.NORTH);
		jframe.add(new MapPanel(), BorderLayout.WEST);
		jframe.add(new OrbitalPanel(), BorderLayout.EAST);
		
		jframe.setVisible(true);
	}
}
