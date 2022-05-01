package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class subPanelForTickets2 extends JPanel{

	public subPanelForTickets2 () {
		
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
                	
                	
                	Entry.selectedSeats=text;
                	for(JToggleButton i:client.buttons2) {
                		if(!text.equals(i.getText())) {                			
                			i.setEnabled(false);
                		}
                	}
                	for(JToggleButton i:client.buttons) {      			
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
			client.buttons2[i] = new JToggleButton(String.valueOf(i+1+20));
			client.buttons2[i].addItemListener(itemListener);
			add(client.buttons2[i]);
		}

		
		setVisible(true);
		setSize(230,230);
		
	}
}


