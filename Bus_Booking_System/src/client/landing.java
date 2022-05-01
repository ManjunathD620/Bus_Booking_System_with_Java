package client;


import javax.swing.*;

import java.awt.Dimension;


public class landing extends JPanel{
	public JButton viewTickets = new JButton("viewTickets");
	public JButton bookTickets = new JButton("bookTickets");
	
	public landing(){
		super();
		setSize(500,500);
		setLayout(null);
		init();
	}
	
	public void init(){
		Dimension userSize = viewTickets.getPreferredSize();
		viewTickets.setBounds(10,10, 139,userSize.height);
        Dimension adminize = bookTickets.getPreferredSize();
        bookTickets.setBounds(10,55,adminize.width,adminize.height);
        add(viewTickets);
        add(bookTickets);
	    setVisible(false);
	}
}
