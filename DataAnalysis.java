import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JOptionPane;

	public class DataAnalysis {
		
		Vector<MallData> mdList;
		Vector<DataByGender> dbgList;
		public DataAnalysis(Vector<MallData> mdList) {
			this.mdList = mdList;
		}
		Vector<DataByGender> getGenderAnalysis() {
			dbgList = new Vector<DataByGender>();
			int countMale=0, countFemale=0, totalAIMale=0, totalAIFemale=0, averageSSMale=0, averageSSFemale=0;
			for(MallData md: mdList) {
				if(md.getGender().toUpperCase().equals("MALE")) {
					countMale++;
					totalAIMale+= md.getAnnualIncome();
					averageSSMale += md.getSpendingScore();
				}
				else {
					countFemale++;
					totalAIFemale+= md.getAnnualIncome();
					averageSSFemale += md.getSpendingScore();
				}
			}
			System.out.println("Male Count = "+countMale);
			System.out.println("Female Count = "+countFemale);
			DataByGender dbgMale = new DataByGender();
			DataByGender dbgFemale = new DataByGender();
			dbgMale.setGender("Male");
			dbgMale.setCount(countMale);
			dbgMale.setTotalAnnualIncome(totalAIMale);
			dbgMale.setAverageSpendingScore(averageSSMale/countMale);
			dbgFemale.setGender("Female");
			dbgFemale.setCount(countFemale);
			dbgFemale.setTotalAnnualIncome(totalAIFemale);
			dbgFemale.setAverageSpendingScore(averageSSFemale/countFemale);
			
			dbgList.add(dbgMale);
			dbgList.add(dbgFemale);
			return dbgList;
		}
		
		
	}


