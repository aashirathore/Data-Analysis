import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowByKMeans extends JFrame implements ActionListener{

	JLabel Heading;
	JButton btnExit;
	Vector<KMeans> kmc;
	JPanel pnlGrid;
	String headers[], data[][];
	public ShowByKMeans(String headers[], String data[][]) {
		setTitle("Data Analysis By Gender");
		this.headers = headers;
		this.data =data;
		//this.kmc=kmc;
		setSize(800,600);
		addControls();
		setVisible(true);
	}

	private void addControls() {
		Container cp = getContentPane();
		
	    Heading = new JLabel("Data Analysis By K-Mean ",JLabel.CENTER);
	    Heading.setFont(new Font("Impact", Font.ITALIC,30));
		cp.add(Heading, "North");

		JPanel pnl = new JPanel();
		pnl.setLayout(new FlowLayout());
		btnExit = new JButton("Exit");
		cp.add(pnl,"South");

		pnlGrid = new JPanel();
		pnlGrid.setLayout(new GridLayout(4,1));
		JTable jTable1 = new JTable(data, headers);
		
		pnlGrid.add(new JScrollPane(jTable1));
		cp.add(pnlGrid,"Center");
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == btnExit) {
			this.setVisible(false);
		
	}

	}
}
