package manager.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddToDoWindow {

	private MainWindow mainWindow;
	private ToDoPanel mainPanel;
	
	private JFrame frmAddToDo;
	private JTextField itemText;

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
		mainWindow.enableFrame(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddToDo = new JFrame();
		frmAddToDo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}
		});
		frmAddToDo.setTitle("Add To-Do List Item");
		frmAddToDo.setBounds(mainWindow.getFrame().getX() + 275, 
				mainWindow.getFrame().getY() + 150, 444, 136);
		frmAddToDo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddToDo.getContentPane().setLayout(null);
		
		itemText = new JTextField();
		itemText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					confirmButton();
				}
			}
		});
		itemText.setBounds(10, 31, 412, 26);
		frmAddToDo.getContentPane().add(itemText);
		itemText.setColumns(10);
		
		JLabel lblEnterNew = new JLabel("Enter new To Do Item:");
		lblEnterNew.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterNew.setBounds(10, 11, 165, 20);
		frmAddToDo.getContentPane().add(lblEnterNew);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmButton();
			}
		});
		btnConfirm.setBounds(329, 68, 89, 23);
		frmAddToDo.getContentPane().add(btnConfirm);
		
		frmAddToDo.setVisible(true);
	}
	
	private void close(){
		mainWindow.enableFrame(true);
		frmAddToDo.dispose();
	}
	
	private void confirmButton(){
		if(itemText.getText().length() >= 1){
			mainPanel.addItem(itemText.getText());
			close();
		}
	}
}
