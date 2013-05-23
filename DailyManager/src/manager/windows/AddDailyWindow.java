package manager.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddDailyWindow {

	private JFrame frmAddNewDaily;
	private JTextField txtTaskEntry;
	private MainWindow mainWindow;
	private DailyPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					AddDailyWindow window = new AddDailyWindow();
//					window.frmAddNewDaily.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddDailyWindow() {
		initialize();
	}

//	public AddDailyWindow(MainDailyWindow window) {
//		mainWindow = window;
//		mainWindow.enableFrame(false);
//		initialize();
//		txtTaskEntry.grabFocus();
//	}
	
	public AddDailyWindow(DailyPanel panel){
		mainPanel = panel;
		mainPanel.setEnabled(false);
		initialize();
		txtTaskEntry.grabFocus();
	}
	
	public AddDailyWindow(MainWindow window, DailyPanel panel){
		mainWindow = window;
		mainPanel = panel;
		mainWindow.enableFrame(false);
		initialize();
		txtTaskEntry.grabFocus();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddNewDaily = new JFrame();
		frmAddNewDaily.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		frmAddNewDaily.setTitle("Add New Daily Task");
		frmAddNewDaily.setBounds(mainWindow.getFrame().getX() + 275, 
				mainWindow.getFrame().getY() + 150, 385, 121);
		frmAddNewDaily.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddNewDaily.getContentPane().setLayout(
				new MigLayout("", "[326px][]", "[20px][][][]"));

		JLabel lblTaskEntry = new JLabel("Enter Task Description:");
		frmAddNewDaily.getContentPane().add(lblTaskEntry, "cell 0 0");
		frmAddNewDaily.setFocusable(true);
		
		txtTaskEntry = new JTextField();
		txtTaskEntry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					confirmButton();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					confirmButton();
				}
			}
		});
		frmAddNewDaily.getContentPane().add(txtTaskEntry,
				"cell 0 1 2 1,alignx left,aligny top");
		txtTaskEntry.setColumns(45);
		txtTaskEntry.grabFocus();

		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmButton();
			}
		});
		frmAddNewDaily.getContentPane().add(btnNewButton,
				"cell 1 2,alignx right,aligny top");
		frmAddNewDaily.setVisible(true);
	}

	private void confirmButton() {
		if (txtTaskEntry.getText().length() > 0) {
			mainPanel.addDaily(txtTaskEntry.getText());
			exitWindow();
		}
	}
	private void exitWindow(){
		mainWindow.enableFrame(true);
		frmAddNewDaily.dispose();
	}
}
