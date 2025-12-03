package pt.longway;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import pt.longway.util.AdventUtil;

public abstract class AdventBase {

	protected final AdventUtil util;

	protected final String fileLocation;

	public AdventBase(AdventUtil util, String fileLocation) {
		super();
		this.util = util;
		this.fileLocation = fileLocation;
	}

	protected String readFileToString() {
		try {
			URI uri = Objects.requireNonNull(getClass().getClassLoader().getResource(fileLocation)).toURI();

			Path path = Path.of(uri);

			return Files.readString(path);
		} catch (Exception e) {
			System.err.println("An error occurred while reading the file: " + e.getMessage());
		}
		return null;
	}

	public abstract void solveFirstPart();

	public abstract void solveSecondPart();

}
