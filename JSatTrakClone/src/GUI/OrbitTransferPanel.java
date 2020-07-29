package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import objects.Satellite;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class OrbitTransferPanel extends JFrame {

	private static final long serialVersionUID = 4891662990030018097L;
	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_inclination;
	private JTextField textField_semiMajorAxis;
	private JTextField textField_logindtudeOfAscendingNode;
	private JTextField textField_argumentOfPeriapsis;
	private JTextField textField_eccentricity;


	/**
	 * Create the frame.
	 */
	public OrbitTransferPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Satellite> comboBox = new JComboBox<objects.Satellite>(GUI.satellites);
		comboBox.setBounds(262, 23, 125, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Satellite");
		lblNewLabel.setBounds(58, 27, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setBounds(58, 54, 46, 14);
		contentPane.add(lbl_name);
		
		JLabel lblNewLabel1 = new JLabel("Eccentricity");
		lblNewLabel1.setBounds(58, 79, 98, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel("Inclination");
		lblNewLabel_1.setBounds(58, 104, 98, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Semi Major Axis");
		lblNewLabel_2.setBounds(58, 129, 98, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Longitude of Ascending Node");
		lblNewLabel_3.setBounds(58, 154, 153, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Argument of Periapsis");
		lblNewLabel_4.setBounds(58, 179, 153, 14);
		contentPane.add(lblNewLabel_4);
		
		textField_name = new JTextField();
		textField_name.setBounds(262, 51, 125, 20);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_inclination = new JTextField();
		textField_inclination.setBounds(262, 101, 125, 20);
		contentPane.add(textField_inclination);
		textField_inclination.setColumns(10);
		
		textField_semiMajorAxis = new JTextField();
		textField_semiMajorAxis.setBounds(262, 126, 125, 20);
		contentPane.add(textField_semiMajorAxis);
		textField_semiMajorAxis.setColumns(10);
		
		textField_logindtudeOfAscendingNode = new JTextField();
		textField_logindtudeOfAscendingNode.setBounds(262, 151, 125, 20);
		contentPane.add(textField_logindtudeOfAscendingNode);
		textField_logindtudeOfAscendingNode.setColumns(10);
		
		textField_argumentOfPeriapsis = new JTextField();
		textField_argumentOfPeriapsis.setBounds(262, 176, 125, 20);
		contentPane.add(textField_argumentOfPeriapsis);
		textField_argumentOfPeriapsis.setColumns(10);
		
		textField_eccentricity = new JTextField();
		textField_eccentricity.setBounds(262, 76, 125, 20);
		contentPane.add(textField_eccentricity);
		textField_eccentricity.setColumns(10);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(122, 240, 89, 23);
		contentPane.add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(248, 240, 89, 23);
		contentPane.add(cancelButton);
		
		setVisible(true);
	}
}
