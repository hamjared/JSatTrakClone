package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private JButton satelliteButton;
	private JButton groundStationButton;
	private JButton orbitTransferButton;

	private JComboBox comboBox;


	public ButtonPanel() {
		super();
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1920, 100));
		satelliteButton = new JButton("Add Satellite");
		groundStationButton = new JButton("Add Ground Station");
		orbitTransferButton = new JButton("Orbit Transfer");
		satelliteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				satelliteButtonPressed();

			}

		});

		groundStationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				groundStationButtonPressed();

			}

		});

		orbitTransferButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				orbitTansferButtonPressed();

			}

		});


		super.add(satelliteButton);
		super.add(groundStationButton);
		super.add(orbitTransferButton);
		

	
		comboBox = new JComboBox(GUI.satellites);


		super.add(comboBox);
	}

	protected void refreshButtonPressed() {
		
		
	}

	protected void orbitTansferButtonPressed() {
		OrbitTransferPanel orbitTransferPanel = new OrbitTransferPanel();

	}

	protected void groundStationButtonPressed() {
		GroundStationPane groundStationPane = new GroundStationPane();

	}

	public void satelliteButtonPressed() {
		System.out.println("Satellite Button Pressed");
		SatellitePanel satellitePanel = new SatellitePanel();
		
		
	}

}
