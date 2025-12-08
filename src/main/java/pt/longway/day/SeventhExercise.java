package pt.longway.day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class SeventhExercise extends AdventBase {

	public SeventhExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String[] lines = fileContent.split("\r\n");
		String[][] diagram = new String[lines.length][lines[0].length()];

		// Prepare as a matrix to ease the navigation
		for (int i = 0; i < lines.length; i++) {
			char[] lineContent = lines[i].toCharArray();
			for (int j = 0; j < lineContent.length; j++) {
				diagram[i][j] = String.valueOf(lineContent[j]);
			}
		}

		int result = 0;
		for (int i = 0; i < diagram.length - 1; i++) {
			for (int j = 0; j < diagram[0].length; j++) {
				String currChar = diagram[i][j];
				if (currChar.equals("S")) {
					diagram[i + 1][j] = "|";
				}

				if (currChar.equals("|")) {
					if (diagram[i + 1][j].equals("^")) {
						diagram[i + 1][j - 1] = "|";
						diagram[i + 1][j + 1] = "|";
						result++;
					} else if (diagram[i + 1][j].equals(".")) {
						diagram[i + 1][j] = "|";
					}
				}
			}
		}

		System.out.println("Seventh Exercise - First Part - Total times beam was split: " + result);

	}

	@Override
	public void solveSecondPart() {

		String[] lines = fileContent.split("\r\n");
		String[][] diagram = new String[lines.length][lines[0].length()];

		// Prepare as a matrix to ease the navigation
		for (int i = 0; i < lines.length; i++) {
			char[] lineContent = lines[i].toCharArray();
			for (int j = 0; j < lineContent.length; j++) {
				diagram[i][j] = String.valueOf(lineContent[j]);
			}
		}

		long result = 0;
		for (int j = 0; j < diagram[0].length; j++) {
			String currChar = diagram[0][j];
			if (currChar.equals("S")) {
				result = this.continueBeamSplit(diagram, 1, j, new HashMap<>());
				break;
			}
		}

		System.out.println("Seventh Exercise - Second Part - Total times beam was split: " + result);
	}

	private long continueBeamSplit(String[][] diagram, int i, int j, Map<List<Integer>, Long> map) {
		if (diagram.length - 1 == i) {
			return 1;
		}

		diagram[i][j] = "|";
//		this.printDiagram(diagram);
		if (diagram[i + 1][j].equals("^")) {
			if (!map.containsKey(List.of(i + 1, j - 1))) {
				map.put(List.of(i + 1, j - 1), continueBeamSplit(diagram, i + 1, j - 1, map));
			}
			if (!map.containsKey(List.of(i + 1, j + 1))) {
				map.put(List.of(i + 1, j + 1), continueBeamSplit(diagram, i + 1, j + 1, map));
			}
			return map.get(List.of(i + 1, j - 1)) + map.get(List.of(i + 1, j + 1));
		} else {
			if (!map.containsKey(List.of(i + 1, j))) {
				map.put(List.of(i + 1, j), continueBeamSplit(diagram, i + 1, j, map));
			}
			return map.get(List.of(i + 1, j));
		}
	}
	
	/*
	 * Auxiliary function to print the whole input changed to help debug.
	 */
	private void printDiagram(String[][] diagram) {
		for (int i = 0; i < diagram.length; i++) {
			for (int j = 0; j < diagram[0].length; j++) {
				System.out.print(diagram[i][j]);
			}
			System.out.println();
		}

		System.out.println("------------------------------");
	}

}
