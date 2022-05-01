package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class subPanelForTickets1 extends JPanel{
	

	
	public subPanelForTickets1 () {
		
		
		
		super();
		
		setLayout(new GridLayout(10,2));

		Color select = UIManager.getColor("ToggleButton.select");
		UIManager.put("ToggleButton.select", Color.GREEN);
		
		ItemListener itemListener = new ItemListener() {

            public void itemStateChanged(ItemEvent itemEvent)
            {
 
                int state = itemEvent.getStateChange();
                String text = ((JToggleButton) itemEvent.getSource()).getText();
                if (state == ItemEvent.SELECTED) {
                	
                	Entry.selectedSeats = text;
                	for(JToggleButton i:client.buttons) {
                		if(!text.equals(i.getText())) {                			
                			i.setEnabled(false);
                		}
                	}
                	for(JToggleButton i:client.buttons2) {      			
                			i.setEnabled(false);
                	}
                }
                else {
              
                	Entry.selectedSeats = "";
                	for(JToggleButton i:client.buttons2) {           			
            			i.setEnabled(true);
            	}
            	for(JToggleButton i:client.buttons) {      			
            			i.setEnabled(true);
            	}
                }
            }
        };
		    
		
		for (int i=0;i<20;i++) {
			client.buttons[i] = new JToggleButton(String.valueOf(i+1));
			client.buttons[i].addItemListener(itemListener);
			add(client.buttons[i]);
		}
		

		setVisible(true);
		setSize(230,230);
	}
}
