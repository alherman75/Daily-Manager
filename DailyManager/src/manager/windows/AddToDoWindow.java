package manager.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class AddToDoWindow {

	private MainWindow mainWindow;
	private ToDoPanel mainPanel;
	
	private JFrame frmAddToDo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddToDoWindow window = new AddToDoWindow();
					window.frmAddToDo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddToDoWindow() {
		initialize();
	}
	
	public AddToDoWindow(MainWindow window, ToDoPanel panel){
		mainWindow = window;
		mainPanel = panel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddToDo = new JFrame();
		frmAddToDo.setTitle("Add To-Do List Item");
		frmAddToDo.setBounds(100, 100, 444, 136);
		frmAddToDo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddToDo.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 31, 412, 26);
		frmAddToDo.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterNew = new JLabel("Enter new To Do Item:");
		lblEnterNew.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterNew.setBounds(10, 11, 165, 20);
		frmAddToDo.getContentPane().add(lblEnterNew);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(329, 68, 89, 23);
		frmAddToDo.getContentPane().add(btnConfirm);
	}
}
