package fi.helsinki.cs.scheduler3000;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileOutput {
	
	public abstract String getContents();
	
	public void writeToFile(String filename) {
		String contents = getContents();
		
		File f = new File(filename);
		// If there is a file with the same name, delete the old file
        if(f.exists())
            f.delete();

        BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filename));
			out.write(contents);
		} catch (IOException e) {
			System.out.println("Ohjelma ei onnistunut kirjoittamaan tietoja tiedostoon.");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}
}
