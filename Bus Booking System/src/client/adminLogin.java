package client;

import java.awt.Dimension;

import javax.swing.*;

public class adminLogin extends JPanel{
	
	public JLabel userNameAdmin = new JLabel("UserName:");
    public JLabel passwordAdmin = new JLabel("Password:");
    public JTextField userNameFieldAdmin = new JTextField();
    public JPasswordField passwordFieldAdmin = new JPasswordField();
    public JButton loginButtonAdim = new JButton("Login");
	
	
	
	
	
	public adminLogin() {
		super();
		setSize(500,500);
		setLayout(null);
		init();
	}
	
	public void init() {
	    
	    
	    Dimension userNameSize = userNameAdmin.getPreferredSize();
	    userNameAdmin.setBounds(10, 10, userNameSize.width, userNameSize.height);

        Dimension passwordSize = passwordAdmin.getPreferredSize();
        passwordAdmin.setBounds(10, 55, passwordSize.width, passwordSize.height);

        userNameFieldAdmin.setBounds(75, 10, 200, 20);

        passwordFieldAdmin.setBounds(75, 55, 200, 20);

        Dimension loginButtonSize = loginButtonAdim.getPreferredSize();
        loginButtonAdim.setBounds(10, 100, 200, loginButtonSize.height);
        
        add(userNameAdmin);
        add(passwordAdmin);
        add(userNameFieldAdmin);
        add(passwordFieldAdmin);
        add(loginButtonAdim);
        
        setVisible(false);
	}

}
