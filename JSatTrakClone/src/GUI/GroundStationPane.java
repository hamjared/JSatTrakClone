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
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
		setResizable(false);
		setTitle("Ground Station");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 329, 169);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{114, 168, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_name = new JTextField();
		GridBagConstraints gbc_textField_name = new GridBagConstraints();
		gbc_textField_name.fill = GridBagConstraints.BOTH;
		gbc_textField_name.insets = new Insets(0, 0, 5, 0);
		gbc_textField_name.gridx = 1;
		gbc_textField_name.gridy = 0;
		contentPane.add(textField_name, gbc_textField_name);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Latitude");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_latitude = new JTextField();
		GridBagConstraints gbc_textField_latitude = new GridBagConstraints();
		gbc_textField_latitude.fill = GridBagConstraints.BOTH;
		gbc_textField_latitude.insets = new Insets(0, 0, 5, 0);
		gbc_textField_latitude.gridx = 1;
		gbc_textField_latitude.gridy = 1;
		contentPane.add(textField_latitude, gbc_textField_latitude);
		textField_latitude.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Longitude");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_longitude = new JTextField();
		GridBagConstraints gbc_textField_longitude = new GridBagConstraints();
		gbc_textField_longitude.fill = GridBagConstraints.BOTH;
		gbc_textField_longitude.insets = new Insets(0, 0, 5, 0);
		gbc_textField_longitude.gridx = 1;
		gbc_textField_longitude.gridy = 2;
		contentPane.add(textField_longitude, gbc_textField_longitude);
		textField_longitude.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.LINE_START;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{137, 168, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnNewButton = new JButton("Add");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGroundStation();
			}
		});
		
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
