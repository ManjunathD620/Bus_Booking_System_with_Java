package client;

import javax.swing.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry extends JFrame {
	InetAddress ip;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectOutputStream out;
	ObjectInputStream oin;

	private intro introPage = new intro();
	private login loginPage = new login();
	private client clientPage;
	private admin adminPage = new admin();
	private create createPage = new create();
	private adminLogin adminLoginPage = new adminLogin();
	private landing landingPage = new landing();
	public static String selectedSeats = new String();
	public static String[] fromList = null;
	public static String[] toList = null;
	public static String[] timeList = null;

	public String[] user_details =  null;
	

	
	private class Disabler extends Thread{
		public void run() {
			while(true){		
				String[] detail_for_seats = {"seatAvailable",clientPage.fromCombo.getSelectedItem().toString(),
						clientPage.toCombo.getSelectedItem().toString(),
						clientPage.dateCombo.getSelectedItem().toString(),
						clientPage.timeCombo.getSelectedItem().toString()}; 
				try {
					Thread.sleep(500);
					out.writeObject(detail_for_seats);
					String[] seats = (String[]) oin.readObject();
					disableBooked(seats);
				} catch (IOException | ClassNotFoundException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	public Entry() {
		super("Bus Booking");
		socketConnector();
		init();
	}

	public void socketConnector() {
		try {
			ip = InetAddress.getByName("localhost");
			s = new Socket(ip, 5056);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			oin = new ObjectInputStream(s.getInputStream());
			String[] req = {"getFrom"};
			String[] req2 = {"getTo"};
			String[] req3 = {"getTime"};

			out.writeObject(req);
			fromList = (String[]) oin.readObject();
			out.writeObject(req2);
			toList = (String[]) oin.readObject();
			out.writeObject(req3);
			timeList = (String[]) oin.readObject();
	
			clientPage = new client();
			Disabler d = new Disabler();
			clientPage.timeCombo.setEnabled(false);
			String[] args = {"getTime2",clientPage.fromCombo.getSelectedItem().toString(),
					clientPage.toCombo.getSelectedItem().toString()};
			out.writeObject(args);
			String t = dis.readUTF();
			clientPage.timeCombo.setSelectedItem(t);
			clientPage.toCombo.removeAllItems();
			String[] s = {"tos",clientPage.fromCombo.getSelectedItem().toString()};
			try {
				out.writeObject(s);
				s = (String[]) oin.readObject();
				for(String i:s) {
					clientPage.toCombo.addItem(new ObjectForCombo(i,i));
		    	}
			} catch (IOException | ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			d.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void disableBooked(String[] args) {
		
		for(JToggleButton i:client.buttons) {
    		if(Arrays.asList(args).contains(i.getText())) {                			
    			i.setEnabled(false);
    		}
    	}
    	for(JToggleButton i:client.buttons2) {      			
    		if(Arrays.asList(args).contains(i.getText())) {                			
    			i.setEnabled(false);
    		}
    	}
	}
	
	private void init() {

		setSize(500, 500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				 try {
					dos.writeUTF("EXIT");
					 s.close();
					dis.close();
					dos.close();
					out.close();
					oin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		introPage.adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				introPage.setVisible(false);
				adminLoginPage.setVisible(true);

			}
		});

		introPage.userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.setVisible(true);
				introPage.setVisible(false);

			}
		});

		
		loginPage.loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] details = { "userLogin", loginPage.passwordField.getText(),
							loginPage.userNameField.getText() };
					out.writeObject(details);
					String received = dis.readUTF();
					user_details = (String[]) oin.readObject(); 
					
					if (received.equals("yes")) {
						loginPage.setVisible(false);
						landingPage.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(loginPage, "UserName or Password incorrect");
					}
				} catch (IOException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(loginPage, "UserName or Password incorrect");
					e1.printStackTrace();
				}

			}
		});

		loginPage.createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.setVisible(false);
				createPage.setVisible(true);
			}
		});

		createPage.createAccountForCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (accountStatus() == true) {
					JOptionPane.showMessageDialog(createPage, "Successful!");
					loginPage.setVisible(true);
					createPage.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(createPage, "Something went wrong!");
					loginPage.setVisible(false);
					createPage.setVisible(true);
				}
			}
			
			
			
		
			
			private boolean accountStatus() {
				String gender = createPage.genderCombo.getSelectedItem().toString();
				String pn = createPage.phoneNoField.getText();
				String dob = createPage.dobField.getText();
				String pin = createPage.pincodeField.getText();
				String dst = createPage.districtField.getText();
				String state = createPage.stateField.getText();
				String pasw = createPage.passwordFieldForCreate.getText();
				String un = createPage.userNameFieldForCreate.getText();
				String[] details = { "newAccount", un, pasw, state, dst, pin, dob, pn, gender };
				try {
					out.writeObject(details);
					String recieved = dis.readUTF();
					if(recieved.equals("yes")) {
						return true;
					}
					else {
						return false;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			}
		});

		adminPage.addVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String[] args = {"addVehicle",adminPage.regNo.getText(),adminPage.empID.getText(),adminPage.from.getText(),adminPage.to.getText(),adminPage.time.getText(),adminPage.price.getText()};  
				try {
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.addEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String[] args = {"addEmployee",adminPage.driverName.getText(),adminPage.salary.getText(),adminPage.dob.getText()};  
				try {
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.removeEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					String[] args = {"removeEmployee",adminPage.ID.getText()};
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminPage.removeVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					String[] args = {"removeVehicle",adminPage.regNo.getText()};
					out.writeObject(args);
					String result = dis.readUTF();
					if(result.equals("yes")) {
						JOptionPane.showMessageDialog(adminPage, "Success!");
					}
					else {
						JOptionPane.showMessageDialog(adminPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} );
		
		adminLoginPage.loginButtonAdim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = adminLoginPage.passwordFieldAdmin.getText();
				String pasw = adminLoginPage.userNameFieldAdmin.getText();
				String[] details = { "adminLogin", name, pasw };
				try {
					out.writeObject(details);
					String received = dis.readUTF();
					if(received.equals("yes")) {
						adminLoginPage.setVisible(false);
						adminPage.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(adminLoginPage, "Something went wrong!");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	
		createPage.phoneNoField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (createPage.phoneNoField.getText().length() >= 10
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					evt.consume();
				}
				if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
					evt.consume();
				}
			}
		});

		createPage.pincodeField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				if (createPage.pincodeField.getText().length() >= 6
						&& !(evt.getKeyChar() == KeyEvent.VK_DELETE || evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					evt.consume();
				}
				if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
					evt.consume();
				}
			}
		});

		landingPage.bookTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				landingPage.setVisible(false);
				clientPage.setVisible(true);

			}
		});

		clientPage.fromCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
					
