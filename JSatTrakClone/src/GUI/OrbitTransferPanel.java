package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.Orbit;
import objects.OrbitTransfer;
import objects.Satellite;
import objects.SatelliteAnimate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class OrbitTransferPanel extends JFrame {

	private static final long serialVersionUID = 4891662990030018097L;
	private JPanel contentPane;
	private JTextField textField_inclination;
	private JTextField textField_semiMajorAxis;
	private JTextField textField_logindtudeOfAscendingNode;
	private JTextField textField_argumentOfPeriapsis;
	private JTextField textField_eccentricity;

	/**
	 * Create the frame.
	 */
	public OrbitTransferPanel() {
		setResizable(false);
		setTitle("Orbit Transfer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{31, 153, 37, 139, 0};
		gbl_contentPane.rowHeights = new int[]{22, 20, 20, 20, 20, 20, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Satellite");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox<Satellite> comboBox = new JComboBox<objects.Satellite>(GUI.satellites);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 0;
		contentPane.add(comboBox, gbc_comboBox);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeOrbit();
			}
		});
		
		JLabel lblNewLabel1 = new JLabel("Eccentricity");
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.gridx = 1;
		gbc_lblNewLabel1.gridy = 1;
		contentPane.add(lblNewLabel1, gbc_lblNewLabel1);
		
		textField_eccentricity = new JTextField();
		GridBagConstraints gbc_textField_eccentricity = new GridBagConstraints();
		gbc_textField_eccentricity.anchor = GridBagConstraints.NORTH;
		gbc_textField_eccentricity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_eccentricity.insets = new Insets(0, 0, 5, 0);
		gbc_textField_eccentricity.gridx = 3;
		gbc_textField_eccentricity.gridy = 1;
		contentPane.add(textField_eccentricity, gbc_textField_eccentricity);
		textField_eccentricity.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Inclination");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_inclination = new JTextField();
		GridBagConstraints gbc_textField_inclination = new GridBagConstraints();
		gbc_textField_inclination.anchor = GridBagConstraints.NORTH;
		gbc_textField_inclination.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_inclination.insets = new Insets(0, 0, 5, 0);
		gbc_textField_inclination.gridx = 3;
		gbc_textField_inclination.gridy = 2;
		contentPane.add(textField_inclination, gbc_textField_inclination);
		textField_inclination.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Semi Major Axis");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_semiMajorAxis = new JTextField();
		GridBagConstraints gbc_textField_semiMajorAxis = new GridBagConstraints();
		gbc_textField_semiMajorAxis.anchor = GridBagConstraints.NORTH;
		gbc_textField_semiMajorAxis.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_semiMajorAxis.insets = new Insets(0, 0, 5, 0);
		gbc_textField_semiMajorAxis.gridx = 3;
		gbc_textField_semiMajorAxis.gridy = 3;
		contentPane.add(textField_semiMajorAxis, gbc_textField_semiMajorAxis);
		textField_semiMajorAxis.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Longitude of Ascending Node");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_logindtudeOfAscendingNode = new JTextField();
		GridBagConstraints gbc_textField_logindtudeOfAscendingNode = new GridBagConstraints();
		gbc_textField_logindtudeOfAscendingNode.anchor = GridBagConstraints.NORTH;
		gbc_textField_logindtudeOfAscendingNode.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_logindtudeOfAscendingNode.insets = new Insets(0, 0, 5, 0);
		gbc_textField_logindtudeOfAscendingNode.gridx = 3;
		gbc_textField_logindtudeOfAscendingNode.gridy = 4;
		contentPane.add(textField_logindtudeOfAscendingNode, gbc_textField_logindtudeOfAscendingNode);
		textField_logindtudeOfAscendingNode.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Argument of Periapsis");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_argumentOfPeriapsis = new JTextField();
		GridBagConstraints gbc_textField_argumentOfPeriapsis = new GridBagConstraints();
		gbc_textField_argumentOfPeriapsis.anchor = GridBagConstraints.NORTH;
		gbc_textField_argumentOfPeriapsis.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_argumentOfPeriapsis.insets = new Insets(0, 0, 5, 0);
		gbc_textField_argumentOfPeriapsis.gridx = 3;
		gbc_textField_argumentOfPeriapsis.gridy = 5;
		contentPane.add(textField_argumentOfPeriapsis, gbc_textField_argumentOfPeriapsis);
		textField_argumentOfPeriapsis.setColumns(10);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.anchor = GridBagConstraints.NORTH;
		gbc_addButton.insets = new Insets(0, 0, 0, 5);
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 6;
		contentPane.add(addButton, gbc_addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.anchor = GridBagConstraints.NORTH;
		gbc_cancelButton.gridx = 3;
		gbc_cancelButton.gridy = 6;
		contentPane.add(cancelButton, gbc_cancelButton);
		
		setVisible(true);
	}

	protected void changeOrbit() {
		Orbit orbit = new Orbit(
				Double.parseDouble(this.textField_eccentricity.getText()),
				Double.parseDouble(this.textField_semiMajorAxis.getText()),
				Double.parseDouble(this.textField_inclination.getText()),
				Double.parseDouble(this.textField_logindtudeOfAscendingNode.getText()),
				Double.parseDouble(this.textField_argumentOfPeriapsis.getText())
				);
		
		Satellite sat = (Satellite) GUI.satellites.getSelectedItem();
		Orbit transferOrbit = OrbitTransfer.hohmannTransfer(sat.getOrbit(), orbit);
		GUI.transferOrbits.add(sat.getOrbit());
		GUI.transferOrbits.add(transferOrbit);
		sat.setOrbit(orbit);
		SatelliteAnimate.orbitChanged = true;
		SatelliteAnimate.onTransferOrbit = true;

		dispose();		
	}
}