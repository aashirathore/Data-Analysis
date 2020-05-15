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

public class ShowAnalysisByAge extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;


	JLabel lblheading;
	JButton btn;
	JPanel pnlGrid;
	Vector<DataByAge> dbaList;
	 public ShowAnalysisByAge(Vector<DataByAge> dbaList) {
	setTitle("ANLYSIS BY AGE");
	this.dbaList=dbaList;
	setSize(600, 800);
	addcontrols();
	setVisible(true);
    setResizable(false);
	 }
	
	 void addcontrols() {
		Container cp = getContentPane();
	    lblheading = new JLabel("Data Analysis By Age",JLabel.CENTER);
	    lblheading.setFont(new Font("Impact", Font.ITALIC,30));
		cp.add(lblheading, "North");
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new FlowLayout());
		btn = new JButton("Exit");
		cp.add(pnl,"South");
		
		pnlGrid = new JPanel();
		pnlGrid.setLayout(new GridLayout(4,1));
		String data[][] = new String[2][4];
		String headers[] = {"Age", "Count", "Total Annual Income (K$)", "Average Spending Score"};
		
		for(int i=0;i<=1;i++) {
			data[i][0] = dbaList.get(i).getAge();
			data[i][1] = dbaList.get(i).getCount()+"";
			data[i][2] = dbaList.get(i).getTotalAnnualIncome()+"";
			data[i][3] = dbaList.get(i).getAverageSpendingScore()+"";
		}
		JTable tbl = new JTable(data, headers);
		System.out.println("hello");
		pnlGrid.add(new JScrollPane(tbl));
		
		final ShowGraphAge gpCount = new ShowGraphAge(dbaList,1," Gender vs Gender Count", "Customers Count");
		final ShowGraphAge gpAI = new ShowGraphAge(dbaList,2," Gender vs Total Annual Income", "Annual Income ");
		final ShowGraphAge gpSS = new ShowGraphAge(dbaList,3," Gender vs Average Spending Score", "Avg Spending Score");
        pnlGrid.add(gpCount);
        pnlGrid.add(gpAI);
        pnlGrid.add(gpSS);
        
        cp.add(pnlGrid, "Center");
        btn.addActionListener(this);
        
	 }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		if(btn == btn) {
			this.setVisible(false);
	}
	}
}
