import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class welcome extends JFrame implements ActionListener {
	Container cp;
	JButton conbutton;
	ImageIcon icon;
	JPanel center;
	JPanel south;
	public welcome() {
		super("welcome");
		this.setSize(800, 600);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension sd = tk.getScreenSize();
		int left = (sd.width-800)/2;
		int top = (sd.height-600)/2;
		this.setLocation(left, top);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addControls();
		this.setVisible(true);
		
	}

	public void addControls() {
		cp=this.getContentPane();
		conbutton = new JButton("Continue");
		center = new JPanel();
		icon = new ImageIcon("./image.png");
		JLabel iblimagecenter = new JLabel(icon);
		center.add(iblimagecenter);
	    south = new JPanel();
		south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		south.add(conbutton);
		cp.add(center,"Center");
		cp.add(south,"South");
		conbutton.addActionListener(this);

			}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			this.setVisible(false);
			new Menu();
			
		}


	
	public static void main(String[] args) {
		new welcome();
	}
	
}
