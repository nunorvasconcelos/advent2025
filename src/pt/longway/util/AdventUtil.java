package pt.longway.util;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class AdventUtil {

	public String readFileToString(String filePath) {
		try {
			URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).toURI();

			Path path = Path.of(uri);

			return Files.readString(path);
		} catch (Exception e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}
		return null;
	}

}
