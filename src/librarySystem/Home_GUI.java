package librarySystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Home_GUI extends JFrame {

	static ArrayList<Book> arrayListOfBooks = new ArrayList<Book>();
	static ArrayList<Patron> arrayListOfPatrons = new ArrayList<Patron>();
	static ArrayList<Admin> arrayListOfAdmins = new ArrayList<Admin>();
	
	static ArrayList<Patron> arrayListContainingCurrentPatron = new ArrayList<Patron>();
	static ArrayList<Admin> arrayListContainingCurrentAdmin = new ArrayList<Admin>();
	
    public static Connection getConnection() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/library";
            String username = "";
            String password = "";
            
            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch(Exception e){System.out.println(e);}
        return null;
    }

	public static void loadBooks() {
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT bookID, title, author, genre, available, userID FROM book");
            ResultSet result = statement.executeQuery();
           
            
            while(result.next()){
                int bookID = result.getInt("bookID");
                String name = result.getString("title");
                String author = result.getString("author");
                String genre = result.getString("genre");
                boolean available = result.getBoolean("available");
                int userID = result.getInt("userID");
                arrayListOfBooks.add(new Book(bookID, name, author, genre, available, userID)); 
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }
	}
	
	public static void loadPatrons() {
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT userID, userFirstName, userLastName, userPassword FROM user WHERE userAdmin=0");
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                int patronID = result.getInt("userID");
                String patronFirstName = result.getString("userFirstName");
                String patronLastName = result.getString("userLastName");
                String patronPassword = result.getString("userPassword");
                arrayListOfPatrons.add(new Patron(patronID, patronFirstName, patronLastName, patronPassword)); 
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }		
	}
	
	public static void loadAdmins() {
        try{
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT userID, userFirstName, userLastName, userPassword FROM user WHERE userAdmin=1");
            ResultSet result = statement.executeQuery();
            
            while(result.next()){
                int adminID = result.getInt("userID");
                String adminFirstName = result.getString("userFirstName");
                String adminLastName = result.getString("userLastName");
                String adminPassword = result.getString("userPassword");
                arrayListOfAdmins.add(new Admin(adminID, adminFirstName, adminLastName, adminPassword)); 
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }		
	}
	
	
	private JPanel contentPane;
	private JTextField textField_UserID;
	private JTextField textField_UserPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_GUI frame = new Home_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		loadBooks();
		loadPatrons();
		loadAdmins();
		
		textField_UserID = new JTextField();
		textField_UserID.setBounds(227, 142, 135, 20);
		contentPane.add(textField_UserID);
		textField_UserID.setColumns(10);
		
		JLabel label_UserID = new JLabel("ID:");
		label_UserID.setBounds(122, 145, 95, 14);
		contentPane.add(label_UserID);
		
		JLabel label_UserPassword = new JLabel("Password:");
		label_UserPassword.setBounds(122, 200, 95, 14);
		contentPane.add(label_UserPassword);
		
		JLabel label_Info = new JLabel("Please enter your details below:");
		label_Info.setFont(new Font("Arial", Font.BOLD, 17));
		label_Info.setBounds(146, 66, 265, 42);
		contentPane.add(label_Info);
		
		textField_UserPassword = new JTextField();
		textField_UserPassword.setBounds(227, 197, 135, 20);
		contentPane.add(textField_UserPassword);
		textField_UserPassword.setColumns(10);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int counter = 0; counter < arrayListOfPatrons.size(); counter++) {
					if(arrayListOfPatrons.get(counter).getID() == Integer.parseInt(textField_UserID.getText())){
						if(arrayListOfPatrons.get(counter).getPassword().length() == textField_UserPassword.getText().toString().length()) {
							arrayListContainingCurrentPatron.add(new Patron(arrayListOfPatrons.get(counter).getID(), arrayListOfPatrons.get(counter).getFirstName(), arrayListOfPatrons.get(counter).getLastName(), arrayListOfPatrons.get(counter).getPassword())); 
							dispose();
							Patron_Section_GUI patron_Frame = new Patron_Section_GUI(); 
							patron_Frame.setVisible(true);
						}
					}
				}
				for(int counter = 0; counter < arrayListOfAdmins.size(); counter++) {
					if(arrayListOfAdmins.get(counter).getID() == Integer.parseInt(textField_UserID.getText())){
						if(arrayListOfAdmins.get(counter).getPassword().length() == textField_UserPassword.getText().toString().length()) {
							arrayListContainingCurrentAdmin.add(new Admin(arrayListOfAdmins.get(counter).getID(), arrayListOfAdmins.get(counter).getFirstName(), arrayListOfAdmins.get(counter).getLastName(), arrayListOfAdmins.get(counter).getPassword()));
							dispose();
							Admin_Section_GUI admin_Frame = new Admin_Section_GUI(); 
							admin_Frame.setVisible(true);
						}
					}
				}
			}
		});
		enterButton.setBounds(243, 258, 89, 23);
		contentPane.add(enterButton);
		

		
		

	}
}
