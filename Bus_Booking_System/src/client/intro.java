package client;


import javax.swing.*;

import java.awt.Dimension;


public class intro extends JPanel{
	public JButton userButton = new JButton("Continue as user");
	public JButton adminButton = new JButton("Continue as admin");
	
	public intro(){
		super();
		setSize(500,500);
		setLayout(null);
		init();
	}
	
	public void init(){
		Dimension userSize = userButton.getPreferredSize();
		userButton.setBounds(10,10, 139,userSize.height);
        Dimension adminize = adminButton.getPreferredSize();
        adminButton.setBounds(10,55,adminize.width,adminize.height);
        add(userButton);
        add(adminButton);
	    setVisible(true);
	}
}
