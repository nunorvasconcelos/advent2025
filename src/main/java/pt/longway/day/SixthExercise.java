package pt.longway.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class SixthExercise extends AdventBase {

	public SixthExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String[] content = fileContent.split("\r\n");
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		long result = 0L;
		for (String currLine : content) {
			currLine = currLine.trim().replaceAll(" +", " ");
			String[] numbers = currLine.split(" ");
			int cicle = 0;
			for (String number : numbers) {
				if (number.equals("*")) {
					long localResult = 1;
					for (Integer currNumber : map.get(cicle)) {
						localResult *= currNumber;
					}
					result += localResult;
				} else if (number.equals("+")) {
					long localResult = 0;
					for (Integer currNumber : map.get(cicle)) {
						localResult += currNumber;
					}
					result += localResult;

				} else {
					Integer currNumber = Integer.valueOf(number);
					List<Integer> numberList = map.get(cicle);
					if (numberList == null) {
						numberList = new ArrayList<>();
						map.put(cicle, numberList);
					}
					numberList.add(currNumber);
				}
				cicle++;
			}
		}

		System.out.println("Sixth Exercise - First Part - Total Number: " + result);
	}

	@Override
	public void solveSecondPart() {
		String[] content = fileContent.split("\r\n");
		HashMap<Integer, List<String>> map = new HashMap<>();
		String lastLine = content[content.length - 1];
		List<Integer> positions = new ArrayList<>();
		char[] lastLineChars = lastLine.toCharArray();

		// First get the positions where the columns change.
		for (int i = 1; i < lastLineChars.length; i++) {
			char currChar = lastLineChars[i];
			if (currChar != ' ') {
				positions.add(i);
			}
		}

		Map<Integer, Map<Integer, String>> allCephalopodNumbers = new HashMap<>();
		// Now fill the map with the strings separated by the columns
		for (String currLine : content) {
			int currIndex = 0;
			int positionAux = 0;
			if (currLine.charAt(0) != '*' && currLine.charAt(0) != '+') {
				for (Integer position : positions) {
					List<String> numberColumn = map.get(currIndex);
					if (numberColumn == null) {
						numberColumn = new ArrayList<>();
						map.put(currIndex, numberColumn);
					}
					numberColumn.add(currLine.substring(positionAux, position - 1));
					positionAux = position;
					currIndex++;
				}
				List<String> numberColumn = map.get(currIndex);
				if (numberColumn == null) {
					numberColumn = new ArrayList<>();
					map.put(currIndex, numberColumn);
				}
				numberColumn.add(currLine.substring(positionAux, currLine.length()));
			}

			if (currLine.charAt(0) == '*' || currLine.charAt(0) == '+') {
				for (int i = 0; i < map.size(); i++) {
					Map<Integer, String> cephalopodNumbers = allCephalopodNumbers.get(i);
					if (cephalopodNumbers == null) {
						cephalopodNumbers = new HashMap<>();
						allCephalopodNumbers.put(i, cephalopodNumbers);
					}
					List<String> numbersColumn = map.get(i);
					int maxDigits = this.getMaxNumberDigits(numbersColumn);
					for (String currNumber : numbersColumn) {
						for (int j = 0; j < maxDigits; j++) {
							String column = cephalopodNumbers.get(j);
							if (column == null) {
								column = "";
							}
							cephalopodNumbers.put(j, column + currNumber.charAt(j));
						}

					}
				}
			}
		}

		long result = 0L;
		String lastLineTrim = lastLine.replaceAll(" ", "");
		char[] operations = lastLineTrim.toCharArray();
		for (int i = 0; i < operations.length; i++) {
			char currOperation = operations[i];
			Map<Integer, String> numberColumn = allCephalopodNumbers.get(i);
			long currResult = 0L;
			if (currOperation == '*') {
				currResult = 1L;
				for (Integer key : numberColumn.keySet()) {
					currResult *= Integer.valueOf(numberColumn.get(key).trim());
				}
			} else {
				for (Integer key : numberColumn.keySet()) {
					currResult += Integer.valueOf(numberColumn.get(key).trim());
				}
			}
			result += currResult;
		}
		System.out.println("Sixth Exercise - Second Part - Total Number: " + result);

	}

	private int getMaxNumberDigits(List<String> numberList) {
		int maxNumber = 0;
		for (String currNumber : numberList) {
			currNumber = currNumber.trim();
			if (currNumber.charAt(0) != '*' && currNumber.charAt(0) != '+') {
				maxNumber = Math.max(maxNumber, Integer.valueOf(currNumber));
			}
		}
		int numberOfDigits = 0;
		while (maxNumber != 0) {
			maxNumber /= 10;
			numberOfDigits++;
		}

		return numberOfDigits;
	}

}
