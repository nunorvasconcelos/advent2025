package pt.longway.secondday;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class SecondExercise extends AdventBase {

	public SecondExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String fileContent = super.readFileToString();

		String[] ranges = fileContent.split(",");

		long result = 0;

		for (String range : ranges) {
			String[] minMax = range.split("-");
			long min = Long.valueOf(minMax[0]);
			long max = Long.valueOf(minMax[1]);
			while (min <= max) {
				if (isRepeatedSequenceTwice(min)) {
					result += min;
				}
				min++;
			}
		}

		System.out.println("Second Exercise - Sum of all invalid IDs is : " + result);

	}

	private boolean isRepeatedSequenceTwice(long number) {

		String numberString = String.valueOf(number);
		int length = numberString.length();
		if (length % 2 > 0) {
			return false;
		}

		String firstPart = numberString.substring(0, length / 2);
		String secondPart = numberString.substring(length / 2, length);
		return firstPart.equals(secondPart);
	}

	@Override
	public void solveSecondPart() {
		String fileContent = super.readFileToString();

		String[] ranges = fileContent.split(",");

		long result = 0;

		for (String range : ranges) {
			String[] minMax = range.split("-");
			long min = Long.valueOf(minMax[0]);
			long max = Long.valueOf(minMax[1]);
			while (min <= max) {
				if (isRepeatedAtLeastTwice(min)) {
					result += min;
				}
				min++;
			}
		}

		System.out.println("Second Exercise - Sum of all invalid IDs is : " + result);

	}

	private boolean isRepeatedAtLeastTwice(long number) {
		String numberString = String.valueOf(number);

		int windowSize = 1;

		while (windowSize <= numberString.length() / 2) {
			String charSequence = numberString.substring(0, windowSize);
			int i = 0;
			for (; i + windowSize <= numberString.length(); i += windowSize) {
				String compareSequence = numberString.substring(i, i + windowSize);
				if (charSequence.equals(compareSequence)) {
					continue;
				} else {
					break;
				}
			}
			if (i >= numberString.length()) {
				return true;
			}
			windowSize++;
		}

		return false;
	}
}
