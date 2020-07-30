package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.GroundStation;
import objects.Position;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.event.ActionEvent;

public class GroundStationPane extends JFrame {
	private static final long serialVersionUID = 7645302882996481456L;
	private JPanel contentPane;
	private JTextField textField_latitude;
	private JTextField textField_longitude;
	private JTextField textField_name;


	/**
	 * Create the frame.
	 */
	public GroundStationPane() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Latitude");
		lblNewLabel.setBounds(88, 46, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Longitude");
		lblNewLabel_1.setBounds(88, 71, 59, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_latitude = new JTextField();
		textField_latitude.setBounds(200, 43, 86, 20);
		contentPane.add(textField_latitude);
		textField_latitude.setColumns(10);
		
		textField_longitude = new JTextField();
		textField_longitude.setBounds(200, 68, 86, 20);
		contentPane.add(textField_longitude);
		textField_longitude.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGroundStation();
			}
		});
		btnNewButton.setBounds(107, 116, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(210, 116, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(88, 21, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_name = new JTextField();
		textField_name.setBounds(200, 18, 86, 20);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		setVisible(true);
	}


	protected void addGroundStation() {
		if(!validateInput()) {
			return;
		}
		
		GroundStation gs = new GroundStation(this.textField_name.getText(), 
				new Position(Double.parseDouble(this.textField_latitude.getText()), 
						Double.parseDouble(this.textField_longitude.getText()))
				);
		
		GUI.groundStations.addElement(gs);
		dispose();
		
	}


	private boolean validateInput() {
		// TODO Auto-generated method stub
		return true;
	}
}
