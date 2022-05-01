package client;

import javax.swing.*;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class client extends JPanel{
    public JLabel from = new JLabel("From:");
    public JLabel to = new JLabel("To:");

    public JLabel date = new JLabel("Date:");
    public JLabel time = new JLabel("Time:");


    public String[] dateList = new String[7]; 
    
    
    
    public JComboBox fromCombo ;
    public JComboBox toCombo ;
    public JComboBox timeCombo ;
    public JButton submit = new JButton("Submit");
	public panelForTickets ticketSelection = new panelForTickets();
    public JComboBox dateCombo ;
    
    public static JToggleButton[] buttons = new JToggleButton[20];
    public static JToggleButton[] buttons2 = new JToggleButton[20];
    
	public client() {
	    super();
	    setSize(1000,1000);
	    setLayout(null);
	    init();
	}
	
	public static String getNextDate(String  curDate) throws ParseException {
		  final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  final Date date = format.parse(curDate);
		  final Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.add(Calendar.DAY_OF_YEAR, 1);
		  return format.format(calendar.getTime()); 
		}
	
	
	public void init() {
		
		
		////should be added in a seperate thread for fast start//////
	
		fromCombo = new JComboBox(Entry.fromList);
		toCombo = new JComboBox(Entry.toList);
		timeCombo = new JComboBox(Entry.timeList);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date curdate = new Date();
	    dateList[0] = formatter.format(curdate);
	    String pdate = dateList[0];
	    System.out.print(pdate);
	    for(int i=1;i<7;i++) {
	    	try {
	    		dateList[i] = getNextDate(pdate);
	    		pdate = dateList[i];
	    	} catch (ParseException e) {
	    		e.printStackTrace();
	    	}  
	    }
	    
	    ///////////////////////////////////////////////////////////////
	    
	    dateCombo = new JComboBox(dateList);
	    
     
        from.setBounds(10,10,200,20);


        to.setBounds(10,55,200,20);
   
 
        fromCombo.setBounds(75,10,200,20);

        toCombo.setBounds(75,55,200,20);

        dateCombo.setBounds(75,100,200,20);

        date.setBounds(10,100,200,20);
        
        timeCombo.setBounds(75,145,200,20);

        time.setBounds(10,145,200,20);
        
       
        submit.setBounds(10, 190,200,20);
        
        ticketSelection.setBounds(450,10,480,570); 
        

       
        add(from);
        add(to);
        add(fromCombo);
        add(toCombo);
        add(submit);
        add(ticketSelection);


        add(date);
        add(dateCombo);
        add(time);
        add(timeCombo);
        setVisible(false);
	}
}
