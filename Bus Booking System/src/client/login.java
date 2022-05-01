package client;

import javax.swing.*;

import java.awt.Dimension;

	
public class login extends JPanel{
	public JLabel userName = new JLabel("UserName:");
    public JLabel password = new JLabel("Password:");
    public JTextField userNameField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JButton loginButton = new JButton("Login");
    public JButton createAccount = new JButton("Create Account");
	
	
	
	
	
	public login() {
		super();
		setSize(500,500);
		setLayout(null);
		init();
	}
	
	public void init() {
	    
	    
	    Dimension userNameSize = userName.getPreferredSize();
        userName.setBounds(10, 10, userNameSize.width, userNameSize.height);

        Dimension passwordSize = password.getPreferredSize();
        password.setBounds(10, 55, passwordSize.width, passwordSize.height);

        userNameField.setBounds(75, 10, 200, 20);

        passwordField.setBounds(75, 55, 200, 20);

        Dimension loginButtonSize = loginButton.getPreferredSize();
        loginButton.setBounds(10, 100, 200, loginButtonSize.height);

        Dimension createAccountSize = createAccount.getPreferredSize();
        createAccount.setBounds(210, 100, 200, createAccountSize.height);
        
        add(userName);
        add(password);
        add(userNameField);
        add(passwordField);
        add(loginButton);
        add(createAccount);
        setVisible(false);
	}
}
