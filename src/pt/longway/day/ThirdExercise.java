package pt.longway.day;

import java.math.BigInteger;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class ThirdExercise extends AdventBase {

	public ThirdExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String fileContent = super.readFileToString();

		String[] banks = fileContent.split("\r\n");
		int result = 0;
		for (String bank : banks) {
			result += this.largestJoltage(bank);
		}

		System.out.println("Third Exercise - First Part - sum of all largest joltages: " + result);
	}

	private int largestJoltage(String bank) {
		char[] bankNumbers = bank.toCharArray();
		int largestJoltage = 0;
		for (int i = 0; i < bankNumbers.length - 1; i++) {
			char currNumber = bankNumbers[i];
			for (int j = i + 1; j < bankNumbers.length; j++) {
				char secondNumber = bankNumbers[j];
				int currJoltage = Integer.valueOf("" + currNumber + secondNumber);
				largestJoltage = Math.max(largestJoltage, currJoltage);
			}
		}
		return largestJoltage;
	}

	@Override
	public void solveSecondPart() {
		String fileContent = super.readFileToString();

		String[] banks = fileContent.split("\r\n");
		BigInteger result = BigInteger.ZERO;
		for (String bank : banks) {
			result = result.add(BigInteger.valueOf(this.largestJoltageTwelveDigits(bank)));
		}

		System.out.println("Third Exercise - Second Part - sum of all largest joltages: " + result);
	}

	private long largestJoltageTwelveDigits(String bank) {
		int windowSize = 11;
		char[] bankNumbers = bank.toCharArray();
		StringBuilder result = new StringBuilder();
		int lastPosition = -1;

		while (result.length() < 12) {
			int maxNumber = 0;
			for (int i = lastPosition + 1; i < bankNumbers.length - windowSize; i++) {
				int currNumber = bankNumbers[i] - '0';
				if (currNumber > maxNumber) {
					maxNumber = currNumber;
					lastPosition = i;
				}
			}
			result.append(maxNumber);
			windowSize--;
		}
//		System.out.println("Current Bank: " + bank + " with largest joltage: " + result);
		return Long.valueOf(result.toString());
	}

}
