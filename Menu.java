import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.mysql.cj.xdevapi.Table;
import org.jfree.ui.RefineryUtilities;
public class Menu extends JFrame implements ActionListener {
	JLabel lblHeading , jl;
	JButton btnLoadData,btnViewData,btnAnalysisBYAge,btnAnalysisBYGender,btnAnalysisByAnnualIncome,
	btnAnalysisBYSpendingScore,btnExite ,btnSave,Kmeanbtn;
	ImageIcon icon;
	JPanel center;
	JTextArea jtaDetails;
	Vector<MallData> mdList;

	public Menu() {
		setTitle("Analaysis");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addcontrols();
		setVisible(true);
		setResizable(false);
	}

	 void addcontrols() {
		 Container cp = this.getContentPane();
		 lblHeading = new JLabel("Data Analysis",JLabel.CENTER);
		 lblHeading.setFont(new Font("Impact",Font.BOLD,30));
		ImageIcon ii = new ImageIcon("images.png");
		Image Image = ii.getImage();
		cp.add(lblHeading,"North");
		
		JPanel pntButtonMenu = new JPanel();
		BoxLayout bl = new BoxLayout(pntButtonMenu, BoxLayout.Y_AXIS);
		pntButtonMenu.setLayout(bl);
		
		btnLoadData = new JButton("Load Data");
		btnViewData = new JButton("view Data");
		btnAnalysisBYGender = new JButton("Analysis BY Gender");
		btnAnalysisBYAge = new JButton("Analysis BY Age");
		btnAnalysisBYSpendingScore = new JButton("Analysis BY Spending Score");
		btnAnalysisByAnnualIncome = new JButton("Analysis BY Annual Income");
		Kmeanbtn = new JButton("Analysis BY K-mean");
		btnExite = new JButton("Exite");
		
		btnLoadData.setMaximumSize(new Dimension(200,500));
		btnViewData.setMaximumSize(new Dimension(200,500));
		btnAnalysisBYAge.setMaximumSize(new Dimension(200,500));
		btnAnalysisByAnnualIncome.setMaximumSize(new Dimension(200,500));
		btnAnalysisBYSpendingScore.setMaximumSize(new Dimension(200,500));
		btnAnalysisBYGender.setMaximumSize(new Dimension(200,500));
		btnViewData.setMaximumSize(new Dimension(200,500));
		Kmeanbtn.setMaximumSize(new Dimension(200,500));
		btnExite.setMaximumSize(new Dimension(200,500));

		
		pntButtonMenu.add(btnLoadData);
		pntButtonMenu.add(btnViewData);
		pntButtonMenu.add(btnAnalysisBYAge);
		pntButtonMenu.add(btnAnalysisByAnnualIncome);
		pntButtonMenu.add(btnAnalysisBYGender);
		pntButtonMenu.add(btnAnalysisBYSpendingScore);
		pntButtonMenu.add(Kmeanbtn);
		pntButtonMenu.add(btnExite);

		cp.add(pntButtonMenu,"West");
		JPanel pnlcenter = new JPanel();

		cp.add(pnlcenter,"East");
		/*
		 * jl.setIcon(new ImageIcon("image.png")); pnlcenter.add(jl); add(jl);
		 * validate(); Image background =
		 * Toolkit.getDefaultToolkit().createImage("image.png");
		 */
		jtaDetails = new JTextArea("Selected File Name:",10,40);
		pnlcenter.add(new JScrollPane(jtaDetails));
		cp.add(pnlcenter,"Center");
		btnLoadData.addActionListener(this);
		btnViewData.addActionListener(this);
		btnAnalysisBYAge.addActionListener(this);
		btnAnalysisByAnnualIncome.addActionListener(this);
		btnExite.addActionListener(this);
		btnAnalysisBYGender.addActionListener(this);
		btnAnalysisBYSpendingScore.addActionListener(this);
		Kmeanbtn.addActionListener(this);
	}
	 @Override
	 public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			if(btn==btnLoadData) {
			JFileChooser jfc = new JFileChooser();
			int ans = jfc.showOpenDialog(jfc);
			if(ans==JFileChooser.APPROVE_OPTION) {
				File f = jfc.getSelectedFile();
				jtaDetails.setText(f.getAbsolutePath());
				FileReader fr;
				try {
					fr= new FileReader(f);
					BufferedReader br= new BufferedReader(fr);

					String str = "";
					Vector<String> q = new Vector<String>();
					while(true) {
						String line = br.readLine();
						if(line==null) 
							break;
						str+=line+"\n";
						String arr[]=line.split(",");
						String query = "insert into mall (Gender, Age, AnnualIncome, SpendingScore) values ('"+arr[1]+"',"+arr[2]+","+arr[3]+","+arr[4]+")";	
						q.add(query);
						
					}
					fr.close();
					jtaDetails.setText(f.getAbsolutePath()+"\n"+str);
					 try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_analysis","root","tiger");
							Statement st= conn.createStatement();
							for(int i=1; i<q.size();i++ ) {
								
							    st.executeUpdate(q.get(i));  

							}
							conn.close();
						 }
						 catch (SQLException e2) {  
						        JOptionPane.showMessageDialog(null, e2);  
						 
						 } catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
					 
				}
				catch(IOException e1) {
					e1.printStackTrace();
				}
			}
		}
			else if(btn==btnViewData) {
			JTable showdata;
			String show[] = {"customerno", "Gender","Age","AnnualIncome","SpendingSore"};
			String data[][] = null;
			try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_analysis","root","tiger");
			Statement st= conn.createStatement();
			Vector<String> vec = new Vector<String>();
		
				ResultSet rs= st.executeQuery("select * from mall");
				while(rs.next()) {
					String s = rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5);
					vec.add(s);
				}
				rs.close();
				data = new String[vec.size()][5];
				int index=0;
				Iterator itr = vec.iterator();
				mdList = new Vector<MallData>();
				while(itr.hasNext()) {
					String str = itr.next()+"";
					String []arr = str.split(",");
					data[index][0] = arr[0];
					data[index][1] = arr[1];
					data[index][2] = arr[2];
					data[index][3] = arr[3];
					data[index][4] = arr[4];
					MallData md = new MallData();
					md.setCustomerId(Integer.parseInt(arr[0]));
					md.setGender(arr[1]);
					md.setAge(Integer.parseInt(arr[2]));
					md.setAnnualIncome(Integer.parseInt(arr[3]));
					md.setSpendingScore(Integer.parseInt(arr[4]));
					mdList.add(md);
					index++;
				}
			}
			catch(Exception s ) {
				System.out.println(""+s);
			}
				showdata = new JTable(data, show);
				setSize(800,600);
				this.getContentPane().add(new JScrollPane(showdata),"Center");
				setVisible(true);
	 }
			else if(btn==btnAnalysisBYAge) {
				DataAnalysisAge daa = new DataAnalysisAge(mdList);
				Vector<DataByAge> dbaList = daa.getAgeAnalysis();
				new ShowAnalysisByAge(dbaList);
			}
			else if(btn==btnAnalysisBYGender) {
				DataAnalysis da= new DataAnalysis(mdList);
				Vector<DataByGender> dbgList = da.getGenderAnalysis();
				new ShowAnalysisByGender(dbgList);
			}
			else if(btn==btnAnalysisBYSpendingScore) {
             DataAnalysisSS ss = new DataAnalysisSS(mdList);
             Vector<DataAS> mdsList =  ss.getAnalysisSS();
            new ShowAnalysisSS(mdsList);
			}
			else if(btn==btnAnalysisByAnnualIncome) {
				DataAnalysisAI aa = new DataAnalysisAI(mdList);
				Vector<DataAI> mdgList = aa.getAnalysisAI();
				new ShowAAI(mdgList);
			}
			else if(btn==Kmeanbtn) {
				KMeans km = new KMeans(mdList);
				km.applyKmeansAlgorithm();

			}  
			else if(btn==btnExite) {
				System.exit(0);
			}  

		
	 }
	
	 public static void main(String[] args) {
	 
new Menu();
	}

}
	

