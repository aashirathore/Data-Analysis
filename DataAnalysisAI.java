import java.util.Vector;

public class DataAnalysisAI {
	Vector<MallData> mdList;
	Vector<DataAI> mdgList;
	public DataAnalysisAI(Vector<MallData > mdList) {
		this.mdList=mdList;
			}
public Vector<DataAI> getAnalysisAI() {
	mdgList = new Vector<DataAI>();
	int groupCount1=0 ,groupSS1=0,groupSS2=0,groupSS3=0,groupSS4=0,groupSS5=0,
			groupCount2=0,groupCount3=0,groupCount4=0,groupCount5=0;
	
	for(MallData md:mdList) {
		if(md.getAnnualIncome()<=30) {
			groupCount1++;
			groupSS1+=md.getSpendingScore();
		}
		else if(md.getAnnualIncome()>30&&md.getAnnualIncome()<=60) {
			groupCount2++;
			groupSS2+=md.getSpendingScore();
			
		}
		else if(md.getAnnualIncome()>60 && md.getAnnualIncome()<=90) {
			groupCount3++;
			groupSS3+=md.getSpendingScore();
			
		}
		else if(md.getAnnualIncome()>90 && md.getAnnualIncome()<=130) {
			groupCount4++;
			groupSS4+=md.getSpendingScore();
		}
		else {
			groupCount5++;
			groupCount5+=md.getSpendingScore();
		}
			
	}
	DataAI dg = new DataAI();
	DataAI dg2 = new DataAI();
	DataAI dg3 = new DataAI();
	DataAI dg4 = new DataAI();
	DataAI dg5 = new DataAI();
     dg.setGender("Group1");
	dg.setCount(groupCount1);
	dg.setAverageSpendingScore(groupSS1/groupCount1);
    dg2.setGender("Group2");
	dg2.setCount(groupCount2);
	dg2.setAverageSpendingScore(groupSS2/groupCount2);
    dg3.setGender("Group3");
	dg3.setCount(groupCount3);
	dg3.setAverageSpendingScore(groupSS3/groupCount3);
    dg4.setGender("Group4");
	dg4.setCount(groupCount4);
	dg4.setAverageSpendingScore(groupSS4/groupCount4);
    dg5.setGender("Group5");
	dg5.setCount(groupCount5);
	dg5.setAverageSpendingScore(groupSS5/groupCount5);
	
	mdgList.add(dg);
	mdgList.add(dg2);
	mdgList.add(dg3);
	mdgList.add(dg4);
	mdgList.add(dg5);
	
	return mdgList;

}
}
