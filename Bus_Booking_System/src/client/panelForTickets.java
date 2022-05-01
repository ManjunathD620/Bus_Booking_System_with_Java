package client;

import javax.swing.*;
import java.awt.*;

public class panelForTickets extends JPanel{
 
	public panelForTickets(){
		super();
		setLayout(null);
		
		subPanelForTickets1 right = new subPanelForTickets1();
		subPanelForTickets2 left = new subPanelForTickets2();
		
		right.setBounds(0,0,230,230);
		left.setBounds(240,0,230,230);

		add(right);
		add(left);

		setSize(600,600);
		setVisible(true);
	}
}
