package librarySystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Admin_Section_GUI extends JFrame {

	private JPanel contentPane;
	
	private JScrollPane scrollPane_PatronTable;
	private JScrollPane scrollPane_BookTable;
	
	private JTable bookTable;
	private JTable patronTable;
	
	DefaultTableModel model;
	
	private JTextField textField_ModifyPatronNewValue;
	private JTextField textField_AddPatronFirstName;
	private JTextField textField_AddPatronLastName;
	private JTextField textField_AddPatronPassword;
	private JTextField textField_ModifyBookNewValue;
	private JTextField textField_AddBookTitle;
	private JTextField textField_AddBookAuthor;
	private JTextField textField_AddBookGenre;
	
	private JComboBox comboBox_ModifySelectPatronID;
	private JComboBox comboBox_ModifySelectPatronCategory;
	
	private JComboBox comboBox_ModifySelectBookID;
	private JComboBox comboBox_ModifySelectBookCategory;
	
	public void PatronIDModifySelector() {
		comboBox_ModifySelectPatronID.removeAllItems();
		for(int i = 0; i< Home_GUI.arrayListOfPatrons.size() ; i++) {
			comboBox_ModifySelectPatronID.addItem("" + (Home_GUI.arrayListOfPatrons.get(i).getID())); 
		}
	}
	
	public void PatronCategoryModifySelector() {
		comboBox_ModifySelectPatronCategory.removeAllItems();
		comboBox_ModifySelectPatronCategory.addItem("" + ("First Name"));
		comboBox_ModifySelectPatronCategory.addItem("" + ("Last Name"));
		comboBox_ModifySelectPatronCategory.addItem("" + ("Password"));
	}
	
	public void BookIDModifySelector() {
		comboBox_ModifySelectBookID.removeAllItems();
		for(int i = 0; i< Home_GUI.arrayListOfBooks.size() ; i++) {
			comboBox_ModifySelectBookID.addItem("" + (Home_GUI.arrayListOfBooks.get(i).getBookID())); 
		}
	}
	
	public void BookCategoryModifySelector() {
		comboBox_ModifySelectBookCategory.removeAllItems();
		comboBox_ModifySelectBookCategory.addItem("" + ("Title"));
		comboBox_ModifySelectBookCategory.addItem("" + ("Author"));
		comboBox_ModifySelectBookCategory.addItem("" + ("Genre"));
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Section_GUI frame = new Admin_Section_GUI();
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
	public Admin_Section_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_WelcomeAdmin = new JLabel("Welcome, "+Home_GUI.arrayListContainingCurrentAdmin.get(0).getFirstName());
		label_WelcomeAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		label_WelcomeAdmin.setBounds(254, 25, 215, 23);
		contentPane.add(label_WelcomeAdmin);
		
		
		
		
		JLabel label_ModifyPatron = new JLabel("Modify Patron");
		label_ModifyPatron.setFont(new Font("Arial", Font.BOLD, 16));
		label_ModifyPatron.setBounds(107, 239, 108, 23);
		contentPane.add(label_ModifyPatron);
		
		JLabel label_AddPatron = new JLabel("Add Patron");
		label_AddPatron.setFont(new Font("Arial", Font.BOLD, 16));
		label_AddPatron.setBounds(121, 66, 108, 23);
		contentPane.add(label_AddPatron);
		
		JLabel label_ModifyBook = new JLabel("Modify Book");
		label_ModifyBook.setFont(new Font("Arial", Font.BOLD, 16));
		label_ModifyBook.setBounds(511, 239, 108, 23);
		contentPane.add(label_ModifyBook);
		
		JLabel label_AddBook = new JLabel("Add Book");
		label_AddBook.setFont(new Font("Arial", Font.BOLD, 16));
		label_AddBook.setBounds(522, 66, 108, 23);
		contentPane.add(label_AddBook);
		
		
		
		
		JLabel label_ModifySelectPatronID = new JLabel("Patron ID");
		label_ModifySelectPatronID.setBounds(25, 281, 46, 14);
		contentPane.add(label_ModifySelectPatronID);
		
		JLabel label_ModifySelectPatronCategory = new JLabel("Modify");
		label_ModifySelectPatronCategory.setBounds(139, 281, 46, 14);
		contentPane.add(label_ModifySelectPatronCategory);
		
		JLabel label_ModifyPatronNewValue = new JLabel("New Value");
		label_ModifyPatronNewValue.setBounds(254, 281, 59, 14);
		contentPane.add(label_ModifyPatronNewValue);
		
		comboBox_ModifySelectPatronID = new JComboBox();
		comboBox_ModifySelectPatronID.setBounds(10, 306, 77, 22);
		contentPane.add(comboBox_ModifySelectPatronID);
		PatronIDModifySelector();
		
		comboBox_ModifySelectPatronCategory = new JComboBox();
		comboBox_ModifySelectPatronCategory.setBounds(97, 306, 118, 22);
		contentPane.add(comboBox_ModifySelectPatronCategory);
		PatronCategoryModifySelector();
		
		textField_ModifyPatronNewValue = new JTextField();
		textField_ModifyPatronNewValue.setBounds(225, 306, 117, 23);
		contentPane.add(textField_ModifyPatronNewValue);
		textField_ModifyPatronNewValue.setColumns(10);
		
		JButton btnModifyPatronConfirm = new JButton("Confirm");
		btnModifyPatronConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int patronID = Integer.parseInt(comboBox_ModifySelectPatronID.getSelectedItem().toString());
				String patronCategory = comboBox_ModifySelectPatronCategory.getSelectedItem().toString();
				String patronChangeValue = textField_ModifyPatronNewValue.getSelectedText().toString();
		        try{
		            Connection con = Home_GUI.getConnection();
		            PreparedStatement statement = con.prepareStatement("UPDATE user SET "+patronCategory+"="+patronChangeValue+" WHERE userID="+patronID);
		            ResultSet result = statement.executeQuery();
		            Home_GUI.loadBooks();
		        }
		        catch(Exception e1){
		        	System.out.println(e1);
		        }
			}
		});
		btnModifyPatronConfirm.setBounds(121, 336, 77, 23);
		contentPane.add(btnModifyPatronConfirm);
		

		
		
		
		JLabel label_AddPatronFirstName = new JLabel("First Name");
		label_AddPatronFirstName.setBounds(58, 104, 59, 14);
		contentPane.add(label_AddPatronFirstName);
		
		JLabel label_AddPatronLastName = new JLabel("Last Name");
		label_AddPatronLastName.setBounds(233, 104, 53, 14);
		contentPane.add(label_AddPatronLastName);
		
		JLabel label_AddPatronPassword = new JLabel("Password");
		label_AddPatronPassword.setBounds(59, 157, 46, 14);
		contentPane.add(label_AddPatronPassword);
		
		textField_AddPatronFirstName = new JTextField();
		textField_AddPatronFirstName.setBounds(10, 129, 152, 23);
		contentPane.add(textField_AddPatronFirstName);
		textField_AddPatronFirstName.setColumns(10);
		
		textField_AddPatronLastName = new JTextField();
		textField_AddPatronLastName.setBounds(172, 129, 170, 23);
		contentPane.add(textField_AddPatronLastName);
		textField_AddPatronLastName.setColumns(10);
		
		textField_AddPatronPassword = new JTextField();
		textField_AddPatronPassword.setColumns(10);
		textField_AddPatronPassword.setBounds(10, 182, 152, 23);
		contentPane.add(textField_AddPatronPassword);
		
		JButton btnAddPatronConfirm = new JButton("Confirm");
		btnAddPatronConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = textField_AddPatronFirstName.getText().toString();
				String lastName = textField_AddPatronLastName.getText().toString();
				String password = textField_AddPatronPassword.getText().toString();
				if(firstName!="" && lastName!="" && password!="") {
			        try{
			            Connection con = Home_GUI.getConnection();
			            PreparedStatement statement = con.prepareStatement("INSERT INTO User (userFirstName, userLastName, userPassword, userAdmin) VALUES ('"+firstName+"', '"+lastName+"' , '"+password+"', 0");
			            ResultSet result = statement.executeQuery();
			        }
			        catch(Exception e1){
			        	System.out.println(e1);
			        }
				}
			}
		});
		btnAddPatronConfirm.setBounds(172, 182, 170, 23);
		contentPane.add(btnAddPatronConfirm);
		

		

		
		scrollPane_BookTable = new JScrollPane();
		scrollPane_BookTable.setBounds(403, 392, 371, 158);
		contentPane.add(scrollPane_BookTable);
		
		//For the Books table 
		bookTable = new JTable();	
		bookTable.setEnabled(false);	//Set to false as I don't need the table to respond to user inputs
		model=new DefaultTableModel(); 
		Object[] bookTableColumn = {"ID", "Name", "Author", "Genre", "Available"}; // Column Names
		Object[] bookTableRow = new Object[5]; 
		model.setColumnIdentifiers(bookTableColumn);
		bookTable.setModel(model); 
		bookTable.getTableHeader().setReorderingAllowed(false); //Stops the table from reordering
		bookTable.getTableHeader().setResizingAllowed(false); //Stops the table from changing size
		scrollPane_BookTable.setViewportView(bookTable);	//The use of a scrollPane on the JTable will allow the JTable hold an unlimited number of passengers due to the addition of a scroll bar on the JTable 
		
		for(int counter = 0; counter < Home_GUI.arrayListOfBooks.size(); counter++) {
			int bookID = Home_GUI.arrayListOfBooks.get(counter).getBookID();
			String bookTitle = Home_GUI.arrayListOfBooks.get(counter).getBookName();
			String bookAuthor = Home_GUI.arrayListOfBooks.get(counter).getBookAuthor();
			String bookGenre = Home_GUI.arrayListOfBooks.get(counter).getBookGenre();
			boolean bookAvailable = Home_GUI.arrayListOfBooks.get(counter).getBookAvailable();
			
			bookTableRow[0] = bookID;
			bookTableRow[1] = bookTitle;
			bookTableRow[2] = bookAuthor;
			bookTableRow[3] = bookGenre;
			bookTableRow[4] = bookAvailable;
			model.addRow(bookTableRow);
		}
		
		
		scrollPane_PatronTable = new JScrollPane();
		scrollPane_PatronTable.setBounds(10, 392, 332, 158);
		contentPane.add(scrollPane_PatronTable);
		
		//For the Books table 
		patronTable = new JTable();	
		patronTable.setEnabled(false);	//Set to false as I don't need the table to respond to user inputs
		model=new DefaultTableModel(); 
		Object[] patronTableColumn = {"ID", "First Name", "Last Name", "Password"}; // Column Names
		Object[] patronTableRow = new Object[4]; 
		model.setColumnIdentifiers(patronTableColumn);
		patronTable.setModel(model); 
		patronTable.getTableHeader().setReorderingAllowed(false); //Stops the table from reordering
		patronTable.getTableHeader().setResizingAllowed(false); //Stops the table from changing size
		scrollPane_BookTable.setViewportView(patronTable);	//The use of a scrollPane on the JTable will allow the JTable hold an unlimited number of passengers due to the addition of a scroll bar on the JTable 
		
		for(int counter = 0; counter < Home_GUI.arrayListOfBooks.size(); counter++) {
			int patronID = Home_GUI.arrayListOfPatrons.get(counter).getID();
			String patronFirstName = Home_GUI.arrayListOfPatrons.get(counter).getFirstName();
			String patronLastName = Home_GUI.arrayListOfPatrons.get(counter).getLastName();
			String patronPassword = Home_GUI.arrayListOfPatrons.get(counter).getPassword();
				
			patronTableRow[0] = patronID;
			patronTableRow[1] = patronFirstName;
			patronTableRow[2] = patronLastName;
			patronTableRow[3] = patronPassword;
			model.addRow(patronTableRow);
		}
		
		
		
		
		
		JLabel label_ModifySelectBookID = new JLabel("Book ID");
		label_ModifySelectBookID.setBounds(420, 281, 46, 14);
		contentPane.add(label_ModifySelectBookID);
		
		JLabel label_ModifySelectBookCategory = new JLabel("Modify");
		label_ModifySelectBookCategory.setBounds(544, 281, 46, 14);
		contentPane.add(label_ModifySelectBookCategory);
		
		JLabel label_ModifyBookNewValue = new JLabel("New Value");
		label_ModifyBookNewValue.setBounds(675, 281, 59, 14);
		contentPane.add(label_ModifyBookNewValue);
		
		comboBox_ModifySelectBookID = new JComboBox();
		comboBox_ModifySelectBookID.setBounds(403, 306, 77, 22);
		contentPane.add(comboBox_ModifySelectBookID);
		BookIDModifySelector();
		
		comboBox_ModifySelectBookCategory = new JComboBox();
		comboBox_ModifySelectBookCategory.setBounds(490, 306, 140, 22);
		contentPane.add(comboBox_ModifySelectBookCategory);
		BookCategoryModifySelector();
		
		textField_ModifyBookNewValue = new JTextField();
		textField_ModifyBookNewValue.setColumns(10);
		textField_ModifyBookNewValue.setBounds(640, 307, 134, 23);
		contentPane.add(textField_ModifyBookNewValue);
		
		JButton btnModifyBookConfirm = new JButton("Confirm");
		btnModifyBookConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookID = Integer.parseInt(comboBox_ModifySelectBookID.getSelectedItem().toString());
				String bookCategory = comboBox_ModifySelectBookCategory.getSelectedItem().toString();
				String bookChangeValue = textField_ModifyBookNewValue.getSelectedText().toString();
		        try{
		            Connection con = Home_GUI.getConnection();
		            PreparedStatement statement = con.prepareStatement("UPDATE book SET "+bookCategory+"="+bookChangeValue+" WHERE bookID="+bookID);
		            ResultSet result = statement.executeQuery();
		            Home_GUI.loadBooks();
		        }
		        catch(Exception e1){
		        	System.out.println(e1);
		        }
			}
		});
		btnModifyBookConfirm.setBounds(528, 336, 77, 23);
		contentPane.add(btnModifyBookConfirm);
		

		
		
		JLabel label_AddBookTitle = new JLabel("Title");
		label_AddBookTitle.setBounds(465, 104, 59, 14);
		contentPane.add(label_AddBookTitle);
		
		JLabel label_AddBookAuthor = new JLabel("Author");
		label_AddBookAuthor.setBounds(670, 104, 53, 14);
		contentPane.add(label_AddBookAuthor);
		
		JLabel label_AddBookGenre = new JLabel("Genre");
		label_AddBookGenre.setBounds(460, 157, 46, 14);
		contentPane.add(label_AddBookGenre);
		
		textField_AddBookTitle = new JTextField();
		textField_AddBookTitle.setColumns(10);
		textField_AddBookTitle.setBounds(403, 130, 152, 23);
		contentPane.add(textField_AddBookTitle);
		
		textField_AddBookAuthor = new JTextField();
		textField_AddBookAuthor.setColumns(10);
		textField_AddBookAuthor.setBounds(604, 130, 170, 23);
		contentPane.add(textField_AddBookAuthor);
		
		textField_AddBookGenre = new JTextField();
		textField_AddBookGenre.setColumns(10);
		textField_AddBookGenre.setBounds(403, 183, 152, 23);
		contentPane.add(textField_AddBookGenre);
		
		JButton btnAddBookConfirm = new JButton("Confirm");
		btnAddBookConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = textField_AddBookTitle.getText().toString();
				String author = textField_AddBookAuthor.getText().toString();
				String genre = textField_AddBookGenre.getText().toString();
				if(title!="" && author!="" && genre!="") {
			        try{
			            Connection con = Home_GUI.getConnection();
			            PreparedStatement statement = con.prepareStatement("INSERT INTO Book (title, author, genre, available) VALUES ('"+title+"', '"+author+"' , '"+genre+"', 1");
			            ResultSet result = statement.executeQuery();
			        }
			        catch(Exception e1){
			        	System.out.println(e1);
			        }
				}
			}
		});
		btnAddBookConfirm.setBounds(604, 182, 170, 23);
		contentPane.add(btnAddBookConfirm);
		
		
		
		
		

		

	}
}
