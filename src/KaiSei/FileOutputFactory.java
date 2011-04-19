package KaiSei;

public class FileOutputFactory {

	public static enum FileType { CSV }
	
	public static FileOutput makeFileOutput(FileType type, Schedule sched) {
		switch (type) {
		
		case CSV:
			return new CSVOutput(sched);
			
		default:
			return null;
		
		}
	}
}
