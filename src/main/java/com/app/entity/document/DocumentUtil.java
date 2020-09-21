package com.app.entity.document;

import java.io.FileWriter;
import java.io.IOException;

public class DocumentUtil {

	public void print(String text, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.append(text);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("Wrong URI received.");
		}
	}

}
