package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 3541175169325575021L;
	private JButton satelliteButton;
	private JButton groundStationButton;
	private JButton orbitTransferButton;
	private JButton refreshButton;
	private JComboBox<objects.Satellite> satelliteComboBox;
	private JComboBox<objects.GroundStation> gsComboBox;
	private OrbitTransferPanel orbitTransferPanel = null;
	private GroundStationPane groundStationPane = null;
	private SatellitePanel satellitePanel = null;

	public ButtonPanel() {
		super();
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1920, 100));
		satelliteButton = new JButton("Add Satellite");
		groundStationButton = new JButton("Add Ground Station");
		orbitTransferButton = new JButton("Orbit Transfer");
		refreshButton = new JButton("Refresh");
		
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
		
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refreshButtonPressed();
			}
		});
		
		add(satelliteButton);
		add(groundStationButton);
		add(orbitTransferButton);
		satelliteComboBox = new JComboBox<objects.Satellite>(GUI.satellites);
		add(satelliteComboBox);
		gsComboBox = new JComboBox<objects.GroundStation>(GUI.groundStations);
		add(gsComboBox);
		add(refreshButton);
	}

	protected void refreshButtonPressed() {
		super.revalidate();
		this.getParent().revalidate();
		this.getParent().repaint();
	}
	
	protected void orbitTansferButtonPressed() {
		if(orbitTransferPanel == null) {
			orbitTransferPanel = new OrbitTransferPanel();
			orbitTransferPanel.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					orbitTransferPanel = null;
				}
			});
		}
	}

	protected void groundStationButtonPressed() {
		if(groundStationPane == null) {
			groundStationPane = new GroundStationPane();
			groundStationPane.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					groundStationPane = null;
				}
			});
		}
	}

	protected void satelliteButtonPressed() {
		//System.out.println("Satellite Button Pressed");
		if(satellitePanel == null) {
			satellitePanel = new SatellitePanel();
			satellitePanel.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					satellitePanel = null;
				}
			});
		}
	}
}