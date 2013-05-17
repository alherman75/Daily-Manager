package manager.daily;

import javax.swing.JPanel;
import javax.swing.JButton;

public class TestPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		
		this.setBounds(0, 0, 605, 463);
		setLayout(null);
		JButton btnHello = new JButton("Hello");
		btnHello.setBounds(150, 112, 99, 55);
		add(btnHello);

	}

}
