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

	public class ShowAnalysisByGender extends JFrame implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		JLabel lblHeading;
		JButton btnExit;
		Vector<DataByGender> dbgList;
		JPanel pnlGrid;
		public ShowAnalysisByGender(Vector<DataByGender> dbgList) {
			setTitle("Data Analysis By Gender");
			this.dbgList = dbgList;
			setSize(800,600);
			addControls();
			setVisible(true);
			setResizable(false);
		}
		
		void addControls() {
			Container cp = this.getContentPane();
			lblHeading = new JLabel("DATA ANALYSIS By Gender", JLabel.CENTER);
			lblHeading.setFont(new Font("Impact", Font.BOLD,30));
			cp.add(lblHeading, "North");
			
			JPanel pnlButtonMenu = new JPanel();
			pnlButtonMenu.setLayout(new FlowLayout());
			btnExit = new JButton("Exit");		
			cp.add(pnlButtonMenu, "South");
			
			pnlGrid = new JPanel();
			pnlGrid.setLayout(new GridLayout(4,1));
			String data[][] = new String[2][4];
			String headers[] = {"Gender", "Count", "Total Annual Income (K$)", "Average Spending Score"};
			String dataAI[][] = new String[2][4];
			String dataSS[][] = new String[2][4];
			for(int i=0;i<=1;i++) {
				data[i][0] = dbgList.get(i).getGender();
				data[i][1] = dbgList.get(i).getCount()+"";
				data[i][2] = dbgList.get(i).getTotalAnnualIncome()+"";
				data[i][3] = dbgList.get(i).getAverageSpendingScore()+"";
			}
			JTable tbl = new JTable(data, headers);
			pnlGrid.add(new JScrollPane(tbl));
			
			final ShowGraph gpCount = new ShowGraph(dbgList,1," Gender vs Gender Count", "Customers Count");
			final ShowGraph gpAI = new ShowGraph(dbgList,2," Gender vs Total Annual Income", "Annual Income ");
			final ShowGraph gpSS = new ShowGraph(dbgList,3," Gender vs Average Spending Score", "Avg Spending Score");
	        pnlGrid.add(gpCount);
	        pnlGrid.add(gpAI);
	        pnlGrid.add(gpSS);
	        
	        
	        cp.add(pnlGrid, "Center");
	        btnExit.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if(btn == btnExit) {
				this.setVisible(false);
			}
		}
	}


