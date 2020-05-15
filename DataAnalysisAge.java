import java.util.Vector;

public class DataAnalysisAge {
	Vector<MallData> mdList;
	Vector<DataByAge> dbaList;
	public DataAnalysisAge(Vector<MallData> mdList) {
		this.mdList = mdList;
	}
	
	public Vector<DataByAge> getAgeAnalysis() {
		dbaList = new Vector<DataByAge>();
		int Adultcount=0,AdultAI=0,AdultSScore=0,SeniorAdultcount=0,SeniorAdultAI=0,SeniorSScore=0;
		for(MallData md:mdList) {
			if(md.getAge()==19||md.getAge()<=35) {
				Adultcount++;
				AdultAI+=md.getAnnualIncome();
				AdultSScore+=md.getSpendingScore();
			}
			else {
				SeniorAdultcount++;
				SeniorAdultAI+=md.getAnnualIncome();
				SeniorSScore+=md.getSpendingScore();
			}
		}
		
		DataByAge dba = new DataByAge();
		DataByAge dbs = new DataByAge();
		dba.setAge("Adult");
		dba.setCount(Adultcount);
		dba.setTotalAnnualIncome(AdultAI);
		dba.setAverageSpendingScore(AdultSScore/Adultcount);
		dbs.setAge("Senior");
		dbs.setCount(SeniorAdultcount);
        dbs.setTotalAnnualIncome(SeniorAdultAI);
	    dbs.setAverageSpendingScore(SeniorSScore/SeniorAdultcount);
	    
	    dbaList.add(dba);
	    dbaList.add(dbs);
	    return dbaList;
	}
}
