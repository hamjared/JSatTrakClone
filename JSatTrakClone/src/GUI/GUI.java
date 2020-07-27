package GUI;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

import objects.Satellite;

public class GUI {
	public static DefaultComboBoxModel<Satellite> satellites = new DefaultComboBoxModel<Satellite>();
	
	public GUI() {
		JFrame jframe = new JFrame();
		jframe.setSize(1920, 900);
		jframe.add(new ButtonPanel(), BorderLayout.NORTH);
		jframe.add(new MapPanel(), BorderLayout.WEST);
		jframe.add(new OrbitalPanel(), BorderLayout.EAST);
		
		jframe.setVisible(true);
	}
}
