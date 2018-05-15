package analysis;

public class Item implements Comparable<Item>{

	String name;
	int anzahl;
	
	public Item(String name, int n) {
		
		String[] s = name.split(" ");
		
		this.name = s[1];
		this.anzahl = n;
	}
	
	public String toString() {
		return name + " : " + anzahl;
	}

	@Override
	public int compareTo(Item o) {
		if(anzahl > o.anzahl) {
			return -1;
		}else if(anzahl < o.anzahl) {
			return 1;
		}
		return 0;
	}
	
}

