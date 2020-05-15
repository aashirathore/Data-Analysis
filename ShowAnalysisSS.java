
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

		public class ShowAnalysisSS extends JFrame implements ActionListener {
			
			private static final long serialVersionUID = 1L;
			JLabel lblHeading;
			JButton btnExit;
			Vector<DataAS> mdsList;
			JPanel pnlGrid;
			
			public ShowAnalysisSS(Vector<DataAS> mdsList) {
				setTitle("Data Analysis By Gender");
				this.mdsList = mdsList;
				setSize(800,600);
				addControls();
				setVisible(true);
				setResizable(false);
			}
			
			
		
			void addControls() {
				Container cp = this.getContentPane();
				lblHeading = new JLabel("DATA ANALYSIS By Spending Score", JLabel.CENTER);
				lblHeading.setFont(new Font("Impact", Font.BOLD,30));
				cp.add(lblHeading, "North");
				
				JPanel pnlButtonMenu = new JPanel();
				pnlButtonMenu.setLayout(new FlowLayout());
				btnExit = new JButton("Exit");		
				cp.add(pnlButtonMenu, "South");
				
				pnlGrid = new JPanel();
				pnlGrid.setLayout(new GridLayout(3,1));
				String data[][] = new String[4][3];
				String headers[] = {"Groups", "Count", "Total Annual Income (K$)"};
				
				for(int i=0;i<=3;i++) {
					data[i][0] = mdsList.get(i).getAverageSpendingScore();
					data[i][1] = mdsList.get(i).getCount()+"";
					data[i][2] = mdsList.get(i).getTotalAnnualIncome()+"";
					
				
				}
				JTable tbl = new JTable(data, headers);
				pnlGrid.add(new JScrollPane(tbl));
				
				final ShowGraphSS gpCount = new ShowGraphSS(mdsList,1," Spending Score vs Count", "Customers Count");
				final ShowGraphSS gpAI = new ShowGraphSS(mdsList,2," Spending Score vs Total Annual Income", "Annual Income ");
		        pnlGrid.add(gpCount);
		        pnlGrid.add(gpAI);
		        
		        
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




