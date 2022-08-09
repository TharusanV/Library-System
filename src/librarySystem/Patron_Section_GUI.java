package librarySystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Patron_Section_GUI extends JFrame {

	private JPanel contentPane;
	
	private JTable bookTable;
	private JTable patronBookTable;
	
	DefaultTableModel model;
	private JLabel label_AllBooksTable;
	private JLabel label_WelcomePatron;
	private JLabel label_YourBooks;
	private JScrollPane scrollPane_PatronBookTable;
	private JComboBox comboBox_BorrowBookSelector;
	private JLabel label_SelectTheBook;
	private JLabel label_SelectTheBook_2;
	private JComboBox comboBox_ReturnBookSelector;
	private JButton btnConfirmBorrow;
	private JButton btnConfirmReturn;

	public void BorrowBookSelector() {
		comboBox_BorrowBookSelector.removeAllItems();
		for(int i = 0; i< Home_GUI.arrayListOfBooks.size() ; i++) {
			if(Home_GUI.arrayListOfBooks.get(i).getBookAvailable() == true) {
				comboBox_BorrowBookSelector.addItem("" + (Home_GUI.arrayListOfBooks.get(i).getBookID())); 
			}	
		}
	}
	
	public void returnBookSelector() {
		comboBox_ReturnBookSelector.removeAllItems();
		for(int i = 0; i< Home_GUI.arrayListOfBooks.size() ; i++) {
			if(Home_GUI.arrayListOfBooks.get(i).getBookUserID() == Home_GUI.arrayListContainingCurrentPatron.get(0).getID()) {
				comboBox_ReturnBookSelector.addItem("" + (Home_GUI.arrayListOfBooks.get(i).getBookID())); 
			}	
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Patron_Section_GUI frame = new Patron_Section_GUI();
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
	public Patron_Section_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_BookTable = new JScrollPane();
		scrollPane_BookTable.setBounds(26, 119, 719, 185);
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
		
		
		
		label_AllBooksTable = new JLabel("Table of all the books:");
		label_AllBooksTable.setFont(new Font("Arial", Font.BOLD, 12));
		label_AllBooksTable.setBounds(53, 96, 140, 14);
		contentPane.add(label_AllBooksTable);
		
		label_WelcomePatron = new JLabel("Welcome " + Home_GUI.arrayListContainingCurrentPatron.get(0).getFirstName());
		label_WelcomePatron.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_WelcomePatron.setBounds(264, 21, 269, 33);
		contentPane.add(label_WelcomePatron);
		
		label_YourBooks = new JLabel("Your books:");
		label_YourBooks.setFont(new Font("Arial", Font.BOLD, 12));
		label_YourBooks.setBounds(53, 340, 89, 14);
		contentPane.add(label_YourBooks);
		
		
		
		
		
		
		scrollPane_PatronBookTable = new JScrollPane();
		scrollPane_PatronBookTable.setBounds(26, 365, 377, 143);
		contentPane.add(scrollPane_PatronBookTable);
		
		//For the Books table 
		patronBookTable = new JTable();	
		patronBookTable.setEnabled(false);	//Set to false as I don't need the table to respond to user inputs
		model=new DefaultTableModel(); 
		Object[] patronBookTableColumn = {"ID", "Name", "Author", "Genre"}; // Column Names
		Object[] patronBookTableRow = new Object[4]; 
		model.setColumnIdentifiers(patronBookTableColumn);
		patronBookTable.setModel(model); 
		patronBookTable.getTableHeader().setReorderingAllowed(false); //Stops the table from reordering
		patronBookTable.getTableHeader().setResizingAllowed(false); //Stops the table from changing size
		scrollPane_BookTable.setViewportView(patronBookTable);	//The use of a scrollPane on the JTable will allow the JTable hold an unlimited number of passengers due to the addition of a scroll bar on the JTable 
		
		for(int counter = 0; counter < Home_GUI.arrayListOfBooks.size(); counter++) {
			if(Home_GUI.arrayListOfBooks.get(counter).getBookUserID() == Home_GUI.arrayListContainingCurrentPatron.get(0).getID()) {
				int bookID = Home_GUI.arrayListOfBooks.get(counter).getBookID();
				String bookTitle = Home_GUI.arrayListOfBooks.get(counter).getBookName();
				String bookAuthor = Home_GUI.arrayListOfBooks.get(counter).getBookAuthor();
				String bookGenre = Home_GUI.arrayListOfBooks.get(counter).getBookGenre();
				
				patronBookTableRow[0] = bookID;
				patronBookTableRow[1] = bookTitle;
				patronBookTableRow[2] = bookAuthor;
				patronBookTableRow[3] = bookGenre;
				model.addRow(patronBookTableRow);
			}
		}
		
		
		
		
		
		
		comboBox_BorrowBookSelector = new JComboBox();
		comboBox_BorrowBookSelector.setBounds(448, 395, 184, 22);
		contentPane.add(comboBox_BorrowBookSelector);
		BorrowBookSelector();

		
		label_SelectTheBook = new JLabel("Select the book you want to borrow:");
		label_SelectTheBook.setFont(new Font("Arial", Font.BOLD, 11));
		label_SelectTheBook.setBounds(479, 370, 206, 14);
		contentPane.add(label_SelectTheBook);
		
		label_SelectTheBook_2 = new JLabel("Select the book you want to return:");
		label_SelectTheBook_2.setFont(new Font("Arial", Font.BOLD, 11));
		label_SelectTheBook_2.setBounds(483, 441, 200, 14);
		contentPane.add(label_SelectTheBook_2);
		
		comboBox_ReturnBookSelector = new JComboBox();
		comboBox_ReturnBookSelector.setBounds(448, 466, 184, 22);
		contentPane.add(comboBox_ReturnBookSelector);
		returnBookSelector();
		
		btnConfirmBorrow = new JButton("Confirm");
		btnConfirmBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = comboBox_BorrowBookSelector.getSelectedItem().toString();
				for(int counter = 0; counter < Home_GUI.arrayListOfBooks.size(); counter++) {
					if(Integer.parseInt(value) ==  Home_GUI.arrayListOfBooks.get(counter).getBookID()) {
				        try{
				            Connection con = Home_GUI.getConnection();
				            PreparedStatement statement = con.prepareStatement("UPDATE book SET available=0, userID="+Home_GUI.arrayListContainingCurrentPatron.get(0).getID()+" WHERE bookID="+Home_GUI.arrayListOfBooks.get(counter).getBookID());
				            ResultSet result = statement.executeQuery();
				            Home_GUI.loadBooks();
				        }
				        catch(Exception e1){
				        	System.out.println(e1);
				        }
					}
				}
			}
		});
		btnConfirmBorrow.setBounds(642, 395, 89, 23);
		contentPane.add(btnConfirmBorrow);
		
		btnConfirmReturn = new JButton("Confirm");
		btnConfirmReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = comboBox_ReturnBookSelector.getSelectedItem().toString();
				for(int counter = 0; counter < Home_GUI.arrayListOfBooks.size(); counter++) {
					if(Integer.parseInt(value) ==  Home_GUI.arrayListOfBooks.get(counter).getBookID()) {
				        try{
				            Connection con = Home_GUI.getConnection();
				            PreparedStatement statement = con.prepareStatement("UPDATE book SET userID=null, available=1 WHERE bookID="+Home_GUI.arrayListOfBooks.get(counter).getBookID());
				            ResultSet result = statement.executeQuery();
				            Home_GUI.loadBooks();
				        }
				        catch(Exception e1){
				        	System.out.println(e1);
				        }
					}
				}
			}
		});
		btnConfirmReturn.setBounds(642, 466, 89, 23);
		contentPane.add(btnConfirmReturn);
		
		

		
		
	}
}
