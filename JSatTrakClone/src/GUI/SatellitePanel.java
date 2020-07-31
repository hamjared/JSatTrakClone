package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import objects.Orbit;
import objects.Satellite;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SatellitePanel extends JFrame {

	private static final long serialVersionUID = 4074816864911541470L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_inclination;
	private JTextField textField_semiMajorAxis;
	private JTextField textField_logindtudeOfAscendingNode;
	private JTextField textField_argumentOfPeriapsis;
	private JTextField textField_eccentricity;

	public SatellitePanel() {
		setResizable(false);
		setTitle("Add Satellite");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{32, 29, 114, 37, 139, 0, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 20, 20, 44, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifyInputs()) {
					addSatellite();
				}
			}
		});
		
		JLabel lbl_name = new JLabel("Name");
		GridBagConstraints gbc_lbl_name = new GridBagConstraints();
		gbc_lbl_name.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_name.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_name.gridx = 1;
		gbc_lbl_name.gridy = 0;
		contentPane.add(lbl_name, gbc_lbl_name);
		
		textField_name = new JTextField();
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.fill = GridBagConstraints.BOTH;
		gbc_textField_name.insets = new Insets(0, 0, 5, 5);
		gbc_textField_name.gridx = 4;
		gbc_textField_name.gridy = 0;
		contentPane.add(textField_name, gbc_textField_name);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Eccentricity");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_eccentricity = new JTextField();
		GridBagConstraints gbc_textField_eccentricity = new GridBagConstraints();
		gbc_textField_eccentricity.fill = GridBagConstraints.BOTH;
		gbc_textField_eccentricity.insets = new Insets(0, 0, 5, 5);
		gbc_textField_eccentricity.gridx = 4;
		gbc_textField_eccentricity.gridy = 1;
		contentPane.add(textField_eccentricity, gbc_textField_eccentricity);
		textField_eccentricity.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Inclination");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_inclination = new JTextField();
		GridBagConstraints gbc_textField_inclination = new GridBagConstraints();
		gbc_textField_inclination.fill = GridBagConstraints.BOTH;
		gbc_textField_inclination.insets = new Insets(0, 0, 5, 5);
		gbc_textField_inclination.gridx = 4;
		gbc_textField_inclination.gridy = 2;
		contentPane.add(textField_inclination, gbc_textField_inclination);
		textField_inclination.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Semi Major Axis");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_semiMajorAxis = new JTextField();
		GridBagConstraints gbc_textField_semiMajorAxis = new GridBagConstraints();
		gbc_textField_semiMajorAxis.fill = GridBagConstraints.BOTH;
		gbc_textField_semiMajorAxis.insets = new Insets(0, 0, 5, 5);
		gbc_textField_semiMajorAxis.gridx = 4;
		gbc_textField_semiMajorAxis.gridy = 3;
		contentPane.add(textField_semiMajorAxis, gbc_textField_semiMajorAxis);
		textField_semiMajorAxis.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Longitude of Ascending Node");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_logindtudeOfAscendingNode = new JTextField();
		GridBagConstraints gbc_textField_logindtudeOfAscendingNode = new GridBagConstraints();
		gbc_textField_logindtudeOfAscendingNode.fill = GridBagConstraints.BOTH;
		gbc_textField_logindtudeOfAscendingNode.insets = new Insets(0, 0, 5, 5);
		gbc_textField_logindtudeOfAscendingNode.gridx = 4;
		gbc_textField_logindtudeOfAscendingNode.gridy = 4;
		contentPane.add(textField_logindtudeOfAscendingNode, gbc_textField_logindtudeOfAscendingNode);
		textField_logindtudeOfAscendingNode.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Argument of Periapsis");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textField_argumentOfPeriapsis = new JTextField();
		GridBagConstraints gbc_textField_argumentOfPeriapsis = new GridBagConstraints();
		gbc_textField_argumentOfPeriapsis.fill = GridBagConstraints.BOTH;
		gbc_textField_argumentOfPeriapsis.insets = new Insets(0, 0, 5, 5);
		gbc_textField_argumentOfPeriapsis.gridx = 4;
		gbc_textField_argumentOfPeriapsis.gridy = 5;
		contentPane.add(textField_argumentOfPeriapsis, gbc_textField_argumentOfPeriapsis);
		textField_argumentOfPeriapsis.setColumns(10);
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.fill = GridBagConstraints.BOTH;
		gbc_addButton.insets = new Insets(0, 0, 0, 5);
		gbc_addButton.gridx = 2;
		gbc_addButton.gridy = 7;
		contentPane.add(addButton, gbc_addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
		gbc_cancelButton.anchor = GridBagConstraints.LINE_START;
		gbc_cancelButton.fill = GridBagConstraints.BOTH;
		gbc_cancelButton.gridx = 4;
		gbc_cancelButton.gridy = 7;
		contentPane.add(cancelButton, gbc_cancelButton);
		setVisible(true);
	}

	protected boolean verifyInputs() {
		return true;
	}

	protected void addSatellite() {
		Orbit orbit = new Orbit(
				Double.parseDouble(this.textField_eccentricity.getText()),
				Double.parseDouble(this.textField_semiMajorAxis.getText()),
				Double.parseDouble(this.textField_inclination.getText()),
				Double.parseDouble(this.textField_logindtudeOfAscendingNode.getText()),
				Double.parseDouble(this.textField_argumentOfPeriapsis.getText())
				);
				
		GUI.satellites.addElement(
				new Satellite(textField_name.getText(), orbit )
				);
		
		dispose();
	}
}