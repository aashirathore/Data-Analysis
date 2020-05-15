import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KMeans {

	Vector<MallData> mdList;
	 Vector<MallData> vecCluster1,vecCluster2,vecCluster3,vecCluster4;

		public KMeans(Vector<MallData> mdList) {
		this.mdList = mdList;		
		
	}
	//sort a records of mall
	void sortList() {
		for(int i=mdList.size()-2;i>=0;i--) {
			for(int j=0;j<=i;j++) {
				MallData md1 = mdList.get(j);
				MallData md2 = mdList.get(j+1);
				if(md1.getAge()>md2.getAge()) {
					mdList.set(j, md2);
					mdList.set(j+1, md1);
				}
			}
		}
	}
	
	int getCentroid(int clusterId) {
		int centroid = 0;
		int totalAge=0;
		clusterId = (clusterId-1) * 50;
		/*
		 	1 -> (1-1)*50 -> 0; 1+49 -> 50
		 	2 -> (2-1)*50 -> 50; 50+49 -> 99
		 	3 -> (3-1)*50 -> 100; 100 + 49 -> 149
		 	4 -> (4-1)*50 -> 150; 150 + 49 -> 199
		 */
		for(int i=clusterId;i<clusterId+49;i++) {
			totalAge += mdList.get(i).getAge();
		}
		centroid = totalAge/50;
		return centroid;
	}
	
	int getX(int clusterId) {
		clusterId = (clusterId-1) * 50;
		int smallestAI=mdList.get(clusterId).getAnnualIncome();
		for(int i=clusterId;i<clusterId+49;i++) {
			if(smallestAI > mdList.get(i).getAnnualIncome()) {
				smallestAI = mdList.get(i).getAnnualIncome();
			}
		}
		return smallestAI;
	}
	
	int getY(int clusterId) {
		clusterId = (clusterId-1) * 50;
		int smallestSS=mdList.get(clusterId).getSpendingScore();
		for(int i=clusterId;i<clusterId+49;i++) {
			if(smallestSS > mdList.get(i).getSpendingScore()) {
				smallestSS = mdList.get(i).getSpendingScore();
			}
		}
		return smallestSS;
	}
	
	void applyKmeansAlgorithm() {
		this.vecCluster1 = new Vector<MallData>();
		this.vecCluster2 = new Vector<MallData>();
		this.vecCluster3 = new Vector<MallData>();
		this.vecCluster4 = new Vector<MallData>();
		sortList();
		int centroid1 = getCentroid(1);
		int centroid2 = getCentroid(2);
		int centroid3 = getCentroid(3);
		int centroid4 = getCentroid(4);
		/*
		 * System.out.println("Centroid 1 :"+centroid1);
		 * System.out.println("Centroid 2 :"+centroid1);
		 * System.out.println("Centroid 3 :"+centroid1);
		 * System.out.println("Centroid 4 :"+centroid1);
		 */
		int coords[] = { this.getX(1),this.getY(1),this.getX(2),this.getY(2),this.getX(3),this.getY(3),this.getX(4),this.getY(4)};
		System.out.println(coords.length);
		for(int i=0;i<coords.length;i++) {
			System.out.println("Coords :"+coords[i]);
		}
		for(MallData md: mdList) {
			MallData md1 = new MallData();
			md1.setCustomerId(md.getCustomerId());
			md1.setGender(md.getGender());
			md1.setAge(md.getAge());
			md1.setAnnualIncome(md.getAnnualIncome());
			md1.setSpendingScore(md.getSpendingScore());
			int x = md1.getAnnualIncome();
			int y = md1.getSpendingScore();
			//ED = SQRT( (x2-x1)^2 + (y2-y1)^2 )
			double distance1 = Math.sqrt((x-coords[0])*(x-coords[0])+(y-coords[1])*(y-coords[1]));
			double distance2 = Math.sqrt((x-coords[2])*(x-coords[2])+(y-coords[3])*(y-coords[3]));
			double distance3 = Math.sqrt((x-coords[4])*(x-coords[4])+(y-coords[5])*(y-coords[5]));
			double distance4 = Math.sqrt((x-coords[6])*(x-coords[6])+(y-coords[7])*(y-coords[7]));
			double darr[] = {distance1, distance2, distance3, distance4};
			System.out.println(distance1+"::"+distance2+"::"+distance3+"::"+distance4);
			Arrays.sort(darr);
			if(darr[0] == distance1) {
				vecCluster1.add(md1);
				coords[0] = Math.abs((coords[0] + md1.getAnnualIncome())/2);
				coords[1] = Math.abs((coords[1] + md1.getSpendingScore())/2);
			}
			else if(darr[0] == distance2) {
				vecCluster2.add(md1);
				coords[2] = Math.abs((coords[2] + md1.getAnnualIncome())/2);
				coords[3] = Math.abs((coords[3] + md1.getSpendingScore())/2);
			}
			else if(darr[0] == distance3) {
				vecCluster3.add(md1);
				coords[4] = Math.abs((coords[4] + md1.getAnnualIncome())/2);
				coords[5] = Math.abs((coords[5] + md1.getSpendingScore())/2);
			}
			else if(darr[0] == distance4) {
				vecCluster4.add(md1);
				coords[6] = Math.abs((coords[6] + md1.getAnnualIncome())/2);
				coords[7] = Math.abs((coords[7] + md1.getSpendingScore())/2);
			}
		}
	

		/*
		 * System.out.println("Size of Cluser 1:"+vecCluster1.size());
		 * System.out.println("Size of Cluser 2:"+vecCluster2.size());
		 * System.out.println("Size of Cluser 3:"+vecCluster3.size());
		 * System.out.println("Size of Cluser 4:"+vecCluster4.size());
		 */
		//apply 10 iterations or no change for stopping
		//no of  iteration
		String test = JOptionPane.showInputDialog("Please Enter a Iteration :");
		int a = Integer.parseInt(test);
		String header[] = {"Iteration", "Cluster Size - 1", "Cluster Size - 2", "Cluster Size - 3", "Cluster Size - 4"};
		String data[][] = new String[a][5];
		for(int i=1;i<=a;i++) {
			boolean f1 = this.iterations(1, coords);
			boolean f2 = this.iterations(2, coords);
			boolean f3 = this.iterations(3, coords);
			boolean f4 = this.iterations(4, coords);
			/*
			 * System.out.println("After Iteration No : "+i);
			 * System.out.println("Size of Cluser 1:"+vecCluster1.size());
			 * System.out.println("Size of Cluser 2:"+vecCluster2.size());
			 * System.out.println("Size of Cluser 3:"+vecCluster3.size());
			 * System.out.println("Size of Cluser 4:"+vecCluster4.size());
			 */
			data[i-1][0] = i+"";
			data[i-1][1] = vecCluster1.size()+"";
			data[i-1][2] = vecCluster2.size()+"";
			data[i-1][3] = vecCluster3.size()+"";
			data[i-1][4] = vecCluster4.size()+"";
			
			if(f1 == false && f2 == false && f3 == false && f4 == false) {
				break;
			}
			
		}
		new ShowByKMeans(header, data);
	}
	
	boolean iterations(int wfc, int coords[]) {
		boolean flag = false;
		
		Vector<MallData> vecCluser = null;
		if(wfc == 1) {
			vecCluser = this.vecCluster1;
		}
		else if(wfc == 2) {
			vecCluser = this.vecCluster2;
		}
		else if(wfc == 3) {
			vecCluser = this.vecCluster3;
		}
		else if(wfc == 4) {
			vecCluser = this.vecCluster4;
		}
		Vector<MallData> vecTemp = new Vector<MallData>();
		for(MallData md: vecCluser) {
			int x = md.getAnnualIncome();
			int y = md.getSpendingScore();
			//ED = SQRT( (x2-x1)^2 + (y2-y1)^2 )
			double distance1 = Math.sqrt((x-coords[0])*(x-coords[0])+(y-coords[1])*(y-coords[1]));
			double distance2 = Math.sqrt((x-coords[2])*(x-coords[2])+(y-coords[3])*(y-coords[3]));
			double distance3 = Math.sqrt((x-coords[4])*(x-coords[4])+(y-coords[5])*(y-coords[5]));
			double distance4 = Math.sqrt((x-coords[6])*(x-coords[6])+(y-coords[7])*(y-coords[7]));
			double darr[] = {distance1, distance2, distance3, distance4};
			Arrays.sort(darr);
			//System.out.println(coords[0]+", "+coords[1]+", "+coords[2]+", "+coords[3]+", "+coords[4]+", "+coords[5]+", "+coords[6]+", "+coords[7]);
			if(darr[0] == distance1 && wfc!=1) {
				//System.out.println("PACE");
				vecCluster1.add(md);
				coords[0] = Math.abs((coords[0] + md.getAnnualIncome())/2);
				coords[1] = Math.abs((coords[1] + md.getSpendingScore())/2);
				coords[(wfc-1)*2] = Math.abs((coords[(wfc-1)*2] - md.getAnnualIncome())/2);
				coords[(wfc-1)*2+1] = Math.abs((coords[(wfc-1)*2+1] - md.getAnnualIncome())/2);
				vecTemp.add(md);
				flag = true;
			}
			else if(darr[0] == distance2 && wfc!=2) {
				//System.out.println("PACE2");
				vecCluster2.add(md);
				coords[2] = Math.abs((coords[2] + md.getAnnualIncome())/2);
				coords[3] = Math.abs((coords[3] + md.getSpendingScore())/2);
				coords[(wfc-1)*2] = Math.abs((coords[(wfc-1)*2] - md.getAnnualIncome())/2);
				coords[(wfc-1)*2+1] = Math.abs((coords[(wfc-1)*2+1] - md.getAnnualIncome())/2);
				vecTemp.add(md);
				flag = true;
			}
			else if(darr[0] == distance3 && wfc!=3) {
				//System.out.println("PACE3");
				vecCluster3.add(md);
				coords[4] = Math.abs((coords[4] + md.getAnnualIncome())/2);
				coords[5] = Math.abs((coords[5] + md.getSpendingScore())/2);
				coords[(wfc-1)*2] = Math.abs((coords[(wfc-1)*2] - md.getAnnualIncome())/2);
				coords[(wfc-1)*2+1] = Math.abs((coords[(wfc-1)*2+1] - md.getAnnualIncome())/2);
				vecTemp.add(md);
				flag = true;
			}
			else if(darr[0] == distance4 && wfc!=4) {
				//System.out.println("PACE4");
				vecCluster4.add(md);
				coords[6] = Math.abs((coords[6] + md.getAnnualIncome())/2);
				coords[7] = Math.abs((coords[7] + md.getSpendingScore())/2);
				coords[(wfc-1)*2] = Math.abs((coords[(wfc-1)*2] - md.getAnnualIncome())/2);
				coords[(wfc-1)*2+1] = Math.abs((coords[(wfc-1)*2+1] - md.getAnnualIncome())/2);
				vecTemp.add(md);
				flag = true;
			}
			//vecCluser.remove(md);
		}
		for(MallData md:vecTemp) {
			vecCluser.remove(md);
		}
		return flag;
	}
	
	
}
