package analysis;

/**
 * 
 * @author alex-
 * @version 1
 * 
 * Klasse die die Altergruppen unterteilt
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
