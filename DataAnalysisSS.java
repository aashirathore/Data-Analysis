	import java.util.Vector;

public class DataAnalysisSS {

		Vector<MallData> mdList;
		Vector<DataAS> mdsList;
		public DataAnalysisSS(Vector<MallData > mdList) {
			this.mdList=mdList;
				}
	public Vector<DataAS> getAnalysisSS() {
		mdsList = new Vector<DataAS>();
		int groupCount1=0 ,groupAI1=0,groupAI2=0,groupAI3=0,groupAI4=0,groupAI5=0,
				groupCount2=0,groupCount3=0,groupCount4=0,groupCount5=0;
		
		for(MallData md:mdList) {
			if(md.getSpendingScore()<=20) {
				groupCount1++;
				groupAI1+=md.getAnnualIncome();
			}
			else if(md.getSpendingScore()>20 && md.getSpendingScore()<=40) {
				groupCount2++;
				groupAI2+=md.getAnnualIncome();
				
			}
			else if(md.getSpendingScore()>40 && md.getSpendingScore()<=60) {
				groupCount3++;
				groupAI3+=md.getAnnualIncome();
				
			}
			else if(md.getSpendingScore()>60 && md.getSpendingScore()<=90) {
				groupCount4++;
				groupAI4+=md.getAnnualIncome();
			}
			
		}
		
	    DataAS d1 = new DataAS();
	    DataAS d2 = new DataAS();
	    DataAS d3 = new DataAS();
	    DataAS d4 = new DataAS();
        
	    d1.setAverageSpendingScore("group1");
	    d1.setCount(groupCount1);
	    d1.setTotalAnnualIncome(groupAI1);

	    d2.setAverageSpendingScore("group2");
	    d2.setCount(groupCount2);
	    d2.setTotalAnnualIncome(groupAI2);

	    d3.setAverageSpendingScore("group3");
	    d3.setCount(groupCount3);
	    d3.setTotalAnnualIncome(groupAI3);

	    d4.setAverageSpendingScore("group4");
	    d4.setCount(groupCount4);
	    d4.setTotalAnnualIncome(groupAI4);
		
	    mdsList.add(d1);
	    mdsList.add(d2);
	    mdsList.add(d3);
	    mdsList.add(d4);

		return mdsList;

	}
	}


