package server;

import java.sql.*;
import java.util.ArrayList;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Database {
	public Connection connection;

	public Database() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus", "root", "1234");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	
	public String[] fromList() {
		ResultSet resultSet;
		ArrayList<String> fromSet = new ArrayList<>();
		String[] fromSetArray = null;
		try {
			String query = String.format("SELECT pickup FROM vehicle");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				fromSet.add(resultSet.getString("pickup"));
			}
			fromSetArray = fromSet.toArray(new String[fromSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return fromSetArray;
	}
	
	
	public String[] toList() {
		ResultSet resultSet;
		ArrayList<String> toSet = new ArrayList<>();
		String[] toSetArray = null;
		try {
			String query = String.format("SELECT destination FROM vehicle");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				toSet.add(resultSet.getString("destination"));
			}
			toSetArray = toSet.toArray(new String[toSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return toSetArray;
	}
	
	public String[] timeList() {
		ResultSet resultSet;
		ArrayList<String> timeSet = new ArrayList<>();
		String[] timeSetArray = null;
		try {
			String query = String.format("SELECT timing FROM vehicle");
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				timeSet.add(resultSet.getString("timing"));
			}
			timeSetArray = timeSet.toArray(new String[timeSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return timeSetArray;
	}
	
	public String[] seatList(String[] args) {
		ResultSet resultSet;
		ArrayList<String> seatSet = new ArrayList<>();
		String[] seatSetArray = null;
		try {
			String query = String.format("SELECT seatNo FROM ticket where pickup='%s' AND destination='%s' AND pickupDate='%s' And pickup_time='%s'",args[1],args[2],args[3],args[4]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				seatSet.add(resultSet.getString("seatNo"));
			}
			seatSetArray = seatSet.toArray(new String[seatSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return seatSetArray;
	}
	
	public String[] tolist2(String args) {
		ResultSet resultSet;
		ArrayList<String> seatSet = new ArrayList<>();
		String[] seatSetArray = null;
		try {
			String query = String.format("SELECT destination FROM vehicle where pickup='%s'",args);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				seatSet.add(resultSet.getString("destination"));
			}
			seatSetArray = seatSet.toArray(new String[seatSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return seatSetArray;
	}
	
	public String[] fromlist2(String args) {
		ResultSet resultSet;
		ArrayList<String> seatSet = new ArrayList<>();
		String[] seatSetArray = null;
		try {
			String query = String.format("SELECT pickup FROM vehicle where destination='%s'",args);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				seatSet.add(resultSet.getString("pickup"));
			}
			seatSetArray = seatSet.toArray(new String[seatSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return seatSetArray;
	}
	

	public boolean insertEmployee(String args[]) {
		try {
			String query = String.format("insert into employee (driverName,salary,dob) values ('%s',%s,'%s')", args[0],args[1],args[2]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	

	public boolean deleteEmployee(String id) {
		try {
			String query = String.format("delete from employee where driverId=%s", id);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}
	
	public String get_time(String[] args) {
		ResultSet resultSet;
	
		String time = null;
		try {
			String query = String.format("SELECT timing FROM vehicle where destination='%s' AND pickup='%s' ",args[2],args[1]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			time = resultSet.getString("timing");
			return time;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return time;
	}

	
	public boolean insertTicket(String args[]) {
//		{"ticket",name,from,to,amount,selectedSeats,pn,pickupDate,reg,time};
		try {
			String count = String.format("select count(seatNo) from ticket where regNO='%s'",args[7]);
			PreparedStatement preparedStatement = connection.prepareStatement(count);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if(rs.getInt("count(seatNo)")<40) {
				String query = String.format(
						"insert into ticket(userName,pickup,destination,amount,seatNo,phoneNo,pickupDate,regNO,pickup_time) values ('%s','%s','%s',%s,%s,%s,'%s','%s','%s')",
						args[0], args[1], args[2], args[3], args[4], args[5], args[6],args[7], args[8]);
				PreparedStatement preparedStatement2 = connection.prepareStatement(query);
				preparedStatement2.executeUpdate();
				
				return true;
			}
			else {
				return false;
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	
	public boolean deleteTicket(int ticketNo) {
		try {
			String query = String.format("delete from ticket where ticketNo=%d", ticketNo);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	

	
	
	public boolean insertVehicle(String args[]) {
		try {
			String query = String.format("insert into vehicle(regNO,driverId,pickup,destination,timing,ticket_price) values('%s',%s,'%s','%s','%s',%s)", args[0],args[1],args[2],args[3],args[4],args[5] );
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean deleteVehicle(String regNO) {
		try {
			String query = String.format("delete from vehicle where regNO = '%s'", regNO);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean insertClients(String args[]) {
		try {
			
			String query = String.format(
					"insert into clients(userName,userPassword,state,district,pincode,dob,phoneNo,gender) values ('%s',MD5('%s'),'%s','%s',%s,'%s',%s,'%s')",
					args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

	
	
	public String[] getReg_N_Amount(String destination,String pickup,String time) {
		ResultSet resultSet;
		ArrayList<String> regPriceSet = new ArrayList<>();
		String[] regPriceArray = null;
		try {
			String query = String.format("select ticket_price,regNO from vehicle where destination='%s' AND pickup='%s' AND timing='%s'",destination,pickup,time);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
				
				regPriceSet.add(resultSet.getString("regNO"));
				regPriceSet.add(resultSet.getString("ticket_price"));
			
			regPriceArray = regPriceSet.toArray(new String[regPriceSet.size()]);
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return regPriceArray;
	}
	
	public String[] getUserdetails(String username,String password){
		ResultSet resultSet;
		String[] userdetails = {"id","name","pn"};
		try {
			
			String query = String.format("select userId,userName,phoneNo from clients where userName='%s' AND userPassword=MD5('%s')",username,password);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			userdetails[0] = resultSet.getString("userId");
			userdetails[1] = resultSet.getString("userName");
			userdetails[2] = resultSet.getString("phoneNo");			
		} catch (SQLException e) {
			System.out.println(e);
		}
	
		return userdetails;
	}
	
	
	public boolean checkLogin(String UserName,String Password) {
		try {
			String query = String.format("select userPassword from clients where userName='%s'",UserName);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String p = resultSet.getString("userPassword");
			MessageDigest md = MessageDigest.getInstance("MD5");
			  
            byte[] messageDigest = md.digest(Password.getBytes());
  
            BigInteger no = new BigInteger(1, messageDigest);
  
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            if(hashtext.equals(p)) {
            	return true;
            }
            else {
            	return false;
            }
		} catch (SQLException e) {
			return false;
		}
		catch (NoSuchAlgorithmException e) {
            return false;
        }
	}
	
	public void close() throws SQLException {
		connection.close();
	}

}