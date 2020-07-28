package GUI;

import java.awt.BorderLayout;
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
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
