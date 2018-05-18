package analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnalysisList implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static List<Analysis> list;

	public AnalysisList() {
		list = new ArrayList<>();
	}
	
	public Analysis getAnalysisAt(int index) {
		return list.get(index);
	}
	
	public void add(Analysis a) {
		List<Analysis> copy = new ArrayList<>();
		
		//add new Analysis at first position
		copy.add(a);
		
		//copy old list
		for(Analysis analysis : list) {
			copy.add(analysis);
		}
		
		//remove Analysis if list is bigger than 5 elements
		if(copy.size()>5) {
			copy.remove(5);
		}
		
		//set list to copy
		list = new ArrayList<>(copy);
		
	}

	public List<Analysis> getList() {
		return list;
	}

	public void setList(List<Analysis> list) {
		AnalysisList.list = list;
	}
		
}
