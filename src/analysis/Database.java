package analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author alex-
 * @version 1
 * 
 * Klasse zur Erstellung der Database
 */

public class Database {

	private static Database database;
	private static AnalysisList list;
	
	private Database() {
		list = new AnalysisList();
		
	}
	/**
	 * Sollte die Database leer sein wird eine neue angelegt
	 * @return Database
	 */
	public static synchronized Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}
	/**
	 * Getter und Setter Klasse für eine Liste
	 * @return List
	 */
	public AnalysisList getList() {
		return list;
	}

	public void setList(AnalysisList list) {
		this.list = list;
	}
	
}
