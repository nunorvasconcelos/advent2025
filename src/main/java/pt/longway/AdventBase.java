package pt.longway;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import pt.longway.util.AdventUtil;

public abstract class AdventBase {

	protected final AdventUtil util;

	protected final String fileLocation;
	
	protected String fileContent;

	public AdventBase(AdventUtil util, String fileLocation) {
		super();
		this.util = util;
		this.fileLocation = fileLocation;
		this.readFileToString();
	}

	protected String readFileToString() {
		if(fileContent != null) {
			return fileContent;
		}
		try {
			URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(fileLocation)).toURI();

			Path path = Path.of(uri);

			fileContent = Files.readString(path);
			return fileContent;
		} catch (Exception e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}
		return null;
	}

	public abstract void solveFirstPart();

	public abstract void solveSecondPart();

}
