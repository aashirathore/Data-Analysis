
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

	public class ShowAAI extends JFrame implements ActionListener{
		private static final long serialVersionUID = 1L;


		JLabel lblheading;
		JButton btn;
		JPanel pnlGrid;
		Vector<DataAI> mdgList;
		 public ShowAAI(Vector<DataAI> mdgList) {
		setTitle("ANLYSIS BY Annual income");
		this.mdgList=mdgList;
		setSize(600, 800);
		addcontrols();
		setVisible(true);
	    setResizable(false);
		 }
		
		 void addcontrols() {
			Container cp = getContentPane();
		    lblheading = new JLabel("Data Analysis By Income",JLabel.CENTER);
		    lblheading.setFont(new Font("Impact", Font.ITALIC,30));
			cp.add(lblheading, "North");
			
			JPanel pnl = new JPanel();
			pnl.setLayout(new FlowLayout());
			btn = new JButton("Exit");
			cp.add(pnl,"South");
			
			pnlGrid = new JPanel();
			pnlGrid.setLayout(new GridLayout(4,1));
			String data[][] = new String[5][3];
			String headers[] = {"Groups", "Count", "Average Spending Score"};
		
			for(int i=0;i<=3;i++) {
				data[i][0] = mdgList.get(i).getGender();
				data[i][1] = mdgList.get(i).getCount()+"";
				data[i][2] = mdgList.get(i).getAverageSpendingScore()+"";
			}
			JTable tbl = new JTable(data, headers);
			pnlGrid.add(new JScrollPane(tbl));
			
			final ShowGraphAI gpCount = new ShowGraphAI(mdgList,1," Annual Income vs  Count", "Customers Count");
			final ShowGraphAI gpSS = new ShowGraphAI(mdgList,2," Annual Income vs Average Spending Score", "Avg Spending Score");
	        pnlGrid.add(gpCount);
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


