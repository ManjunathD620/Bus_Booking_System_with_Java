package client;

import javax.swing.*;

import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class create extends JPanel {
	public JLabel userNameForCreate = new JLabel("UserName:");
	public JLabel passwordForCreate = new JLabel("Password:");
	public JTextField userNameFieldForCreate = new JTextField();
	public JPasswordField passwordFieldForCreate = new JPasswordField();
	public JButton createAccountForCreate = new JButton("Create Account");

	public JLabel state = new JLabel("state:");
	public JLabel district = new JLabel("district:");
	public JLabel pincode = new JLabel("pincode:");
	public JLabel dob = new JLabel("dob:");
	public JLabel phoneNo = new JLabel("phoneNo:");
	public JLabel gender = new JLabel("gender:");

	public JTextField stateField = new JTextField();
	public JTextField districtField = new JTextField();
	public JTextField pincodeField = new JTextField();
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public JFormattedTextField dobField = new JFormattedTextField(df);
	
	public JTextField phoneNoField = new JTextField();
    public String genders[] = {"Male","Female","Other"};
    public JComboBox genderCombo = new JComboBox(genders);
	
	public create() {
		super();
		setSize(500, 500);
		setLayout(null);
		init();
	}

	public void init() {
		
		
		Dimension userNameForCreateSize = userNameForCreate.getPreferredSize();
		userNameForCreate.setBounds(10, 10, userNameForCreateSize.width, userNameForCreateSize.height);

		Dimension passwordForCreateSize = passwordForCreate.getPreferredSize();
		passwordForCreate.setBounds(10, 55, passwordForCreateSize.width, passwordForCreateSize.height);

		userNameFieldForCreate.setBounds(75, 10, 200, 20);

		passwordFieldForCreate.setBounds(75, 55, 200, 20);
		
		stateField.setBounds(75, 100, 200, 20);
		
		districtField.setBounds(75, 145, 200, 20);
		
		pincodeField.setBounds(75, 190, 200, 20);
		
		dobField.setBounds(75, 235, 200, 20);
		
		phoneNoField.setBounds(75, 280, 200, 20);

		Dimension stateSize = state.getPreferredSize();
		state.setBounds(10, 100, passwordForCreateSize.width, stateSize.height);
		
		Dimension districtSize = district.getPreferredSize();
		district.setBounds(10, 145, passwordForCreateSize.width, districtSize.height);

		Dimension pincodeSize = pincode.getPreferredSize();
		pincode.setBounds(10, 190, passwordForCreateSize.width, pincodeSize.height);
		
		Dimension phoneNoSize = phoneNo.getPreferredSize();
		phoneNo.setBounds(10, 280, passwordForCreateSize.width, phoneNoSize.height);

		Dimension dobSize = dob .getPreferredSize();
		dob.setBounds(10, 235, passwordForCreateSize.width, dobSize.height);
		
		Dimension genderSize = gender.getPreferredSize();
		gender.setBounds(10, 325, passwordForCreateSize.width, genderSize.height);

		Dimension genderComboSize = genderCombo.getPreferredSize();
		genderCombo.setBounds(75, 325, 200, genderComboSize.height);


		Dimension createAccountForCreateSize = genderCombo.getPreferredSize();
		createAccountForCreate.setBounds(10, 370, 200, createAccountForCreateSize.height);

	
		add(userNameForCreate);
		add(passwordForCreate);
		add(userNameFieldForCreate);
		add(passwordFieldForCreate);
		add(createAccountForCreate);
		add(gender);
		add(phoneNo);
		add(dob );
		add(pincode);
		add(district);
		add(state);
		add(genderCombo);
		add(phoneNoField);
		add(dobField);
		add(pincodeField);
		add(districtField);
		add(stateField);
		setVisible(false);
	}
}
