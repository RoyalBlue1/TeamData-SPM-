package analysis;

/**
 * In dieser Klasse werden die Kunden in Altersgruppen unterteilt
 * 
 * @author Kai Blokker
 */

public class AgeGroup implements Comparable<AgeGroup>{
	
	String name;
	int count;
	
	public AgeGroup(String name, int count) {
		this.name = name;
		this.count = count;
	}

	@Override
	public int compareTo(AgeGroup o) {
		if(count > o.count) {
			return -1;
		}else if(count < o.count){
			return 1;
		}
		return 0;
	}


}
