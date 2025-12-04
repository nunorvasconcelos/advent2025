package pt.longway.day;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class FourthExercise extends AdventBase {

	private String[] rowsOfPaper;
	private String[][] allRowsOfPaper;

	public FourthExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	private void init() {
		this.rowsOfPaper = super.fileContent.split("\r\n");
		this.allRowsOfPaper = new String[rowsOfPaper.length][rowsOfPaper[0].length()];
		for (int i = 0; i < rowsOfPaper.length; i++) {
			char[] currPaperRow = rowsOfPaper[i].toCharArray();
			for (int j = 0; j < currPaperRow.length; j++) {
				allRowsOfPaper[i][j] = String.valueOf(currPaperRow[j]);
			}
		}
	}

	@Override
	public void solveFirstPart() {
		this.init();
		int result = 0;
		for (int i = 0; i < allRowsOfPaper.length; i++) {
			String[] currRow = allRowsOfPaper[i];
			for (int j = 0; j < currRow.length; j++) {
				int adjacentNumberOfRolls = this.countAdjacentNumberOfRolls(allRowsOfPaper, i, j);
				if (adjacentNumberOfRolls < 4) {
					result++;
				}
			}
		}

		System.out.println("Forth Exercise - First Part - Number of rolls of paper that can be moved: " + result);
	}

	private int countAdjacentNumberOfRolls(String[][] allRowsOfPaper, int i, int j) {
		if (!allRowsOfPaper[i][j].equals("@")) {
			return Integer.MAX_VALUE;
		}
		int adjacentNumberOfRolls = 0;

		if (i > 0) {
			// Top left
			if (j > 0 && allRowsOfPaper[i - 1][j - 1].equals("@")) {
				adjacentNumberOfRolls++;
			}
			// Top middle
			if (allRowsOfPaper[i - 1][j].equals("@")) {
				adjacentNumberOfRolls++;
			}
			// Top right
			if (j < allRowsOfPaper[0].length - 1 && allRowsOfPaper[i - 1][j + 1].equals("@")) {
				adjacentNumberOfRolls++;
			}
		}

		if (i < allRowsOfPaper.length - 1) {
			// bottom left
			if (j > 0 && allRowsOfPaper[i + 1][j - 1].equals("@")) {
				adjacentNumberOfRolls++;
			}
			// bottom middle
			if (allRowsOfPaper[i + 1][j].equals("@")) {
				adjacentNumberOfRolls++;
			}
			// bottom right
			if (j < allRowsOfPaper[0].length - 1 && allRowsOfPaper[i + 1][j + 1].equals("@")) {
				adjacentNumberOfRolls++;
			}
		}

		// Middle left
		if (j > 0 && allRowsOfPaper[i][j - 1].equals("@")) {
			adjacentNumberOfRolls++;
		}

		// middle right
		if (j < allRowsOfPaper[0].length - 1 && allRowsOfPaper[i][j + 1].equals("@")) {
			adjacentNumberOfRolls++;
		}

		return adjacentNumberOfRolls;
	}

	@Override
	public void solveSecondPart() {
		this.init();

		int result = 0;
		int increment = 1;
		String[][] changedRowsOfPaper =allRowsOfPaper.clone();
		while (increment != 0) {
			increment = 0;
			for (int i = 0; i < allRowsOfPaper.length; i++) {
				String[] currRow = allRowsOfPaper[i];
				for (int j = 0; j < currRow.length; j++) {
					int adjacentNumberOfRolls = this.countAdjacentNumberOfRolls(allRowsOfPaper, i, j);
					if (adjacentNumberOfRolls < 4) {
						increment++;
						changedRowsOfPaper[i][j]="x";
					}
				}
			}
			result += increment;
			allRowsOfPaper = changedRowsOfPaper.clone();
		}

		System.out.println("Forth Exercise - Second Part - Number of rolls of paper that can be moved: " + result);
	}


}
