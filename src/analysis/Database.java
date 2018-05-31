package analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Database {

	private static Database database;
	private static AnalysisList list;
	
	private Database() {
		list = new AnalysisList();
		
	}
	
	public static synchronized Database getInstance() {
		if(database == null) {
			database = new Database();
		}
		return database;
	}

	public AnalysisList getList() {
		return list;
	}

	public void setList(AnalysisList list) {
		this.list = list;
	}
	
}