//					clientPage.toCombo.removeAllItems();
//					String[] s = {"tos",clientPage.fromCombo.getSelectedItem().toString()};
//					try {
//						out.writeObject(s);
//						s = (String[]) oin.readObject();
//						for(String i:s) {
//							clientPage.toCombo.addItem(new ObjectForCombo(i,i));
//				    	}
//					} catch (IOException | ClassNotFoundException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					
				
					String[] detail_for_seats = {"seatAvailable",clientPage.fromCombo.getSelectedItem().toString(),
							clientPage.toCombo.getSelectedItem().toString(),
							clientPage.dateCombo.getSelectedItem().toString(),
							clientPage.timeCombo.getSelectedItem().toString()}; 
					try {
						
						out.writeObject(detail_for_seats);
						String[] seats = (String[]) oin.readObject();
						disableBooked(seats);
						String[] args = {"getTime2",detail_for_seats[0],detail_for_seats[1]};
						out.writeObject(args);
						String t = dis.readUTF();
						clientPage.timeCombo.setSelectedItem(t);
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
			
		});
		
	
		
		clientPage.dateCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

					String[] detail_for_seats = {"seatAvailable",clientPage.fromCombo.getSelectedItem().toString(),
							clientPage.toCombo.getSelectedItem().toString(),
							clientPage.dateCombo.getSelectedItem().toString(),
							clientPage.timeCombo.getSelectedItem().toString()}; 
					try {
						out.writeObject(detail_for_seats);
						String[] seats = (String[]) oin.readObject();
						disableBooked(seats);
						String[] args = {"getTime2",detail_for_seats[0],detail_for_seats[1]};
						out.writeObject(args);
						String t = dis.readUTF();
						clientPage.timeCombo.setSelectedItem(t);
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
		});
		
		clientPage.submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] prreq = {"getRegPrice",clientPage.toCombo.getSelectedItem().toString(),clientPage.fromCombo.getSelectedItem().toString(),clientPage.timeCombo.getSelectedItem().toString()}; 
				String[] price_reg = {"",""};
	
				try {
					out.writeObject(prreq);
					price_reg = (String[]) oin.readObject();
					
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String date = clientPage.dateCombo.getSelectedItem().toString();
				String from = clientPage.fromCombo.getSelectedItem().toString();
				String to = clientPage.toCombo.getSelectedItem().toString();
				String time = clientPage.timeCombo.getSelectedItem().toString();
				String pickupDate =  clientPage.dateCombo.getSelectedItem().toString();
				String amount = price_reg[1];
				String reg = price_reg[0];
				String name = user_details[1];
				String pn = user_details[2];
				
				String[] details = {"ticket",name,from,to,amount,selectedSeats,pn,pickupDate,reg,time};
				String result = null;
				try {
					out.writeObject(details);
					result = dis.readUTF();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result.equals("yes")) {
					JOptionPane.showMessageDialog(createPage, "Successful!");
				} else {
					JOptionPane.showMessageDialog(createPage, "Something went wrong!");
				}
			}
		});

		add(introPage);
		add(loginPage);
		add(clientPage);
		add(adminPage);
		add(createPage);
		add(adminLoginPage);
		add(landingPage);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Entry();
	}
}